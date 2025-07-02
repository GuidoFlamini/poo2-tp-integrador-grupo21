package tpIntegrador;

interface ObserverDeOrganizacion {
    void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra); //update
    void muestraVerificada(ZonaDeCobertura zona, Muestra muestra); //update
}