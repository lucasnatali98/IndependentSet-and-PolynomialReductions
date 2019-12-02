package tp_paa;

import java.io.FileNotFoundException;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Grafo grafo = new Grafo("teste1.txt");
		Grafo grafo1 = new Grafo("ConjuntoIndTeste1.txt");
		Grafo grafo2 = new Grafo("ConjuntoIndTeste2.txt");
		
		
		
		

		
		
		System.out.println("Instâncias do Conjunto Independente");
		//Primeira instância do C.I
		long inicio = System.nanoTime();
		int[] conjunto = grafo.conjuntoIndependente();
		System.out.println();
		for(int i = 0; i<conjunto.length; i++)
			System.out.print(conjunto[i]+ "|");
		System.out.println();
		conjunto = null;
		long fim = System.nanoTime();
		System.out.println("Tempo de execução - C.I: " + (fim -inicio) + "ns");
		System.out.println();
		
		//Segunda instância do C.I
		long inicio2 = System.nanoTime();
		conjunto = grafo1.conjuntoIndependente();
		for (int i = 0; i < conjunto.length; i++) {
			System.out.print(conjunto[i] + "|");
		}
		System.out.println();
		long fim2 = System.nanoTime();
		System.out.println("Tempo de execução - C.I (2): " + (fim2 -inicio2) + "ns");
		conjunto = null;
		System.out.println();
		
		long inicio3 = System.nanoTime();
		conjunto = grafo2.conjuntoIndependente();
		for (int i = 0; i < conjunto.length; i++) {
			System.out.print(conjunto[i] + "|");
		}
		System.out.println();
		long fim3 = System.nanoTime();
		System.out.println("Tempo de execução - C.I (3): " + (fim3-inicio3) + "ns");
		conjunto = null;
		System.out.println();
		
		System.out.println("Instâncias do clique");
		
		inicio = System.nanoTime();
		int[] clique = grafo.clique();
		for(int i=0; i<clique.length; i++)
		{
			System.out.print(clique[i] + "|");
		}
		fim = System.nanoTime();
		System.out.println();
		System.out.println("Tempo de execução: - Clique: " + (fim - inicio) + "ns");
		System.out.println();
		clique = null;
		
		inicio2 = System.nanoTime();
		clique = grafo1.clique();
		for(int i=0; i<clique.length; i++)
		{
			System.out.print(clique[i] + "|");
		}
		fim2 = System.nanoTime();
		System.out.println();
		System.out.println("Tempo de execução: - Clique (2): " + (fim2 - inicio2) + "ns");
		System.out.println();
		clique = null;
		
		inicio3 = System.nanoTime();
		clique = grafo2.clique();
		for(int i=0; i<clique.length; i++)
		{
			System.out.print(clique[i] + "|");
		}
		fim3 = System.nanoTime();
		System.out.println();
		System.out.println("Tempo de execução: - Clique (3): " + (fim3 - inicio3) + "ns");
		System.out.println();
		clique = null;
	}
}
