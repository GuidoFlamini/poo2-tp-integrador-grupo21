package tpIntegrador;

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
}
