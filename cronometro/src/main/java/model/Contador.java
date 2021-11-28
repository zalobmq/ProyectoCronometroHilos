package model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.application.Platform;


public class Contador implements Runnable {
	//
	private IntegerProperty hora;
	private IntegerProperty minuto;
	private IntegerProperty segundo;
	//
	private StringProperty StringHora;
	private StringProperty StringMinuto;
	private StringProperty StringSegundo;
	
	public SolicitarSuspender suspendido=new SolicitarSuspender();
	
	
	public Contador() {
		this.hora = new SimpleIntegerProperty(0);
		this.minuto = new SimpleIntegerProperty(0);
		this.segundo = new SimpleIntegerProperty(0);
		this.StringHora = new SimpleStringProperty("00");
		this.StringMinuto = new SimpleStringProperty("00");
		this.StringSegundo = new SimpleStringProperty("00");
		this.suspendido.setSuspendido(false);


	}
	
	public IntegerProperty getHora() {
		return hora;
	}
	public void setHora(IntegerProperty hora) {
		this.hora = hora;
	}
	public IntegerProperty getMinuto() {
		return minuto;
	}
	public void setMinuto(IntegerProperty minuto) {
		this.minuto = minuto;
	}
	public IntegerProperty getSegundo() {
		return segundo;
	}
	public void setSegundo(IntegerProperty segundo) {
		this.segundo = segundo;
	}
	public StringProperty getFraseHora() {
		return StringHora;
	}
	public void setFraseHora(StringProperty fraseHora) {
		this.StringHora = fraseHora;
	}
	public StringProperty getFraseMinuto() {
		return StringMinuto;
	}
	public void setFraseMinuto(StringProperty fraseMinuto) {
		this.StringMinuto = fraseMinuto;
	}
	public StringProperty getFraseSegundo() {
		return StringSegundo;
	}
	public void setFraseSegundo(StringProperty fraseSegundo) {
		this.StringSegundo = fraseSegundo;
	}
	public SolicitarSuspender getSuspedido() {
		return suspendido;
	}
	public void setSuspedido(SolicitarSuspender suspedido) {
		this.suspendido = suspedido;
	}
	
	public void run() {
		// TODO Auto-generated method stub

		while (!this.suspendido.getSuspendido()) {
			
			Platform.runLater(() -> {
				segundo.set(segundo.get() + 1);
				if(segundo.get()<10) {
					StringSegundo.set("0"+segundo.get());
				}else {
					StringSegundo.set(segundo.get()+"");
				}
				
			});

			if (segundo.get() == 59) {
				Platform.runLater(() -> {
					minuto.set(minuto.get() + 1);
					if(minuto.get()<10) {
						StringMinuto.set("0"+minuto.get());
					}else {
						StringMinuto.set(minuto.get()+"");
					}
					segundo.set(0);
					StringSegundo.set(segundo.get()+"0");
				});

				if (minuto.get() == 59) {
					Platform.runLater(() -> {
						minuto.set(0);
						StringMinuto.set(minuto.get()+"");
						hora.set(hora.get() + 1);
						StringHora.set(hora.get()+"");
					});

					if (hora.get() == 24) {
						Platform.runLater(() -> {
							hora.set(0);
							StringHora.set(hora.get()+"");
						});

					}
				}
			}
			try {
				
				Thread.sleep(1000);
				this.suspendido.waitReanudar();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//System.exit(0);
				Platform.runLater(() -> {
					segundo.set(0);
					StringSegundo.set(segundo.get()+"0");
					
					minuto.set(0);
					StringMinuto.set(minuto.get()+"0");
					
					hora.set(0);
					StringHora.set(hora.get()+"0");
				});
				
			}
			
				
		}
		
		}
	
}