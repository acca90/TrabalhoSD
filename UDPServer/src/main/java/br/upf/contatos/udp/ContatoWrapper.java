/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.udp;

import br.upf.contatos.dal.model.Contato;
import br.upf.contatos.msg.model.ContatoBean;
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
