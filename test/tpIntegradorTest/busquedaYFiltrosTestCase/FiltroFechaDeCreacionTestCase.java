package tpIntegradorTest.busquedaYFiltrosTestCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.Muestra;
import tpIntegrador.busquedaYFiltros.FiltroFechaDeCreacion;

import static org.mockito.Mockito.*;

import java.time.LocalDate;


public class FiltroFechaDeCreacionTestCase {
	FiltroFechaDeCreacion filtroFecha = new FiltroFechaDeCreacion(LocalDate.of(2025, 1, 1));
	Muestra muestra1 = mock(Muestra.class);
	
	Muestra muestra2 = mock(Muestra.class);
	
	@BeforeEach
	void setUp(){
		when(muestra1.getFechaDeCreacion()).thenReturn(LocalDate.now());
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.of(2024, 1, 1));		
	}
	
	@Test
	void unaMuestraCreadaDespuesDeLaFechaIndicadaEnElFiltroCumpleConEste() {
		assertTrue(filtroFecha.cumple(muestra1));
	}
	
	@Test
	void unaMuestraCreadaAntesDeLaFechaAFiltrarNoCumpleConElFiltro() {
		assertFalse(filtroFecha.cumple(muestra2));
	}
}
