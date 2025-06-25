package tpIntegrador;

import java.util.ArrayList;
import java.util.List;

public class BusquedaDeMuestra {
	FiltroMuestra filtro;
	List<Muestra> muestrasABuscar;
	
	public BusquedaDeMuestra(List<Muestra> muestrasABuscar, FiltroMuestra filtro){
		this.filtro = filtro;
		this.muestrasABuscar = muestrasABuscar;
	}
	
/*	public void agregarFiltro(FiltroMuestra nuevoFiltro) {
		filtros.add(nuevoFiltro);
	}*/
	
	public List<Muestra> getResultados(){
		return muestrasABuscar.stream().filter(muestra -> filtro.cumple(muestra)).toList();
	}
	
	
	
	
	
}
