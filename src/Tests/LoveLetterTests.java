package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import cartas.*;
import jugador.*;
import sala.*;

class LoveLetterTests {

	// TEST DE MECANICAS DE LA SALA
	
	@Test
	void cantidadDeCartasEnMazoTest() {
		Mazo mazo = new Mazo();
		assertEquals(16, mazo.size());
	}

	@Test
	void mezclarMazoNoAlteraCantidadDeCadaTipoSeCartaTest() {
		Mazo mazo = new Mazo();
		mazo.mezclar();
		assertEquals(16, mazo.size());
		HashMap<String, Integer> conteo = new HashMap<String, Integer>();
		while (mazo.size() > 0) {
			String cartaObtenida = mazo.getCarta().getNombre();
			conteo.put(cartaObtenida, conteo.getOrDefault(cartaObtenida, 0) + 1);
		}
		assertEquals(conteo.get("Baron"), 2);
		assertEquals(conteo.get("Guardia"), 5);
		assertEquals(conteo.get("Sacerdote"), 2);
		assertEquals(conteo.get("Mucama"), 2);
		assertEquals(conteo.get("Principe"), 2);
		assertEquals(conteo.get("Rey"), 1);
		assertEquals(conteo.get("Condesa"), 1);
		assertEquals(conteo.get("Princesa"), 1);
	}
	
	@Test
	void todosLosJugadoresSeUnenCorrectamente() {
		Jugador j1 = new Jugador("Carlos");
		Jugador j2 = new Jugador("Roberto");
		Jugador j3 = new Jugador("Samanta");
		
		Sala sala = new Sala ( j1 , 3);	
		j2.unirseASala(sala);
		j3.unirseASala(sala);
		
		assertEquals( 3, sala.jugadoresEnRonda());		
		
	}
	
	@Test
	void ganadorRondaTodosDescalificados() {
		Jugador j1 = new Jugador("Carlos");
		Jugador j2 = new Jugador("Roberto");
		Jugador j3 = new Jugador("Samanta");
		
		Sala sala = new Sala ( j1 , 3);	
		j2.unirseASala(sala);
		j3.unirseASala(sala);
		
		j1.setCarta(new Guardia());
		j2.setCarta(new Principe());
		j3.setCarta(new Princesa());
		/* La princesa ganaria por un mayor valor de carta
		 * pero el jugador esta descalificado.
		 */
		
		j1.salirDeRonda();
		j3.salirDeRonda();
		
		Jugador ganador = sala.buscarGanadorRonda();
		
		assertEquals( j2.getNombre(), ganador.getNombre());		
		
	}
	

	// TEST PARA JUGAR CARTAS DE DIFERENTE TIPO
	
	@Test
	void jugarPrincesaTest() {
		Jugador player = new Jugador("Carlos");
		player.setCarta(new Princesa());
		assertEquals(player.juegaRonda(), true);
		player.getCarta().descartar(player);
		;
		assertEquals(player.juegaRonda(), false);
	}

	@Test
	void jugarMucamaTest() {
		Jugador player = new Jugador("Carlos");
		Sala sala=new Sala(player,5);
		sala.generarMazo();
		player.setCarta(new Mucama());
		assertEquals(player.isProtegido(), false);
		player.getCarta().descartar(player);
		assertEquals(player.isProtegido(), true);
		player.setCarta(new Condesa());
		//player.jugar();
		//assertEquals(player.isProtegido(), false);	//queria probar que al jugar el turno vuelve a estar desprotegido, le puse una condesa para que a veces tire sin preguntar.
	}
	
	@Test
	void jugarBaronQuedoAfueraTest() {
		Jugador player1 = new Jugador("Carlos");
		Jugador player2 = new Jugador("Roberto");
		Baron baron=new Baron();
		player1.setCarta(new Condesa());
		player2.setCarta(new Guardia());
		baron.eliminarCorrespondiente(player1, player2);
		assertEquals(player1.juegaRonda(), true);
		assertEquals(player2.juegaRonda(), false);
	}
	
	@Test
	void jugarBaronQuedaAfueraTest() {
		Jugador player1 = new Jugador("Carlos");
		Jugador player2 = new Jugador("Roberto");
		Baron baron=new Baron();
		player1.setCarta(new Mucama());
		player2.setCarta(new Sacerdote());
		baron.eliminarCorrespondiente(player1, player2);
		assertEquals(player1.juegaRonda(), true);
		assertEquals(player2.juegaRonda(), false);
	}
	
	@Test
	void jugarGuardiaAdivinoTest() {
		Jugador player1 = new Jugador("Carlos");
		Jugador player2 = new Jugador("Roberto");
		player1.setCarta(new Guardia());
		player2.setCarta(new Sacerdote());
		((Guardia)player1.getCarta()).verificarCarta(player2, "Sacerdote");
		assertEquals(player1.juegaRonda(), true);
		assertEquals(player2.juegaRonda(), false);
	}
	
	@Test
	void jugarGuardiaFalloTest() {
		Jugador player1 = new Jugador("Carlos");
		Jugador player2 = new Jugador("Roberto");
		player1.setCarta(new Guardia());
		player2.setCarta(new Princesa());
		((Guardia)player1.getCarta()).verificarCarta(player2, "Sacerdote");
		assertEquals(player1.juegaRonda(), true);
		assertEquals(player2.juegaRonda(), true);
	}
	
	@Test
	void jugarSacerdoteTest() {
		Jugador player1 = new Jugador("Carlos");
		Jugador player2 = new Jugador("Roberto");
		player1.setCarta(new Sacerdote());
		player2.setCarta(new Princesa());
		assertEquals(((Sacerdote)player1.getCarta()).obtenerCarta(player2),"Princesa");
		player2.setCarta(new Guardia());
		assertEquals(((Sacerdote)player1.getCarta()).obtenerCarta(player2),"Guardia");
	}
	
	@Test
	void jugarPrincipeConPrincesaTest(){
		Jugador player1 = new Jugador("Carlos");
		Jugador player2 = new Jugador("Roberto");
		Sala sala=new Sala(player1,5);
		player2.unirseASala(sala);
		sala.generarMazo();
		player1.setCarta(new Principe());
		player2.setCarta(new Princesa());
		Carta cartaPlayer2=player2.getCarta();
		assertEquals(player2.juegaRonda(),true);
		((Principe)player1.getCarta()).cambiarCarta(player2);
		assertEquals(player2.juegaRonda(),false);
		assertEquals(cartaPlayer2!=player2.getCarta(),true);
	}
	
	@Test
	void jugarPrincipeSinPrincesaTest(){
		Jugador player1 = new Jugador("Carlos");
		Jugador player2 = new Jugador("Roberto");
		Sala sala=new Sala(player1,5);
		player2.unirseASala(sala);
		sala.generarMazo();
		player1.setCarta(new Principe());
		player2.setCarta(new Mucama());
		Carta cartaPlayer2=player2.getCarta();
		assertEquals(player2.juegaRonda(),true);
		((Principe)player1.getCarta()).cambiarCarta(player2);
		assertEquals(player2.juegaRonda(),true);
		assertEquals(cartaPlayer2!=player2.getCarta(),true);
	}
	
	@Test
	void jugarReyTest(){
		Jugador player1 = new Jugador("Carlos");
		Jugador player2 = new Jugador("Roberto");
		Sala sala=new Sala(player1,5);
		player2.unirseASala(sala);
		sala.generarMazo();
		player1.setCarta(new Guardia());
		player2.setCarta(new Princesa());
		Carta cartaPlayer1=player1.getCarta();
		Carta cartaPlayer2=player2.getCarta();
		new Rey().hacerIntercambio(player1, player2);
		assertEquals(cartaPlayer1==player2.getCarta(),true);
		assertEquals(cartaPlayer2==player1.getCarta(),true);
	}
	
}
