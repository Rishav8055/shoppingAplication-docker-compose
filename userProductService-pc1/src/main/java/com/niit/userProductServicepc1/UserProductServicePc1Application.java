package com.niit.userProductServicepc1;

import com.niit.userProductServicepc1.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class UserProductServicePc1Application {
	public static void main(String[] args) {
		SpringApplication.run(UserProductServicePc1Application.class, args);


	}

	@Bean
	public FilterRegistrationBean jwtFilterBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/addproduct/*");
		return filterRegistrationBean;
	}


}
