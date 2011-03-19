package AG.Cromosoma;

public class CromosomaEjemplo extends Cromosoma{

	@Override
	public void inicializaCromosoma() {
		this.setXmin(0.0);		
		this.setXmax(15.0);
		super.inicializaCromosoma();
	}

	@Override
	public double evalua() {
		double x = fenotipo();
		return Math.abs((x-5)/(2+Math.sin(x)));
	}

}