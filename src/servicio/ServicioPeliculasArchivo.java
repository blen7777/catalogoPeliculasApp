package servicio;

import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivo implements IServicioPeliculas
{

    private final String NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivo()
    {
        var archivo = new File(NOMBRE_ARCHIVO);
        try
        {
            if(archivo.exists()){
                System.out.println("El archivo ya existe!");
            }else{
                // Si no existe se crear el archivo
                var salida = new PrintWriter(new FileWriter(archivo));
                salida.close();
                System.out.println("Se ha creado el archivo");
            }
        }
        catch (Exception e){
            System.out.println("Ocurrio un erro creando el archivo: " + e.getMessage());
        }
    }
    @Override
    public void listarPeliculas()
    {
        // Volvemos abrir el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            System.out.println("Listado de peliculas");
            //Abrimos el archivo solo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            // Leemos linea a linea el arcivo
            String linea;
            linea  = entrada.readLine();
            // Leemos toda las lineas disponibles
            while (linea !=null){
                var pelicula =  new Pelicula(linea);
                System.out.println(pelicula);
                // Antes de terminar el ciclo volvemos a leer la siguiente linea
                linea = entrada.readLine();
            }
            // Cerrar el recurso del archivo
            entrada.close();

        }catch (Exception e){
            System.out.println("Ocurrio un error al abrir el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula)
    {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            // Agregamos la pelicula (toString)
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agrego al archivo el nombre de la pelicula");

        }catch (Exception e){
            System.out.println("Error al tratar de agregar pelicula: "+e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula)
    {
        var archivo = new File(NOMBRE_ARCHIVO);
        try
        {
            // Abrimos el archivo para lectura linea a linea
            var entrada = new BufferedReader(new FileReader(archivo));
            var lineaText =  entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var peliculaBUsca = pelicula.getNombre();
            while (lineaText != null)
            {
                // Buscamos sin importar mayuscula y minusculas
                if(peliculaBUsca != null && peliculaBUsca.equalsIgnoreCase(lineaText))
                {
                    encontrada = true;
                    break;
                }
                // Leemos la siguiente linea antes de la siguiente interacccion
                lineaText = entrada.readLine();
                indice++;
            } // Fin while

            // Imprimimos resultados de la busqueda
            System.out.println("Pelicula: " + lineaText + "encontrada en la linea - " + indice);
            entrada.close();

        }catch (Exception e){
            System.out.println("Error al tratar de abrir el archivo: "+e.getMessage());
        }
    }
}
