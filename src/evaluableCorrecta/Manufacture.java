package evaluableCorrecta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class Manufacture {

	/**
	 * Clase principal de la aplicación Manufacture, que recibe un pedido por
	 * argumentos y lanza los hilos para la fabricación de piezas.
	 * 
	 * @param args Array de String con el número de piezas a fabricar de cada tipo
	 */
	public static void main(String[] args) {

		int numI = Integer.parseInt(args[0]);
		int numO = Integer.parseInt(args[1]);
		int numT = Integer.parseInt(args[2]);
		int numJ = Integer.parseInt(args[3]);
		int numL = Integer.parseInt(args[4]);
		int numS = Integer.parseInt(args[5]);
		int numZ = Integer.parseInt(args[6]);

		int total = numI + numO + numT + numJ + numL + numS + numZ;
		int capacidad = 8;
		ArrayList<String> pedido = new ArrayList<String>();
		for (int i = 0; i < numI; i++)
			pedido.add("I");
		for (int i = 0; i < numO; i++)
			pedido.add("O");
		for (int i = 0; i < numT; i++)
			pedido.add("T");
		for (int i = 0; i < numJ; i++)
			pedido.add("J");
		for (int i = 0; i < numL; i++)
			pedido.add("L");
		for (int i = 0; i < numS; i++)
			pedido.add("S");
		for (int i = 0; i < numZ; i++)
			pedido.add("Z");

		ArrayList<String> listaPedido = new ArrayList<String>();
		Thread[] loteHilos = new Thread[capacidad];
		int hilosLanzados = 0;
		for (int i = 0; i < total; i++) {
			// System.out.println("Hilos activos " + ThreadTretomino.numHilos);
			if (hilosLanzados == capacidad) {
				for (int j = 0; j < capacidad; j++) {
					System.err.println(">>> La maquina ha alcanzado su capacidad maxima. Esperando a que finalice el lote <<<");
					try {
						loteHilos[j].join();
						hilosLanzados--;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			ThreadTetromino hc = new ThreadTetromino(pedido.get(i), listaPedido);
			loteHilos[hilosLanzados] = new Thread(hc);
			loteHilos[hilosLanzados].start();
			System.out.println("Inicio de fabricacion de tetromino " + (i + 1) + "/" + total + ": tipo " + pedido.get(i)
					+ " (" + loteHilos[hilosLanzados].getName() + ")");
			hilosLanzados++;

		}
		while (listaPedido.size() < total) {
			try {
				Thread.sleep(1000);
				System.out.println("Todos los hilos lanzados -> Esperando a que finalice el proceso "
						+ listaPedido.size() + "/" + total);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
		}
		String formato = "YYYYMMdd_HHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		String timeStamp = simpleDateFormat.format(new Date());
		File log = new File("LOG_" + timeStamp + ".txt");
		try {
			FileWriter fw = new FileWriter(log);
			BufferedWriter bw = new BufferedWriter(fw);
			for (String item : listaPedido) {
				bw.write(item + "\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nNUMERO DE TETROMINOS FABRICADOS: " + listaPedido.size());

	}

}
