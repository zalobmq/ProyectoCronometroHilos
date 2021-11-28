package model;

public class SolicitarSuspender {

	private boolean suspendido;//false esta corriendo /true esta parado

	 public synchronized void setSuspendido(boolean b) {
		 this.suspendido=b;
		 notifyAll();
		 
	 }
	 public boolean getSuspendido() {
		 return suspendido;
	 }
	 public synchronized void waitReanudar() {
		 while(this.suspendido==true) {
			 try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	 }


	}