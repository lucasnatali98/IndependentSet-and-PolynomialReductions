package tp_paa;
import java.io.*;

public class Grafo {
	private int [] nAdjacencias;
    private int vertices;
    private int contadorLinha;
    private int contadorColuna;
    private int tamanho;
    public int [][] arestas;
    public int [][] matrizSat;
    public int [][] matrizAux;
    
    
    public Grafo(String nomeDoArquivo)
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
    public void leGrafo(String nomeDoArquivo)
    {
    	System.out.println("Entrou 1");
    	try {
    		 BufferedReader br = new BufferedReader(new FileReader("teste1.txt"));
    		 System.out.println("entrou 2 ");
    		 arestas = new int[vertices][vertices];
    		 
    		this.vertices = Integer.parseInt(br.readLine()); //Rolou.
    		 
    		
    		
    		 System.out.println("vertices:" + getVertices());
    		 while(br.ready())
    		 {
    			 int [] linhas = new int[getVertices()];
    			
    			
    			for(int i=0; i<getVertices(); i++)
    			{
    				linhas[i] = br.read();
    				this.arestas[i] = linhas;
    				
    			}
    				
    		 }
    		 
    		 br.close();
    	}
    	catch(IOException ioe) {
    		System.out.println("PAOOO");
    		ioe.printStackTrace();
    	}
    	
    }
    public int[] listaAdjacencias(int coluna)
    {
        return this.arestas[coluna];
    }
    
    public int[] conjuntoIndependente()
    {
        int []conjunto = new int[vertices];
        
        for(int i=0; i<getVertices(); i++) conjunto[i] = 1;
  
        int melhor = 0;
        
        for(int j=0; j<getVertices(); j++)
        {
            int [] proposta = new int[vertices];
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
    	
        System.out.println("VALOR DOS VERTICES: " + getVertices());
        for(int i=0; i<getVertices(); i++)
        {
            for(int j=0; j<getVertices(); j++)
            {
                System.out.println(this.arestas[i][j]);
            }
            System.out.println();
        }
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

