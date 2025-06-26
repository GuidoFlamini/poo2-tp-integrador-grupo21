package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import tpIntegrador.Muestra;
import tpIntegrador.Organizacion;
import tpIntegrador.Ubicacion;
import tpIntegrador.ZonaDeCobertura;


public class ZonaDeCoberturaTestCase {
	//Ubicacion unq = new Ubicacion(-34.7207, -58.2549);
	Ubicacion unq = mock(Ubicacion.class);
	Ubicacion lasFlores = mock(Ubicacion.class);
	Ubicacion tandil = mock(Ubicacion.class);
	ZonaDeCobertura bernal = new ZonaDeCobertura("Bernal", unq, 11); //nombre, epicentro, radio
	ZonaDeCobertura wilde = new ZonaDeCobertura("Wilde", lasFlores, 6);
	ZonaDeCobertura partidoDeTandil = new ZonaDeCobertura("Partido de Tandil", tandil, 50);
	Muestra muestra1 = mock(Muestra.class);
	Muestra muestra2 = mock(Muestra.class);
	Muestra muestra3 = mock(Muestra.class);
	
	@BeforeEach
	void setUp() {
		when(unq.distanciaEnKmA(tandil)).thenReturn((double) 400);
		when(muestra1.getUbicacion()).thenReturn(lasFlores);
		when(muestra2.getUbicacion()).thenReturn(unq);
		when(muestra3.getUbicacion()).thenReturn(tandil);	
	}
	
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
	
	@Test
	void unaZonaDeCoberturaPuedeSaberLasMuestrasQueSeEncuentranDentroDeElla() {
			
		when(unq.distanciaEnKmA(unq)).thenReturn((double) 0);
		when(unq.distanciaEnKmA(lasFlores)).thenReturn((double) 8);
		
		List<Muestra> muestras = Arrays.asList(muestra1, muestra2, muestra3);
		assertEquals(bernal.lasMuestrasQueEstanDentroDeLaZona(muestras).size(), 2);
		assertFalse(bernal.lasMuestrasQueEstanDentroDeLaZona(muestras).contains(muestra3));

	}
	
	@Test
	void unaZonaDeCoberturaPuedeSaberLasZonasConLasQueSeSolapa() {
		List<ZonaDeCobertura> listaDeZonas = Arrays.asList(wilde, partidoDeTandil);
		assertEquals(bernal.zonasConLasQueSeSolapa(listaDeZonas).size(), 1);
		assertTrue(bernal.zonasConLasQueSeSolapa(listaDeZonas).contains(wilde));
		assertFalse(bernal.zonasConLasQueSeSolapa(listaDeZonas).contains(partidoDeTandil));
	}
	
	@Test
	void unaZonaDeCoberturaRegistraUnaMuestraSiEstaDEntroDeSuRadio() {
		bernal.agregarMuestra(muestra1);
		bernal.agregarMuestra(muestra3);
		assertTrue(bernal.getMuestras().contains(muestra1));
		assertFalse(bernal.getMuestras().contains(muestra3));
	}
	
	@Test
	void cuandoUnaZonaDeCoberturaAgregaUnaMuestraSeSuscribeComoObserverAMuestra() {
		bernal.agregarMuestra(muestra1);
		verify(muestra1, times(1)).agregarObserver(bernal);
	}
	
	@Test
	void cuandoUnaZonaDeCoberturaAgregaUnaMuestraSeNotificaALasOrganizacionesQueSeHayanRegistrado() {
		Organizacion orga1 = mock(Organizacion.class);
		bernal.agregarObservador(orga1);
		bernal.agregarMuestra(muestra1);
		verify(orga1, times(1)).nuevaMuestra(bernal, muestra1);
	}
	
	@Test
	void cuandoUnaZonaDeCoberturaSeEnteraDeLaVerificacionDeUnaMuestraNotificaALasOrganizacionesQueSeHayanRegistrado() {
		Organizacion orga1 = mock(Organizacion.class);
		bernal.agregarObservador(orga1);
		bernal.agregarMuestra(muestra1);
		bernal.notificarMuestraVerificada(muestra1);
		verify(orga1, times(1)).muestraVerificada(bernal, muestra1);
	}
}
