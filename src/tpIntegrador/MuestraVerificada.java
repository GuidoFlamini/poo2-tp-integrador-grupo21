package tpIntegrador;

public class MuestraVerificada extends MuestraState {

    @Override
    public boolean esVerificada() {
        return true;
    }
    
    @Override
    public void agregarOpinion(Muestra muestra, Opinion opinion) {
        throw new IllegalArgumentException("No se puede opinar sobre la muestra porque ya está verificada.");

    }
}
