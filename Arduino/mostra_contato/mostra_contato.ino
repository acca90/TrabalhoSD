#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(12, 11, 5, 4, 3, 2);//seta os pinos que vão ser usados para com o LCD
String nome[10];
int indice=0;
byte sts=0;
//        STATUS
// case 0 primeira passagem
// case 1 conteudo do LCD atualizado
// case 2 botao 1 foi precionado
// case 3 botão 2  ||   ||

void setup()
{
    lcd.begin(16, 2);//inicializa o LCD passando a quantidade de colunas 16 e linhas 2
    //attachInterrupt(digitalPinToInterrupt(19), botaocima, HIGH);// se mudar tensão de negativo para positivo no pino 2
    //attachInterrupt(digitalPinToInterrupt(20), botaobaixo, HIGH);// se mudar tensão de negativo para positivo no pino 2
    //attachInterrupt(19, botaocima, RISING); // so when interrupt one (digital pin 3) changes state, it will trigger the interrupt and go to function 'panictwo'
    Serial.begin(9600);
    pinMode(18,INPUT);
    pinMode(19,INPUT);
    pinMode(20,INPUT);
    fazvetor();
}
/*void botaocima()
{
    
        Serial.println("Botao UP : ");
        status = 2;
        delay(200);
}

void botaobaixo()
{
    
        Serial.println("Botao DOW: ");
        status = 3;
        delay(200);
}
*/
void fazvetor()
{
  nome[0]=("kleiton Pase");
  nome[1]=("(54)9963-1249");
  nome[2]=("Jonas Silva");
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
    lcd.setCursor(0, 1);//informa a posição que vai ser escrito no display
    lcd.print(nome[indice+1]);//escreve no display
    lcd.setCursor(0, 0);
    lcd.print(nome[indice]);
    int j = nome[indice].length()-16;//pega o tamanho da string a ser impressa menos o tamanho do display
    for(int i=0; i<=j; i++) //para os cazos que a linha for maior que 16 ele
    {
        lcd.setCursor(0-i, 0);
        lcd.print(nome[indice]);
        delay(350);
    }
    if(j>0)//se a string for maior que 16 imprimir de novo o começo da string
    {
        lcd.setCursor(0,0);
        lcd.print(nome[indice]);
    }
    sts=1;
    Serial.println("LCD Atualizado");
}
void loop()
{    
    if(sts==0)escreve();//atualiza
    
    if(digitalRead(18)==1)
    {
      Serial.println("Botao Buscar: ");
        sts = 0;
        delay(150);
    }
     if(digitalRead(19)==1)
    {
      Serial.println("Botao UP: ");
        sts = 0;
        indice++;
        indice++;
        Serial.println(indice);
        delay(150);
    }
     if(digitalRead(20)==1)
    {
      Serial.println("Botao DOW: ");
        sts = 0;
        indice--;
        indice--;
        Serial.println(indice);
        delay(150);
    }
    if(indice>=9)indice=8;
    if(indice<=-1)indice=0;
   
}
