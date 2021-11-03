/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import javax.swing.JOptionPane;

/**
 *
 * @author ADAMERIQUEMACHADOBOA
 */

public class TestaConexao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       try {
           new ConnectionFactory().getConnection();
           JOptionPane.showMessageDialog(null,"Conectou com sucesso!");
       }catch (Exception e){
           JOptionPane.showMessageDialog(null,"Ops não conectou =[");
       }
            
    }
    
}
