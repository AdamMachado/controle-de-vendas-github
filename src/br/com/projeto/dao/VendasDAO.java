/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ADAMERIQUEMACHADOBOA
 */
public class VendasDAO {
    private Connection con;
    
    public VendasDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    
    //Cadastrar Venda
    public void cadastrarVenda(Vendas obj){
        try {
                // Inicializando Sql
            String sql = "insert into tb_vendas(cliente_id,data_venda,total_venda,observacoes) values(?,?,?,?)";
            //Conectanco com o banco de dados
            PreparedStatement stmt = con.prepareStatement(sql);
            //Passando valores para o banco de dados
            stmt.setInt(1,obj.getCliente().getId());
            stmt.setString(2,obj.getData_venda());
            stmt.setDouble(3,obj.getTotal_vendas());
            stmt.setString(4,obj.getObs());
            
            stmt.execute();
            stmt.close();
            //Mensagem de cadastrado com sucesso
            JOptionPane.showMessageDialog(null,"Venda cadastrado com sucesso");
            
            
     
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Não conseguimos cadastrar a venda"+e);
        }
    }
    
    
    
    // Retorna a ultima venda
    public int retornaUltimaVenda(){
        try {
            int idvenda = 0;
            String sql = "select max(id) id from tb_vendas;";
            
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            if(rs.next()){
                Vendas p = new Vendas();

                p.setId(rs.getInt("id"));
                idvenda = p.getId();
            }
            return idvenda;
        } catch (SQLException e) {
            throw new RuntimeException(e); 
        }
        
    }
            

}