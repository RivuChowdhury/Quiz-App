package com.accenture.course_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	@Autowired
	LoadBalancerClient loadBalancerClient;
	
	@Value("${questionservice.base.url}")
	private String questionBaseURL;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	@LoadBalanced
	public WebClient webClient() {
		/*ServiceInstance serviceInstance=loadBalancerClient.choose("question-service");
		String uri=serviceInstance.getUri().toString();
		return WebClient.builder().baseUrl(uri).build();*/
		//return WebClient.builder().baseUrl(http://question-service).build();
		return WebClient.builder()
				.baseUrl("http://question-service")  // service name only
				.build();
	}

}
