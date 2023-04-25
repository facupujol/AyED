package tp02.ejercicio2;

public class PilaGenerica<T> {
	
	ListaEnlazadaGenerica lista = new ListaEnlazadaGenerica();

	public void apilar (T elem) {
		lista.agregarInicio(elem);
	}
	
	public T desapilar() {
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
