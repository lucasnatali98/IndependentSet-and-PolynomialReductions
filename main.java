package tp_paa;

import java.io.FileNotFoundException;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Grafo grafo = new Grafo("testeSat.txt");
		
		//Variáveis para fazer a medida do tempo
		long inicio = System.currentTimeMillis();
		long fim = System.currentTimeMillis();  
		
		
		grafo.leSat("testeSat.txt");
		System.out.println();
	
		/*
		int[] conjunto = grafo.conjuntoIndependente();
		int[] clique = grafo.clique();
		
		for(int i=0; i<clique.length; i++)
		{
			System.out.print(clique[i] + "|");
		}
		
		System.out.println();
		for(int i = 0; i<conjunto.length; i++)
			System.out.print(conjunto[i]+ "|");
			
		 */
	}
}
