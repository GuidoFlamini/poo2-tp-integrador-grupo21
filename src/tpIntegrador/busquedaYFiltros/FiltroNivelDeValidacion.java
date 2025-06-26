package tpIntegrador.busquedaYFiltros;

import tpIntegrador.Muestra;

public class FiltroNivelDeValidacion extends FiltroMuestra{
	
	String nivelDeValidacion;
	
	public FiltroNivelDeValidacion(String nivelDeValidacion) {
		this.nivelDeValidacion = nivelDeValidacion;
	}
	
	@Override
	public boolean cumple(Muestra muestra) {
		return nivelDeValidacion == muestra.getNivelDeValidacion();
	}

}
