package laboratorioFinal;

import laboratorioFinal.PeliculasNegocio.*;

import java.util.Scanner;

public class CPJLaboratorioFinal {
    public static void main(String[] args) {
        var opcion = 1;
        while (opcion != 0){
            try{
                var scaner = new Scanner(System.in);
                ICatalogoPeliculas catalogo = new CatalogoPeliculasImpl();
                System.out.println("Eliga una opcion: \n" +
                        "1. Iniciar cataloco de peliculas\n" +
                        "2. Agregar Pelicula\n" +
                        "3. Listar Peliculas\n" +
                        "4. Buscar Peliculas\n" +
                        "0. Salir");
                opcion = Integer.parseInt(scaner.nextLine());
                switch (opcion){
                    case 1:
                        catalogo.iniciarCatalogoDePeliculas();
                        break;
                    case 2:
                        System.out.println("Por favor ingresa el nombre de la pelicula:");
                        var nombrePelicula = scaner.nextLine();
                        catalogo.agregarPelicula(nombrePelicula);
                        break;
                    case 3:
                        catalogo.listarPeliculas();
                        break;
                    case 4:
                        System.out.println("Por favor ingresa la pelicula a consultar: " );
                        var buscar = scaner.nextLine();
                        catalogo.buscarPelicula(buscar);
                        break;
                    case 0 :
                        System.out.println("Gracias por utilizar el programa, adios");
                        break;
                    default:
                        System.out.println("Opcion no permitida");
                }
            }catch (NumberFormatException e){
                System.out.println("######################");
                System.out.println("# ACCION NO PERMITIDA #" );
                System.out.println("######################");

        }

    }
    }
}
