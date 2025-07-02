package tpIntegrador;

import java.util.Map;
import java.util.stream.Collectors;

import tpIntegrador.enums.TipoDeOpinion;

public class MuestraNoVerificada extends MuestraState {
    
    @Override
    public void agregarOpinion(Muestra muestra, Opinion opinion) {
        if(muestra.elUsuarioYaOpino(opinion.getNombreDeUsuario()) ) {
    		throw new IllegalArgumentException("El usuario dado ya opinó sobre la muestra.");
    	} else {
    		muestra.getNombresDeUsuario().add(opinion.getNombreDeUsuario());
    		muestra.getOpiniones().add(opinion);
        }
    }

    @Override
    public TipoDeOpinion resultadoActual(Muestra muestra) {
      return muestra.getTiposDeOpinion().stream()
                .collect(Collectors.groupingBy(
                    tipo -> tipo,                   // clave: TipoDeOpinion
                    Collectors.counting()           // valor: cantidad de veces que aparece
            ))
                .entrySet().stream()                // stream de pares (TipoDeOpinion, cantidad)
                .max(Map.Entry.comparingByValue())  // el que tenga la cantidad más grande
                .map(Map.Entry::getKey)             // devolver solo el TipoDeOpinion
                .orElse(null); 
    }

	@Override
	protected String getNivelDeValidacion() {
		return "Votada";
	} 
	
	@Override
	public void actualizarEstado(Muestra m) {
		if (m.hayOpinionesDeExpertos()) {
        	m.setEstado(new MuestraParcialmenteVerificada());
		}
	}
}
