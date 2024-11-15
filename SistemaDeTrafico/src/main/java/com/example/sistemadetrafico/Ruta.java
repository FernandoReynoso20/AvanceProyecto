package com.example.sistemadetrafico;

public class Ruta {
    private Parada origen;
    private Parada destino;
    private int tiempo; // minutos
    private int transbordos;// cantidad de transbordos

    public Ruta(Parada origen, Parada destino, int tiempo, int transbordos) {
        this.origen = origen;
        this.destino = destino;
        this.tiempo = tiempo;
        this.transbordos = transbordos;
    }

    public Parada getOrigen() {
        return origen;
    }

    public void setOrigen(Parada origen) {
        this.origen = origen;
    }

    public Parada getDestino() {
        return destino;
    }

    public void setDestino(Parada destino) {
        this.destino = destino;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getTransbordos() {
        return transbordos;
    }

    public void setTransbordos(int transbordos) {
        this.transbordos = transbordos;
    }
}