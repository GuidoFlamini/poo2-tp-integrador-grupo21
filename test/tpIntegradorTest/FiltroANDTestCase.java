package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.FiltroAND;
import tpIntegrador.FiltroFechaDeCreacion;
import tpIntegrador.FiltroNivelDeValidacion;
import tpIntegrador.FiltroOR;
import tpIntegrador.Muestra;

import static org.mockito.Mockito.*;

public class FiltroANDTestCase {
	FiltroAND filtroAND = new FiltroAND();
	FiltroFechaDeCreacion filtroFechaDeCreacion = mock(FiltroFechaDeCreacion.class);
	FiltroNivelDeValidacion filtroNivelDeValidacion = mock(FiltroNivelDeValidacion.class);
	Muestra muestra1 = mock(Muestra.class);
	FiltroOR filtroOR = mock(FiltroOR.class);
	
	@Test
	void unFiltroANDPuedeTenerVariosFiltros() {
		assertEquals(filtroAND.getFiltros().size(), 0);
		filtroAND.agregarFiltro(filtroFechaDeCreacion);
		filtroAND.agregarFiltro(filtroNivelDeValidacion);
		assertEquals(filtroAND.getFiltros().size(), 2);		
	}
	
	@Test
	void unFiltroANDPuedeRemoverFiltros() {
		filtroAND.agregarFiltro(filtroFechaDeCreacion);
		assertTrue(filtroAND.getFiltros().contains(filtroFechaDeCreacion));
		filtroAND.eliminarFiltro(filtroFechaDeCreacion);
		assertFalse(filtroAND.getFiltros().contains(filtroFechaDeCreacion));
	}
	
	@Test
	void unFiltroANDPuedeObtenerUnFiltroEnLaPosicionIndicada() {
		filtroAND.agregarFiltro(filtroFechaDeCreacion);
		assertEquals(filtroAND.getFiltroEnPosicion(0), filtroFechaDeCreacion);
		
	}
	
	@Test
	void unaMuestraQueCumpleTodosLosFiltrosCumpleConElFiltroAND() {
		when(filtroFechaDeCreacion.cumple(muestra1)).thenReturn(true);
		when(filtroNivelDeValidacion.cumple(muestra1)).thenReturn(true);
		filtroAND.agregarFiltro(filtroFechaDeCreacion);
		filtroAND.agregarFiltro(filtroNivelDeValidacion);
		assertTrue(filtroAND.cumple(muestra1));
	}
	
	@Test
	void unaMuestraQueNoCumpleConTodosLosFiltrosTampocoCumpleConElFiltroAND() {
		when(filtroFechaDeCreacion.cumple(muestra1)).thenReturn(true);
		when(filtroNivelDeValidacion.cumple(muestra1)).thenReturn(false);
		filtroAND.agregarFiltro(filtroFechaDeCreacion);
		filtroAND.agregarFiltro(filtroNivelDeValidacion);
		assertFalse(filtroAND.cumple(muestra1));
	}
	
	
}
