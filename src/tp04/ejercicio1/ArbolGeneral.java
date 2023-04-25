package tp04.ejercicio1;

import tp02.ejercicio2.*;

public class ArbolGeneral<T> {

	private T dato;

	private ListaGenerica<ArbolGeneral<T>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();

	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}

	public void setHijos(ListaGenerica<ArbolGeneral<T>> hijos) {
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ArbolGeneral(T dato) {
		this.dato = dato;
	}

	public ArbolGeneral(T dato, ListaGenerica<ArbolGeneral<T>> hijos) {
		this(dato);
		if (hijos==null)
			this.hijos = new ListaEnlazadaGenerica<ArbolGeneral<T>>();
		else
			this.hijos = hijos;
	}

	public ListaGenerica<ArbolGeneral<T>> getHijos() {
		return this.hijos;
	}

	public void agregarHijo(ArbolGeneral<T> unHijo) {

		this.getHijos().agregarFinal(unHijo);
	}

	public boolean esHoja() {

		return !this.tieneHijos();
	}
	
	public boolean tieneHijos() {
		return !this.hijos.esVacia();
	}
	
	public boolean esVacio() {

		return this.dato == null && !this.tieneHijos();
	}

	

	public void eliminarHijo(ArbolGeneral<T> hijo) {
		if (this.tieneHijos()) {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			if (hijos.incluye(hijo)) 
				hijos.eliminar(hijo);
		}
	}
	
	// EJERCICIO 4
	
	public ListaEnlazadaGenerica<T> preOrden() {
		return null;
	}
	
	public Integer altura() {
		if (this.esHoja()) {
			return 0;
		}
		else {
			int max = Integer.MIN_VALUE;
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			int cant = 1 + hijos.proximo().altura();
			max = Math.max(max, cant);
			return max;
		}
	}

	public Integer nivel(T dato) {
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		ArbolGeneral<T> arbolAux = null;
		int nivel = 0;
		cola.encolar(this);
		cola.encolar(null);
		while (cola.esVacia()&&(arbolAux.getDato() != dato)) {
			arbolAux = cola.desencolar();
			if (arbolAux != null) {
				ListaGenerica<ArbolGeneral<T>> hijos = arbolAux.getHijos();
				while (!hijos.fin()) {
					cola.encolar(hijos.proximo());
				}
			}
			else if (!cola.esVacia()) {
				cola.encolar(null);
				nivel++;
			}
		}
		return nivel;
	}

	public Integer ancho() {
		int anchoMaximo = Integer.MIN_VALUE; int anchoNivel = 0;
		ColaGenerica<ArbolGeneral<T>> cola = new ColaGenerica<ArbolGeneral<T>>();
		ArbolGeneral<T> arbolAux;
		cola.encolar(this);
		cola.encolar(null);
		while (!cola.esVacia()) {
			arbolAux = cola.desencolar();
			if (arbolAux != null) {
				ListaGenerica<ArbolGeneral<T>> hijos = arbolAux.getHijos();
				while (!hijos.fin()) {
					cola.encolar(hijos.proximo());
					anchoNivel++;
				}
			}
			else if (!cola.esVacia()) {
				cola.encolar(null);
				if (anchoNivel > anchoMaximo) {
					anchoMaximo = anchoNivel;
				}
				anchoNivel = 0;
			}
		}
		return anchoMaximo;
	}
	
	//	EJERCICIO 6
	
	public Boolean esAncestro(T a, T b) {
		ArbolGeneral<T> arbolAux = this.buscar(a);
		if (arbolAux.buscar(b) != null) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	private ArbolGeneral<T> buscar(T dato){
		if (this.getDato() == dato) {
			return this;
		}
		else {
			ListaGenerica<ArbolGeneral<T>> hijos = this.getHijos();
			hijos.comenzar();
			while (!hijos.fin()) {
				hijos.proximo().buscar(dato);
			}
		}
		return null;
	}
	

}