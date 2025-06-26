package tpIntegrador;

public interface SubjectZonaDeCobertura {
	public void notificarNuevaMuestra(Muestra muestra);
	public void notificarMuestraVerificada(Muestra muestra);
	public void agregarObservador(ObserverDeOrganizacion observador);
	public void quitarObservador(ObserverDeOrganizacion observador);
	
}
