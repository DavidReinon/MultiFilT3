package exercicis;

public class Ej5KFC implements Runnable {

	private static int numeroAlesTotal = 100;
	private static int numeroAlesConsumides = 0;
	private static int numeroCompanys = 30;

	synchronized public static void ConsumirAla(String nombre, int numeroRandom) {

		if (numeroRandom + numeroAlesConsumides > numeroAlesTotal) {
			System.out.println("NO." + nombre + " vol agarrar mes de les que queden");
			return;
		}
		System.out.println(nombre + " consumeix les seguents alites: " + numeroRandom);
		numeroAlesConsumides += numeroRandom;
		return;

	}

	@Override
	public void run() {
		String nombre = Thread.currentThread().getName();
		int numeroRandom = (int) (Math.random() * 10 + 1);
		ConsumirAla(nombre, numeroRandom);
	}

	public static void main(String[] args) {
		Ej5KFC claseRunabble = new Ej5KFC();
		Thread t;
		for (int i = 0; i < numeroCompanys; i++) {
			t = new Thread(claseRunabble);
			t.setName("Amigo " + (i + 1));
			t.start();
		}
		try {
			Thread.sleep(2000); //Asegurarse que acaban todos los hilos
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("=> Nombre totas de ales consumides: " + numeroAlesConsumides);
	}

}
