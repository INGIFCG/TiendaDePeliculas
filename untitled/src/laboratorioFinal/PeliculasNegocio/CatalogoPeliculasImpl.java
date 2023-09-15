package laboratorioFinal.PeliculasNegocio;

import laboratorioFinal.Excepciones.AccesoDatosEx;
import laboratorioFinal.Excepciones.LecturaDatosEx;
import laboratorioFinal.PeliculasDatos.AccesoDatosImpl;
import laboratorioFinal.PeliculasDatos.IAccesoDatos;
import laboratorioFinal.PeliculasDomain.Peliculas;

public class CatalogoPeliculasImpl implements ICatalogoPeliculas {

    private final IAccesoDatos datos;
    public CatalogoPeliculasImpl() {
        this.datos = new AccesoDatosImpl();
    }
    @Override
    public void agregarPelicula(String nombrePelicula) {
        Peliculas pelicula = new Peliculas(nombrePelicula);
        boolean anexar = false;
        try{
            anexar = datos.existe(NOMBRE_RECURSO);
            datos.escribir(pelicula,NOMBRE_RECURSO,anexar);
        }catch (AccesoDatosEx ex){
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);// se envia el error a la consola
        }
    }
    @Override
    public void listarPeliculas() {
        try{
            var peliculas = this.datos.listar(NOMBRE_RECURSO);
            for (var pelicula:peliculas) {
                System.out.println("pelicula = " + pelicula);
            }
        }catch(LecturaDatosEx ex){
            System.out.println("Error al Verificar peliculas");
            ex.printStackTrace();
        }
    }

    @Override
    public void buscarPelicula(String buscar) {
        String resultado = null;
        try {
            resultado = this.datos.buscar(NOMBRE_RECURSO, buscar);
        } catch (AccesoDatosEx ex) {
            System.out.println("No se encontraron coincidencias a la pelicula tipeada");
            ex.printStackTrace(System.out);
        }
        System.out.println("resultado= " + resultado);
    }
    @Override
    public void iniciarCatalogoDePeliculas() {
        try{
            if(this.datos.existe(NOMBRE_RECURSO)){
                datos.borrar(NOMBRE_RECURSO);
                datos.crear(NOMBRE_RECURSO);
            }else {
                datos.crear(NOMBRE_RECURSO);
            }
        }catch (AccesoDatosEx ex){
            System.out.println("Error en catalogo de peliculas" );
            ex.printStackTrace(System.out);
        }
    }
}
