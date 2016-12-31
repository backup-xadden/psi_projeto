package com.ementas.projecto.models;



public class Fatura {
    private long id;
    private String data;
    private Double preco;
    private String refeicao;
    private String prato;

    public Fatura () {
        this(0, null, null, null, null);
    }

    public Fatura(long id, String data, Double preco, String refeicao, String prato) {
        this.id = id;
        this.data = data;
        this.preco = preco;
        this.refeicao = refeicao;
        this.prato = prato;
    }

    public Fatura(String data, Double preco, String refeicao, String prato) {
        this.data = data;
        this.preco = preco;
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
