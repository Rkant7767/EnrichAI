package com.example.Enrichai.model;

public class LoginResponseModel {
	private String startBit= "78 78";
	private String length="05";
	private String protocolNo="01";
	private  String informationSerialNo="";
	private String errorcheck="";
	private String stopBit="0D 0A";
	public LoginResponseModel(LoginRequestModel loginRequestModel) {
		this.informationSerialNo=Integer.toHexString(loginRequestModel.getInformationSerialNo()+1);
		if(informationSerialNo.length()==1) {
			this.informationSerialNo="0"+this.informationSerialNo;
		}
		
		String error= loginRequestModel.getErrorcheck().trim();
		String arr[]=error.split("\\s");
		for(String s: arr) {
			this.errorcheck+=" "+Integer.toHexString(Integer.parseInt(s));
		}
		
		this.errorcheck=this.errorcheck.trim();
		
	}
	public String getStartBit() {
		return startBit;
	}
	public void setStartBit(String startBit) {
		this.startBit = startBit;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getProtocolNo() {
		return protocolNo;
	}
	public void setProtocolNo(String protocolNo) {
		this.protocolNo = protocolNo;
	}
	public String getInformationSerialNo() {
		return informationSerialNo;
	}
	public void setInformationSerialNo(String informationSerialNo) {
		this.informationSerialNo = informationSerialNo;
	}
	public String getErrorcheck() {
		return errorcheck;
	}
	public void setErrorcheck(String errorcheck) {
		this.errorcheck = errorcheck;
	}
	public String getStopBit() {
		return stopBit;
	}
	public void setStopBit(String stopBit) {
		this.stopBit = stopBit;
	}
	@Override
	public String toString() {
		return "LoginResponseModel [startBit=" + startBit + ", length=" + length + ", protocolNo=" + protocolNo
				+ ", informationSerialNo=" + informationSerialNo + ", errorcheck=" + errorcheck + ", stopBit=" + stopBit
				+ "]";
	}
	
	
	public String getResponse() {
		return startBit+ " "+length+" "+protocolNo+" "+informationSerialNo+" "+errorcheck+" "+stopBit;
	}
	

}
