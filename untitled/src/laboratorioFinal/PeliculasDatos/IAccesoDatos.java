package laboratorioFinal.PeliculasDatos;

import laboratorioFinal.Excepciones.AccesoDatosEx;
import laboratorioFinal.Excepciones.EscrituraDatosEx;
import laboratorioFinal.Excepciones.LecturaDatosEx;
import laboratorioFinal.PeliculasDomain.Peliculas;

import java.util.List;

public interface IAccesoDatos {
    boolean existe(String nombreRecurso) throws AccesoDatosEx;
    List<Peliculas>  listar(String nombreRecurso) throws LecturaDatosEx;
    void escribir(Peliculas peliculas, String nombreRecurso,boolean anexar) throws EscrituraDatosEx;
    String buscar(String nombreRecurso, String buscar) throws LecturaDatosEx;

    void crear(String nombreRecurso)throws AccesoDatosEx;
    void borrar(String nombreRecurso) throws AccesoDatosEx;
}
