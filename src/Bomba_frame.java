import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import control.ControlBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Bomba_frame extends JFrame {

	private ControlBD cBD = new ControlBD();
	private JPanel contentPane;
	private JTextField modelo;
	private JTextField pot_tbjo;
	private JTextField alimentacion;
	private JTextField tipo_bomba;
	private JTextField npsh;
	private JTextField pot_nom;
	private JTextField eficiencia;
	private JCheckBox chckbxVariador;




	/**
	 * Create the frame.
	 * @param id_sist 
	 * @param id_sist 
	 * @wbp.parser.constructor
	 */
	public Bomba_frame(final String nom_campo, final int idCampo, final String nom_Empresa, final int idEmpresa, final int id_sist) {
		setTitle("Nueva Bomba");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FelipeSebasti\u00E1n\\Desktop\\Mas Recursos Naturales\\Proyecto_Ef_Energetica\\src\\logo_2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		
		JButton button = new JButton("Volver...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
				nuevo.setVisible(true);
				dispose();				
				
			}
		});
		button.setBounds(10, 11, 107, 23);
		contentPane.add(button);
		
		JLabel lblModelorodeterpm = new JLabel("Modelo/Rodete/R.P.M.");
		lblModelorodeterpm.setBounds(10, 45, 138, 14);
		contentPane.add(lblModelorodeterpm);
		
		modelo = new JTextField();
		modelo.setColumns(10);
		modelo.setBounds(10, 59, 138, 20);
		contentPane.add(modelo);
		
		JLabel lblConsumo = new JLabel("Potencia de Trabajo");
		lblConsumo.setBounds(10, 85, 107, 14);
		contentPane.add(lblConsumo);
		
		pot_tbjo = new JTextField();
		pot_tbjo.setColumns(10);
		pot_tbjo.setBounds(10, 99, 138, 20);
		contentPane.add(pot_tbjo);
		
		JLabel lblFuenteDeAlimentacin = new JLabel("Fuente de Alimentaci\u00F3n");
		lblFuenteDeAlimentacin.setBounds(10, 124, 138, 14);
		contentPane.add(lblFuenteDeAlimentacin);
		
		alimentacion = new JTextField();
		alimentacion.setColumns(10);
		alimentacion.setBounds(10, 141, 138, 20);
		contentPane.add(alimentacion);
		
		JLabel lblTipoDeBomba = new JLabel("Tipo de Bomba");
		lblTipoDeBomba.setBounds(10, 167, 112, 14);
		contentPane.add(lblTipoDeBomba);
		
		tipo_bomba = new JTextField();
		tipo_bomba.setColumns(10);
		tipo_bomba.setBounds(10, 183, 138, 20);
		contentPane.add(tipo_bomba);
		
		JLabel lblNpsh = new JLabel("N.P.S.H.");
		lblNpsh.setBounds(212, 45, 50, 14);
		contentPane.add(lblNpsh);
		
		npsh = new JTextField();
		npsh.setColumns(10);
		npsh.setBounds(212, 58, 112, 20);
		contentPane.add(npsh);
		
		JLabel lblPotenciaNominal = new JLabel("Potencia Nominal");
		lblPotenciaNominal.setBounds(212, 84, 112, 14);
		contentPane.add(lblPotenciaNominal);
		
		pot_nom = new JTextField();
		pot_nom.setColumns(10);
		pot_nom.setBounds(212, 98, 112, 20);
		contentPane.add(pot_nom);
		
		JLabel lblEficiencia = new JLabel("Eficiencia");
		lblEficiencia.setBounds(212, 123, 64, 14);
		contentPane.add(lblEficiencia);
		
		eficiencia = new JTextField();
		eficiencia.setColumns(10);
		eficiencia.setBounds(212, 140, 112, 20);
		contentPane.add(eficiencia);
		
		chckbxVariador = new JCheckBox("Variador");
		chckbxVariador.setBounds(212, 182, 97, 23);
		contentPane.add(chckbxVariador);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String modelo_ = modelo.getText();
				float consumo_ = Float.parseFloat(pot_tbjo.getText());
				String alimentacion_ = alimentacion.getText();
				String tipo_bomba_ = tipo_bomba.getText();
				float npsh_ = Float.parseFloat(npsh.getText());
				float pot_nom_ = Float.parseFloat(pot_nom.getText());
				float eficiencia_ = Float.parseFloat(eficiencia.getText());
				boolean variador_ = chckbxVariador.isSelected();
				
				String sql="INSERT into Bomba (Modelo_Rodete_RPM,Consumo,Fuente_Alimentación,Tipo_Bomba,NPSH,Potencia_Nominal,Eficiencia,Variador) VALUES ('"+modelo_+"',"+consumo_+",'"+alimentacion_+"','"+tipo_bomba_+"',"+npsh_+","+pot_nom_+","+eficiencia_+","+variador_+")";
				String sql2="SELECT top 1 ID_Bomba from Bomba order by ID_Bomba desc;";
				String sql3;
				cBD.conectar();
				try{
					cBD.getStat().executeUpdate(sql);
					
					ResultSet rst = cBD.getStat().executeQuery(sql2);
					rst.next();
					int id_bomb = rst.getInt(1);
					
					sql3="INSERT into Sistema_Bomba (ID_Sistema,ID_Bomba) VALUES ("+id_sist+","+id_bomb+")";
					cBD.getStat().executeUpdate(sql3);
					cBD.desconectar();
					
					JOptionPane.showMessageDialog(null, "Se han insertado los datos...");

					Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
					nuevo.setVisible(true);
					dispose();
					
				}catch(SQLException e2){
					e2.printStackTrace();
					cBD.desconectar();
				}
				
				
			}
		});
		btnAgregar.setBounds(235, 227, 89, 23);
		contentPane.add(btnAgregar);
	}

		
}

