package evaluableCorrecta;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ThreadTetromino implements Runnable {

	String tipo;
	ArrayList<String> listaPedido;

	/**
	 * Método constructor de la clase ThreadTetromino.
	 * 
	 * @param tipo        String con el tipo de tetromino a fabricar.
	 * @param listaPedido ArrayList de Strings con la lista de tetrominos
	 *                    fabricados.
	 */
	ThreadTetromino(String tipo, ArrayList<String> listaPedido) {
		this.tipo = tipo;
		this.listaPedido = listaPedido;
	}

	/**
	 * Método run del hilo que simula la fabricación de un tetromino con un tiempo
	 * asignado en función del tipo.
	 */
	@Override
	public void run() {
		int[] tiemposTetromino = { 1000, 2000, 3000, 4000, 4000, 5000, 5000 }; // I, O, T, J, L, S, Z
		int tFabricacion = 0;
		switch (tipo) {
		case "I":
			tFabricacion = tiemposTetromino[0];
			break;
		case "O":
			tFabricacion = tiemposTetromino[1];
			break;
		case "T":
			tFabricacion = tiemposTetromino[2];
			break;
		case "J":
			tFabricacion = tiemposTetromino[3];
			break;
		case "L":
			tFabricacion = tiemposTetromino[4];
			break;
		case "S":
			tFabricacion = tiemposTetromino[5];
			break;
		case "Z":
			tFabricacion = tiemposTetromino[6];
			break;
		default:
			break;
		}
		procesoFabricacion(tFabricacion);
		String formato = "YYYYMMdd_HHmmss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		String timeStamp = simpleDateFormat.format(new Date());
		String strInsercion = tipo + "_" + timeStamp;
		synchronized (listaPedido) {
			System.out.println(Thread.currentThread().getName() + ": " + strInsercion);
			listaPedido.add(strInsercion);
		}
	}

	/**
	 * Método que simula el tiempo empleado por la máquina en la fabricación
	 * 
	 * @param tFabricacion Tiempo de fabricación según el tipo de tetrónimo
	 */
	public static void procesoFabricacion(int tFabricacion) {
		long tIni = System.currentTimeMillis();
		long tFin = tIni + tFabricacion;
		int iteraciones = 0;
		while (System.currentTimeMillis() < tFin) {
			iteraciones++;
		}
	}

}
