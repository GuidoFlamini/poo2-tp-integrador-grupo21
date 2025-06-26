package tpIntegradorTest.busquedaYFiltrosTestCase;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.Muestra;
import tpIntegrador.busquedaYFiltros.BusquedaDeMuestra;
import tpIntegrador.busquedaYFiltros.FiltroTipoDeInsecto;

import static org.mockito.Mockito.*;


public class BusquedaDeMuestraTestCase {
	Muestra muestra1 = mock(Muestra.class);
	Muestra muestra2 = mock(Muestra.class);
	Muestra muestra3 = mock(Muestra.class);
	FiltroTipoDeInsecto filtroTipoInsecto = mock(FiltroTipoDeInsecto.class);
	List<Muestra> listaDeMuestras = Arrays.asList(muestra1, muestra2, muestra3);
	
	BusquedaDeMuestra unaBusqueda = new BusquedaDeMuestra(listaDeMuestras, filtroTipoInsecto);
	
	@BeforeEach
	void setUp() {
		listaDeMuestras = Arrays.asList(muestra1, muestra2, muestra3);
		unaBusqueda = new BusquedaDeMuestra(listaDeMuestras, filtroTipoInsecto);
	}
	
	@Test
	void unaBusquedaArrojaDosMuestras() {
		when(filtroTipoInsecto.cumple(muestra1)).thenReturn(false);
		when(filtroTipoInsecto.cumple(muestra2)).thenReturn(true);
		when(filtroTipoInsecto.cumple(muestra3)).thenReturn(true);
		assertEquals(unaBusqueda.getResultados().size(),2);
		assertFalse(unaBusqueda.getResultados().contains(muestra1));
		assertTrue(unaBusqueda.getResultados().contains(muestra2));
		assertTrue(unaBusqueda.getResultados().contains(muestra3));
	}
	
	@Test
	void cuandoNingunaMuestraCoincideConElFiltroLaBusquedaArrojaUnaListaVacia() {
		when(filtroTipoInsecto.cumple(muestra1)).thenReturn(false);
		when(filtroTipoInsecto.cumple(muestra2)).thenReturn(false);
		when(filtroTipoInsecto.cumple(muestra3)).thenReturn(false);
		assertTrue(unaBusqueda.getResultados().isEmpty());
	}
	
}
