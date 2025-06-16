package tpIntegrador;

import java.util.ArrayList;
import java.util.List;

import tpIntegrador.enums.TipoDeOpinion;

public class Usuario {
	private String nombre;
	private boolean esEspecialista;
	private UsuarioState categoria;
	private List<Muestra> muestrasEnviadas = new ArrayList<>();
	private List<Opinion> opinionesRealizadas = new ArrayList<>();
	
	public Usuario(String nombre, boolean esEspecialista) {
		this.nombre = nombre;
		this.esEspecialista = esEspecialista;
		if(esEspecialista) {
			categoria = new UsuarioExperto();
		} else {
			categoria = new UsuarioBasico();
		}
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public boolean esEspecialista() {
		return esEspecialista;
	}
	
	public String getCategoria() {
		//Antes del return debería ejecutarse el algoritmo para determinar la categoria
		return categoria.getCategoriaPara(this);
	}
	
	public void opinarSobre(TipoDeOpinion tipoDeOpinion, Muestra m) {
		categoria.opinarSobre(this, tipoDeOpinion, m);
		Opinion o = new Opinion(this, tipoDeOpinion);
		m.agregarVotacion(o);
		opinionesRealizadas.add(o);
	}
}
