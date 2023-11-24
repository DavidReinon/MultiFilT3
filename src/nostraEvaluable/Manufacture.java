package ae01;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Manufacture {
	private List<Piece> piecesList = new CopyOnWriteArrayList<>();
	private List<Piece> fabricatedPieces = new CopyOnWriteArrayList<>();
    private String filename;
    private Queue<Integer> machines;

    public Manufacture(List<Piece> piecesList, String filename) {
        this.piecesList = piecesList;
        this.filename = filename;
        this.machines = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            machines.offer(i);
        }
    }
    /**
     * Hace una Copia de la Lista de Piezas, Itera sobre Piezas y Cantidad, 
     * Inicia Hilos de Fabricación y Escribe en el Archivo
     */
    public void startManufacturing() {
        List<Piece> piecesCopy = new ArrayList<>(piecesList);
        List<Thread> threads = new ArrayList<>();

        for (Piece piece : piecesCopy) {
            for (int i = 0; i < piece.getQuantity(); i++) {
                Integer machineNumber = machines.poll();
                if (machineNumber != null) {
                    PieceManufacturingThread thread = new PieceManufacturingThread(piece.getType(), machineNumber, piece.getQuantity());
                    thread.start();
                    threads.add(thread);
                } else {
                    System.out.println("No hay máquinas disponibles.");
                }
            }
        }

        // Esperar a que todos los hilos terminen antes de finalizar
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Después de que todos los hilos hayan terminado, escribir en el archivo
        writeListToFile();
    }

 /**
  * Método para obtener la instancia de Piece actual basada en el nombre
  * @param pieceName
  * @return
  */
    private synchronized Piece getCurrentPiece1(String pieceName) {
        List<Piece> copyList = new ArrayList<>(fabricatedPieces);

        for (Piece piece : copyList) {
            if (piece.toString().equals(pieceName)) {
                return piece;
            }
        }

        return null;
    }



    /**
     * Crea y Agrega Nuevas Pieza, 
     * @param piece
     * @param quantity
     */
    private synchronized void addPieceToList(String piece, int quantity) {
        // Crear un nuevo objeto Piece y agregarlo a la lista
        Piece newPiece = new Piece(piece, quantity);
        piecesList.add(newPiece);
        System.out.println(newPiece);
        machines.offer(newPiece.getType().hashCode() % 8); // Simulación de liberar la máquina

        // Almacenar las piezas fabricadas en la lista separada
        fabricatedPieces.add(newPiece);
    }

    /**
     * Genera el Nombre del Archivo y su escritura
     */
    private void writeListToFile() {
        String fileNameWithTimestamp;
        if (filename.isEmpty()) {
            // Si no se proporciona un nombre de archivo, usa un timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            fileNameWithTimestamp = "LOG_" + timestamp + ".txt";
        } else {
            // Si se proporciona un nombre de archivo, úsalo y asegúrate de que tenga la extensión .txt
            if (filename.toLowerCase().endsWith(".txt")) {
                fileNameWithTimestamp = filename;
            } else {
                fileNameWithTimestamp = filename + ".txt";
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileNameWithTimestamp))) {
            for (Piece piece : fabricatedPieces) {
                // Aquí estoy asumiendo que tienes un método toString() en la clase Piece
                writer.write(piece.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }








    /**
     * Tiempo de Fabricación y Procesamiento y Verificación de Finalización
     */
    private class PieceManufacturingThread extends Thread {
        private String type;
        private int machineNumber;
        private int quantity; // Nuevo campo

        public PieceManufacturingThread(String type, int machineNumber, int quantity) {
            this.type = type;
            this.machineNumber = machineNumber;
            this.quantity = quantity; // Inicializar el nuevo campo
        }

        @Override
        public void run() {
            // Simulación del tiempo de fabricación
            int timeManufacture = getTimeManufacture(type);
            procesFabricacio(timeManufacture);

            // Construir el nombre de la pieza y añadirla a la lista
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String pieceName = type + "_" + timestamp;
            addPieceToList(pieceName, quantity); // Usar la cantidad directamente

            // Obtener la pieza actual
            Piece currentPiece = getCurrentPiece1(pieceName);

            // Si todas las piezas han sido fabricadas, escribir la lista en el archivo
            if (currentPiece != null && piecesList.size() == currentPiece.getQuantity()) {
                writeListToFile();
            }
        }
    }


        /**
         *  Método para obtener la instancia de Piece actual basada en el nombre
         * @param pieceName
         * @return
         */
        private Piece getCurrentPiece(String pieceName) {
            for (Piece piece : piecesList) {
                if (piece.toString().equals(pieceName)) {
                    return piece;
                }
            }
            return null;
        }

        /**
         * Para poner el tieme spane
         * @param type
         * @return
         */
        private int getTimeManufacture(String type) {
            switch (type) {
                case "I":
                    return 1000;
                case "O":
                    return 2000;
                case "T":
                    return 3000;
                case "J":
                case "L":
                    return 4000;
                case "S":
                case "Z":
                    return 5000;
                default:
                    return 0;
            }
        }

        public void procesFabricacio(int tempsFabricacio) {
            long tempsInici = System.currentTimeMillis();
            long tempsFinal = tempsInici + tempsFabricacio; // Temps de fabricació en milisegons
            int iteracions = 0;
            while (System.currentTimeMillis() < tempsFinal) {
                // Realitzar iteracions addicionals per a consumir processador (simula ocupacio de maquina)
                iteracions++;
            }
        }
    }

