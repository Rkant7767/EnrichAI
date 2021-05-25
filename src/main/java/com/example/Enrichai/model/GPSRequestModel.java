package com.example.Enrichai.model;

import java.util.Arrays;

import com.example.Enrichai.util.Util;

public class GPSRequestModel {
	private int length;
	private int protocolNumber;
	private String dateTime="";
	private String quantity="";
	private String positioningSatelliteno;
	private double Latitude;
	private double Longitude;
	private double speed;
	private String course;
	private String [] status= new String [4];
	private int mcc;
	private int mnc;
	private  int lac;
	private int cellId;
	private String acc;
	private String dataupload;
	private int informationSerialNo=0;
	private String errorcheck="";
	private String Response="";
	private String GpsRealTime="";
	private double mileage;
	public GPSRequestModel(String [] request) {
		this.length=Integer.parseInt(request[2],16);
		this.protocolNumber=Integer.parseInt(request[3],16);
		decodeDateTime(request);
		decodeQuantity(request);
		decodeLatitudeLongitudeSpeed(request);
		decodeCourseandStatus(request);
		this.mcc=Integer.parseInt(request[22]+request[23],16);
		this.mnc=Integer.parseInt(request[24],16);
		this.lac=Integer.parseInt(request[25]+request[26],16);
		this.cellId=Integer.parseInt(request[27]+request[28]+request[29],16);
		decodeAcc(request);
		decodeDataUpload(request);
		if(request[32].equals("00")) {
			GpsRealTime="Real time upload";
		}
		else if (request[32].equals("01")){
			GpsRealTime="Re-upload";
		}
		if(request.length>39) {
			String str=request[33]+request[34]+request[35]+request[36];
			mileage=Integer.parseInt(str,16);
			mileage=mileage/100;
			informationSerialNo=Integer.parseInt(request[37]+request[38],16);
			Util util= new Util();
			errorcheck+=" "+util.crc16(Integer.parseInt(request[38]));
			errorcheck+=" "+util.crc16(Integer.parseInt(request[39]));
			errorcheck=errorcheck.trim();
		}
		else {
			informationSerialNo=Integer.parseInt(request[33]+request[34],16);
			Util util= new Util();
			errorcheck+=" "+util.crc16(Integer.parseInt(request[35]));
			errorcheck+=" "+util.crc16(Integer.parseInt(request[36]));
			errorcheck=errorcheck.trim();
		}
		
	}

	private void decodeDataUpload(String[] request) {
		if(request[31].equals("00")) {
			this.dataupload="Upload by time interval";
		}
		else if(request[31].equals("01")) {
			this.dataupload="Upload by distance interval";
		}
		else if(request[31].equals("02")) {
			this.dataupload="Inflection point upload";	
				}
		else if(request[31].equals("03")) {
			this.dataupload="ACC status upload";
		}
		else if(request[31].equals("04")) {
			this.dataupload="Re-upload the last GPS point when back to static.";
		}
		else if(request[31].equals("05")) {
			this.dataupload="Upload the last effective point when network recovers";
		}
		else if(request[31].equals("06")) {
			this.dataupload="Update ephemeris and upload GPS data compulsorily";
		}
		else if(request[31].equals("07")) {
			this.dataupload="Upload location when side key triggered";
		}
		else if(request[31].equals("08")) {
			this.dataupload="Upload location after power on";
		}
		else if(request[31].equals("09")) {
			this.dataupload="Unused";
		}
		else if(request[31].equals("0A")) {
			this.dataupload="Upload the last longitude and latitude when device is static";
		}
		else if(request[31].equals("0B")) {
			this.dataupload="time updated";
		}
		else if(request[31].equals("0C")) {
			this.dataupload="time updated";
		}
		
		else if(request[31].equals("0D")) {
			this.dataupload="Upload the last longitude and latitude when device is static";
		}
		else if(request[31].equals("0E")) {
			this.dataupload="Gpsdup upload (Upload regularly in a static state.).";
		}
		
	}

	private void decodeAcc(String[] request) {
		if(request[30].equals("01")) {
			this.acc="high";
		}
		else if (request[30].equals("00")){
			this.acc="low";
		}
		else {
			this.acc="not available";
		}
		
	}

	private void decodeCourseandStatus(String[] request) {
		String str="";
		str+=Integer.toBinaryString(Integer.parseInt(request[20],16));
		System.out.println(str);
		for(int i=0;i<8-str.length();i++) {
			str="0"+str;
		}
		String str1="";
		str1+=Integer.toBinaryString(Integer.parseInt(request[21],16));
		for(int i=0;i<8-str1.length();i++) {
			str1="0"+str1;
		}
		System.out.println(str1);
		str=str+str1;
		int counter=0;
		
			if( str.charAt(2)=='0') {
				this.status[counter++]="real time GPS";
			}
			else {
				this.status[counter++]="differential " + 
						"positioning";
			}
			
			if( str.charAt(3)=='1') {
				this.status[counter++]="GPS has been positioned";
			}
			else {
				this.status[counter++]="GPS has not been positioned";
			}
			
			if( str.charAt(2)=='0') {
				this.status[counter++]="East Longitude";
			}
			else {
				this.status[counter++]="West Longitude";
			}
			
			if( str.charAt(2)=='1') {
				this.status[counter++]="North Latitude";
			}
			else {
				this.status[counter++]="South Latitude";
			}
			
			this.course+=Integer.parseInt(str.substring(6),16);
		
	}

	private void decodeLatitudeLongitudeSpeed(String[] request) {
		String str="";
		for(int i=11;i<15;i++) {
			str+=request[i];
		}
		double d=Integer.parseInt(str,16);
		this.Latitude=d/1800000;
		str="";
		for(int i=15;i<19;i++) {
			str+=request[i];
		}
		d=Integer.parseInt(str,16);
		this.Longitude=d/1800000;
		this.speed=Integer.parseInt(request[19],16);
	}

	private void decodeQuantity(String[] request) {
		this.quantity=Character.toString(request[10].charAt(0));
		this.positioningSatelliteno+=Integer.parseInt(Character.toString(request[10].charAt(1)),16);
		
	}

	private void decodeDateTime(String[] request) {
		
			this.dateTime=Integer.parseInt(request[4], 16)+"/"+Integer.parseInt(request[5], 16)+"/"+Integer.parseInt(request[6], 16)+" "+
					Integer.parseInt(request[7], 16)+":"+Integer.parseInt(request[8], 16)+":"+Integer.parseInt(request[9], 16);
		
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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPositioningSatelliteno() {
		return positioningSatelliteno;
	}

	public void setPositioningSatelliteno(String positioningSatelliteno) {
		this.positioningSatelliteno = positioningSatelliteno;
	}

	public double getLatitude() {
		return Latitude;
	}

	public void setLatitude(double latitude) {
		Latitude = latitude;
	}

	public double getLongitude() {
		return Longitude;
	}

	public void setLongitude(double longitude) {
		Longitude = longitude;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String[] getStatus() {
		return status;
	}

	public void setStatus(String[] status) {
		this.status = status;
	}

	public int getMcc() {
		return mcc;
	}

	public void setMcc(int mcc) {
		this.mcc = mcc;
	}

	public int getMnc() {
		return mnc;
	}

	public void setMnc(int mnc) {
		this.mnc = mnc;
	}

	public int getLac() {
		return lac;
	}

	public void setLac(int lac) {
		this.lac = lac;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public String getDataupload() {
		return dataupload;
	}

	public void setDataupload(String dataupload) {
		this.dataupload = dataupload;
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

	public String getGpsRealTime() {
		return GpsRealTime;
	}

	public void setGpsRealTime(String gpsRealTime) {
		GpsRealTime = gpsRealTime;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}

	@Override
	public String toString() {
		return "GPSRequestModel [length=" + length + ", protocolNumber=" + protocolNumber + ", dateTime=" + dateTime
				+ ", quantity=" + quantity + ", positioningSatelliteno=" + positioningSatelliteno + ", Latitude="
				+ Latitude + ", Longitude=" + Longitude + ", speed=" + speed + ", course=" + course + ", status="
				+ Arrays.toString(status) + ", mcc=" + mcc + ", mnc=" + mnc + ", lac=" + lac + ", cellId=" + cellId
				+ ", acc=" + acc + ", dataupload=" + dataupload + ", informationSerialNo=" + informationSerialNo
				+ ", errorcheck=" + errorcheck + ", Response=" + Response + ", GpsRealTime=" + GpsRealTime
				+ ", mileage=" + mileage + "]";
	}
	
	
}
