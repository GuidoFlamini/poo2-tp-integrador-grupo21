package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import tpIntegrador.FuncionalidadExterna;
import tpIntegrador.Ubicacion;
import tpIntegrador.ZonaDeCobertura;
import tpIntegrador.Opinion;
import tpIntegrador.Organizacion;
import tpIntegrador.enums.TipoDeOrganizacion;
import tpIntegrador.Muestra;
import tpIntegrador.usuario.Usuario;

public class OrganizacionTestCase {
	
	Organizacion organizacion = new Organizacion("ONG", new Ubicacion(1, 1), TipoDeOrganizacion.SALUD, 100);
    Muestra muestra = mock(Muestra.class);
    ZonaDeCobertura zonaDeCobertura1 = mock(ZonaDeCobertura.class);
    ZonaDeCobertura zonaDeCobertura2 = mock(ZonaDeCobertura.class);
	

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
    void unaOrganizacionPuedeDejarDeEstarRegistradaEnUnaZonaDeCobertura() {
    	organizacion.registrarEnZona(zonaDeCobertura1);
    	assertTrue(organizacion.getZonasRegistradas().contains(zonaDeCobertura1));
    	organizacion.desregistrarEnZona(zonaDeCobertura1);
    	assertFalse(organizacion.getZonasRegistradas().contains(zonaDeCobertura1));
    	verify(zonaDeCobertura1, times(1)).quitarObservador(organizacion);
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
