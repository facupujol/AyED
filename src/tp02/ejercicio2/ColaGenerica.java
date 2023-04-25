package tp02.ejercicio2;

public class ColaGenerica<T> {
	
	ListaEnlazadaGenerica lista = new ListaEnlazadaGenerica();
	
	public void encolar (T elem) {
		lista.agregarFinal(elem);
		
	}
	
	public T desencolar() {
		T elem = (T)lista.elemento(1);
		lista.eliminarEn(1);
		return elem;
	}
	
	public T tope() {
		return (T)lista.elemento(lista.tamanio());
	}
	
	
	public boolean esVacia() {
		return lista.esVacia();
	}
	

}
