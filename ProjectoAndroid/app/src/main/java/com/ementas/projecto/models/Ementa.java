package com.ementas.projecto.models;

public class Ementa {
    private long id;
    private String diadasemana;
    private String data;
    private String refeicao;
    private String sopa;
    private String carne;
    private String peixe;
    private String vegetariano;
    private String sobremesa;
    private int haementa;

    public Ementa(long id, String diadasemana, String data, String refeicao, String sopa, String carne, String peixe, String vegetariano, String sobremesa, int haementa) {
        this.id = id;
        this.diadasemana = diadasemana;
        this.data = data;
        this.refeicao = refeicao;
        this.sopa = sopa;
        this.carne = carne;
        this.peixe = peixe;
        this.vegetariano = vegetariano;
        this.sobremesa = sobremesa;
        this.haementa = haementa;
    }

    public Ementa(String diadasemana, String data, String refeicao, String sopa, String carne, String peixe, String vegetariano, String sobremesa) {
        this.diadasemana = diadasemana;
        this.data = data;
        this.refeicao = refeicao;
        this.sopa = sopa;
        this.carne = carne;
        this.peixe = peixe;
        this.vegetariano = vegetariano;
        this.sobremesa = sobremesa;
    }

    public Ementa() {
        this(0, null, null, null, null, null, null, null, null, 0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDiadasemana() {
        return diadasemana;
    }

    public void setDiadasemana(String diadasemana) {
        this.diadasemana = diadasemana;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(String refeicao) {
        this.refeicao = refeicao;
    }

    public String getSopa() {
        return sopa;
    }

    public void setSopa(String sopa) {
        this.sopa = sopa;
    }

    public String getCarne() {
        return carne;
    }

    public void setCarne(String carne) {
        this.carne = carne;
    }

    public String getPeixe() {
        return peixe;
    }

    public void setPeixe(String peixe) {
        this.peixe = peixe;
    }

    public String getVegetariano() {
        return vegetariano;
    }

    public void setVegetariano(String vegetariano) {
        this.vegetariano = vegetariano;
    }

    public String getSobremesa() {
        return sobremesa;
    }

    public void setSobremesa(String sobremesa) {
        this.sobremesa = sobremesa;
    }

    public int getHaementa() {
        return haementa;
    }

    public void setHaementa(int haementa) {
        this.haementa = haementa;
    }


}
