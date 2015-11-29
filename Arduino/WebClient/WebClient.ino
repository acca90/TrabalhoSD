/*
  Web client

 Circuit:
 * Ethernet shield attached to pins 10, 11, 12, 13
 */

#include <SPI.h>
#include <Ethernet.h>

// Enter a MAC address for your controller below.
// Newer Ethernet shields have a MAC address printed on a sticker on the shield
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
char k[10];
byte indice=0;
String contatopronto="";
IPAddress server(192,168,137,1);  
//char server[] = "www.google.com";//

// Set the static IP address to use if the DHCP fails to assign
IPAddress ip(192, 168, 137, 177);
byte DNS[] = {8, 8, 8, 8};
byte GTW[] = {192, 168, 137, 1};
byte NET[] = {255, 255, 255, 0};

// Initialize the Ethernet client library
// with the IP address and port of the server
// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }

  delay(1000);
  Serial.print("Setting IP Address...");
  #if 1
    Ethernet.begin(mac, ip, DNS, GTW, NET); 
  #else
  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    // try to congifure using IP address instead of DHCP:
    Ethernet.begin(mac, ip);
  }
  #endif
  // give the Ethernet shield a second to initialize:
  Serial.println("IP DONE!");
  delay(3000);
  
}
void conectar()
{
  Serial.println("connecting...");

  // if you get a connection, report back via serial:
  if (client.connect(server, 8080)) {
    Serial.println("connected");
    // Make a HTTP request:
    //client.println("GET / HTTP/1.1");
    //client.println("Connection: close");
    //client.println("Cache-Control: max-age=0");
    //client.println("Accept: text/html");
    //client.println("User-Agent: Mozilla/5.0");
    //client.println("Accept-Language: pt-BR");
    //client.println();

    client.print("GET /wsRest/index.php/contato HTTP/1.0\r\nConnection: close\r\n\r\n");
  }
  else {
    // kf you didn't get a connection to the server:
    Serial.println("connection failed");
  }
  while(client.connected()){
    // if there are incoming bytes available
  // from the server, read them and print them:
  if (client.available()) {
    char c = client.read();     
    Serial.print(c);
    k[indice]=c;
    indice++;
    if(indice>=11)indice=0;       
  }

  // if the server's disconnected, stop the client:
  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();
    }
}
}
/*HTTP/1.1 200 OK
Date: Sun, 29 Nov 2015 10:53:52 GMT
Server: Apache/2.4.17 (Win32) OpenSSL/1.0.2d PHP/5.6.14
X-Powered-By: PHP/5.6.14
Content-Length: 553
Connection: close
Content-Type: text/html;charset=UTF-8

[{"codigo":1,"nome":"dasd","email":"sadasdas","endereco":"asdasdasd","complemento":"asdasdadas","cep":99050120,"cidade":"Passo fundo","estado":"RS","email_alter":"daksdasdas"},{"codigo":2,"nome":"kleiton","email":"kleiton@coco.com","endereco":"rua da bosta","complemento":"nom tem","cep":99631249,"cidade":"passo fundo","estado":"RS","email_alter":"shuashua"},{"codigo":3,"nome":"kleiton pase","email":"kleiton@kiol.com","endereco":"rua da merda","complemento":"nom tem","cep":99631249,"cidade":"passo fundo","estado":"RS","email_alter":"jhgvttiji"}]
*/

void loop()
{

conectar();
delay(6000);
Serial.println("chamou conectar");
}

