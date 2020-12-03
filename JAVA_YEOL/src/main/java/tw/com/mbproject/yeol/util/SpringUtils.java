package tw.com.mbproject.yeol.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Return an instance, which may be shared or independent, of the specified bean.
     *
     * @param <T>
     * @param name the name of the bean to retrieve
     * @return
     */
    public static <T> T getBean(final String name, final Class<T> requiredType) {
        if (applicationContext != null) {
            return applicationContext.getBean(name, requiredType);
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
     * @return Class<T>
     * @throws ClassNotFoundException
     * @throws LinkageError
     */
    public static <T> Class<T> findClass(String fullClassName, ClassLoader classLoader) throws ClassNotFoundException, LinkageError {
        @SuppressWarnings("unchecked")
        Class<T> ret = (Class<T>) ClassUtils.forName(fullClassName, classLoader);
        return ret;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }
}
