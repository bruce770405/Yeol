package tw.com.mbproject.yeol.config.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tw.com.mbproject.yeol.controller.response.code.ErrCode;
import tw.com.mbproject.yeol.exception.YeolException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Log4j2
@Component
public class PBKDF2Encoder implements PasswordEncoder {
    @Value("${yeol.password.encoder.secret:default}")
    private String secret;

    @Value("${yeol.password.encoder.iteration:1024}")
    private Integer iteration;

    @Value("${yeol.password.encoder.key.length:256}")
    private Integer keylength;

    @Override
    public String encode(CharSequence charSequence) {
        try {
            byte[] result = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512")
                    .generateSecret(new PBEKeySpec(charSequence.toString().toCharArray(), secret.getBytes(), iteration, keylength)).getEncoded();

            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.info(e.getMessage());
            throw new YeolException(ErrCode.FAIL);
        }
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encode(charSequence).equals(s);
    }
}
