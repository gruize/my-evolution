package AG.Cromosoma;

public class CromosomaFuncion2 extends Cromosoma{
	
	//Valores minimo y maximo
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	
	public CromosomaFuncion2(double tolerancia){
		super(tolerancia);
		this.setXmin(-3.0);
		this.setXmax(12.1);
		this.setYmin(4.1);
		this.setYmax(5.8);
		this.setFenotipo(new double[2]);
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

	public double getYmin() {
		return ymin;
	}
	
	public void setYmin(double ymin) {
		this.ymin = ymin;
	}
	
	public double getYmax() {
		return ymax;
	}
	
	public void setYmax(double ymax) {
		this.ymax = ymax;
	}

	public int calculoLongitudX(){
		return (int) Math.abs((Math.log10(1+((this.xmax-this.xmin)/this.getTolerancia()))/Math.log10(2)));
	}
	
	public int calculoLongitudY(){
		return (int) (Math.abs(Math.log10(1+((this.ymax-this.ymin)/this.getTolerancia()))/Math.log10(2)));
	}
	
	@Override
	public int calculoLongitudCromosoma(){
		int x = calculoLongitudX();
		int y = calculoLongitudY();
		return (x + y);
	}
	
	@Override
	public double evalua() {
		this.getFenotipo()[0] = this.getX();
		this.getFenotipo()[1] = this.getY();
		return 21.5 + ( this.getFenotipo()[0] * Math.sin(4 * Math.PI * this.getFenotipo()[0]) ) + ( this.getFenotipo()[1] * Math.sin(20 * Math.PI * this.getFenotipo()[1]));
	}
	
	@Override
	public double getX() {
		String v = "";
		for(int i = 0; i < this.calculoLongitudX(); i++)
			if(this.getGenes()[i])
				v = v + "1";
			else
				v = v + "0";		
		return (this.getXmin() + Integer.parseInt(v, 2) * ((this.getXmax() - this.getXmin())/(Math.pow(2, calculoLongitudX()) - 1)));
	}
	
	public double getY() {
		String v = "";
		for(int i = this.calculoLongitudX(); i < this.getLongitudCromosoma(); i++)
			if(this.getGenes()[i])
				v = v + "1";
			else
				v = v + "0";		
		return (this.getYmin() + Integer.parseInt(v, 2) * ((this.getYmax() - this.getYmin())/(Math.pow(2, calculoLongitudY()) - 1)));
	}
	
	@Override
	public double[] fenotipo() {
		this.getFenotipo()[0] = this.getX();
		this.getFenotipo()[1] = this.getY();
		return this.getFenotipo();
	}

}
