package AG.Cromosoma;

public class CromosomaFuncion1 extends Cromosoma{
	
	public CromosomaFuncion1(double tolerancia){
		super(tolerancia);
	}
	
	@Override
	public void inicializaCromosoma() {
		this.setXmin(0.0);		
		this.setXmax(32.0);
		super.inicializaCromosoma();
	}
	
	@Override
	public double evalua() {
		this.setFenotipo(this.fenotipo());
		return 20 + Math.E - (20 * Math.pow(Math.E, (-0.2*Math.abs(this.getFenotipo())))) - Math.pow(Math.E, Math.cos(2 * Math.PI * this.getFenotipo()));
	}
	
}
