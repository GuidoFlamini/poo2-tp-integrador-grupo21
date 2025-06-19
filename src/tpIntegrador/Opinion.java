package tpIntegrador;
import tpIntegrador.enums.TipoDeOpinion;
import tpIntegrador.enums.EspecieDeVinchuca;
import java.time.LocalDate;

public class Opinion {
    private Usuario usuario;
    private LocalDate fechaDeCreacion;
    private TipoDeOpinion tipoDeOpinion;          // (Vinchuca, Chinche Foliada, Phtia-Chinche, Ninguna, Imagen poco clara.)
    private boolean esOpinionDeExperto;
    private EspecieDeVinchuca especie;   // (VINCHUCA_INFESTANS , VINCHUCAS_SORDIDA, VINCHUCAS_GUASAYANA, NINGUNA)

    public Opinion(Usuario usuario, TipoDeOpinion tipoDeOpinion, EspecieDeVinchuca especie) {
        this.usuario = usuario;
        this.tipoDeOpinion = tipoDeOpinion;
        this.fechaDeCreacion = LocalDate.now();
        this.esOpinionDeExperto = usuario.getCategoria() == "Usuario Experto";
        this.especie = especie;
    }

    public boolean esOpinionDeExperto() {         
        return esOpinionDeExperto;
    }

    public TipoDeOpinion getTipo() { 
        return tipoDeOpinion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDate getFecha() {
        return fechaDeCreacion;
    }

    public EspecieDeVinchuca tipoDeVinchuca() {
        if(esVinchuvaConEspecie()) {
            return especie;
        } else {
            throw new IllegalArgumentException("No es Vinchuca");
        }
    }

    public boolean esVinchuvaConEspecie() {
        return tipoDeOpinion == TipoDeOpinion.VINCHUCA ;
    }
    

}
