package com.example.sistemadetrafico;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private List<Parada> paradas;
    private List<Ruta> rutas;
    private List<List<Parada>> listaAdyacencia;
    //private String mensajeError;

    public Grafo() {
        paradas = new ArrayList<>();
        rutas = new ArrayList<>();
        listaAdyacencia = new ArrayList<>();
        //mensajeError = "";
    }
    public String agregarParada(Parada parada) {
        paradas.add(parada);
        listaAdyacencia.add(new ArrayList<>());
        return "La ruta se ha creado correctamente";
    }

    public String agregarRuta(Ruta ruta) {
        rutas.add(ruta);
        int indexOrigen = paradas.indexOf(ruta.getOrigen());
        listaAdyacencia.get(indexOrigen).add(ruta.getDestino());
        ruta.getOrigen().agregarRuta(ruta);
        return "Se agrego la ruta de Parada" + ruta.getOrigen() + "a Parada" + ruta.getDestino();
    }

    public List<Parada> Dijkstra(Parada inicio, Parada destino) {
        int n = paradas.size();
        boolean[] visitado = new boolean[n];
        Parada[] anterior = new Parada[n];
        int[] tiempos = new int[n];

        // Inicializar arreglos
        for (int i = 0; i < n; i++) {
            tiempos[i] = Integer.MAX_VALUE;
            visitado[i] = false;
            anterior[i] = null;
        }

        int inicioIndex = paradas.indexOf(inicio);
        tiempos[inicioIndex] = 0;

        // Encontrar el camino más corto
        for (int cont = 0; cont < n - 1; cont++) {
            int minTiempo = Integer.MAX_VALUE;
            int minIndex = -1;

            // Encontrar el vértice no visitado con el menor tiempo
            for (int v = 0; v < n; v++) {
                if (!visitado[v] && tiempos[v] <= minTiempo) {
                    minTiempo = tiempos[v];
                    minIndex = v;
                }
            }

            if (minIndex == -1) break;

            visitado[minIndex] = true;
            Parada paradaActual = paradas.get(minIndex);

            // Actualizar los tiempos de los vértices adyacentes
            for (Ruta ruta : paradaActual.getRutasConectadas()) {
                int destIndex = paradas.indexOf(ruta.getDestino());
                if (!visitado[destIndex]) {
                    int nuevoTiempo = tiempos[minIndex] + ruta.getTiempo();
                    if (nuevoTiempo < tiempos[destIndex]) {
                        tiempos[destIndex] = nuevoTiempo;
                        anterior[destIndex] = paradaActual;
                    }
                }
            }
        }

        // Construir el trayecto
        List<Parada> trayecto = new ArrayList<>();
        Parada actual = destino;
        while (actual != null) {
            trayecto.addFirst(actual);
            int actualIndice = paradas.indexOf(actual);
            actual = anterior[actualIndice];
        }

        return trayecto;
    }

    public String modificarParada(Parada parada, int nuevoNumero) {
        int index = paradas.indexOf(parada);
        parada = paradas.get(index);
        parada.setNumero(nuevoNumero);
        return "Se modifico correctamente la parada";
    }

    public String modificarRuta(Ruta ruta, Parada nuevoOrigen, Parada nuevoDestino, int nuevoTiempo, int nuevosTransbordos) {

        ruta.getOrigen().eliminarRuta(ruta);
        listaAdyacencia.get(paradas.indexOf(ruta.getOrigen())).remove(ruta.getDestino());
        ruta.setOrigen(nuevoOrigen);
        ruta.setDestino(nuevoDestino);
        ruta.setTiempo(nuevoTiempo);
        ruta.setTransbordos(nuevosTransbordos);

        nuevoOrigen.agregarRuta(ruta);
        listaAdyacencia.get(paradas.indexOf(nuevoOrigen)).add(nuevoDestino);
        return "Se modifico correctamente la ruta";
    }

    public String eliminarParada(Parada parada) {
        int i = paradas.indexOf(parada);

        paradas.remove(i);
        listaAdyacencia.remove(i);
        rutas.removeIf(ruta -> ruta.getOrigen().equals(parada) || ruta.getDestino().equals(parada));
        return "La parada se ha eliminado correctamente";
    }
    public void eliminarRuta(Ruta ruta) {
        rutas.remove(ruta);
        int iOrigen = paradas.indexOf(ruta.getOrigen());
        if (iOrigen != -1) {
            listaAdyacencia.get(iOrigen).remove(ruta.getDestino());
            ruta.getOrigen().getRutasConectadas().remove(ruta);
        }
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public List<Ruta> getRutas() {
        return rutas;
    }
}
