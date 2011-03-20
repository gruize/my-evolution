package Test;

import util.CromosomaFactoria;
import util.Funciones;
import util.Selecciones;
import AG.AlgoritmoGenetico;
import AG.Cromosoma.Cromosoma;

public class TestEjemplo {

	public static void main(String[] args) {
		AlgoritmoGenetico algGen = new AlgoritmoGenetico(4, 4, 0.7, 0.3, 0.5, Funciones.Ejemplo, Selecciones.Ruleta);
		algGen.inicializa();
		int tam = algGen.getTamPoblacion();
		int longitud = algGen.getPob()[0].getLongitudCromosoma();
		
		
		System.out.print("***************Ahora se evalua la población*************\n");
		
		
		algGen.evaluarPoblacion();
		while(algGen.getGeneracion() < algGen.getNumMaxGen()){		
			
			System.out.print("El mejor : " + algGen.getElMejor().getFenotipo() + "\n");
			System.out.print("************Ahora se ejecuta la seleccion para la generacion " + algGen.getGeneracion() + ":\n");
			
			
			Cromosoma[] nuevaPob = new Cromosoma[algGen.getTamPoblacion()];
			for(int i = 0; i < algGen.getTamPoblacion(); i++)
				nuevaPob[i] = CromosomaFactoria.crearCromosoma(algGen.getFuncion(),algGen.getTolerancia());
			algGen.getSeleccion().ejecutaSeleccion(algGen.getPob(), nuevaPob, algGen.getTamPoblacion());	
			
			
			System.out.print("Poblacion actual: \n");
			for(int i = 0; i < tam; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
					if(algGen.getPob()[i].getGenes()[j])
						cromo = cromo + "1";
					else
						cromo = cromo + "0";
				}
				System.out.print(cromo + "\t" + algGen.getPob()[i].getFenotipo() + "\t" +  algGen.getPob()[i].getAptitud() + "\t" + algGen.getPob()[i].getPuntuacion() + "\t" + algGen.getPob()[i].getPuntuacionAcumulada() + "\n");
			}
			
			System.out.print("Nueva poblacion: \n");
			for(int i = 0; i < tam; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){					
					if(nuevaPob[i].getGenes()[j])
						cromo = cromo + "1";
					else
						cromo = cromo + "0";
				}
				System.out.print(cromo + "\t" + nuevaPob[i].getFenotipo() + "\t" +  nuevaPob[i].getAptitud() + "\t" + nuevaPob[i].getPuntuacion() + "\t" + nuevaPob[i].getPuntuacionAcumulada() + "\n");
			}
			System.out.print("************Ahora se ejecuta la seleccion para la reproduccion " + algGen.getGeneracion() + ":\n");
			
			
			algGen.reproduccion(nuevaPob);
			
			System.out.print("Poblacion despues del cruce: \n");
			for(int i = 0; i < tam; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
					if(nuevaPob[i].getGenes()[j])
						cromo = cromo + "1";
					else
						cromo = cromo + "0";
				}
				System.out.print(cromo + "\t" + nuevaPob[i].getFenotipo() + "\t" +  nuevaPob[i].getAptitud() + "\t" + nuevaPob[i].getPuntuacion() + "\t" + nuevaPob[i].getPuntuacionAcumulada() + "\n");
			}
			
			for(int i = 0; i < algGen.getTamPoblacion(); i++)
				algGen.getPob()[i].clone(nuevaPob[i]);
			
			
			System.out.print("************Ahora se ejecutan las mutaciones:\n");
			
			
			algGen.mutacion();		
			
			System.out.print("Poblacion actual despues de las mutaciones: \n");
			for(int i = 0; i < tam; i++){			
				String cromo = "";
				for(int j = 0; j < longitud; j++){
					
					if(algGen.getPob()[i].getGenes()[j])
						cromo = cromo + "1";
					else
						cromo = cromo + "0";
				}
				System.out.print(cromo + "\t" + algGen.getPob()[i].getFenotipo() + "\t" +  algGen.getPob()[i].getAptitud() + "\t" + algGen.getPob()[i].getPuntuacion() + "\t" + algGen.getPob()[i].getPuntuacionAcumulada() + "\n");
			}
			
			algGen.evaluarPoblacion();			
			algGen.setGeneracion(algGen.getGeneracion() + 1);
			
		}
	}

}
