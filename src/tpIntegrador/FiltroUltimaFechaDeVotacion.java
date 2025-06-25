package tpIntegrador;

import java.time.LocalDate;
import java.util.List;

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
