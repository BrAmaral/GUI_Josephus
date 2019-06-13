package Josephus;
// Autores: Breno Amaral, Gabrielle Ramos & Victor Bulh�es

public class Josephus {
	public FilaCircular grupo;
	//public No primeiro;
	//public No ultimo;
	public int qtdPessoas;
	public int next;
	
	public Josephus(int qtdPessoas, int next) {
		grupo = new FilaCircular();
		setPessoas(qtdPessoas);
		setProximoMorto(next);
	}
	
	public void setProximoMorto(int next) {
		this.next = next;
	}
	
	
	public void setPessoas(int qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
	}
	
	public int getProximoMorto() {
		return(next);
	}
	
	public int getPessoas() {
		return (qtdPessoas);
	}
	
	public FilaCircular getGrupo(){
		return (grupo);
	}
	
	public No getPrimeiro() {
		return(getGrupo().fila.getInicio());
	}
	
	public No getUltimo() {
		return(getGrupo().fila.getFim());
	}
	
	
	public boolean checarVivo() { 
		// Retorna true se o elemento est� vivo
		boolean vivo;
		No aux = grupo.getAtual();
		Object conteudo = aux.getConteudo();
		Integer auxiliar = (Integer) conteudo;
		
		if(auxiliar < 0) {
			vivo = false;
		} else {
			vivo = true;
		}
		
		return vivo;
	}
	
	public void mostrarVivos(int vivos) {
		int i = 0; // Vari�vel para loop
		No aux = getPrimeiro();
		Object conteudo = aux.getConteudo();
		Integer auxiliar;
		
		System.out.print("[");
		
		while(i < vivos) {
			auxiliar = (Integer) conteudo;
			if(auxiliar > 0) {
				System.out.print(" " + aux.getConteudo());
				aux = aux.getProximo();
			} else {
				aux = aux.getProximo();
			}
			
			conteudo = aux.getConteudo();
			i++;
		}
		
		System.out.println(" ]");
		
	}
	
	public void criarCenario() {
		int i; // Vari�vel para o loop de preenchimento dos membros a serem mortos
		int pessoa = getPessoas();
		
		for(i = 1; i <= pessoa; i++) {
			grupo.fila.inserirFim(i); // Insere i no conte�do do N� atual
                        
		}
		
		grupo.fecharFilaCircular(grupo.fila);; // Faz a lista virara uma fila circular
		grupo.setAtual(grupo.fila.getInicio()); // Aponta o atual para o primeiro da fila circular
                
                
	}
	
        public String ordemDeFalecimento(String morte, No atual){
		
		Object conteudo = atual.getConteudo();
		String aux =  conteudo.toString();
		
		morte = (morte + aux + " ");
		
		return morte;
	}

        
	public void eliminar(No atual) {
		int aux1 = -1;
		Object conteudo = atual.getConteudo();
		Integer aux2 = (Integer) conteudo;
		
		aux1 = aux1 * aux2;
		
		atual.setConteudo(aux1);
	}
	
	public Object loopJosephus() {// Atribuir ao conteúdo dos mortos null
		//No matador = grupo.getAtual();
		String mortos = ("Ordem de morte: ");
		boolean vivo = true; // Variável que determina o estado de vida do elemento
		boolean end = false; // Variável que determina se há apenas uma pessoa viva
		Object conteudo;
		Integer conteudoObjeto;
		int qtdVivos = getPessoas();
		int vivosInicial = getPessoas();
		//int qtdMortos = 0; // Contagem de mortos
		int proximos = getProximoMorto(); // proximos serve de controle para quantas pessoas o matador ignora
		int i = 1; // Variável de loop para encontrar o alvo
		
		mostrarVivos(qtdVivos);
		
		while(end == false){ // Enquanto mais de uma pessoa está viva
			
			while(i < proximos) { // Loop para chegar no alvo
				
				grupo.setAtual(grupo.getAtual().getProximo());
				vivo = checarVivo();
				
				//if((vivo == true) && (grupo.getAtual().getConteudo() != matador.getConteudo())) {
				if(vivo == true){
					i = i + 1;
				} else {
					// vazio
				}
			}
			i = 1;
			
			mortos = ordemDeFalecimento(mortos, grupo.getAtual());
			eliminar(grupo.getAtual()); // Mata o elemento atual
			
			qtdVivos--;
			
			mostrarVivos(vivosInicial);
			
			grupo.setAtual(grupo.getAtual().getProximo());; // O atual se torna o próximo elemento
			
			conteudo = grupo.getAtual().getConteudo();
			
			conteudoObjeto = (Integer) conteudo;
			
			while(conteudoObjeto < 0) { // Procura o próximo elemento vivo para se tornar o matador
				grupo.setAtual(grupo.getAtual().getProximo());
				conteudo = grupo.getAtual().getConteudo();
				conteudoObjeto = (Integer) conteudo;
			}
			
			//matador = grupo.getAtual();
			
			if(qtdVivos == 1) {
				end = true;
                                
			} else {
				end = false;
			}
		}
		
		System.out.println(mortos);
                
		return grupo.getAtual().getConteudo();
	}
}
