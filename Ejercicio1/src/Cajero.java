import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Cajero implements Runnable {
	private int id;
	private int tiempoMaximoPorCliente;
	private int maximaEspera; // tiempo máximo que estaremos sin atender antes de cerrar caja.
	private BlockingQueue q;
	
	public Cajero(int id, int tiempoPorCliente, int maximaEspera,int tiempoMaximoPorCliente, BlockingQueue q) {
		super();
		this.maximaEspera = maximaEspera;
		this.tiempoMaximoPorCliente = tiempoMaximoPorCliente;
		this.id = id;
		this.q = q;
	}

	@Override
	public void run() {
		
		Random rnd = new Random();
		
		// TODO: mientras hayan clientes...
		while  (q.isEmpty()!=true){
		
		try{
			// sacamos un cliente de la cola, imprimimos "CAJERO X ATENDIENDO CLIENTE Y"
			System.out.println("Cajero: "+this.id+ " Atendiendo cliente: "+ q.element().toString());
			// esperamos un tiempo aleatorio entre 1segundo y tiempoMaximoPorCliente
			// AYUDA: (int)(rnd.nextDouble() * tiempoMaximoPorCliente + 1);
			q.poll((int)(rnd.nextDouble() * tiempoMaximoPorCliente + 1), TimeUnit.SECONDS);
			
			// esperamos y imprimimos "CAJERO X FINALIZA CON CLIENTE Y. ATENDIDO EN T SEGUNDOS"
			// donde T es el tiempo que ha tardado en esperar en la cola + ser atendido.
			System.out.println("Cajero: "+this.id+ " Finaliza con cliente: "+ q.take() + " Atendido en "+
					(System.nanoTime() - (int)(rnd.nextDouble() * tiempoMaximoPorCliente + 1)) );
			
			// Si estamos más de "maximaEspera" segundos sin que hayan clientes imprimimos "CAJERO X CERRANDO."
			if ( System.nanoTime()>maximaEspera){
				System.out.println("CAJERO"+ this.id+" CERRANDO");
				break;
			}
		}catch (InterruptedException e){
			e.printStackTrace();
		}	
		
	}//while
	}//run
}
