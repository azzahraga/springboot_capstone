package com.project.capstone.service.JwtServices;
// package com.project.capstone.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.project.capstone.security.jwt.AuthenticationTokenFilter;
// import com.project.capstone.security.jwt.JwtAuthenticationEntryPoint;
// import com.project.capstone.service.implementastions.JwtUserDetailsService;

// @Configuration
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(
//         prePostEnabled = true)
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//     @Autowired
//     JwtUserDetailsService userDetailsService;

//     @Autowired
//     private JwtAuthenticationEntryPoint unauthorizedHandler;

//     @Bean
//     public AuthenticationTokenFilter authenticationJwtTokenFilter() {
//         return new AuthenticationTokenFilter();
//     }

//     @Override
//     public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//         authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//     }

//     @Bean
//     @Override
//     public AuthenticationManager authenticationManagerBean() throws Exception {
//         return super.authenticationManagerBean();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.cors().and().csrf().disable()
//                 .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                 .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//                 .antMatchers("/api/test/**").permitAll()
//                 .anyRequest().authenticated();

//         http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//     }
// }