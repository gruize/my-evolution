package AG.Cromosoma;

public class CromosomaFuncion5 extends Cromosoma{
	
	//Valores minimo y maximo
	private double xmin;
	private double xmax; 
	
	public CromosomaFuncion5(double tolerancia){
		super(tolerancia);
		this.setXmin(-10);
		this.setXmax(10);
		this.setFenotipo(new double[2]);
		this.setAptitud(Double.MAX_VALUE);
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

	public int calculoCadaX(){
		return (int) Math.abs((Math.log10(1+((this.xmax-this.xmin)/this.getTolerancia()))/Math.log10(2)));
	}
	
	@Override
	public int calculoLongitudCromosoma(){		
		return calculoCadaX() * 2;
	}
	
	@Override
	public double evalua() {
		// TODO Modificar los fenotipos
		this.getFenotipo()[0] = this.getX1();
		this.getFenotipo()[1] = this.getX2();
		double sumatorioX1 = 0;
		double sumatorioX2 = 0;
		for(int i = 1; i <= 5; i++){
			sumatorioX1 = sumatorioX1 + (i * Math.cos(((i + 1) * this.getFenotipo()[0]) + i));			
		}
		for(int i = 1; i <= 5; i++){
			sumatorioX2 = sumatorioX2 + (i * Math.cos(((i + 1) * this.getFenotipo()[1]) + i));			
		}
		return sumatorioX1 * sumatorioX2;
	}
	
	@Override
	public double getX() {			
		return 0;
	}
	
	public double getX1(){
		String v = "";
		for(int j = 0; j < this.calculoCadaX(); j++)
			if(this.getGenes()[j])
				v = v + "1";
			else
				v = v + "0";		
		return (this.getXmin() + Integer.parseInt(v, 2) * ((this.getXmax() - this.getXmin())/(Math.pow(2, calculoCadaX()) - 1)));		
	}
	
	public double getX2(){
		String v = "";
		for(int j = calculoCadaX(); j < this.getLongitudCromosoma(); j++)
			if(this.getGenes()[j])
				v = v + "1";
			else
				v = v + "0";		
		return (this.getXmin() + Integer.parseInt(v, 2) * ((this.getXmax() - this.getXmin())/(Math.pow(2, calculoCadaX()) - 1)));		
	}
	
	@Override
	public double[] fenotipo() {		
		double[] fenotipo = new double[2];
		fenotipo[0] = this.getX1();
		fenotipo[1] = this.getX2();
		return fenotipo;
	}
}
