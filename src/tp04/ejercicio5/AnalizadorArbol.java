package tp04.ejercicio5;

import tp04.ejercicio1.*;
import tp02.ejercicio2.*;

public class AnalizadorArbol {
	
	public int devolverMaximoPromedio(ArbolGeneral<AreaEmpresa> a) {
		ColaGenerica<ArbolGeneral<AreaEmpresa>> cola = new ColaGenerica<ArbolGeneral<AreaEmpresa>>();
		ArbolGeneral<AreaEmpresa> arbolAux;
		int nivel = 0; double maxPromedio = Integer.MIN_VALUE; int totalNivel = 0; int totalNodosNivel = 0; int nivelMaximo = 0;
		cola.encolar(a);
		cola.encolar(null);
		while (!cola.esVacia()) {
			arbolAux = cola.desencolar();
			totalNodosNivel++;
			totalNivel+= arbolAux.getDato().getTardanza();
			if (arbolAux.tieneHijos()) {
				ListaGenerica<ArbolGeneral<AreaEmpresa>> hijos = new ListaEnlazadaGenerica<ArbolGeneral<AreaEmpresa>>();
				while (!hijos.fin()) {
					cola.encolar(hijos.proximo());
				}
			}
			else if (!cola.esVacia()) {
				cola.encolar(null);
				double promedioNivel = totalNivel / totalNodosNivel;
				if (promedioNivel > maxPromedio) {
					maxPromedio = promedioNivel;
					nivelMaximo = nivel;
				}
				totalNivel = 0; totalNodosNivel = 0;
				nivel++;
			}
		}
		return nivelMaximo;
	}

}
