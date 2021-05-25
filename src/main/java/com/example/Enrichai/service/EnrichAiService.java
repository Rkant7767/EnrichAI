package com.example.Enrichai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Enrichai.model.GPSRequestModel;
import com.example.Enrichai.model.HeartbeatRequestModel;
import com.example.Enrichai.model.HeartbeatResponseModel;
import com.example.Enrichai.model.LoginRequestModel;
import com.example.Enrichai.model.LoginResponseModel;
@Service
public class EnrichAiService {
	
	public LoginRequestModel loginRequestDecode(String request) {
		String requestarr []= request.split("\\s");
		LoginRequestModel loginRequestModel= new LoginRequestModel(requestarr);
		
		LoginResponseModel loginResponseModel= new LoginResponseModel(loginRequestModel);
		loginRequestModel.setResponse(loginResponseModel.getResponse());
		System.out.println(loginRequestModel.toString());
		return loginRequestModel;
		
	}

	public HeartbeatRequestModel heartbeatRequestDecode(String request) {
		String requestarr []= request.split("\\s");
		HeartbeatRequestModel heartbeatRequestModel= new HeartbeatRequestModel(requestarr);
		HeartbeatResponseModel heartBeatResponseModel= new HeartbeatResponseModel(heartbeatRequestModel);
		heartbeatRequestModel.setResponse(heartBeatResponseModel.getResponse());
		System.out.println(heartbeatRequestModel.toString());
		return heartbeatRequestModel;
	}
	
	
	public GPSRequestModel gpsRequestDecode(String request) {
		String requestarr []= request.split("\\s");
		GPSRequestModel gpsRequestModel= new GPSRequestModel(requestarr) ;
		return gpsRequestModel;
	} 

}
