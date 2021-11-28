package com.psp.cronometro;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Contador;

public class CronometroController {

	@FXML
	private ImageView imageView1;
	@FXML
	private Label Label_Hora;
	@FXML
	private Label Label_Minuto;
	@FXML
	private Label Label_Segundo;
	@FXML
	private Button botonIniciar;
	@FXML
	private Button botonPausar;
	@FXML
	private Button botonReiniciar;

	Contador c = new Contador();
	Thread t = new Thread(c);
	boolean result=false;

	//Texto para los botones
	private String iniciar = "INICIAR";
	private String contando = "CONTANDO";
	private String pausar = "PAUSAR";
	private String reanudar = "REANUDAR";
	private String reiniciar = "REINICIAR";

	@FXML
	protected void initialize() {
	
		//logo app
		Image a = new Image(App.class.getResourceAsStream("icono.png"));
		imageView1.setImage(a);

		Label_Hora.textProperty().bind(c.getFraseHora());
		Label_Minuto.textProperty().bind(c.getFraseMinuto());
		Label_Segundo.textProperty().bind(c.getFraseSegundo());
		
		//CAMBIAR COLOR DE BOTON
		 botonIniciar.setStyle("-fx-background-color: MediumSeaGreen");
		 
		//HABILITAR O DESHABILITAR BOTONES
		 botonPausar.setDisable(true);
		 botonReiniciar.setDisable(true);
		 

	}

	public void Iniciar() {
	
		t=new Thread(c);
		t.start();
	
		c.suspendido.setSuspendido(false);

		Label_Hora.textProperty().bind(c.getFraseHora());
		Label_Minuto.textProperty().bind(c.getFraseMinuto());
		Label_Segundo.textProperty().bind(c.getFraseSegundo());
		
		//HABILITAR O DESHABILITAR BOTONES
		botonIniciar.setDisable(true);
		botonPausar.setDisable(false);
		botonReiniciar.setDisable(false);
		
		//AÑADIR TEXTO A BOTONES
		botonIniciar.setText(contando);
		botonIniciar.setStyle("");
		//CAMBIAR COLOR DE BOTON
		botonPausar.setStyle("-fx-background-color: ORANGERED");
		botonReiniciar.setStyle("-fx-background-color: 	LIGHTSKYBLUE");
	}


	public void Pausar() {
		if (botonPausar.getText().toString().equals(pausar)) {
	
			c.suspendido.setSuspendido(true);
			
			Label_Hora.textProperty().bind(c.getFraseHora());
			Label_Minuto.textProperty().bind(c.getFraseMinuto());
			Label_Segundo.textProperty().bind(c.getFraseSegundo());
			
			botonIniciar.setDisable(true);
			
			
			botonPausar.setText(reanudar);
			botonReiniciar.setDisable(true);
			//CAMBIAR COLOR DE BOTON
			botonPausar.setStyle("-fx-background-color: MediumSeaGreen");
			
		} else if (botonPausar.getText().toString().equals(reanudar)) {
			//CAMBIAR COLOR DE BOTON
			botonPausar.setStyle("-fx-background-color: ORANGERED");
			
			c.suspendido.setSuspendido(false);
			
			Label_Hora.textProperty().bind(c.getFraseHora());
			Label_Minuto.textProperty().bind(c.getFraseMinuto());
			Label_Segundo.textProperty().bind(c.getFraseSegundo());
			
			//AÑADIR TEXTO A BOTONES
			botonPausar.setText(pausar);
			
			//HABILITAR O DESHABILITAR BOTONES
			botonIniciar.setDisable(true);
			botonReiniciar.setDisable(false);
		}

	}
	public void Reiniciar() {
		

		c.suspendido.setSuspendido(true);
		t.interrupt();

		c.setHora(new SimpleIntegerProperty(0));
		c.setMinuto(new SimpleIntegerProperty(0));
		c.setSegundo(new SimpleIntegerProperty(0));

		/*
		 * Comprobar valor de Hora,Minuto y segundo 
		 * 
		System.out.println(c.getHora().toString());
		System.out.println(c.getMinuto().toString());
		System.out.println(c.getSegundo().toString());
		 */
	
		Label_Hora.textProperty().bind(c.getFraseHora());
		Label_Minuto.textProperty().bind(c.getFraseMinuto());
		Label_Segundo.textProperty().bind(c.getFraseSegundo());
		
		//AÑADIR TEXTO A BOTONES
		botonIniciar.setText(iniciar);
		
		//HABILITAR O DESHABILITAR BOTONES
		botonPausar.setDisable(true);
		botonIniciar.setDisable(false);
		
		//CAMBIAR COLOR DE BOTON
		botonIniciar.setStyle("-fx-background-color: MediumSeaGreen");

		
	}

}
