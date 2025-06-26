package tpIntegrador.busquedaYFiltros;

import java.util.ArrayList;
import java.util.List;

import tpIntegrador.Muestra;

public class FiltroOR extends FiltroMuestra {
	
	List<FiltroMuestra> filtros;
	
	public List<FiltroMuestra> getFiltros() {
		return filtros;
	}
	
	public FiltroOR() {
		filtros = new ArrayList<>();
	}
	
	@Override
	public boolean cumple(Muestra muestra) {
		return filtros.stream().anyMatch(filtro -> filtro.cumple(muestra));
	}
	
	public void agregarFiltro(FiltroMuestra nuevoFiltro) {
		filtros.add(nuevoFiltro);
	}
	
	public void eliminarFiltro(FiltroMuestra filtro) {
		filtros.remove(filtro);
	}
	
	public FiltroMuestra getFiltroEnPosicion(int posicion) {
		return filtros.get(posicion);
	}

}
