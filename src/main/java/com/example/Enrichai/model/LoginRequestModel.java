package com.example.Enrichai.model;

import java.util.TimeZone;

import com.example.Enrichai.util.Util;

public class LoginRequestModel {
	
	private int length;
	private int protocolNumber;
	private String terminalID="";
	private String identificationCode="";
	private String timeZoneLanguage="";
	private int informationSerialNo=0;
	private String errorcheck="";
	private String Response="";
	public LoginRequestModel (String[] request){
		this.length=Integer.parseInt(request[2],16);
		this.protocolNumber=Integer.parseInt(request[3],16);
		for(int i=4;i<12;i++) {
			this.terminalID=terminalID+Integer.parseInt(request[i],16);
		}	
		this.identificationCode+=Integer.parseInt(request[12]+request[13],16);
		this.informationSerialNo=Integer.parseInt(request[16]+request[17],16);
		for(int i=18;i<request.length-2;i++) {
			Util util= new Util();
			errorcheck+=" "+util.crc16(Integer.parseInt(request[i]));
		}
		
	}
	LoginRequestModel() {
		
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getProtocolNumber() {
		return protocolNumber;
	}
	public void setProtocolNumber(int protocolNumber) {
		this.protocolNumber = protocolNumber;
	}
	public String getTerminalID() {
		return terminalID;
	}
	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}
	public String getIdentificationCode() {
		return identificationCode;
	}
	public void setIdentificationCode(String identificationCode) {
		this.identificationCode = identificationCode;
	}
	public String getTimeZoneLanguage() {
		return timeZoneLanguage;
	}
	public void setTimeZoneLanguage(String timeZoneLanguage) {
		this.timeZoneLanguage = timeZoneLanguage;
	}
	
	public int getInformationSerialNo() {
		return informationSerialNo;
	}


	public void setInformationSerialNo(int informationSerialNo) {
		this.informationSerialNo = informationSerialNo;
	}


	public String getErrorcheck() {
		return errorcheck;
	}


	public void setErrorcheck(String errorcheck) {
		this.errorcheck = errorcheck;
	}
	

	public String getResponse() {
		return Response;
	}


	public void setResponse(String response) {
		Response = response;
	}


	@Override
	public String toString() {
		return "LoginRequestModel [length=" + length + ", protocolNumber=" + protocolNumber + ", terminalID="
				+ terminalID + ", identificationCode=" + identificationCode + ", timeZoneLanguage=" + timeZoneLanguage
				+ ", informationSerialNo=" + informationSerialNo + ", errorcheck=" + errorcheck + "]";
	}
	
	
	
	

}
