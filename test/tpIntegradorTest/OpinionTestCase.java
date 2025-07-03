package tpIntegradorTest;

import tpIntegrador.Opinion;
import tpIntegrador.enums.TipoDeOpinion;
import tpIntegrador.usuario.Usuario;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class OpinionTestCase {
	TipoDeOpinion vinchucaInfestans = TipoDeOpinion.VINCHUCA_INFESTANS;
	Usuario usuario = mock(Usuario.class);
	Usuario usuario2= mock(Usuario.class);
	
	Opinion opinion = new Opinion(usuario, vinchucaInfestans);
	Opinion opinion2 = new Opinion(usuario2, vinchucaInfestans);
	
	@BeforeEach
	void setUp() {
		when(usuario.getCategoria()).thenReturn("Usuario Basico");
		when(usuario2.getCategoria()).thenReturn("Usuario Experto");
		opinion = new Opinion(usuario, vinchucaInfestans);
		opinion2 = new Opinion(usuario2, vinchucaInfestans);
	}
	
	@Test
	void unaOpinionTieneUnUsuarioCreador() {
		assertEquals(opinion.getUsuario(), usuario);
	}
	
	@Test
	void unaOpinionEmitidaPorUnUsuarioExpertoEsUnaOpinionDeExperto() {
		assertFalse(opinion.esOpinionDeExperto());
		assertTrue(opinion2.esOpinionDeExperto());
	}
	
	@Test
	void unaOpinionSigueSiendoDeExpertoAunqueSuCreadorHayaDejadoDESerlo() {
		assertTrue(opinion2.esOpinionDeExperto());
		when(usuario2.getCategoria()).thenReturn("Usuario Basico");
		assertTrue(opinion2.esOpinionDeExperto());
	}
	
	@Test
	void unaOpinionTieneUnaFechaDeCreacionYUnTipo() {
		assertEquals(opinion.getFecha(), LocalDate.now());
		assertEquals(opinion.getTipo(), TipoDeOpinion.VINCHUCA_INFESTANS);
	}
}
