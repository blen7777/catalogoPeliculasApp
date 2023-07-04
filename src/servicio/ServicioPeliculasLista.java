package servicio;

import dominio.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class ServicioPeliculasLista implements IServicioPeliculas
{
    private  final List<Pelicula> peliculas;

    public ServicioPeliculasLista() {
        this.peliculas = new ArrayList<>();
    }

    @Override
    public void listarPeliculas()
    {
        System.out.println("Lista de peliculas....");
        peliculas.forEach(System.out::println);
    }

    @Override
    public void agregarPelicula(Pelicula pelicula)
    {
        peliculas.add(pelicula);
        System.out.println("La pelicula fue agregado exitosamente: " + pelicula);
    }

    @Override
    public void buscarPelicula(Pelicula pelicula)
    {
        // Regresa el indice de la pelicula encontrada en la lista
        var indice = peliculas.indexOf(pelicula);
        if (indice == -1){
            System.out.println("No se encontro la pelicula: " + pelicula);
        }
        else {
            System.out.println("Pelicula encontrada en el indice: " + indice);
        }

    }

    public static void main(String[] args) {
        // Creamos algunos objetos de tipo peliculas
        var pelicula1 = new Pelicula("Batman");
        var pelicula2 = new Pelicula("Spiderman");
        var pelicula3 = new Pelicula("Superman");
        var pelicula4 = new Pelicula("Iroman");
        var pelicula5 = new Pelicula("Acuaman");

        // Creamos el servicio (patron de diseno de service)
        IServicioPeliculas servicioPeliculas = new ServicioPeliculasLista();

        // Agregamos las peliculas a la lista
        servicioPeliculas.agregarPelicula(pelicula1);
        servicioPeliculas.agregarPelicula(pelicula2);
        servicioPeliculas.agregarPelicula(pelicula3);
        servicioPeliculas.agregarPelicula(pelicula4);
        servicioPeliculas.agregarPelicula(pelicula5);

        // Listamos las peliculas
        servicioPeliculas.listarPeliculas();

        // Buscamos una pelicula, para eso se debe implementar los metodos de equals y hashCode
        servicioPeliculas.buscarPelicula(pelicula2);


    }
}
