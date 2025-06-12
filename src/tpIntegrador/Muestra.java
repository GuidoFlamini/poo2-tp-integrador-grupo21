package tpIntegrador;
import java.time.LocalDate;
import java.util.List;

public class Muestra {
    private String foto;
    private Ubicacion ubicacion;
    private LocalDate fechaDeCreacion;
    private List<String> nombresDeUsuario;
    private List<Opinion> opiniones;

    public Muestra(String foto, Ubicacion ubicacion, List<String> nombresDeUsuario, List<Opinion> opiniones) {
        this.foto = foto;
        this.ubicacion = ubicacion;
        this.fechaDeCreacion = LocalDate.now();
        this.nombresDeUsuario = nombresDeUsuario;
        this.opiniones = opiniones;
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
}

