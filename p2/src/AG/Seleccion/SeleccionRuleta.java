package AG.Seleccion;

import util.CromosomaFactoria;
import util.Funciones;
import AG.AlgoritmoGenetico;
import AG.Cromosoma.Cromosoma;

public class SeleccionRuleta implements Seleccion {

	public void ejecutaSeleccion(AlgoritmoGenetico algGen){
		double probabilidad;
		int posSuperviviente;		
		Cromosoma[] nuevaPob = new Cromosoma[algGen.getTamPoblacion()];
		for(int i = 0; i < algGen.getTamPoblacion(); i++)
			nuevaPob[i] = CromosomaFactoria.crearCromosoma(algGen.getFuncion(),algGen.getTolerancia(),algGen.getN());
		for (int i = 0; i < algGen.getTamPoblacion(); i++){
			probabilidad = Math.random();
			posSuperviviente = 0;
			while ( (probabilidad > algGen.getPob().get(posSuperviviente).getPuntuacionAcumulada()) &&
					(posSuperviviente < algGen.getTamPoblacion()) )
				posSuperviviente++;			
			nuevaPob[i].clone(algGen.getPob().get(posSuperviviente));
		}
		for (int i = 0; i < algGen.getTamPoblacion(); i++){
			algGen.getPob().get(i).clone(nuevaPob[i]);
		}
	}
	
}
