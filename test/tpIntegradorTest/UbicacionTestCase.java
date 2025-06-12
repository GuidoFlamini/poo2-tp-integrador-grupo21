package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import tpIntegrador.Ubicacion;

public class UbicacionTestCase {
	Ubicacion avellaneda = new Ubicacion(-34.6633, -58.3647);
	Ubicacion quilmes = new Ubicacion(-34.7207, -58.2549);
	Ubicacion madrid = new Ubicacion(40.4169, -3.7033);
	
	@BeforeEach
	public void setUp() {
		avellaneda = new Ubicacion(-34.6633, -58.3647);
		
	}
	
	@Test
	void existeUnaUbicacionConLasCoordenadasDeAvellaneda() {
		assertEquals(avellaneda.getLatitud(), -34.6633);
		assertEquals(avellaneda.getLongitud(), -58.3647);
	}
	
	@Test
	void sePuedeCalcularLaDistanciaEnKilometrosEntreDosUbicaciones() {
		
		assertEquals(avellaneda.distanciaEnKmA(quilmes), 11.895914535573418);
	}
	
	@Test
	void unaUbicacionPuedeConocerLasUbicacionesQueSeEncuentrenAMenosDeCiertaDistanciaAPartirDeUnaListaDeUbicaciones() {
		List<Ubicacion> ubicaciones = Arrays.asList(quilmes, madrid);
		List<Ubicacion> ubisCercanas = avellaneda.lasQueEstanAMenosDe_Km(ubicaciones, 20);
		assertTrue(ubisCercanas.contains(quilmes));
		assertFalse(ubisCercanas.contains(madrid));
	}
	 
}
