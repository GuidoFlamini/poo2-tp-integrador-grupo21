package tpIntegrador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tpIntegrador.enums.TipoDeOpinion;

public class Muestra {
    private String foto;
    private Ubicacion ubicacion;
    private LocalDate fechaDeCreacion;
    private List<String> nombresDeUsuario;
    private List<Opinion> opiniones;
    private Usuario usuarioCreadorDeMuestra;

    public Muestra(String foto, Ubicacion ubicacion, Usuario usuarioCreadorDeMuestra) {
        this.foto = foto;
        this.ubicacion = ubicacion;
        this.fechaDeCreacion = LocalDate.now();
        this.nombresDeUsuario = new ArrayList<>();
        this.opiniones = new ArrayList<>();
        this.usuarioCreadorDeMuestra = usuarioCreadorDeMuestra;
    }

    public String getFoto() {
        return foto;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public List<String> getNombresDeUsuario() {
        return nombresDeUsuario;
    }

    public List<Opinion> getOpiniones() {
        return opiniones;
    }

    public Usuario getUsuarioCreadorDeMuestra() {
        return usuarioCreadorDeMuestra;      
    }



    public void agregarOpinion(Opinion opinion) {  // ¿Por qué no de tipo List<Opinion>?
        getOpiniones().add(opinion);
    } 

    private boolean hayOpinionesDeExpertos() {
        return getOpiniones().stream().anyMatch(Opinion::esOpinionDeExperto);
    }

        
    //public void getTipoDeOpinion(List<Opinion> getOpiniones) {
    //    getOpiniones.stream().forEach(opinion -> opinion.getTipo());
    //}
    // Recorre la lista de opiniones usando un stream.
    // Para cada opinion, llama a getTipo().  
    // Pero no hace nada con el resultado de getTipo(). Simplemente lo obtiene y lo descarta.


    public List<TipoDeOpinion> getTiposDeOpinion() {
    return opiniones.stream()
            .map(opinion -> opinion.getTipo())  // Por cada Opinion, llamá a getTipo() y devolvé ese valor.
            .collect(Collectors.toList());      // Esto recolecta todos los valores del stream y los mete en una lista.
}



    private List<Opinion> getOpinionesDeExperto() {
        return getOpiniones().stream()
                                .filter(opinion -> opinion.esOpinionDeExperto())
                                .collect(Collectors.toList());
    }   



    public boolean esMuestraVerificada() { //  Para que la muestra quede verificada, deben coincidir dos expertos en su opinión
          if (hayOpinionesDeExpertos())   {
            return getOpinionesDeExperto().stream()
                        .collect(Collectors.groupingBy(
                            opinion -> opinion.getTipo(),       // Agrupamos los elementos iguales (clave = el propio elemento) 
                            Collectors.counting()))             // Por cada grupo, contamos cuántas veces aparece ese elemento 
                        .values().stream()                      // Tomamos solo los valores del mapa (las cantidades de ocurrencias)
                        .anyMatch(cantidad -> cantidad >= 2);   // Esto nos dice si hay algún elemento que se repite al menos 2 veces
        } else {
            return false;
        }
    }


    public void resultadoActual() {  // Con las opiniones que hay, cual es el tipo mas votado. (osea, cual aparece mas veces)
            getTiposDeOpinion().stream()
                .collect(Collectors.groupingBy(
                    tipo -> tipo,                   // clave: TipoDeOpinion
                    Collectors.counting()           // valor: cantidad de veces que aparece
            ))

            //revisar a partir de acá.

                .entrySet().stream()                // stream de pares (TipoDeOpinion, cantidad)
                .max(Map.Entry.comparingByValue())  // el que tenga la cantidad más grande
                .map(Map.Entry::getKey)             // devolver solo el TipoDeOpinion
                .orElse(null);                // si no hay opiniones, devuelve null
    } 
}

