package tpIntegrador.usuario;

import tpIntegrador.Muestra;
import tpIntegrador.Opinion;
import tpIntegrador.enums.TipoDeOpinion;

public class UsuarioBasico extends UsuarioState {

	@Override
	public String getCategoriaPara(Usuario usuario) {
		return "Usuario Basico";
	}
	
	@Override
	public void opinarSobre(Usuario usuario, TipoDeOpinion tipoDeOpinion, Muestra m) {
		if(m.hayOpinionesDeExpertos()) {
			throw new IllegalArgumentException("No se pudo realizar la votación dado que la muestra está verificada o sólo permite expertos");
		} else {
			Opinion o = new Opinion(usuario, tipoDeOpinion);
			usuario.getOpinionesRealizadas().add(o);
			m.agregarOpinion(o);
		}
	}
	
}
