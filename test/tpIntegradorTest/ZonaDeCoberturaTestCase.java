package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import tpIntegrador.Ubicacion;
import tpIntegrador.ZonaDeCobertura;


public class ZonaDeCoberturaTestCase {
	//Ubicacion unq = new Ubicacion(-34.7207, -58.2549);
	Ubicacion unq = mock(Ubicacion.class);
	Ubicacion lasFlores = mock(Ubicacion.class);
	ZonaDeCobertura bernal = new ZonaDeCobertura("Bernal", unq, 11); //nombre, epicentro, radio
	ZonaDeCobertura wilde = new ZonaDeCobertura("Wilde", lasFlores, 6);
	
	@Test
	void unaZonaDeCoberturaTieneNombreEpicentroYRadio() {
		assertEquals(bernal.getNombre(), "Bernal");
		assertEquals(bernal.getEpicentro(), unq);
		assertEquals(bernal.getRadio(), 11);
	}
	
	@Test
	void wildeSeSolapaConBernalYViceversa() {
		when(lasFlores.distanciaEnKmA(unq)).thenReturn((double) 8);
		when(unq.distanciaEnKmA(lasFlores)).thenReturn((double) 8);
		
		assertTrue(wilde.seSolapaCon(bernal));
		assertTrue(bernal.seSolapaCon(wilde));
		verify(lasFlores).distanciaEnKmA(unq);
		verify(unq).distanciaEnKmA(lasFlores);
	}
	
	@Test
	void wildeNoSeSolapaConBernal() {
		when(lasFlores.distanciaEnKmA(unq)).thenReturn((double) 20);
		assertFalse(wilde.seSolapaCon(bernal));
	}
}
