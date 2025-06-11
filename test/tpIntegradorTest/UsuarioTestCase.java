package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import tpIntegrador.Usuario;

public class UsuarioTestCase {
	Usuario pepe = new Usuario("pepe", false);
	Usuario juan = new Usuario("juan", true);
	
	@BeforeEach
	public void setUp() throws Exception {
		pepe = new Usuario("pepe", false);
		juan = new Usuario("juan", true);
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
	
}
