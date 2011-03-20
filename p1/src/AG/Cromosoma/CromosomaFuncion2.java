package AG.Cromosoma;

public class CromosomaFuncion2 extends Cromosoma{
	
	public CromosomaFuncion2(double tolerancia){
		super(tolerancia);
	}
	
	private double ymin = 4.1;
	private double ymax = 5.8;
	
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
	
	@Override
	public void inicializaCromosoma() {
		this.setXmin(-3.0);
		this.setXmax(12.1);
		super.inicializaCromosoma();
	}
	@Override
	public double evalua() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int calculoLongitudCromosoma(){
		return super.calculoLongitudCromosoma() + (int)(Math.abs(Math.log10(1+((this.ymax-this.ymin)/getTolerancia()))/Math.log10(2)));
	}
	@Override
	public void clone(Cromosoma cromosoma){
		super.clone(cromosoma);
		this.ymin = ((CromosomaFuncion2) cromosoma).getYmin();
		this.ymax = ((CromosomaFuncion2) cromosoma).getYmax();
	}
}
