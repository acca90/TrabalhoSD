/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.upf.contatos.udp.cli;

import br.upf.contatos.msg.model.Operacao;
import com.beust.jcommander.IStringConverter;

/**
 *
 * @author Mauricley
 */
public class OperacaoConverter implements IStringConverter <Operacao> {
    @Override
    public Operacao convert(String operacao) {
        return Operacao.valueOf(operacao.toUpperCase());
    }
}