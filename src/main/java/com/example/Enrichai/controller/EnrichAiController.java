package com.example.Enrichai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Enrichai.model.GPSRequestModel;
import com.example.Enrichai.model.HeartbeatRequestModel;
import com.example.Enrichai.model.LoginRequestModel;
import com.example.Enrichai.service.EnrichAiService;

@RestController
public class EnrichAiController {
	@Autowired
	EnrichAiService enrichAiService;
	
	@RequestMapping("/login")
	public @ResponseBody LoginRequestModel login(@RequestBody String request) {
		LoginRequestModel loginRequestModel=enrichAiService.loginRequestDecode(request);
		return loginRequestModel;
	}
	
	@RequestMapping("/heartbeat")
	public @ResponseBody HeartbeatRequestModel heartbeat(@RequestBody String request) {
		HeartbeatRequestModel heartBeatRequestModel=enrichAiService.heartbeatRequestDecode(request);
		return heartBeatRequestModel;
	}
	
	@RequestMapping("/gps")
	public @ResponseBody GPSRequestModel gps(@RequestBody String request) {
		GPSRequestModel gpsRequestModel=enrichAiService.gpsRequestDecode(request);
		return gpsRequestModel;
	}
}
