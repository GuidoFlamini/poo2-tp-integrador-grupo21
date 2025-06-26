package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import tpIntegrador.FuncionalidadExterna;
import tpIntegrador.Muestra;
import tpIntegrador.Ubicacion;
import tpIntegrador.Usuario;
import tpIntegrador.ZonaDeCobertura;
import tpIntegrador.Opinion;
import tpIntegrador.Organizacion;
import tpIntegrador.enums.TipoDeOrganizacion;

public class OrganizacionTestCase {
<<<<<<< gestorDeMuestras-y-gestorDeZonas
	
	Organizacion organizacion = new Organizacion("ONG", new Ubicacion(1, 1), TipoDeOrganizacion.SALUD, 100);
    Muestra muestra = mock(Muestra.class);
    ZonaDeCobertura zonaDeCobertura1 = mock(ZonaDeCobertura.class);
    ZonaDeCobertura zonaDeCobertura2 = mock(ZonaDeCobertura.class);
	
/*
=======

    

>>>>>>> main
    @Test
    public void testNotificaNuevaMuestra() {

        FuncionalidadExterna func = mock(FuncionalidadExterna.class);
        Organizacion organizacion = new Organizacion("ONG", new Ubicacion(1, 1), TipoDeOrganizacion.SALUD, 100);
        Muestra muestra = mock(Muestra.class);


        organizacion.nuevaMuestra(muestra);

        verify(func, times(1)).nuevoEvento(organizacion, null, muestra); //verificamos que la funcionalidad fue llamada
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
    }*/


    @Test
    void unaOrganizacionRegistraUnaMuestra() {
        organizacion.registrarMuestra(muestra);
        assertEquals(1, organizacion.getHistorialDeMuestras().size());
        assertTrue(organizacion.getHistorialDeMuestras().contains(muestra));
    }
    
    @Test
    void unaOrganizacionPuedeRegistrarseEnUnaOVariasZonasDeCobertura() {
    	assertTrue(organizacion.getZonasRegistradas().isEmpty());
    	organizacion.registrarEnZona(zonaDeCobertura1);
    	organizacion.registrarEnZona(zonaDeCobertura2);
    	assertTrue(organizacion.getZonasRegistradas().contains(zonaDeCobertura1));
    	assertTrue(organizacion.getZonasRegistradas().contains(zonaDeCobertura2));
    }
    
    @Test
    void unaOrganizacionPuedeIncrementarOReducirLaCantidadDeEmpleados() {
    	assertEquals(organizacion.getCantDeEmpleados(), 100);
    	organizacion.agregarEmpleados(20);
    	assertEquals(organizacion.getCantDeEmpleados(), 120);
    	organizacion.reducirEmpleados(5);
    	assertEquals(organizacion.getCantDeEmpleados(), 115);
    }
    
    @Test
    void unaOrganizacionNoPuedeAumentarOReducirUnaCantidadNegativaDeEmpleados() {
    	assertEquals(organizacion.getCantDeEmpleados(), 100);
    	assertThrows(IllegalArgumentException.class, () -> { 
			 organizacion.agregarEmpleados(-20);
	        });
    	assertThrows(IllegalArgumentException.class, () -> { 
			 organizacion.reducirEmpleados(-20);
	        });
    }
    
}
