package tpIntegrador.busquedaYFiltros;

import java.time.LocalDate;

import tpIntegrador.Muestra;

public class FiltroUltimaFechaDeVotacion extends FiltroMuestra {
	
	LocalDate fecha;
	
	public FiltroUltimaFechaDeVotacion(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	@Override
	public boolean cumple(Muestra muestra) {
		LocalDate fechaDeUltimaOpinion = muestra.laOpinionMasReciente().getFecha();
		return fechaDeUltimaOpinion.isAfter(fecha);
	}

	

}
