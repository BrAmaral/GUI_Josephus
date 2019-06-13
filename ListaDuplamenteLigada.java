package Josephus;
/**
 * classe: ListaDuplamenteLigada
 * 	Implementa a TAD Lista Duplamente Ligada
 * 
 * Autores: Breno Amaral, Gabrielle Ramos & Victor Bulh�es
 * @version 20190520
 */
class ListaDuplamenteLigada implements IListaDuplamenteLigada {
	private No inicio; // ref para primeiro elemento
	private No fim;    // ref para ultimo elemento

	// Setters e Getters
	/**
	 * @return endereco do primeiro elemento da lista
	 */
	public No getInicio() {
		return inicio;
	}
	
	
	/**
	 * @param inicio endereco do primeiro elemento da lista
	 */
	public void setInicio(No inicio) {
		this.inicio = inicio;
	}
	
	/**
	 * @return endereco do ultimo elemento da lista
	 */
	public No getFim() {
		return fim;
	}
	
	/**
	 * @param fim endereco do ultimo elemento da lista
	 */
	public void setFim(No fim) {
		this.fim = fim;
	}

	/**
	 * Construtor da Lista
	 */
	public ListaDuplamenteLigada() {   
		setInicio(null);
		setFim(null);
	}
	
	/**
	 * Verifica se a Lista esta vazia
	 * 
	 * @return true se estiver vazia e false caso contrario
	 * 
	 */
	public boolean estaVazia() {
		return (getInicio() == null && getFim() == null); 
	}

	/**
	 * Insere um novo No no inicio da Lista
	 *  
	 */
	public void inserirInicio(Object elem) {

		// Cria novo no
		No novoNo = new No(elem); 

		// Insere novo no na Lista (atualizando ponteiros)
		if( estaVazia() ) {   // se a lista estiver vazia
			setFim(novoNo);
		} else {
			getInicio().setAnterior(novoNo);
		}
		novoNo.setProximo(getInicio());
		setInicio(novoNo);
	}

	/**
	 * Insere um novo No no finsl da Lista
	 *  
	 */
	public void inserirFim(Object elem)	{
		// Cria novo no
		No novoNo = new No(elem);  

		// Insere novo no na Lista (atualizando ponteiros)
		if( estaVazia() ) { // se a lista estiver vazia
			setInicio(novoNo);
		} else {
			getFim().setProximo(novoNo); 
			novoNo.setAnterior(getFim());
		}
		setFim(novoNo);
	}

	/**
	 * Insere um novo No apos um No encontrado (pela chave)
	 * 
	 * @param chave codigo do elemento a ser encontrado
	 * 
	 * @param elem elemento a ser inserido
	 * 
	 * @return true se o o no foi inserido e false caso contrario
	 * 
	 */
	public boolean inserirApos(int chave, Object elem) {
		No noAtual = getInicio(); 

		// percorre a lista em busca do No
		while((Integer)noAtual.getConteudo() != chave) {
			noAtual = noAtual.getProximo(); 
			if(noAtual == null) { // nao encontrou posicao
				return false;  
			}
		}
		
		// Cria novo no
		No novoNo = new No(elem);

		// Insere novo no na Lista (atualizando ponteiros)
		if(noAtual == getFim()) {
			novoNo.setProximo(null); 
			setFim(novoNo); 
		}
		else {
			novoNo.setProximo(noAtual.getProximo());
			noAtual.getProximo().setAnterior(novoNo);
		}
		novoNo.setAnterior(noAtual);
		noAtual.setProximo(novoNo); 
		
		return true; 
	}

	/**
	 * Remove o primeiro No da Lista
	 * 
	 * @return No removido ou null se a Lista estiver vazia
	 * 
	 */
	public No removerInicio() {
		No temp = null;
		if(getInicio() != null) {
			temp = getInicio();
			if(getInicio().getProximo() == null) {
				setFim(null); 
			} else {
				getInicio().getProximo().setAnterior(null); 
			}
			setInicio(getInicio().getProximo());

			// isola o no removido (remove as referencias para proximo e anterior)
			temp.setProximo(null);
			temp.setAnterior(null);
		}

		// retorna o no removido
		return temp;
	}

	/**
	 * Remove o ultimo No da Lista
	 * 
	 * @return No removido ou null se a Lista estiver vazia
	 * 
	 */
	public No removerFim() {
		No temp = null;
		
		if(getFim() != null) {

			// Guarda o no
			temp = getFim();

			// Acerta todas as referencias (ponteiros)
			if(getInicio().getProximo() == null) {
				setInicio(null); 
			}
			else {
				getFim().getAnterior().setProximo(null);
			}
			setFim(getFim().getAnterior());
			
			// isola o no removido (remove as referencias para proximo e anterior)
			temp.setProximo(null);
			temp.setAnterior(null);
		}
		
		// retorna o no removido
		return temp;
	}


	/**
	 * Remove um No de acordo com uma chave (inteiro)
	 * 
	 * 0param chave numero inteiro para buscar o no a ser removido
	 * 
	 * @return No removido ou null caso nao encontre
	 * 
	 */
	public No removerPelaChave(int chave) {
		No temp = null; // Ponteiro para percorrer a lista
		
		if(getInicio() != null) {
			
			temp = getInicio(); // comeca do nicio

			// Percorre ate encontrar o No, ou retorn null caso nao encontre
			while((Integer)temp.getConteudo() != chave) {
				temp = temp.getProximo(); 
				if(temp == null) {
					return null; 				
				}
			}

			// Acerta todas as referencias (ponteiros)
			if(temp == getInicio()) { // se for inicio
				setInicio(temp.getProximo());
			} else {
				temp.getAnterior().setProximo(temp.getProximo());
			}

			if(temp == getFim()) { // se for fim
				setFim(temp.getAnterior());
			} else {
				temp.getProximo().setAnterior(temp.getAnterior());
			}
			
			// isola o no removido (remove as referencias para proximo e anterior)
			temp.setProximo(null);
			temp.setAnterior(null);
		}
				
		// retorna o no removido		
		return temp;
	}

	/**
	 * Retorna o conteudo da Lista como String (do inicio ate o fim)
	 */
	public String toString() {
		String s = "[ ";
		No noAtual = getInicio();  // inicia do inicio
		while(noAtual != null) {    // enquanto nao for fim da lista,
			s = s + noAtual.toString() + " ";  // monta os dados como string
			noAtual = noAtual.getProximo();   // vai para o proximo
		}
		s = s + "]";

		return s;
	}

	/**
	 * Retorna o conteudo da Lista como String (do fim ate o inicio)
	 */
	public String toStrinDoFim() {
		String s = "[ ";
		No noAtual = getFim();  // inicia no fim

		while(noAtual != null) { // enquanto nao for inicio da lista,
			s = s + noAtual.toString() + " "; // monta os dados como string
			noAtual = noAtual.getAnterior(); // vai para o anterior
		}
		
		s = s + "]";
		
		return s;
	}
}  
