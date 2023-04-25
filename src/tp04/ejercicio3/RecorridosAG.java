package tp04.ejercicio3;

import tp02.ejercicio2.*;

import tp04.ejercicio1.ArbolGeneral;

public class RecorridosAG {
	
	
	// ------------------ PRE ORDEN -------------------

	public ListaGenerica<Integer> numerosImparesMayoresQuePreOrden (ArbolGeneral <Integer> a, Integer n){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		this.numerosImparesMayoresQuePreOrden(a, lista, n);
		return lista;
	}
	
	private void numerosImparesMayoresQuePreOrden(ArbolGeneral<Integer> a, ListaGenerica<Integer> l, Integer n) {
		if (a.getDato() > n) {
			l.agregarFinal(a.getDato());
		}
		ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
		lHijos.comenzar();
		while (!lHijos.fin()) {
			this.numerosImparesMayoresQuePreOrden(lHijos.proximo(), l, n);
		}
	}
	
	// ------------------ POS ORDEN -------------------
	
	public ListaGenerica<Integer> numerosImparesMayoresQuePostOrden (ArbolGeneral <Integer> a, Integer n){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		this.numerosImparesMayoresQuePostOrden(a, lista, n);
		return lista;
	}
	
	private void numerosImparesMayoresQuePostOrden(ArbolGeneral<Integer> a, ListaGenerica<Integer> l, Integer n) {
		ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
		lHijos.comenzar();
		while (!lHijos.fin()) {
			this.numerosImparesMayoresQuePostOrden(lHijos.proximo(), l, n);
		}
		if (a.getDato() > n) {
			l.agregarFinal(a.getDato());
		}
	}
	
	// ------------------ IN ORDEN -------------------

	public ListaGenerica<Integer> numerosImparesMayoresQueInOrden (ArbolGeneral <Integer> a, Integer n){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		this.numerosImparesMayoresQueInOrden(a, lista, n);
		return lista;
	}
	
	private void numerosImparesMayoresQueInOrden(ArbolGeneral<Integer> a, ListaGenerica<Integer> l, Integer n) {
		ListaGenerica<ArbolGeneral<Integer>> lHijos = a.getHijos();
		lHijos.comenzar();
		if (!lHijos.esVacia()) {
			this.numerosImparesMayoresQueInOrden(lHijos.proximo(), l, n);
		}
		if (a.getDato() > n) {
			l.agregarFinal(a.getDato());
		}
		while (!lHijos.fin()) {
			this.numerosImparesMayoresQueInOrden(lHijos.proximo(), l, n);
		}
	}
	
	// ------------------ POR NIVELES -------------------
	
	public ListaGenerica< Integer > numerosImparesMayoresQuePorNiveles(ArbolGeneral <Integer> a, Integer n){
		ListaGenerica<Integer> lista = new ListaEnlazadaGenerica<Integer>();
		ColaGenerica<ArbolGeneral<Integer>> cola = new ColaGenerica<ArbolGeneral<Integer>>();
		ArbolGeneral<Integer> arbolAux;
		cola.encolar(a);
		while (!cola.esVacia()) {
			arbolAux = cola.desencolar();
			if (arbolAux.getDato() > n) {
				lista.agregarFinal(arbolAux.getDato());
			}
			if (arbolAux.tieneHijos()) {
				ListaGenerica<ArbolGeneral<Integer>> hijos = arbolAux.getHijos();
				hijos.comenzar();
				while (!hijos.fin()) {
					cola.encolar(hijos.proximo());
				}
			}
		}
		return lista;
	}
	
}
