package tpIntegrador;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion {

	private double latitud;
	private double longitud;
	
	public Ubicacion(double latitud, double longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }
	
	public double getLatitud() {
		return latitud;
	}
	
	public double getLongitud() {
		return longitud;
	}

	public Double distanciaEnKmA(Ubicacion otraUbicacion) {
		
		double lat1 = Math.toRadians(latitud);
		double lat2 = Math.toRadians(otraUbicacion.getLatitud());
		double long1 = Math.toRadians(longitud);
		double long2 = Math.toRadians(otraUbicacion.getLongitud());

		double dlat = lat1 - lat2;
		double dLong = long1 - long2;

		double a = Math.pow(Math.sin(dlat / 2), 2)
		         + Math.cos(lat2) * Math.cos(lat1) * Math.pow(Math.sin(dLong / 2), 2);

		double radioTierra = 6371; // en km
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distancia = radioTierra * c;

		return distancia;

	}
	
	public List<Ubicacion> lasQueEstanAMenosDe_Km(List<Ubicacion> ubicaciones, double distanciaLimite){
		List<Ubicacion> nuevaLista = ubicaciones.stream().filter(ubicacion -> this.distanciaEnKmA(ubicacion)<distanciaLimite).toList();
		return nuevaLista;
	}
/*	
	public List<Muestra> lasMuestras_QueEstanAMenosDe_KmDe_(List<Muestra> listaDeMuestras, double distancia, Muestra muestra){
		List<Muestra> nuevaListaDeMuestras = new ArrayList<>();
		for (Muestra m : listaDeMuestras) {
			if(distanciaEnKmA(muestra.getUbicacion())<distancia) {
				
			}
		}
	}*/
}
