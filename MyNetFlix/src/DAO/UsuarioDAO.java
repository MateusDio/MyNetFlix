
package DAO;

import DTO.UsuarioDTO;
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
    
    public void logar(UsuarioDTO objusuarioDTO){
    String sql = "select * from tb_Usuario where Login = ? and Senha = ?";
        conexao = ConexaoDAO.conector();
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objusuarioDTO.getLogin_usuario());
            pst.setString(2, objusuarioDTO.getSenha_usuario());
            
            rs = pst.executeQuery();
            
            if(rs.next()){
                String login = rs.getString(3);
                System.out.println(login);
                
                if(login.equals("admin")){
                    TelaPrincipal pr = new TelaPrincipal();
                    pr.setVisible(true);
                    TelaPrincipal.menuAjuda.setEnabled(true);
                    TelaPrincipal.subMenuAdcFilmes.setEnabled(true);
                    TelaPrincipal.txtBnv.setText("Bem vindo, " + rs.getString(2));
                    TelaPrincipal.txtBnv.setForeground(Color.RED);
                    conexao.close();
                }else{
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    principal.txtBnv.setText("Bem vindo, " + rs.getString(2));
                    TelaPrincipal.txtBnv.setForeground(Color.BLUE);
                    conexao.close();
                
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos");
                
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "** tela login ***" + e);
        }
    }
    
     public void inserirAtualização(UsuarioDTO objUsuarioDTO){
        String sql = "insert into tb_Usuario (nota_usuario, status_usuario)"
            + "values (?)";
        conexao = new ConexaoDAO().conector();
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setInt(4, objUsuarioDTO.getNota_usuario());
            pst.setString(3, objUsuarioDTO.getStatus_usuario());
          
            int add = pst.executeUpdate();
            
            if(add > 0){
                
                pst.close();
                limpar();
                JOptionPane.showMessageDialog(null, "Nota e status inserido com sucesso!");
                
            }
            
          
            
        } catch (Exception e){
            
            JOptionPane.showMessageDialog(null," Método Inserir " + e);
        }
        
    }
     
    public void limpar() {
  
 
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
            if (conexao != null) conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}
