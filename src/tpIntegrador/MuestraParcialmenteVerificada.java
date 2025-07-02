package tpIntegrador;

import java.util.stream.Collectors;

import tpIntegrador.enums.TipoDeOpinion;

public class MuestraParcialmenteVerificada extends MuestraState { // Debe tener al menos un experto e impedir que un usuario basico opine.
    

    @Override
    public void agregarOpinion(Muestra muestra, Opinion opinion) {
        if(muestra.elUsuarioYaOpino(opinion.getNombreDeUsuario())  || (! opinion.esOpinionDeExperto())) { // ! significa not
    		throw new IllegalArgumentException("El usuario dado ya opinó sobre la muestra o no es una opinion de un experto.");
    	} else {
    		muestra.getNombresDeUsuario().add(opinion.getNombreDeUsuario());
    		muestra.getOpiniones().add(opinion);
        }
    }

    @Override
    public TipoDeOpinion resultadoActual(Muestra muestra) {
        return muestra.getOpinionesDeExperto().stream()
                .map(opinion -> opinion.getTipo())
                .findAny()
                .orElse(null); // este casp no se va a a dar nunca.
    }

	@Override
	protected String getNivelDeValidacion() {
		return "Parcialmente Verificada";
	}
	
	@Override
	public void actualizarEstado(Muestra m) {
		if(hayDosOMasExpertosConLaMismaOpinion(m)) {
			m.setEstado(new MuestraVerificada());
			m.notificarVerificada();
		}
		
	}
	
	private boolean hayDosOMasExpertosConLaMismaOpinion(Muestra m) {
		return m.getOpinionesDeExperto().stream()
        .collect(Collectors.groupingBy(
                opinion -> opinion.getTipo(),       // Agrupamos los elementos iguales (clave = el propio elemento) 
                Collectors.counting()))             // Por cada grupo, contamos cuántas veces aparece ese elemento 
            .values().stream()                      // Tomamos solo los valores del mapa (las cantidades de ocurrencias)
           .anyMatch(cantidad -> cantidad >= 2);
	}
}
