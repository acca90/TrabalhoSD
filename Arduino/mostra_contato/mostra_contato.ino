#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(9, 8, 7, 6, 5, 4);//seta os pinos que vão ser usados para com o LCD
String nome[10];
int indice=0;
byte sts=0;//sts *case 0 primeira passagem *case 1 conteudo do LCD atualizado *case 2 botao 1 foi precionado *case 3 botão 2  *case 4 botão 3 
int PbotUp=2;//botao up
int PbotDw=3;//botao Dow
int PbotCall=19;//botao Call

void setup()
{
    lcd.begin(16, 2);//inicializa o LCD passando a quantidade de colunas 16 e linhas 2
    attachInterrupt(digitalPinToInterrupt(PbotCall), botaocall, FALLING);
    attachInterrupt(digitalPinToInterrupt(PbotUp), botaocima, FALLING);// se mudar tensão de negativo para positivo ou vice versa no pino 2
    attachInterrupt(digitalPinToInterrupt(PbotDw), botaobaixo, FALLING);// se mudar tensão de negativo para positivo ou vice versa no pino 3
    Serial.begin(9600);
    fazvetor();
}
void botaocima()
{    
        Serial.println("Botao UP : ");
        sts = 2;
        indice=indice+2;
        if(indice>=9)indice=8;
}
void botaobaixo()
{
        Serial.println("Botao DOW: ");
        sts = 3;
        indice=indice-2;
        if(indice<=-1)indice=0;
}
void botaocall()
{    
        Serial.println("Botao CALL : ");
        sts = 4;
}
void fazvetor()
{
  nome[0]=("kleiton Pase");
  nome[1]=("(54)9963-1249");
  nome[2]=("Jonas Silvaaaaaaa");
  nome[3]=("(54)9645-8751");
  nome[4]=("Camila Posser");
  nome[5]=("(54)9958-8745");
  nome[6]=("Mateus Nogueira Silva");
  nome[7]=("(54)9963-1578");
  nome[8]=("Joao Vargas");
  nome[9]=("(54)9635-8657");
  for(int i=0;i<=9;i++){
  Serial.println(nome[i]);
  }
}
void escreve()
{   
    lcd.clear();
    lcd.setCursor(0, 0);
    lcd.print(nome[indice]);
    lcd.setCursor(0, 1);//informa a posição que vai ser escrito no display
    lcd.print(nome[indice+1]);//escreve no display
    sts=1;
    Serial.println("LCD Atualizado");
}
void loop()
{
    if(sts!=1)escreve();//atualiza display segundo parametro é a linha que quer que seja escrito 
}

