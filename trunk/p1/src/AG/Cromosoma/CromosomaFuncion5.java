package AG.Cromosoma;

public class CromosomaFuncion5 extends Cromosoma{
	
	public CromosomaFuncion5(double tolerancia){
		super(tolerancia);
	}
	
	@Override
	public void inicializaCromosoma() {
		this.setXmin(-10.0);
		this.setXmax(10.0);
		super.inicializaCromosoma();
	}
	@Override
	public double evalua() {
		// TODO Auto-generated method stub
		return 0;
	}
}
