package AG.Cromosoma;

import util.MiRandom;

public abstract class Cromosoma {

	//Cadena de bits (fenotipo)
	private boolean[] genes;

	//Longitud del cromosoma
	private int longitudCromosoma;

	//Fenotipo
	private double fenotipo;

	//Funcion de evaluacion fitness adaptacion
	private double aptitud;

	//Puntuacion relativa (aptitud/suma)
	private double puntuacion;

	//Puntuacion acumulada para seleccion
	private double puntuacionAcumulada;

	//Tolerancia
	private double tolerancia;

	//Valores minimo y maximo
	private double xmin;
	private double xmax; 

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

	public double getFenotipo() {
		return fenotipo;
	}

	public void setFenotipo(double fenotipo) {
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

	public double getXmin() {
		return xmin;
	}

	public void setXmin(double xmin) {
		this.xmin = xmin;
	}

	public double getXmax() {
		return xmax;
	}

	public void setXmax(double xmax) {
		this.xmax = xmax;
	}

	public double fenotipo(){
		String v = "";
		for(int i = 0; i < this.longitudCromosoma; i++)
			if(this.genes[i])
				v = v + "1";
			else
				v = v + "0";		
		return (this.xmin + Integer.parseInt(v, 2) * ((this.xmax - this.xmin)/(Math.pow(2, this.longitudCromosoma) - 1)));
	}

	public int calculoLongitudCromosoma(){
		double xresta = (this.xmax-this.xmin);
		double suma = 1+(xresta/this.tolerancia);
		double dividendo = Math.log10(suma);		 
		return (int) Math.abs((dividendo/Math.log10(2)));
	}

	public void inicializaCromosoma(){
		this.longitudCromosoma = calculoLongitudCromosoma();
		this.genes = new boolean[this.longitudCromosoma];
		for (int i = 0; i<longitudCromosoma; i++)  
			this.genes[i] = MiRandom.boolRandom();
		
	}
	
	public abstract double evalua();

	public void clone(Cromosoma cromosoma) {
		this.aptitud = cromosoma.aptitud;
		this.fenotipo = cromosoma.fenotipo;
		this.longitudCromosoma = cromosoma.longitudCromosoma;
		this.puntuacion = cromosoma.puntuacion;
		this.puntuacionAcumulada = cromosoma.puntuacionAcumulada;
		this.tolerancia = cromosoma.tolerancia;
		this.xmax = cromosoma.xmax;
		this.xmin = cromosoma.xmin;
		this.genes = new boolean[this.longitudCromosoma];
		for(int i = 0; i < this.longitudCromosoma; i++)
			this.genes[i] = cromosoma.getGenes()[i];		
	}

}
