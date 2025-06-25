package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import tpIntegrador.FuncionalidadExterna;
import tpIntegrador.Muestra;
import tpIntegrador.Ubicacion;
import tpIntegrador.Usuario;
import tpIntegrador.Opinion;
import tpIntegrador.Organizacion;
import tpIntegrador.enums.TipoDeOrganizacion;

public class OrganizacionTestCase {

    @Test
    public void testNotificaNuevaMuestra() {

        FuncionalidadExterna func = mock(FuncionalidadExterna.class);
        Organizacion organizacion = new Organizacion("ONG", new Ubicacion(1, 1), TipoDeOrganizacion.SALUD, 100);
        Muestra muestra = mock(Muestra.class);


        organizacion.nuevaMuestra(muestra);

        verify(func).nuevoEvento(organizacion, null, muestra); //verificamos que la funcionalidad fue llamada
    } 

    // En teoria con Mockito anda.

    @Test
    public void testMuestraNoVerificadaNoNotifica() {
        FuncionalidadExterna func = mock(FuncionalidadExterna.class);
        Muestra muestra = mock(Muestra.class);
        when(muestra.esMuestraVerificada()).thenReturn(false);

        Organizacion org = new Organizacion("ONG", new Ubicacion(1, 1), TipoDeOrganizacion.SALUD, 100);

        org.muestraVerificada(muestra);

        verify(func, never()).nuevoEvento(any(), any(), any()); // Al no estar verificada no debería llamar a la funcion.
    }


    @Test
    public void testHistorialDeMuestras() {
        Muestra muestra = mock(Muestra.class);
        Organizacion organizacion = new Organizacion("ONG", new Ubicacion(1, 1), TipoDeOrganizacion.SALUD, 100);

        organizacion.nuevaMuestra(muestra);

        assertEquals(1, organizacion.getHistorialDeMuestras().size());
}
}
