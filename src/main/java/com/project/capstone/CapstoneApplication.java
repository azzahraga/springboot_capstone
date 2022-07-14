package com.project.capstone;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.capstone.domain.dao.Role;
import com.project.capstone.domain.dao.RoleEnum;
import com.project.capstone.repository.RoleRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootApplication
// @EnableJpaRepositories(repositoryFactoryBeanClass = CustomJpaRepositoryFactoryBean.class)
public class CapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer getCorsConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000", "https://springboot-postgresql-capstone.herokuapp.com","https://fanciful-trifle-3600ae.netlify.app/")
				.allowCredentials(true).allowedMethods("GET", "PUT", "POST", "DELETE");
			}
		};
	}

	@Autowired
	private RoleRepository roleRepository;
	
	@Bean
	InitializingBean sendDatabase() {
    return () -> {
		Role checkAdmin = roleRepository.findByName(RoleEnum.ROLE_ADMIN).orElse(null);
		if(checkAdmin == null) {
			log.info("Set role admin");
			Role roleAdmin = new Role();
			roleAdmin.setName(RoleEnum.ROLE_ADMIN);
			roleRepository.save(roleAdmin);
		}	
		Role checkDokter = roleRepository.findByName(RoleEnum.ROLE_DOKTER).orElse(null);
				if(checkDokter == null) {
					log.info("Set role user");
					Role roleDokter = new Role();
					roleDokter.setName(RoleEnum.ROLE_DOKTER);
					roleRepository.save(roleDokter);
				}
			};
   }

}
