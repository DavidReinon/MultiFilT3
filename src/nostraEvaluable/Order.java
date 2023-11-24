package ae01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Order extends JFrame {
    private JButton btnExecutar;
    private List<Piece> piecesList;
    private JTextField textFieldGuantitat_I;
    private JTextField textFieldGuantitat_O;
    private JTextField textFieldGuantitat_T;
    private JTextField textFieldGuantitat_J;
    private JTextField textFieldGuantitat_L;
    private JTextField textFieldGuantitat_S;
    private JTextField textFieldGuantitat_Z;
    private JTextField textFieldNomArxiu;

    public Order() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 511, 509);
        JPanel contentPane = new JPanel();

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(128, 128, 128));
        panel.setBounds(50, 11, 365, 427);
        contentPane.add(panel);
        panel.setLayout(null);
		
		JLabel lblgestio = new JLabel("Gestió de peces");
		lblgestio.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblgestio.setBounds(135, 25, 130, 14);
		panel.add(lblgestio);
		
		JLabel lblNom = new JLabel("Quantitat de peces de I:");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNom.setBounds(10, 70, 142, 14);
		panel.add(lblNom);
		
		JTextField textFieldGuantitat_I = new JTextField();
		textFieldGuantitat_I.setBounds(162, 68, 175, 20);
		panel.add(textFieldGuantitat_I);
		textFieldGuantitat_I.setColumns(10);
		
		JTextField textFieldGuantitat_O = new JTextField();
		textFieldGuantitat_O.setColumns(10);
		textFieldGuantitat_O.setBounds(162, 106, 175, 20);
		panel.add(textFieldGuantitat_O);
		
		
		JLabel lblQuantitatDePeces = new JLabel("Quantitat de peces de O:");
		lblQuantitatDePeces.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantitatDePeces.setBounds(10, 109, 142, 14);
		panel.add(lblQuantitatDePeces);
		
		textFieldGuantitat_T = new JTextField();
		textFieldGuantitat_T.setColumns(10);
		textFieldGuantitat_T.setBounds(162, 147, 175, 20);
		panel.add(textFieldGuantitat_T);
		
		JLabel lblQuantitatDePeces_2 = new JLabel("Quantitat de peces de T:");
		lblQuantitatDePeces_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantitatDePeces_2.setBounds(10, 150, 142, 14);
		panel.add(lblQuantitatDePeces_2);
		
		textFieldGuantitat_J = new JTextField();
		textFieldGuantitat_J.setColumns(10);
		textFieldGuantitat_J.setBounds(162, 187, 175, 20);
		panel.add(textFieldGuantitat_J);
		
		JLabel lblQuantitatDePeces_2_1 = new JLabel("Quantitat de peces de J:");
		lblQuantitatDePeces_2_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantitatDePeces_2_1.setBounds(10, 190, 142, 14);
		panel.add(lblQuantitatDePeces_2_1);
		
		textFieldGuantitat_L = new JTextField();
		textFieldGuantitat_L.setColumns(10);
		textFieldGuantitat_L.setBounds(162, 227, 175, 20);
		panel.add(textFieldGuantitat_L);
		
		JLabel lblQuantitatDePeces_2_1_1 = new JLabel("Quantitat de peces de L:");
		lblQuantitatDePeces_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantitatDePeces_2_1_1.setBounds(10, 230, 142, 14);
		panel.add(lblQuantitatDePeces_2_1_1);
		
		textFieldGuantitat_S = new JTextField();
		textFieldGuantitat_S.setColumns(10);
		textFieldGuantitat_S.setBounds(162, 267, 175, 20);
		panel.add(textFieldGuantitat_S);
		
		JLabel lblQuantitatDePeces_2_1_1_1 = new JLabel("Quantitat de peces de S:");
		lblQuantitatDePeces_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantitatDePeces_2_1_1_1.setBounds(10, 270, 142, 14);
		panel.add(lblQuantitatDePeces_2_1_1_1);
		
		textFieldGuantitat_Z = new JTextField();
		textFieldGuantitat_Z.setColumns(10);
		textFieldGuantitat_Z.setBounds(162, 308, 175, 20);
		panel.add(textFieldGuantitat_Z);
		
		JLabel lblQuantitatDePeces_2_1_1_1_1 = new JLabel("Quantitat de peces de Z:");
		lblQuantitatDePeces_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblQuantitatDePeces_2_1_1_1_1.setBounds(10, 311, 142, 14);
		panel.add(lblQuantitatDePeces_2_1_1_1_1);
		
		btnExecutar = new JButton("Executar");
		btnExecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantity_I = Integer.parseInt(textFieldGuantitat_I.getText());
                int quantity_O = Integer.parseInt(textFieldGuantitat_O.getText());
                int quantity_T = Integer.parseInt(textFieldGuantitat_T.getText());
                int quantity_J = Integer.parseInt(textFieldGuantitat_J.getText());
                int quantity_L = Integer.parseInt(textFieldGuantitat_L.getText());
                int quantity_S = Integer.parseInt(textFieldGuantitat_S.getText());
                int quantity_Z = Integer.parseInt(textFieldGuantitat_Z.getText());

                String arxiu = textFieldNomArxiu.getText();

                piecesList = new ArrayList<>();
                piecesList.add(new Piece("I", quantity_I));
                piecesList.add(new Piece("O", quantity_O));
                piecesList.add(new Piece("T", quantity_T));
                piecesList.add(new Piece("J", quantity_J));
                piecesList.add(new Piece("L", quantity_L));
                piecesList.add(new Piece("S", quantity_S));
                piecesList.add(new Piece("Z", quantity_Z));

                Manufacture manufacture = new Manufacture(piecesList, arxiu);
                manufacture.startManufacturing();
            }
        });
        btnExecutar.setBackground(Color.WHITE);
        btnExecutar.setBounds(135, 393, 101, 23);
        panel.add(btnExecutar);
        
        textFieldNomArxiu = new JTextField();
        textFieldNomArxiu.setColumns(10);
        textFieldNomArxiu.setBounds(162, 349, 175, 20);
        panel.add(textFieldNomArxiu);
        
        JLabel lblQuantitatDePeces_2_1_1_1_1_1 = new JLabel("Nom arxiu(opcional):");
        lblQuantitatDePeces_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblQuantitatDePeces_2_1_1_1_1_1.setBounds(10, 352, 142, 14);
        panel.add(lblQuantitatDePeces_2_1_1_1_1_1);

        setVisible(true);
    }

   //* private void manufacturePieces(String type, int quantity) {
        //String filename = "LOG_" + type + "_";

        // Llamada a la clase Manufacture para fabricar las piezas
       // Manufacture manufacture = new Manufacture(type, quantity, filename);
     //   manufacture.startManufacturing();
   // }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Order());
   }
}

