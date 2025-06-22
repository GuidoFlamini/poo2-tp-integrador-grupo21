package tpIntegrador;

import java.util.List;
import java.util.ArrayList;

public class ZonaDeCobertura {
	
	String nombre;
	Ubicacion epicentro;
	double radio; //en kilometros

	public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radio) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
	}

	public String getNombre() {
		return nombre;
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	public double getRadio() {
		return radio;
	}

	public boolean seSolapaCon(ZonaDeCobertura otraZonaDeCobertura) {
		double distanciaEntreEpicentros = epicentro.distanciaEnKmA(otraZonaDeCobertura.getEpicentro());
		return ((radio > distanciaEntreEpicentros) || (otraZonaDeCobertura.getRadio() > distanciaEntreEpicentros));
	}
	
	public List<Muestra> lasMuestrasQueEstanDentroDeLaZona(List<Muestra> listaDeMuestras){
		List<Muestra> muestrasDentroDeLaZona = new ArrayList<>();
		for (Muestra muestra : listaDeMuestras) {
			if(laMuestraEstaDentroDeLaZona(muestra)) {
				muestrasDentroDeLaZona.add(muestra);
			}
		}
		return muestrasDentroDeLaZona;
	}

	private boolean laMuestraEstaDentroDeLaZona(Muestra muestra) {
		return (epicentro.distanciaEnKmA(muestra.getUbicacion()) < radio);
	}

	public List<ZonaDeCobertura> zonasConLasQueSeSolapa(List<ZonaDeCobertura> listaDeZonas) {
		List<ZonaDeCobertura> zonasConLasQueSeSolapa = new ArrayList<>();
		for (ZonaDeCobertura zona : listaDeZonas) {
			if(this.seSolapaCon(zona) && (nombre != zona.getNombre())) {
				zonasConLasQueSeSolapa.add(zona);
			}
		}
		return zonasConLasQueSeSolapa;
	}

}
