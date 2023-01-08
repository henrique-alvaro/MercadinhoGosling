package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Produto;

public class ProdutoDAO {
    
    public void create(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("INSERT INTO produto (descricao, qtd, preco)VALUES(?,?,?)");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQtd());
            stmt.setDouble(3, p.getPreco());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Salvo com Sucesso");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao salvar"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Produto> read(){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR DADOS"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return produtos;
    }
    public List<Produto> readForDesc(String desc){
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Produto> produtos = new ArrayList<>();
        
        try{
            stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao LIKE ?");
            stmt.setString(1, "%"+desc+"%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setQtd(rs.getInt("qtd"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "ERRO AO CARREGAR DADOS"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return produtos;
    }
    
    public void update(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("UPDATE produto SET descricao=?, qtd=?, preco=? WHERE id=?");
            stmt.setString(1, p.getDescricao());
            stmt.setInt(2, p.getQtd());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Atualizado com Sucesso");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Atualizar"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public void delete(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
            stmt = con.prepareStatement("DELETE FROM produto WHERE id=?");
            stmt.setInt(1, p.getId());
            
            stmt.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Excluido com Sucesso");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ao Excluir"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
}
