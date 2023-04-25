package tp03.ejercicio5;

import tp02.ejercicio2.ColaGenerica;
import tp03.ejercicio1.ArbolBinario;

public class ProfundidadDeArbolBinario {
	ArbolBinario<Integer> arbol;
	
	public int sumaElementosProfundidad(int p) {
		int n = 0; int total = 0;
		ArbolBinario<Integer> arbol = null;
		ColaGenerica<ArbolBinario<Integer>> cola = new ColaGenerica<ArbolBinario<Integer>>();
		cola.encolar(this.arbol);
		cola.encolar(null);
		while (!cola.esVacia()) {
			arbol = cola.desencolar();
			if (arbol != null) {
				if (n == p) {
					total += arbol.getDato();
				}
				else {
					if (arbol.tieneHijoDerecho())
						cola.encolar(arbol.getHijoDerecho());
					if (arbol.tieneHijoIzquierdo())
						cola.encolar(arbol.getHijoIzquierdo());
				}
			}
			else {
				if (!cola.esVacia()) {
					cola.encolar(null);
					n++;
				}
			}
		}
		return total;
	}
	

}
