package tpIntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.GestorDeZonas;
import tpIntegrador.ZonaDeCobertura;

public class GestorDeZonasTestCase {
	GestorDeZonas unGestorDeZonas = new GestorDeZonas();
	ZonaDeCobertura unaZona = mock(ZonaDeCobertura.class);
	
	@BeforeEach
	void setUp() {
		unGestorDeZonas = new GestorDeZonas();
	}
	
	@Test
	void unGestorDeZonasAgregaUnaZonaDeCobertura() {
		assertEquals(unGestorDeZonas.cantidadDeZonas(), 0);
		unGestorDeZonas.agregarZonaDeCobertura(unaZona);
		assertEquals(unGestorDeZonas.cantidadDeZonas(), 1);
	}
	
	@Test
	void unGestorDeZonasSabeLasZonasQueSeSolapanConOtra() {
		unGestorDeZonas.agregarZonaDeCobertura(unaZona);
		unGestorDeZonas.lasQueSeSolapanCon(unaZona);
		verify(unaZona, times(1)).zonasConLasQueSeSolapa(unGestorDeZonas.getZonas());
	}
	
}
