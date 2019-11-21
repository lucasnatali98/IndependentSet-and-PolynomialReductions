package tp_paa;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Grafo {
	private int [] nAdjacencias;
    private int vertices;
    private int contadorLinha;
    private int contadorColuna;
    private int tamanho;
    public int [][] arestas;
    public int [][] matrizSat;
    public int [][] matrizAux;
    
    
    public Grafo(String nomeDoArquivo) throws FileNotFoundException
    {
        if(nomeDoArquivo != "")
        {
            leGrafo(nomeDoArquivo);
        }
        else
        {
            this.vertices = 0;
            this.arestas = null;
            this.nAdjacencias = null;
            
        }
        
    }
    public void leGrafo(String nomeDoArquivo) throws FileNotFoundException
    {
    	Scanner leitura = new Scanner(new File(nomeDoArquivo));
    	int linha=0,coluna=0;
    	this.vertices = leitura.nextInt();
	    System.out.println(getVertices());
	    this.arestas = new int[12][getVertices()];
	      
	    while (leitura.hasNext()) {
	    	int aux = leitura.nextInt();
	    	this.arestas[linha][coluna] = aux;
	    	coluna++;
	    	if(coluna == getVertices())
	    	{
    		  coluna = 0;
    		  linha++;
	    	}     	    	
	    }
	    leitura.close();
	    setContadorLinha(linha);
	   
	    nAdjacencias = new int[getVertices()];
	    for (int i = 0; i < getVertices(); i++) {
			nAdjacencias[i] = 0;
		}
	    
	    int cont;
	    for(int i=0; i< getVertices(); i++ ){
	        int[] adjacencias = listaAdjacencias(i);        // recebe a lista de adjacencias do vertice
	        cont =0;
	        for(int j=0; j< getVertices(); j++){
	            if (adjacencias[i] == 1)                          // conta o numero de vizinhos do vertice
	                cont ++;
	        }
	        nAdjacencias[i]=cont;
	    }
    		
    }
    	
    	
    
    public int[] listaAdjacencias(int coluna)
    {
        return this.arestas[coluna];
    }
    
    public int[] conjuntoIndependente()
    {
        int []conjunto = new int[getVertices()];
        
        for(int i=0; i<getVertices(); i++) conjunto[i] = 1;
  
        int melhor = 0;
        
        for(int j=0; j<getVertices(); j++)
        {
            int [] proposta = new int[getVertices()];
            for(int k=0; k< getVertices(); k++)
            {
                proposta[k] = 0;
            }
            proposta[j] = 1;
            conjunto = auxConjuntoIndependente(conjunto, melhor,proposta,j);
            melhor = 0;
            for(int k=0; k < getVertices(); k++)
            {
                if(conjunto[k] == 1)
                {
                    melhor += 1;
                }
            }
           
        }
        
        return conjunto;   
    }
    
    public int[] auxConjuntoIndependente(int[] melhorConjunto, int melhorTamanho, int [] proposta,
            int vertice)
    {
        int [] adjacencia = listaAdjacencias(vertice);
        for(int i=0; i<getVertices(); i++)
        {
            if(proposta[i] != 1)
            {
                proposta[i] = 1;
                if(eConsistente(proposta))
                {
                    proposta = auxConjuntoIndependente(melhorConjunto, melhorTamanho, proposta, i);
                }
                else{
                    proposta[i] = 0;
                }
            }
            if(ePromissor(proposta,melhorTamanho)){                                                         // ao final das propostas, se ela for promissora
                melhorConjunto = proposta;                                                                  // assume como melhor conjunto
                melhorTamanho=0;
                for(int contador = 0; contador < vertice; contador ++)                                      // e conta seu tamanho
                    if(melhorConjunto[contador] == 1)
                        melhorTamanho+=1;
        }
        
     
    }
        return melhorConjunto;   
    }
    
    public void complemento(){
        for (int i = 0; i < vertices; i++)
            for (int j = 0; j < vertices; j++){
                if(arestas[i][j]==1)
                    arestas[i][j]=0;
                else if( i!=j )
                    arestas[i][j]=1;
            }
        

    for(int i=0; i < vertices; i++)
        nAdjacencias[i] = vertices - nAdjacencias[i];
    }
    
    public boolean eClique(int[] solucao){
        for(int i=0; i<vertices; i++){
            if(solucao[i]==1){
                int[] adjacencais=listaAdjacencias(i);
                for(int j=0; j < vertices; j++)
                    if(i != j && solucao[j] != adjacencais[j])
                        return false;
            }
        }
    return true;
    }   

    public int[] clique(){
        complemento();
        int[] clique = conjuntoIndependente();
        complemento();

        if(!eClique(clique))
            for(int i=0; i< vertices; i++)  clique[i]=0;

        return clique;
    }

    public boolean eConsistente(int[] solucao){
    for(int i=0; i < vertices; i++){
        if (solucao[i]==1){
            int[] vizinhos=listaAdjacencias(i);
            for(int j=0; j < vertices; j++)
                if (vizinhos[j]==1 && solucao[j] == 1 )
                    return false;
        }
    }
    return true;
    }

    public boolean ePromissor(int []solucao, int melhor){
    int contador=0;
    for(int i=0; i < vertices; i++)
        if(solucao[i] == 1)
            contador++;
    return contador > melhor;
    }
    
    public void imprimeGrafo()
    {       
        for(int i=0; i<getContadorLinha(); i++)
        {
        	System.out.println();
            for(int j=0; j<getVertices(); j++)
            {
                System.out.print(this.arestas[i][j]+ " | ");
            }
        }
    }

    public void leSat(String nomeDoArquivo) throws FileNotFoundException
    {
    	String fimDeLinha;
    	Scanner leitura = new Scanner(new File(nomeDoArquivo));
    	
    	this.contadorLinha = 0;
    	int v = leitura.nextInt();
    	
    	System.out.println("Numero de vertices (SAT): ");
    	while(leitura.hasNext()) {
    		
    		this.contadorLinha++;
    			
    	}
    	
    	leitura.close();
    	
    	System.out.println("ContadorLinha: "+getContadorLinha());//Pegando TODAS linhas
    	//Alocando memória para as matrizes
    	this.matrizSat = new int[getContadorLinha()-2][getVertices()];
    	this.matrizAux = new int[getContadorLinha()-2][getVertices()];
    	
    	
    	//Preenchendo a matriz com zeros
    	for (int i = 0; i < getContadorLinha()-1; i++) {
			for (int j = 0; j < arestas.length; j++) {
				
			}
		}
    }
    public void imprimeSat()
    {
    	for (int i = 0; i < getTamanho(); i++) {
    		System.out.println();
			for (int j = 0; j < getTamanho(); j++) {
				System.out.print(this.arestas[i][j] + " | ");
			}
		}
    }
    public int[] satisfatibilidade()
    {
    	// Faz as ligações das variáveis(vértices) pertencentes a mesma clausula 
        this.tamanho = 0;
        int contador = 0, aux = 0;
        int i = 0, j = 0, k = 0, m = 0;

        for(i = 0; i < contadorLinha; i++){     // Pega o total de variaveis diferentes de 2
            for(j = 0; j < vertices; j++)
                if(matrizSat[i][j] != 2)
                    tamanho++;
        }
        arestas = new int[tamanho][tamanho]; // Cria uma matriz de tamanho = tamanho * tamanho
        for(i=0; i < tamanho; i++)
            arestas[i] = new int[tamanho];

        for(i = 0; i < tamanho; i++)            // Preenche a matriz criada com zeros
            for(j = 0; j < tamanho; j++)
                arestas[i][j] = 0;

        for(i = 0; i < contadorLinha; i++){     // Percorre a matriz de entrada do SAT
            for(j = 0; j < vertices; j++){
                if(matrizSat[i][j] != 2)          // Conta todos os valores da matriz diferentes de 2 na mesma linha 
                    contador++;

                for(k = 0; k < contador; k++)
                    for(m = 0; m < contador; m++){
                        if(k != m)
                            arestas[k+aux][m+aux] = 1;    // Se não atribui valor 1 (ligação de variaveis da mesma clausula)
                    }
            }
            aux += contador;
            contador = 0;
        }

        // Preenche matriz auxiliar
        int qtd = 0;

        for(int x= 0; x < getContadorLinha(); x++){
            for(int y = 0; y < getVertices(); y++){
                if (matrizSat[x][y] == 2)
                {
                    matrizAux[x][y] = -1;
                }else{
                    matrizAux[x][y] = qtd;
                    qtd++;
                }
            }
        }

        // Faz as ligações da mesma variavél(vértice) negada e não negada
        i = 0;
        j = 0;
        aux = 1;
        int numCol = 1;

        while (numCol != vertices+1)
        {
            while (i != contadorLinha-1)
            {
                if (matrizSat[i][numCol-1] != 2 ){
                    if (matrizSat[aux][numCol-1] != 2)
                    {
                        if (matrizSat[aux][numCol-1] != matrizSat[i][numCol-1] )
                        {
                            //ligacao no grafo grande
                            int numeroColuna = matrizAux[i][numCol-1];
                            int numeroLinha =  matrizAux[aux][numCol-1];

                            arestas[numeroColuna][numeroLinha] = 1;
                            arestas[numeroLinha][numeroColuna] = 1;
                        }
                    }
                }

                aux++;

                if (aux == contadorLinha ){
                    i++;
                    aux = i+1;
                }
            }
            i=0;
            j++;
            aux=1;
            numCol++;
        }

        int ponte = vertices;
        vertices = tamanho;

        int[] conjuntoIndependente = this.conjuntoIndependente();

        j=0;
        for(i=0; i < vertices; i++)
            if(conjuntoIndependente[i]==1)
                j++;

        if(j==contadorLinha){
            System.out.println("Possui");
        }
        else{
            System.out.println("Nao possui");
            for (i=0;i < getVertices(); i++)
            {
                conjuntoIndependente[i]=0;
            }
        }
        System.out.println("solucao que satisfaca!");
        vertices = ponte;
        return conjuntoIndependente;
    	
    }
    public int[] getnAdjacencias() {
        return nAdjacencias;
    }


    public int getVertices() {
        return vertices;
    }

    public void setVertices(int vertices) {
        this.vertices = vertices;
    }

    public int getContadorLinha() {
        return contadorLinha;
    }

    public void setContadorLinha(int contadorLinha) {
        this.contadorLinha = contadorLinha;
    }

    public int getContadorColuna() {
        return contadorColuna;
    }

    public void setContadorColuna(int contadorColuna) {
        this.contadorColuna = contadorColuna;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
}

