package com.example.adam.aplikacja;

/**
 * Created by Adam on 25.03.2016.
 */
public class Data {
    private long id;
    private String daty;
    private long przyci;
    private double czas;

    public Data(long id, String daty, long przyci, double czas) {
        this.id = id;
        this.daty = daty;
        this.przyci = przyci;
        this.czas= czas;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDaty() {
        return daty;
    }

    public void setDaty(String daty) {
        this.daty = daty;
    }
    public long getPrzyci() {
        return przyci;
    }

    public void setPrzyci(long przyci) {
        this.przyci = przyci;
    }
    public double getCzas() {
        return czas;
    }

    public void setCzas(double czas) {
        this.czas = czas;
    }

}
