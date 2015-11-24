#include <LiquidCrystal.h>

// initialize the library with the numbers of the interface pins
LiquidCrystal lcd(8, 9, 4, 5, 6, 7);

void setup() {
  // set up the LCD's number of columns and rows:
  lcd.begin(16, 2);
}
String escreve(String disp){
   lcd.print(disp);
   delay(150);
   for(int i=0;i<=(disp.length()-16);i++)
   {
   lcd.setCursor(0-i, 0);
   lcd.print(disp);
   delay(150);
   lcd.setCursor(2, 1);
   lcd.print(i);
   }
   lcd.setCursor(0,0);
   lcd.print(disp);
}
void loop() {
   String disp =  String("Texto de teste para saber se está certo o texto");
   escreve(disp);
   
   
}