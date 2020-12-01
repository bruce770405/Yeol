package tw.com.mbproject.yeol.util;

import org.springframework.util.ClassUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringUtils {

    private SpringUtils() {

    }

    /**
     * Return an instance, which may be shared or independent, of the specified bean.
     *
     * @param <T>
     * @param name the name of the bean to retrieve
     * @return
     */
    public static <T> T getBean(final String name, final Class<T> requiredType) {
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        if (context != null) {
            return context.getBean(name, requiredType);
        } else {
            throw new UnsupportedOperationException("can not found spring application context.");
        }
    }

    /**
     * returns Class instances load by current thread. this can pass CheckMARX.
     *
     * @param <T>
     * @param fullClassName the name of the Class
     * @return
     * @throws ClassNotFoundException
     * @throws LinkageError
     */
    public static <T> Class<T> findClass(final String fullClassName) throws ClassNotFoundException, LinkageError {
        return findClass(fullClassName, Thread.currentThread().getContextClassLoader());
    }

    /**
     * returns Class instances. this can pass CheckMARX.
     *
     * @param <T>
     * @param fullClassName the name of the Class
     * @param classLoader   the class loader to use
     * @return
     * @throws ClassNotFoundException
     * @throws LinkageError
     */
    public static <T> Class<T> findClass(String fullClassName, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
        @SuppressWarnings("unchecked")
        Class<T> ret = (Class<T>) ClassUtils.forName(fullClassName, classLoader);
        return ret;
    }
}
