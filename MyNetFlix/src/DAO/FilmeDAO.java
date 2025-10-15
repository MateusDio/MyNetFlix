package DAO;

import DTO.FilmeDTO;
import DTO.UsuarioDTO;
import VIEW.Catalogo;
import VIEW.Filmes;
import VIEW.Classificacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FilmeDAO {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public void add(FilmeDTO objFilmeDTO) {
        String sql = "INSERT INTO catalogo_filmes (tituloFilme, generoFilme, plataforma, faixaEtaria, sinopse) VALUES (?,?,?,?,?)";
        conexao = new ConexaoDAO().conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objFilmeDTO.getTitulo_Filme());
            pst.setString(2, objFilmeDTO.getGenero_Filme());
            pst.setString(3, objFilmeDTO.getPlataforma_filme());
            pst.setInt(4, objFilmeDTO.getFaixaEtaria());
            pst.setString(5, objFilmeDTO.getSinopse_filme());
            int add = pst.executeUpdate();
            if (add > 0) {
                pesquisaAuto();
                limpar();
                JOptionPane.showMessageDialog(null, "Filme adicionado com sucesso!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método adicionar " + e);
        } finally {
            fecharConexao();
        }
    }

    public void editar(FilmeDTO objFilmeDTO) {
        String sql = "UPDATE catalogo_filmes SET tituloFilme = ?, generoFilme = ?, plataforma = ?, faixaEtaria = ?, sinopse = ? WHERE idFilme = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, objFilmeDTO.getTitulo_Filme());
            pst.setString(2, objFilmeDTO.getGenero_Filme());
            pst.setString(3, objFilmeDTO.getPlataforma_filme());
            pst.setInt(4, objFilmeDTO.getFaixaEtaria());
            pst.setString(5, objFilmeDTO.getSinopse_filme());
            pst.setInt(6, objFilmeDTO.getId_Filme());
            int add = pst.executeUpdate();
            if (add > 0) {
                JOptionPane.showMessageDialog(null, "Filme editado com sucesso!");
                pesquisaAuto();
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método editar " + e);
        } finally {
            fecharConexao();
        }
    }

    public void limpar() {
        DefaultTableModel modelo = (DefaultTableModel) Filmes.tbFilmes.getModel();
        modelo.setRowCount(0);
    }

    public void pesquisaAuto() {
        String sql = "SELECT * FROM catalogo_filmes";
        conexao = ConexaoDAO.conector();
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) Filmes.tbFilmes.getModel();
            model.setNumRows(0);
            while (rs.next()) {
                int id = rs.getInt("idFilme");
                String titulo = rs.getString("tituloFilme");
                String genero = rs.getString("generoFilme");
                String plataforma = rs.getString("plataforma");
                int faixa = rs.getInt("faixaEtaria");
                String sinopse = rs.getString("sinopse");
                model.addRow(new Object[]{id, titulo, genero, plataforma, faixa, sinopse});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método pesquisar " + e);
        } finally {
            fecharConexao();
        }
    }

    public void deletar(FilmeDTO objFilmeDTO) {
        String sql = "DELETE FROM catalogo_filmes WHERE idFilme = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objFilmeDTO.getId_Filme());
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Filme deletado com sucesso!");
                pesquisaAuto();
                limpar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Método deletar " + e);
        } finally {
            fecharConexao();
        }
    }

 public void addStatus(int idUsuario, int idFilme, String status) throws SQLException {

    String sqlCheck = "SELECT * FROM usuario_filme1 WHERE id_usuario = ? AND id_filme = ?";
    try (Connection conexao = ConexaoDAO.conector();
         PreparedStatement pstCheck = conexao.prepareStatement(sqlCheck)) {

        pstCheck.setInt(1, idUsuario);
        pstCheck.setInt(2, idFilme);
        ResultSet rs = pstCheck.executeQuery();

        if (rs.next()) {
            // Atualiza status existente
            String sqlUpdate = "UPDATE usuario_filme1 SET status_visualizacao = ? WHERE id_usuario = ? AND id_filme = ?";
            try (PreparedStatement pstUpdate = conexao.prepareStatement(sqlUpdate)) {
                pstUpdate.setString(1, status);
                pstUpdate.setInt(2, idUsuario);
                pstUpdate.setInt(3, idFilme);
                pstUpdate.executeUpdate();
            }
        } else {
            // Insere novo registro
            String sqlInsert = "INSERT INTO usuario_filme1 (id_usuario, id_filme, status_visualizacao) VALUES (?, ?, ?)";
            try (PreparedStatement pstInsert = conexao.prepareStatement(sqlInsert)) {
                pstInsert.setInt(1, idUsuario);
                pstInsert.setInt(2, idFilme);
                pstInsert.setString(3, status);
                pstInsert.executeUpdate();
            }
        }
    }
}


    public void atualizarTabelaStatus(UsuarioDTO usuario) {
        if (usuario == null) {
            JOptionPane.showMessageDialog(null, "Usuário não informado.");
            return;
        }
        if (Filmes.tbFilmes == null) {
            JOptionPane.showMessageDialog(null, "Tabela tbFilmes não inicializada.");
            return;
        }

        String sql = "SELECT f.idFilme, f.tituloFilme, f.generoFilme, f.plataforma, f.faixaEtaria, uf.status_visualizacao "
                   + "FROM catalogo_filmes f "
                   + "LEFT JOIN usuario_filme1 uf ON f.idFilme = uf.id_filme AND uf.id_usuario = ?";

        try (Connection conexao = new ConexaoDAO().conector();
             PreparedStatement pst = conexao.prepareStatement(sql)) {

            pst.setInt(1, usuario.getId_usuario());
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) Filmes.tbFilmes.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                int id = rs.getInt("idFilme");
                String titulo = rs.getString("tituloFilme");
                String genero = rs.getString("generoFilme");
                String plataforma = rs.getString("plataforma");
                int faixa = rs.getInt("faixaEtaria");
                String status = rs.getString("status_visualizacao");
                if (status == null) status = "Não visto";
                model.addRow(new Object[]{id, titulo, genero, plataforma, faixa, status});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar tabela: " + e.getMessage());
        }
    }

 public Integer buscarIdPorTitulo(String titulo) {
    Integer idFilme = null;
    String sql = "SELECT idFilme FROM catalogo_filmes WHERE TRIM(tituloFilme) = ?";
    try (Connection conexao = ConexaoDAO.conector();
         PreparedStatement pst = conexao.prepareStatement(sql)) {
        pst.setString(1, titulo.trim());
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                idFilme = rs.getInt("idFilme");
            }
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao buscar ID do filme: " + e.getMessage());
    }
    return idFilme;
}

    public String buscarSinopsePorTitulo(String titulo) {
        String sinopse = null;
        String sql = "SELECT sinopse FROM catalogo_filmes WHERE tituloFilme = ?";
        conexao = ConexaoDAO.conector();

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, titulo);
            rs = pst.executeQuery();
            if (rs.next()) {
                sinopse = rs.getString("sinopse");
            }
        } catch (Exception e) {
            System.out.println("Erro ao buscar sinopse: " + e.getMessage());
        } finally {
            fecharConexao();
        }
        return sinopse;
    }

public void listarFilmesAssistidos(UsuarioDTO usuario) {
    String sql = "SELECT f.tituloFilme, f.generoFilme, f.plataforma, f.faixaEtaria, uf.status_visualizacao "
               + "FROM usuario_filme1 uf "
               + "JOIN catalogo_filmes f ON uf.id_filme = f.idFilme "
               + "WHERE uf.id_usuario = ? AND uf.status_visualizacao IN ('Assistido', 'Assistindo')";

    try (Connection conexao = ConexaoDAO.conector();
         PreparedStatement pst = conexao.prepareStatement(sql)) {

        pst.setInt(1, usuario.getId_usuario());
        ResultSet rs = pst.executeQuery();

        DefaultTableModel model = (DefaultTableModel) Catalogo.TbFilmes.getModel();
        model.setNumRows(0);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("tituloFilme"),
                rs.getString("generoFilme"),
                rs.getString("plataforma"),
                rs.getInt("faixaEtaria"),
                rs.getString("status_visualizacao")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar filmes assistidos: " + e.getMessage());
        e.printStackTrace();
    }
}

    public void listarFilmesFiltrados(Catalogo c1) {
        Object generoObj = c1.FiltroGenero.getSelectedItem();
        Object faixaEtariaObj = c1.FiltroFaixaEtaria.getSelectedItem();
        Object plataformaObj = c1.FiltroPlataforma.getSelectedItem();

        String genero = (generoObj != null) ? generoObj.toString().trim() : "Vazio";
        String faixaEtaria = (faixaEtariaObj != null) ? faixaEtariaObj.toString().trim() : "Vazio";
        String plataforma = (plataformaObj != null) ? plataformaObj.toString().trim() : "Vazio";

        StringBuilder sql = new StringBuilder("SELECT tituloFilme, generoFilme, plataforma, faixaEtaria FROM catalogo_filmes WHERE 1=1");
        List<String> parametros = new ArrayList<>();

        if (!genero.equalsIgnoreCase("Vazio")) {
            if (genero.equalsIgnoreCase("Outro")) {
                List<String> generosConhecidos = c1.generos;
                if (generosConhecidos != null && !generosConhecidos.isEmpty()) {
                    sql.append(" AND generoFilme NOT IN (");
                    for (int i = 0; i < generosConhecidos.size(); i++) {
                        sql.append("?");
                        if (i < generosConhecidos.size() - 1) sql.append(", ");
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
                        if (i < faixasConhecidas.size() - 1) sql.append(", ");
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
                        if (i < plataformasConhecidas.size() - 1) sql.append(", ");
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
            fecharConexao();
        }
    }

    private void fecharConexao() {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
            if (conexao != null) conexao.close();
        } catch (Exception e) {
        }
    }
}

