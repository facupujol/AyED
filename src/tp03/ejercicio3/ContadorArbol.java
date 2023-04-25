package tp03.ejercicio3;
import tp03.ejercicio1.*;
import tp02.ejercicio2.*;

public class ContadorArbol {
	private ArbolBinario<Integer> arbol;

	public ListaEnlazadaGenerica<Integer> numerosParesA() {
		ListaEnlazadaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		inOrden(arbol, lista);
		return lista;
	}
	
	public ListaEnlazadaGenerica<Integer> numerosParesB() {
		ListaEnlazadaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		postOrden(arbol, lista);
		return lista;
	}
	
	
	private void inOrden(ArbolBinario<Integer> arbol, ListaEnlazadaGenerica<Integer> lista) {
		if (arbol.tieneHijoIzquierdo()) {
			inOrden(arbol.getHijoIzquierdo(), lista);
		}
		if (arbol.getDato()%2 == 0) {
			lista.agregarFinal(arbol.getDato());
		}
		if (arbol.tieneHijoDerecho()) {
			inOrden(arbol.getHijoDerecho(), lista);
		}
	}
	
	private void postOrden(ArbolBinario<Integer> arbol, ListaEnlazadaGenerica<Integer> lista) {
		if (arbol.tieneHijoIzquierdo()) {
			inOrden(arbol.getHijoIzquierdo(), lista);
		}
		if (arbol.tieneHijoDerecho()) {
			inOrden(arbol.getHijoDerecho(), lista);
		}
		if (arbol.getDato()%2 == 0) {
			lista.agregarFinal(arbol.getDato());
		}
	}

	
}
