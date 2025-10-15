/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VIEW;

import DAO.FilmeDAO;
import DTO.FilmeDTO;
import DTO.UsuarioDTO;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;

public class Catalogo extends javax.swing.JInternalFrame {

    private UsuarioDTO usuarioLogado;
    private FilmeDAO f1 = new FilmeDAO();
    public List<String> faixaEtaria = Arrays.asList();
    public List<String> generos = Arrays.asList();
    public List<String> plataformas = Arrays.asList();
    UsuarioDTO u1 = new UsuarioDTO();
    FilmeDTO f2 = new FilmeDTO();
    Classificacao c2 = new Classificacao(u1, f2);

    public Catalogo(UsuarioDTO usuario) {

        initComponents();
        this.usuarioLogado = usuario;

        f1.atualizarTabelaStatus(usuarioLogado);
        generos = Arrays.asList(
                "Vazio", "Ação", "Aventura", "Animação", "Comédia", "Comédia Romântica", "Crime", "Documentário",
                "Drama", "Ficção Científica", "Fantasia", "Guerra", "Mistério", "Musical", "Policial",
                "Romance", "Suspense", "Terror", "Thriller", "Biografia", "Histórico", "Esporte",
                "Faroeste", "Família", "Outro"
        );

        FiltroGenero.removeAllItems();
        for (String genero : generos) {
            FiltroGenero.addItem(genero);
        }

        faixaEtaria = Arrays.asList(
                "Vazio", "Livre", "12 Anos", "14 Anos", "16 Anos", "18 Anos"
        );

        FiltroFaixaEtaria.removeAllItems();
        for (String faixa : faixaEtaria) {
            FiltroFaixaEtaria.addItem(faixa);
        }

        plataformas = Arrays.asList(
                "Vazio", "Netflix", "Prime Video", "HBO Max", "Disney+", "Star+",
                "Globoplay", "Paramount+", "Apple TV+", "Crunchyroll",
                "YouTube", "Claro TV+", "Telecine Play", "Now", "MUBI",
                "Plex", "Tubi TV", "Rakuten TV", "Pluto TV", "Outro"
        );

        FiltroPlataforma.removeAllItems();
        for (String plataforma : plataformas) {
            FiltroPlataforma.addItem(plataforma);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        FiltroFaixaEtaria = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        FiltroPlataforma = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TbFilmes = new javax.swing.JTable();
        FiltroGenero = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        Catalogo = new javax.swing.JMenu();
        Historico = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Catálogo de Filmes por:");

        jLabel2.setText("Gênero:");

        jLabel4.setText("Faixa Etária:");

        FiltroFaixaEtaria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FiltroFaixaEtaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroFaixaEtariaActionPerformed(evt);
            }
        });

        jLabel5.setText("Plataforma:");

        FiltroPlataforma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FiltroPlataforma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroPlataformaActionPerformed(evt);
            }
        });

        TbFilmes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Titulo", "Gênero", "Plataforma", "Faixa Etária", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        TbFilmes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TbFilmesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TbFilmes);
        TableColumn txtTituloFilme = TbFilmes.getColumnModel().getColumn(0);

        txtTituloFilme.setHeaderValue("Titulo");

        FiltroGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FiltroGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FiltroGeneroActionPerformed(evt);
            }
        });

        Catalogo.setText("Catálogo");
        Catalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CatalogoMouseClicked(evt);
            }
        });
        jMenuBar1.add(Catalogo);

        Historico.setText("Histórico");
        Historico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HistoricoMouseClicked(evt);
            }
        });
        jMenuBar1.add(Historico);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel2)
                                .addGap(39, 39, 39))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FiltroGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addComponent(FiltroFaixaEtaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(FiltroPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(FiltroFaixaEtaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(FiltroGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(FiltroPlataforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(49, 49, 49)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoricoMouseClicked
        System.out.println("Histórico aberto!");
        FiltroPlataforma.setSelectedItem("Vazio");
        FiltroFaixaEtaria.setSelectedItem("Vazio");
        FiltroGenero.setSelectedItem("Vazio");
        f1.listarFilmesAssistidos(u1);

    }//GEN-LAST:event_HistoricoMouseClicked

    private void CatalogoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CatalogoMouseClicked
        System.out.println("Catalogo aberto!");
        FiltroPlataforma.setSelectedItem("Vazio");
        FiltroFaixaEtaria.setSelectedItem("Vazio");
        FiltroGenero.setSelectedItem("Vazio");
        f1.listarFilmesFiltrados(this);
    }//GEN-LAST:event_CatalogoMouseClicked

    private void FiltroFaixaEtariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroFaixaEtariaActionPerformed
        f1.listarFilmesFiltrados(this);
    }//GEN-LAST:event_FiltroFaixaEtariaActionPerformed

    private void FiltroGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroGeneroActionPerformed
        f1.listarFilmesFiltrados(this);
    }//GEN-LAST:event_FiltroGeneroActionPerformed

    private void FiltroPlataformaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FiltroPlataformaActionPerformed
        f1.listarFilmesFiltrados(this);
    }//GEN-LAST:event_FiltroPlataformaActionPerformed

    private void TbFilmesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TbFilmesMouseClicked
        try {
            // Verificar linha selecionada
            int linha = TbFilmes.getSelectedRow();
            System.out.println("Linha selecionada: " + linha);
            System.out.println("Usuario logado: " + usuarioLogado);

            // Se não há linha selecionada ou usuário não está logado, não faz nada
            if (linha == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um filme antes de continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (usuarioLogado == null) {
                JOptionPane.showMessageDialog(this, "Usuário não logado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Pegar os dados da tabela
            String titulo = TbFilmes.getValueAt(linha, 0).toString().trim();
            String genero = TbFilmes.getValueAt(linha, 1).toString();
            String plataforma = TbFilmes.getValueAt(linha, 2).toString();
            int faixaEtaria = Integer.parseInt(TbFilmes.getValueAt(linha, 3).toString());

            // Buscar ID do filme no banco
            Integer idFilme = f1.buscarIdPorTitulo(titulo);
            if (idFilme == null || idFilme == 0) {
                JOptionPane.showMessageDialog(this, "Não foi possível identificar o ID do filme selecionado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Debug: verificar IDs
            System.out.println("Usuario ID: " + usuarioLogado.getId_usuario());
            System.out.println("Titulo selecionado: '" + titulo + "'");
            System.out.println("ID Filme buscado: " + idFilme);

            // Buscar sinopse
            String sinopse = f1.buscarSinopsePorTitulo(titulo);
            if (sinopse == null || sinopse.isEmpty()) {
                sinopse = "Sinopse não disponível.";
            }

            // Criar DTO do filme
            FilmeDTO filmeSelecionado = new FilmeDTO();
            filmeSelecionado.setId_Filme(idFilme);
            filmeSelecionado.setTitulo_Filme(titulo);
            filmeSelecionado.setGenero_Filme(genero);
            filmeSelecionado.setPlataforma_filme(plataforma);
            filmeSelecionado.setFaixaEtaria(faixaEtaria);
            filmeSelecionado.setSinopse_filme(sinopse);

            // Abrir tela de classificação
            Classificacao c2 = new Classificacao(usuarioLogado, filmeSelecionado);
            c2.setLocationRelativeTo(this);
            c2.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocorreu um erro ao selecionar o filme: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_TbFilmesMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Catalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Catalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Catalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Catalogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UsuarioDTO usuarioTeste = new UsuarioDTO();
                usuarioTeste.setId_usuario(1);
                usuarioTeste.setNome_usuario("João");

                // Passa o usuário logado para o Catalogo
                new Catalogo(usuarioTeste).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Catalogo;
    public static javax.swing.JComboBox<String> FiltroFaixaEtaria;
    public static javax.swing.JComboBox<String> FiltroGenero;
    public static javax.swing.JComboBox<String> FiltroPlataforma;
    private javax.swing.JMenu Historico;
    public static javax.swing.JTable TbFilmes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
