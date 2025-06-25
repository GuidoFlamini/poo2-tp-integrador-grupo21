package tpIntegrador;

import tpIntegrador.enums.TipoDeOpinion;

public class FiltroTipoDeInsecto extends FiltroMuestra {
	
	TipoDeOpinion tipoDeInsecto;
	
	public FiltroTipoDeInsecto(TipoDeOpinion tipoDeInsecto) {
		this.tipoDeInsecto = tipoDeInsecto;
	}
	
	@Override
	public boolean cumple(Muestra muestra) {
		return tipoDeInsecto == (muestra.resultadoActual());
	}

}
