package AG.Cromosoma;

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
	private double punt_acum;
	
	//Tolerancia
	private double tolerancia;

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

	public double getPunt_acum() {
		return punt_acum;
	}

	public void setPunt_acum(double punt_acum) {
		this.punt_acum = punt_acum;
	}

	public double getTolerancia() {
		return tolerancia;
	}

	public void setTolerancia(double tolerancia) {
		this.tolerancia = tolerancia;
	}
	
	public abstract void inicializaCromosoma();
	
	public abstract double evalua();
	
}
