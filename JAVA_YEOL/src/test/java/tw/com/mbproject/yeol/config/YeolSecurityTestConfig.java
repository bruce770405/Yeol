package tw.com.mbproject.yeol.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import tw.com.mbproject.yeol.config.security.YeolUserDetails;
import tw.com.mbproject.yeol.constant.Role;

import java.util.Arrays;
import java.util.Collections;

@TestConfiguration
public class YeolSecurityTestConfig {
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        System.out.println("register in memory UserDetails");
        YeolUserDetails user = new YeolUserDetails();
        user.setUsername("yeol_user");
        user.setPassword("1qaz3edc");
        user.setEnabled(true);
        user.setRoles(Collections.singletonList(Role.USER));

        YeolUserDetails admin = new YeolUserDetails();
        admin.setUsername("yeol_admin");
        admin.setPassword("1qaz3edc");
        admin.setEnabled(true);
        admin.setRoles(Collections.singletonList(Role.ADMIN));

        return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    }
}
