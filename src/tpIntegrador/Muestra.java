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
        this.estado = new MuestraNoVerificada();
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
    
    public void agregarOpinion(Opinion opinion) {
    	if(elUsuarioYaOpino(opinion.getNombreDeUsuario())) {
    		throw new IllegalArgumentException("No puedes opinar dos veces sobre la misma muestra");
    	} else {
    		nombresDeUsuario.add(opinion.getNombreDeUsuario());
    		opiniones.add(opinion);
    	}
    }
    
    private boolean elUsuarioYaOpino(String usuarioQueQuiereOpinar) {
    	return nombresDeUsuario.stream().anyMatch(nombreUsuario -> (nombreUsuario==usuarioQueQuiereOpinar));
    }

    public boolean hayOpinionesDeExpertos() {
        return getOpiniones().stream().anyMatch(opinion -> opinion.esOpinionDeExperto());
    }


    public List<TipoDeOpinion> getTiposDeOpinion() {
    return opiniones.stream()
            .map(opinion -> opinion.getTipo())  // Por cada Opinion, llamá a getTipo() y devolvé ese valor.
            .collect(Collectors.toList());      // Esto recolecta todos los valores del stream y los mete en una lista.
}


    public boolean esMuestraVerificada() {
        return estado.esVerificada();
    }

    //public boolean esMuestraVerificada() { //  Para que la muestra quede verificada, deben coincidir dos expertos en su opinión
    //      if (hayOpinionesDeExpertos())   {
    //        return getOpinionesDeExperto().stream()
    //                    .collect(Collectors.groupingBy(
    //                        opinion -> opinion.getTipo(),       // Agrupamos los elementos iguales (clave = el propio elemento) 
    //                        Collectors.counting()))             // Por cada grupo, contamos cuántas veces aparece ese elemento 
    //                    .values().stream()                      // Tomamos solo los valores del mapa (las cantidades de ocurrencias)
    //                    .anyMatch(cantidad -> cantidad >= 2);   // Esto nos dice si hay algún elemento que se repite al menos 2 veces
    //    } else {
    //        return false;
    //    }
    //}

    private List<Opinion> getOpinionesDeExperto() {
        return getOpiniones().stream()
                                .filter(opinion -> opinion.esOpinionDeExperto())
                                .collect(Collectors.toList());
    } 

    

    private void actualizarEstadoDeMuestra() {
		if(getOpinionesDeExperto().size() >= 2) {
            this.estado = new MuestraVerificada();
		} else {
			this.estado = new MuestraNoVerificada();
		}
	}


    //public void verificar() {
    //    this.estado = new MuestraVerificada();
    //}

    //public void desverificar() {
    //    this.estado = new MuestraNoVerificada();
    //}



    public TipoDeOpinion resultadoActual() {  // Con las opiniones que hay, cual es el tipo mas votado. (osea, cual aparece mas veces)
            return getTiposDeOpinion().stream()
                .collect(Collectors.groupingBy(
                    tipo -> tipo,                   // clave: TipoDeOpinion
                    Collectors.counting()           // valor: cantidad de veces que aparece
            ))
                .entrySet().stream()                // stream de pares (TipoDeOpinion, cantidad)
                .max(Map.Entry.comparingByValue())  // el que tenga la cantidad más grande
                .map(Map.Entry::getKey)             // devolver solo el TipoDeOpinion
                .orElse(null);                // si no hay opiniones, devuelve null
    }

	public String getNivelDeValidacion() {
		if(esMuestraVerificada()) {
			return "Verificada";
		} else {
			return "Votada";
		}
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
}

