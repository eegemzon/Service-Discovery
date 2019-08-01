package com.example.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private EurekaClient eurekaClient;
	
	@Value("${application.service.name}")
	private String applicationServiceName;
	
	@Autowired
	private RestTemplateBuilder restTemplateBuilder;
	
	@GetMapping
	private ResponseEntity<String> callAppServ() {
		RestTemplate restTemplate = restTemplateBuilder.build();
		InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(applicationServiceName, false); ///HTTP only
		ResponseEntity<String> response = restTemplate.exchange(instanceInfo.getHomePageUrl(), HttpMethod.GET, null, String.class);
		System.out.println(response.getBody());
		return ResponseEntity.ok(response.getBody());
	}
}
