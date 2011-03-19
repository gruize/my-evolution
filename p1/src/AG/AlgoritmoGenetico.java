package AG;

import util.CromosomaFactoria;
import util.Funciones;
import util.SeleccionFactoria;
import util.Selecciones;
import AG.Cromosoma.Cromosoma;
import AG.Cromosoma.CromosomaFuncion1;
import AG.Seleccion.Seleccion;

public abstract class AlgoritmoGenetico {

	//Poblacion
	private Cromosoma[] pob;
	
	//Tamanyo de la poblacion
	private int tamPoblacion;
	
	//Numero de generaciones
	private int generacion;
	
	//Numero maximo de generaciones
	private int numMaxGen;
	
	//Cromosoma del mejor individuo
	private Cromosoma elMejor;
	
	//Posicion del cromosoma del mejor individuo en la poblacion
	private int posMejor;
	
	//Probabilidad de Cruce
	private double probCruce;
	
	//Probabilidad de Mutacion
	private double probMut;
	
	//Tolerancia de la representacion
	private double tolerancia;
	
	//Funcion
	private Funciones funcion;
	
	//Seleccion
	private Seleccion seleccion;
	
	//Tipos de seleccion
	private Selecciones tipoSeleccion;

	public AlgoritmoGenetico(Funciones funcion, Selecciones seleccion) {
		this.funcion = funcion;
		this.tipoSeleccion = seleccion;
		inicializa();
		evaluarPoblacion();
		
	}
	
	public void inicializa(){
		seleccion = SeleccionFactoria.crearSeleccionador(this.tipoSeleccion);
		for(int i = 0; i < this.tamPoblacion; i++){
			pob[i] = CromosomaFactoria.crearCromosoma(this.funcion);
			pob[i].inicializaCromosoma();
			pob[i].setAptitud(pob[i].evalua());	
			
		}
	}
	
	public void evaluarPoblacion() {
		double punt_acum = 0;
		double aptitud_mejor = 0;
		double suma_aptitud = 0; 
		
		Cromosoma mejor_seleccion = CromosomaFactoria.crearCromosoma(this.funcion);
		
		for(int i = 0; i < tamPoblacion; i++){
			suma_aptitud = suma_aptitud + pob[i].getAptitud();
			if(pob[i].getAptitud() > aptitud_mejor){
				mejor_seleccion = pob[i];
				aptitud_mejor = mejor_seleccion.getAptitud();
			}
		}
		
		for(int i = 0; i < tamPoblacion; i++){
			pob[i].setPuntuacion(pob[i].getAptitud() / suma_aptitud);
			pob[i].setPuntuacionAcumulada(pob[i].getPuntuacionAcumulada() + pob[i].getPuntuacion());
			punt_acum = punt_acum + pob[i].getPuntuacion();
		}
		
		if( mejor_seleccion.getAptitud() > elMejor.getAptitud()){
			elMejor = mejor_seleccion;
		}
		
	}
	
	public void reproduccion() {
		int[] selCruce = new int[tamPoblacion];
		int numSelCruce = 0;
		int puntoCruce;
		double probabilidad;
		Cromosoma hijo1, hijo2;
		
		
	}
	
	public void mutacion() {
	}
	
	public void ejecucion(){
		this.generacion++;
		Cromosoma[] nuevaPob = new Cromosoma[tamPoblacion];
		for(int i = 0; i < this.tamPoblacion; i++)
			nuevaPob[i] = CromosomaFactoria.crearCromosoma(this.funcion);
		seleccion.ejecutaSeleccion(pob, nuevaPob, tamPoblacion);
		reproduccion();
		mutacion();
		evaluarPoblacion();
	}
	
}