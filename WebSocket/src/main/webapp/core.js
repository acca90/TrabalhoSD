            //abre a conexão
            openSocket(); 
            
                       
            var webSocket;
            var messages = document.getElementById("messages");
            function getAll(event){
                document.getElementById("op").value=1;                
                event.data.setData();
                send();
            }
            
            
            function insert(){
                var op = document.getElementById("op").value=2;       
                send();
            }
           
           
            function openSocket(){
                // Ensures only one connection is open at a time
                if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
                   writeResponse("WebSocket is already opened.");
                    return;
                }
                // Create a new instance of the websocket
                webSocket = new WebSocket("ws:localhost:8080/WebSocket/echo");
                 
                /**
                 * Binds functions to the listeners for the websocket.
                 */
                webSocket.onopen = function(event){
                    // For reasons I can't determine, onopen gets called twice
                    // and the first time event.data is undefined.
                    // Leave a comment if you know the answer.
                    if(event.data === undefined)
                        return;
 
                    writeResponse(event.data);
                };
 
                webSocket.onmessage = function(event){
                    writeResponse(event.data);
                };
 
                webSocket.onclose = function(event){
                    writeResponse("Connection closed");
                };
            }
           
            /**
             * Sends the value of the text input to the server
             */
            function send(){
                var op = document.getElementById("op").value;                
                var obj = {
                            codigo:  parseFloat(document.getElementById("codigo").value),
                            "nome": document.getElementById("nome").value                             
                            
                            
                        };
                
                console.log(obj);           
                
                
                webSocket.send(JSON.stringify(obj));
            }
           
            function closeSocket(){
                webSocket.close();
            }
 
            function writeResponse(text){
               document.getElementById("messages").innerHTML+= "<br/>" + text;
            }