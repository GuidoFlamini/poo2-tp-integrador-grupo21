package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.FiltroNivelDeValidacion;
import tpIntegrador.Muestra;

import static org.mockito.Mockito.*;



public class FiltroNivelDeValidacionTestCase {
	FiltroNivelDeValidacion filtroNivel = new FiltroNivelDeValidacion("Verificada");
	Muestra muestra1 = mock(Muestra.class);
	Muestra muestra2 = mock(Muestra.class);
	
	@BeforeEach
	void setUp(){
		when(muestra1.getNivelDeValidacion()).thenReturn("Verificada");
		when(muestra2.getNivelDeValidacion()).thenReturn("Votada");		
	}
	
	@Test
	void unaMuestraCumpleConUnFiltroDeValidacion() {
		assertTrue(filtroNivel.cumple(muestra1));
	}
	
	@Test
	void unaMuestraNoCumpleConUnFiltroDeValidacion() {
		assertFalse(filtroNivel.cumple(muestra2));
	}
}
