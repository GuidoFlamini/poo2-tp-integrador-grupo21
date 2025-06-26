package tpIntegrador;

import java.util.ArrayList;
import java.util.List;

import tpIntegrador.enums.TipoDeOpinion;

public class GestorDeMuestras {
	//La idea es que esta clase tenga todas las muestras del sistema
	
	List<Muestra> muestras;
	GestorDeZonas zonasDeCobertura;
	
	public GestorDeMuestras(GestorDeZonas zonasDeCobertura) {
		muestras = new ArrayList<>();
		this.zonasDeCobertura = zonasDeCobertura;
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
		if(nuevaMuestra.laOpinionMasReciente().toString().startsWith("VINCHUCA")) {
			nuevaMuestra.getUsuarioCreadorDeMuestra().enviarMuestra(nuevaMuestra);
			muestras.add(nuevaMuestra);
			List <ZonaDeCobertura>listaDeZonas = zonasDeCobertura.getZonas();
			for(ZonaDeCobertura zona : listaDeZonas) {
				zona.agregarMuestra(); // sólo va agregar la muestra si ésta se encuentra dentro de la zona
			}
		}else {
			throw new IllegalArgumentException("No se puede subir una muestra cuya especie no sea vinchuca");
		}
		
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

	//PRUEBA
	
	public List<Muestra> realizarBusquedaDeMuestrasConFiltro(FiltroMuestra filtro) {
		BusquedaDeMuestra nuevaBusqueda = new BusquedaDeMuestra(muestras, filtro);
		return nuevaBusqueda.getResultados();
	}
}
