package Josephus;
public class FilaCircular 
{
	public ListaDuplamenteLigada fila;
	public No atual;
	
	public FilaCircular(){
		fila = new ListaDuplamenteLigada();
		fila.setInicio(null);
		fila.setFim(null);
		setAtual(null);
	}
	
	public void setAtual(No atual){
		this.atual = atual;
	}
	
	public No getAtual(){
		return atual;
	}
	
	public void fecharFilaCircular(ListaDuplamenteLigada grupo) { // Faz a fila se tornar um c�rculo ao unir o final da lista com o in�cio
		grupo.getInicio().setAnterior(grupo.getFim());
		grupo.getFim().setProximo(grupo.getInicio());
	}
	
}
