package DAO;

import DTO.CadastrarDTO;
import VIEW.Cadastrar;
import VIEW.TelaPrincipal;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CadastrarDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void cadastrar(CadastrarDTO objCadastroDTO) {
        String sql = "insert into cadastro_usuarios (idCadastro, nome, dataNascimento, senha, confirSenha)"
                + "values (?,?,?,?,?)";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objCadastroDTO.getIdCadastro());
            pst.setString(2, objCadastroDTO.getNome());
            pst.setString(3, objCadastroDTO.getDataNascimento());
            pst.setString(4, objCadastroDTO.getSenha());
            pst.setString(5, objCadastroDTO.getConfirSenha());

            int add = pst.executeUpdate();
            if (add > 0) {
                listar();
                pst.close();
                limpar();
                JOptionPane.showMessageDialog(null, "Usuario adicionado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método adicionar " + e);
        }

    }

    public void listar() {
        String sql = "select * from cadastro_usuarios";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) Cadastrar.TbCadastros.getModel();
            model.setNumRows(0);
            while (rs.next()) {
                int id = rs.getInt("idCadastro");
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("dataNascimento");
                String senha = rs.getString("senha");
                String confirSenha = rs.getString("confirSenha");
                model.addRow(new Object[]{id, nome, dataNascimento, senha, confirSenha});

            }
            conexao.close();
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Método listar " + e);
        }
    }

    public void deletar(CadastrarDTO objCadastrarDTO) {
        String sql = "delete from cadastro_usuarios where idCadastro = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objCadastrarDTO.getIdCadastro());
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro removido com sucesso.");
                listar();
                conexao.close();
                limpar();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método deletar " + e);
        }

    }

    public void limpar() {
        DefaultTableModel modelo = (DefaultTableModel) Cadastrar.TbCadastros.getModel();
        modelo.setRowCount(0);
    }

    public void editar(CadastrarDTO objCadastrarDTO) {
        String sql = "update cadastro_usuarios set nome = ?, dataNascimento = ?, senha = ?, confirSenha = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objCadastrarDTO.getNome());
            pst.setString(2, objCadastrarDTO.getDataNascimento());
            pst.setString(3, objCadastrarDTO.getSenha());
            pst.setString(4, objCadastrarDTO.getConfirSenha());
            int add = pst.executeUpdate();
            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Cadastro editado com sucesso.");
                listar();
                conexao.close();
                limpar();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método editar " + e);
        }

    }
}
