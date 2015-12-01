/*
  Web client

 Circuit:
 * Ethernet shield attached to pins 10, 11, 12, 13
 * 
 *  LCD
 Circuit:
 * LCD RS pin to digital pin 9
 * LCD Enable pin to digital pin 8
 * LCD D4 pin to digital pin 7
 * LCD D5 pin to digital pin 6
 * LCD D6 pin to digital pin 5
 * LCD D7 pin to digital pin 4
 * LCD R/W pin to ground
 * 10K resistor:
 * ends to +5V and ground
 * wiper to LCD VO pin (pin 3)

SD card read/write
circuit:
 * SD card attached to SPI bus as follows:
 ** MOSI - pin 11
 ** MISO - pin 12
 ** CLK - pin 13
 ** CS - pin 4
 * 
 */
 
#include <LiquidCrystal.h>
#include <SPI.h>
#include <Ethernet.h>
#include <aJSON.h> //https://github.com/interactive-matter/aJson
#include <SD.h>



// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(29, 28, 27, 26, 25, 24);//seta os pinos que vão ser usados para com o LCD


int indice=1;
byte sts=0;//sts *case 0 primeira passagem *case 1 conteudo do LCD atualizado *case 2 botao 1 foi precionado *case 3 botão 2  *case 4 botão 3 
int PbotUp=2;//botao up
int PbotDw=3;//botao Dow
int PbotCall=19;//botao Call

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
IPAddress server(192,168,137,1);
//char server[] = "www.google.com";//

// Set the static IP address to use if the DHCP fails to assign
IPAddress ip(192, 168, 137, 177);
byte DNS[] = {8, 8, 8, 8};
byte GTW[] = {192, 168, 137, 1};
byte NET[] = {255, 255, 255, 0};

// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;

int qtdcontatos=1;

void setup()
{
    lcd.begin(16, 2);//inicializa o LCD passando a quantidade de colunas 16 e linhas 2
    attachInterrupt(digitalPinToInterrupt(PbotCall), botaocall, FALLING);
    attachInterrupt(digitalPinToInterrupt(PbotUp), botaocima, FALLING);// se mudar tensão de negativo para positivo ou vice versa no pino 2
    attachInterrupt(digitalPinToInterrupt(PbotDw), botaobaixo, FALLING);// se mudar tensão de negativo para positivo ou vice versa no pino 3
    Serial.begin(9600);
    while (!Serial)
    {
        ; // wait for serial port to connect. Needed for Leonardo only
    }
    if (!SD.begin(4)) {
    Serial.println("initialization failed!");
    return;
    }
    Serial.println("initialization done.");
    Serial.print("Setting IP Address...");
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("obtendo IP...");
#if 1
    Ethernet.begin(mac, ip, DNS, GTW, NET);
#else
    // start the Ethernet connection:
    if (Ethernet.begin(mac) == 0)
    {
        Serial.println("Failed to configure Ethernet using DHCP");
        // no point in carrying on, so do nothing forevermore:
        // try to congifure using IP address instead of DHCP:
        Ethernet.begin(mac, ip);
    }
#endif
    // give the Ethernet shield a second to initialize:
    Serial.println("IP Enderecado!");
    delay(4000);
    conectar();
}

void botaocima()
{    
        Serial.println("Botao UP : ");
        sts = 2;
        indice++;
        if(indice>=qtdcontatos)indice=qtdcontatos;
}
void botaobaixo()
{
        Serial.println("Botao DOW: ");
        sts = 3;
        indice--;
        if(indice<=1)indice=1;
}
void botaocall()
{    
        Serial.println("Botao CALL : ");
        sts = 4;
        indice=1;
        conectar();
        
}



void conectar()
{
    Serial.println("conectando...");
    
    // if you get a connection, report back via serial:
    if (client.connect(server, 8080))
    {
        Serial.println("connectado");
        lcd.clear();
        lcd.setCursor(0, 0);
        lcd.print("conectado !");
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
    else
    {
        // kf you didn't get a connection to the server:
        Serial.println("conexao falhou!!");
        lcd.clear();
        lcd.setCursor(0, 0);
        lcd.print("conexao falhou!!");
    }
    char dadcont[256];
    bool st=0;
    int i=0;
    while(client.connected())
    {
        // if there are incoming bytes available
        // from the server, read them and print them:
        if (client.available())
        {
            char c = client.read();
            //Serial.print(c);
            if(c=='{'){
              st=1;          
            }
            if(st==1){
              dadcont[i]=c;
              i++;
            }
            if(c=='}')
            {
              qtdcontatos++;
              st=0;
              i=0;
              //Serial.println(dadcont);
              json(dadcont);
            }
        }

        // if the server's disconnected, stop the client:
        if (!client.connected())
        {
            Serial.println();
            Serial.println("desconectado!!");
            
        }
    }   
    //Serial.println(dadcont);
    
}

void escrevesd(String arqname,String nomep,String emailp,String enderecop,String complementop,String cepp,String cidadep,String estadop,String email2p)
{
  Serial.print("Initializing SD card...");
  Serial.println(arqname);

  
  File myFile;//SD
  // open the file. note that only one file can be open at a time,
  // so you have to close this one before opening another.
  myFile = SD.open(arqname, FILE_WRITE);
  // if the file opened okay, write to it:
  if (myFile) {
    Serial.print("Writing to contatos.txt...");
    myFile.println(nomep);
    myFile.println(emailp);
    myFile.println(enderecop);
    myFile.println(complementop);
    myFile.println(cepp);
    myFile.println(cidadep);
    myFile.println(estadop);
    myFile.println(email2p);
    // close the file:
    myFile.close();
    Serial.println("done.");
    //SD.remove("contatos.txt"); 
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Gravado!");
    delay(1000);
  }
  else {
    // if the file didn't open, print an error:
    Serial.println("Erro na gravação do arquivo test.txt");
  }
}


void json(char* sonString){
    //char asonString[]="{\"codigo\":1,\"nome\":\"dasd\",\"email\":\"sadasdas\",\"endereco\":\"asdasdasd\",\"complemento\":\"asdasdadas\",\"cep\":99050120,\"cidade\":\"Passo fundo\",\"estado\":\"RS\",\"email_alter\":\"daksdasdas\"}";
    //Serial.println(sonString);

    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print("Gravando...");
    
    aJsonObject* contatos = aJson.parse(sonString);
    
    aJsonObject* codigo = aJson.getObjectItem(contatos, "codigo");

    aJsonObject* nome = aJson.getObjectItem(contatos, "nome");
    
    aJsonObject* email = aJson.getObjectItem(contatos, "email");

    aJsonObject* endereco = aJson.getObjectItem(contatos, "endereco");

    aJsonObject* complemento = aJson.getObjectItem(contatos, "complemento");

    aJsonObject* cep = aJson.getObjectItem(contatos, "cep");

    aJsonObject* cidade = aJson.getObjectItem(contatos, "cidade");

    aJsonObject* estado = aJson.getObjectItem(contatos, "estado");

    aJsonObject* email2 = aJson.getObjectItem(contatos, "email_alter");
     
    
    int codigop=codigo->valueint;
    Serial.println("Codigo: ");
    Serial.println(codigop);

    String nomep=nome->valuestring;
    Serial.println("Nome: ");
    Serial.println(nomep);

    String emailp=email->valuestring;
    Serial.println("E-mail: ");
    Serial.println(emailp);
    
    String enderecop=endereco->valuestring;
    Serial.println("Endereço: ");
    Serial.println(enderecop);
    
    String complementop=complemento->valuestring;
    Serial.println("Complemento: ");
    Serial.println(complementop);

    int cepp=cep->valueint;
    Serial.println("CEP: ");
    Serial.println(cepp);

    String cidadep=cidade->valuestring;
    Serial.println("Cidade: ");
    Serial.println(cidadep);

   
    String estadop=estado->valuestring;
    Serial.println("Estado: ");
    Serial.println(estadop);

    String email2p=email2->valuestring;
    Serial.println("E-mail alternativo: ");
    Serial.println(email2p);
//------------------------------------------------
    String arqname=String(codigop);
    arqname+=".txt";
    SD.remove(arqname);
    escrevesd(arqname,nomep,emailp,enderecop,complementop,String(cepp),cidadep,estadop,email2p);
//-------------------------------------------------
}
void escreve()
{   
    String referencia="";
    String referencia2="";
    lesd(referencia,referencia2);
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print(referencia);
    lcd.setCursor(0, 1);//informa a posição que vai ser escrito no display
    lcd.print(referencia2);//escreve no display
    sts=1;
    Serial.println("LCD Atualizado");
}
void lesd(String &referencia,String &referencia2){
  
  String arqname=String(indice);
  arqname+=".txt";
  File myFile;//SD
  myFile = SD.open(arqname);
  if (myFile) {
    Serial.println("contatos.txt:");
    // read from the file until there's nothing else in it:
    bool stf=0;
    while (myFile.available()) {
      char c=myFile.read();
      referencia+=c;
      if(c=='\\' || stf==1){
        stf=1;
        referencia2+=c;
      }
    }
    Serial.println(referencia);
    Serial.println(referencia2);
    // close the file:
    myFile.close();
  } else {
    // if the file didn't open, print an error:
    Serial.println("error opening contatos.txt");
  } 
}
void loop()
{
    if(sts!=1)escreve();//atualiza display
}
