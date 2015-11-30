/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcpclient;

/**
 *
 * @author jonas
 */
public class Client {
    private final String ENDERECO;
    private final Integer PORTA;
    private final ClientConsoleOperation console;
    
    public Client(String endereco, Integer porta){
        this.ENDERECO = endereco;
        this.PORTA = porta;
        console = new ClientConsoleOperation();
    }
    
    public void iniciar(){
        ClientOperation clienteOper = new ClientOperation(ENDERECO, PORTA);
        clienteOper.conecta();
        Integer opcao = 4;//1-adiciona/2-altera/3-exclui/4-consulta/5-listacidade/6-encerrarcliente
        do {
            opcao = console.menuPrincipal();
            switch (opcao) {
                case 1://inserir
                    clienteOper.insert();
                    break;
                case 2://alterar
                    clienteOper.update();
                    break;
                case 3:
                    clienteOper.delete();
                    break;
                case 4:
                    clienteOper.search();
                    break;
                case 5:
                    clienteOper.searchByCidade();
                    break;
                case 6:
                    clienteOper.desconecta();
                    break;
                default:
                    clienteOper.search();
                    break;
            }
        } while (opcao != 6);
    }
    
    public static void main(String[] args) {
        new Client("localhost", 2005).iniciar();
    }
}
