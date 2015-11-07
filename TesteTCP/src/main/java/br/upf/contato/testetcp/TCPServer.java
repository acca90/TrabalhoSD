package br.upf.contato.testetcp;

import java.io.*;
import java.net.*;

 class TCPServer {
    public static void main (String argv[]) throws Exception {
        String clienteSentenca;
        String sentencaCapturada;
        //cria socket de comunicação com os clientes na porta 6789
        ServerSocket bemVindoSocket = new ServerSocket(6789);
        //espera a msg de algum cliente
        while(true){
            //espera a conexão de algum cliente
            Socket conexaoSocket = bemVindoSocket.accept();
            //cria streame de estrada e saida com o cliente que chegou
            BufferedReader cadeiaCliente = 
                    new BufferedReader (new InputStreamReader(conexaoSocket.getInputStream()));
            DataOutputStream servidorParaCliente =
                    new DataOutputStream(conexaoSocket.getOutputStream());
            //le uma linha do cliente
            clienteSentenca = cadeiaCliente.readLine();
            //transforma a linha em maiuscula
            sentencaCapturada = clienteSentenca.toUpperCase() + '\n';
            //envia a linha maiuscula para o cliente
            servidorParaCliente.writeBytes(sentencaCapturada);
        }
    }
}
