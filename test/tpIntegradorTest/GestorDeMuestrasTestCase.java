package tpIntegradorTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpIntegrador.FiltroMuestra;
import tpIntegrador.FiltroTipoDeInsecto;
import tpIntegrador.GestorDeMuestras;
import tpIntegrador.GestorDeZonas;
import tpIntegrador.Muestra;
import tpIntegrador.Opinion;
import tpIntegrador.Ubicacion;
import tpIntegrador.Usuario;
import tpIntegrador.ZonaDeCobertura;
import tpIntegrador.enums.TipoDeOpinion;

import static org.mockito.Mockito.*;

public class GestorDeMuestrasTestCase {
	GestorDeMuestras unGestorDeMuestras = new GestorDeMuestras(mock(GestorDeZonas.class));
	Ubicacion unq = mock(Ubicacion.class);
	Usuario unUsuario = mock(Usuario.class);
	TipoDeOpinion vinchucaInfestans = TipoDeOpinion.VINCHUCA_INFESTANS;
	ZonaDeCobertura quilmes = mock(ZonaDeCobertura.class);
	Muestra unaMuestra = mock(Muestra.class);
	Muestra otraMuestra = mock(Muestra.class);
	Opinion opinion = mock(Opinion.class);
	
	@BeforeEach
	void setUp() {
		unGestorDeMuestras = new GestorDeMuestras(mock(GestorDeZonas.class));
		when(unaMuestra.getUbicacion()).thenReturn(unq);
		when(unaMuestra.getUsuarioCreadorDeMuestra()).thenReturn(unUsuario);
		when(unaMuestra.laOpinionMasReciente()).thenReturn(opinion);
		when(otraMuestra.getUbicacion()).thenReturn(unq);
		when(otraMuestra.getUsuarioCreadorDeMuestra()).thenReturn(unUsuario);
		when(otraMuestra.laOpinionMasReciente()).thenReturn(opinion);
		when(opinion.getTipo()).thenReturn(vinchucaInfestans);

	}
	
	@Test
	void unGestorDeMuestrasAgregaUnaMuestraASuListaDeMuestras() {
		assertTrue(unGestorDeMuestras.getMuestras().isEmpty());
		unGestorDeMuestras.agregarMuestraDeTipoYUbicacionYUsuario(vinchucaInfestans, unq, unUsuario);
		assertFalse(unGestorDeMuestras.getMuestras().isEmpty());
	}
	
	@Test
	void unGestorDeMuestrasNoPuedeAgregarUnaMuestraCuyaPrimeraOpinionNoSeaVinchuca() {
		TipoDeOpinion imagenPocoClara = TipoDeOpinion.IMAGEN_POCO_CLARA;
		assertThrows(IllegalArgumentException.class, () -> { // dado que la especie inicial de la muestra nada mas puede ser vinchuca, lanzará una excepción
			 unGestorDeMuestras.agregarMuestraDeTipoYUbicacionYUsuario(imagenPocoClara, unq, unUsuario);
	        });
		assertTrue(unGestorDeMuestras.getMuestras().isEmpty());
	}
	
	@Test
	void dadaUnaZonaDeCoberturaElGestoDeMuestrasPuedeArrojarTodasLasMuestrasQueSeEncuentrenDentroDeEsaZona() {
		unGestorDeMuestras.muestrasDentroDeLaZona(quilmes);
		verify(quilmes, times(1)).lasMuestrasQueEstanDentroDeLaZona(unGestorDeMuestras.getMuestras()); 
	}
	
	@Test
	void dadaUnaMuestraUnGestorDeMuestrasConoceTodasLasMuestrasObtenidasAMenosDeUnaDistanciaDada() {
		unGestorDeMuestras.agregarMuestra(unaMuestra);
		unGestorDeMuestras.lasMuestrasAMenosDe_KmDe(10, otraMuestra);
		verify(unq, times(1)).estaAMenosDe_KmDe(10, otraMuestra.getUbicacion());
	}
	
	@Test
	void unGestorDeMuestrasPuedeIniciarUnaBusqueda() {
		FiltroTipoDeInsecto filtro = mock(FiltroTipoDeInsecto.class);
		when(filtro.cumple(unaMuestra)).thenReturn(true);
		when(filtro.cumple(otraMuestra)).thenReturn(false);
		unGestorDeMuestras.agregarMuestra(unaMuestra);
		unGestorDeMuestras.agregarMuestra(otraMuestra);
		assertTrue(unGestorDeMuestras.realizarBusquedaDeMuestrasConFiltro(filtro).contains(unaMuestra));
		assertFalse(unGestorDeMuestras.realizarBusquedaDeMuestrasConFiltro(filtro).contains(otraMuestra));
	}
}
