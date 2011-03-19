package AG.Seleccion;

import AG.Cromosoma.Cromosoma;

public class SeleccionRuleta implements Seleccion {

	public void ejecutaSeleccion(Cromosoma[] pob,Cromosoma[] nuevaPob,int tamPoblacion){
		double probabilidad;
		int posSuperviviente;		
		for (int i = 0; i < tamPoblacion; i++){
			probabilidad = Math.random();
			posSuperviviente = 0;
			while ( (probabilidad > pob[posSuperviviente].getPuntuacionAcumulada()) &&
					(posSuperviviente < tamPoblacion) )
				posSuperviviente++;			
			nuevaPob[i].clone(pob[posSuperviviente]);
		}
	}
	
}
