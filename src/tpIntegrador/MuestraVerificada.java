package tpIntegrador;

import java.util.Map;
import java.util.stream.Collectors;

import tpIntegrador.enums.TipoDeOpinion;

public class MuestraVerificada extends MuestraState {

    @Override
    public boolean esVerificada() {
        return true;
    }
    
    @Override
    public void agregarOpinion(Muestra muestra, Opinion opinion) {
        throw new IllegalArgumentException("No se puede opinar sobre la muestra porque ya está verificada.");

    }

    @Override
    public TipoDeOpinion resultadoActual(Muestra muestra) {
        return muestra.getOpinionesDeExperto().stream()
                .map(opinion -> opinion.getTipo())
                .collect(Collectors.groupingBy(tipo -> tipo, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
