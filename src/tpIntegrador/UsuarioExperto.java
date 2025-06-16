package tpIntegrador;

import tpIntegrador.enums.TipoDeOpinion;

public class UsuarioExperto extends UsuarioState {

	@Override
	public String getCategoriaPara(Usuario usuario) {
		return "Usuario Experto";		
	}
	
	@Override
	public void opinarSobre(Usuario usuario, TipoDeOpinion tipoDeOpinion, Muestra m) {
		//A menos de que esté cerrada pasa siempre
	}

}
