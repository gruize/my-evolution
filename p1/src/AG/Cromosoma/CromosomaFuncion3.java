package AG.Cromosoma;

public class CromosomaFuncion3 extends Cromosoma{

	public CromosomaFuncion3(double tolerancia){
		super(tolerancia);
	}
	
	@Override
	public void inicializaCromosoma() {
		this.setXmin(-250.0);
		this.setXmax(250.0);
		super.inicializaCromosoma();
	}
	@Override
	public double evalua() {
		// TODO Auto-generated method stub
		return 0;
	}
}
