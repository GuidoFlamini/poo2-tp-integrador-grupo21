package tpIntegrador;


interface Observer {
    void nuevaMuestra(ZonaDeCobertura zona, Muestra muestra);
    void muestraVerificada(ZonaDeCobertura zona, Muestra muestra);
}

//public abstract class Observer {
//    public abstract void nuevaMuestra(Muestra muestra);
//    public abstract void muestraVerificada(Muestra muestra);
//}