package practicaParcialArb;
import tp02.ejercicio2.ListaGenerica;
import tp03.ejercicio1.ArbolBinario;

public class Resultado {
	
	// Ejercicio retornar datos pares
	int cantPares;
	ListaGenerica<ArbolBinario<Integer>> arbolesQueCumplen;
	
	//Ejercicio retornar nodo a mayor profundidad
	
	int nivelMaximo;
	int valorARetornar;
	
	public int getCantPares() {
		return cantPares;
	}
	public void setCantPares(int cantPares) {
		this.cantPares = cantPares;
	}
	public ListaGenerica<ArbolBinario<Integer>> getArbolesQueCumplen() {
		return arbolesQueCumplen;
	}
	public void setArbolesQueCumplen(ListaGenerica<ArbolBinario<Integer>> arbolesQueCumplen) {
		this.arbolesQueCumplen = arbolesQueCumplen;
	}
	public int getNivelMaximo() {
		return nivelMaximo;
	}
	public void setNivelMaximo(int nivelMaximo) {
		this.nivelMaximo = nivelMaximo;
	}
	public int getValorARetornar() {
		return valorARetornar;
	}
	public void setValorARetornar(int valorARetornar) {
		this.valorARetornar = valorARetornar;
	}

	
	
}
