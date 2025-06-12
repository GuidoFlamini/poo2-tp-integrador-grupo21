package tpIntegradorTest;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import tpIntegrador.Ubicacion;

public class UbicacionTestCase {
	Ubicacion avellaneda = new Ubicacion(-34.6633, -58.3647);
	Ubicacion quilmes = new Ubicacion(-34.7207, -58.2549);
	
	@BeforeEach
	public void setUp() {
		avellaneda = new Ubicacion(-34.6633, -58.3647);
		
	}
	
	@Test
	void existeUnaUbicacionConLasCoordenadasDeAvellaneda() {
		assertEquals(avellaneda.getLatitud(), -34.6633);
		assertEquals(avellaneda.getLongitud(), -58.3647);
	}
	
	@Test
	void sePuedeCalcularLaDistanciaEnKilometrosEntreDosUbicaciones() {
		
		assertEquals(avellaneda.distanciaEnKmA(quilmes), 11.895914535573418);
	}
	
}
