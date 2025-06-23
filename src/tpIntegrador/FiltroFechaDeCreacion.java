package tpIntegrador;

import java.time.LocalDate;

public class FiltroFechaDeCreacion extends FiltroMuestra {
	
	LocalDate fecha;
	
	public FiltroFechaDeCreacion(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public boolean cumple(Muestra muestra) {
		return muestra.getFechaDeCreacion().isAfter(fecha);
	}

}
