# DNS Resolution flow

DNS is involved in resolution of domain name to its IP address of the machine serving the request.
Lets understand the complete flow with some examples:

## User accessing Web server hosted at Azure

Lets assume web server using ([Azure App service](https://azure.microsoft.com/en-in/services/app-service/#overview)) to host a website.

<image src="./Images/User_Accessing_WebServer.png" height=300px>

Flow:
1. User typed web server URL (https://learningwebapp-1.azurewebsites.net) to access the website it hosts.
1. DNS resolution kicks off which returns the IP address of the web server.
1. TCP connection gets established between User's machine and web server via ISP.
1. User can now start seeing the content of the website.


### Understand the DNS resolution flow in detail

<image src="./Images/DNS_Resolution_User_Accessing_WebServer.png" height=600px>

Flow in detail:
1. User typed web server URL (https://learningwebapp-1.azurewebsites.net) in its browser to access the content. Browser looks for the associated IP in its cache and use it if it finds.
1. If its not find in cache, then it takes help from recursive DNS by passing web server URL.
1. Recursive DNS returns the IP from its cache if it has else ask to Root Name server.
1. Root Name server extract the TLD (Top level domain), which is **.net**, from the web server URL and return the TLD (Top level domain) server IPs to recursive DNS.
1. Recursive DNS picks one of the TLD IPs to connect to. TLD server extracts domain from the URL, which is **azurewebsites**, and return the list of Authoritative Name server IPs to recursive DNS.
1. Recursive DNS then picks up one of the IPs and establish connection with Authoritative Name server to get the IP of web server.
1. Authoritvative Name server looks for URL to IP mapping in its database and return it to recursive DNS.
1. Recursive DNS stores Web server to IP mapping in its cache and return IP to browser.
1. Browser then uses this IP to connect to Webserver.


## User accessing Web server hosted at Azure Via Traffic Manager

Lets assume web server using ([Azure App service](https://azure.microsoft.com/en-in/services/app-service/#overview)) to host a website which are deployed at two different locations (US, Europe) behind the [Azure Traffic Manager](https://docs.microsoft.com/en-us/azure/traffic-manager/traffic-manager-overview).

<image src="./Images/User_WebServer_Via_TM.png" height=300px>

### URLs
Since there are two Web Server (Using Azure App Service) deployed at two different locations:
1. US Location : https://learningwebapp-1.azurewebsites.net
1. Europe Location : https://learningwebapp-2.azurewebsites.net
1. Traffic Manager: http://learningtm-1.trafficmanager.net

### Traffic Manager Configuration
Some configuration needed at Traffic manager side. [Refer](https://docs.microsoft.com/en-us/azure/traffic-manager/traffic-manager-manage-endpoints) here to know more

Traffic Manager Endpoints 
    * US Location : https://learningwebapp-1.azurewebsites.net
    * Europe Location : https://learningwebapp-2.azurewebsites.net


Flow:
1. User typed Traffic Manager URL (http://learningtm-1.trafficmanager.net) to access one of the web servers deployed at two different regions hosting web sites.
1. When user uses traffic manager url to access the webserver, traffic manager decides which web server it should route the request to based upon [routing method](https://docs.microsoft.com/en-us/azure/traffic-manager/traffic-manager-routing-methods) 
1. DNS resolution kicks off which returns the IP address of the web server.
1. TCP connection gets established between User's machine and web server via ISP.
1. User can now start seeing the content of the website.


### Understand the DNS resolution flow in detail

<image src="./Images/DNS_Resolution_User_Webserver_TM.png" height=600px>

Flow in detail:
1. User typed web server URL (http://learningtm-1.trafficmanager.net) in its browser to access the content. Browser looks for the associated IP in its cache and use it if it finds.
1. If its not find in cache, then it takes help from recursive DNS by passing web server URL.
1. Recursive DNS returns the IP from its cache if it has else ask to Root Name server.
1. Root Name server extract the TLD (Top level domain), which is **.net**, from the web server URL and return the TLD (Top level domain) server IPs to recursive DNS.
1. Recursive DNS picks one of the TLD IPs to connect to. TLD server extracts domain from the URL, which is **trafficmanager**, and return the list of Authoritative Name server IPs (which is Traffic Manager Name servers) to recursive DNS.
1. Recursive DNS then picks up one of the IPs and establish connection with Traffic Manager Name server to get the IP of web server. Traffic Manager Name server picks the Web server based upon the Routing method setting.
    1. During the process of finding the appropriate web server, it considers **Recursive DNS** IP address.
1. Traffic Manager Name server looks for URL to Endpoints mapping in its database and return Web server URL (https://learningwebapp-1.azurewebsites.net) to recursive DNS.
1. Recusive DNS still does not have IP. Instead it has web server URL. It starts the process again from calling Root Server to getting the IPs of Name servers holding info of azurewebsites.net domain which is cloud app name server.
1. Recursive DNS then contacts the cloud app name server to get the IP.
1. Recursive DNS stores this mapping (Web server URL to IP) in its cache and return IP to browser.
1. Browser then uses this IP to connect to Webserver.

# References:
1. https://docs.microsoft.com/en-us/azure/traffic-manager/traffic-manager-how-it-works