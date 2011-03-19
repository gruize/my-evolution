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
		int[] selCruce = new int[this.tamPoblacion];
		int numSelCruce = 0;
		int puntoCruce;
		double probabilidad;
		Cromosoma hijo1 = CromosomaFactoria.crearCromosoma(funcion);
		Cromosoma hijo2 = CromosomaFactoria.crearCromosoma(funcion);
		//Se eligen los individuos a cruzar
		for(int i = 0; i < this.tamPoblacion; i++){
			probabilidad = Math.random();
			if(probabilidad < this.probCruce){
				selCruce[numSelCruce] = i;
				numSelCruce++;
			}
		}
		/**
		 * Si el numero de individuos a cruzar es impar, se debe
		 * eliminar uno de los individuos (el ultimo)
		 */		
		if((numSelCruce % 2) == 1)
			numSelCruce--;
		/**
		 * Se cruzan los individuos elegidos en un punto al azar
		 */
		puntoCruce = (int)(Math.random() * (this.pob[0].getLongitudCromosoma() + 1));
		for(int i = 0; i < numSelCruce; i = i + 2){
			cruce(this.pob[i], this.pob[i+1], hijo1, hijo2, puntoCruce);
			pob[selCruce[i]] = hijo1;
			pob[selCruce[i+1]] = hijo2;
		}
	}	
	
	private void cruce(Cromosoma padre1, Cromosoma padre2,
			Cromosoma hijo1, Cromosoma hijo2, int puntoCruce) {		
		hijo1.inicializaCromosoma();
		hijo2.inicializaCromosoma();
		//Primera parte: 1 a 1 y 2 a 2
		for(int i = 0; i < puntoCruce; i++){
			hijo1.getGenes()[i] = padre1.getGenes()[i];
			hijo2.getGenes()[i] = padre2.getGenes()[i];
		}
		//Segunda parte: 1 a 2 y 2 a 1
		for(int i = puntoCruce; i < this.pob[0].getLongitudCromosoma(); i++){
			hijo1.getGenes()[i] = padre2.getGenes()[i];
			hijo2.getGenes()[i] = padre1.getGenes()[i];
		}
		//Se evaluan
		hijo1.setAptitud(hijo1.evalua());
		hijo2.setAptitud(hijo2.evalua());
	}

	public void mutacion() {
		boolean mutado;
		double probabilidad;
		for(int i = 0; i < this.tamPoblacion; i++){
			mutado = false;
			for(int j = 0; j < this.pob[0].getLongitudCromosoma(); j++){
				probabilidad = Math.random();
				//Mutan los genes
				if(probabilidad < this.probMut){
					mutado = true;
					pob[i].getGenes()[j] = !pob[i].getGenes()[j];					
				}
				if(mutado)
					pob[i].setAptitud(pob[i].evalua());
			}
		}		
	}
	
	public void ejecucion(){
		while(this.generacion < this.numMaxGen){		
			Cromosoma[] nuevaPob = new Cromosoma[tamPoblacion];
			for(int i = 0; i < this.tamPoblacion; i++)
				nuevaPob[i] = CromosomaFactoria.crearCromosoma(this.funcion);
			seleccion.ejecutaSeleccion(this.pob, nuevaPob, this.tamPoblacion);
			this.reproduccion();
			this.mutacion();
			this.evaluarPoblacion();
			this.generacion++;
		}
	}
	
}