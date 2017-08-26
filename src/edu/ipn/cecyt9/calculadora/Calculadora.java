
package edu.ipn.cecyt9.calculadora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * Interfaz para nuestra calculadora basica
 * 
 * @author:  emmanuel 
 * @version:  1.0 
 */
public class Calculadora extends JFrame {

	/**
	 * Generamos la clase llamada Calculadora
         * Implementamos el JFrame
	 */
	private static final long serialVersionUID = 1583724102189855698L;

	/** Será el número tecleado */
	JTextField pantalla;

	/** Guarda el resultado de la operacion anterior o el número tecleado */
	double resultado;

        double resultado2;
	/** para guardar la operacion a realizar */
	String operacion;

	/** Los paneles donde colocaremos los botones */
	JPanel panelNumeros, panelOperaciones;

	/** Indica si estamos iniciando o no una operación */
	boolean nuevaOperacion = true;

	/**
	 * Constructor. Crea los botones y componentes de la calculadora
	 */
	public Calculadora() {
		super();
		setSize(500, 500);
		setTitle("Calculadora Simple");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		/**
                 * Vamos a dibujar sobre el panel
                 */
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

                /**
                 * Creamos la sección en donde se mostrará el resultado
                 */
		pantalla = new JTextField("0", 20);
		pantalla.setBorder(new EmptyBorder(4, 4, 4, 4));
		pantalla.setFont(new Font("Arial", Font.BOLD, 25));
		pantalla.setHorizontalAlignment(JTextField.RIGHT);
		pantalla.setEditable(false);
		pantalla.setBackground(Color.WHITE);
		panel.add("North", pantalla);
                
                /**
                 * Creamos el margen de la calculadora
                 */
		panelNumeros = new JPanel();
		panelNumeros.setLayout(new GridLayout(4, 3));
		panelNumeros.setBorder(new EmptyBorder(4, 4, 4, 4));
                
                /**
                 * Se acomodan los numeros y botones
                 */
		for (int n = 9; n >= 0; n--) {
			nuevoBotonNumerico("" + n);
		}
                /**
                 * Creamos el boton punto
                 */
		nuevoBotonNumerico(".");
                
                /**
                 * Centramos los números
                 */

		panel.add("Center", panelNumeros);
                
                /**
                 * Creamos la sección en donde se mostrarán las operaciones
                 */
		panelOperaciones = new JPanel();
		panelOperaciones.setLayout(new GridLayout(6, 1));
		panelOperaciones.setBorder(new EmptyBorder(4, 4, 4, 4));
                
                /**
                 * Creamos los botones correspondientes a las operaciones
                 */
		nuevoBotonOperacion("+");
		nuevoBotonOperacion("-");
		nuevoBotonOperacion("*");
		nuevoBotonOperacion("/");
                nuevoBotonOperacion("√");
                nuevoBotonOperacion("^");
                nuevoBotonOperacion("sin");
                nuevoBotonOperacion("cos");
                nuevoBotonOperacion("tan");
                nuevoBotonOperacion("=");
		nuevoBotonOperacion("CE");

		panel.add("East", panelOperaciones);

		validate();
	}

	/**
	 * Crea un boton del teclado numérico y enlaza sus eventos con el listener
	 * correspondiente
	 * 
	 * @param digito
	 *            boton a crear
	 */
	private void nuevoBotonNumerico(String digito) {
		JButton btn = new JButton();
		btn.setText(digito);
		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				numeroPulsado(btn.getText());
			}
		});

		panelNumeros.add(btn);
	}
        //Cierre del método nuevoBotonNumerico

	/**
	 * Crea un botón de operacion y lo enlaza con sus eventos.
	 * 
	 * @param operacion
	 */
	private void nuevoBotonOperacion(String operacion) {
		JButton btn = new JButton(operacion);
		btn.setForeground(Color.RED);

		btn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent evt) {
				JButton btn = (JButton) evt.getSource();
				operacionPulsado(btn.getText());
			}
		});

		panelOperaciones.add(btn);
	}
        //Cierre del metodo nuevoBotonOperacion

	/**
	 * Gestiona las pulsaciones de teclas numéricas
	 * 
	 * @param digito
	 *            tecla pulsada
	 */
	private void numeroPulsado(String digito) {
		if (pantalla.getText().equals("0") || nuevaOperacion) {
			pantalla.setText(digito);
		} else {
			pantalla.setText(pantalla.getText() + digito);
		}
		nuevaOperacion = false;
	}
        //Cierre del método numeroPulsado

	/**
	 * Gestiona el gestiona las pulsaciones de teclas de operación
	 * 
	 * @param tecla
	 */
	private void operacionPulsado(String tecla) {
		if (tecla.equals("=")) {
			calcularResultado();
		} else if (tecla.equals("CE")) {
			resultado = 0;
			pantalla.setText("");
			nuevaOperacion = true;
		} else {
			operacion = tecla;
			if ((resultado > 0) && !nuevaOperacion) {
				calcularResultado();
			} else {
				resultado = new Double(pantalla.getText());
			}
		}

		nuevaOperacion = true;
	}
        //Cierre del método operacionPulsado

	/**
	 * Calcula el resultado y lo muestra por pantalla
	 */
	private void calcularResultado() {
		if (operacion.equals("+")) {
			resultado += new Double(pantalla.getText());
		} else if (operacion.equals("-")) {
			resultado -= new Double(pantalla.getText());
		} else if (operacion.equals("/")) {
			resultado /= new Double(pantalla.getText());
		} else if (operacion.equals("*")) {
			resultado *= new Double(pantalla.getText());
		}else if (operacion.equals("√")) {
                    resultado = new Double(pantalla.getText());
                    resultado = Math.sqrt(resultado);
		}else if (operacion.equals("^")) {
                        resultado = new Double(pantalla.getText());
			resultado = Math.pow(resultado, 2);
		}else if (operacion.equals("sin")) {
			resultado = new Double(pantalla.getText());
                        resultado = Math.sin(resultado);
		}else if (operacion.equals("cos")) {
			resultado = new Double(pantalla.getText());
                        resultado = Math.cos(resultado);
		}else if (operacion.equals("tan")) {
			resultado = new Double(pantalla.getText());
                        resultado = Math.tan(resultado);

		}
                

		pantalla.setText("" + resultado);
		operacion = "";
	}
        //Cierre del metodo
}
//Cierre de la clase y del ejemplo "CALCULADORA BÁSICA"
