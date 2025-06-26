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
	Opinion opinion = new Opinion(usuario, vinchucaInfestans);
	
	@Test
	void unaOpinionTieneUnUsuarioCreador() {
		assertEquals(opinion.getUsuario(), usuario);
	}
	
	@Test
	void unaOpinionEmitidaPorUnUsuarioExpertoEsUnaOpinionDeExperto() {
		when(usuario.getCategoria()).thenReturn("Usuario Basico");
		assertFalse(opinion.esOpinionDeExperto());
		when(usuario.getCategoria()).thenReturn("Usuario Experto");
		assertTrue(opinion.esOpinionDeExperto());
	}
	
	@Test
	void unaOpinionTieneUnaFechaDeCreacionYUnTipo() {
		assertEquals(opinion.getFecha(), LocalDate.now());
		assertEquals(opinion.getTipo(), TipoDeOpinion.VINCHUCA_INFESTANS);
	}
}
