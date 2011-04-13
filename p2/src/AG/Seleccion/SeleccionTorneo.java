package AG.Seleccion;

import util.CromosomaFactoria;
import util.Funciones;
import AG.AlgoritmoGenetico;
import AG.Cromosoma.Cromosoma;

public class SeleccionTorneo implements Seleccion {

	@Override
	public void ejecutaSeleccion(AlgoritmoGenetico algGen) {
		int pos1, pos2;		
		Cromosoma[] nuevaPob = new Cromosoma[algGen.getTamPoblacion()];
		for(int i = 0; i < algGen.getTamPoblacion(); i++)
			nuevaPob[i] = CromosomaFactoria.crearCromosoma(algGen.getFuncion(),algGen.getTolerancia(),algGen.getN());
		for(int i = 0; i < algGen.getTamPoblacion(); i++){
			pos1 = (int) (Math.random() * algGen.getTamPoblacion());
			pos2 = (int) (Math.random() * algGen.getTamPoblacion());
			if(!algGen.isFuncionDeMinimizacion())
				if(algGen.getPob().get(pos1).getAptitud() >= algGen.getPob().get(pos2).getAptitud())
					nuevaPob[i].clone(algGen.getPob().get(pos1));
				else
					nuevaPob[i].clone(algGen.getPob().get(pos2));
			else
				if(algGen.getPob().get(pos1).getAptitud() <= algGen.getPob().get(pos2).getAptitud())
					nuevaPob[i].clone(algGen.getPob().get(pos1));
				else
					nuevaPob[i].clone(algGen.getPob().get(pos2));
		}		
		for (int i = 0; i < algGen.getTamPoblacion(); i++){
			algGen.getPob().get(i).clone(nuevaPob[i]);
		}
	}

}
