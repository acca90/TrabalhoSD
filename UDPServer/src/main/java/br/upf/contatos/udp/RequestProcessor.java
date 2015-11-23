/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.udp;

import br.upf.contatos.dal.service.ContatoService;
import br.upf.contatos.msg.Request;
import br.upf.contatos.msg.Response;
import br.upf.contatos.msg.ResponseImpl;
import br.upf.contatos.msg.model.ContatoBean;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author kaeff
 */
public class RequestProcessor implements Runnable {
    private final DatagramSocket socket;
    private final BlockingQueue<DatagramPacket> fila;
    private final ContatoService service = new ContatoService();
    
    public RequestProcessor(DatagramSocket socket, BlockingQueue fila) {
        this.socket = socket;
        this.fila = fila;
    }

    @Override
    public void run() {
        Request req;
        byte [] byteResp;
        for(;;) {
            try {
                DatagramPacket pct = fila.take();
                req = byteArray2Request(pct.getData());
                byteResp = processaRequest(req);
                sendResponse(pct, byteResp);
            } catch (InterruptedException ex) {
                System.out.println("Ocorreu uma interrupção enquanto aguardava a chegada da próxima requisição!");
            } catch (IOException ex) {
                System.out.println("Ocorreu um erro ao tentar obter o stream dos dados:\n" + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("Não foi possível identificar os dados recebidos!");
            }
        }
    }
    
    private byte[] processaRequest(Request req) throws IOException {
        Response resp;
        
        switch(req.getOperacao()) {
            case GETALL:
                resp = getAll();
                break;
            case GETBYID:
                resp = getById(req.getId());
                break;
            case GETBYCIDADE:
                resp = getByCidade(req.getCidade());
                break;
            case INSERT:
                resp = insert(req.getContato());
                break;
            case UPDATE:
                resp = update(req.getContato());
                break;
            case DELETE:
                resp = delete(req.getId());
                break;
            default:
                resp = badRequest();
        }
        return response2ByteArray(resp);
    }

    private Response getAll() {
        return new ResponseImpl().toGetAll(ContatoWrapper.jpa2tcp(service.getAll()));
    }

    private Response getById(Integer idContato) {
        return new ResponseImpl().toGetById(ContatoWrapper.jpa2tcp(service.getById(idContato)));
    }

    private Response getByCidade(String cidade) {
        return new ResponseImpl().toGetByCidade(ContatoWrapper.jpa2tcp(service.getByCidade(cidade)));
    }

    private Response insert(ContatoBean contato) {
        return new ResponseImpl().toInsert(ContatoWrapper.jpa2tcp(service.add(ContatoWrapper.tcp2jpa(contato))));
    }

    private Response update(ContatoBean contato) {
        return new ResponseImpl().toUpdate(ContatoWrapper.jpa2tcp(service.update(ContatoWrapper.tcp2jpa(contato))));
    }

    private Response delete(Integer idContato) {
        return new ResponseImpl().toDelete(ContatoWrapper.jpa2tcp(service.delete(idContato)));
    }

    private Response badRequest() {
        return new ResponseImpl().toBadRequest();
    }
    
    private Request byteArray2Request(byte[] data) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(data));
        return (Request) in.readObject();
    }
    
    private byte[] response2ByteArray(Response resp) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(resp);
        return os.toByteArray();
    }

    private void sendResponse(DatagramPacket pct, byte[] byteResp) {
        InetAddress ip = pct.getAddress();
        int porta = pct.getPort();
        DatagramPacket respPct = new DatagramPacket(byteResp, byteResp.length, ip, porta);
        try {
            socket.send(respPct);
        } catch (IOException ex) {
            System.out.println("Ocorreu um erro ao tentar enviar a resposta para o cliente " + ip + ":" + porta + "!");
        }
    }
}
