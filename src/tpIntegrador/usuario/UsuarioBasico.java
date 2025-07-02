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
			Opinion o = new Opinion(usuario, tipoDeOpinion);
			usuario.getOpinionesRealizadas().add(o);
			m.agregarOpinion(o);
		
	}

	@Override
	protected void actualizarCategoria(Usuario usuario) {
		if((usuario.cantidadDeMuestrasEnviadasEnElUltimoMes() > 10) && (usuario.cantidadDeOpinionesRealizadasEnElUltimoMes() > 20)) {
			usuario.setCategoria(new UsuarioExperto()); 
		}
	}
	
}
