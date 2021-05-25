<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>EnrichAi</title>
</head>
<body>
	<button onclick="sendLogin()"> Login </button>
	<br>
	<button onclick="stop()"> Stop </button>
	
	<h1>Login decoded Request</h1><br><table id="login" ></table><br>
	
	<h1>Login Response</h1><br><table id="loginr" ></table><br>
	
	<h1>HeartBeat decoded Request</h1><br><table id="heartbeat" ></table><br>
	
	<h1>HeartBeat Response</h1><table id="heartbeatr" ></table><br>
	
	<h1>GPS decoded Request</h1><table id="gps" ></table><br>
</body> 

<script>
	var bool=0;
	function stop(){
		bool=1;
		console.log("stop"+bool);
	}
	function sendLogin(){
		bool=0;
		fetch('/login',{
			method: 'POST',
			headers: {
			      'Content-Type': 'application/json'
			      // 'Content-Type': 'application/x-www-form-urlencoded',
			    },
			body: "78 78 11 01 03 51 60 80 80 77 92 88 22 03 32 01 01 AA 53 36 0D 0A"
			})
		  .then(response => response.json())
		  .then(data => afterlogin(data));
	}
	function afterlogin(data){
		//console.log(data);
		var rows = '';
		for (var p in data){
			rows += "<tr><td>" + p + "</td><td>" + data[p] + "</td></tr>";
		}
		var node = document.getElementById("login").innerHTML+=rows;
		sendHeartBeat();
		sendGPSPacket();
	}
	
	
	function sendHeartBeat(){
		fetch('/heartbeat',{
			method: 'POST',
			headers: {
			      'Content-Type': 'application/json'
			      // 'Content-Type': 'application/x-www-form-urlencoded',
			    },
			body: "78 78 0B 23 C0 01 22 04 00 01 00 08 18 72 0D 0A"
			})
		  .then(response => response.json())
		  .then(data => heartBeatResponse(data));
	}
	
	function heartBeatResponse(data){
		console.log(bool);
		if(bool==0){
			var rows = '';
			for (var p in data){
				rows += "<tr><td>" + p + "</td><td>" + data[p] + "</td></tr>";
			}
			var node = document.getElementById("heartbeat").innerHTML+=rows;
			sleep(3000);
			sendHeartBeat();
		}
		
	}
	
	function sendGPSPacket(){
		fetch('/gps',{
			method: 'POST',
			headers: {
			      'Content-Type': 'application/json'
			      // 'Content-Type': 'application/x-www-form-urlencoded',
			    },
			body: "78 78 22 22 0F 0C 1D 02 33 05 C9 02 7A C8 18 0C 46 58 60 00 14 00 01 CC 00 28 7D 00 1F 71 00 00 01 00 08 20 86 0D 0A"
			})
		  .then(response => response.json())
		  .then(data => gpsPacketResponse(data));
	}
	function gpsPacketResponse(data){
		console.log(bool);
		if(bool==0){
		var rows = '';
		for (var p in data){
			rows += "<tr><td>" + p + "</td><td>" + data[p] + "</td></tr>";
		}
		var node = document.getElementById("gps").innerHTML+=rows;
		 sleep(3000);
		sendGPSPacket();
		}
	}
	
	function  sleep( sleepDuration ){
		  var now = new Date().getTime();
		  while(new Date().getTime() < now + sleepDuration){ /* do nothing */ } 
		}
	
	
</script>
</html>