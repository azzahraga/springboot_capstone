// package com.project.capstone.config;


// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.project.capstone.security.jwt.SecurityFilter;

// import lombok.RequiredArgsConstructor;

// import org.springframework.security.authentication.AuthenticationManager;

// @Configuration
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
//     private final UserDetailsService userService;
//     private final SecurityFilter securityFilter;
    
//     @Bean
//     public PasswordEncoder passwordEncoder(){
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.userDetailsService(userService)
//             .passwordEncoder(passwordEncoder());
//     }

//     @Bean
//     @Override
//     protected AuthenticationManager authenticationManager() throws Exception {
//         return super.authenticationManager();
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.headers().frameOptions().disable();
//         http.csrf().disable()
//             .authorizeRequests()
//             .antMatchers("/api/auth/**").permitAll()
//             .antMatchers("/**").hasAnyRole("ADMIN","DOKTER")
//             .anyRequest().authenticated();
//         //remove session
//         http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//         //filter jwt
//         http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
//     }


// }
