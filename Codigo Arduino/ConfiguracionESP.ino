
//#include <SoftwareSerial.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <Arduino.h>
//#include <dht.h>
#if defined(ESP32)
  #include <WiFi.h>
#elif defined(ESP8266)
  #include <ESP8266WiFi.h>
#endif
#include <Firebase_ESP_Client.h>
#include <DHT.h>  

//Provide the token generation process info.
#include "addons/TokenHelper.h"
//Provide the RTDB payload printing info and other helper functions.
#include "addons/RTDBHelper.h"

// Insert your network credentials
#define WIFI_SSID "OnePlus 8T"
#define WIFI_PASSWORD "lucho2022"

// Insert Firebase project API Key
#define API_KEY "AIzaSyC168yXrD17sSdIRlWuPJ4h2MQBZ1ED9eQ"

// Insert RTDB URLefine the RTDB URL */
#define DATABASE_URL "https://airtech-1b766-default-rtdb.firebaseio.com/" 
//D6 = Rx & D5 = Tx
//SoftwareSerial nodemcu(D5, D6);
//Define Firebase Data object
FirebaseData fbdo;

FirebaseAuth auth;
FirebaseConfig config;

unsigned long sendDataPrevMillis = 0;
int count = 0;
bool signupOK = false;

const unsigned char Passive_buzzer = 14;
#define DHTPIN 2
#define DHTTYPE DHT11
//#define LED1 0
int LED1 = 5;
#define LED2 4
#define LED3 16  
#define LED4 13 
DHT dht2(DHTPIN, DHTTYPE);
int smokeA0 = A0;
String tobesend;


void setup(){
  Serial.begin(9600);
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  dht2.begin();
  pinMode(LED4, OUTPUT);
  
  Serial.print("Connecting to Wi-Fi");
  while (WiFi.status() != WL_CONNECTED){
    Serial.print(".");
    digitalWrite(LED4, HIGH);
    
    
    delay(300);

  
  }
  digitalWrite(LED4, LOW);
  Serial.println();
  Serial.print("Connected with IP: ");
  
  Serial.println(WiFi.localIP());
  Serial.println();

  /* Assign the api key (required) */
  config.api_key = API_KEY;

  /* Assign the RTDB URL (required) */
  config.database_url = DATABASE_URL;

  /* Sign up */
  if (Firebase.signUp(&config, &auth, "", "")){
    Serial.println("ok");
    signupOK = true;
  }
  else{
    Serial.printf("%s\n", config.signer.signupError.message.c_str());
    
  }

  /* Assign the callback function for the long running token generation task */
  config.token_status_callback = tokenStatusCallback; //see addons/TokenHelper.h
  
  Firebase.begin(&config, &auth);
  Firebase.reconnectWiFi(true);

  pinMode(smokeA0, INPUT);
  pinMode(LED1, OUTPUT); 
  pinMode(LED2, OUTPUT);
  pinMode(LED3, OUTPUT);
  
  pinMode (Passive_buzzer,OUTPUT) ;
}


void loop(){
  int analogSensor = analogRead(smokeA0);
  //StaticJsonBuffer<1000> jsonBuffer;
  //JsonObject& data = jsonBuffer.parseObject(nodemcu);
  
  //DynamicJsonDocument doc(1024);
  //float hume = 0, tempe = 0;
  //serializeJson(doc,Serial);
  // Reading the response
  //int chk = DHT.read11(DHT11_PIN);
  char* alerta1=" ";
  char* alerta2=" ";
  char* alerta3=" ";
  float hume = dht2.readHumidity();
   
  float tempe = dht2.readTemperature( );
    
  if (tempe < 22.0){
    alerta2 = "muy frio";
    
  }
  if (tempe >= 22 && tempe<24){
    alerta2 = "frio";
   
  }
  if (tempe >= 24 && tempe<25){
    alerta2 = "confort";
   
  }
  if (tempe >= 25 && tempe<27){
    alerta2 = "caliente";
    digitalWrite(LED1, LOW);
  }
  if (tempe >= 27){
    alerta2 = "peligro";
    digitalWrite(LED1, HIGH);
     

    WiFiClient client;
    HTTPClient http; //Declare an object of class HTTPClient
      //Specify request destination
    tobesend = "http://api.callmebot.com/whatsapp.php?";
    tobesend = tobesend + "phone=+593939793519";
    tobesend = tobesend + "&text=La+temperatura+es+peligrosa";
    tobesend = tobesend + "&apikey=855410";
    http.begin(client,tobesend); 
    int httpCode = http.GET(); //Send the request
    if (httpCode > 0) 
        { 
          //Check the returning coderr
         String payload = http.getString(); //Get the request response payload
         Serial.println(payload); //Print the response payload
         }
    http.end(); //Close connection
    
  }
  
  if (hume >= 33 && hume<40){
    alerta1 = "confortable";
    digitalWrite(LED2, LOW);
  }
  if (hume >= 50){
    alerta1 = "peligro electricidad";
    digitalWrite(LED2, HIGH);
    WiFiClient client;
    HTTPClient http; //Declare an object of class HTTPClient
      //Specify request destination
    tobesend = "http://api.callmebot.com/whatsapp.php?";
    tobesend = tobesend + "phone=+593939793519";
    tobesend = tobesend + "&text=La+humedad+es+alta+:+peligro+electricidad";
    tobesend = tobesend + "&apikey=855410";
    http.begin(client,tobesend); 
    int httpCode = http.GET(); //Send the request
    if (httpCode > 0) 
        { 
          //Check the returning coderr
         String payload = http.getString(); //Get the request response payload
         Serial.println(payload); //Print the response payload
         }
    http.end(); //Close connection
  }

  if (analogSensor < 181 ){
    alerta3 = "pobre";
  }
  if (analogSensor > 181 && analogSensor< 225){
    alerta3 = "pobre";
  }
  if (analogSensor > 225 && analogSensor<300){
    alerta3 = "Muy mal";
    digitalWrite(LED3, LOW);
  }
  if (analogSensor > 300 && hume<350){
    alerta3 = "Peligro maximo";
    digitalWrite(LED3, HIGH);
    WiFiClient client;
    HTTPClient http; //Declare an object of class HTTPClient
      //Specify request destination
    tobesend = "http://api.callmebot.com/whatsapp.php?";
    tobesend = tobesend + "phone=+593939793519";
    tobesend = tobesend + "&text=La+concentracion+de+monoxido+es+peligrosa";
    tobesend = tobesend + "&apikey=855410";
    http.begin(client,tobesend); 
    int httpCode = http.GET(); //Send the request
    if (httpCode > 0) 
        { 
          //Check the returning coderr
         String payload = http.getString(); //Get the request response payload
         Serial.println(payload); //Print the response payload
         }
    http.end(); //Close connection
  }
  if (analogSensor > 350 ){
    alerta3 = "toxico";
  }
  

  //Serial.println(hume);
  if (Firebase.ready() && signupOK && (millis() - sendDataPrevMillis > 5000 || sendDataPrevMillis == 0)){
    sendDataPrevMillis = millis();
    // Write an Int number on the database path test/int
    if (Firebase.RTDB.setFloat(&fbdo, "Salón Eventos/temperatura", tempe)){
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
      tone(Passive_buzzer, LOW) ; //DO note 523 Hz
    }
    count++;
    
    // Write an Float number on the database path test/float
    if (Firebase.RTDB.setFloat(&fbdo, "Salón Eventos/calidadaire", analogSensor)){
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
      tone(Passive_buzzer, LOW) ; //DO note 523 Hz
    }
    if (Firebase.RTDB.setFloat(&fbdo, "Salón Eventos/humedad", hume)){
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
    }
     if (Firebase.RTDB.setString(&fbdo, "Casa GYE/alertahumedad", alerta1)){
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
      tone(Passive_buzzer, LOW) ; //DO note 523 Hz
    }
    else{
      if(tempe >= 27){
        tone(Passive_buzzer, 523) ; //DO note 523 Hz
        delay (500); 
        tone(Passive_buzzer, 587) ; //RE note ...
        delay (500);
      }
      
    }
    if (Firebase.RTDB.setString(&fbdo, "Casa GYE/alertatemperatura", alerta2)){
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
    }
    if (Firebase.RTDB.setString(&fbdo, "Casa GYE/alertacalidad", alerta3)){
      Serial.println("PASSED");
      Serial.println("PATH: " + fbdo.dataPath());
      Serial.println("TYPE: " + fbdo.dataType());
    }
  }
  if(WiFi.status() != WL_CONNECTED){
    digitalWrite(LED4, HIGH);
    
  }
  else{
    digitalWrite(LED4, LOW);
  }
}
