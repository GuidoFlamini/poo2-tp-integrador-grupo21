package tpIntegradorTest.busquedaYFiltrosTestCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.Muestra;
import tpIntegrador.busquedaYFiltros.FiltroTipoDeInsecto;
import tpIntegrador.enums.TipoDeOpinion;

import static org.mockito.Mockito.*;



public class FiltroTipoDeInsectoTestCase {
	FiltroTipoDeInsecto filtroInsecto = new FiltroTipoDeInsecto(TipoDeOpinion.CHINCHE_FOLIADA);
	Muestra muestra1 = mock(Muestra.class);
	Muestra muestra2 = mock(Muestra.class);
	
	@BeforeEach
	void setUp(){
		when(muestra1.resultadoActual()).thenReturn(TipoDeOpinion.CHINCHE_FOLIADA);
		when(muestra2.resultadoActual()).thenReturn(TipoDeOpinion.IMAGEN_POCO_CLARA);		
	}
	
	@Test
	void unaMuestraCumpleConElFiltroDeTipo() {
		assertTrue(filtroInsecto.cumple(muestra1));
	}
	
	@Test
	void unaMuestraNoCumpleConElFiltroDeTipo() {
		assertFalse(filtroInsecto.cumple(muestra2));
	}
}

