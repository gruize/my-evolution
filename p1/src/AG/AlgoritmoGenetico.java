package AG;

import util.CromosomaFactoria;
import util.Funciones;
import util.SeleccionFactoria;
import util.Selecciones;
import AG.Cromosoma.Cromosoma;
import AG.Seleccion.Seleccion;

public class AlgoritmoGenetico {

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
	
	public AlgoritmoGenetico(int tamPoblacion, int numMaxGen, double probCruce,
			double probMut, double tolerancia, Funciones funcion,
			Selecciones tipoSeleccion) {		
		this.tamPoblacion = tamPoblacion;
		this.numMaxGen = numMaxGen;
		this.probCruce = probCruce;
		this.probMut = probMut;
		this.tolerancia = tolerancia;
		this.funcion = funcion;
		seleccion = SeleccionFactoria.crearSeleccionador(tipoSeleccion);
		this.pob = new Cromosoma[this.tamPoblacion];
		this.elMejor = new CromosomaFactoria().crearCromosoma(funcion, tolerancia);
	}

	public Cromosoma[] getPob() {
		return pob;
	}

	public void setPob(Cromosoma[] pob) {
		this.pob = pob;
	}

	public int getTamPoblacion() {
		return tamPoblacion;
	}

	public void setTamPoblacion(int tamPoblacion) {
		this.tamPoblacion = tamPoblacion;
	}

	public int getGeneracion() {
		return generacion;
	}

	public void setGeneracion(int generacion) {
		this.generacion = generacion;
	}

	public int getNumMaxGen() {
		return numMaxGen;
	}

	public void setNumMaxGen(int numMaxGen) {
		this.numMaxGen = numMaxGen;
	}

	public Cromosoma getElMejor() {
		return elMejor;
	}

	public void setElMejor(Cromosoma elMejor) {
		this.elMejor = elMejor;
	}

	public int getPosMejor() {
		return posMejor;
	}

	public void setPosMejor(int posMejor) {
		this.posMejor = posMejor;
	}

	public double getProbCruce() {
		return probCruce;
	}

	public void setProbCruce(double probCruce) {
		this.probCruce = probCruce;
	}

	public double getProbMut() {
		return probMut;
	}

	public void setProbMut(double probMut) {
		this.probMut = probMut;
	}

	public double getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(double tolerancia) {
		this.tolerancia = tolerancia;
	}

	public Funciones getFuncion() {
		return funcion;
	}

	public void setFuncion(Funciones funcion) {
		this.funcion = funcion;
	}

	public Seleccion getSeleccion() {
		return seleccion;
	}

	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}

	public void inicializa(){		
		for(int i = 0; i < this.tamPoblacion; i++){
			pob[i] = CromosomaFactoria.crearCromosoma(this.funcion, this.tolerancia);
			pob[i].inicializaCromosoma();
			pob[i].setAptitud(pob[i].evalua());	
			
		}
	}
	
	public void evaluarPoblacion() {
		double punt_acum = 0;
		double aptitud_mejor = 0;
		double suma_aptitud = 0; 		
		Cromosoma mejor_seleccion = CromosomaFactoria.crearCromosoma(this.funcion,this.tolerancia);		
		for(int i = 0; i < tamPoblacion; i++){
			suma_aptitud = suma_aptitud + pob[i].getAptitud();
			if(pob[i].getAptitud() > aptitud_mejor){
				mejor_seleccion = pob[i];
				aptitud_mejor = mejor_seleccion.getAptitud();
			}
		}		
		for(int i = 0; i < tamPoblacion; i++){
			pob[i].setPuntuacion(pob[i].getAptitud() / suma_aptitud);
			pob[i].setPuntuacionAcumulada(punt_acum + pob[i].getPuntuacion());
			punt_acum = punt_acum + pob[i].getPuntuacion();
		}		
		if( mejor_seleccion.getAptitud() > elMejor.getAptitud()){
			elMejor = mejor_seleccion;
		}		
	}
	
	public void reproduccion(Cromosoma[] poblacionReproducir) {
		int[] selCruce = new int[this.tamPoblacion];
		int numSelCruce = 0;
		int puntoCruce;
		double probabilidad;
		Cromosoma hijo1 = CromosomaFactoria.crearCromosoma(funcion, this.tolerancia);
		Cromosoma hijo2 = CromosomaFactoria.crearCromosoma(funcion, this.tolerancia);
		//Se eligen los individuos a cruzar
		for(int i = 0; i < this.tamPoblacion; i++){
			probabilidad = Math.random();
			if(probabilidad < this.probCruce){
				selCruce[numSelCruce] = i;
				numSelCruce++;
			}
		}
		
		
		System.out.print("Individuos elegidos:" + numSelCruce + " \n");
		
		
		/**
		 * Si el numero de individuos a cruzar es impar, se debe
		 * eliminar uno de los individuos (el ultimo)
		 */		
		if((numSelCruce % 2) == 1)
			numSelCruce--;
		
		System.out.print("Individuos elegidos: \n");
		for(int i = 0; i < numSelCruce; i++)
			System.out.print(selCruce[i] + "\t");
		System.out.print("\n");		
		
		
		/**
		 * Se cruzan los individuos elegidos en un punto al azar
		 */
		
		puntoCruce = (int)(Math.random() * (this.pob[0].getLongitudCromosoma()));
		
		System.out.print("Punto de cruce = " + puntoCruce + "\n");
		
		for(int i = 0; i < numSelCruce; i = i + 2){
			cruce(poblacionReproducir[selCruce[i]], poblacionReproducir[selCruce[i+1]], hijo1, hijo2, puntoCruce);
			poblacionReproducir[selCruce[i]].clone(hijo1);
			poblacionReproducir[selCruce[i+1]].clone(hijo2);			
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
		for(int i = puntoCruce; i < padre1.getLongitudCromosoma(); i++){
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

			
			System.out.print("Individuo " + i + ":\t");
			
			
			for(int j = 0; j < this.pob[0].getLongitudCromosoma(); j++){
				probabilidad = Math.random();
				//Mutan los genes
				if(probabilidad < this.probMut){
					mutado = true;
					pob[i].getGenes()[j] = !pob[i].getGenes()[j];
			
					
					System.out.print("Muta gen " + j + "\t");
					
					
				}
			}
			if(mutado)
				pob[i].setAptitud(pob[i].evalua());
			
			
			System.out.print("\n");
			
			
		}		
	}
	
	public void ejecucion(){
		this.inicializa();
		this.evaluarPoblacion();
		while(this.generacion < this.numMaxGen){		
			Cromosoma[] nuevaPob = new Cromosoma[tamPoblacion];
			for(int i = 0; i < this.tamPoblacion; i++)
				nuevaPob[i] = CromosomaFactoria.crearCromosoma(this.funcion, this.tolerancia);
			seleccion.ejecutaSeleccion(this.pob, nuevaPob, this.tamPoblacion);
			this.reproduccion(nuevaPob);
			for(int i = 0; i < this.tamPoblacion; i++)
				this.pob[i].clone(nuevaPob[i]);
			this.mutacion();			
			this.evaluarPoblacion();
			this.generacion++;
		}
	}
	
}