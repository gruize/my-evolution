package AG.Cromosoma;

public class CromosomaEjemplo extends Cromosoma{
	
	public CromosomaEjemplo(double tolerancia){
		super(tolerancia);
	}
	
	@Override
	public void inicializaCromosoma() {		
		this.setXmin(0.0);		
		this.setXmax(15.0);
		super.inicializaCromosoma();
		
	}

	@Override
	public double evalua() {
		this.setFenotipo(fenotipo());		
		return Math.abs((this.getFenotipo()-5)/(2+Math.sin(this.getFenotipo())));
	}

}