package tpIntegrador.usuario;

import tpIntegrador.enums.TipoDeOpinion;
import tpIntegrador.Muestra;

public abstract class UsuarioState {

	public abstract String getCategoriaPara(Usuario usuario);

	protected abstract void opinarSobre(Usuario usuario, TipoDeOpinion tipoDeOpinion, Muestra m);
		
	

}
