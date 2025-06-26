package tpIntegrador.usuario;

import tpIntegrador.Muestra;
import tpIntegrador.Opinion;
import tpIntegrador.enums.TipoDeOpinion;

public class UsuarioExperto extends UsuarioState {

	@Override
	public String getCategoriaPara(Usuario usuario) {
		return "Usuario Experto";		
	}
	
	@Override
	public void opinarSobre(Usuario usuario, TipoDeOpinion tipoDeOpinion, Muestra m) {
		if(m.esMuestraVerificada()) {
			throw new IllegalArgumentException("No se pudo realizar la votación dado que la muestra está verificada");
		} else {
			Opinion o = new Opinion(usuario, tipoDeOpinion);
			usuario.getOpinionesRealizadas().add(o);
			m.agregarOpinion(o);
		}
	}

}
