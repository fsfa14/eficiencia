import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import control.ControlBD;


public class Bomba_frame_2 extends JFrame {

	private JPanel contentPane;
	private JTextField modelo;
	private JTextField pot_tbjo;
	private JTextField alimentacion;
	private JTextField tipo_bomba;
	private JTextField npsh;
	private JTextField pot_nom;
	private JTextField eficiencia;
	private JCheckBox chckbxVariador;
	private ControlBD cBD = new ControlBD();


	

	/**
	 * Create the frame.
	 */
	public Bomba_frame_2(final String nom_campo, final int idCampo, final String nom_Empresa,final int idEmpresa, final int id_bomb, final String nom_bomb) {
		setTitle("Bomba "+nom_bomb);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FelipeSebasti\u00E1n\\Desktop\\Mas Recursos Naturales\\Proyecto_Ef_Energetica\\src\\logo_2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		ResultSet rst;
		
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
		modelo.setEditable(false);
		modelo.setColumns(10);
		modelo.setBounds(10, 59, 138, 20);
		contentPane.add(modelo);
		
		JLabel lblConsumo = new JLabel("Potencia de Trabajo");
		lblConsumo.setBounds(10, 85, 107, 14);
		contentPane.add(lblConsumo);
		
		pot_tbjo = new JTextField();
		pot_tbjo.setEditable(false);
		pot_tbjo.setColumns(10);
		pot_tbjo.setBounds(10, 99, 138, 20);
		contentPane.add(pot_tbjo);
		
		JLabel lblFuenteDeAlimentacin = new JLabel("Fuente de Alimentaci\u00F3n");
		lblFuenteDeAlimentacin.setBounds(10, 124, 138, 14);
		contentPane.add(lblFuenteDeAlimentacin);
		
		alimentacion = new JTextField();
		alimentacion.setEditable(false);
		alimentacion.setColumns(10);
		alimentacion.setBounds(10, 141, 138, 20);
		contentPane.add(alimentacion);
		
		JLabel lblTipoDeBomba = new JLabel("Tipo de Bomba");
		lblTipoDeBomba.setBounds(10, 167, 112, 14);
		contentPane.add(lblTipoDeBomba);
		
		tipo_bomba = new JTextField();
		tipo_bomba.setEditable(false);
		tipo_bomba.setColumns(10);
		tipo_bomba.setBounds(10, 183, 138, 20);
		contentPane.add(tipo_bomba);
		
		JLabel lblNpsh = new JLabel("N.P.S.H.");
		lblNpsh.setBounds(212, 45, 50, 14);
		contentPane.add(lblNpsh);
		
		npsh = new JTextField();
		npsh.setEditable(false);
		npsh.setColumns(10);
		npsh.setBounds(212, 58, 112, 20);
		contentPane.add(npsh);
		
		JLabel lblPotenciaNominal = new JLabel("Potencia Nominal");
		lblPotenciaNominal.setBounds(212, 84, 112, 14);
		contentPane.add(lblPotenciaNominal);
		
		pot_nom = new JTextField();
		pot_nom.setEditable(false);
		pot_nom.setColumns(10);
		pot_nom.setBounds(212, 98, 112, 20);
		contentPane.add(pot_nom);
		
		JLabel lblEficiencia = new JLabel("Eficiencia");
		lblEficiencia.setBounds(212, 123, 64, 14);
		contentPane.add(lblEficiencia);
		
		eficiencia = new JTextField();
		eficiencia.setEditable(false);
		eficiencia.setColumns(10);
		eficiencia.setBounds(212, 140, 112, 20);
		contentPane.add(eficiencia);
		
		chckbxVariador = new JCheckBox("Variador");
		chckbxVariador.setBounds(212, 182, 97, 23);
		chckbxVariador.setEnabled(false);
		contentPane.add(chckbxVariador);
		
		//se conecta a la BD...
		
		cBD.conectar();
		String sql = "SELECT Modelo_Rodete_RPM,Consumo,Fuente_Alimentación,Tipo_Bomba,NPSH,Potencia_Nominal,Eficiencia,Variador from Bomba where ID_Bomba="+id_bomb+";";
		try{
			
			rst = cBD.getStat().executeQuery(sql);
			//se llenan los campos de texto con la bomba de ID recibido...
			while(rst.next()){
				modelo.setText(rst.getString("Modelo_Rodete_RPM"));
				pot_tbjo.setText(Float.toString(rst.getFloat("Consumo")));
				alimentacion.setText(rst.getString("Fuente_Alimentación"));
				tipo_bomba.setText(rst.getString("Tipo_Bomba"));
				npsh.setText(Float.toString(rst.getFloat("NPSH")));
				pot_nom.setText(Float.toString(rst.getFloat("Potencia_Nominal")));
				eficiencia.setText(Float.toString(rst.getFloat("Eficiencia")));
				chckbxVariador.setSelected(rst.getBoolean("Variador"));
			}	
			
		}catch(SQLException e){
			e.printStackTrace();
			cBD.desconectar();
		}
		
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				  modelo.setEditable(true);
				  pot_tbjo.setEditable(true);
				  alimentacion.setEditable(true);
				  tipo_bomba.setEditable(true);
				  npsh.setEditable(true);
				  pot_nom.setEditable(true);
				  eficiencia.setEditable(true);
				  chckbxVariador.setEnabled(true);

				
			}
		});
		btnEditar.setBounds(59, 227, 89, 23);
		contentPane.add(btnEditar);
		
		
		JButton btnTerminarEd = new JButton("Terminar Edición");
		btnTerminarEd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//se bloquean los campos
				modelo.setEditable(false);
				pot_tbjo.setEditable(false);
				alimentacion.setEditable(false);
				tipo_bomba.setEditable(false);
				npsh.setEditable(false);
				pot_nom.setEditable(false);
				eficiencia.setEditable(false);
				chckbxVariador.setEnabled(false);
				
				//se rescatan los valores
				String modelo_ = modelo.getText();
				float consumo_ = Float.parseFloat(pot_tbjo.getText());
				String alimentacion_ = alimentacion.getText();
				String tipo_bomba_ = tipo_bomba.getText();
				float npsh_ = Float.parseFloat(npsh.getText());
				float pot_nom_ = Float.parseFloat(pot_nom.getText());
				float eficiencia_ = Float.parseFloat(eficiencia.getText());
				boolean variador_ = chckbxVariador.isSelected();
				
				//hacer sentencia sql UPDATE 
				String sql = "UPDATE Bomba SET Modelo_Rodete_RPM = '"+modelo_+"', Consumo = "+consumo_+", Fuente_Alimentación = '"+alimentacion_+"', Tipo_Bomba = '"+tipo_bomba_+"', NPSH = "+npsh_+", Potencia_Nominal = "+pot_nom_+", Eficiencia = "+eficiencia_+", Variador = "+variador_+" where ID_Bomba = "+id_bomb+";";
				cBD.conectar();
				try{
					cBD.getStat().executeUpdate(sql);
					cBD.desconectar();
					JOptionPane.showMessageDialog(null, "Se han actualizado los datos...");

					
				}catch(SQLException e1){
					e1.printStackTrace();
					cBD.desconectar();
				}
				
				Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
				nuevo.setVisible(true);
				dispose();
				
			}
		});
		btnTerminarEd.setBounds(212, 227, 147, 23);
		contentPane.add(btnTerminarEd);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//sql para eliminar la bomba
				String sql = "DELETE from Bomba where ID_Bomba = "+id_bomb+";";
				
				cBD.conectar();
				try{
					
					cBD.getStat().executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "Se han eliminado los datos...");

					
				}catch(SQLException e1){
					e1.printStackTrace();
					cBD.desconectar();
				}
				
				Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
				nuevo.setVisible(true);
				dispose();		
				
				
			}
		});
		btnEliminar.setBounds(335, 11, 89, 23);
		contentPane.add(btnEliminar);
		
		
	}
}
