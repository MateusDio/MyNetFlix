
package DAO;

import DTO.CadastrarDTO;
import VIEW.TelaPrincipal;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;


public class CadastrarDAO {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public void cadastrar (CadastrarDTO objCadastroDTO){
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

            rs = pst.executeQuery();

            if (rs.next()) {
                String login = rs.getString(3);
                System.out.println(login);

                if (login.equals("admin")) {
                    TelaPrincipal pr = new TelaPrincipal();
                    pr.setVisible(true);
                    TelaPrincipal.menuAjuda.setEnabled(true);
                    TelaPrincipal.subMenuAdcFilmes.setEnabled(true);
                    TelaPrincipal.txtBnv.setText("Bem vindo, " + rs.getString(2));
                    TelaPrincipal.txtBnv.setForeground(Color.RED);
                    conexao.close();
                } else {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    principal.txtBnv.setText("Bem vindo, " + rs.getString(2));
                    TelaPrincipal.txtBnv.setForeground(Color.BLUE);
                    conexao.close();

                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "** tela login ***" + e);
        }
    }
        
    }
    
    

