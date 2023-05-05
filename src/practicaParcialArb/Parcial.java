package practicaParcialArb;

import tp02.ejercicio2.*;
import tp03.ejercicio1.ArbolBinario;
import tp04.ejercicio1.ArbolGeneral;

public class Parcial {
	
	ArbolGeneral<Integer> arbol;
	
	public Parcial (ArbolGeneral<Integer> a){
		this.arbol = a;
	}
	
	/*
	 Devolver un camino q cumpla con la siguiente condicion: la cantidad de numeros pares
	 q contenga dicho camino debe ser mayor o igual al parametro min, si existen varios caminos
	 devolver el primero que encuentre
	 */
	public ListaGenerica<Integer> resolver(ArbolBinario<Integer> ab, int min){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		if (!ab.esVacio()) {
			resolver(lista, ab, min);
		}
		return lista;
	}

	private boolean resolver(ListaGenerica<Integer> l, ArbolBinario<Integer> a, int min) {
		boolean encontre = false;
		l.agregarFinal(a.getDato());
		if (a.getDato() % 2 == 0) {
			min-= 1;
		}
		if (a.esHoja()) {
			if (min <=0) {
				encontre = true;
				return true;
			} else {
				l.eliminarEn(l.tamanio());
			}
			
		} else {
			if (a.tieneHijoDerecho()&&!encontre) {
				encontre = this.resolver(l, a.getHijoDerecho(), min);
			}
			if (a.tieneHijoIzquierdo()&&!encontre) {
				encontre = this.resolver(l, a.getHijoIzquierdo(), min);
			}
			if (!encontre) {
				l.eliminarEn(l.tamanio());
			}
		}
		return encontre;
	}
	
	
	/*
	 Devolver una lista con los valores de los nodos q tengan la misma cantidad de descendientes tanto en su subarbol izquierdo
	 como en el subarbol derecho
	 */
	public ListaGenerica<Integer> resolver2(ArbolBinario<Integer> arbol){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		if (!arbol.esVacio()) {
			resolver2(lista, arbol);
		}
		return lista;
	}
	
	private int resolver2(ListaGenerica<Integer> l , ArbolBinario<Integer> a) {
		if (a.esVacio()) {
			return 0;
		} else {
			int cantHI = 0;
			int cantHD = 0;
			if (a.tieneHijoIzquierdo()) {
				cantHI = resolver2(l, a.getHijoIzquierdo());
			}
			if (a.tieneHijoDerecho()) {
				cantHD = resolver2(l, a.getHijoDerecho());
			}
			if (cantHI == cantHD) {
				l.agregarFinal(a.getDato());
			}
			return cantHI+cantHD+1;
		}
	}
	
	/*
	 Devolver el camino determinado por el valor que posee cada nodo
	 */
	
	
	public ListaGenerica<ArbolGeneral<Integer>> resolver3(ArbolGeneral<Integer> arbol){
		ListaGenerica<ArbolGeneral<Integer>> lista = new ListaEnlazadaGenerica<ArbolGeneral<Integer>>();
		if (!arbol.esVacio()) {
			resolver3(lista, arbol);
		}
		return lista;
	}

	public void resolver3(ListaGenerica<ArbolGeneral<Integer>> l, ArbolGeneral<Integer> a) {
		ListaGenerica<ArbolGeneral<Integer>> hijos = a.getHijos();
		if (a.getDato() < hijos.tamanio()) {
			l.agregarFinal(a);
			ArbolGeneral<Integer> arbolAux = hijos.elemento(a.getDato());
			resolver3(l, arbolAux);
		}
	}
	
	/*
	 Retornar una lista con la suma de todos los datos contenidos en los nodos
	 del arbol que tiene un numero impar de numeros hijos
	 */
	
	public ListaGenerica<Integer> resolver4(){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		if (!this.arbol.esVacio()) {
			this.resolver4(lista, arbol);
		}
		return lista;
	}
	
	private void resolver4 (ListaGenerica<Integer> l, ArbolGeneral<Integer> a) {
		if (!a.tieneHijos()) {
			ListaGenerica<ArbolGeneral<Integer>> hijos = arbol.getHijos();
			hijos.comenzar();
			while (!hijos.fin()) {
				this.resolver4(l, hijos.proximo());
			}
			if (hijos.tamanio() % 2 != 0) {
				hijos.comenzar();
				int total = 0;
				while (!hijos.fin()) {
					total+= hijos.proximo().getDato();
				}
				l.agregarFinal(total);
			}
		}
	}
	
	/*
	 Imprimir el camino a la hoja mas lejana
	 */
	
	public ListaGenerica<ArbolGeneral<Integer>> resolver5(ArbolGeneral<Integer> arbol){
		ListaGenerica<ArbolGeneral<Integer>> listaMax = new ListaEnlazadaGenerica<ArbolGeneral<Integer>>();
		ListaGenerica<ArbolGeneral<Integer>> lista = new ListaEnlazadaGenerica<ArbolGeneral<Integer>>();
		if (!arbol.esVacio()) {
			resolver5(listaMax, lista, arbol);
		}
		return listaMax;
	}
	
	private void resolver5(ListaGenerica<ArbolGeneral<Integer>> listaRes, ListaGenerica<ArbolGeneral<Integer>> l2, ArbolGeneral<Integer> arbol){
		if (!arbol.esVacio()) {
			l2.agregarFinal(arbol);
			if (arbol.esHoja()) {
				if (listaRes.tamanio() < l2.tamanio()) {
					listaRes = l2.clonar();
				}
				l2.eliminarEn(l2.tamanio());
			} else {

				ListaGenerica<ArbolGeneral<Integer>> hijos = arbol.getHijos();
				hijos.comenzar();
				while (!hijos.fin()) {
					resolver5(listaRes, l2, hijos.proximo());
				}
				l2.eliminarEn(l2.tamanio());
			}
		}
	}
	
}
