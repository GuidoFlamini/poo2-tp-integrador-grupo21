package tpIntegrador.busquedaYFiltros;

import java.util.ArrayList;
import java.util.List;

import tpIntegrador.Muestra;

public class FiltroAND extends FiltroMuestra {
	
	List<FiltroMuestra> filtros;
	
	public FiltroAND() {
		filtros = new ArrayList<>();
	}
	
	public List<FiltroMuestra> getFiltros() {
		return filtros;
	}

	@Override
	public boolean cumple(Muestra muestra) {
		return filtros.stream().allMatch(filtro -> filtro.cumple(muestra));
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
