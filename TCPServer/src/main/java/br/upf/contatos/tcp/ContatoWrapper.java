/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.tcp;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.tcpmsg.model.ContatoBean;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauricley
 */
public class ContatoWrapper {
    public static ContatoBean jpa2tcp(Contato c) {
        if (c == null) return null;
        return new ContatoBean(c.getId(), c.getNome(), c.getEmail(), c.getEndereco(), c.getComplemento(),
                c.getCep(), c.getCidade(), c.getEstado(), c.getEmailAlternativo());
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokespecial java/lang/Object."<init>":()V
         * 4: aload_0
         * 5: aload_1
         * 6: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.id:Ljava/lang/Integer;
         * 9: aload_0
         * 10: aload_2
         * 11: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.nome:Ljava/lang/String;
         * 14: aload_0
         * 15: aload_3
         * 16: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.email:Ljava/lang/String;
         * 19: aload_0
         * 20: aload         4
         * 22: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.endereco:Ljava/lang/String;
         * 25: aload_0
         * 26: aload         5
         * 28: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.complemento:Ljava/lang/String;
         * 31: aload_0
         * 32: aload         6
         * 34: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.cep:Ljava/lang/Integer;
         * 37: aload_0
         * 38: aload         7
         * 40: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.cidade:Ljava/lang/String;
         * 43: aload_0
         * 44: aload         8
         * 46: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.estado:Ljava/lang/String;
         * 49: aload_0
         * 50: aload         9
         * 52: putfield      br/upf/contatos/tcpmsg/model/ContatoBean.emailAlternativo:Ljava/lang/String;
         * 55: return
         *  */
        // </editor-fold>
    }
    
    public static List<ContatoBean> jpa2tcp(List<Contato> l) {
        List<ContatoBean> lista = new ArrayList<>();
        for(Contato c: l) {
            lista.add(jpa2tcp(c));
        }
        return lista;
    }
    
    public static Contato tcp2jpa(ContatoBean cb) {
        Contato c = new Contato();
        c.setId(cb.getId());
        c.setNome(cb.getNome());
        c.setEmail(cb.getEmail());
        c.setEndereco(cb.getEndereco());
        c.setComplemento(cb.getComplemento());
        c.setCep(cb.getCep());
        c.setCidade(cb.getCidade());
        c.setEstado(cb.getEstado());
        c.setEmailAlternativo(cb.getEmailAlternativo());
        return c;
    }
}
