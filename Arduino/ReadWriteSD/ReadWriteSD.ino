/*
  SD card read/write

 This example shows how to read and write data to and from an SD card file
 The circuit:
 * SD card attached to SPI bus as follows:
 ** MOSI - pin 11
 ** MISO - pin 12
 ** CLK - pin 13
 ** CS - pin 4

 created   Nov 2010
 by David A. Mellis
 modified 9 Apr 2012
 by Tom Igoe

 This example code is in the public domain.

 */

#include <SPI.h>
#include <SD.h>

File myFile;

void escrevesd(String txt,String nome)
{
  Serial.print("Initializing SD card...");

  if (!SD.begin(4)) {
    Serial.println("initialization failed!");
    return;
  }
  Serial.println("initialization done.");

  // open the file. note that only one file can be open at a time,
  // so you have to close this one before opening another.
  myFile = SD.open(nome+".txt", FILE_WRITE);

  // if the file opened okay, write to it:
  if (myFile) {
    Serial.print("Writing to contatos.txt...");
    myFile.println(txt);
    myFile.println(txt);
    myFile.println(txt);
    // close the file:
    myFile.close();
    Serial.println("done.");
    //SD.remove("contatos.txt"); 
  }
  else {
    // if the file didn't open, print an error:
    Serial.println("Erro na gravação do arquivo test.txt");
  }
}
void lesd(String nome){
  // re-open the file for reading:
  myFile = SD.open(nome+".txt");
  if (myFile) {
    Serial.println("contatos.txt:");

    // read from the file until there's nothing else in it:
    while (myFile.available()) {
      Serial.write(myFile.read());
    }
    // close the file:
    myFile.close();
  } else {
    // if the file didn't open, print an error:
    Serial.println("error opening contatos.txt");
  }
  }
void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  String texto=("teste de escrita em texto");
  String nome=("contato");
  escrevesd(texto,nome); 
  lesd(nome);
}

void loop() {
  // nothing happens after setup
}


