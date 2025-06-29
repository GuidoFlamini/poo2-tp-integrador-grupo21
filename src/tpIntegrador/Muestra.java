package tpIntegrador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import tpIntegrador.enums.TipoDeOpinion;
import tpIntegrador.usuario.Usuario;

public class Muestra implements SubjectMuestra {
    private String foto;
    private Ubicacion ubicacion;
    private LocalDate fechaDeCreacion;
    private List<String> nombresDeUsuario;
    private List<Opinion> opiniones;
    private Usuario usuarioCreadorDeMuestra;
    private List<ObserverDeZonaDeCobertura> observadores;
    private MuestraState estado;

    

public Muestra(Ubicacion ubicacion, TipoDeOpinion tipo, Usuario usuarioCreadorDeMuestra) {
    	this.foto = "fotoDeLaMuestra";
        this.ubicacion = ubicacion;
        this.fechaDeCreacion = LocalDate.now();
        this.nombresDeUsuario = new ArrayList<>();
        nombresDeUsuario.add(usuarioCreadorDeMuestra.getNombre());
        Opinion opinionInicial = new Opinion(usuarioCreadorDeMuestra, tipo);
        this.opiniones = new ArrayList<>();
        opiniones.add(opinionInicial);
        this.usuarioCreadorDeMuestra = usuarioCreadorDeMuestra;
        this.observadores = new ArrayList<>();
        if(usuarioCreadorDeMuestra.esEspecialista()) {
			estado = new MuestraParcialmenteVerificada();
		} else {
			estado = new MuestraNoVerificada();
		}
    }

    public LocalDate getFechaDeCreacion() {
		return fechaDeCreacion;
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
    
    public String getNombreDeUsuarioCreador() {
    	return usuarioCreadorDeMuestra.getNombre();
    }

    public MuestraState getEstadoDeMuestra() {
        return estado;
    }

    public boolean esMuestraVerificada() {
        return estado.esVerificada();
    }

    public boolean esParcialmenteVerificada() {
        return estado.esParcialmenteVerificada();
    }

    public void agregarOpinion(Opinion opinion) {
        estado.agregarOpinion(this, opinion);
        actualizarEstado();
    }
        
    private void actualizarEstado() {
        long cantidadExpertos = opiniones.stream()
                                         .filter(opinion -> opinion.esOpinionDeExperto())
                                         .count();

        if (cantidadExpertos >= 2) {
            estado = new MuestraVerificada();
        } else if (cantidadExpertos == 1) {
            estado = new MuestraParcialmenteVerificada();
        } else {
            estado = new MuestraNoVerificada();
        }
    }


    
    public boolean elUsuarioYaOpino(String usuarioQueQuiereOpinar) {
    	return nombresDeUsuario.stream().anyMatch(nombreUsuario -> (nombreUsuario==usuarioQueQuiereOpinar));
    }

    public boolean hayOpinionesDeExpertos() {
        return esMuestraVerificada() || esParcialmenteVerificada();
    }


    public List<TipoDeOpinion> getTiposDeOpinion() {
    return opiniones.stream()
            .map(opinion -> opinion.getTipo())  // Por cada Opinion, llamá a getTipo() y devolvé ese valor.
            .collect(Collectors.toList());      // Esto recolecta todos los valores del stream y los mete en una lista.
}


    public String getNivelDeValidacion() {
		if(esMuestraVerificada()) {
			return "Verificada";
		} else {
			return "Votada";
		}
	}


    public TipoDeOpinion resultadoActual() {  // Con las opiniones que hay, cual es el tipo mas votado. (osea, cual aparece mas veces)
        return estado.resultadoActual(this);         
    }

    public List<Opinion> getOpinionesDeExperto() {
    return opiniones.stream()
                    .filter(opinion -> opinion.esOpinionDeExperto())
                    .collect(Collectors.toList());
    }



	public Opinion laOpinionMasReciente() {
		//Precondición: opiniones no puede ser vacía
		Opinion masReciente = opiniones.get(0);

		for (Opinion op : opiniones) {
		    if (op.getFecha().isAfter(masReciente.getFecha())) {
		        masReciente = op;
		    }
		}
		
		return masReciente;
	}

	@Override
	public void notificarVerificada() {
		for (ObserverDeZonaDeCobertura observador : observadores) {
            observador.muestraFueVerificada(this);
    	}
		
	}

	@Override
	public void agregarObserver(ObserverDeZonaDeCobertura observer) {
		observadores.add(observer);
	}

	@Override
	public void quitarObserver(ObserverDeZonaDeCobertura observer) {
		observadores.remove(observer);
	} 
}

