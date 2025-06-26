package tpIntegrador;


interface Observer {
    void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra);
    void muestraVerificada(ZonaDeCobertura zona, Muestra muestra);
}