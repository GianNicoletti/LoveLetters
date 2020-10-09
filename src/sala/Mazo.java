package sala;

import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;
import cartas.*;

public class Mazo {

	private LinkedList<Carta> mazo;

	public Mazo() {
		mazo = new LinkedList<Carta>();
		int i;
		for (i = 0; i < 5; i++)
			mazo.add(new Guardia());
		for (i = 0; i < 2; i++)
			mazo.add(new Sacerdote());
		for (i = 0; i < 2; i++)
			mazo.add(new Baron());
		for (i = 0; i < 2; i++)
			mazo.add(new Mucama());
		for (i = 0; i < 2; i++)
			mazo.add(new Principe());
		mazo.add(new Rey());
		mazo.add(new Condesa());
		mazo.add(new Princesa());
	}

	public void mezclar() {

//		Random rand = new Random(System.currentTimeMillis());
//		Carta carta;
//		for (int i = 0; i < 16; i++) {
//			carta = mazo.remove((rand.nextInt(16)));
//			mazo.add(rand.nextInt(16), carta);
//		}

		Collections.shuffle(mazo);

	}

	public void sacarCartaAlAzar() {
		Random rand = new Random(System.currentTimeMillis());
		mazo.remove(rand.nextInt(16));
	}

	public Carta getCarta() {
		return mazo.pop();
	}

	public int size() {
		return mazo.size();
	}
}
