package AG.Cromosoma;

import util.MyRandom;

public abstract class Cromosoma implements Comparable{

	//Cadena de bits (fenotipo)
	private boolean[] genes;

	//Longitud del cromosoma
	private int longitudCromosoma;

	//Fenotipo
	private double[] fenotipo;

	//Funcion de evaluacion fitness adaptacion
	private double aptitud;

	//Puntuacion relativa (aptitud/suma)
	private double puntuacion;

	//Puntuacion acumulada para seleccion
	private double puntuacionAcumulada;

	//Tolerancia
	private double tolerancia;
	
	public Cromosoma(double tolerancia){
		this.tolerancia = tolerancia;
	}
	
	public boolean[] getGenes() {
		return genes;
	}

	public void setGenes(boolean[] genes) {
		this.genes = genes;
	}

	public int getLongitudCromosoma() {
		return longitudCromosoma;
	}

	public void setLongitudCromosoma(int longitudCromosoma) {
		this.longitudCromosoma = longitudCromosoma;
	}

	public double[] getFenotipo() {
		return fenotipo;
	}

	public void setFenotipo(double[] fenotipo) {
		this.fenotipo = fenotipo;
	}

	public double getAptitud() {
		return aptitud;
	}

	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}

	public double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public double getPuntuacionAcumulada() {
		return puntuacionAcumulada;
	}

	public void setPuntuacionAcumulada(double puntuacionAcumulada) {
		this.puntuacionAcumulada = puntuacionAcumulada;
	}

	public double getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(double tolerancia) {
		this.tolerancia = tolerancia;
	}				

	public void inicializaCromosoma(){		
		this.longitudCromosoma = calculoLongitudCromosoma();
		this.genes = new boolean[this.longitudCromosoma];
		for (int i = 0; i<longitudCromosoma; i++)  
			this.genes[i] = MyRandom.boolRandom();
		
	}	

	public void clone(Cromosoma cromosoma) {
		this.aptitud = cromosoma.aptitud;
		this.fenotipo = cromosoma.fenotipo;
		this.longitudCromosoma = cromosoma.longitudCromosoma;
		this.puntuacion = cromosoma.puntuacion;
		this.puntuacionAcumulada = cromosoma.puntuacionAcumulada;
		this.tolerancia = cromosoma.tolerancia;		
		this.genes = new boolean[this.longitudCromosoma];
		for(int i = 0; i < this.longitudCromosoma; i++)
			this.genes[i] = cromosoma.getGenes()[i];
	}
	
	public int compareTo(Object o){
		Cromosoma cromo = (Cromosoma)o;
		if(this.aptitud > cromo.aptitud)
			return -1;
		else
			if(this.aptitud == cromo.aptitud)
				return 0;
			else
				return 1;
	}
	
	public abstract int calculoLongitudCromosoma();
	
	public abstract double[] fenotipo();
	
	public abstract double getX();
	
	public abstract double evalua();

}
