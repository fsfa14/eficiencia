import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JComboBox;

import control.ControlBD;
import control.Nom_e_ID;

import javax.swing.JLabel;
import javax.swing.JCheckBox;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.swing.SwingConstants;


public class Sist_Riego_frame extends JFrame {

	private JPanel contentPane;
	private JTextField txtf_nom_sist;
	private JTextField txtf_tipo_con;
	private JComboBox n_sist_Riego;
	private JComboBox n_Cuarteles;
	private JComboBox n_Bombas;
	private JCheckBox ckbx_N_Sistema;
	private String[] sistemas_riego;
	private ControlBD cBD = new ControlBD();

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 * @param idEmpresa 
	 * @param nom_Empresa 
	 * @param idCampo 
	 * @param nom_campo 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Sist_Riego_frame(final String nom_campo, final int idCampo, final String nom_Empresa, final int idEmpresa) {
		setTitle("Sistemas de Riego - Campo "+nom_campo+" - Empresa "+nom_Empresa);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FelipeSebasti\u00E1n\\Desktop\\Mas Recursos Naturales\\Proyecto_Ef_Energetica\\src\\logo_2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 496, 349);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnNewButton = new JButton("Volver...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Campo_frame n_ventana = new Campo_frame(nom_Empresa,idEmpresa);
				n_ventana.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setBounds(10, 11, 107, 23);
		contentPane.add(btnNewButton);
		
		txtf_nom_sist = new JTextField();
		txtf_nom_sist.setEditable(false);
		txtf_nom_sist.setColumns(10);
		txtf_nom_sist.setBounds(181, 45, 112, 20);
		contentPane.add(txtf_nom_sist);
		
		cBD.conectar();
		sistemas_riego=cBD.getSist_Riego(idCampo);
		cBD.desconectar();
		
		n_sist_Riego = new JComboBox(sistemas_riego);
		n_sist_Riego.setBounds(10, 45, 140, 20);
		contentPane.add(n_sist_Riego);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			@SuppressWarnings({ })
			public void actionPerformed(ActionEvent arg0) {
				
				//rescatar sistemas de riego del campo
				
				String n_Sist=(String)n_sist_Riego.getSelectedItem();
				txtf_nom_sist.setEditable(false);
				txtf_tipo_con.setEditable(false);
				String sql="SELECT Nombre_Sist, Tipo_Conexion from Sistema_de_Riego where ID_Campo="+idCampo+" and Nombre_Sist='"+n_Sist+"';";
				ResultSet rst;
				cBD.conectar();
				try{ 
					rst=cBD.getStat().executeQuery(sql);
					while(rst.next()){
						txtf_nom_sist.setText(rst.getString("Nombre_Sist"));
						txtf_tipo_con.setText(rst.getString("Tipo_Conexion"));
					}
					n_Bombas.setModel(new javax.swing.DefaultComboBoxModel(cBD.getBombas_cbbx(n_Sist, idCampo).getNombres()));
					n_Cuarteles.setModel(new javax.swing.DefaultComboBoxModel(cBD.getCuarteles_cbbx(n_Sist, idCampo).getNombres()));
					
					cBD.desconectar();
				}catch(SQLException e1){
					e1.printStackTrace();
					cBD.desconectar();
				}
				
				
			}
		});
		btnVer.setBounds(10, 76, 107, 23);
		contentPane.add(btnVer);
		
		txtf_tipo_con = new JTextField();
		txtf_tipo_con.setEditable(false);
		txtf_tipo_con.setColumns(10);
		txtf_tipo_con.setBounds(181, 87, 112, 20);
		contentPane.add(txtf_tipo_con);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtf_nom_sist.setEditable(true);
				txtf_tipo_con.setEditable(true);

				
			}
		});
		btnEditar.setBounds(343, 28, 107, 23);
		contentPane.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nom_sist_chkbx = (String) n_sist_Riego.getSelectedItem();
				String sql="DELETE from Sistema_de_Riego where ID_Campo="+idCampo+" and Nombre_Sist='"+nom_sist_chkbx+"';";
				//hacer eliminacion
				if(nom_sist_chkbx.equals("Nuevo Sistema...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Sistema de Riego...");
				}else{
					
					int rpta=JOptionPane.showConfirmDialog(null, "Va a elimiar los datos del Sistema de Riego "+". ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(rpta == JOptionPane.YES_OPTION){
						
						cBD.conectar();
						try{
							cBD.getStat().executeUpdate(sql);
							cBD.desconectar();
						}catch(SQLException e){
							e.printStackTrace();
							cBD.desconectar();
						}
						
					}
					
				}
						
				
			}
		});
		btnEliminar.setBounds(343, 109, 107, 23);
		contentPane.add(btnEliminar);
		
		n_Cuarteles = new JComboBox();
		n_Cuarteles.setBounds(181, 205, 140, 20);
		contentPane.add(n_Cuarteles);
		
		n_Bombas = new JComboBox();
		n_Bombas.setBounds(10, 205, 140, 20);
		contentPane.add(n_Bombas);
		
		JButton btnVerCuartel = new JButton("Ver Cuartel");
		btnVerCuartel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id_sist = 0;
				String nom_cuartel;
				String nom_sist = (String) n_sist_Riego.getSelectedItem();
				if(nom_sist.equals("Nuevo Sistema...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Sistema de Riego...");

				}else{
					
					nom_cuartel = (String) n_Cuarteles.getSelectedItem();
					if(nom_cuartel.equals("Nuevo Cuartel...")){
						//ventana para insertar un nuevo cuartel con los datos del mes asociado
						int rpta=JOptionPane.showConfirmDialog(null, "Va a agregar un nuevo Cuartel.¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(rpta == JOptionPane.YES_OPTION){
							cBD.conectar();
							try{
								String sql_0 = "SELECT ID_Sistema from Sistema_de_Riego where ID_Campo="+idCampo+" and Nombre_Sist='"+nom_sist+"';";
								ResultSet rst = cBD.getStat().executeQuery(sql_0);
								rst.next();
								id_sist = rst.getInt("ID_Sistema");
								cBD.desconectar();
							}catch(SQLException e1){
								e1.printStackTrace();
								cBD.desconectar();
							}
							Cuartel_frame nuevo = new Cuartel_frame(nom_campo,idCampo,nom_Empresa,idEmpresa,id_sist);
							nuevo.setVisible(true);
							dispose();
						}else{
							
							JOptionPane.showMessageDialog(null, "No se ha agregado información...");
						}
						
					}else{
						
						//cuartel existente
						cBD.conectar();
						int seleccion=n_Cuarteles.getSelectedIndex();
						Nom_e_ID datos = cBD.getCuarteles_cbbx(nom_sist, idCampo);
						int id_cuart = datos.getIdPosicion(seleccion);
						String nom_cuart = datos.getNombrePosicion(seleccion);
						
						
						try{
							String sql_0 = "SELECT ID_Sistema from Sistema_de_Riego where ID_Campo="+idCampo+" and Nombre_Sist='"+nom_sist+"';";
							ResultSet rst = cBD.getStat().executeQuery(sql_0);
							rst.next();
							id_sist = rst.getInt("ID_Sistema");
							cBD.desconectar();
						}catch(SQLException e1){
							e1.printStackTrace();
						}
						cBD.desconectar();
						
						String mes_anio = JOptionPane.showInputDialog(" Ingrese el mes para ver datos\n de consumo hídrico.Para ingresar una nueva fecha,\n deje este cuadro en blanco y haga click en ''Aceptar''\n (Ejemplo: ENERO).");						
						String anio = "";
						
						if(mes_anio.equals("")){
							//ventana para NO editar la info. del cuartel
							//e insertar un nuevo mes
							
							Cuartel_frame_2 nuevo = new Cuartel_frame_2(nom_campo,idCampo,nom_Empresa,idEmpresa, id_sist, nom_cuart, id_cuart);
							nuevo.setVisible(true);
							dispose();
							
							
						}else{
							//ventana para editar la info. del cuartel
							while(anio.equals("")){
								anio = JOptionPane.showInputDialog(" Ingrese el año para ver datos\n de consumo hídrico.\n (Ejemplo: 2015).");;
							}
							
							Cuartel_frame_3 nuevo = new Cuartel_frame_3(nom_campo,idCampo,nom_Empresa,idEmpresa,id_sist,nom_cuart,id_cuart,mes_anio,anio);
							nuevo.setVisible(true);
							dispose();		
							
						}
						
									
					}
					
				}
				
			}
		});
		btnVerCuartel.setBounds(181, 236, 107, 23);
		contentPane.add(btnVerCuartel);
		
		JButton btnVerBomba = new JButton("Ver Bomba");
		btnVerBomba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id_sist = 0;
				String nom_bomba;
				String nom_sist = (String) n_sist_Riego.getSelectedItem();
				if(nom_sist.equals("Nuevo Sistema...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Sistema de Riego...");

				}else{
					
					nom_bomba = (String) n_Bombas.getSelectedItem();
					if(nom_bomba.equals("Nueva Bomba...")){
						
						int rpta=JOptionPane.showConfirmDialog(null, "Va a agregar una nueva Bomba.¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(rpta == JOptionPane.YES_OPTION){
							cBD.conectar();
							try{
								String sql_0 = "SELECT ID_Sistema from Sistema_de_Riego where ID_Campo="+idCampo+" and Nombre_Sist='"+nom_sist+"';";
								ResultSet rst = cBD.getStat().executeQuery(sql_0);
								rst.next();
								id_sist = rst.getInt("ID_Sistema");
							}catch(SQLException e){
								e.printStackTrace();
								cBD.desconectar();
							}
							
							Bomba_frame nuevo = new Bomba_frame(nom_campo,idCampo,nom_Empresa,idEmpresa,id_sist);
							nuevo.setVisible(true);
							dispose();
							
						}else{
							JOptionPane.showMessageDialog(null, "No se ha agregado información...");						
						}
					}else{
						
						cBD.conectar();
						int seleccion=n_Bombas.getSelectedIndex();
						Nom_e_ID datos = cBD.getBombas_cbbx(nom_sist, idCampo);
						int id_bomb = datos.getIdPosicion(seleccion);
						String nom_bomb = datos.getNombrePosicion(seleccion);
						cBD.desconectar();
						
						Bomba_frame_2 nuevo = new Bomba_frame_2(nom_campo,idCampo,nom_Empresa,idEmpresa,id_bomb,nom_bomb);
						nuevo.setVisible(true);
						dispose();
					}
				}
				
				
			}
		});
		btnVerBomba.setBounds(10, 236, 107, 23);
		contentPane.add(btnVerBomba);
		
		JLabel lblNombreSistematipo = new JLabel("Nombre Sistema (Tipo)");
		lblNombreSistematipo.setBounds(181, 30, 140, 14);
		contentPane.add(lblNombreSistematipo);
		
		JLabel lblTipoDeConexin = new JLabel("Tipo de Conexi\u00F3n");
		lblTipoDeConexin.setBounds(181, 72, 112, 14);
		contentPane.add(lblTipoDeConexin);
		
		JButton btnTerminarEdicin = new JButton("Terminar Edici\u00F3n");
		btnTerminarEdicin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtf_nom_sist.setEditable(false);
				txtf_tipo_con.setEditable(false);
				
				int rpta;
				boolean chk = ckbx_N_Sistema.isSelected();
				if(chk == true){
					
					rpta=JOptionPane.showConfirmDialog(null, "Va a ingresar un nuevo Sistema de Riego. ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(rpta == JOptionPane.YES_OPTION){
						
						String nom_sist = txtf_nom_sist.getText();
						String tipo_con = txtf_tipo_con.getText();
						String sql = "INSERT into Sistema_de_Riego (ID_Campo,Nombre_Sist,Tipo_Conexion) VALUES ("+idCampo+",'"+nom_sist+"','"+tipo_con+"');";
						cBD.conectar();
						try{
							
							cBD.getStat().executeUpdate(sql);
							cBD.desconectar();
							
							Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
							nuevo.setVisible(true);
							dispose();	
							
						}catch(SQLException e){
							e.printStackTrace();
							cBD.desconectar();
						}
						
					}else{
						
						JOptionPane.showMessageDialog(null, "No se ha agregado información...");						
					}					
				}else{
					
					//editar info
					
					String nom_sist_chkbx = (String) n_sist_Riego.getSelectedItem();
					String nom_sist = txtf_nom_sist.getText();
					String tipo_con = txtf_tipo_con.getText();
					String sql_2 = "UPDATE Sistema_de_Riego SET (Nombre_Sist='"+nom_sist+"',Tipo_Conexion='"+tipo_con+"') where ID_Campo="+idCampo+" and Nombre_Sist='"+nom_sist_chkbx+"';";
					
					cBD.conectar();					
					try{
						
						cBD.getStat().executeUpdate(sql_2);
						cBD.desconectar();
						JOptionPane.showMessageDialog(null, "Se han actualizado los datos...");

					}catch(SQLException e){
						e.printStackTrace();
						cBD.desconectar();
					}
					
					Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,nom_Empresa,idEmpresa);
					nuevo.setVisible(true);
					dispose();
					
				}
				
				
				
			}
		});
		btnTerminarEdicin.setBounds(331, 70, 130, 23);
		contentPane.add(btnTerminarEdicin);
		
		ckbx_N_Sistema = new JCheckBox("Nuevo Sistema");
		ckbx_N_Sistema.setBounds(181, 109, 123, 23);
		contentPane.add(ckbx_N_Sistema);
		
		JButton btnReporteAnual = new JButton("Reporte Anual");
		btnReporteAnual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int id_sist = 0;
				String nom_cuartel;
				String nom_sist = (String) n_sist_Riego.getSelectedItem();
				if(nom_sist.equals("Nuevo Sistema...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Sistema de Riego...");

				}else{
					
					nom_cuartel = (String) n_Cuarteles.getSelectedItem();
					if(nom_cuartel.equals("Nuevo Cuartel...")){ 
						
						JOptionPane.showMessageDialog(null, "Debe seleccionar un Cuartel...");
						
					}else{
						int seleccion = n_Cuarteles.getSelectedIndex();
						String nom_cuart = (String) n_Cuarteles.getSelectedItem();
						cBD.conectar();
						Nom_e_ID dato = cBD.getCuarteles_cbbx(nom_sist, idCampo);
						int id_cuart = dato.getIdPosicion(seleccion);
						cBD.desconectar();
						
						String anio = "";
						while(anio.equals("")){
							anio = JOptionPane.showInputDialog(" Ingrese el año para generar reporte con datos\n de consumo hídrico.\n (Ejemplo: 2015).");;
						}
						
						String[] titulos = {"Campo","Nombre","Variedad","Categoría","Tamaño","Plantas / Há","Goteros / Planta","Caudal / Gotero","Caudal / Cuartel","Punto de Eficiencia Bomba","Mes","Año","ETo","Coef. Cultivo","Precipitación Sist.","Eficiencia Sist. de Riego","Horas de Riego Efectivas","Horas de Riego Teóricas","Consumo Cuartel","Horas de Control de Riego"};
						String [][] datos = null;
						float [][]datos_num = null;
						cBD.conectar();
						try{
							String sql_0 = "SELECT ID_Sistema from Sistema_de_Riego where ID_Campo="+idCampo+" and Nombre_Sist='"+nom_sist+"';";
							ResultSet rst = cBD.getStat().executeQuery(sql_0);
							rst.next();
							id_sist = rst.getInt("ID_Sistema");
							cBD.desconectar();
						}catch(SQLException e1){
							e1.printStackTrace();
							cBD.desconectar();
						}
						
						String sql_dim = "SELECT COUNT (*) AS num_Filas FROM Sistema_Cuartel WHERE ID_Sistema = "+id_sist+" AND ID_Cuartel = "+id_cuart+" AND Año = '"+anio+"';";
						String sql_1 = "SELECT Nombre,Variedad,Categoría,Tamaño,Plantas_Há,Goteros_Planta,Caudal_Gotero,Caudal_Cuartel,Punto_Eficiencia FROM Cuartel WHERE ID_Cuartel="+id_cuart+";";
						String sql_2 = "SELECT Mes_Año,Año,Evapotranspiración,Coef_Cultivo,Precipitación_Sist,Eficiencia_Sist_Riego,Horas_Riego_Efectivas,Horas_Riego_Teóricas,Consumo_Cuartel FROM Sistema_Cuartel WHERE ID_Sistema = "+id_sist+" AND ID_Cuartel = "+id_cuart+" AND Año = '"+anio+"';";
						int num_filas=0;
						cBD.conectar();
						try{
							
							ResultSet rst = cBD.getStat().executeQuery(sql_dim);
							rst.next();
							num_filas=rst.getInt("num_Filas");
							num_filas++;
							//System.out.println(num_filas);
							datos = new String[num_filas][20];
							datos_num = new float[num_filas][20];
							rst = cBD.getStat().executeQuery(sql_1);
							int i = 0;
							while(rst.next()){
								
								if(i==0){
									for(int c = 0;c < 10;c++){
										datos[i][c] = titulos[c];
									}
								}
								
								datos[1][0] = nom_campo;
								datos[1][1] = rst.getString("Nombre");
								datos[1][2] = rst.getString("Variedad");
								datos[1][3] = rst.getString("Categoría");
								datos_num[1][4] = (rst.getFloat("Tamaño"));
								datos_num[1][5] = (rst.getFloat("Plantas_Há"));
								datos_num[1][6] = (rst.getFloat("Goteros_Planta"));
								datos_num[1][7] = (rst.getFloat("Caudal_Gotero"));
								datos_num[1][8] = (rst.getFloat("Caudal_Cuartel"));
								datos_num[1][9] = rst.getFloat("Punto_Eficiencia");
								
								for(int f = 2;f < num_filas;f++){
									
									datos[f][0] = nom_campo;
									datos[f][1] = datos[1][1];
									datos[f][2] = datos[1][2];
									datos[f][3] = datos[1][3];
									datos_num[f][4] = datos_num[1][4];
									datos_num[f][5] = datos_num[1][5];
									datos_num[f][6] = datos_num[1][6];
									datos_num[f][7] = datos_num[1][7];
									datos_num[f][8] = datos_num[1][8];
									datos_num[f][9] = datos_num[1][9];
									
								}
								
							}
							
							rst = cBD.getStat().executeQuery(sql_2);
							int j = 0;
							while(rst.next()){
								
								if(j==0){
									
									for(int c = 10;c < 20;c++){
										datos[j][c] = titulos[c];
									}
								}
								j++;
								datos[j][10] = rst.getString("Mes_Año");
								datos[j][11] = rst.getString("Año");
								datos_num[j][12] = (rst.getFloat("Evapotranspiración"));
								datos_num[j][13] = (rst.getFloat("Coef_Cultivo"));
								datos_num[j][14] = (rst.getFloat("Precipitación_Sist"));
								float eficiencia1 = rst.getFloat("Eficiencia_Sist_Riego");
								datos_num[j][15] = (eficiencia1);
								datos_num[j][16] = (rst.getFloat("Horas_Riego_Efectivas"));
								float hrs_teo = rst.getFloat("Horas_Riego_Teóricas");
								datos_num[j][17] = (hrs_teo);
								datos_num[j][18] = (rst.getFloat("Consumo_Cuartel"));
								float hrs_control = (hrs_teo*100)/eficiencia1;
								datos_num[j][19] = (hrs_control);
										
							}
							
							
						}catch(SQLException e){
							e.printStackTrace();
							cBD.desconectar();
						}
						
						/**- codigo para generar archivo xls -**/
						/*for(int x=0;x<num_filas;x++){
							for(int y=0;y<19;y++){
								System.out.print(datos[x][y]+" ");
							}
							System.out.println("");
						}*/

						String rutaArchivo = System.getProperty("user.home")+"/Cuartel "+nom_cuart+" -AÑO "+anio+".xls";
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
						Sheet hoja = libro.createSheet("Detalle "+anio);
						/**
						 * crear arreglo con los titulos de las columnas (OK ^w^ )
						 * y arreglo (o matriz) con los valores asociados ( PENDIENTE )
						 **/
						System.out.println(datos.length);
						for(int f=0;f<num_filas;f++){
							/**
							 * OJO AQUI
							 * 
							 * **/
							Row fila = hoja.createRow(f);
							for(int c=0;c<20;c++){
								Cell celda = fila.createCell(c);
								if(f==0){
									celda.setCellValue(titulos[c]);
								}else{
									if(c<4){
										celda.setCellValue(datos[f][c]);
									}else{
										if(c<10){
											celda.setCellValue(datos_num[f][c]);
										}else{
											if(c<12){
												celda.setCellValue(datos[f][c]);
											}else{
												if(c<20){
													celda.setCellValue(datos_num[f][c]);
												}
											}
										}
									}
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
					
				}
				
				
				
			}
		});
		btnReporteAnual.setBounds(331, 204, 130, 23);
		contentPane.add(btnReporteAnual);
		
		JButton btnReporteMensualSist = new JButton("Reporte Mensual Sist.");
		btnReporteMensualSist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String[] titulos = {"Campo","Nombre","Variedad","Categoría","Tamaño","Plantas / Há","Goteros / Planta","Caudal / Gotero","Caudal / Cuartel","Punto de Eficiencia Bomba","Mes","Año","ETo","Coef. Cultivo","Precipitación Sist.","Eficiencia Sist. de Riego","Horas de Riego Efectivas","Horas de Riego Teóricas","Consumo Cuartel","Consumo Teórico Cuartel","Caudal/ha (m3) Real"};
				int [] id_cuarteles;
				String [] datos = new String[21];
				float [] datos_num = new float[21];
				int a=0;
				String nom_sistema = (String) n_sist_Riego.getSelectedItem();
				
				if(nom_sistema.equals("Nuevo Sistema...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Sistema de Riego...");

				}else{
					
					String mes_anio = "";
					while(mes_anio.equals("")){						
						mes_anio = JOptionPane.showInputDialog(" Ingrese el mes para ver datos de consumo hídrico \n del Sistema de Riego asociado.\n (Ejemplo: ENERO).");
					}
					
					String anio = "";
					while(anio.equals("")){
						anio = JOptionPane.showInputDialog(" Ingrese el año para ver datos\n de consumo hídrico.\n (Ejemplo: 2015).");;
					}
					
					cBD.conectar();
					Nom_e_ID datos_cuart = cBD.getCuarteles_cbbx(nom_sistema, idCampo);
					id_cuarteles = datos_cuart.getIDs();

					//se crea el archivo
					String rutaArchivo = System.getProperty("user.home")+"/Sistema "+nom_sistema+"- Campo "+nom_campo+".xls";
					//System.out.println(rutaArchivo);
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
					//se crea una nueva hoja
					Sheet hoja = libro.createSheet("Detalle "+mes_anio);
					
					
					for(int i=1; i<(id_cuarteles.length);i++){
						if(i==(id_cuarteles.length-1)&&a!=0){
							//System.out.println("entré");
							i=1;
						}
						String sql_1 = "SELECT Nombre,Variedad,Categoría,Tamaño,Plantas_Há,Goteros_Planta,Caudal_Gotero,Caudal_Cuartel,Punto_Eficiencia FROM Cuartel WHERE ID_Cuartel="+id_cuarteles[i]+";";
						String sql_2 ="SELECT Mes_Año,Año,Evapotranspiración,Coef_Cultivo,Precipitación_Sist,Eficiencia_Sist_Riego,Horas_Riego_Efectivas,Horas_Riego_Teóricas,Consumo_Cuartel,Consumo_Teo_Cuartel,Caudal_h_m3_real FROM Sistema_Cuartel WHERE ID_Cuartel="+id_cuarteles[i]+" AND Mes_Año='"+mes_anio+"' AND Año='"+anio+"';";
						//System.out.println("i="+i);
						//System.out.println(id_cuarteles[i]);
						//se hacen las consultas
						cBD.conectar();
						try{
							//consulta n°1
							ResultSet rst = cBD.getStat().executeQuery(sql_1);
							while(rst.next()){
								datos[0] = nom_campo;
								for(int j=1;j<10;j++){
									if(j<4){
										datos[j] = rst.getString(j);
									}else{
										if(j<10){
											datos_num[j] = rst.getFloat(j);
										}
									}
								}
							}
							//consulta n°2
							rst = cBD.getStat().executeQuery(sql_2);
							while(rst.next()){
								for(int j=10,k=1;j<21;j++,k++){
									if(j<12){
										datos[j] = rst.getString(k);
									}else{
										if(j<21){
											datos_num[j] = rst.getFloat(k);
										}
									}
								}
								
							}							
							cBD.desconectar();
							
						}catch(SQLException e){
							e.printStackTrace();
							cBD.desconectar();
						}
						
						//se crea una nueva fila en la hoja
						Row fila = hoja.createRow(i);
						for(int c=0;c<21;c++){
							Cell celda = fila.createCell(c);
							if(a==0){
								
								celda.setCellValue(titulos[c]); //encabezado de las filas
								
							}else{
								if(c<4){
									celda.setCellValue(datos[c]);
								}else{
									if(c<10){
										celda.setCellValue(datos_num[c]);
									}else{
										if(c<12){
											celda.setCellValue(datos[c]);
										}else{
											if(c<21){
												celda.setCellValue(datos_num[c]);
											}//else{
												//float hrs_ctrl = (datos_num[17]*100)/datos_num[15];//cambiar por Caudal_h_m3_real
												//celda.setCellValue(hrs_ctrl);
											//}
										}
									}
								}							
							}
							
						}
						a++;
						//System.out.println(a);
						if(a==id_cuarteles.length-1){
							//System.out.println("salir(hacer i=a)");
							i=a;
							fila = hoja.createRow(0);
							for(int c=0;c<21;c++){
								Cell celda = fila.createCell(c);
								celda.setCellValue(titulos[c]); //encabezado de las filas	
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
				
				
			}
		});
		btnReporteMensualSist.setBounds(10, 110, 150, 23);
		contentPane.add(btnReporteMensualSist);
		
		JButton btnReporteAnualSist = new JButton("Reporte Anual Sist.");
		btnReporteAnualSist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String[] titulos = {"Campo","Nombre","Variedad","Categoría","Tamaño","Plantas / Há","Goteros / Planta","Caudal / Gotero","Caudal / Cuartel","Punto de Eficiencia Bomba","Mes","Año","ETo","Coef. Cultivo","Precipitación Sist.","Eficiencia Sist. de Riego","Horas de Riego Efectivas","Horas de Riego Teóricas","Consumo Cuartel","Consumo Teórico Cuartel","Caudal/ha (m3) Real"};
				String[][] datos = null;
				float [][] datos_num = null;
				int filasExcel = 0;
				int numCuarteles;
				int num_filas = 0;
				int id_sist = 0;
				String nom_cuartel;
				String nom_sist = (String) n_sist_Riego.getSelectedItem();
				if(nom_sist.equals("Nuevo Sistema...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Sistema de Riego...");

				}else{
					String anio = "";
					while(anio.equals("")){
						anio = JOptionPane.showInputDialog(" Ingrese el año para ver datos\n de consumo hídrico.\n (Ejemplo: 2015).");;
					}
					//se cuentan cuantas filas va a tener el reporte anual
					String sql = "SELECT COUNT (*) AS num_filas FROM Sistema_Cuartel WHERE Año='"+anio+"' AND ID_Sistema=(SELECT ID_Sistema FROM Sistema_de_Riego WHERE ID_Campo="+idCampo+" AND Nombre_Sist='"+nom_sist+"');";
					cBD.conectar();
					ResultSet rst;
					try{
						rst = cBD.getStat().executeQuery(sql);
						rst.next();
						num_filas = rst.getInt("num_filas");
						System.out.println(num_filas);
						cBD.desconectar();
						//se dimensionan las matrices
						datos = new String[num_filas+1][21];
						datos_num = new float[num_filas+1][21];
						
						
					}catch(SQLException e){
						e.printStackTrace();
						cBD.desconectar();
					}
					cBD.conectar();
					//se obtienen los ID_Cuarteles en un array
					Nom_e_ID datos_cuarteles = cBD.getCuarteles_cbbx(nom_sist, idCampo);
					int []cuarteles = datos_cuarteles.getIDs();
					cBD.desconectar();
					System.out.println(cuarteles.length+"\n");
					for(int i=1;i<cuarteles.length-1;i++){
						System.out.println(cuarteles[i]);
					}
					
					numCuarteles = cuarteles.length-1;
					
					//cantidad de veces que se repite la info del cuartel
					int cant_meses=num_filas/(numCuarteles-1);
					
					System.out.println("\n"+cant_meses);
					
					String sql_2 ="SELECT Mes_Año,Año,Evapotranspiración,Coef_Cultivo,Precipitación_Sist,Eficiencia_Sist_Riego,Horas_Riego_Efectivas,Horas_Riego_Teóricas,Consumo_Cuartel,Consumo_Teo_Cuartel,Caudal_h_m3_real,ID_Cuartel FROM Sistema_Cuartel WHERE Año='"+anio+"' AND ID_Sistema=(SELECT ID_Sistema FROM Sistema_de_Riego WHERE ID_Campo="+idCampo+" AND Nombre_Sist='"+nom_sist+"') ORDER BY ID_Cuartel DESC;";
					int fila = 0;
					cBD.conectar();
					try{
						rst = cBD.getStat().executeQuery(sql_2);
						//primero se rescatan los valores de la tabla de riego
						while(rst.next()){
							fila++;
							for(int col=10,k=1;col<21;col++,k++){
								if(col<12){
									
									datos[fila][col] = rst.getString(k);
								}else{
									
									if(col<21){
										
										datos_num[fila][col] = rst.getFloat(k);
									}
								}
							}
						}
						
					}catch(SQLException e){
						e.printStackTrace();
						cBD.desconectar();
					}
					
					int cuart_actual;
					cBD.conectar();
					for(cuart_actual=1;cuart_actual<numCuarteles;cuart_actual++){
						String sql_1 = "SELECT Nombre,Variedad,Categoría,Tamaño,Plantas_Há,Goteros_Planta,Caudal_Gotero,Caudal_Cuartel,Punto_Eficiencia FROM Cuartel WHERE ID_Cuartel="+cuarteles[cuart_actual]+";";
						try{
							
							rst = cBD.getStat().executeQuery(sql_1);
							while(rst.next()){
								int copias=1;
								//for(int copias = 1;copias<cant_meses;copias++)
									//copia n°1
								int filas = cant_meses*(cuart_actual-1)+copias;
								datos[filas][0] = nom_campo;
								datos[filas][1] = rst.getString("Nombre");
								datos[filas][2] = rst.getString("Variedad");
								datos[filas][3] = rst.getString("Categoría");
								datos_num[filas][4] = (rst.getFloat("Tamaño"));
								datos_num[filas][5] = (rst.getFloat("Plantas_Há"));
								datos_num[filas][6] = (rst.getFloat("Goteros_Planta"));
								datos_num[filas][7] = (rst.getFloat("Caudal_Gotero"));
								datos_num[filas][8] = (rst.getFloat("Caudal_Cuartel"));
								datos_num[filas][9] = rst.getFloat("Punto_Eficiencia");
								
								for(copias = 1;copias < cant_meses;copias++,filas++){
									
									datos[filas+1][0] = nom_campo;
									datos[filas+1][1] = datos[filas][1];
									datos[filas+1][2] = datos[filas][2];
									datos[filas+1][3] = datos[filas][3];
									datos_num[filas+1][4] = datos_num[filas][4];
									datos_num[filas+1][5] = datos_num[filas][5];
									datos_num[filas+1][6] = datos_num[filas][6];
									datos_num[filas+1][7] = datos_num[filas][7];
									datos_num[filas+1][8] = datos_num[filas][8];
									datos_num[filas+1][9] = datos_num[filas][9];
									
								}
							}
							
							
						}catch(SQLException e){
							e.printStackTrace();
							cBD.desconectar();
						}
						
					}
					cBD.desconectar();
					//continuar aca ==>
					//ahora se pasa al excel
					//se crea el archivo
					String rutaArchivo = System.getProperty("user.home")+"/Sistema "+nom_sist+"- Año "+anio+".xls";
					//System.out.println(rutaArchivo);
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
					//se crea una nueva hoja
					Sheet hoja = libro.createSheet("Detalle "+anio);
					for(int f=0;f<num_filas+1;f++){
						
						Row fila_2 = hoja.createRow(f);
						for(int c=0;c<20;c++){
							Cell celda = fila_2.createCell(c);
							if(f==0){
								celda.setCellValue(titulos[c]);
							}else{
								if(c<4){
									celda.setCellValue(datos[f][c]);
								}else{
									if(c<10){
										celda.setCellValue(datos_num[f][c]);
									}else{
										if(c<12){
											celda.setCellValue(datos[f][c]);
										}else{
											if(c<20){
												celda.setCellValue(datos_num[f][c]);
											}
										}
									}
								}
							}
						}
					}
					/****/
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
				
			}
		});
		btnReporteAnualSist.setBounds(10, 144, 150, 23);
		contentPane.add(btnReporteAnualSist);
		
		JButton btnReporteMensualCampo = new JButton("Reporte Mensual Campo.");
		btnReporteMensualCampo.setEnabled(false);
		btnReporteMensualCampo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//genera un reporte mensual de los cuarteles
				//asociados a cada sisema de riego de un campo
				//primero se setean los titulos de las columnas en un array
				String[] titulos = {"Campo","Nombre","Variedad","Categoría","Tamaño","Plantas / Há","Goteros / Planta","Caudal / Gotero","Caudal / Cuartel","Punto de Eficiencia Bomba","Mes","Año","ETo","Coef. Cultivo","Precipitación Sist.","Eficiencia Sist. de Riego","Horas de Riego Efectivas","Horas de Riego Teóricas","Consumo Cuartel","Consumo Teórico Cuartel","Horas de Control de Riego"};
				
				//después, se seleciona el mes y el año del reporte
				String mes_anio = "";
				while(mes_anio.equals("")){						
					mes_anio = JOptionPane.showInputDialog(" Ingrese el mes para ver datos de consumo hídrico \n del Sistema de Riego asociado.\n (Ejemplo: ENERO).");
				}
				
				String anio = "";
				while(anio.equals("")){
					anio = JOptionPane.showInputDialog(" Ingrese el año para ver datos\n de consumo hídrico.\n (Ejemplo: 2015).");;
				}
				Nom_e_ID info_cuarteles;
				int[] id_cuarteles = null;
				//luego, se cuenta la cantidad de filas que va a tener el reporte
				int cant_filas=0;
				cBD.conectar();
				for(int sistemas=1;sistemas<sistemas_riego.length;sistemas++){
					//se selecciona el sistema de la posicion "sistemas" en el array
					//para contar los cuarteles asociados
					info_cuarteles = cBD.getCuarteles_cbbx(sistemas_riego[sistemas], idCampo);
					id_cuarteles = info_cuarteles.getIDs();
					cant_filas = id_cuarteles.length - 2 + cant_filas;
					
				}	
				//se crea el archivo
				String rutaArchivo = System.getProperty("user.home")+"/Campo "+nom_campo+".xls";
				//						System.out.println(rutaArchivo);
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
				//se crea una nueva hoja
				Sheet hoja = libro.createSheet("Detalle "+mes_anio);
				int offset=0;
				for(int sistemas=1;sistemas<sistemas_riego.length;sistemas++){
					//se rescatan la informacion de los cuarteles del sistema
					//en la posicion "sistemas" para el mes y año correspondiente
					
					String sql_1 = "SELECT Nombre,Variedad,Categoría,Tamaño,Plantas_Há,Goteros_Planta,Caudal_Gotero,Caudal_Cuartel,Punto_Eficiencia FROM Cuartel WHERE ID_Cuartel="+id_cuarteles[sistemas]+";";
					String sql_2 ="SELECT Mes_Año,Año,Evapotranspiración,Coef_Cultivo,Precipitación_Sist,Eficiencia_Sist_Riego,Horas_Riego_Efectivas,Horas_Riego_Teóricas,Consumo_Cuartel,Consumo_Teo_Cuartel FROM Sistema_Cuartel WHERE ID_Cuartel="+id_cuarteles[sistemas]+" AND Mes_Año='"+mes_anio+"' AND Año='"+anio+"';";
					// continuar aquí ==>
					
				}
			}
		});
		btnReporteMensualCampo.setBounds(181, 144, 157, 23);
		//contentPane.add(btnReporteMensualCampo);
	}
}
