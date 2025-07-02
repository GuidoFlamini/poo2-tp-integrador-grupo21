package tpIntegrador.usuario;

import tpIntegrador.Muestra;
import tpIntegrador.enums.TipoDeOpinion;

public abstract class UsuarioState {

	public abstract String getCategoriaPara(Usuario usuario);

	protected abstract void opinarSobre(Usuario usuario, TipoDeOpinion tipoDeOpinion, Muestra m);

	protected abstract void actualizarCategoria(Usuario usuario);
		
	

}
