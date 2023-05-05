package practicaParcialArb;

import tp03.ejercicio1.ArbolBinario;
import tp02.ejercicio2.*;



/*
 Devolver una lista con todos los arboles con 2 hijos y dato par Y la cantidad de valores pares del arbol
 */


public class ProcesadorDeArbol {
	
	ArbolBinario<Integer> arbol;
	
	public ProcesadorDeArbol (ArbolBinario<Integer> a) {
		this.arbol = a;
	}
	
	
	/*
	 Retornar una lista con los arboles que tienen dato par y 2 hijos, y tambien la cantidad de nodos con dato par
	 */
	public Resultado procesar() {
		Resultado r = new Resultado();
		if (!this.arbol.esVacio()) {
			this.procesar(r, arbol);
		}
		return r;
	}
	
	private void procesar(Resultado r, ArbolBinario<Integer> a) {
		if (a.getDato() % 2 == 0) {
			r.setCantPares(r.getCantPares()+1);
			if (a.tieneHijoDerecho()&&a.tieneHijoIzquierdo()) {
				r.getArbolesQueCumplen().agregarFinal(a);
			}
		}
		if (a.tieneHijoIzquierdo()&&!a.getHijoIzquierdo().esVacio()) {
			this.procesar(r, a.getHijoIzquierdo());
		}
		if (a.tieneHijoDerecho()&&!a.getHijoDerecho().esVacio()) {
			this.procesar(r, a.getHijoDerecho());
		}
	}
	
	/*
	 Devolver el elemento que se encuentra a mayor profundidad
	 */
	
	public Integer buscar() {
		Resultado r = new Resultado();
		if (!this.arbol.esVacio()) {
			this.buscar(arbol, 0, r);
		}
		return r.getValorARetornar();
	}
	
	public void buscar(ArbolBinario<Integer> a, int nivelActual, Resultado r) {
		if (!a.esVacio()) {
			if (a.tieneHijoIzquierdo()){
				this.buscar(a.getHijoIzquierdo(), nivelActual+1, r);
			}
			if (a.tieneHijoDerecho()) {
				this.buscar(a.getHijoDerecho(), nivelActual+1, r);
			}
			if (nivelActual > r.getNivelMaximo()) {
				r.setNivelMaximo(nivelActual);
				r.setValorARetornar(a.getDato());
			}
		}
	}
}
