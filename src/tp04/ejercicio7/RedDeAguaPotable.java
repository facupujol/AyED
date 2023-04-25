package tp04.ejercicio7;

import tp02.ejercicio2.ListaGenerica;
import tp04.ejercicio1.ArbolGeneral;

public class RedDeAguaPotable {
	
	ArbolGeneral<Double> red;
	
	public double minimoCaudal(double caudal) {
		double min = Integer.MAX_VALUE;
		return minimoCaudal(caudal, min);
	}
	
	private double minimoCaudal (double caudal, double min) {
		if (red.esVacio()) {
			return 0;
		}
		red.setDato(caudal);
		if (red.getDato()<min) {
			min = red.getDato();
		}
		ListaGenerica<ArbolGeneral<Double>> hijos = red.getHijos();
		hijos.comenzar();
		while (!hijos.fin()) {
			minimoCaudal(caudal / hijos.tamanio(), min);
		}
		return min;
	}
}
