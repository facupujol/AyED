package tp03.ejercicio4;
import tp03.ejercicio1.*;

public class RedBinariaLlena {
	ArbolBinario<Integer> arbol;

	public int retardoReenvio() {
		return this.retardoReenvio(this.arbol);
	}
	
	private int retardoReenvio(ArbolBinario<Integer> arbol) {
		int HI = 0;int HD = 0;
		if (this.arbol.esHoja()) {
			return this.arbol.getDato();
		}
		else {
			if (this.arbol.tieneHijoIzquierdo()) {
				HI = this.retardoReenvio(this.arbol.getHijoIzquierdo());
			}
			if (this.arbol.tieneHijoDerecho()) {
				HD = this.retardoReenvio(this.arbol.getHijoDerecho());
			}
		}
		return Math.max(HI, HD) + this.arbol.getDato();		
	}
}
