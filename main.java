package tp_paa;

import java.io.FileNotFoundException;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Grafo grafo = new Grafo("teste1.txt");
		System.out.println("PASSOU");
		grafo.imprimeGrafo();

	}

}
