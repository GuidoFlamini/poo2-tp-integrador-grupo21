package tpIntegrador;

import java.util.ArrayList;
import java.util.List;

import tpIntegrador.enums.TipoDeOpinion;

public class GestorDeMuestras {
	//La idea es que esta clase tenga todas las muestras del sistema
	
	List<Muestra> muestras;
	
	public GestorDeMuestras() {
		muestras = new ArrayList<>();
	}
	
	public void agregarMuestraDeTipoYUbicacionYUsuario(TipoDeOpinion tipoDeVinchuca, Ubicacion ubicacionDeLaMuestra, Usuario usuarioCreador) {
		if(tipoDeVinchuca.toString().startsWith("VINCHUCA")) {
			Muestra nuevaMuestra = new Muestra(ubicacionDeLaMuestra, tipoDeVinchuca, usuarioCreador);
			usuarioCreador.enviarMuestra(nuevaMuestra);
			muestras.add(nuevaMuestra);
		} else {
			throw new IllegalArgumentException("No se puede subir una muestra cuya especie no sea vinchuca");
		}
	}
	
	public void agregarMuestra(Muestra nuevaMuestra) {
		nuevaMuestra.getUsuarioCreadorDeMuestra().enviarMuestra(nuevaMuestra);
		muestras.add(nuevaMuestra);
	}
	
	public List<Muestra> getMuestras(){
		return muestras;
	}

	public List<Muestra> muestrasDentroDeLaZona(ZonaDeCobertura unaZona) {
		return unaZona.lasMuestrasQueEstanDentroDeLaZona(this.getMuestras());
	}

	public List<Muestra> lasMuestrasAMenosDe_KmDe(double distanciaEnKm, Muestra unaMuestra) {
		Ubicacion ubicacionDeLaMuestra = unaMuestra.getUbicacion();
		return muestras.stream().filter(muestra -> ubicacionDeLaMuestra.estaAMenosDe_KmDe(distanciaEnKm, muestra.getUbicacion())).toList();
	}

	
}
