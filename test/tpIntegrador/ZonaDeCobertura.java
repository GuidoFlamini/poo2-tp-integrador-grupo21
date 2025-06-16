package tpIntegrador;

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

}
