/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author ADAMERIQUEMACHADOBOA
 */
public class ConnectionFactory {
   
    /**
     * Conecta ao banco de dados ou criar um novo banco
     */
    // primeiro passo importar biblioteca
    
    
    //conectar ao banco de dados
    public Connection getConnection(){
        try{
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdvendas","usuarioscurso","123");
        }catch(Exception erro){
            throw new RuntimeException(erro);
        }
        
    }

}