/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ADAMERIQUEMACHADOBOA
 */
public class ProdutosDAO {
    private Connection con;
    
    //Construtor
    public ProdutosDAO(){
        this.con = new ConnectionFactory().getConnection();
    }
    //Metodos 
    public void cadastrar(Produtos obj){
        try {
            // Inicializando Sql
            String sql = "insert into tb_produtos(descricao,preco,qtd_estoque,for_id) values(?,?,?,?)";
            //Conectanco com o banco de dados
            PreparedStatement stmt = con.prepareStatement(sql);
            //Passando valores para o banco de dados
            stmt.setString(1,obj.getDescricao());
            stmt.setDouble(2,obj.getPreco());
            stmt.setInt(3,obj.getQtd_estoque());
            stmt.setInt(4,obj.getFornecedor().getId());
            
            stmt.execute();
            stmt.close();
            //Mensagem de cadastrado com sucesso
            JOptionPane.showMessageDialog(null,"Prosuto cadastrado com sucesso");
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Erro"+e);
        }
        
    }
 
    //Metod altera Produtos 
    

    public void alterar(Produtos obj) {
        try {

            String sql = "update tb_produtos set descricao=?,preco=?,qtd_estoque=?,for_id=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5, obj.getId());
            
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Alterando com sucesso!!!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "ERRO" + e);
        }
    }
    
    public void excluir(Produtos obj) {
        try {

            String sql = "DELETE FROM tb_produtos where id=?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Excluindo com sucesso!!!");

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "ERRO"+e);
        }

    }

    public List<Produtos> listarProdutos(){
        try {
            List<Produtos> lista = new ArrayList<>();
            
            //inicializando a String e referenciando a chave estrangeira
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p"
                    + " inner join tb_fornecedores as f on(p.for_id = f.id)"; 
            // Conectando ao banco de dados 
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                lista.add(obj);
        
            }
            return lista;
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Nao consegui encontrar o produto"+ e);
           return null;
        }
        
    }
    // Metodo listar Produtos por nome
    public List<Produtos> listarProdutosPorNome(String nome){
        try {
            List<Produtos> lista = new ArrayList<>();
            
            //inicializando a String e referenciando a chave estrangeira
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p"
                    + " inner join tb_fornecedores as f on(p.for_id = f.id)where p.descricao like ?"; 
            // Conectando ao banco de dados 
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Produtos obj = new Produtos();
                Fornecedores f = new Fornecedores();
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                lista.add(obj);
        
            }
            return lista;
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Nao consegui encontrar o produto"+ e);
           return null;
        }
        
    }
     public Produtos consultaProdutosPorNome(String nome){
        try {
            
            //inicializando a String e referenciando a chave estrangeira
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p"
                    + " inner join tb_fornecedores as f on(p.for_id = f.id)where p.descricao = ? ?"; 
            // Conectando ao banco de dados 
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,nome);
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();
            
                    
            
            if(rs.next()){
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                
            }
            return obj;
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Nao consegui encontrar o produto"+ e);
           return null;
        }
        
    }
     //Busca produto por codigo de barra
     public Produtos buscaPorCodigo(int id){
        try {
            
            //inicializando a String e referenciando a chave estrangeira
            String sql = "select p.id, p.descricao, p.preco, p.qtd_estoque, f.nome from tb_produtos as p"
                    + " inner join tb_fornecedores as f on(p.for_id = f.id)where p.id = ? "; 
            // Conectando ao banco de dados 
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            Produtos obj = new Produtos();
            Fornecedores f = new Fornecedores();
            
                    
            
            if(rs.next()){
                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));
                
                f.setNome(rs.getString("f.nome"));
                
                obj.setFornecedor(f);
                
            }
            return obj;
            
        } catch (Exception e) {
           JOptionPane.showMessageDialog(null,"Nao consegui encontrar o produto"+ e);
           return null;
        }
        
    }
}

    