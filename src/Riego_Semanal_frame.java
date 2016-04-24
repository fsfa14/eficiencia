import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.Toolkit;

import javax.swing.JButton;

import control.ControlBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Riego_Semanal_frame extends JFrame {

	private JPanel contentPane;
	private JTable datos_riego=null;
	private ControlBD cBD = new ControlBD();



	/**
	 * Create the frame.
	 * @param anio 
	 */
	public Riego_Semanal_frame(final String nom_campo, final int idCampo, final String nom_Empresa, final int idEmpresa, final int id_sist, final String nom_cuart, final int id_cuart, final String mes_anio, final String anio) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FelipeSebasti\u00E1n\\Desktop\\Mas Recursos Naturales\\Proyecto_Ef_Energetica\\src\\logo_2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 28, 434, 261);
		contentPane.add(scrollPane);
		
		//meter consulta a la BD
		String[] columns=new String[]{"Semana","Cant_Horas","Cant_Agua"};
		cBD.conectar();
		String riegos[][] = cBD.getRiegosSemanales(id_cuart);
			
		datos_riego = new JTable(riegos,columns);
		//  riegos,columns
		datos_riego.setEnabled(true);
		scrollPane.setViewportView(datos_riego);
		//scrollPane.setViewportView(datos_riego);
		
		JButton btnVolver = new JButton("Volver...");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Cuartel_frame_3 nuevo = new Cuartel_frame_3(nom_campo,idCampo,nom_Empresa,idEmpresa,id_sist,nom_cuart,id_cuart,mes_anio,anio);
				nuevo.setVisible(true);
				dispose();
				
			}
		});
		btnVolver.setBounds(0, 0, 89, 23);
		contentPane.add(btnVolver);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(99, 0, 89, 23);
		contentPane.add(btnAgregar);
		
		
		setLocationRelativeTo(null);
	}
}
