package AG.Cromosoma;

public class CromosomaFuncion4 extends Cromosoma{

	public CromosomaFuncion4(double tolerancia){
		super(tolerancia);
	}
	
	@Override
	public void inicializaCromosoma() {
		this.setXmin(0.0);
		this.setXmax(100.0);
		super.inicializaCromosoma();
	}
	@Override
	public double evalua() {
		// TODO Auto-generated method stub
		return 0;
	}
}
