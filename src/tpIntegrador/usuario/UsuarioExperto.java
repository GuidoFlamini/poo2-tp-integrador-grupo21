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
			Opinion o = new Opinion(usuario, tipoDeOpinion);
			usuario.getOpinionesRealizadas().add(o);
			m.agregarOpinion(o);
		
	}
	
	@Override
	protected void actualizarCategoria(Usuario usuario) {
		if(!cumpleConLasCondiciones(usuario)) {
			usuario.setCategoria(new UsuarioBasico()); 
		}
	}
	
	private boolean cumpleConLasCondiciones(Usuario usuario) {
		return (usuario.cantidadDeMuestrasEnviadasEnElUltimoMes() > 10) && (usuario.cantidadDeOpinionesRealizadasEnElUltimoMes() > 20) || usuario.esEspecialista();
	}
}
