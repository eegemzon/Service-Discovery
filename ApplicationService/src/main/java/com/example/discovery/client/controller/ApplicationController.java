package com.example.discovery.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applicationService")
public class ApplicationController {

	@Value("${service.instance.name}")
	private String instanceName;

	@GetMapping
	public ResponseEntity<String> getInstanceName() {
		return ResponseEntity.ok(instanceName);
	}
}
