package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import tpIntegrador.FuncionalidadExterna;
import tpIntegrador.Muestra;
import tpIntegrador.Ubicacion;
import tpIntegrador.ZonaDeCobertura;
import tpIntegrador.Opinion;
import tpIntegrador.Organizacion;
import tpIntegrador.enums.TipoDeOrganizacion;
import tpIntegrador.usuario.Usuario;

public class OrganizacionTestCase {
	FuncionalidadExterna funcNuevaMuestra = mock(FuncionalidadExterna.class);
	FuncionalidadExterna funcValidacion = mock(FuncionalidadExterna.class);
	Organizacion organizacion = new Organizacion("ONG", new Ubicacion(1, 1), TipoDeOrganizacion.SALUD, 100, funcNuevaMuestra, funcValidacion);
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
    
    @Test
	void cuandoUnaOrganizacionSeEnteraDeQueUnaMuestraNuevaFueCreadaEnSuZonaDeInteresEjecutaLaFuncionalidadExterna() {
    	organizacion.nuevaMuestra(zonaDeCobertura1, muestra);
    	verify(funcNuevaMuestra, times(1)).nuevoEvento(organizacion, zonaDeCobertura1, muestra);
    }
    
    @Test
	void cuandoUnaOrganizacionSeEnteraDeQueUnaMuestraFueVerificadaEnSuZonaDeInteresEjecutaLaFuncionalidadExterna() {
    	organizacion.muestraVerificada(zonaDeCobertura1, muestra);
    	verify(funcValidacion, times(1)).nuevoEvento(organizacion, zonaDeCobertura1, muestra);
    }
    
    @Test
    void unaOrganizacionPuedeCambiarSuFuncionalidadExterna() {
    	organizacion.setFuncionalidadNuevaMuestra(funcValidacion);
    	assertEquals(organizacion.getFuncionalidadNuevaMuestra(), funcValidacion);
    	organizacion.setFuncionalidadValidacion(funcNuevaMuestra);
    	assertEquals(organizacion.getFuncionalidadValidacion(), funcNuevaMuestra);
    }
    
}
