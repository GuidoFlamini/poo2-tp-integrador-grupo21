package tpIntegrador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import tpIntegrador.enums.TipoDeOpinion;

public class Usuario {
	private String nombre;
	private boolean esEspecialista;
	private UsuarioState categoria;
	private List<Muestra> muestrasEnviadas = new ArrayList<>();
	private List<Opinion> opinionesRealizadas = new ArrayList<>();
	
	public List<Opinion> getOpinionesRealizadas() {
		return opinionesRealizadas;
	}

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
		this.actualizarCategoria();
		return categoria.getCategoriaPara(this);
	}
	
	public void opinarSobre(TipoDeOpinion tipoDeOpinion, Muestra m) {
			this.actualizarCategoria();
			categoria.opinarSobre(this, tipoDeOpinion, m);
		}
		
	private void actualizarCategoria() {
		if((cantidadDeMuestrasEnviadasEnElUltimoMes() > 10) && (cantidadDeOpinionesRealizadasEnElUltimoMes() > 20) || esEspecialista) {
			categoria = new UsuarioExperto(); 
		} else {
			categoria = new UsuarioBasico();
		}
	}

	private int cantidadDeOpinionesRealizadasEnElUltimoMes() {
		LocalDate hace30Dias = LocalDate.now().minusDays(30);
		return (int) opinionesRealizadas.stream().filter(opinion -> opinion.getFecha().isAfter(hace30Dias)).count();
	}

	private int cantidadDeMuestrasEnviadasEnElUltimoMes() {
		LocalDate hace30Dias = LocalDate.now().minusDays(30);
		return (int) muestrasEnviadas.stream().filter(muestra -> muestra.getFechaDeCreacion().isAfter(hace30Dias)).count();
	}

	public List<Muestra> getMuestrasEnviadas() {
		
		return muestrasEnviadas;
	}

	public void enviarMuestra(Muestra nuevaMuestra) {
		muestrasEnviadas.add(nuevaMuestra);
		this.actualizarCategoria();
	}

	public void setCategoria(UsuarioState nuevaCategoria) {
		this.categoria = nuevaCategoria;
	}

	
}
