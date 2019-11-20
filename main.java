package tp_paa;

import java.io.FileNotFoundException;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Grafo grafo = new Grafo("teste1.txt");
		
		grafo.imprimeGrafo();
		System.out.println();
		
		int[] conjunto = grafo.conjuntoIndependente();
		for(int i = 0; i<conjunto.length; i++)
			System.out.print(conjunto[i]+ "|");
	}
}
