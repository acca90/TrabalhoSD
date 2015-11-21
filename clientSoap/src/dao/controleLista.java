/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.service.Contato;
import java.util.ArrayList;

/**
 *
 * @author joao
 */
public class controleLista {
    private static ArrayList<Contato> list;

    public static ArrayList<Contato> getList() {
        return list;
    }

    public static void setList(ArrayList<Contato> list) {
        controleLista.list = list;
    }
    
    
}
