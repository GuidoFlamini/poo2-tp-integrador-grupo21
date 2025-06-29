package tpIntegradorTest.busquedaYFiltrosTestCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.busquedaYFiltros.FiltroAND;
import tpIntegrador.busquedaYFiltros.FiltroFechaDeCreacion;
import tpIntegrador.busquedaYFiltros.FiltroNivelDeValidacion;
import tpIntegrador.busquedaYFiltros.FiltroOR;
import tpIntegrador.Muestra;

import static org.mockito.Mockito.*;

public class FiltroORTestCase {
	FiltroOR filtroOR = new FiltroOR();
	FiltroFechaDeCreacion filtroFechaDeCreacion = mock(FiltroFechaDeCreacion.class);
	FiltroNivelDeValidacion filtroNivelDeValidacion = mock(FiltroNivelDeValidacion.class);
	Muestra muestra1 = mock(Muestra.class);
	FiltroAND filtroAND = mock(FiltroAND.class);
	
	@Test
	void unFiltroORPuedeTenerVariosFiltros() {
		assertEquals(filtroOR.getFiltros().size(), 0);
		filtroOR.agregarFiltro(filtroFechaDeCreacion);
		filtroOR.agregarFiltro(filtroNivelDeValidacion);
		assertEquals(filtroOR.getFiltros().size(), 2);		
	}
	
	@Test
	void unFiltroORPuedeRemoverFiltros() {
		filtroOR.agregarFiltro(filtroFechaDeCreacion);
		assertTrue(filtroOR.getFiltros().contains(filtroFechaDeCreacion));
		filtroOR.eliminarFiltro(filtroFechaDeCreacion);
		assertFalse(filtroOR.getFiltros().contains(filtroFechaDeCreacion));
	}
	
	@Test
	void unFiltroORPuedeObtenerUnFiltroEnLaPosicionIndicada() {
		filtroOR.agregarFiltro(filtroFechaDeCreacion);
		assertEquals(filtroOR.getFiltroEnPosicion(0), filtroFechaDeCreacion);
		
	}
	
	@Test
	void unaMuestraQueCumpleTodosLosFiltrosCumpleConElFiltroOR() {
		when(filtroFechaDeCreacion.cumple(muestra1)).thenReturn(true);
		when(filtroNivelDeValidacion.cumple(muestra1)).thenReturn(true);
		filtroOR.agregarFiltro(filtroFechaDeCreacion);
		filtroOR.agregarFiltro(filtroNivelDeValidacion);
		assertTrue(filtroOR.cumple(muestra1));
	}
	
	@Test
	void unaMuestraQueCumpleConAlMenosUnFiltroCumpleConElFiltroOR() {
		when(filtroFechaDeCreacion.cumple(muestra1)).thenReturn(true);
		when(filtroNivelDeValidacion.cumple(muestra1)).thenReturn(false);
		filtroOR.agregarFiltro(filtroFechaDeCreacion);
		filtroOR.agregarFiltro(filtroNivelDeValidacion);
		assertTrue(filtroOR.cumple(muestra1));
	}
	
	@Test
	void unaMuestraQueNoCumpleConNingunFiltroTampocoCumpleConUnFiltroOR() {
		when(filtroFechaDeCreacion.cumple(muestra1)).thenReturn(false);
		when(filtroNivelDeValidacion.cumple(muestra1)).thenReturn(false);
		filtroOR.agregarFiltro(filtroFechaDeCreacion);
		filtroOR.agregarFiltro(filtroNivelDeValidacion);
		assertFalse(filtroOR.cumple(muestra1));
	}
	
	@Test
	void unFiltroORPuedeTenerFiltrosCompuestos() {
		when(filtroFechaDeCreacion.cumple(muestra1)).thenReturn(false);
		when(filtroNivelDeValidacion.cumple(muestra1)).thenReturn(false);
		filtroAND.agregarFiltro(filtroFechaDeCreacion);
		filtroAND.agregarFiltro(filtroNivelDeValidacion);
		filtroOR.agregarFiltro(filtroFechaDeCreacion);
		filtroOR.agregarFiltro(filtroNivelDeValidacion);
		filtroOR.agregarFiltro(filtroAND);
		assertFalse(filtroOR.cumple(muestra1));
	}
}
