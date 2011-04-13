package util;

import AG.Seleccion.Seleccion;
import AG.Seleccion.SeleccionRuleta;
import AG.Seleccion.SeleccionTorneo;

public class SeleccionFactoria{

	public static Seleccion crearSeleccionador(Selecciones tipo){
        switch (tipo){
        	case Ruleta:
        		return new SeleccionRuleta();
        	case Torneo: 
        		return new SeleccionTorneo();
        	default:
        		return null;
        }
    }

	

}