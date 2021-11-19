package com.psp.cronometro;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CronometroController {
	
	@FXML
	ImageView imageView1;
	@FXML
    protected void initialize () {
		
		Image a = new Image(App.class.getResourceAsStream("icono.png"));
		imageView1.setImage(a);

	 
	}
	

}

