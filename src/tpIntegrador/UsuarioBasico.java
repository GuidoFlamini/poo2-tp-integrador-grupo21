package tpIntegrador;

import tpIntegrador.enums.TipoDeOpinion;

public class UsuarioBasico extends UsuarioState {

	@Override
	public String getCategoriaPara(Usuario usuario) {
		return "Usuario Basico";
	}
	
	@Override
	public void opinarSobre(Usuario usuario, TipoDeOpinion tipoDeOpinion, Muestra m) {
		//Si está cerrada u opinó un experto no pasa
	}
}
