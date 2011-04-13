package AG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.CromosomaFactoria;
import util.Funciones;
import util.SeleccionFactoria;
import util.Selecciones;
import AG.Cromosoma.Cromosoma;
import AG.Seleccion.Seleccion;

public class AlgoritmoGenetico {

	//Poblacion
	private List<Cromosoma> pob;
	
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
	
	//Tipo de funcion
	private boolean funcionDeMinimizacion;
	
	//Funcion 4 n
	private int n;
	
	//Elitismo
	private boolean elitismo;
	private List<Cromosoma> elite;
	private int tamElite;
	
	
	//Valores de graficas	
	private double[] mejorAbsoluto;
	private double[] mejorGeneracion;
	private double[] mediaGeneracion;
	
	public AlgoritmoGenetico(int tamPoblacion, int numMaxGen, double probCruce,
			double probMut, double tolerancia, Funciones funcion,
			Selecciones tipoSeleccion, int n, boolean elitismo, double elite) {		
		this.tamPoblacion = tamPoblacion;
		this.numMaxGen = numMaxGen;
		this.probCruce = probCruce;
		this.probMut = probMut;
		this.tolerancia = tolerancia;
		this.funcion = funcion;
		this.n = n;
		seleccion = SeleccionFactoria.crearSeleccionador(tipoSeleccion);
		this.pob = new ArrayList<Cromosoma>();
		this.elMejor = CromosomaFactoria.crearCromosoma(funcion, tolerancia, n);
		switch(funcion){
			case Funcion1:
			case Funcion2:
				this.funcionDeMinimizacion = false;
				break;
			case Funcion3:
			case Funcion4:
			case Funcion5:
				this.funcionDeMinimizacion = true;
				break;
		}
		this.mejorAbsoluto = new double[this.numMaxGen];
		this.mejorGeneracion = new double[this.numMaxGen];
		this.mediaGeneracion = new double[this.numMaxGen];
		this.elitismo = elitismo;
		if(this.elitismo){
			tamElite = (int) (this.tamPoblacion * 0.01 * elite);
			this.elite = new ArrayList<Cromosoma>();
		}
	}

	public List<Cromosoma> getPob() {
		return pob;
	}

	public void setPob(List<Cromosoma> pob) {
		this.pob = pob;
	}

	public void setElite(List<Cromosoma> elite) {
		this.elite = elite;
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

	public boolean isFuncionDeMinimizacion() {
		return funcionDeMinimizacion;
	}

	public void setFuncionDeMinimizacion(boolean funcionDeMinimizacion) {
		this.funcionDeMinimizacion = funcionDeMinimizacion;
	}

	public double[] getMejorAbsoluto() {
		return mejorAbsoluto;
	}

	public void setMejorAbsoluto(double[] mejorAbsoluto) {
		this.mejorAbsoluto = mejorAbsoluto;
	}

	public double[] getMejorGeneracion() {
		return mejorGeneracion;
	}

	public void setMejorGeneracion(double[] mejorGeneracion) {
		this.mejorGeneracion = mejorGeneracion;
	}

	public double[] getMediaGeneracion() {
		return mediaGeneracion;
	}

	public void setMediaGeneracion(double[] mediaGeneracion) {
		this.mediaGeneracion = mediaGeneracion;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public boolean isElitismo() {
		return elitismo;
	}

	public void setElitismo(boolean elitismo) {
		this.elitismo = elitismo;
	}

	public void inicializa(){		
		for(int i = 0; i < this.tamPoblacion; i++){
			pob.add(CromosomaFactoria.crearCromosoma(this.funcion, this.tolerancia, this.n));
			pob.get(i).inicializaCromosoma();
			pob.get(i).setAptitud(pob.get(i).evalua());	
		}
	}
	
	public void evaluarPoblacion() {
		double punt_acum = 0;
		double aptitud_mejor = Double.MIN_VALUE;
		double suma_aptitud = 0;
		int mejor_seleccion = 0;
		if(!this.funcionDeMinimizacion){
		//this.revisarAdaptacionMinimiza();
			//Maximizacion				
			for(int i = 0; i < tamPoblacion; i++){
				suma_aptitud = suma_aptitud + pob.get(i).getAptitud();
				if(pob.get(i).getAptitud() > aptitud_mejor){
					mejor_seleccion = i;
					aptitud_mejor = pob.get(i).getAptitud();
				}
			}		
			for(int i = 0; i < tamPoblacion; i++){
				pob.get(i).setPuntuacion(pob.get(i).getAptitud() / suma_aptitud);
				pob.get(i).setPuntuacionAcumulada(punt_acum + pob.get(i).getPuntuacion());
				punt_acum = punt_acum + pob.get(i).getPuntuacion();
			}		
			if( aptitud_mejor > elMejor.getAptitud()){
				elMejor.clone(pob.get(mejor_seleccion));
			}
		}else{
			//Minimizacion
			aptitud_mejor = Double.MAX_VALUE;
			//TODO: Realizar calculos para adaptar a la minimizacion
			for(int i = 0; i < tamPoblacion; i++){
				suma_aptitud = suma_aptitud + pob.get(i).getAptitud();
				if(pob.get(i).getAptitud() < aptitud_mejor){
					mejor_seleccion = i;
					aptitud_mejor = pob.get(i).getAptitud();
				}
			}
			for(int i = 0; i < tamPoblacion; i++){
				pob.get(i).setPuntuacion(pob.get(i).getAptitud() / suma_aptitud);
				pob.get(i).setPuntuacionAcumulada(punt_acum + pob.get(i).getPuntuacion());
				punt_acum = punt_acum + pob.get(i).getPuntuacion();
			}		
			if(aptitud_mejor < elMejor.getAptitud()){
				elMejor.clone(pob.get(mejor_seleccion));		
			}
		}		
		this.mejorAbsoluto[this.generacion] = elMejor.getAptitud();
		this.mejorGeneracion[this.generacion] = aptitud_mejor;
		this.mediaGeneracion[this.generacion] = suma_aptitud / tamPoblacion;
	}
	
	public void reproduccion() {
		int[] selCruce = new int[this.tamPoblacion];
		int numSelCruce = 0;
		int puntoCruce;
		double probabilidad;
		Cromosoma hijo1 = CromosomaFactoria.crearCromosoma(funcion, this.tolerancia, this.n);
		Cromosoma hijo2 = CromosomaFactoria.crearCromosoma(funcion, this.tolerancia, this.n);
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
		
		puntoCruce = (int)(Math.random() * (this.pob.get(0).getLongitudCromosoma()));
		
		System.out.print("Punto de cruce = " + puntoCruce + "\n");
		
		for(int i = 0; i < numSelCruce; i = i + 2){
			cruce(this.pob.get(selCruce[i]), this.pob.get(selCruce[i+1]), hijo1, hijo2, puntoCruce);
			this.pob.get(selCruce[i]).clone(hijo1);
			this.pob.get(selCruce[i+1]).clone(hijo2);			
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
			
			
			for(int j = 0; j < this.pob.get(0).getLongitudCromosoma(); j++){
				probabilidad = Math.random();
				//Mutan los genes
				if(probabilidad < this.probMut){
					mutado = true;
					pob.get(i).getGenes()[j] = !pob.get(i).getGenes()[j];
			
					
					System.out.print("Muta gen " + j + "\t");
					
					
				}
			}
			if(mutado)
				pob.get(i).setAptitud(pob.get(i).evalua());
			
			
			System.out.print("\n");
			
			
		}		
	}
	
	public void separaMejores(){
		if(this.elitismo){
			List<Cromosoma> mejoresCromos = new ArrayList<Cromosoma>();
			for(int i = 0; i < this.tamPoblacion; i++){
				Cromosoma temp = CromosomaFactoria.crearCromosoma(this.funcion, this.tolerancia, this.n);
				temp.clone(this.pob.get(i));				
				mejoresCromos.add(temp);
			}
			Collections.sort(mejoresCromos);
			for(int i = 0; i < this.tamElite; i++){
				this.elite.add(CromosomaFactoria.crearCromosoma(this.funcion, this.tolerancia, this.n));
				this.elite.get(i).clone(mejoresCromos.get(i));
			}
			
		}			
	}
	
	public void incluyeMejores(){
		if(this.elitismo){			
			List<Cromosoma> nuevo = new ArrayList<Cromosoma>();
			for(int i = 0; i < this.tamPoblacion; i++){
				nuevo.add(CromosomaFactoria.crearCromosoma(this.funcion, this.tolerancia, this.n));
				nuevo.get(i).clone(this.pob.get(i));
			}
			for(int i = 0; i < this.tamElite; i++){
				nuevo.add(CromosomaFactoria.crearCromosoma(this.funcion, this.tolerancia, this.n));
				nuevo.get(this.tamPoblacion + i).clone(this.elite.get(i));
			}
			this.tamPoblacion = this.tamPoblacion + this.elite.size();
			pob = new ArrayList<Cromosoma>();
			for(int i = 0; i < this.tamPoblacion; i++){
				pob.set(i,CromosomaFactoria.crearCromosoma(this.funcion, this.tolerancia, this.n));
				pob.get(i).clone(nuevo.get(i));					
			}			
		}
	}
	
	public void revisarAdaptacionMinimiza(){
		if(this.funcionDeMinimizacion){
			double cmax = Double.MIN_VALUE;
			for(int i = 0; i < this.tamPoblacion; i++){
				if(this.pob.get(i).getAptitud() > cmax)
					cmax = this.pob.get(i).getAptitud();				
			}
			cmax = cmax * 1.05;
			for(int i = 0; i < this.tamPoblacion; i++){
				this.pob.get(i).setAptitud(cmax - this.pob.get(i).getAptitud());
			}
		}
	}
	
	public void ejecucion(){
		this.inicializa();
		int longitud = this.getPob().get(0).getLongitudCromosoma();
		
		
		System.out.print("***************Ahora se evalua la poblacion*************\n");
		
		
		this.evaluarPoblacion();
		
		System.out.print("Poblacion actual: \n");
		System.out.print("Genes\tFenotipo\t\tAptitud\t\t\tPuntuacion\t\tPuntuacionAcumulada\n");
		for(int i = 0; i < this.tamPoblacion; i++){			
			String cromo = "";
			for(int j = 0; j < longitud; j++){
				
				if(this.getPob().get(i).getGenes()[j])
					cromo = cromo + "1";
				else
					cromo = cromo + "0";
			}
			System.out.print(cromo + "\t" );
			for(int j = 0; j < this.getPob().get(i).getFenotipo().length; j++)				
				System.out.print(this.getPob().get(i).getFenotipo()[j] + "\t");
			System.out.print(this.getPob().get(i).getAptitud() + "\t" + this.getPob().get(i).getPuntuacion() + "\t" + this.getPob().get(i).getPuntuacionAcumulada() + "\n");
		}
		
		while(this.getGeneracion() < this.getNumMaxGen()){		
			
			System.out.print("El mejor (genes): ");
			for(int j = 0; j < this.getElMejor().getGenes().length; j++)
				if(this.getElMejor().getGenes()[j])
					System.out.print("1");
				else
					System.out.print("0");
			System.out.print("\n");
			System.out.print("************Ahora se ejecuta la seleccion para la generacion " + this.getGeneracion() + ":\n");
			
			
			this.separaMejores();	
			this.getSeleccion().ejecutaSeleccion(this);	
			
			
			System.out.print("Nueva poblacion actual: \n");
			for(int i = 0; i < this.tamPoblacion; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
					if(this.getPob().get(i).getGenes()[j])
						cromo = cromo + "1";
					else
						cromo = cromo + "0";
				}
				System.out.print(cromo + "\t" );
				for(int j = 0; j < this.getPob().get(i).getFenotipo().length; j++)				
					System.out.print(this.getPob().get(i).getFenotipo()[j] + "\t");
				System.out.print(this.getPob().get(i).getAptitud() + "\t" + this.getPob().get(i).getPuntuacion() + "\t" + this.getPob().get(i).getPuntuacionAcumulada() + "\n");
			}					
			
			System.out.print("************Ahora se ejecuta la reproduccion para la generacion " + this.getGeneracion() + ":\n");

			
			this.reproduccion();
			
			
			System.out.print("Poblacion despues del cruce: \n");
			for(int i = 0; i < this.tamPoblacion; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
					if(this.getPob().get(i).getGenes()[j])
						cromo = cromo + "1";
					else
						cromo = cromo + "0";
				}
				System.out.print(cromo + "\t" );
				for(int j = 0; j < this.getPob().get(i).getFenotipo().length; j++)				
					System.out.print(this.getPob().get(i).getFenotipo()[j] + "\t");
				System.out.print(this.getPob().get(i).getAptitud() + "\t" + this.getPob().get(i).getPuntuacion() + "\t" + this.getPob().get(i).getPuntuacionAcumulada() + "\n");
			}				
			
			System.out.print("************Ahora se ejecutan las mutaciones:\n");
			
			
			this.mutacion();
			this.incluyeMejores();
			
			
			System.out.print("Poblacion actual despues de las mutaciones: \n");
			for(int i = 0; i < this.tamPoblacion; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
					if(this.getPob().get(i).getGenes()[j])
						cromo = cromo + "1";
					else
						cromo = cromo + "0";
				}
				System.out.print(cromo + "\t" );
				for(int j = 0; j < this.getPob().get(i).getFenotipo().length; j++)				
					System.out.print(this.getPob().get(i).getFenotipo()[j] + "\t");
				System.out.print(this.getPob().get(i).getAptitud() + "\t" + this.getPob().get(i).getPuntuacion() + "\t" + this.getPob().get(i).getPuntuacionAcumulada() + "\n");
			}
			
			
			this.evaluarPoblacion();
			
			
			System.out.print("Poblacion actual final: \n");
			for(int i = 0; i < this.tamPoblacion; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
					if(this.getPob().get(i).getGenes()[j])
						cromo = cromo + "1";
					else
						cromo = cromo + "0";
				}
				System.out.print(cromo + "\t" );
				for(int j = 0; j < this.getPob().get(i).getFenotipo().length; j++)				
					System.out.print(this.getPob().get(i).getFenotipo()[j] + "\t");
				System.out.print(this.getPob().get(i).getAptitud() + "\t" + this.getPob().get(i).getPuntuacion() + "\t" + this.getPob().get(i).getPuntuacionAcumulada() + "\n");
			}
			
			
			this.setGeneracion(this.getGeneracion() + 1);
		}
		
		
		System.out.print("El mejor (genes): ");
		for(int j = 0; j < this.getElMejor().getGenes().length; j++)
			if(this.getElMejor().getGenes()[j])
				System.out.print("1");
			else
				System.out.print("0");
		System.out.print("\tFenotipo: ");
		for(int i = 0; i < this.getElMejor().getFenotipo().length; i++)
			System.out.print(this.getElMejor().getFenotipo()[i] + "\t");
		System.out.print("\tAptitud: " + this.getElMejor().getAptitud() + "\n");
	}
	
}