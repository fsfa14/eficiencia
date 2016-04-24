import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import control.ControlBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cuartel_frame extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField variedad;
	private JTextField categoria;
	private JTextField tamano;
	private JTextField plantas_Ha;
	private JTextField fecha;
	private JTextField goteros_planta;
	private JTextField caudal_gotero;
	private JTextField caudal_cuartel;
	private JTextField horas_teo;
	private JTextField evapotrans;
	private JTextField coef_cult;
	private JTextField eficiencia_sist;
	private JTextField horas_efect;
	private JTextField precipitacion;
	private JTextField anio;
	private ControlBD cBD = new ControlBD();
	private JTextField ef_bomba;


	/**
	 * Create the frame.
	 * @param id_sist 
	 */
	public Cuartel_frame(final String nom_campo, final int idCampo, final String nom_Empresa, final int idEmpresa, final int id_sist) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FelipeSebasti\u00E1n\\Desktop\\Mas Recursos Naturales\\Proyecto_Ef_Energetica\\src\\logo_2.png"));
		setTitle("Nuevo Cuartel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("Volver...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
				nuevo.setVisible(true);
				dispose();	
				
			}
		});
		button.setBounds(10, 11, 107, 23);
		contentPane.add(button);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 41, 112, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setColumns(10);
		nombre.setBounds(10, 55, 112, 20);
		contentPane.add(nombre);
		
		JLabel lblVariedad = new JLabel("Variedad");
		lblVariedad.setBounds(10, 81, 112, 14);
		contentPane.add(lblVariedad);
		
		variedad = new JTextField();
		variedad.setColumns(10);
		variedad.setBounds(10, 95, 112, 20);
		contentPane.add(variedad);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 119, 112, 14);
		contentPane.add(lblCategora);
		
		categoria = new JTextField();
		categoria.setColumns(10);
		categoria.setBounds(10, 133, 112, 20);
		contentPane.add(categoria);
		
		tamano = new JTextField();
		tamano.setColumns(10);
		tamano.setBounds(10, 173, 112, 20);
		contentPane.add(tamano);
		
		JLabel lblTamaoha = new JLabel("Tama\u00F1o (Ha)");
		lblTamaoha.setBounds(10, 159, 112, 14);
		contentPane.add(lblTamaoha);
		
		JLabel lblPlantash = new JLabel("Plantas/H\u00E1");
		lblPlantash.setBounds(10, 198, 112, 14);
		contentPane.add(lblPlantash);
		
		plantas_Ha = new JTextField();
		plantas_Ha.setColumns(10);
		plantas_Ha.setBounds(10, 212, 112, 20);
		contentPane.add(plantas_Ha);
		
		JLabel lblFecha = new JLabel("Mes");
		lblFecha.setBounds(141, 41, 56, 14);
		contentPane.add(lblFecha);
		
		fecha = new JTextField();
		fecha.setColumns(10);
		fecha.setBounds(141, 55, 90, 20);
		contentPane.add(fecha);
		
		JLabel lblGoterosplanta = new JLabel("Goteros/Planta");
		lblGoterosplanta.setBounds(175, 81, 112, 14);
		contentPane.add(lblGoterosplanta);
		
		goteros_planta = new JTextField();
		goteros_planta.setColumns(10);
		goteros_planta.setBounds(175, 95, 112, 20);
		contentPane.add(goteros_planta);
		
		JLabel lblCaudalgotero = new JLabel("Caudal/Gotero");
		lblCaudalgotero.setBounds(175, 119, 112, 14);
		contentPane.add(lblCaudalgotero);
		
		caudal_gotero = new JTextField();
		caudal_gotero.setColumns(10);
		caudal_gotero.setBounds(175, 133, 112, 20);
		contentPane.add(caudal_gotero);
		
		JLabel lblTotalAgua = new JLabel("Caudal Cuartel");
		lblTotalAgua.setBounds(175, 159, 112, 14);
		contentPane.add(lblTotalAgua);
		
		caudal_cuartel = new JTextField();
		caudal_cuartel.setText("#########");
		caudal_cuartel.setEditable(false);
		caudal_cuartel.setColumns(10);
		caudal_cuartel.setBounds(175, 173, 112, 20);
		contentPane.add(caudal_cuartel);
		
		JLabel lblEvapotranspiracin = new JLabel("Horas de Riego Efectivas");
		lblEvapotranspiracin.setBounds(311, 198, 144, 14);
		contentPane.add(lblEvapotranspiracin);
		
		horas_teo = new JTextField();
		horas_teo.setText("#########");
		horas_teo.setEditable(false);
		horas_teo.setColumns(10);
		horas_teo.setBounds(175, 212, 112, 20);
		contentPane.add(horas_teo);
		
		JLabel lblETo = new JLabel("ETo");
		lblETo.setBounds(333, 11, 112, 14);
		contentPane.add(lblETo);
		
		evapotrans = new JTextField();
		evapotrans.setColumns(10);
		evapotrans.setBounds(333, 25, 112, 20);
		contentPane.add(evapotrans);
		
		JLabel lblCoefDeCultivo = new JLabel("Coef. de Cultivo");
		lblCoefDeCultivo.setBounds(333, 51, 112, 14);
		contentPane.add(lblCoefDeCultivo);
		
		coef_cult = new JTextField();
		coef_cult.setColumns(10);
		coef_cult.setBounds(333, 65, 112, 20);
		contentPane.add(coef_cult);
		
		JLabel lblEficiencia = new JLabel("Eficiencia ");
		lblEficiencia.setBounds(333, 147, 112, 14);
		contentPane.add(lblEficiencia);
		
		eficiencia_sist = new JTextField();
		eficiencia_sist.setColumns(10);
		eficiencia_sist.setBounds(333, 173, 112, 20);
		contentPane.add(eficiencia_sist);
		
		JLabel lblHorasDeRiego = new JLabel("Horas de Riego Te\u00F3ricas");
		lblHorasDeRiego.setBounds(153, 198, 144, 14);
		contentPane.add(lblHorasDeRiego);
		
		horas_efect = new JTextField();
		horas_efect.setColumns(10);
		horas_efect.setBounds(333, 212, 112, 20);
		contentPane.add(horas_efect);
		
		JLabel lblSistemaDeRiego = new JLabel("Sistema de Riego");
		lblSistemaDeRiego.setBounds(333, 159, 112, 14);
		contentPane.add(lblSistemaDeRiego);
		
		anio = new JTextField();
		anio.setText((String) null);
		anio.setColumns(10);
		anio.setBounds(241, 55, 80, 20);
		contentPane.add(anio);
		
		JButton btnDetalleRiego = new JButton("Agregar");
		btnDetalleRiego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String nombre_ = nombre.getText();
				String variedad_ = variedad.getText();
				String categoria_ = categoria.getText();
				float tamano_ = Float.parseFloat(tamano.getText());
				float plantas_Ha_ =Float.parseFloat(plantas_Ha.getText());
				float goteros_planta_ = Float.parseFloat(goteros_planta.getText());
				float caudal_gotero_ = Float.parseFloat(caudal_gotero.getText());
				float eficiencia_bomb = Float.parseFloat(ef_bomba.getText());
				
				String fecha_ = fecha.getText();
				String anio_ = anio.getText();
				float evapotrans_ = Float.parseFloat(evapotrans.getText());
				float coef_cult_ = Float.parseFloat(coef_cult.getText());
				float horas_efect_ = Float.parseFloat(horas_efect.getText());
				float eficiencia_sist_ = Float.parseFloat(eficiencia_sist.getText());
				
				float precipitacion_;
				float horas_teo_;
				float consumo_cuartel;
				float consumo_teo;
				int id_new_cuart=-1;
				
				//hacer insert en cadena
				String sql = "INSERT INTO Cuartel (Nombre,Variedad,Categoría,Tamaño,Plantas_Há,Goteros_Planta,Caudal_Gotero,Punto_Eficiencia) VALUES ('"+nombre_+"','"+variedad_+"','"+categoria_+"',"+tamano_+","+plantas_Ha_+","+goteros_planta_+","+caudal_gotero_+","+eficiencia_bomb+");";
				String sql2 = "SELECT top 1 ID_Cuartel from Cuartel ORDER BY ID_Cuartel desc;";
				
				precipitacion_=(caudal_gotero_*goteros_planta_*plantas_Ha_)/10000;// en lt/hr (mm/hr)
				horas_teo_ = (evapotrans_*coef_cult_)/precipitacion_;
				consumo_cuartel = ((caudal_gotero_*goteros_planta_*plantas_Ha_*tamano_)/1000)*horas_efect_;
				consumo_teo = ((caudal_gotero_*goteros_planta_*plantas_Ha_*tamano_)/1000)*horas_teo_;

				
				ResultSet rst;
				cBD.conectar();
				try{
					
					cBD.getStat().executeUpdate(sql);
					rst = cBD.getStat().executeQuery(sql2);
					rst.next();
					id_new_cuart = rst.getInt(1);
					String sql3 = "INSERT INTO Sistema_Cuartel (ID_Sistema,ID_Cuartel,Mes_Año,Año,Evapotranspiración,Coef_Cultivo,Precipitación_Sist,Eficiencia_Sist_Riego,Horas_Riego_Efectivas,Horas_Riego_Teóricas,Consumo_Cuartel,Consumo_Teo_Cuartel) VALUES ("+id_sist+","+id_new_cuart+",'"+fecha_+"','"+anio_+"',"+evapotrans_+","+coef_cult_+","+precipitacion_+","+eficiencia_sist_+","+horas_efect_+","+horas_teo_+","+consumo_cuartel+","+consumo_teo+");";
					cBD.getStat().executeUpdate(sql3);
					cBD.desconectar();
				}catch(SQLException e1){
					e1.printStackTrace();
					cBD.desconectar();
				}
				
				
			}
		});
		btnDetalleRiego.setBounds(333, 260, 101, 23);
		contentPane.add(btnDetalleRiego);
		
		JLabel lblPrecipitacion = new JLabel("Precipitaci\u00F3n");
		lblPrecipitacion.setBounds(333, 95, 112, 14);
		contentPane.add(lblPrecipitacion);
		
		precipitacion = new JTextField();
		precipitacion.setText("#########");
		precipitacion.setEditable(false);
		precipitacion.setColumns(10);
		precipitacion.setBounds(333, 109, 112, 20);
		contentPane.add(precipitacion);
		
		JLabel label = new JLabel("A\u00F1o");
		label.setBounds(241, 41, 46, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Punto de Eficiencia");
		label_1.setBounds(10, 243, 112, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Bomba");
		label_2.setBounds(10, 260, 112, 14);
		contentPane.add(label_2);
		
		ef_bomba = new JTextField();
		ef_bomba.setText((String) null);
		ef_bomba.setColumns(10);
		ef_bomba.setBounds(10, 276, 112, 20);
		contentPane.add(ef_bomba);
		
		
		
		
		setLocationRelativeTo(null);

	}
}
