package tpIntegradorTest.busquedaYFiltrosTestCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.Opinion;
import tpIntegrador.busquedaYFiltros.FiltroUltimaFechaDeVotacion;
import tpIntegrador.Muestra;

import static org.mockito.Mockito.*;

import java.time.LocalDate;


public class FiltroUltimaFechaDeVotacionTestCase {
	FiltroUltimaFechaDeVotacion filtroFechaVotacion = new FiltroUltimaFechaDeVotacion(LocalDate.of(2025, 1, 1));
	Muestra muestra1 = mock(Muestra.class);
	Muestra muestra2 = mock(Muestra.class);
	Opinion op1 = mock(Opinion.class);
	Opinion op2 = mock(Opinion.class);
	
	@BeforeEach
	void setUp(){
		when(op1.getFecha()).thenReturn(LocalDate.now());
		when(op2.getFecha()).thenReturn(LocalDate.of(2024, 1, 1));
		when(muestra1.laOpinionMasReciente()).thenReturn(op1);
		when(muestra2.laOpinionMasReciente()).thenReturn(op2);		
	}
	
	@Test
	void unaMuestraCuyaUltimaOpinionEsDespuesDeLaFechaIndicadaEnElFiltro_CumpleConEste() {
		assertTrue(filtroFechaVotacion.cumple(muestra1));
	}
	
	@Test
	void unaMuestraCuyaUltimaOpinionFueCreadaAntesDeLaFechaAFiltrarNoCumpleConElFiltro() {
		assertFalse(filtroFechaVotacion.cumple(muestra2));
	}
}
