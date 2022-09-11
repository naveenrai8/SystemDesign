# DNS Resolution flow

DNS is involved in resolution of domain name to its IP address of the machine serving the request.
Lets understand the complete flow with some examples:

## User accessing Web server hosted at Azure

Lets assume web server using ([Azure App service](https://azure.microsoft.com/en-in/services/app-service/#overview)) to host a website.

<image src="./Images/User_Accessing_WebServer.png" height=100px>

Flow:
1. User typed web server URL (https://learningwebapp-1.azurewebsites.net) to access the website it hosts.
1. DNS resolution kicks off which returns the IP address of the web server.
1. TCP connection gets established between User's machine and web server via ISP.
1. User can now start seeing the content of the website.


### Understand the DNS resolution flow in detail

<image src="./Images/DNS_Resolution_User_Accessing_WebServer.png" height=300px>

Flow in detail:
1. User typed web server URL (https://learningwebapp-1.azurewebsites.net) in its browser to access the content. Browser looks for the associated IP in its cache and use it if it finds.
1. If its not find in cache, then it takes help from recursive DNS by passing web server URL.
1. Recursive DNS returns the IP from its cache if it has else ask from Root Name server.
1. Root Name server extract the TLD (Top level domain), which is **.net**, from the web server URL and return the TLD (Top level domain) server IPs to recursive DNS.
1. Recursive DNS picks one of the TLD IPs to connect to. TLD server extracts domain from the URL, which is **azurewebsites**, and return the list of Authoritative Name server IPs to recursive DNS.
1. Recursive DNS then picks up one of the IPs and establish connection with Authoritative Name server to get the IP of web server.
1. Authoritvative Name server looks for URL to IP mapping in its database and return it to recursive DNS.
1. Recursive DNS stores this info in its cache and return to browser.
1. Browser then uses this IP to connect to Webserver.s
