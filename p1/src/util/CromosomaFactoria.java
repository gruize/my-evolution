package util;

import AG.Cromosoma.Cromosoma;
import AG.Cromosoma.CromosomaEjemplo;
import AG.Cromosoma.CromosomaFuncion1;
import AG.Cromosoma.CromosomaFuncion2;
import AG.Cromosoma.CromosomaFuncion3;
import AG.Cromosoma.CromosomaFuncion4;
import AG.Cromosoma.CromosomaFuncion5;

public class CromosomaFactoria {

	public static Cromosoma crearCromosoma(Funciones tipo, double tolerancia){
        switch (tipo){
        	case Ejemplo:
        		return new CromosomaEjemplo(tolerancia);
        	case Funcion1: 
        		return new CromosomaFuncion1(tolerancia);
        	case Funcion2:
        		return new CromosomaFuncion2(tolerancia);
        	case Funcion3: 
        		return new CromosomaFuncion3(tolerancia);
        	case Funcion4:
        		return new CromosomaFuncion4(tolerancia);
        	case Funcion5:
        		return new CromosomaFuncion5(tolerancia);
        	default:
        		return null;
        }
    } 
	
}
