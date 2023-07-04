package presentacion;

import dominio.Pelicula;
import servicio.IServicioPeliculas;
import servicio.ServicioPeliculasArchivo;
import servicio.ServicioPeliculasLista;

import java.util.Scanner;

public class CatalogoPeliculasApp
{
    public static void main(String[] args)
    {
        var salir = false;
        var consola = new Scanner(System.in);
        // Agregamos la implementacion del servicio
        //IServicioPeliculas iServicioPeliculas = new ServicioPeliculasLista();
        IServicioPeliculas iServicioPeliculas = new ServicioPeliculasArchivo();
        while (!salir){
            try
            {
                mostrarMenu();
                salir = ejecutarOpciones(consola, iServicioPeliculas);

            } // Fin try
            catch (Exception e)
            {
                System.out.println("Ocurrio un problema: " + e.getMessage());
            }// Catch
            System.out.println();
        } // Fin while


    }

    private static boolean ejecutarOpciones(Scanner consola, IServicioPeliculas servicioPeliculas)
    {
        var opciones = Integer.parseInt(consola.nextLine());
        var salir = false;
        switch (opciones){
            case 1 ->{
                System.out.print("Introduce el nombre de la pelicula: ");
                var  nombrePelicula  = consola.nextLine();
                servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));
            }
            case 2 -> servicioPeliculas.listarPeliculas();
            case 3 -> {
                System.out.print("Introduce la pelicula a buscar: ");
                var buscar = consola.nextLine();
                servicioPeliculas.buscarPelicula(new Pelicula(buscar));
            }
            case 4 -> {
                System.out.println("Hasta pronto....");
                salir = true;
            }
            default -> System.out.println("Opcion no reconocida!");
        } // fin switch

        return salir;
    }

    private static void mostrarMenu()
    {
        System.out.println("""
                **** Catalogo de peliculas ***
                1. Agregar pelicula
                2. Listar pelicula
                3. Buscar pelicula
                4. Salir
                """);
    }
}
