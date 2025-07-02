package tpIntegrador;

import java.util.stream.Collectors;

import tpIntegrador.enums.TipoDeOpinion;


public abstract class MuestraState {

    protected abstract void agregarOpinion(Muestra muestra, Opinion opinion);

    public abstract TipoDeOpinion resultadoActual(Muestra muestra);
    
    public abstract void actualizarEstado(Muestra muestra);
    	

	protected abstract String getNivelDeValidacion();
}
