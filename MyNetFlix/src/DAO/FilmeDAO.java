/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.FilmeDTO;
import DTO.UsuarioDTO;
import VIEW.Cadastrar;
import VIEW.Filmes;
import VIEW.TelaPrincipal;
import com.sun.xml.internal.ws.client.ContentNegotiation;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aluno.saolucas
 */
public class FilmeDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void add(FilmeDTO objFilmeDTO) {
        String sql = "insert into catalogo_filmes (idFilme, tituloFilme, generoFilme, plataforma, faixaEtaria)"
                + "values (?,?,?,?,?)";
        conexao = new ConexaoDAO().conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objFilmeDTO.getId_Filme());
            pst.setString(2, objFilmeDTO.getTitulo_Filme());
            pst.setString(3, objFilmeDTO.getGenero_Filme());
            pst.setString(4, objFilmeDTO.getPlataforma_filme());
            pst.setInt(5, objFilmeDTO.getFaixaEtaria());
            int add = pst.executeUpdate();
            if (add > 0) {
                pesquisaAuto();
                pst.close();
                limpar();              
                JOptionPane.showMessageDialog(null, "Filme adicionado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método adicionar " + e);
        }

    }

    public void editar(FilmeDTO objFilmeDTO) {
        String sql = "update catalogo_filmes set tituloFilme = ?, generoFilme = ?, plataforma = ?, faixaEtaria = ? where idFilme = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objFilmeDTO.getTitulo_Filme());
            pst.setString(2, objFilmeDTO.getGenero_Filme());
            pst.setString(3, objFilmeDTO.getPlataforma_filme());
            pst.setInt(4, objFilmeDTO.getFaixaEtaria());
            pst.setInt(5, objFilmeDTO.getId_Filme());
            int add = pst.executeUpdate();
            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Filme editado com sucesso!");
                pesquisaAuto();
                conexao.close();
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método editar " + e);
        }
    }

         public void limpar() {
        DefaultTableModel modelo = (DefaultTableModel) Filmes.tbFilmes.getModel();
        modelo.setRowCount(0);
    }

   

    public void pesquisaAuto() {
        String sql = "select * from catalogo_filmes";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) Filmes.tbFilmes.getModel();
            model.setNumRows(0);
            while (rs.next()) {
                int id = rs.getInt("idFilme");
                String titulo = rs.getString("tituloFIlme");
                String genero = rs.getString("generoFilme");
                String plataforma = rs.getString("plataforma");
                int faixa = rs.getInt("faixaEtaria");
                model.addRow(new Object[]{id, titulo, genero, plataforma, faixa});

            }
            conexao.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método pesquisar " + e);
        }
    }

    public void deletar(FilmeDTO objFilmeDTO) {
        String sql = "delete from catalogo_filmes where idFilme = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objFilmeDTO.getId_Filme());
            int del = pst.executeUpdate();
            if (del > 0) {               
                JOptionPane.showMessageDialog(null, "Filme deletado com sucesso!");                
                pesquisaAuto();        
                conexao.close();
                limpar();
             
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método deletar " + e);
        }

    }
    
    
     public void listarFilmesAssistidos(UsuarioDTO objUsuarioDTO) {
        String sql = "SELECT * FROM tb_Usuario WHERE status_usuario IN ('Assistido', 'Assistindo') AND id_usuario = ?";
        conexao = ConexaoDAO.conector();

        try (PreparedStatement pst = conexao.prepareStatement(sql)) {
            pst.setInt(1, objUsuarioDTO.getId_usuario());
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                int idFilme = rs.getInt(objUsuarioDTO.getId_usuario());
                String nomeFilme = rs.getString("nome_filme");
                String status = rs.getString(objUsuarioDTO.getStatus_usuario());

                System.out.println("Filme: " + nomeFilme + " - Status: " + status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
     
     

}
