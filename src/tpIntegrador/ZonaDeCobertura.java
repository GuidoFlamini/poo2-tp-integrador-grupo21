package tpIntegrador;

import java.util.List;
import java.util.ArrayList;

public class ZonaDeCobertura {
	
	String nombre;
	Ubicacion epicentro;
	double radio; //en kilometros
	List<Observer> observadores;
	List<Muestra> muestras;

	public ZonaDeCobertura(String nombre, Ubicacion epicentro, double radio) {
		this.nombre = nombre;
		this.epicentro = epicentro;
		this.radio = radio;
		this.observadores = new ArrayList<>();
		this.muestras = new ArrayList<>();
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

	public List<Observer> getObservadores() {
		return observadores;
	}

	public List<Muestra> getMuestras() {
		return muestras;
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
	
	public void agregarObservador(Observer observador) {
	   	observadores.add(observador);
    }

	public void agregarMuestras(Muestra muestra) {
		if (laMuestraEstaDentroDeLaZona(muestra) && muestra.esMuestraVerificada()) {
			muestras.add(muestra);
    	    notificarMuestraVerificada(muestra);
        } else if (laMuestraEstaDentroDeLaZona(muestra)){
			muestras.add(muestra);
			notificarNuevaMuestra(muestra);
		}
    }

    
    private void notificarNuevaMuestra(Muestra muestra) { // notificar a los observadores sobre nueva muestra
        for (Observer observador : observadores) {
            observador.nuevaMuestra(muestra);
        }
    }

  
    private void notificarMuestraVerificada(Muestra muestra) {  // notificar a los observadores sobre muestra verificada
        for (Observer observador : observadores) {
            observador.muestraVerificada(muestra);
        }
    }
}

