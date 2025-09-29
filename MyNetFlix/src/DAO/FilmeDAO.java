/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.FilmeDTO;
import VIEW.Cadastrar;
import VIEW.TelaPrincipal;
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

    

    public void inserirAtualização(FilmeDTO objFilmeDTO) {
        String sql = "insert into Catalogo_Filmes (nota_usuario, status_usuario)"
                + "values (?)";
        conexao = new ConexaoDAO().conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(4, objUsuarioDTO.getNota_usuario());
            pst.setString(3, objUsuarioDTO.getStatus_usuario());

            int add = pst.executeUpdate();

            if (add > 0) {

                pst.close();
                limpar();
                JOptionPane.showMessageDialog(null, "Nota e status inserido com sucesso!");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, " Método Inserir " + e);
        }

    }

    public void limpar() {

        DefaultTableModel modelo = (DefaultTableModel) Cadastrar.TbUsuarios.getModel();

        modelo.setRowCount(0);

        Cadastrar.txtNomeCadastro.setText(null);
        Cadastrar.txtDataCadastro.setText(null);
        Cadastrar.txtSenha.setText(null);
        Cadastrar.txtConfirSenha.setText(null);
        
 
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
