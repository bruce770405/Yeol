package tw.com.mbproject.yeol.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
public class RsaUtils {

    private final static Map<String, String> keyMap;
    /**
     * public key.
     */
    public static final String PUBLIC_KXY = "publicKxy";
    /**
     * private key.
     */
    public static final String PRIVATE_KXY = "privateKxy";

    protected static final String ALGORITHM_RSA = "RSA";
    protected static final String ALGORITHM_SIGN = "MD5withRSA";

    static {
        keyMap = generatorKxyPair();
    }

    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    public static String encryptBASE64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * generatorKxyPair
     */
    protected static Map<String, String> generatorKxyPair() {
        Map<String, String> keyMap = new ConcurrentHashMap<>();

        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM_RSA);
            keyPairGen.initialize(1024);
            KeyPair keyPair = keyPairGen.generateKeyPair();
            // 1. 產生public key
            RSAPublicKey rsaPublicKxy = (RSAPublicKey) keyPair.getPublic();
            byte[] keyBs = rsaPublicKxy.getEncoded();
            String publicKxy = encryptBASE64(keyBs);
            keyMap.put(PUBLIC_KXY, publicKxy);

            // 2. 產生
            RSAPrivateKey rsaPrivateKxy = (RSAPrivateKey) keyPair.getPrivate();
            keyBs = rsaPrivateKxy.getEncoded();
            String privateKxy = encryptBASE64(keyBs);
            keyMap.put(PRIVATE_KXY, privateKxy);
        } catch (NoSuchAlgorithmException e) {
            log.error("generator Kxy Pair fail", e);
        }

        return keyMap;
    }

    /**
     * get Public Kxy
     */
    public static PublicKey getPublicKxy(String publicKxy) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec publicKxySpec = new X509EncodedKeySpec(decryptBASE64(publicKxy));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePublic(publicKxySpec);
    }

    /**
     * get Private Kxy
      */
    public static PrivateKey getPrivateKxy(String privateKxy) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec privateKxySpec = new PKCS8EncodedKeySpec(decryptBASE64(privateKxy));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
        return keyFactory.generatePrivate(privateKxySpec);
    }

    /**
     * Public Kxy encrypt
     *
     * @param source
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptionByPublicKxy(String source, String key) throws Exception {
        PublicKey publicKxy = getPublicKxy(key);
        Cipher cipher = Cipher.getInstance(publicKxy.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKxy);
        cipher.update(source.getBytes(StandardCharsets.UTF_8));
        return encryptBASE64(cipher.doFinal());
    }

    /**
     * Public Kxy decrypt
     *
     * @param target
     * @throws Exception
     */
    public static String decryptionByPublicKxy(String target, String key) throws Exception {
        PublicKey publicKxy = getPublicKxy(key);
        Cipher cipher = Cipher.getInstance(publicKxy.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKxy);
        cipher.update(decryptBASE64(target));
        return new String(cipher.doFinal(), StandardCharsets.UTF_8);
    }

    /**
     * Public Kxy verify
     *
     * @return
     * @throws Exception
     */
    public static boolean verifyByPublicKxy(String target, String sign, String key) throws Exception {
        PublicKey publicKxy = getPublicKxy(key);
        Signature signature = Signature.getInstance(ALGORITHM_SIGN);
        signature.initVerify(publicKxy);
        signature.update(target.getBytes(StandardCharsets.UTF_8));

        if (signature.verify(decryptBASE64(sign))) {
            return true;
        }

        return false;
    }

    /**
     * Private Kxy encrypt
     *
     * @param source
     * @return
     * @throws Exception
     */
    public static String encryptionByPrivateKxy(String source, String key) throws Exception {
        PrivateKey privateKxy = getPrivateKxy(key);
        Cipher cipher = Cipher.getInstance(privateKxy.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKxy);
        cipher.update(source.getBytes(StandardCharsets.UTF_8));
        return encryptBASE64(cipher.doFinal());
    }

    /**
     * Private Kxy decrypt
     *
     * @param target
     * @throws Exception
     */
    public static String decryptionByPrivateKxy(String target, String key) throws Exception {
        PrivateKey privateKxy = getPrivateKxy(key);
        Cipher cipher = Cipher.getInstance(privateKxy.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKxy);
        cipher.update(decryptBASE64(target));
        return new String(cipher.doFinal(), StandardCharsets.UTF_8);
    }

    /**
     * Private Kxy sign
     *
     * @param target
     * @return
     * @throws Exception
     */
    public static String signByPrivateKxy(String target, String key) throws Exception {
        PrivateKey privateKxy = getPrivateKxy(key);
        Signature signature = Signature.getInstance(ALGORITHM_SIGN);
        signature.initSign(privateKxy);
        signature.update(target.getBytes(StandardCharsets.UTF_8));
        return encryptBASE64(signature.sign());
    }

    /**
     * Convert byte to String
     *
     * @param b
     * @return String
     */
    public static String toHex(byte b) {
        return ("" + "0123456789ABCDEF".charAt(0xf & b >> 4) + "0123456789ABCDEF".charAt(b & 0xf));
    }

    public static Map<String, String> kxyMap() {
        return keyMap;
    }

}
