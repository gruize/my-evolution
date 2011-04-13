package Test;

import AG.AlgoritmoGenetico;
import util.Funciones;
import util.Selecciones;

public class TestOrdenCromosomas {

	public static void main(String[] args) {
		AlgoritmoGenetico algGen = new AlgoritmoGenetico(4, 1, 0.7, 0.7, 0.0001, Funciones.Funcion1, Selecciones.Ruleta, 5, true,0);
		algGen.inicializa();
		
		System.out.print("Poblacion actual: \n");
		System.out.print("Genes\tFenotipo\t\tAptitud\t\t\tPuntuacion\t\tPuntuacionAcumulada\n");
		for(int i = 0; i < algGen.getTamPoblacion(); i++){			
			String cromo = "";
			for(int j = 0; j < algGen.getPob().get(0).getLongitudCromosoma(); j++){
				
				if(algGen.getPob().get(i).getGenes()[j])
					cromo = cromo + "1";
				else
					cromo = cromo + "0";
			}
			System.out.print(cromo + "\t" );
			for(int j = 0; j < algGen.getPob().get(i).getFenotipo().length; j++)				
				System.out.print(algGen.getPob().get(i).getFenotipo()[j] + "\t");
			System.out.print(algGen.getPob().get(i).getAptitud() + "\t" + algGen.getPob().get(i).getPuntuacion() + "\t" + algGen.getPob().get(i).getPuntuacionAcumulada() + "\n");
		}
		
		algGen.separaMejores();
		
		
		
	}

}
