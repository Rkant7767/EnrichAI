# EnrichAI

This is the documentation for the AnrichAI webapp created for the second round of interview.

You will be able to find the code inside src/java/com/example/EnrichAI

This project has three urls
1. /login
  this url is used for decoding the login packets and responding according to the request send
  request=> 78 78 11 01 03 51 60 80 80 77 92 88 22 03 32 01 01 AA 53 36 0D 0A
2. /heartbeat
  this url is used for decoding the heartbeat packets and responding according to the request send after the login.
  request=> 78 78 0B 23 C0 01 22 04 00 01 00 08 18 72 0D 0A
3. /gps
  this url is used for decoding the GPS location packets.
  request=> 78 78 22 22 0F 0C 1D 02 33 05 C9 02 7A C8 18 0C 46 58 60 00 14 00 01 CC 00 28 7D 00 1F 71 00 00 01 00 08 20 86 0D 0A
  All the three urls here recieve the packet data in hexadecimal format and the 
