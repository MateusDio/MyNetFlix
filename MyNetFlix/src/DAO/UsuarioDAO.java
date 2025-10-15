package DAO;

import DTO.CadastrarDTO;
import DTO.UsuarioDTO;
import VIEW.Cadastrar;
import VIEW.Login;
import VIEW.TelaPrincipal;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UsuarioDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean logar(UsuarioDTO objusuarioDTO) {
    String sql = "SELECT * FROM cadastro_usuarios WHERE nome = ? AND senha = ?";

    try (Connection conexao = ConexaoDAO.conector();
         PreparedStatement pst = conexao.prepareStatement(sql)) {

        pst.setString(1, objusuarioDTO.getLogin_usuario());
        pst.setString(2, objusuarioDTO.getSenha_usuario());
        ResultSet rs = pst.executeQuery();

        return rs.next(); 

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

public CadastrarDTO getTipoUsuario(CadastrarDTO objCadastroDTO) {
    CadastrarDTO usuarioAutenticado = null;

    // Verifica se é admin
    if ("admin".equals(objCadastroDTO.getNome()) && "123".equals(objCadastroDTO.getSenha())) {
        usuarioAutenticado = new CadastrarDTO();
        usuarioAutenticado.setNome("admin");
    } else {
        // Login normal via banco
        String sql = "SELECT idCadastro, nome, dataNascimento, senha, confirSenha "
                   + "FROM nome WHERE nome = ? AND senha = ?";

        try (Connection conexao = ConexaoDAO.conector();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setString(1, objCadastroDTO.getNome());
            pst.setString(2, objCadastroDTO.getSenha());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    usuarioAutenticado = new CadastrarDTO();
                    usuarioAutenticado.setIdCadastro(rs.getInt("idCadastro"));
                    usuarioAutenticado.setNome(rs.getString("nome"));
                    usuarioAutenticado.setDataNascimento(rs.getString("dataNascimento"));
                    usuarioAutenticado.setSenha(rs.getString("senha"));
                    usuarioAutenticado.setConfirSenha(rs.getString("confirSenha"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 

    return usuarioAutenticado;
}

public void inserirAtualização(UsuarioDTO objUsuarioDTO) {
        String sql = "insert into tb_Usuario (nota_usuario, status_usuario)"
                + "values (?)";
        conexao = new ConexaoDAO().conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(4, objUsuarioDTO.getNota_usuario());
            pst.setString(3, objUsuarioDTO.getStatus_usuario());

            int add = pst.executeUpdate();

            if (add > 0) {

                pst.close();
                PesquisaAuto();
                limpar();
                JOptionPane.showMessageDialog(null, "Nota e status inserido com sucesso!");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, " Método Inserir " + e);
        }

    }

    public void limpar() {

        DefaultTableModel modelo = (DefaultTableModel) Cadastrar.TbCadastros.getModel();

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

    public void editar(UsuarioDTO objUsuarioDTO) {
        String sql = "update tb_Usuario set nome_usuario = ?, Login = ?, Senha = ? where id_usuario = ?";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(4, objUsuarioDTO.getId_usuario());
            pst.setString(3, objUsuarioDTO.getSenha_usuario());
            pst.setString(2, objUsuarioDTO.getLogin_usuario());
            pst.setString(1, objUsuarioDTO.getNome_usuario());
            int add = pst.executeUpdate();
            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
                PesquisaAuto();
                conexao.close();
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Método editar " + e);
        }
    }

    public void deletar(UsuarioDTO objUsuarioDTO) {
        String sql = "delete from tb_Usuario where id_usuario = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getId_usuario());
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso!");
                PesquisaAuto();
                conexao.close();
                limpar();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Método deletar " + e);

        }
    }

    public void PesquisaAuto() {
        String sql = "select * from tb_usuarioss";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) Cadastrar.TbCadastros.getModel();
            model.setNumRows(0);
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome_usuario");
                String login = rs.getString("Login");
                String senha = rs.getString("Senha");
                model.addRow(new Object[]{id, nome, login, senha});

            }
            conexao.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método Pesquisar Automático " + e);
        }
    }

}
