package tpIntegrador;

import tpIntegrador.enums.TipoDeOpinion;

public class MuestraParcialmenteVerificada extends MuestraState { // Debe tener al menos un experto e impedir que un usuario basico opine.
    
    @Override
    public boolean esParcialmenteVerificada() {
        return true;
    }

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
                .orElse(null); // este caso no se va a a dar nunca.
    }
}
