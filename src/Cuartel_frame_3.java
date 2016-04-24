import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;















import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import control.ControlBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cuartel_frame_3 extends JFrame {

	private JPanel contentPane;
	private JTextField nombre;
	private JTextField variedad;
	private JTextField categoria;
	private JTextField tamano;
	private JTextField plantas_Ha;
	private JTextField goteros_planta;
	private JTextField caudal_gotero;
	private JTextField caudal_cuartel;
	
	private JTextField fecha;
	private JTextField horas_teo;
	private JTextField evapotrans;
	private JTextField coef_cult;
	private JTextField eficiencia_sist;
	private JTextField horas_efect;
	private JTextField precipitacion;
	private JTextField consumo_cuart;
	private JTextField hrs_Control;
	private JTextField txtf_anio;
	private JTextField ef_bomba;
	private JTextField consumo_teo;
	private ControlBD cBD = new ControlBD();
	
	private String nombre_;
	private String variedad_;
	private String categoria_;
	private float tamano_;
	private float plantas_Ha_;
	private float goteros_planta_;
	private float caudal_gotero_;
	private float caudal_cuartel_;
	private float ef_bomba_;
	
	private String fecha_;
	private String anio_;
	private float horas_teo_;
	private float evapotrans_;
	private float coef_cult_;
	private float eficiencia_sist_;
	private float horas_efect_;
	private float precipitacion_;
	private float consumo_cuart_;
	private float hrs_Control_;
	private float consumo_teo_;


	/**
	 * Create the frame.
	 * @param id_sist 
	 * @param anio 
	 */
	public Cuartel_frame_3(final String nom_campo, final int idCampo, final String nom_Empresa, final int idEmpresa, final int id_sist, final String nom_cuart, final int id_cuart, final String mes_anio, final String anio) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FelipeSebasti\u00E1n\\Desktop\\Mas Recursos Naturales\\Proyecto_Ef_Energetica\\src\\logo_2.png"));
		setTitle("Cuartel "+nom_cuart);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 379);
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
		
		JLabel lblFecha = new JLabel("Mes");
		lblFecha.setBounds(132, 41, 59, 14);
		contentPane.add(lblFecha);
		
		fecha = new JTextField();
		fecha.setEditable(false);
		fecha.setColumns(10);
		fecha.setBounds(132, 55, 89, 20);
		contentPane.add(fecha);
		
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
		
		caudal_cuartel = new JTextField();
		caudal_cuartel.setEditable(false);
		caudal_cuartel.setColumns(10);
		caudal_cuartel.setBounds(175, 173, 112, 20);
		contentPane.add(caudal_cuartel);
		
		JLabel lblEvapotranspiracin = new JLabel("Horas de Riego Efectivas");
		lblEvapotranspiracin.setBounds(307, 184, 144, 14);
		contentPane.add(lblEvapotranspiracin);
		
		horas_teo = new JTextField();
		horas_teo.setEditable(false);
		horas_teo.setColumns(10);
		horas_teo.setBounds(175, 212, 112, 20);
		contentPane.add(horas_teo);
		
		JLabel lblEvapotranspiracin_1 = new JLabel("ETo");
		lblEvapotranspiracin_1.setBounds(333, 11, 112, 14);
		contentPane.add(lblEvapotranspiracin_1);
		
		evapotrans = new JTextField();
		evapotrans.setEditable(false);
		evapotrans.setColumns(10);
		evapotrans.setBounds(333, 25, 112, 20);
		contentPane.add(evapotrans);
		
		JLabel lblCoefDeCultivo = new JLabel("Coef. de Cultivo");
		lblCoefDeCultivo.setBounds(333, 51, 112, 14);
		contentPane.add(lblCoefDeCultivo);
		
		coef_cult = new JTextField();
		coef_cult.setEditable(false);
		coef_cult.setColumns(10);
		coef_cult.setBounds(333, 65, 112, 20);
		contentPane.add(coef_cult);
		
		JLabel lblEficiencia = new JLabel("Eficiencia ");
		lblEficiencia.setBounds(329, 133, 112, 14);
		contentPane.add(lblEficiencia);
		
		eficiencia_sist = new JTextField();
		eficiencia_sist.setEditable(false);
		eficiencia_sist.setColumns(10);
		eficiencia_sist.setBounds(329, 159, 112, 20);
		contentPane.add(eficiencia_sist);
		
		precipitacion = new JTextField();
		precipitacion.setEditable(false);
		precipitacion.setColumns(10);
		precipitacion.setBounds(333, 109, 112, 20);
		contentPane.add(precipitacion);
		
		hrs_Control = new JTextField();
		hrs_Control.setEditable(false);
		hrs_Control.setColumns(10);
		hrs_Control.setBounds(329, 234, 112, 20);
		contentPane.add(hrs_Control);
		
		consumo_cuart = new JTextField();
		consumo_cuart.setEditable(false);
		consumo_cuart.setColumns(10);
		consumo_cuart.setBounds(175, 248, 112, 20);
		contentPane.add(consumo_cuart);
		
		JLabel lblHorasDeRiego = new JLabel("Horas de Riego Te\u00F3ricas");
		lblHorasDeRiego.setBounds(153, 198, 144, 14);
		contentPane.add(lblHorasDeRiego);
		
		horas_efect = new JTextField();
		horas_efect.setEditable(false);
		horas_efect.setColumns(10);
		horas_efect.setBounds(329, 198, 112, 20);
		contentPane.add(horas_efect);
		
		JLabel lblSistemaDeRiego = new JLabel("Sistema de Riego");
		lblSistemaDeRiego.setBounds(329, 145, 112, 14);
		contentPane.add(lblSistemaDeRiego);
		
		JButton btnDetalleRiego = new JButton("Detalle Riego");
		btnDetalleRiego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Riego_Semanal_frame nuevo = new Riego_Semanal_frame(nom_campo,idCampo,nom_Empresa,idEmpresa,id_sist,nom_cuart,id_cuart,mes_anio,anio);
				nuevo.setVisible(true);
				dispose();
				
			}
		});
		btnDetalleRiego.setBounds(333, 306, 101, 23);
		contentPane.add(btnDetalleRiego);
		
		JLabel lblAo = new JLabel("A\u00F1o");
		lblAo.setBounds(231, 41, 46, 14);
		contentPane.add(lblAo);
		
		txtf_anio = new JTextField();
		txtf_anio.setText((String) null);
		txtf_anio.setEditable(false);
		txtf_anio.setColumns(10);
		txtf_anio.setBounds(231, 55, 80, 20);
		contentPane.add(txtf_anio);
		
		
		ef_bomba = new JTextField();
		ef_bomba.setText((String) null);
		ef_bomba.setEditable(false);
		ef_bomba.setColumns(10);
		ef_bomba.setBounds(10, 264, 112, 20);
		contentPane.add(ef_bomba);
		
		consumo_teo = new JTextField();
		consumo_teo.setText((String) null);
		consumo_teo.setEditable(false);
		consumo_teo.setColumns(10);
		consumo_teo.setBounds(329, 279, 112, 20);
		contentPane.add(consumo_teo);
		
		
		
		//consultas para llenar los campos necesarios

		String sql = "SELECT Nombre,Variedad,Categoría,Tamaño,Plantas_Há,Goteros_Planta,Caudal_Gotero,Caudal_Cuartel,Punto_Eficiencia FROM Cuartel WHERE ID_Cuartel="+id_cuart+";";
		String sql2 ="SELECT Mes_Año,Año,Evapotranspiración,Coef_Cultivo,Precipitación_Sist,Eficiencia_Sist_Riego,Horas_Riego_Efectivas,Horas_Riego_Teóricas,Consumo_Cuartel,Consumo_Teo_Cuartel FROM Sistema_Cuartel WHERE ID_Sistema="+id_sist+" AND ID_Cuartel="+id_cuart+" AND Mes_Año='"+mes_anio+"' AND Año='"+anio+"';";
		
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
			rst = cBD.getStat().executeQuery(sql2);
			while(rst.next()){
				
				fecha_ = rst.getString("Mes_Año");
				anio_ = rst.getString("Año");
				evapotrans_ = rst.getFloat("Evapotranspiración");
				coef_cult_ = rst.getFloat("Coef_Cultivo");
				precipitacion_ = rst.getFloat("Precipitación_Sist");
				eficiencia_sist_ = rst.getFloat("Eficiencia_Sist_Riego");
				horas_efect_ = rst.getFloat("Horas_Riego_Efectivas");
				horas_teo_ = rst.getFloat("Horas_Riego_Teóricas");
				consumo_cuart_ = rst.getFloat("Consumo_Cuartel");
				consumo_teo_ = rst.getFloat("Consumo_Teo_Cuartel");
								
			}
			//cBD.desconectar();
			
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
		
		fecha.setText(fecha_);
		txtf_anio.setText(anio_);
		evapotrans.setText(Float.toString(evapotrans_));
		coef_cult.setText(Float.toString(coef_cult_));
		precipitacion.setText(Float.toString(precipitacion_));		
		eficiencia_sist.setText(Float.toString(eficiencia_sist_));
		horas_efect.setText(Float.toString(horas_efect_));
		horas_teo.setText(Float.toString(horas_teo_));
		consumo_cuart.setText(Float.toString(consumo_cuart_));
		consumo_teo.setText(Float.toString(consumo_teo_));
		
		hrs_Control_ = (horas_teo_*100)/eficiencia_sist_;
		hrs_Control.setText(Float.toString(hrs_Control_));

		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				nombre.setEditable(true);
				variedad.setEditable(true);
				categoria.setEditable(true);
				tamano.setEditable(true);
				plantas_Ha.setEditable(true);
				goteros_planta.setEditable(true);
				caudal_gotero.setEditable(true);
				ef_bomba.setEditable(true);


				fecha.setEditable(true);
				evapotrans.setEditable(true);
				coef_cult.setEditable(true);
				eficiencia_sist.setEditable(true);
				horas_efect.setEditable(true);
				
			}
		});
		btnEditar.setBounds(185, 279, 89, 23);
		contentPane.add(btnEditar);
		
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
		
		JButton btnTerminarEdicin = new JButton("Terminar Edici\u00F3n");
		btnTerminarEdicin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//bloquear campos
				nombre.setEditable(false);
				variedad.setEditable(false);
				categoria.setEditable(false);
				tamano.setEditable(false);
				plantas_Ha.setEditable(false);
				goteros_planta.setEditable(false);
				caudal_gotero.setEditable(false);
				caudal_cuartel.setEditable(false);
				ef_bomba.setEditable(false);
				
				fecha.setEditable(false);
				evapotrans.setEditable(false);
				coef_cult.setEditable(false);
				eficiencia_sist.setEditable(false);
				horas_efect.setEditable(false);
				
				//rescatar info de los campos...  
				
				String nombre_ = nombre.getText();
				String variedad_ = variedad.getText();
				String categoria_ = categoria.getText();
				float tamano_ = Float.parseFloat(tamano.getText());
				float plantas_Ha_ =Float.parseFloat(plantas_Ha.getText());
				float goteros_planta_ = Float.parseFloat(goteros_planta.getText());
				float caudal_gotero_ = Float.parseFloat(caudal_gotero.getText());
				float ef_bomba_ = Float.parseFloat(ef_bomba.getText());
				
				String fecha_ = fecha.getText();
				float evapotrans_ = Float.parseFloat(evapotrans.getText());
				float coef_cult_ = Float.parseFloat(coef_cult.getText());
				float eficiencia_sist_ = Float.parseFloat(eficiencia_sist.getText());
				float horas_efect_ = Float.parseFloat(horas_efect.getText());
				
				float precipitacion_=(caudal_gotero_*goteros_planta_*plantas_Ha_)/10000;// en lt/hr (mm/hr)
				float horas_teo_ = (evapotrans_*coef_cult_)/precipitacion_;
				float consumo_cuartel = ((caudal_gotero_*goteros_planta_*plantas_Ha_*tamano_)/1000)*horas_efect_;
				float consumo_teo = ((caudal_gotero_*goteros_planta_*plantas_Ha_*tamano_)/1000)*horas_teo_;
				
				
				/**hacer actualizacion en cadena
				 * 1°) hacer query para actualizar tabla Cuartel
				 * 2°) hacer query para actualizar tabla Sistema-Cuartel
				 **/
				String sql_1="UPDATE Cuartel SET Nombre='"+nombre_+"',Variedad='"+variedad_+"',Categoría='"+categoria_+"',Tamaño="+tamano_+",Plantas_Há="+plantas_Ha_+",Goteros_Planta="+goteros_planta_+",Caudal_Gotero="+caudal_gotero_+",Punto_Eficiencia="+ef_bomba_+" where ID_Cuartel="+id_cuart+";";
				String sql_2="UPDATE Sistema_Cuartel SET Mes_Año='"+fecha_+"',Evapotranspiración="+evapotrans_+",Coef_Cultivo="+coef_cult_+",Precipitación_Sist="+precipitacion_+",Eficiencia_Sist_Riego="+eficiencia_sist_+",Horas_Riego_Efectivas="+horas_efect_+",Horas_Riego_Teóricas="+horas_teo_+",Consumo_Cuartel="+consumo_cuartel+",Consumo_Teo_Cuartel="+consumo_teo+" where ID_Sistema="+id_sist+" AND ID_Cuartel="+id_cuart+" AND Mes_Año='"+mes_anio+"';";
				
				cBD.conectar();
				try{
					cBD.getStat().executeUpdate(sql_1);
					cBD.getStat().executeUpdate(sql_2);

				}catch(SQLException e1){
					e1.printStackTrace();
					cBD.desconectar();
				}
				
				Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
				nuevo.setVisible(true);
				dispose();	
				
				
			}
		});
		btnTerminarEdicin.setBounds(153, 306, 134, 23);
		contentPane.add(btnTerminarEdicin);
		
		
		JLabel lblPrecipitacion = new JLabel("Precipitaci\u00F3n Sistema");
		lblPrecipitacion.setBounds(333, 95, 112, 14);
		contentPane.add(lblPrecipitacion);
		
		JLabel lblHorasDeControl = new JLabel("Horas de Control de Riego ");
		lblHorasDeControl.setBounds(297, 219, 154, 14);
		contentPane.add(lblHorasDeControl);
		
		
		JLabel lblConsumoCuartel = new JLabel("Consumo Cuartel ");
		lblConsumoCuartel.setBounds(175, 234, 112, 14);
		contentPane.add(lblConsumoCuartel);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/**- codigo para generar archivo xlsx -**/


				String rutaArchivo = System.getProperty("user.home")+"/Cuartel "+nom_cuart+" -MES "+mes_anio+".xls";
				File archivoXLS = new File(rutaArchivo);
				if(archivoXLS.exists()) {
					archivoXLS.delete();
				}
				try {
					archivoXLS.createNewFile();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				@SuppressWarnings("resource")
				Workbook libro = new HSSFWorkbook();
				FileOutputStream archivo = null;
				try {
					archivo = new FileOutputStream(archivoXLS);
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				Sheet hoja = libro.createSheet("Detalle "+mes_anio);
				/**
				 * crear arreglo con los titulos de las columnas (OK ^w^ )
				 * y arreglo (o matriz) con los valores asociados ( OK ^w^ )
				 **/
				String[] titulos = {"Campo","Nombre","Variedad","Categoría","Tamaño","Plantas / Há","Goteros / Planta","Caudal / Gotero","Caudal / Cuartel","Punto de Eficiencia Bomba","Mes","Año","ETo","Coef. Cultivo","Precipitación Sist.","Eficiencia Sist. de Riego","Horas de Riego Efectivas","Horas de Riego Teóricas","Consumo Cuartel","Consumo Teórico Cuartel","Horas de Control de Riego"};
				String[] datos = {nom_campo,nombre.getText(),variedad.getText(),categoria.getText(),tamano.getText(),plantas_Ha.getText(),goteros_planta.getText(),caudal_gotero.getText(),caudal_cuartel.getText(),ef_bomba.getText(),fecha.getText(),txtf_anio.getText(),evapotrans.getText(),coef_cult.getText(),precipitacion.getText(),eficiencia_sist.getText(),horas_efect.getText(),horas_teo.getText(),consumo_cuart.getText(),consumo_teo.getText(),hrs_Control.getText()};
				for(int f=0;f<2;f++){ //arreglar tipo de datos (array de String y array de float)
					
					Row fila = hoja.createRow(f);
					for(int c=0;c<21;c++){
					   Cell celda = fila.createCell(c);
						if(f==0){
						   celda.setCellValue(titulos[c]); //encabezado de las filas
						}else{
							celda.setCellValue(datos[c]); //datos duros para copiar en el excel
						}
					   
					}
					
				}
				try {
					libro.write(archivo);
					archivo.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					Desktop.getDesktop().open(archivoXLS);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnGenerarReporte.setBounds(10, 306, 134, 23);
		contentPane.add(btnGenerarReporte);
		
		JLabel lblPuntoDeEficiencia = new JLabel("Punto de Eficiencia");
		lblPuntoDeEficiencia.setBounds(10, 234, 112, 14);
		contentPane.add(lblPuntoDeEficiencia);
		
		
		JLabel lblBomba = new JLabel("Bomba");
		lblBomba.setBounds(10, 251, 112, 14);
		contentPane.add(lblBomba);
		
		JLabel lblConsumoCuartelterico = new JLabel("Consumo Cuartel (Te\u00F3rico)");
		lblConsumoCuartelterico.setBounds(307, 265, 134, 14);
		contentPane.add(lblConsumoCuartelterico);
		
		
	
		
		
		setLocationRelativeTo(null);

	}
}

