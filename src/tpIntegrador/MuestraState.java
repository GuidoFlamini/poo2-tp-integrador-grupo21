package tpIntegrador;

import tpIntegrador.enums.TipoDeOpinion;


public abstract class MuestraState {
    
    public boolean esVerificada() {
        return false;
    }

    public boolean esParcialmenteVerificada() {
        return false; 
    }

    protected abstract void agregarOpinion(Muestra muestra, Opinion opinion);

    public abstract TipoDeOpinion resultadoActual(Muestra muestra);
}
