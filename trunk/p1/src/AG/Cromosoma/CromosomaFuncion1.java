package AG.Cromosoma;

public class CromosomaFuncion1 extends Cromosoma{
	
	@Override
	public void inicializaCromosoma() {
		this.setXmin(0.0);		
		this.setXmax(32.0);
		super.inicializaCromosoma();
	}
	
	@Override
	public double evalua() {
		
		return 0;
	}
	
}