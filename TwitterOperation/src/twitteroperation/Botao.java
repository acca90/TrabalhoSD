/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitteroperation;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Botao extends JFrame{
 private JButton ok;
 
 public Botao(){
  super("Criando botões");

  ok = new JButton("OK");
  add(ok);
  
 }

}