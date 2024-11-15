package application;

import com.example.sistemadetrafico.Grafo;
import com.example.sistemadetrafico.Parada;
import com.example.sistemadetrafico.Ruta;

import java.util.List;

public class PruebAlgoritmo {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        Parada p1 = new Parada(1);
        Parada p2 = new Parada(2);
        Parada p3 = new Parada(3);
        Parada p4 = new Parada(4);
        Parada p5 = new Parada(5);
        Parada p6 = new Parada(6);

        grafo.agregarParada(p1);
        grafo.agregarParada(p2);
        grafo.agregarParada(p3);
        grafo.agregarParada(p4);
        grafo.agregarParada(p5);
        grafo.agregarParada(p6);

        grafo.agregarRuta(new Ruta(p1, p2, 10, 0));
        grafo.agregarRuta(new Ruta(p2, p3, 15, 1));
        grafo.agregarRuta(new Ruta(p2, p4, 20, 1));
        grafo.agregarRuta(new Ruta(p1, p5, 25, 0));
        grafo.agregarRuta(new Ruta(p5, p3, 15, 1));
        grafo.agregarRuta(new Ruta(p3, p4, 10, 0));
        grafo.agregarRuta(new Ruta(p4, p5, 20, 1));

        System.out.println("Información del grafo:");
        System.out.println("Paradas: " + grafo.getParadas().size());
        System.out.println("Rutas: " + grafo.getRutas().size());

        System.out.println("\nRutas creadas:");
        for (Ruta ruta : grafo.getRutas()) {
            System.out.println("Parada " + ruta.getOrigen().getNumero() + " -> " + "Parada " + ruta.getDestino().getNumero()
                    + "\nTiempo: " + ruta.getTiempo() + " min, " + "Transbordos: " + ruta.getTransbordos() + "\n");
        }
        //PRUEBA
        System.out.println("Ruta de 1 a 4");
        probarRuta(grafo, p1, p4);

        /*System.out.println("\nRuta de 1 a 3");
        probarRuta(grafo, p1, p3);

        System.out.println("\nRuta de 5 a 4");
        probarRuta(grafo, p5, p4);*/

        /*Parada P4 = grafo.getParadas().get(3);
        grafo.modificarParada(P4, 7);

        System.out.println("\nInformación del grafo después de modificar parada:");
        mostrarParadas(grafo);
        mostrarRutas(grafo);*/

        System.out.println("\nPrueba de eliminación de parada p4:");
        Parada paradaEliminar = p4;
        grafo.eliminarParada(paradaEliminar);

        System.out.println("Información del grafo después de eliminar parada:");
        mostrarParadas(grafo);

        System.out.println("\nRutas actuales después de eliminar parada:");
        mostrarRutas(grafo);
       /* Ruta rutaModificar = null;
        for (Ruta r : grafo.getRutas()) {
            if (r.getOrigen().equals(p1) && r.getDestino().equals(p2)) {
                rutaModificar = r;
                break;
            }
        }
        if (rutaModificar != null) {
            grafo.modificarRuta(rutaModificar, p1, p6, 30, 2);
        }

        System.out.println("\nInformación del grafo después de modificar:");
        mostrarRutas(grafo);*/

        /*System.out.println("\nPrueba de eliminación de ruta p4->p5:");
        Ruta rutaEliminar = null;
        for (Ruta r : grafo.getRutas()) {
            if (r.getOrigen().equals(p4) && r.getDestino().equals(p5)) {
                rutaEliminar = r;
                break;
            }
        }
        if (rutaEliminar != null) {
            grafo.eliminarRuta(rutaEliminar);
        }

        System.out.println("Información del grafo después de eliminar:");
        mostrarRutas(grafo);*/
    }

    private static void mostrarParadas(Grafo grafo) {
        System.out.println("Paradas actuales:");
        for (Parada parada : grafo.getParadas()) {
            System.out.println("Parada " + parada.getNumero());
        }
    }

    private static void mostrarRutas(Grafo grafo) {
        System.out.println("\nRutas actuales:");
        for (Ruta ruta : grafo.getRutas()) {
            System.out.println("Parada " + ruta.getOrigen().getNumero() + " -> " + "Parada " + ruta.getDestino().getNumero() +
                    "\nTiempo: " + ruta.getTiempo() + " min, " + "Transbordos: " + ruta.getTransbordos() + "\n");
        }
    }

    private static void probarRuta(Grafo grafo, Parada inicio, Parada fin) {
        List<Parada> rutaOptima = grafo.Dijkstra(inicio, fin);

        if (!rutaOptima.isEmpty()) {
            System.out.println("Ruta mas corta:");
            for (int i = 0; i < rutaOptima.size(); i++) {
                System.out.print(rutaOptima.get(i).getNumero());
                if (i < rutaOptima.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();

            int tiempoTotal = 0;
            int transbordosTotal = 0;
            for (int i = 0; i < rutaOptima.size() - 1; i++) {
                Parada actual = rutaOptima.get(i);
                Parada siguiente = rutaOptima.get(i + 1);
                for (Ruta ruta : actual.getRutasConectadas()) {
                    if (ruta.getDestino().equals(siguiente)) {
                        tiempoTotal += ruta.getTiempo();
                        transbordosTotal += ruta.getTransbordos();
                        break;
                    }
                }
            }
            System.out.println("Tiempo total: " + tiempoTotal + " minutos");
            System.out.println("Transbordos totales: " + transbordosTotal);
        }
    }

}
