package tp03.ejercicio1;

import tp02.ejercicio2.*;

public class ArbolBinario<T> {
	private T dato;
	private ArbolBinario<T> hijoIzquierdo;   
	private ArbolBinario<T> hijoDerecho; 

	
	public ArbolBinario() {
		super();
	}

	public ArbolBinario(T dato) {
		this.dato = dato;
	}

	/*
	 * getters y setters
	 * 
	 */
	public T getDato() {
		return dato;
	}

	public void setDato(T dato) {
		this.dato = dato;
	}
	
	/**
	 * Preguntar antes de invocar si tieneHijoIzquierdo()
	 * @return
	 */
	public ArbolBinario<T> getHijoIzquierdo() {
		return this.hijoIzquierdo;
	}

	public ArbolBinario<T> getHijoDerecho() {
		return this.hijoDerecho;

	}

	public void agregarHijoIzquierdo(ArbolBinario<T> hijo) {
		this.hijoIzquierdo = hijo;
	}

	public void agregarHijoDerecho(ArbolBinario<T> hijo) {
		this.hijoDerecho = hijo;
	}

	public void eliminarHijoIzquierdo() {
		this.hijoIzquierdo = null;
	}

	public void eliminarHijoDerecho() {
		this.hijoDerecho = null;
	}

	public boolean esVacio() {
		return this.getDato() == null && !this.tieneHijoIzquierdo() && !this.tieneHijoDerecho();
	}

	public boolean esHoja() {
		return (!this.tieneHijoIzquierdo() && !this.tieneHijoDerecho());

	}

	@Override
	public String toString() {
		return this.getDato().toString();
	}

	 
	public boolean tieneHijoIzquierdo() {
		return this.hijoIzquierdo!=null;
	}

	 
	public boolean tieneHijoDerecho() {
		return this.hijoDerecho!=null;
	}

	public int contarHojas() {
		if (this.esHoja()) {
			return 1;
		}
		else {
			return this.getHijoDerecho().contarHojas() + this.getHijoIzquierdo().contarHojas();
		}
	}
	
    public ArbolBinario<T> espejo() {
    	if (this.esVacio()) {
    		return null;
    	}
    	else {
    		ArbolBinario<T> abb = new ArbolBinario<T>();
    		abb.setDato(this.dato);
    		if (this.tieneHijoDerecho()) {
            	abb.agregarHijoIzquierdo(this.getHijoDerecho().espejo());
    		}
    		if (this.tieneHijoIzquierdo()) {
            	abb.agregarHijoDerecho(this.getHijoIzquierdo().espejo());
    		}
    		return abb;
    	}
	}

	public void entreNiveles(int n, int m){
		ArbolBinario<T> arbol = null;
		int altura = 0;
		ColaGenerica<ArbolBinario<T>> cola = new ColaGenerica<ArbolBinario<T>>();
		cola.encolar(this);
		cola.encolar(null);
		while (!cola.esVacia()) {
			arbol = cola.desencolar();
			if (arbol != null) {
				if ((n<altura)&&(altura<m)) {
					System.out.println(arbol.getDato());
				}
				if (arbol.tieneHijoDerecho()) {
					cola.encolar(arbol.getHijoDerecho());
				}
				if (arbol.tieneHijoIzquierdo()) {
					cola.encolar(arbol.getHijoIzquierdo());
				}
			} else if (!cola.esVacia()) {
				System.out.println();
				cola.encolar(null);
				altura++;
			}
	}
	
	}

}
