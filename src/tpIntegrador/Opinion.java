package tpIntegrador;
import tpIntegrador.enums.TipoDeOpinion;
import tpIntegrador.usuario.Usuario;

import java.time.LocalDate;

public class Opinion {
    private Usuario usuario;
    private LocalDate fechaDeCreacion;
    private TipoDeOpinion tipo;
    private boolean esOpinionDeExperto;

    public Opinion(Usuario usuario, TipoDeOpinion tipo) {
        this.usuario = usuario;
        this.tipo = tipo;
        this.fechaDeCreacion = LocalDate.now();
        this.esOpinionDeExperto = (usuario.getCategoria() == "Usuario Experto");
    }

    public boolean esOpinionDeExperto() {         
    	return esOpinionDeExperto;
    }

    public TipoDeOpinion getTipo() { // ( Vinchuca, Chinche Foliada, Phtia-Chinche, Ninguna, Imagen poco clara.)
        return tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public String getNombreDeUsuario() {
    	return usuario.getNombre();
    }

    public LocalDate getFecha() {
        return fechaDeCreacion;
    }
}
