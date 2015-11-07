package br.upf.contato.testetcp;

import java.io.*;
import java.net.*;

class TCPClient {
     public static void teste (String argv[]) throws Exception {
         String sentenca;
         String sentencaModificada;
         //cria o streame do teclado
         BufferedReader cadeiaUsuario = new BufferedReader (new InputStreamReader(System.in));
         //cria o socket de acesso ao server hostname na porta 6789
         Socket clienteSocket = new Socket("127.0.0.1", 6789);
         //cria os streams de entrada e saida com o servidor
         DataOutputStream clienteParaServidor = 
                 new DataOutputStream(clienteSocket.getOutputStream());
         BufferedReader cadeiaServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
         //le uma linha do tecado e coloca em sentenca
         System.out.println("Digite: ");
         sentenca = cadeiaUsuario.readLine();
         //envia a linha para o servver
         clienteParaServidor.writeBytes(sentenca + '\n');
         //le a linha do server
         sentencaModificada = cadeiaServidor.readLine();
         
         System.out.println("Par o servidor " + sentencaModificada);
         clienteSocket.close();
     }
     
}

