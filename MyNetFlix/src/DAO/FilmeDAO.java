package DAO;

import DTO.FilmeDTO;
import DTO.UsuarioDTO;
import VIEW.Cadastrar;
import VIEW.Catalogo;
import VIEW.Filmes;
import VIEW.TelaPrincipal;
import com.sun.xml.internal.ws.client.ContentNegotiation;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "insert into catalogo_filmes (tituloFilme, generoFilme, plataforma, faixaEtaria, sinopse)"
                + "values (?,?,?,?,?)";
        conexao = new ConexaoDAO().conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objFilmeDTO.getTitulo_Filme());
            pst.setString(2, objFilmeDTO.getGenero_Filme());
            pst.setString(3, objFilmeDTO.getPlataforma_filme());
            pst.setInt(4, objFilmeDTO.getFaixaEtaria());
            pst.setString(5, objFilmeDTO.getSinopse());
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
        String sql = "update catalogo_filmes set tituloFilme = ?, generoFilme = ?, plataforma = ?, faixaEtaria = ?, sinopse = ? where idFilme = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objFilmeDTO.getTitulo_Filme());
            pst.setString(2, objFilmeDTO.getGenero_Filme());
            pst.setString(3, objFilmeDTO.getPlataforma_filme());
            pst.setInt(4, objFilmeDTO.getFaixaEtaria());
            pst.setString(5, objFilmeDTO.getSinopse());
            pst.setInt(6, objFilmeDTO.getId_Filme());
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
                String sinopse = rs.getString("sinopse");
                model.addRow(new Object[]{id, titulo, genero, plataforma, faixa, sinopse});

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
            System.out.println("ID recebido para edição: " + objFilmeDTO.getId_Filme());
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
    String sql = "SELECT f.tituloFilme, f.generoFilme, f.plataforma, f.faixaEtaria, uf.status_visualizacao " +
                 "FROM usuario_filme1 uf " +  // Tabela correta: usuario_filme1
                 "JOIN Catalogo_Filmes f ON uf.id_filme = f.idFilme " +
                 "WHERE uf.id_usuario = ? AND uf.status_visualizacao IN ('Assistido', 'Assistindo')";

    conexao = ConexaoDAO.conector();

    try (PreparedStatement pst = conexao.prepareStatement(sql)) {
        pst.setInt(1, objUsuarioDTO.getId_usuario());
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) Catalogo.TbFilmes.getModel();
        model.setNumRows(0); // Limpa a tabela

        while (rs.next()) {
            String titulo = rs.getString("tituloFilme");
            String genero = rs.getString("generoFilme");
            String plataforma = rs.getString("plataforma");
            int faixaEtaria = rs.getInt("faixaEtaria");
            String status = rs.getString("status_visualizacao");

            
            model.addRow(new Object[]{titulo, genero, plataforma, faixaEtaria, status});
            System.out.println("Filme: " + titulo + " - Status: " + status);
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

//     public void listar() {
//        String sql = "SELECT tituloFilme FROM Catalogo_Filmes";
//        conexao = ConexaoDAO.conector();
//
//        try {
//            pst = conexao.prepareStatement(sql);
//            rs = pst.executeQuery();
//            DefaultTableModel model = (DefaultTableModel) Catalogo.TbFilmes.getModel();
//            model.setNumRows(0);
//
//            while (rs.next()) {
//
//                String Titulo = rs.getString("tituloFilme");
//
//                model.addRow(new Object[]{Titulo});
//
//            }
//            conexao.close();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Método Listar " + e.getMessage());
//        }
//
//    }
//     
//   
 public void listarFilmesFiltrados(Catalogo c1) {
    Object generoObj = c1.FiltroGenero.getSelectedItem();
    Object faixaEtariaObj = c1.FiltroFaixaEtaria.getSelectedItem();
    Object plataformaObj = c1.FiltroPlataforma.getSelectedItem();

    String genero = (generoObj != null) ? generoObj.toString().trim() : "Vazio";
    String faixaEtaria = (faixaEtariaObj != null) ? faixaEtariaObj.toString().trim() : "Vazio";
    String plataforma = (plataformaObj != null) ? plataformaObj.toString().trim() : "Vazio";

    StringBuilder sql = new StringBuilder("SELECT tituloFilme, generoFilme, plataforma, faixaEtaria FROM Catalogo_Filmes WHERE 1=1");
    List<String> parametros = new ArrayList<>();

    if (!genero.equalsIgnoreCase("Vazio")) {
        if (genero.equalsIgnoreCase("Outro")) {
            List<String> generosConhecidos = c1.generos;
            if (generosConhecidos != null && !generosConhecidos.isEmpty()) {
                sql.append(" AND generoFilme NOT IN (");
                for (int i = 0; i < generosConhecidos.size(); i++) {
                    sql.append("?");
                    if (i < generosConhecidos.size() - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");
                parametros.addAll(generosConhecidos);
            }
        } else {
            sql.append(" AND generoFilme = ?");
            parametros.add(genero);
        }
    }

    if (!faixaEtaria.equalsIgnoreCase("Vazio")) {
        if (faixaEtaria.equalsIgnoreCase("Outro")) {
            List<String> faixasConhecidas = c1.faixaEtaria;
            if (faixasConhecidas != null && !faixasConhecidas.isEmpty()) {
                sql.append(" AND faixaEtaria NOT IN (");
                for (int i = 0; i < faixasConhecidas.size(); i++) {
                    sql.append("?");
                    if (i < faixasConhecidas.size() - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");
                parametros.addAll(faixasConhecidas);
            }
        } else {
            sql.append(" AND faixaEtaria = ?");
            parametros.add(faixaEtaria);
        }
    }

    if (!plataforma.equalsIgnoreCase("Vazio")) {
        if (plataforma.equalsIgnoreCase("Outro")) {
            List<String> plataformasConhecidas = c1.plataformas;
            if (plataformasConhecidas != null && !plataformasConhecidas.isEmpty()) {
                sql.append(" AND plataforma NOT IN (");
                for (int i = 0; i < plataformasConhecidas.size(); i++) {
                    sql.append("?");
                    if (i < plataformasConhecidas.size() - 1) {
                        sql.append(", ");
                    }
                }
                sql.append(")");
                parametros.addAll(plataformasConhecidas);
            }
        } else {
            sql.append(" AND plataforma = ?");
            parametros.add(plataforma);
        }
    }

    conexao = ConexaoDAO.conector();

    try {
        pst = conexao.prepareStatement(sql.toString());

        for (int i = 0; i < parametros.size(); i++) {
            pst.setString(i + 1, parametros.get(i));
        }

        rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) Catalogo.TbFilmes.getModel();
        model.setNumRows(0);

        while (rs.next()) {
            String titulo = rs.getString("tituloFilme");
            String generoResult = rs.getString("generoFilme");
            String plataformaResult = rs.getString("plataforma");
            String faixaEtariaResult = rs.getString("faixaEtaria");

            model.addRow(new Object[]{titulo, generoResult, plataformaResult, faixaEtariaResult});
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar filmes com filtros: " + e.getMessage());
    } finally {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (Exception e) {
        }
    }
}
}

