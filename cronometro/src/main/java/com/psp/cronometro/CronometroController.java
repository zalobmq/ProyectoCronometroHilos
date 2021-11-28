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
	private Button botonParar;
	@FXML
	private Button botonReiniciar;

	Contador c = new Contador();
	Thread t = new Thread(c);
	boolean result=false;

	private String iniciar = "INICIAR";
	
	private String contando = "CONTANDO";
	private String pausar = "PAUSAR";
	private String reanudar = "REANUDAR";
	private String reiniciar = "REINICIAR";

	@FXML
	protected void initialize() {
		
	
		
			

		Image a = new Image(App.class.getResourceAsStream("icono.png"));
		imageView1.setImage(a);

		Label_Hora.textProperty().bind(c.getFraseHora());
		Label_Minuto.textProperty().bind(c.getFraseMinuto());
		Label_Segundo.textProperty().bind(c.getFraseSegundo());
		
		

	}

	public void Iniciar() {
	
		t=new Thread(c);
		t.start();
	
		c.suspendido.setSuspendido(false);

			
			
		

		Label_Hora.textProperty().bind(c.getFraseHora());
		Label_Minuto.textProperty().bind(c.getFraseMinuto());
		Label_Segundo.textProperty().bind(c.getFraseSegundo());
		botonIniciar.setDisable(true);
		botonParar.setDisable(false);
		botonIniciar.setText(contando);
	}


	public void Pausar() {
		if (botonParar.getText().toString().equals(pausar)) {
	
			c.suspendido.setSuspendido(true);
			
			Label_Hora.textProperty().bind(c.getFraseHora());
			Label_Minuto.textProperty().bind(c.getFraseMinuto());
			Label_Segundo.textProperty().bind(c.getFraseSegundo());
			
			botonIniciar.setDisable(true);
			
			
			botonParar.setText(reanudar);
			botonReiniciar.setDisable(true);
			
		} else if (botonParar.getText().toString().equals(reanudar)) {
			c.suspendido.setSuspendido(false);
			
			Label_Hora.textProperty().bind(c.getFraseHora());
			Label_Minuto.textProperty().bind(c.getFraseMinuto());
			Label_Segundo.textProperty().bind(c.getFraseSegundo());
			
			botonIniciar.setDisable(true);
			
			botonParar.setText(pausar);
			botonReiniciar.setDisable(false);
		}

	}
	public void Reiniciar() {
		

c.suspendido.setSuspendido(true);
	t.interrupt();

		c.setHora(new SimpleIntegerProperty(0));
		c.setMinuto(new SimpleIntegerProperty(0));
		c.setSegundo(new SimpleIntegerProperty(0));

		
		System.out.println(c.getHora().toString());
		System.out.println(c.getMinuto().toString());
		System.out.println(c.getSegundo().toString());

	
		Label_Hora.textProperty().bind(c.getFraseHora());
		Label_Minuto.textProperty().bind(c.getFraseMinuto());
		Label_Segundo.textProperty().bind(c.getFraseSegundo());
		
		botonIniciar.setDisable(false);
		
		
		
	
		
		botonIniciar.setText(iniciar);
		
		botonIniciar.setDisable(false);
		botonParar.setDisable(true);
		
	}

}
