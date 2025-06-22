package tpIntegrador;

import java.util.ArrayList;
import java.util.List;

public class GestorDeZonas {
	private List<ZonaDeCobertura> zonasDeCobertura;
	
	public GestorDeZonas() {
		zonasDeCobertura = new ArrayList<>();		
	}

	public List<ZonaDeCobertura> getZonas() {
		return zonasDeCobertura;
	}
	
	public void agregarZonaDeCobertura(ZonaDeCobertura nuevaZona) {
		zonasDeCobertura.add(nuevaZona);
	}
	
	public List<ZonaDeCobertura> lasQueSeSolapanCon(ZonaDeCobertura unaZona){
		return unaZona.zonasConLasQueSeSolapa(zonasDeCobertura);
	}
	
	

	public Integer cantidadDeZonas() {
		return zonasDeCobertura.size();
	}
	
}
