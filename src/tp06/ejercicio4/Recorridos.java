package tp06.ejercicio4;

import tp02.ejercicio2.*;
import tp06.ejercicio3.*;

public class Recorridos<T> {
	
	public ListaGenerica<Vertice<T>> dfs (Grafo<T> grafo){
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		for (int i=1; i<=grafo.listaDeVertices().tamanio(); i++) {
			if (!marca[i]) {
				this.dfs(i, grafo, marca, lista);
			}
		}
		return lista;
		
	}
	
	private void dfs (int i, Grafo<T> grafo, boolean[] marca, ListaGenerica<Vertice<T>> res){
		marca[i] = true;
		Vertice<T> vertice = grafo.listaDeVertices().elemento(i);
		res.agregarFinal(vertice);
		ListaGenerica<Arista<T>> ady = grafo.listaDeAdyacentes(vertice);
		ady.comenzar();
		while (!ady.fin()) {
			int j = ady.proximo().verticeDestino().getPosicion();
			if (!marca[j]) {
				this.dfs(i, grafo, marca, res);
			}
		}
	}
	
	public ListaGenerica<Vertice<T>> bfs (Grafo<T> grafo){
		boolean[] marca = new boolean[grafo.listaDeVertices().tamanio()+1];
		ListaEnlazadaGenerica<Vertice<T>> lista = new ListaEnlazadaGenerica<Vertice<T>>();
		for (int i=1; i<=grafo.listaDeVertices().tamanio(); i++) {
			if (!marca[i]) {
				this.bfs(i, grafo, marca, lista);
			}
		}
		return lista;
	}
	
	private void bfs (int i, Grafo<T> grafo, boolean[] marca, ListaGenerica<Vertice<T>> res) {
		ListaGenerica<Arista<T>> ady = null;
		ColaGenerica<Vertice<T>> cola = new ColaGenerica<Vertice<T>>();
		cola.encolar(grafo.listaDeVertices().elemento(i));
		marca[i] = true;
		while (!cola.esVacia()) {
			Vertice<T> vertice = cola.desencolar();
			res.agregarFinal(vertice);
			ady = grafo.listaDeAdyacentes(vertice);
			ady.comenzar();
			while (!ady.fin()) {
				Arista<T> arista = ady.proximo();
				int j = arista.verticeDestino().getPosicion();
				if (!marca[j]) {
					Vertice<T> vDestino = arista.verticeDestino();
					marca[j] = true;
					cola.encolar(vDestino);
				}
			}
		}
	}
}
