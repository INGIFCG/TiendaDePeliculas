package laboratorioFinal.PeliculasDatos;

import laboratorioFinal.Excepciones.AccesoDatosEx;
import laboratorioFinal.Excepciones.EscrituraDatosEx;
import laboratorioFinal.Excepciones.LecturaDatosEx;
import laboratorioFinal.PeliculasDomain.Peliculas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AccesoDatosImpl implements IAccesoDatos {
// en esta seccion se encuentra toda la logica para accesar al archivo y poder hacer la consulta
    //pertinente o la funcionalidad pertinente segun sea el caso

    @Override
    public boolean existe(String nombreRecurso) throws AccesoDatosEx {
        File archivo = new File(nombreRecurso);
        return archivo.exists();
    }

    @Override
    public List<Peliculas> listar(String nombreRecurso) throws LecturaDatosEx {
        File archivo = new File(nombreRecurso);// SE CREA UNA VARIABLE CON EL NOMBRE DEL ARCHIVO A LISTAR
        List<Peliculas> peliculas = new ArrayList<>();// SE CREA UNA LISTA DE OBJETOS PELICULAS
        try{
            var entrada = new BufferedReader(new FileReader(archivo));//ABRE EL ARCHIVO PARA LEERLO
            String line = null;// SE CREA UNA VARIABLE PARA LEER LAS LINEAS DEL ARCHIVO
            line = entrada.readLine();
            while(line != null){// LEE LINEA POR LINEA HASTA QUE NO HAYA DATOS EN EL ARCHIVO PARA LISTAR
                var pelicula1 = new Peliculas(line);
                peliculas.add(pelicula1);// si encuentra datos lo agrega a la lista
                line = entrada.readLine();//pasa a la siguiente linea
            }
            entrada.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al liestar peliculas: " + ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new LecturaDatosEx("Excepcion al liestar peliculas: " + ex.getMessage());
        }
        return peliculas;
    }

    @Override
    public void escribir(Peliculas peliculas, String nombreRecurso, boolean anexar) throws EscrituraDatosEx {
        var archivo = new File(nombreRecurso);
        try{
            var salida = new PrintWriter(new FileWriter(archivo,anexar));
            salida.println(peliculas.toString());
            salida.close();
            System.out.println("se ha escrito informacion al archivo: "+peliculas);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new EscrituraDatosEx("Exepcion al anexar Pelicula: " + ex.getMessage());
        }
    }
    @Override
    public String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx {
        var archivo = new File(nombreRecurso);
        String resultado = null;
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            String line = null;// linea del archivo
            line = entrada.readLine();// lee la primera linea del archivo
            int indice=1;//indice del archivo
            while(line != null){
                if(buscar!= null && buscar.equalsIgnoreCase(line)){// verifica si no esrta vacio y si el nombre de la pelicula buscado es igual a la primera linea
                    resultado = "pelicula Encontrada : "+line+" encontrada en el indice "+indice;
                    break;
                }
                line = entrada.readLine();
                indice++;
            }
            entrada.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
            throw new LecturaDatosEx("Exepcion al Buscar pelicula: " + ex.getMessage());
        }catch( IOException ex){
            ex.printStackTrace();
            throw new LecturaDatosEx("Exepcion al Buscar pelicula: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public void crear(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        try{
            var salida = new PrintWriter(new FileWriter(nombreRecurso));
            salida.close();
            System.out.println("Se ha creado exitosamente el archivo");
        }catch (IOException ex){
            ex.printStackTrace();
            throw new AccesoDatosEx("Exepcion al crear el archivo: " + ex.getMessage());
        }
    }

    @Override
    public void borrar(String nombreRecurso) throws AccesoDatosEx {
        var archivo = new File(nombreRecurso);
        if(archivo.exists()){
            archivo.delete();
            System.out.println("Se ha borrado el archivo exitosamente");
        }
    }
}
