package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

import java.time.LocalDate;

import tpIntegrador.Opinion;
import tpIntegrador.Ubicacion;
import tpIntegrador.enums.TipoDeOpinion;
import tpIntegrador.Muestra;
import tpIntegrador.usuario.Usuario;
import tpIntegrador.usuario.UsuarioExperto;

public class UsuarioTestCase {
	Usuario pepe = new Usuario("pepe", false);
	Usuario juan = new Usuario("juan", true);
	Muestra muestra = mock(Muestra.class);
	Muestra muestra2 = mock(Muestra.class);
	Ubicacion quilmes = mock(Ubicacion.class);
	TipoDeOpinion vinchucaInfestans = TipoDeOpinion.VINCHUCA_INFESTANS;
	
	@BeforeEach
	public void setUp() throws Exception {
		pepe = new Usuario("pepe", false);
		juan = new Usuario("juan", true);
		muestra = mock(Muestra.class);
	}
	
	@Test
	void existeUnUsuarioDeNombrePepeQueNoEsEspecialista() {
		assertEquals(pepe.getNombre(), "pepe");
		assertEquals(pepe.esEspecialista(), false);
	}
	
	@Test
	void pepeEmpiezaComoUsuarioBasico() {
		assertEquals(pepe.getCategoria(), "Usuario Basico");
	}
	
	@Test
	void existeUnUsuarioDeNombreJuanQueEsEspecialistaYEmpiezaComoExperto() {
		assertEquals(juan.getCategoria(), "Usuario Experto");
	}
	
	
	@Test
	void unUsuarioOpinaSobreUnaMuestra() {
		assertEquals(pepe.getOpinionesRealizadas().size(), 0);
		pepe.opinarSobre(vinchucaInfestans, muestra);
		assertEquals(pepe.getOpinionesRealizadas().size(), 1);
	}
	
	@Test
	void unUsuarioNoPuedeOpinarSobreUnaMuestraVerificada() {
		when(muestra.esMuestraVerificada()).thenReturn(true);
		assertThrows(IllegalArgumentException.class, () -> {
			 juan.opinarSobre(vinchucaInfestans, muestra);
	        });
	
	}
	
	@Test
	void unUsuarioBasicoNoPuedeOpinarUnaVezQueOpinoUnExperto() {
		when(muestra.hayOpinionesDeExpertos()).thenReturn(true);
		when(muestra.esMuestraVerificada()).thenReturn(false);
		assertThrows(IllegalArgumentException.class, () -> { // pepe, que no es experto, lanza una excepción cuando intenta opinar
			 pepe.opinarSobre(vinchucaInfestans, muestra);
	        });
		juan.opinarSobre(vinchucaInfestans, muestra); // juan, que es experto, puede opinar normalmente
		assertEquals(pepe.getOpinionesRealizadas().size(), 0); // pepe tiene cero opiniones realizadas
		assertEquals(juan.getOpinionesRealizadas().size(), 1); // juan, al ser experto, añade su opinión a opinionesRealizadas
	}
	
	@Test
	void unUsuarioBasicoSeConvierteEnExperto() {
		Muestra otraMuestra = mock(Muestra.class);
		when(otraMuestra.getFechaDeCreacion()).thenReturn(LocalDate.now());
		assertEquals(pepe.getCategoria(), "Usuario Basico");
		for (int i = 0; i < 21; i++) {
	        pepe.opinarSobre(vinchucaInfestans, muestra);
	        pepe.enviarMuestra(otraMuestra);
	    }
		assertEquals(pepe.getOpinionesRealizadas().size(), 21);
		assertEquals(pepe.getCategoria(), "Usuario Experto");
	}
	
	@Test
	void unUsuarioExpertoSeConvierteEnBasico() {
		pepe.setCategoria(new UsuarioExperto());
		//pepe no tiene suficientes posteos como para mantener su categoria de experto, por lo que vuelve a básico
		assertEquals(pepe.getCategoria(), "Usuario Basico");
	}
	
	@Test
	void unUsuarioEnviaUnaMuestra() {
		when(muestra2.getFechaDeCreacion()).thenReturn(LocalDate.now());
		assertEquals(pepe.getMuestrasEnviadas().size(), 0);
		pepe.enviarMuestra(muestra2);
		assertEquals(pepe.getMuestrasEnviadas().size(), 1);
	}
/*	
	@Test
	void unUsuarioNoPuedeEnviarUnaMuestraConUnaEspecieQueNoSeaVinchuca() {
		TipoDeOpinion chinche = mock(TipoDeOpinion.class);
				
		when(chinche.toString()).thenReturn("CHINCHE_FOLIADA"); // acá se crea el TipoDeOpinion que va a ser chinche
		assertThrows(IllegalArgumentException.class, () -> { // dado que la especie inicial de la muestra nada mas puede ser vinchuca, lanzará una excepción
			 pepe.enviarMuestraConUbicacionYTipo(quilmes, chinche);
	        });
		
		assertEquals(pepe.getMuestrasEnviadas().size(), 0);
	}*/
	
	
	
}
