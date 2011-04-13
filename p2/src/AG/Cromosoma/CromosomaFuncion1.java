package AG.Cromosoma;

public class CromosomaFuncion1 extends Cromosoma{
	
	//Valores minimo y maximo
	private double xmin;
	private double xmax; 	
	
	public CromosomaFuncion1(double tolerancia){		
		super(tolerancia);
		this.setXmin(0.0);		
		this.setXmax(32.0);
		this.setFenotipo(new double[1]);
		this.setAptitud(Double.MIN_VALUE);
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
	
	@Override
	public int calculoLongitudCromosoma(){
		double xresta = (this.xmax-this.xmin);
		double suma = 1+(xresta/this.getTolerancia());
		double dividendo = Math.log10(suma);		 
		return (int) Math.abs((dividendo/Math.log10(2)));
	}
	
	@Override
	public double evalua() {
		this.getFenotipo()[0] = this.getX();		
		return 20 + Math.E - (20 * Math.pow(Math.E, (-0.2*Math.abs(this.getFenotipo()[0])))) - Math.pow(Math.E, Math.cos(2 * Math.PI * this.getFenotipo()[0]));
	}
	
	@Override
	public double getX() {
		String v = "";
		for(int i = 0; i < this.getLongitudCromosoma(); i++)
			if(this.getGenes()[i])
				v = v + "1";
			else
				v = v + "0";		
		return (this.getXmin() + Integer.parseInt(v, 2) * ((this.getXmax() - this.getXmin())/(Math.pow(2, this.getLongitudCromosoma()) - 1)));
	}

	@Override
	public double[] fenotipo() {
		double[] fenotipo = new double[1];
		fenotipo[0] = this.getX();
		return fenotipo;
	}

}
