package es.ucm.fdi.pe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;
import org.math.plot.plots.Plot;

import util.CromosomaFactoria;
import util.Funciones;
import util.Selecciones;

import AG.AlgoritmoGenetico;
import AG.Cromosoma.Cromosoma;

import es.ucm.fdi.pe.ConfigPanel.ChoiceOption;
import es.ucm.fdi.pe.ConfigPanel.ConfigListener;
import es.ucm.fdi.pe.ConfigPanel.DoubleOption;
import es.ucm.fdi.pe.ConfigPanel.IntegerOption;

public class GUI extends JFrame {

	private static final long serialVersionUID = -8090911838320644551L;

	public GUI() {
		setSize(680, 667);
		setTitle("Algoritmos Geneticos");
		setPreferredSize(new Dimension(507, 520));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panelCentral = new JPanel(new GridLayout(3, 2));
		add(panelCentral,BorderLayout.LINE_START);
		
		/**
		// crea dos figuras
		*/
		final Figura f1 = new Figura("Primera");
		/**
		final Figura f2 = new Figura("Segunda");
		*/
		
		// crea un panel central y lo asocia con la primera figura
		final ConfigPanel<Figura> cp = creaPanelConfiguracion();
		
		// asocia el panel con la figura
		cp.setTarget(f1);
		
		// carga los valores de la figura en el panel
		cp.initialize();		
		//add(cp, BorderLayout.CENTER);

		panelCentral.add(cp);
		
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
		/**
		// crea una etiqueta que indica la figura que se esta editando
		final JLabel panelEnEdicion = new JLabel("Editando figura 1");
		add(panelEnEdicion, BorderLayout.NORTH);
		*/
		// usado por todos los botones
		JButton boton;

		boton = new JButton();
		boton.setBounds(new Rectangle(222, 461, 157, 25));
		boton.setText("Ejecutar");
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AlgoritmoGenetico algGen = new AlgoritmoGenetico(f1.getTamPoblacion(), f1.getNumMaxGen(), f1.getProbCruce(), f1.getProbMutacion(), f1.getTolerancia(), f1.getFuncion(), f1.getSeleccion());				
				algGen.inicializa();
				int tam = algGen.getTamPoblacion();
				int longitud = algGen.getPob()[0].getLongitudCromosoma();
				
				
				System.out.print("***************Ahora se evalua la poblaciÃ³n*************\n");
				
				
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
				Plot2DPanel plot = new Plot2DPanel();
/**
		        BaseLabel title = new BaseLabel(text, Color.BLACK, 0.5, 1.1);
		        title.setFont(new Font("Arial", Font.BOLD, 15));
		        plot.addPlotable(title);
		        plot.addLegend("SOUTH");

		        plot.setAxisLabels("Núm. Generaciones", "Valor");
		        // Ahora anyadimos las graficas
		        plot.addLinePlot("Gráfica de generaciones/mejores individuos",
		                generaciones, mejoresIndividuos);
		        plot.addLinePlot("Gráfica de generaciones/medias población",
		                generaciones, medias);
		        plot.getAxis(0).setLabelPosition(0.5, -0.15);
		        plot.getAxis(1).setLabelPosition(-0.15, 0.5);*/
		        // Las mostramos
		        JFrame frame = new JFrame("Gráficas de resultados");
		        frame.setSize(600, 600);
		        //frame.setContentPane(Plot);
		        frame.setVisible(true);
			}
		});
		panelCentral.add(boton);	
		panelCentral.add(valido);
	}
	
	public ConfigPanel<Figura> creaPanelConfiguracion() {		
		Selecciones[] selecciones = new Selecciones[] {Selecciones.Ruleta};
		Funciones[] funciones = new Funciones[] {Funciones.Ejemplo,Funciones.Funcion1, Funciones.Funcion2, Funciones.Funcion3, Funciones.Funcion4, Funciones.Funcion5};
		ConfigPanel<Figura> config = new ConfigPanel<Figura>();
		
		// se pueden añadir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
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
				"Tamaño de la población",
				"tamPoblacion",
				1, 
				10));
		config.addOption(new IntegerOption<Figura>(
				"Num. Generaciones: ", 
				"Numero de generaciones",
				"numMaxGen",
				2, 
				10));
		config.addOption(new DoubleOption<Figura>(
				"Tolerancia: ",
				"Tolerancia",
				"tolerancia",
				0,
				1,
				1));
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
	
	// --- clases de ejemplo

	/** una figura */
	public static class Figura {
		private String nombre;
		
		private Funciones funcion;
		private Selecciones seleccion;
		private double probCruce = 0;
		private double probMutacion = 0;
		private int tamPoblacion = 1;
		private int numMaxGen = 2;
		private double tolerancia = 1;
		
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
	}	
	
}
