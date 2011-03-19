package util;

import AG.Cromosoma.Cromosoma;
import AG.Cromosoma.CromosomaEjemplo;
import AG.Cromosoma.CromosomaFuncion1;
import AG.Cromosoma.CromosomaFuncion2;
import AG.Cromosoma.CromosomaFuncion3;
import AG.Cromosoma.CromosomaFuncion4;
import AG.Cromosoma.CromosomaFuncion5;

public class CromosomaFactoria {

	public static Cromosoma crearCromosoma(Funciones tipo){
        switch (tipo){
        	case Ejemplo:
        		return new CromosomaEjemplo();
        	case Funcion1: 
        		return new CromosomaFuncion1();
        	case Funcion2:
        		return new CromosomaFuncion2();
        	case Funcion3: 
        		return new CromosomaFuncion3();
        	case Funcion4:
        		return new CromosomaFuncion4();
        	case Funcion5:
        		return new CromosomaFuncion5();
        	default:
        		return null;
        }
    } 
	
}
