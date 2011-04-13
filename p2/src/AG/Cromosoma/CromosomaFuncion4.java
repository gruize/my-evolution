package AG.Cromosoma;

public class CromosomaFuncion4 extends Cromosoma{

	//Valores minimo y maximo
	private double xmin;
	private double xmax; 
	private int n;
	
	public CromosomaFuncion4(double tolerancia, int n){
		super(tolerancia);
		this.setXmin(0);
		this.setXmax(100);
		this.setN(n);
		this.setFenotipo(new double[n]);	
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

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int calculoEachN(){
		return (int) Math.abs((Math.log10(1+((this.xmax-this.xmin)/this.getTolerancia()))/Math.log10(2)));
	}
	
	@Override
	public int calculoLongitudCromosoma(){
		return (int) (calculoEachN() * this.n);
	}
	
	public double getX(int i) {
		int posInicial = i * calculoEachN();
		int posFinal = posInicial + calculoEachN();
		String v = "";
		for(int j = posInicial; j < posFinal; j++)
			if(this.getGenes()[j])
				v = v + "1";
			else
				v = v + "0";		
		return (this.getXmin() + Integer.parseInt(v, 2) * ((this.getXmax() - this.getXmin())/(Math.pow(2, calculoEachN()) - 1)));
	}
	
	@Override
	public double evalua() {	
		double sumatorio = 0;
		for(int i = 1; i <= this.n; i++){
			this.getFenotipo()[i - 1] = getX(i - 1);
			sumatorio = sumatorio + (- this.getFenotipo()[i - 1] * Math.sin(Math.sqrt(Math.abs(this.getFenotipo()[i - 1]))));
		}
		return sumatorio;
	}
	
	@Override
	public double getX() {
		return 0;
	}

	@Override
	public double[] fenotipo() {		
		double[] fenotipo = new double[this.n];
		for(int i = 0; i < this.n; i++)
			fenotipo[i] = this.getX(i);
		return fenotipo;
	}
}
