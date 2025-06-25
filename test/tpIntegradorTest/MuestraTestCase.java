package tpIntegradorTest;
import tpIntegradorTest.UbicacionTestCase;
import tpIntegradorTest.UsuarioTestCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import tpIntegrador.Muestra;
import tpIntegrador.Ubicacion;
import tpIntegrador.Usuario;
import tpIntegrador.Opinion;
import tpIntegrador.enums.TipoDeOpinion;


public class MuestraTestCase {

    Usuario tito = new Usuario("Tito", false);
    Usuario manuel = new Usuario("Manuel", true);
    Usuario jorge = new Usuario("Jorge", true);
    Ubicacion sarandi = new Ubicacion(-34.6633, -58.3647);
	Ubicacion wilde = new Ubicacion(-34.7207, -58.2549);
    TipoDeOpinion vinchucaInfestans = TipoDeOpinion.VINCHUCA_INFESTANS;
    TipoDeOpinion ninguna = TipoDeOpinion.NINGUNA;
    TipoDeOpinion imagenPocoClara = TipoDeOpinion.IMAGEN_POCO_CLARA;
    TipoDeOpinion chincheFolida = TipoDeOpinion.CHINCHE_FOLIADA;  
    Opinion opinion1 = new Opinion(tito, ninguna);
    Opinion opinion2 = new Opinion(manuel, imagenPocoClara);
    Opinion opinion3 = new Opinion(jorge, chincheFolida);
    Opinion opinion4 = new Opinion(manuel, chincheFolida); 

    
    
    Muestra muestra1 = new Muestra(sarandi, vinchucaInfestans ,manuel);
    Muestra muestra2 = new Muestra(wilde, ninguna, tito);
   
   
    Usuario pepe= mock(Usuario.class);
    Usuario juan= mock(Usuario.class);
    Ubicacion quilmes = mock(Ubicacion.class);
    Ubicacion avellaneda = mock(Ubicacion.class);


    @BeforeEach
	public void setUp() throws Exception {
		muestra1 = new Muestra(sarandi, vinchucaInfestans, manuel);
		muestra2 = new Muestra(wilde, ninguna, tito);
	}

    @Test
    void agregarOpinionDeUsuario() {
        
        muestra1.agregarOpinion(opinion3);
        List<Opinion> opiniones = muestra1.getOpiniones();
        
        assertEquals(2, opiniones.size());
        assertThrows(IllegalArgumentException.class, () -> {
			 muestra1.agregarOpinion(opinion3);
	        });
    }

    @Test
    void existeMuestraVerificada() {
        assertTrue(muestra1.hayOpinionesDeExpertos());
        assertFalse(muestra1.esMuestraVerificada()); 

        muestra2.agregarOpinion(opinion3);
        muestra2.agregarOpinion(opinion4);

        when(muestra2.resultadoActual()).thenReturn(chincheFolida);
        assertTrue(muestra2.esMuestraVerificada());
        

    }

    @Test
    void existenLosDatosDeLaMuestra() {
        when(manuel.getNombre()).thenReturn("Manuel");
        when(muestra1.resultadoActual()).thenReturn(vinchucaInfestans);
        
        
        assertEquals(muestra1.getUbicacion(), sarandi);
        assertEquals(muestra1.getTiposDeOpinion(), Arrays.asList(vinchucaInfestans));
        assertEquals(muestra1.getUsuarioCreadorDeMuestra(), manuel);  
    }
}
