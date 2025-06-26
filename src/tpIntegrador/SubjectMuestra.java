package tpIntegrador;

public interface SubjectMuestra {
	public void notificarVerificada();
	public void agregarObserver(ObserverDeZonaDeCobertura observer);
	public void quitarObserver(ObserverDeZonaDeCobertura observer);
}
