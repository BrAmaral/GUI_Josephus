package Josephus; 

/**
 * Contem assinaturas do metodos da TAD Lista Duplamente Ligada
 * 
 * Autores: Breno Amaral, Gabrielle Ramos & Victor Bulh�es
 * @version 20190520
 */
public interface IListaDuplamenteLigada{
    public boolean estaVazia(); 
    
    public void inserirInicio(Object novo); 

    public void inserirFim(Object novo);
    
    public boolean inserirApos(int chave, Object novo);

    public Object removerInicio();

    public Object removerFim();
    
    public Object removerPelaChave(int chave);
}
