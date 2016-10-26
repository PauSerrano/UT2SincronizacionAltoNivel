import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DemoColaCarrefour {
	public static void main(String[] args) {
		// TODO: creamos nuestra BloquingQueue
		
		BlockingQueue<Cliente> q = new ArrayBlockingQueue<Cliente>(1000);
		
		Object[] elementos; 
		//hay que new thread , new generador clientes con paramentros y darle al start y pasarle el generadoe de cliestes, que es una tarea rounable
		// TODO: Creamos y arrancamos el generador de clientes. Por ejemplo:
		// 30 clientes iniciales
		// 2 clientes nuevos por segundo
		// 100 segundos antes de cerrar las puertas del super...
		GeneradorClientes gClientes = new GeneradorClientes(30,2,10,q);
		Thread tclientes = new Thread (gClientes);
		tclientes.start();
		
		
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(q.isEmpty());
			System.out.println(q.toArray().length);
		
		
		// 3 cajeros para atender
//		Thread tcajero = null;
//		for(int i=0; i<3; i++) {
			// TODO: Creamos y arrancamos los cajeros en sus Threads
//			Cajero cajero = new Cajero(i,1,15,7,q);
			
			//hay que poner put para crear los clientes
			//bucle white mientras
			//para los cajeros poll
//		}
		Cajero cajero1 = new Cajero(1,1,15,100,q);
		Cajero cajero2 = new Cajero(2,1,15,100,q);
		Cajero cajero3 = new Cajero(3,1,15,100,q);
		Thread tCajero1 = new Thread (cajero1);
		Thread tCajero2 = new Thread (cajero2);
		Thread tCajero3 = new Thread (cajero3);
		tCajero1.start();
		tCajero2.start();
		tCajero3.start();
		
		
		// TODO: Imprimir cada 2segundos el tamaño de la cola
		
		
		while (!tCajero1.isAlive() & !tCajero2.isAlive() & !tCajero3.isAlive()){
			elementos = q.toArray();
			System.out.println("Numero de clisentes: "+ elementos.length);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		try {
			tCajero1.join();
			tCajero2.join();
			tCajero3.join();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		// TODO: Esperamos a que se vayan los clientes y cajeros para cerrar el super
		// y imprimimos "SUPERMERCADO CERRADO."
		
		System.out.println("SUPERMERCADO CERRADO");
		
		
		
	}
}
