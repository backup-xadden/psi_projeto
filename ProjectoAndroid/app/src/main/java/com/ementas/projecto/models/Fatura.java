package com.ementas.projecto.models;

//java.util.Calendar;


public class Fatura {
    private long id;
    //private Calendar data;
    private String data;
    private Double preco;
    private String cantina;
    private String refeicao;
    private String prato;

    public Fatura(long id, String data, Double preco, String cantina, String refeicao, String prato) {
        this.id = id;
        this.data = data;
        this.preco = preco;
        this.cantina = cantina;
        this.refeicao = refeicao;
        this.prato = prato;
    }

    public Fatura(String data, Double preco, String cantina, String refeicao, String prato) {
        this.data = data;
        this.preco = preco;
        this.cantina = cantina;
        this.refeicao = refeicao;
        this.prato = prato;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCantina() {
        return cantina;
    }

    public void setCantina(String cantina) {
        this.cantina = cantina;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(String refeicao) {
        this.refeicao = refeicao;
    }

    public String getPrato() {
        return prato;
    }

    public void setPrato(String prato) {
        this.prato = prato;
    }
}
