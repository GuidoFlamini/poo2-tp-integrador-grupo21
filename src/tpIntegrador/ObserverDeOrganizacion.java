package tpIntegrador;


interface ObserverDeOrganizacion {
    void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra);
    void muestraVerificada(ZonaDeCobertura zona, Muestra muestra);
}