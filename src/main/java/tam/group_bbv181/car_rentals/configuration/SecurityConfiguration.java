package tam.group_bbv181.car_rentals.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import tam.group_bbv181.car_rentals.services.login.impls.LoginServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  /*  @Override
     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
         auth.inMemoryAuthentication()
                 .withUser("user")
                 .password("user")
                 .roles("USER")
                 .and()
                 .withUser("admin")
                 .password("admin")
                 .roles("ADMIN");
     }
     @Bean
     public PasswordEncoder getPasswordEncoder(){
         return NoOpPasswordEncoder.getInstance();
     }
 }*/
   @Autowired
   private LoginServiceImpl userService;

    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(bcryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling().accessDeniedPage("/login")
                .and()
                .authorizeRequests()
                .regexMatchers("^\\S*.js|\\S*.css$").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/readme.txt", "/css/*", "/CarRentals/signUp", "/image/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/CarRentals/signIn").defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
         http.csrf().disable();
    }



   /* @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("user")
                .antMatchers("/*").permitAll()
                .and().formLogin().loginPage("/login").permitAll().and().logout().permitAll()
        ;
        http.csrf().disable();
    }
    */

}