package com.example.Enrichai.model;

import java.util.Arrays;

import com.example.Enrichai.util.Util;

public class HeartbeatRequestModel {
	private int length;
	private int protocolNumber;
	private int terminalInfoContent=0;
	private String[] terminalInfoDecoded= new String [8];
	private double voltageLevel=0;
	private String GSMsignalStrength="";
	private String language="";
	private int informationSerialNo=0;
	private String errorcheck="";
	private String Response="";
	public HeartbeatRequestModel(String [] request) {
		this.length=Integer.parseInt(request[2],16);
		this.protocolNumber=Integer.parseInt(request[3],16);
		this.terminalInfoContent=Integer.parseInt(request[4],16);
		String terminalInfo=Integer.toBinaryString(this.terminalInfoContent);
		decodeTerminalInfo( terminalInfo);
		double sum=Integer.parseInt(request[5],16)+Integer.parseInt(request[6],16);
		this.voltageLevel=(sum)/100;
		decodeGSMsignalStrength( request);
		decodeLanguage(request);
		this.informationSerialNo=Integer.parseInt(request[10])+Integer.parseInt(request[11]);
		Util util= new Util();
		errorcheck=util.crc16(Integer.parseInt(request[12]))+" "+util.crc16(Integer.parseInt(request[13]));
	}
	
	public void decodeTerminalInfo(String terminalInfo) {
		
		for(int i=0;i<terminalInfo.length();i++) {
			if(terminalInfo.charAt(i)=='1') {
				if(i==0) {
					terminalInfoDecoded[i]="Oil and electricity disconnected";
				}
				if(i==1) {
					terminalInfoDecoded[i]="GPS tracking is on";
				}
				if(i==5) {
					terminalInfoDecoded[i]="Charge On";
				}
				if(i==6) {
					terminalInfoDecoded[i]="ACC high";
				}
				if(i==7) {
					terminalInfoDecoded[i]="Defense Activated";
				}
			}
			else {
				if(i==0) {
					terminalInfoDecoded[i]="Oil and electricity connected";
				}
				if(i==1) {
					terminalInfoDecoded[i]="GPS tracking is off";
				}
				if(i==5) {
					terminalInfoDecoded[i]="Charge Off";
				}
				if(i==6) {
					terminalInfoDecoded[i]="ACC low";
				}
				if(i==7) {
					terminalInfoDecoded[i]="Defense Deactivated";
				}
			}
		}
	}
	
	
	public void decodeGSMsignalStrength(String [] request) {
		if(request[7].equals("04")) {
			GSMsignalStrength="strong signal";
		}
		else if(request[7].equals("03")) {
			GSMsignalStrength="good signal";
		}
		else if(request[7].equals("02")) {
			GSMsignalStrength="very weak signal";
		}
		else if(request[7].equals("01")) {
			GSMsignalStrength="extremely weak signal";
		}
		else if(request[7].equals("00")) {
			GSMsignalStrength="no signal";
		}
	}
	
	
	public void decodeLanguage(String [] request) {
		if(request[9].equals("01")) {
			this.language="Chinese";
		}
		else if( request[9].equals("02")){
				this.language="English";
		}
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

	public int getTerminalInfoContent() {
		return terminalInfoContent;
	}

	public void setTerminalInfoContent(int terminalInfoContent) {
		this.terminalInfoContent = terminalInfoContent;
	}

	public String[] getTerminalInfoDecoded() {
		return terminalInfoDecoded;
	}

	public void setTerminalInfoDecoded(String[] terminalInfoDecoded) {
		this.terminalInfoDecoded = terminalInfoDecoded;
	}

	public double getVoltageLevel() {
		return voltageLevel;
	}

	public void setVoltageLevel(double voltageLevel) {
		this.voltageLevel = voltageLevel;
	}

	public String getGSMsignalStrength() {
		return GSMsignalStrength;
	}

	public void setGSMsignalStrength(String gSMsignalStrength) {
		GSMsignalStrength = gSMsignalStrength;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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
		return "HeartbeatRequestModel [length=" + length + ", protocolNumber=" + protocolNumber
				+ ", terminalInfoContent=" + terminalInfoContent + ", terminalInfoDecoded="
				+ Arrays.toString(terminalInfoDecoded) + ", voltageLevel=" + voltageLevel + ", GSMsignalStrength="
				+ GSMsignalStrength + ", language=" + language + ", informationSerialNo=" + informationSerialNo
				+ ", errorcheck=" + errorcheck + ", Response=" + Response + "]";
	}
	
	
	
}
