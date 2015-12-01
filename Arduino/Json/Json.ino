#include <aJSON.h>

// function definitions
//char* parseJson(char *jsonString) ;

// Json string to parse
char jsonString[] = "{\"codigo\":1,\"nome\":\"dasd\",\"email\":\"sadasdas\",\"endereco\":\"asdasdasd\",\"complemento\":\"asdasdadas\",\"cep\":99050120,\"cidade\":\"Passo fundo\",\"estado\":\"RS\",\"email_alter\":\"daksdasdas\"}";

void setup()
{
    Serial.begin(9600);
    Serial.println(jsonString);
    Serial.println("");
    
    aJsonObject* contatos = aJson.parse(jsonString);
    
    aJsonObject* codigo = aJson.getObjectItem(contatos, "codigo");

    aJsonObject* nome = aJson.getObjectItem(contatos, "nome");
    
    aJsonObject* email = aJson.getObjectItem(contatos, "email");

    aJsonObject* endereco = aJson.getObjectItem(contatos, "endereco");

    aJsonObject* complemento = aJson.getObjectItem(contatos, "complemento");

    aJsonObject* cep = aJson.getObjectItem(contatos, "cep");

    aJsonObject* cidade = aJson.getObjectItem(contatos, "cidade");

    aJsonObject* estado = aJson.getObjectItem(contatos, "estado");

    aJsonObject* email2 = aJson.getObjectItem(contatos, "email_alter");

    int valor = codigo->valueint;

    int ceps = cep->valueint;

    Serial.println("O CEP está com problemas: ");
    Serial.println(ceps);
    
    Serial.println("codigo: ");
    Serial.println(valor);
    



}

void loop()
{
; 
}

