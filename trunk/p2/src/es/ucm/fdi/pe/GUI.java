package es.ucm.fdi.pe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

import util.Funciones;
import util.Selecciones;

import AG.AlgoritmoGenetico;

import es.ucm.fdi.pe.ConfigPanel.ChoiceOption;
import es.ucm.fdi.pe.ConfigPanel.ConfigListener;
import es.ucm.fdi.pe.ConfigPanel.DoubleOption;
import es.ucm.fdi.pe.ConfigPanel.IntegerOption;

public class GUI extends JFrame {

	private static final long serialVersionUID = -8090911838320644551L;

	public GUI() {
        OutputStream output;
        String name = "AGTraza"+ Calendar.getInstance().getTime() +".txt";
		try {
			output = new FileOutputStream(name.replace(":", "."));
			PrintStream printOut = new PrintStream(output);
			System.setOut(printOut);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        setSize(680, 667);
		setTitle("Algoritmos Geneticos");
		//setLayout(new BorderLayout());
		setPreferredSize(new Dimension(507, 520));
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panelCentral = new JPanel();	
		JPanel panelBoton = new JPanel();	
		
		final Figura formulario = new Figura("Formulario");
		
		// crea un panel central y lo asocia con la primera figura
		final ConfigPanel<Figura> cp = creaPanelConfiguracion();
		
		// asocia el panel con la figura
		cp.setTarget(formulario);
		
		// carga los valores de la figura en el panel
		cp.initialize();		
		// crea una etiqueta que dice si todo es valido
		final String textoTodoValido = "Todos los campos OK";
		final String textoHayErrores = "Hay errores en algunos campos";
		final JLabel valido = new JLabel(textoHayErrores);
		// este evento se lanza cada vez que la validez cambia
		cp.addConfigListener(new ConfigListener() {
			@Override
			public void configChanged(boolean isConfigValid) {
				valido.setText(isConfigValid ? textoTodoValido: textoHayErrores);								
			}
		});		
		JButton boton = new JButton();
		boton.setText("Ejecutar");		
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(valido.getText().equals(textoTodoValido)){
					System.out.println("********************************************************************************************************************************************************************");
					System.out.println("Algoritmo genetico - Traza de la funcion " + formulario.getFuncion().toString() + " con seleccion de " + formulario.getSeleccion().toString());
					System.out.println("********************************************************************************************************************************************************************");
					AlgoritmoGenetico algGen = new AlgoritmoGenetico(formulario.getTamPoblacion(), formulario.getNumMaxGen(), formulario.getProbCruce(), formulario.getProbMutacion(), formulario.getTolerancia(), formulario.getFuncion(), formulario.getSeleccion(), formulario.getN(), formulario.getElitismo(), formulario.getElite());							
					algGen.ejecucion();
									
					Plot2DPanel resultados = new Plot2DPanel();	
					BaseLabel titulo = new BaseLabel("Algoritmos Geneticos. " + algGen.getFuncion().toString() + ".", Color.BLUE, 0.5, 1.1);
					titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20)); // fuente para el titulo
	            	resultados.addPlotable(titulo);
					resultados.addLegend("SOUTH");
					resultados.setAxisLabels("Generaciones","");
					
			        
			        double[] generacion = new double[algGen.getGeneracion()];
			        for(int i = 0; i < algGen.getNumMaxGen(); i++)
			        	generacion[i] = i;	
			        resultados.addLinePlot("Mejor de la generacion",generacion,algGen.getMejorGeneracion());
			        resultados.addLinePlot("Media de la generacion",generacion, algGen.getMediaGeneracion());
			        resultados.addLinePlot("Mejor absoluto",generacion, algGen.getMejorAbsoluto());
			        JFrame frame = new JFrame("Graficas de resultados");
			        frame.setSize(600, 600);			        
			        frame.setContentPane(resultados);
			        frame.setVisible(true);
			        frame.add(resultados);
			        JPanel panelValores = new JPanel();
			        frame.add(panelValores,BorderLayout.SOUTH);
			        JLabel valores = new JLabel();
			        valores.setText("Algo");
			        panelValores.add(valores);
			        frame.add(valores,BorderLayout.SOUTH);
			       
			    }else
			    	valido.setText("No se puede ejecutar. Todos los campos no son validos.");				
			}
		});	
		panelCentral.add(cp);
		add(panelCentral);
		add(panelBoton,BorderLayout.SOUTH);
		panelBoton.add(valido);
		panelBoton.add(boton);		
		
	}
	
	public ConfigPanel<Figura> creaPanelConfiguracion() {		
		Selecciones[] selecciones = new Selecciones[] {Selecciones.Ruleta, Selecciones.Torneo};
		Funciones[] funciones = new Funciones[] {Funciones.Funcion1, Funciones.Funcion2, Funciones.Funcion3, Funciones.Funcion4, Funciones.Funcion5};
		ConfigPanel<Figura> config = new ConfigPanel<Figura>();
		
		// se pueden anyadir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
		config.addOption(new ChoiceOption<Figura>(
				"Funcion: ",
				"Posibles funciones a aplicar",
				"funcion",
				funciones));
		config.addOption(new ChoiceOption<Figura>(
				"Seleccion: ",
				"Tipo de seleccion a aplicar",
				"seleccion",
				selecciones));
		config.addOption(new DoubleOption<Figura>(
				"Prob. Cruce: ",
				"Probabilidad de cruce",
				"probCruce",
				0,
				1,
				1));
		config.addOption(new DoubleOption<Figura>(
				"Prob. Mutacion: ",
				"Probabilidad de mutacion",
				"probMutacion",
				0,
				1,
				1));
		config.addOption(new IntegerOption<Figura>(
				"Tam. Poblacion: ", 
				"Tamanyo de la poblacion",
				"tamPoblacion",
				1, 
				Integer.MAX_VALUE));
		config.addOption(new IntegerOption<Figura>(
				"Num. Generaciones: ", 
				"Numero de generaciones",
				"numMaxGen",
				2, 
				Integer.MAX_VALUE));
		config.addOption(new DoubleOption<Figura>(
				"Tolerancia: ",
				"Tolerancia",
				"tolerancia",
				0,
				1,
				1));
		config.addOption(new IntegerOption<Figura>(
				"Num. Valores: ", 
				"Numero de valores",
				"n",
				1, 
				Integer.MAX_VALUE));	
		/**
		config.addOption(new DoubleOption<Figura>(
				"Elitismo: ",
				"Porcentaje de elitismo",
				"elite",
				0,
				100,
				1));*/
			  // y ahora ya cerramos el formulario		
		config.endOptions();		
		return config;
	}
	
	
	// construye y muestra la interfaz
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.setSize(600, 600);
		gui.setVisible(true);	
	}

	/**Formulario*/
	public static class Figura {
		private String nombre;
		
		private Funciones funcion;
		private Selecciones seleccion;
		private double probCruce = 0.5;
		private double probMutacion = 0.5;
		private int tamPoblacion = 10;
		private int numMaxGen = 10;
		private double tolerancia = 0.001;
		private int n = 1;
		private boolean elitismo = false;
		private double elite = 0;
		
		public Figura(String nombre) {
			this.nombre = nombre;
		}
		
		// getters y setters (compactados para reducir lineas)
		public Funciones getFuncion() {	return funcion;	}
		public void setFuncion(Funciones funcion) {	this.funcion = funcion;	}
		public Selecciones getSeleccion() {	return seleccion;	}
		public void setSeleccion(Selecciones seleccion) {	this.seleccion = seleccion;	}
		public double getProbCruce() { return probCruce; }
		public void setProbCruce(double probCruce) { this.probCruce = probCruce; }
		public double getProbMutacion() { return probMutacion; }
		public void setProbMutacion(double probMutacion) { this.probMutacion = probMutacion; }
		public int getTamPoblacion() { return tamPoblacion; }
		public void setTamPoblacion(int tamPoblacion) { this.tamPoblacion = tamPoblacion; }
		public int getNumMaxGen() { return numMaxGen; }
		public void setNumMaxGen(int numMaxGen) { this.numMaxGen = numMaxGen; }
		public double getTolerancia() { return tolerancia; }
		public void setTolerancia(double tolerancia) { this.tolerancia = tolerancia; }
		public int getN() { return n; }
		public void setN(int n) { this.n = n; }
		public boolean getElitismo() {  return elitismo;  }
		public void setElitismo(boolean elitismo) {  this.elitismo = elitismo;  }
		public double getElite() {  return elite;  }
		public void setElite(double elite) {  this.elite = elite;  }
	}	
	
}
