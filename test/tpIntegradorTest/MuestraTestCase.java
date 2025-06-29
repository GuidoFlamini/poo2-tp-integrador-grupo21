package tpIntegradorTest;
import tpIntegradorTest.UbicacionTestCase;
import tpIntegradorTest.UsuarioTestCase;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import tpIntegrador.Ubicacion;
import tpIntegrador.ZonaDeCobertura;
import tpIntegrador.Opinion;
import tpIntegrador.enums.TipoDeOpinion;
import tpIntegrador.Muestra;
import tpIntegrador.usuario.Usuario;


public class MuestraTestCase {

    Usuario tito = mock(Usuario.class);
    Usuario manuel = mock(Usuario.class);
    Usuario jorge = mock(Usuario.class);
    Ubicacion sarandi = mock(Ubicacion.class);
	Ubicacion wilde =  mock(Ubicacion.class);
    TipoDeOpinion vinchucaInfestans = TipoDeOpinion.VINCHUCA_INFESTANS;
    TipoDeOpinion ninguna = TipoDeOpinion.NINGUNA;
    TipoDeOpinion imagenPocoClara = TipoDeOpinion.IMAGEN_POCO_CLARA;
    TipoDeOpinion chincheFolida = TipoDeOpinion.CHINCHE_FOLIADA;  
    Opinion opinion1 = mock(Opinion.class);
    Opinion opinion2 = mock(Opinion.class);
    Opinion opinion3 = mock(Opinion.class);
    Opinion opinion4 = mock(Opinion.class);
    
    
    Muestra muestra1 = new Muestra(sarandi, vinchucaInfestans ,jorge);
    Muestra muestra2 = new Muestra(wilde, ninguna, tito);
   
   
    Usuario pepe= mock(Usuario.class);
    Usuario juan= mock(Usuario.class);
    Ubicacion quilmes = mock(Ubicacion.class);
    Ubicacion avellaneda = mock(Ubicacion.class);


    @BeforeEach
	public void setUp() throws Exception {
    	//USUARIOS
    	when(tito.getNombre()).thenReturn("Tito");
    	when(tito.getCategoria()).thenReturn("Usuario Experto");
    	when(manuel.getNombre()).thenReturn("Manuel");
    	when(manuel.getCategoria()).thenReturn("UsuarioExperto");
    	
    	//OPINIONES
    	//opinion1
    	when(opinion1.getUsuario()).thenReturn(tito);
    	when(opinion1.esOpinionDeExperto()).thenReturn(true);
    	when(opinion1.getTipo()).thenReturn(chincheFolida);
    	when(opinion1.getNombreDeUsuario()).thenReturn("Tito");
    	when(opinion1.getFecha()).thenReturn(LocalDate.of(2030, 6, 6));
    	
    	//opinion2
    	when(opinion2.getUsuario()).thenReturn(manuel);
    	when(opinion2.esOpinionDeExperto()).thenReturn(true);
    	when(opinion2.getTipo()).thenReturn(chincheFolida);
    	when(opinion2.getNombreDeUsuario()).thenReturn("Manuel");

    	
    	//MUESTRAS
		muestra1 = new Muestra(sarandi, vinchucaInfestans, jorge);
		muestra2 = new Muestra(wilde, ninguna, tito);
	}
    
    @Test
    void unaMuestraTieneUnCreador() {
    	assertEquals(muestra1.getUsuarioCreadorDeMuestra(), jorge);
    }
    
    @Test
    void unaMuestraTieneFechaDeCreacionYUbicacion() {
    	LocalDate fechaActual = LocalDate.now();
    	assertEquals(muestra1.getFechaDeCreacion(), fechaActual);
    	assertEquals(muestra1.getUbicacion(), sarandi);
    }
    
    @Test
    void unaMuestraAgregaUnaOpinion() {
    	
        muestra1.agregarOpinion(opinion1);
        
        
        assertEquals(2, muestra1.getOpiniones().size());
        assertTrue(muestra1.getOpiniones().contains(opinion1));
        assertThrows(IllegalArgumentException.class, () -> {
			 muestra1.agregarOpinion(opinion1);
	        });
    }

    @Test
    void cuandoUnaMuestraRecibeDosOpinionesDeExpertoSeVerifica() {
        assertFalse(muestra1.esMuestraVerificada());
    	muestra1.agregarOpinion(opinion1);
    	assertTrue(muestra1.hayOpinionesDeExpertos());
    	muestra1.agregarOpinion(opinion2);
    	assertTrue(muestra1.esMuestraVerificada());
    }

    @Test
    void sePuedeSaberElNivelDeValidacionDeUnaMuestra() {
    	muestra1.agregarOpinion(opinion1);
    	assertEquals(muestra1.getNivelDeValidacion(), "Votada");
    	muestra1.agregarOpinion(opinion2);
    	assertEquals(muestra1.getNivelDeValidacion(), "Verificada");
    }
    
    @Test
    void sePuedeConocerLaOpinionMasRecienteEnUnaMuestra() {
    	muestra1.agregarOpinion(opinion1);
    	assertEquals(muestra1.laOpinionMasReciente(), opinion1);
    }
    
    @Test
    void sePuedenConocerTodasLasOpinionesDeUnaMuestra() {
    	muestra1.agregarOpinion(opinion1);
    	assertEquals(muestra1.getTiposDeOpinion().size(), 2);
    	assertTrue(muestra1.getTiposDeOpinion().contains(opinion1.getTipo()));
    }
    
    @Test
    void sePuedeSaberEnTodoMomentoElResultadoActualDeUnaMuestra() {
    	assertEquals(muestra1.resultadoActual(), vinchucaInfestans); //el resultado acá sería la opinion del que subió la muestra, dado que es la única
    	muestra1.agregarOpinion(opinion1);
    	muestra1.agregarOpinion(opinion2);
    	assertEquals(muestra1.resultadoActual(), chincheFolida); //el resultado cambia a chinche foliada, dado que hay dos opiniones con ese tipo
    }
    
    @Test
    void unaMuestraVerificadaNoPuedeRecibirMasOpiniones() {
    	muestra1.agregarOpinion(opinion1);
    	muestra1.agregarOpinion(opinion2);
    	assertTrue(muestra1.esMuestraVerificada());
    	assertThrows(IllegalArgumentException.class, () -> { 
			 muestra1.agregarOpinion(opinion3);
	        });
    }
    
    @Test
    void cuandoUnaMuestraSeVerificaLeAvisaASuZonaDeCobertura() {
    	ZonaDeCobertura zona1 = mock(ZonaDeCobertura.class);
    	muestra1.agregarObserver(zona1);
    	muestra1.agregarOpinion(opinion1);
    	muestra1.agregarOpinion(opinion2);
    	verify(zona1, times(1)).muestraFueVerificada(muestra1);
    }
}
