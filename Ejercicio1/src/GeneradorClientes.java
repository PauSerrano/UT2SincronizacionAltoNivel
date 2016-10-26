import java.util.concurrent.BlockingQueue;

public class GeneradorClientes implements Runnable {
	private int clientesIniciales;
	private int clientesPorSegundo;
	private long tiempoMaximo;
	private BlockingQueue<Cliente> q;
	
	public GeneradorClientes(int clientesIniciales, int clientesPorSegundo, long tiempoMaximo, BlockingQueue q) {
		super();
		this.clientesIniciales = clientesIniciales;
		this.clientesPorSegundo = clientesPorSegundo;
		this.tiempoMaximo = tiempoMaximo;
		this.q = q;
	}
	
	@Override
	public void run() {
		long tiempoInicial = System.nanoTime();
		// TODO: generamos "clientesIniciales" y los encolamos
		for (int i=0; i < clientesIniciales; i++){
			Cliente c = new Cliente(i);
			//System.out.println(i);
			try {
				q.put(c);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// mientras no nos pasemos del tiempoMaximo
		int j = clientesIniciales;
		
		while ((System.nanoTime() - tiempoInicial) < tiempoMaximo) {
		
		//System.out.println(j);
			for (int i=0; i< clientesPorSegundo; i++){
			
			Cliente c = new Cliente(j+i);
			
			try {
				q.put(c);
				// TODO: esperar y generar cliente según "clientesPorSegundo"
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			j++;
			
		}
		
		// TODO: Imprimir "Cerrando supermercado, ya no entran más clientes."
		System.out.println("Cerrando supermercado, ya no entran mas clientes");
	}

}

