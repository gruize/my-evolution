package AG;

import AG.Cromosoma.Cromosoma;

public abstract class AlgoritmoGenetico {

	//Poblacion
	private Cromosoma[] pob;
	
	//Tamanyo de la poblacion
	private int tam_pob;
	
	//Numero maximo de generaciones
	private int num_max_gen;
	
	//Cromosoma del mejor individuo
	private Cromosoma el_mejor;
	
	//Posicion del cromosoma del mejor individuo en la poblacion
	private int pos_mejor;
	
	//Probabilidad de Cruce
	private double prob_cruce;
	
	//Probabilidad de Mutacion
	private double prob_mut;
	
	//Tolerancia de la representacion
	private double tol;

	public AlgoritmoGenetico() {
		
	}
	
	public abstract void inicializa();
	
	public abstract void evaluarPoblacion();
	
	public abstract void seleccionRuleta();
	
	public abstract void reproduccion();
	
	public abstract void mutacion();
	
	public abstract void ejecucion();
	
	
}
