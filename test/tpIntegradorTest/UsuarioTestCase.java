package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;

import java.time.LocalDate;

import tpIntegrador.Muestra;
import tpIntegrador.Opinion;
import tpIntegrador.Ubicacion;
import tpIntegrador.enums.TipoDeOpinion;
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
	
}
