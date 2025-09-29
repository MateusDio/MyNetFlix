/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;


public class FilmeDTO {
    private int id_Filme,FaixaEtaria;
    private String titulo_Filme, genero_Filme, plataforma_filme;

    public int getId_Filme() {
        return id_Filme;
    }

    public void setId_Filme(int id_Filme) {
        this.id_Filme = id_Filme;
    }

    public int getFaixaEtaria() {
        return FaixaEtaria;
    }

    public void setFaixaEtaria(int FaixaEtaria) {
        this.FaixaEtaria = FaixaEtaria;
    }

    public String getTitulo_Filme() {
        return titulo_Filme;
    }

    public void setTitulo_Filme(String titulo_Filme) {
        this.titulo_Filme = titulo_Filme;
    }

    public String getGenero_Filme() {
        return genero_Filme;
    }

    public void setGenero_Filme(String genero_Filme) {
        this.genero_Filme = genero_Filme;
    }

    public String getPlataforma_filme() {
        return plataforma_filme;
    }

    public void setPlataforma_filme(String plataforma_filme) {
        this.plataforma_filme = plataforma_filme;
    }
    
    
}
