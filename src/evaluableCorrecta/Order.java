package es.florida.psp_ae1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;

public class Order extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_I;
	private JTextField textField_O;
	private JTextField textField_T;
	private JTextField textField_J;
	private JTextField textField_L;
	private JTextField textField_S;
	private JTextField textField_Z;
	private JTextField txtFichero;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton rdbtnConsola, rdbtnFichero;

	/**
	 * Método principal de la clase Order con interfaz gráfica
	 * 
	 * @param args No requiere parámetros de entrada
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order frame = new Order();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Método que crea la interfaz gráfica de la clase Order y define las acciones realizadas al hacer clic en el botón Lanzar
	 */
	public Order() {
		setTitle("Tetrominoes Inc.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTetrominosInc = new JLabel("TETROMINOES INC.");
		lblTetrominosInc.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTetrominosInc.setBounds(24, 20, 661, 26);
		contentPane.add(lblTetrominosInc);

		JLabel lblTypes = new JLabel("TYPES:");
		lblTypes.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTypes.setBounds(24, 56, 73, 17);
		contentPane.add(lblTypes);

		JLabel lblType = new JLabel("TYPE");
		lblType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblType.setBounds(75, 169, 73, 17);
		contentPane.add(lblType);

		JLabel lblNumber = new JLabel("NUMBER");
		lblNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNumber.setBounds(153, 169, 73, 17);
		contentPane.add(lblNumber);

		JLabel lblTipoI = new JLabel("I");
		lblTipoI.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoI.setBounds(75, 205, 45, 17);
		contentPane.add(lblTipoI);

		JLabel lblTipoO = new JLabel("O");
		lblTipoO.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoO.setBounds(75, 230, 45, 17);
		contentPane.add(lblTipoO);

		JLabel lblTipoT = new JLabel("T");
		lblTipoT.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoT.setBounds(75, 257, 45, 17);
		contentPane.add(lblTipoT);

		JLabel lblTipoJ = new JLabel("J");
		lblTipoJ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoJ.setBounds(75, 284, 45, 17);
		contentPane.add(lblTipoJ);

		textField_I = new JTextField();
		textField_I.setText("5");
		textField_I.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_I.setBounds(155, 204, 64, 19);
		contentPane.add(textField_I);
		textField_I.setColumns(10);

		textField_O = new JTextField();
		textField_O.setText("5");
		textField_O.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_O.setColumns(10);
		textField_O.setBounds(155, 229, 64, 19);
		contentPane.add(textField_O);

		textField_T = new JTextField();
		textField_T.setText("5");
		textField_T.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_T.setColumns(10);
		textField_T.setBounds(155, 256, 64, 19);
		contentPane.add(textField_T);

		textField_J = new JTextField();
		textField_J.setText("5");
		textField_J.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_J.setColumns(10);
		textField_J.setBounds(155, 283, 64, 19);
		contentPane.add(textField_J);

		JButton btn_lanzar = new JButton("Launch");
		btn_lanzar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String numI = textField_I.getText();
				String numO = textField_O.getText();
				String numT = textField_T.getText();
				String numJ = textField_J.getText();
				String numL = textField_L.getText();
				String numS = textField_S.getText();
				String numZ = textField_Z.getText();
				boolean outputConsola = rdbtnConsola.isSelected();
				String fichero = txtFichero.getText();
				llamadaManufacture(numI, numO, numT, numJ, numL, numS, numZ, outputConsola, fichero);
			}

		});
		btn_lanzar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_lanzar.setBounds(271, 363, 85, 21);
		contentPane.add(btn_lanzar);
		
		JLabel lblTipoL = new JLabel("L");
		lblTipoL.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoL.setBounds(75, 311, 45, 17);
		contentPane.add(lblTipoL);
		
		JLabel lblTipoS = new JLabel("S");
		lblTipoS.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoS.setBounds(75, 338, 45, 17);
		contentPane.add(lblTipoS);
		
		JLabel lblTipoZ = new JLabel("Z");
		lblTipoZ.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTipoZ.setBounds(75, 365, 45, 17);
		contentPane.add(lblTipoZ);
		
		textField_L = new JTextField();
		textField_L.setText("5");
		textField_L.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_L.setColumns(10);
		textField_L.setBounds(155, 312, 64, 19);
		contentPane.add(textField_L);
		
		textField_S = new JTextField();
		textField_S.setText("5");
		textField_S.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_S.setColumns(10);
		textField_S.setBounds(155, 339, 64, 19);
		contentPane.add(textField_S);
		
		textField_Z = new JTextField();
		textField_Z.setText("5");
		textField_Z.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_Z.setColumns(10);
		textField_Z.setBounds(155, 366, 64, 19);
		contentPane.add(textField_Z);
		
		JLabel lblManage = new JLabel("Manage factory output...");
		lblManage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblManage.setBounds(271, 230, 257, 17);
		contentPane.add(lblManage);
		
		rdbtnConsola = new JRadioButton("Show in console");
		rdbtnConsola.setSelected(true);
		buttonGroup.add(rdbtnConsola);
		rdbtnConsola.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnConsola.setBounds(271, 272, 178, 21);
		contentPane.add(rdbtnConsola);
		
		rdbtnFichero = new JRadioButton("Save in file:");
		buttonGroup.add(rdbtnFichero);
		rdbtnFichero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnFichero.setBounds(271, 309, 110, 21);
		contentPane.add(rdbtnFichero);
		
		txtFichero = new JTextField();
		txtFichero.setText(".txt");
		txtFichero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtFichero.setColumns(10);
		txtFichero.setBounds(387, 310, 187, 19);
		contentPane.add(txtFichero);
		
		JPanel panel = new JPanel();
		panel.setBounds(107, 56, 566, 87);
		try {
			BufferedImage myPicture = ImageIO.read(new File("tetris.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			panel.add(picLabel);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		contentPane.add(panel);
	}

	/**
	 * Método que llama a la aplicación Manufacture
	 * 
	 * @param tipoI String con el número de piezas I a fabricar
	 * @param tipoO String con el número de piezas I a fabricar
	 * @param tipoT String con el número de piezas I a fabricar
	 * @param tipoJ String con el número de piezas I a fabricar
	 * @param tipoL String con el número de piezas I a fabricar
	 * @param tipoS String con el número de piezas I a fabricar
	 * @param tipoZ String con el número de piezas I a fabricar
	 * @param outputConsola boolean que indica si la salida de Manufacture se recogerá por consola
	 * @param nombreFichero String con el nombre del archivo donde guardar las salidas de la aplicación Manufacture
	 */
	public void llamadaManufacture(String tipoI, String tipoO, String tipoT, String tipoJ, String tipoL, String tipoS, String tipoZ, boolean outputConsola, String nombreFichero) {

		try {
			
			String clase = "es.florida.psp_ae1.Manufacture";
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;

			List<String> command = new ArrayList<>();
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(tipoI);
			command.add(tipoO);
			command.add(tipoT);
			command.add(tipoJ);
			command.add(tipoL);
			command.add(tipoS);
			command.add(tipoZ);

			long tiempoInicio = System.nanoTime();
			ProcessBuilder builder = new ProcessBuilder(command);
			if (outputConsola) {
				Process process = builder.inheritIO().start();
				process.waitFor();
			} else {
				File fichero = new File(nombreFichero);
				builder.redirectOutput(fichero);
				Process process = builder.start();
				process.waitFor();
			}
			
			long tiempoFin = System.nanoTime();
			long duracion = (tiempoFin - tiempoInicio) / 1000000; // milisegundos
			JOptionPane.showMessageDialog(null, "Tiempo ejecucion total: " + duracion + " ms", "PROCESO FINALIZADO",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(),	"ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
