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

import control.ControlBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cuartel_frame_2 extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField variedad;
	private JTextField categoria;
	private JTextField tamano;
	private JTextField plantas_Ha;
	private JTextField goteros_planta;
	private JTextField caudal_gotero;
	private JTextField caudal_cuartel;
	private JTextField horas_teo;
	private JTextField evapotrans;
	private JTextField coef_cult;
	private JTextField eficiencia_sist;
	private JTextField horas_efect;
	private JTextField precipitacion;
	private JTextField hrs_Control;
	private JTextField consumo_cuart;

	private String nombre_;
	private String variedad_;
	private String categoria_;
	private float tamano_;
	private float plantas_Ha_;
	private float goteros_planta_;
	private float caudal_gotero_;
	private float caudal_cuartel_;
	private float ef_bomba_;
	
	private ControlBD cBD = new ControlBD();
	private JTextField fecha;
	private JTextField anio;
	private JTextField ef_bomba;



	/**
	 * Create the frame.
	 * @param id_sist 
	 */
	public Cuartel_frame_2(final String nom_campo, final int idCampo, final String nom_Empresa, final int idEmpresa, final int id_sist, final String nom_cuart, final int id_cuart) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FelipeSebasti\u00E1n\\Desktop\\Mas Recursos Naturales\\Proyecto_Ef_Energetica\\src\\logo_2.png"));
		setTitle("Cuartel "+nom_cuart);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 363);
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
		nombre.setEditable(false);
		nombre.setColumns(10);
		nombre.setBounds(10, 55, 112, 20);
		contentPane.add(nombre);
		
		JLabel lblVariedad = new JLabel("Variedad");
		lblVariedad.setBounds(10, 81, 112, 14);
		contentPane.add(lblVariedad);
		
		variedad = new JTextField();
		variedad.setEditable(false);
		variedad.setColumns(10);
		variedad.setBounds(10, 95, 112, 20);
		contentPane.add(variedad);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 119, 112, 14);
		contentPane.add(lblCategora);
		
		categoria = new JTextField();
		categoria.setEditable(false);
		categoria.setColumns(10);
		categoria.setBounds(10, 133, 112, 20);
		contentPane.add(categoria);
		
		tamano = new JTextField();
		tamano.setEditable(false);
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
		plantas_Ha.setEditable(false);
		plantas_Ha.setColumns(10);
		plantas_Ha.setBounds(10, 212, 112, 20);
		contentPane.add(plantas_Ha);
		
		JLabel lblGoterosplanta = new JLabel("Goteros/Planta");
		lblGoterosplanta.setBounds(175, 81, 112, 14);
		contentPane.add(lblGoterosplanta);
		
		goteros_planta = new JTextField();
		goteros_planta.setEditable(false);
		goteros_planta.setColumns(10);
		goteros_planta.setBounds(175, 95, 112, 20);
		contentPane.add(goteros_planta);
		
		JLabel lblCaudalgotero = new JLabel("Caudal/Gotero");
		lblCaudalgotero.setBounds(175, 119, 112, 14);
		contentPane.add(lblCaudalgotero);
		
		caudal_gotero = new JTextField();
		caudal_gotero.setEditable(false);
		caudal_gotero.setColumns(10);
		caudal_gotero.setBounds(175, 133, 112, 20);
		contentPane.add(caudal_gotero);
		
		JLabel lblTotalAgua = new JLabel("Caudal Cuartel");
		lblTotalAgua.setBounds(175, 159, 112, 14);
		contentPane.add(lblTotalAgua);
		
		caudal_cuartel = new JTextField();  //caudal del cuartel
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
		
		JLabel lblEvapotranspiracin_1 = new JLabel("ETo");
		lblEvapotranspiracin_1.setBounds(333, 11, 112, 14);
		contentPane.add(lblEvapotranspiracin_1);
		
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
		
		ef_bomba = new JTextField();
		ef_bomba.setText((String) null);
		ef_bomba.setEditable(false);
		ef_bomba.setColumns(10);
		ef_bomba.setBounds(10, 276, 112, 20);
		contentPane.add(ef_bomba);
				
		
		JLabel lblSistemaDeRiego = new JLabel("Sistema de Riego");
		lblSistemaDeRiego.setBounds(333, 159, 112, 14);
		contentPane.add(lblSistemaDeRiego);
		
		JButton btnDetalleRiego = new JButton("Detalle Riego");
		btnDetalleRiego.setBounds(333, 290, 101, 23);
		contentPane.add(btnDetalleRiego);
		
		
		
		
		
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//eliminar cuartel
				int rpta=JOptionPane.showConfirmDialog(null, "Va a elimiar los datos del Cuartel "+nom_cuart+". ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(rpta == JOptionPane.YES_OPTION){
					String sql="DELETE from Cuartel where ID_Cuartel="+id_cuart+";";
					cBD.conectar();
					//bloque try catch sql exception
					try{
						cBD.getStat().executeUpdate(sql);
						cBD.desconectar();
						JOptionPane.showMessageDialog(null, "Se ha eliminado la información");
						
						Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
						nuevo.setVisible(true);
						dispose();	
						
					}catch(SQLException e){
						e.printStackTrace();
						cBD.desconectar();
					}
				}else{
					
					JOptionPane.showMessageDialog(null, "No se ha eliminado la información");

				}
			}
		});
		btnEliminar.setBounds(175, 11, 89, 23);
		contentPane.add(btnEliminar);
		
		//rescatar valores para llenar la ventana
		
		String sql = "SELECT Nombre,Variedad,Categoría,Tamaño,Plantas_Há,Goteros_Planta,Caudal_Gotero,Caudal_Cuartel,Punto_Eficiencia FROM Cuartel WHERE ID_Cuartel="+id_cuart+";";
		cBD.conectar();
		
		try{
			ResultSet rst = cBD.getStat().executeQuery(sql);
			while(rst.next()){
				nombre_ = rst.getString("Nombre");
				variedad_ = rst.getString("Variedad");
				categoria_ = rst.getString("Categoría");
				tamano_ = rst.getFloat("Tamaño");
				plantas_Ha_ = rst.getFloat("Plantas_Há");
				goteros_planta_ = rst.getFloat("Goteros_Planta");
				caudal_gotero_ = rst.getFloat("Caudal_Gotero");
				caudal_cuartel_ = rst.getFloat("Caudal_Cuartel");
				ef_bomba_ = rst.getFloat("Punto_Eficiencia");
			}
			cBD.desconectar();
		}catch(SQLException e1){
			e1.printStackTrace();
			cBD.desconectar();
		}
		
		
		nombre.setText(nombre_);
		variedad.setText(variedad_);
		categoria.setText(categoria_);
		tamano.setText(Float.toString(tamano_));
		plantas_Ha.setText(Float.toString(plantas_Ha_));
		goteros_planta.setText(Float.toString(goteros_planta_));
		caudal_gotero.setText(Float.toString(caudal_gotero_));
		caudal_cuartel.setText(Float.toString(caudal_cuartel_));
		ef_bomba.setText(Float.toString(ef_bomba_));
		
		
		
		
		
		JButton btnTerminarEdicin = new JButton("Terminar Edici\u00F3n");
		btnTerminarEdicin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//bloquear campos
				fecha.setEditable(false);
				anio.setEditable(false);
				evapotrans.setEditable(false);
				coef_cult.setEditable(false);
				eficiencia_sist.setEditable(false);
				horas_efect.setEditable(false);
								
				String fecha_ = fecha.getText();
				String anio_ = anio.getText();
				float evapotrans_ = Float.parseFloat(evapotrans.getText());
				float coef_cult_ = Float.parseFloat(coef_cult.getText());
				float eficiencia_sist_ = Float.parseFloat(eficiencia_sist.getText());
				float horas_efect_ = Float.parseFloat(horas_efect.getText());
				
				/**hacer actualizacion en cadena
				 * 2°) hacer query para insertar en tabla Sistema-Cuartel
				 **/
				
				
				float precipitacion_=(caudal_gotero_*goteros_planta_*plantas_Ha_)/10000;// en lt/hr (mm/hr)
				float horas_teo_ = (evapotrans_*coef_cult_)/precipitacion_;
				float consumo_cuartel = ((caudal_gotero_*goteros_planta_*plantas_Ha_*tamano_)/1000)*horas_efect_;
				float consumo_teo = ((caudal_gotero_*goteros_planta_*plantas_Ha_*tamano_)/1000)*horas_teo_;
				
				
				String sql2 = "INSERT INTO Sistema_Cuartel (ID_Sistema,ID_Cuartel,Mes_Año,Año,Evapotranspiración,Coef_Cultivo,Precipitación_Sist,Eficiencia_Sist_Riego,Horas_Riego_Efectivas,Horas_Riego_Teóricas,Consumo_Cuartel,Consumo_Teo_Cuartel) VALUES ("+id_sist+","+id_cuart+",'"+fecha_+"','"+anio_+"',"+evapotrans_+","+coef_cult_+","+precipitacion_+","+eficiencia_sist_+","+horas_efect_+","+horas_teo_+","+consumo_cuartel+","+consumo_teo+");";				
				cBD.conectar();
				try{
					
					cBD.getStat().executeUpdate(sql2);
					cBD.desconectar();
				}catch(SQLException e1){
					e1.printStackTrace();
					cBD.desconectar();
				}
				
				
				Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
				nuevo.setVisible(true);
				dispose();
				
			}
		});
		btnTerminarEdicin.setBounds(130, 290, 134, 23);
		contentPane.add(btnTerminarEdicin);
		
		precipitacion = new JTextField();
		precipitacion.setText("#########");
		precipitacion.setEditable(false);
		precipitacion.setColumns(10);
		precipitacion.setBounds(333, 109, 112, 20);
		contentPane.add(precipitacion);
		
		JLabel lblPrecipitacion = new JLabel("Precipitaci\u00F3n Sistema");
		lblPrecipitacion.setBounds(333, 95, 112, 14);
		contentPane.add(lblPrecipitacion);
		
		JLabel lblHorasDeControl = new JLabel("Horas de Control de Riego ");
		lblHorasDeControl.setBounds(301, 233, 154, 14);
		contentPane.add(lblHorasDeControl);
		
		hrs_Control = new JTextField();
		hrs_Control.setText("#########");
		hrs_Control.setEditable(false);
		hrs_Control.setColumns(10);
		hrs_Control.setBounds(333, 248, 112, 20);
		contentPane.add(hrs_Control);
		
		JLabel lblConsumoCuartel = new JLabel("Consumo Cuartel");
		lblConsumoCuartel.setBounds(175, 234, 112, 14);
		contentPane.add(lblConsumoCuartel);
		
		consumo_cuart = new JTextField();
		consumo_cuart.setText("#########");
		consumo_cuart.setEditable(false);
		consumo_cuart.setColumns(10);
		consumo_cuart.setBounds(175, 248, 112, 20);
		contentPane.add(consumo_cuart);
		
		JLabel label = new JLabel("Mes");
		label.setBounds(132, 41, 59, 14);
		contentPane.add(label);
		
		fecha = new JTextField();
		fecha.setText((String) null);
		fecha.setColumns(10);
		fecha.setBounds(132, 55, 89, 20);
		contentPane.add(fecha);
		
		anio = new JTextField();
		anio.setText((String) null);
		anio.setColumns(10);
		anio.setBounds(231, 55, 80, 20);
		contentPane.add(anio);
		
		JLabel label_1 = new JLabel("A\u00F1o");
		label_1.setBounds(231, 41, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Bomba");
		label_2.setBounds(10, 260, 112, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Punto de Eficiencia");
		label_3.setBounds(10, 243, 112, 14);
		contentPane.add(label_3);
		
		
		
		setLocationRelativeTo(null);

	}
}
