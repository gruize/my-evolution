package Test;

import util.Funciones;
import util.Selecciones;
import AG.AlgoritmoGenetico;

public class TestFuncion3 {

	public static void main(String[] args) {
		AlgoritmoGenetico algGen = new AlgoritmoGenetico(4, 4, 0.7, 0.3, 0.5, Funciones.Funcion3, Selecciones.Ruleta, 0, false,0);
		algGen.inicializa();
		int tam = algGen.getTamPoblacion();
		int longitud = algGen.getPob().get(0).getLongitudCromosoma();
		
		
		System.out.print("***************Ahora se evalua la poblacion*************\n");
		
		
		algGen.evaluarPoblacion();
		
		System.out.print("Poblacion actual: \n");
		System.out.print("Genes\t\tFenotipo\t\tAptitud\t\t\tPuntuacion\t\tPuntuacionAcumulada\n");
		for(int i = 0; i < tam; i++){			
			String cromo = "";
			for(int j = 0; j < longitud; j++){
				
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
		
		
		while(algGen.getGeneracion() < algGen.getNumMaxGen()){		
			
			System.out.print("El mejor (genes): ");
			for(int j = 0; j < algGen.getElMejor().getGenes().length; j++)
				if(algGen.getElMejor().getGenes()[j])
					System.out.print("1");
				else
					System.out.print("0");
			System.out.print("\n");
			System.out.print("************Ahora se ejecuta la seleccion para la generacion " + algGen.getGeneracion() + ":\n");
						
			algGen.getSeleccion().ejecutaSeleccion(algGen);	
			
			System.out.print("Nueva poblacion actual: \n");
			for(int i = 0; i < tam; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
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
			
			System.out.print("************Ahora se ejecuta la reproduccion para la generacion " + algGen.getGeneracion() + ":\n");

			algGen.reproduccion();
			
			System.out.print("Poblacion despues del cruce: \n");
			for(int i = 0; i < tam; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
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
			
			System.out.print("************Ahora se ejecutan las mutaciones:\n");
						
			algGen.mutacion();		
			
			System.out.print("Poblacion actual despues de las mutaciones: \n");
			for(int i = 0; i < tam; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
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
			
			algGen.evaluarPoblacion();
			
			System.out.print("Poblacion actual final: \n");
			for(int i = 0; i < tam; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
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
			
		
			algGen.setGeneracion(algGen.getGeneracion() + 1);
			
		}
		
		System.out.print("El mejor (genes): ");
		for(int j = 0; j < algGen.getElMejor().getGenes().length; j++)
			if(algGen.getElMejor().getGenes()[j])
				System.out.print("1");
			else
				System.out.print("0");
		System.out.print("\n");
	}

}
