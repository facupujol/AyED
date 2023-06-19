package tp06.ejercicio5;

import tp02.ejercicio2.*;
import tp06.ejercicio3.*;

public class Mapa {
	
	private Grafo<String> mapaCiudades; 
	
	public Mapa (Grafo<String> mapaCiudades) {
		this.mapaCiudades = mapaCiudades;
	}
	
	
	
	// Devolver un camino de ciudad1 a ciudad2
	
	public ListaGenerica<String> devolverCamino (String ciudad1, String ciudad2){
		boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<String> lista = new ListaEnlazadaGenerica<String>();	// Lista que va a ir variando 
		ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();	// Lista que va a tener el camino resultado
		ListaGenerica<Vertice<String>> ciudades = mapaCiudades.listaDeVertices();	// Me agarro todas las ciudades
		ciudades.comenzar();
		boolean encontre = false;
		int i = -1;
		Vertice<String> vertice;
		while ((!ciudades.fin())&&(!encontre)) {	// Mientra no haya recorrido todas las ciudades y no haya encontrado la ciudad1
			vertice = ciudades.proximo();	// Agarro un vertice
			if (vertice.dato() == ciudad1) {	// Chequeo si tiene la ciudad que estoy buscando o no
				encontre = true;	// Actualizo el booleano para parar la busqueda
				i = vertice.getPosicion();	// Me guardo su posicion para empezar a buscar desde ahi
				lista.agregarFinal(vertice.dato());	// Lo agrego a la lista
			}
		}
		if (i != -1) {	// Si i = -1 significa que ciudad1 no estaba en el grafo
			dfs(i, marca, lista, camino, ciudad2);	// Llamo al dfs con la posicion de ciudad1 y ciudad2 como destino
		}
		return camino;
		
	}
	
	private void clonar (ListaGenerica<String> lista, ListaGenerica<String> camino) {
		lista.comenzar();
		while (!lista.fin()) {
			camino.agregarFinal(lista.proximo());
		}
	}
	
	private void dfs (int i, boolean[] marca, ListaEnlazadaGenerica<String> lista, ListaEnlazadaGenerica<String> camino, String ciudad2) {
		marca[i] = true;	// Marco la ciudad como visitada
		Vertice<String> vertice = mapaCiudades.listaDeVertices().elemento(i);	// Me garro el vertice donde estoy parado
		if (vertice.dato() == ciudad2) {	// Chequeo si es la ciudad destino
			clonar(lista, camino);	// Si es asi, actualizo el camino
		}
		if (camino.esVacia()) {	// Si todavia no encontre ningun camino
			ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(vertice);	// Me agarro los adyacentes del vertice donde estoy
			ady.comenzar();	// Comienzo la lista
			while (!ady.fin()&&(camino.esVacia())) {	// Mientras haya adyacentes y no haya encontrado ningun camino
				Vertice<String> destino = ady.proximo().verticeDestino();	// Me agarro el vertice que sigue
				int j = destino.getPosicion();	// Me agarro tambien su posicion
				if (!marca[j]) {	// Si no esta visitado
					lista.agregarFinal(destino.dato());	// Lo agrego a la lista
					dfs(j, marca, lista, camino, ciudad2);	// Llamo a la recursion para seguir la busqueda
					lista.eliminarEn(lista.tamanio());	// Lo elimino para seguir buscando por otro lado
				}
			}
		}
	}
	
	
	
	// Devolver camino que no contenta las ciudades que se encuentran en la lista "ciudades" (estoy asumiendo que no contiene ni ciudad 1 ni ciudad 2)
	
	public ListaGenerica<String> devolverCaminoExceptuando (String ciudad1, String ciudad2, ListaGenerica<String> ciudades){
		boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<String> lista = new ListaEnlazadaGenerica<String>();
		ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();		
		ListaGenerica<Vertice<String>> ciudadesMapa = mapaCiudades.listaDeVertices();
		ciudadesMapa.comenzar();
		boolean encontre = false;
		int i = -1;
		Vertice<String> vertice;
		while ((!ciudadesMapa.fin())&&(!encontre)) {
			vertice = ciudadesMapa.proximo();
			if (vertice.dato() == ciudad1) {
				encontre = true;
				i = vertice.getPosicion();
				lista.agregarFinal(vertice.dato());
			}
		}
		if (i != -1) {
			dfsExceptuando(i, marca, lista, camino, ciudad2, ciudades);
		}
		return camino;	
	}
	
	private void dfsExceptuando (int i, boolean[] marca, ListaGenerica<String> lista, ListaGenerica<String> camino, String ciudad2, ListaGenerica<String> ciudades) {
		marca[i] = true;
		Vertice<String> vertice = mapaCiudades.listaDeVertices().elemento(i);
		if (vertice.dato() == ciudad2) {
			clonar(lista, camino);
		}
		if (camino.esVacia()) {
			ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(vertice);
			ady.comenzar();
			while ((!ady.fin()) && (camino.esVacia())) {
				Vertice<String> destino = ady.proximo().verticeDestino();
				int j = destino.getPosicion();
				if ((!marca[j])&&(!ciudades.incluye(destino.dato()))) {	// Agrego a la condicion, que la ciudad que voy a visitar no este en la lista de ciudades que no puedo visitar
					lista.agregarFinal(destino.dato());
					this.dfsExceptuando(j, marca, lista, camino, ciudad2, ciudades);
					lista.eliminarEn(lista.tamanio());
				}
			}
		}
	}
	
	
	
	// Devolver el camino mas corto
	
	public ListaGenerica<String> caminoMasCorto (String ciudad1, String ciudad2){
		boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<String> lista = new ListaEnlazadaGenerica<String>();
		ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();		
		ListaGenerica<Vertice<String>> ciudades = mapaCiudades.listaDeVertices();
		ciudades.comenzar();
		boolean encontre = false;
		int i = -1;
		Vertice<String> vertice;
		while ((!ciudades.fin())&&(!encontre)) {
			vertice = ciudades.proximo();
			if (vertice.dato() == ciudad1) {
				encontre = true;
				i = vertice.getPosicion();
				lista.agregarFinal(vertice.dato());
			}
		}
		if (i != -1) {
			dfsMasCorto(i, marca, lista, camino, ciudad2);
		}
		return camino;	
	}
	
	private void clonarLimpiandoCamino (ListaGenerica<String> lista, ListaGenerica<String> camino) {	// Necesario en caso de tener que actualizar la lista camino, cuando puede haber mas de un camino hacia el destino
		camino.comenzar();
		while (!camino.fin()) {
			camino.eliminar(camino.proximo());
		}
		lista.comenzar();
		while (!lista.fin()) {
			camino.agregarFinal(lista.proximo());
		}
	}
	
	private void dfsMasCorto (int i, boolean[] marca, ListaGenerica<String> lista, ListaGenerica<String> camino, String ciudad2) {
		Vertice<String> vertice = mapaCiudades.listaDeVertices().elemento(i);
		if (vertice.dato() == ciudad2) {	// Si el vertice tiene la ciudad destino
			if ((lista.tamanio() < camino.tamanio())||(camino.esVacia())) {	// Si el camino es mas corto O si la lista es vacia (es decir si es el primer camino que encontre)
				clonarLimpiandoCamino(lista, camino);	// Copio al camino (todavia no pongo marca porque voy a verificar otros caminos)
			}
		} else {
			marca[i] = true;	// Marco la localidad como visitada
			ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(vertice);
			ady.comenzar();
			while (!ady.fin()) {
				Vertice<String> destino = ady.proximo().verticeDestino();
				int j = destino.getPosicion();
				if (!marca[j]) {
					lista.agregarFinal(destino.dato());
					this.dfsMasCorto(j, marca, lista, camino, ciudad2);
					lista.eliminarEn(lista.tamanio());
				}
			}
		}
	}
	
	
	
	// Devolver un camino en el que el auto no se quede sin combustible (no puede cargar) (asumo que el parametro es > 0)
	
	public ListaGenerica<String> caminoSinCargarCombustible (String ciudad1, String ciudad2, int tanqueAuto){
		boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<String> lista = new ListaEnlazadaGenerica<String>();
		ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();		
		ListaGenerica<Vertice<String>> ciudades = mapaCiudades.listaDeVertices();
		ciudades.comenzar();
		boolean encontre = false;
		int i = -1;
		Vertice<String> vertice;
		while ((!ciudades.fin())&&(!encontre)) {
			vertice = ciudades.proximo();
			if (vertice.dato() == ciudad1) {
				encontre = true;
				i = vertice.getPosicion();
				lista.agregarFinal(vertice.dato());
			}
		}
		if (i != -1) {
			dfsSinCargarCombustible(i, marca, lista, camino, ciudad2, tanqueAuto);
		}
		return camino;		
	}
	
	private void dfsSinCargarCombustible (int i, boolean[] marca, ListaGenerica<String> lista, ListaGenerica<String> camino, String ciudad2, int tanqueAuto) {
		marca[i] = true;	// Marco la ciudad como visitada
		Vertice<String> vertice = mapaCiudades.listaDeVertices().elemento(i);
		if (vertice.dato() == ciudad2) {	// Si es la ciudad destino
			clonar(lista, camino);	// Copio al camino
		}
		if (camino.esVacia()) {	// Si todavia no encontre ningun camino
			ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(vertice);	// Agarro los adyacentes
			ady.comenzar();
			while ((!ady.fin())&&(camino.esVacia())) {	// Mientras haya adyacentes y no haya encontrado camino
				Arista<String> arista = ady.proximo();	// Me agarro la arista, para saber su peso
				int j = arista.verticeDestino().getPosicion();
				if (!marca[j]) {
					tanqueAuto-= arista.peso();	// Actualizo el combustible con el costo del viaje
					if (tanqueAuto >= 0) {	// Si me alcanza
						lista.agregarFinal(arista.verticeDestino().dato());	// Agrego a la lista y llamo a la recursion
						this.dfsSinCargarCombustible(j, marca, lista, camino, ciudad2, tanqueAuto);
						lista.eliminarEn(lista.tamanio());
					}
					tanqueAuto+= arista.peso();	// Deshago el peso, porque voy volviendo para atras en el grafo
				}
			}
		}
	}
	
	
	
	// Camino con menor carga de combustible (el auto no puede quedarse sin combustible en la ruta y puede llenar el tanque en cualquier ciudad)
	
	public ListaGenerica<String> caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto){
		boolean[] marca = new boolean[mapaCiudades.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<String> lista = new ListaEnlazadaGenerica<String>();
		ListaEnlazadaGenerica<String> camino = new ListaEnlazadaGenerica<String>();		
		ListaGenerica<Vertice<String>> ciudades = mapaCiudades.listaDeVertices();
		ciudades.comenzar();
		boolean encontre = false;
		int i = -1;
		Vertice<String> vertice;
		while ((!ciudades.fin())&&(!encontre)) {
			vertice = ciudades.proximo();
			if (vertice.dato() == ciudad1) {
				encontre = true;
				i = vertice.getPosicion();
				lista.agregarFinal(vertice.dato());
			}
		}
		if (i != -1) {
			Minimo m = new Minimo();	// Utilizo un objeto para tener rastro de la cantidad de cargas minimas en el dfs
			m.setMin(Integer.MAX_VALUE);	// Inicializo en un numero grande, asi siempre se actualiza la primera vez
			int cantCargas = 0;	// La cantidad de cargas inicial va a ser 0.
			int carga = tanqueAuto;	// La cantidad de carga va a ser la capacidad del tanque del auto
			dfsConMenorCargaDeCombustible(i, marca, lista, camino, ciudad2, tanqueAuto, cantCargas, carga, m);
		}
		return camino;			
	}
	
	private void dfsConMenorCargaDeCombustible(int i, boolean[] marca, ListaGenerica<String> lista, ListaGenerica<String> camino, String ciudad2, int tanqueAuto, int cantCargas, int carga, Minimo m) {
		Vertice<String> vertice = mapaCiudades.listaDeVertices().elemento(i);
		if ((vertice.dato() == ciudad2)&&(cantCargas < m.getMin())) {	// Si es la ciudad destino y la cantidad de cargas es menor al minimo
			clonarLimpiandoCamino(lista, camino);	//	Copio el camino
			m.setMin(cantCargas);	// Actualizo el minimo (no marco porque voy a volver, probando otros caminos)
		} else {
			marca[i] = true;	// Marco como visitado
			if (tanqueAuto == 0) {	// Si me quede sin combustible
				tanqueAuto = carga;	// Cargo
				cantCargas++;	// Sumo la cantidad de cargas
			}
			ListaGenerica<Arista<String>> ady = mapaCiudades.listaDeAdyacentes(vertice);
			ady.comenzar();
			while (!ady.fin()) {
				Arista<String> arista = ady.proximo();	// Me agarro la arista (para asi saber su peso)
				int j = arista.verticeDestino().getPosicion();
				if (!marca[j]) {
					tanqueAuto-= arista.peso();	// Actualizo con el costo del viaje
					if (tanqueAuto >= 0) {	// Si me alcanza para el viaje
						lista.agregarFinal(arista.verticeDestino().dato());	// Visito la localidad y la agrego a la lista
						this.dfsConMenorCargaDeCombustible(j, marca, lista, camino, ciudad2, tanqueAuto, cantCargas, carga, m);	// Llamo al dfs
						lista.eliminarEn(lista.tamanio());	// La elimino para seguir buscando por otros lados
					}
					tanqueAuto+= arista.peso();	// Como cambio de localidad, revierto el costo del viaje
				}
			}
		}
	}
	
	
	
	
}
