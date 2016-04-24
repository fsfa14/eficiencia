import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import control.ControlBD;

import javax.swing.JTextField;
import javax.swing.JCheckBox;

import jxl.*;
import jxl.read.biff.BiffException;


@SuppressWarnings("serial")
public class Campo_frame extends JFrame {

	private JPanel contentPane;
	
	private ControlBD cBD=new ControlBD();
	@SuppressWarnings("rawtypes")
	private JComboBox n_campos;
	private JTextField txtf_n_campo;
	private JTextField txtf_Pot_Inst;
	private Workbook origenDatos;
	private int id_campo;
	private int id_sistema;
	private int id_cuartel;
	


	/**
	 * Create the frame.
	 * @param idEmpresa 
	 * @param Nom_Empresa 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Campo_frame(final String Nom_Empresa, final int idEmpresa) {
		
		cBD.conectar();
		String[] campos=cBD.getCampos(idEmpresa);
		cBD.desconectar();
		
		setTitle("Campos - "+Nom_Empresa);
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
				
				inicio nuevo=new inicio();
				nuevo.setVisible(true);
				dispose();
				
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		contentPane.add(btnNewButton);
		
		n_campos = new JComboBox(campos);
		n_campos.setBounds(10, 55, 128, 20);
		contentPane.add(n_campos);
		
		JLabel lblNombreCampo = new JLabel("Nombre Campo");
		lblNombreCampo.setBounds(10, 86, 112, 14);
		contentPane.add(lblNombreCampo);
		
		txtf_n_campo = new JTextField();
		txtf_n_campo.setEditable(false);
		txtf_n_campo.setColumns(10);
		txtf_n_campo.setBounds(10, 100, 112, 20);
		contentPane.add(txtf_n_campo);
		
		JButton btnNewButton_1 = new JButton("Ver");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//aca va la consulta para llenar los txtfields
				String n_Campo=(String)n_campos.getSelectedItem();
				txtf_n_campo.setEditable(false);
				txtf_Pot_Inst.setEditable(false);
				String sql="SELECT Nombre,Potencia_Instalada from Campo where ID_Empresa="+idEmpresa+" and Nombre='"+n_Campo+"';";
				ResultSet rst;
				cBD.conectar();
				try{
					rst=cBD.getStat().executeQuery(sql);
					while(rst.next()){
						txtf_n_campo.setText(rst.getString("Nombre"));
						txtf_Pot_Inst.setText(Float.toString(rst.getFloat("Potencia_Instalada")));
					}
					cBD.desconectar();
				}catch(SQLException e1){
					e1.printStackTrace();
					cBD.desconectar();
				}
				
			}
		});
		btnNewButton_1.setBounds(162, 54, 77, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblPotenciaInstalada = new JLabel("Potencia Instalada");
		lblPotenciaInstalada.setBounds(10, 126, 112, 14);
		contentPane.add(lblPotenciaInstalada);
		
		txtf_Pot_Inst = new JTextField();
		txtf_Pot_Inst.setEditable(false);
		txtf_Pot_Inst.setColumns(10);
		txtf_Pot_Inst.setBounds(10, 140, 112, 20);
		contentPane.add(txtf_Pot_Inst);
		
		JButton btnConsumoElctrico = new JButton("Consumo El\u00E9ctrico");
		btnConsumoElctrico.setEnabled(false);
		btnConsumoElctrico.setBounds(249, 103, 157, 23);
		contentPane.add(btnConsumoElctrico);
		
		JButton btnConsumoHdrico = new JButton("Consumo H\u00EDdrico");
		btnConsumoHdrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nom_campo = (String) n_campos.getSelectedItem();
				if(nom_campo.equals("Nuevo Campo...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Campo...");
				}else{
				
					cBD.conectar();
					String sql="SELECT ID_Campo from Campo where Nombre='"+nom_campo+"';";
					try{
						ResultSet rst=cBD.getStat().executeQuery(sql);
						rst.next();
						int idCampo=rst.getInt(1);
						cBD.desconectar();
						Sist_Riego_frame nuevo = new Sist_Riego_frame(nom_campo,idCampo,Nom_Empresa,idEmpresa);
						nuevo.setVisible(true);
						dispose();
					}catch(SQLException e2){
						e2.printStackTrace();
						cBD.desconectar();
					}
				}
			}
		});
		btnConsumoHdrico.setBounds(249, 137, 157, 23);
		contentPane.add(btnConsumoHdrico);
		
		JButton btn_Editar = new JButton("Editar");
		btn_Editar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtf_n_campo.setEditable(true);
				txtf_Pot_Inst.setEditable(true);
				
			}
		});
		btn_Editar.setBounds(249, 54, 77, 23);
		contentPane.add(btn_Editar);
		
		JButton btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nom_campo = (String) n_campos.getSelectedItem();
				int rpta;
				if(nom_campo.equals("Nuevo Campo...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar un Campo...");

				}else{
					rpta=JOptionPane.showConfirmDialog(null, "Va a elimiar los datos del Campo "+nom_campo+". ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(rpta == JOptionPane.YES_OPTION){
						
						cBD.conectar();
						String sql_4="DELETE from Campo	where Nombre='"+nom_campo+"' AND ID_Empresa="+idEmpresa+";";  //los nombres de los campos son unicos
						try{
							cBD.getStat().executeUpdate(sql_4);
							
							txtf_n_campo.setText("");
							txtf_Pot_Inst.setText("");				
	
							cBD.desconectar();
							JOptionPane.showMessageDialog(null, "Se ha eliminado la información");
	
							
							Campo_frame n_ventana = new Campo_frame(Nom_Empresa,idEmpresa);
							n_ventana.setVisible(true);
							dispose();
							
						}catch(SQLException e4){
							e4.printStackTrace();
							cBD.desconectar();
						}
	
						
					}else{
						
						JOptionPane.showMessageDialog(null, "No se ha eliminado la información");
						
					}
				}				
			}
		});
		btn_Eliminar.setBounds(336, 54, 87, 23);
		contentPane.add(btn_Eliminar);
		
		final JCheckBox chckbxNuevoCampo = new JCheckBox("Nuevo Campo");
		chckbxNuevoCampo.setBounds(10, 185, 123, 23);
		contentPane.add(chckbxNuevoCampo);
		
		JButton btnTerminarEdicion = new JButton("Terminar Edici\u00F3n");
		btnTerminarEdicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtf_n_campo.setEditable(false);
				txtf_Pot_Inst.setEditable(false);
				
				int rspta;
				boolean chkbox=chckbxNuevoCampo.isSelected();
				if(chkbox==true){
					
					rspta=JOptionPane.showConfirmDialog(null, "Va a ingresar un nuevo Campo. ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(rspta == JOptionPane.YES_OPTION){
						//se obtienen los campos
						String nom = txtf_n_campo.getText();
						String potencia_ = txtf_Pot_Inst.getText();
						float potencia = Float.parseFloat(potencia_);
						String sql_1 = "INSERT INTO Campo (ID_Empresa,Nombre,Potencia_Instalada) VALUES ("+idEmpresa+",'"+nom+"','"+potencia+"');";
						cBD.conectar();
						try{
							cBD.getStat().executeUpdate(sql_1);
							cBD.desconectar();
							
							Campo_frame n_ventana = new Campo_frame(Nom_Empresa,idEmpresa);
							n_ventana.setVisible(true);
							dispose();
							
						}catch(SQLException e1){
							e1.printStackTrace();
							cBD.desconectar();
						}
					}else{
						JOptionPane.showMessageDialog(null, "No se ha agregado la información");
					}
				}else{
					
					String nom_campo = (String) n_campos.getSelectedItem();
					rspta=JOptionPane.showConfirmDialog(null, "Va a modificar los datos del Campo "+nom_campo+". ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(rspta == JOptionPane.YES_OPTION){
						String nom = txtf_n_campo.getText();
						float potencia = Float.parseFloat(txtf_Pot_Inst.getText());
						String sql_3="UPDATE Campo SET Nombre='"+nom+"',Potencia_Instalada="+potencia+" where ID_Empresa="+idEmpresa+" and ID_Campo=(select ID_Campo from Campo where Nombre='"+nom_campo+"');";
						cBD.conectar();
						try{
							cBD.getStat().executeUpdate(sql_3);
							cBD.desconectar();
							
							Campo_frame n_ventana = new Campo_frame(Nom_Empresa,idEmpresa);
							n_ventana.setVisible(true);
							dispose();
							
						}catch(SQLException e2){
							e2.printStackTrace();
							cBD.desconectar();
						}
					}else{
						JOptionPane.showMessageDialog(null, "No se ha modificado la información");
					}
				}
				
				
			}
		});
		btnTerminarEdicion.setBounds(10, 231, 138, 23);
		contentPane.add(btnTerminarEdicion);
		
		final JButton btnCargarArchivo = new JButton("Cargar Archivo");
		btnCargarArchivo.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				
				
				JFileChooser elegir = new JFileChooser();
				//Creamos el filtro
				FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.XLS", "xls");
				  
				 //Le indicamos el filtro
				elegir.setFileFilter(filtro);
                int opcion = elegir.showOpenDialog(btnCargarArchivo);
            
                 //Si presionamos el boton ABRIR en pathArchivo obtenemos el path del archivo
                if (opcion == JFileChooser.APPROVE_OPTION) {
                	String pathArchivo = elegir.getSelectedFile().getPath(); //Obtiene path del archivo
                    String nombre = elegir.getSelectedFile().getName(); //obtiene nombre del archivo
                    
                    System.out.println("El nombre del archivo es: "+ nombre);
                    System.out.println("El path del archivo es: "+ pathArchivo);
                    
                    
                    //comenzar a importar archivo
                   
					try{
						//Pasamos el excel que vamos a cargar
						origenDatos = Workbook.getWorkbook(new File(pathArchivo));
					}catch (BiffException e) {
						e.printStackTrace();
					}catch (IOException e) {
						e.printStackTrace();
					}
					
                    Sheet sheet = origenDatos.getSheet(0);//Seleccionamos la hoja que vamos a leer
                    String[][] datos = new String [sheet.getRows()][18];
                    float [][] datos_num = new float[sheet.getRows()][18];
                    for(int i=0;i<18;i++){
                    	datos[0][i]="void";
                    }
                    
                    int a;
                    a=0;
                    System.out.println(sheet.getRows());
                    for (int fila = 1; fila < sheet.getRows(); fila++) {
                    	for (int columna = 0; columna < sheet.getColumns(); columna++) {
                    		
                    		if(columna<1){
                    			datos[fila][columna] = sheet.getCell(columna, fila).getContents();
                    			a++;
                    			System.out.println("a="+a);
                    		}else{
                    			
                    			if(columna<2){
                    				
                    				datos_num[fila][columna] = Float.parseFloat(sheet.getCell(columna, fila).getContents());
                    				a++;
                    				System.out.println("a="+a);
                    			}else{
                    				
                    				if(columna<5){
                    					
                    					datos[fila][columna] = sheet.getCell(columna, fila).getContents();
                    					a++;
                        				System.out.println("a="+a);
                    				}else{
                    					
                    					if(columna<6){
                    						
                    						datos_num[fila][columna] = Float.parseFloat(sheet.getCell(columna, fila).getContents());
                    						a++;
                            				System.out.println("a="+a);
                    					}else{
                    						
                    						if(columna<8){
                    							
                    							datos[fila][columna] = sheet.getCell(columna, fila).getContents();
                    							a++;
                                				System.out.println("a="+a);
                    						}else{
                    							
                    							if(columna<12){
                    								datos_num[fila][columna] = Float.parseFloat(sheet.getCell(columna, fila).getContents());
                    								a++;
                                    				System.out.println("a="+a);
                    							}else{
                    								
                    								if(columna<14){
                            							datos[fila][columna] = sheet.getCell(columna, fila).getContents();
                    									a++;
                                        				System.out.println("a="+a);
                    								}else{
                    									
                    									if(columna<18){
                    										
                    										datos_num[fila][columna] = Float.parseFloat(sheet.getCell(columna, fila).getContents());
                    										a++;
                    	                    				System.out.println("a="+a);
                    									}
                    								}
                    								
                    									
                    							}
                    						}
                    					}
                    				}
                    				
                    			}
                    		}
                    		
                    	}
                    	
                    	
                    }
					

                    //System.out.println(sheet.getRows());
                    for(int i=0;i<datos.length;i++){
                    	for(int j=0;j<18;j++){
                    		if(datos[i][j]==null){
                        		System.out.print(datos_num[i][j]+" ");
                    		}else{
                        		System.out.print(datos[i][j]+" ");
                    		}
                    	}
                    	System.out.println("");
                    }
                    
                    int rspta=JOptionPane.showConfirmDialog(null, "se han leido los datos. ¿Desea guardarlos en la Base de Datos?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    
                    if(rspta == JOptionPane.YES_OPTION){
	                    
                    	for(int filas=1;filas<sheet.getRows();filas++){
	                    	//analisis de cada fila del archivo de entrada
	                    	if(!(datos[filas][0].equals(datos[filas-1][0]))){
	                    		//nuevo campo dentro del archivo
	                    		//System.out.println("nuevo campo"+"\n"+idEmpresa+"\n"+datos[filas][0]+"\n"+datos_num[filas][1]);
	                    		id_campo = insertarCampo(idEmpresa, datos[filas][0], datos_num[filas][1]);
	                    		//System.out.println(id_campo);
	                    		                   		
	                    	}
	                    	if(!(datos[filas][2].equals(datos[filas-1][2]))){
	                			
	                			id_sistema = insertarSistema(id_campo, datos[filas][2], datos[filas][3]);
	                    		System.out.println(id_sistema);
	                    		
	                			
	                		}
	                    	if(!(datos[filas][4].equals(datos[filas-1][4]))){
	                    		
	                    		id_cuartel = insertarCuartel(datos[filas][4],(datos_num[filas][5]),datos[filas][6],datos[filas][7],(datos_num[filas][8]),(datos_num[filas][9]),(datos_num[filas][10]),(datos_num[filas][11]));;
	                    		System.out.println(id_cuartel);
	                    	}
	                    	if(!(datos[filas][12].equals(datos[filas-1][12]) && datos[filas][13].equals(datos[filas-1][13])) || (!(datos[filas][4].equals(datos[filas-1][4])))){
	                    		
	                    		float precipitacion = (datos_num[filas][11]*datos_num[filas][10]*datos_num[filas][9])/10000;
	                    		float horas_teo = (datos_num[filas][14]*datos_num[filas][15])/precipitacion;
	                    		float consumo_cuart = 10*precipitacion*datos_num[filas][8]*datos_num[filas][17];
	                    		float consumo_teo = 10*precipitacion*datos_num[filas][8]*horas_teo;
	                    		
	                    		insertarRiego(id_sistema, id_cuartel, datos[filas][12], datos[filas][13], datos_num[filas][14], datos_num[filas][15], precipitacion, datos_num[filas][16], datos_num[filas][17], horas_teo, consumo_cuart, consumo_teo);
	                    		
	                    	}
	                    	
	                    }
	                    
                    	Campo_frame n_ventana = new Campo_frame(Nom_Empresa,idEmpresa);
    					n_ventana.setVisible(true);
    				 	dispose();
	                    
	                    /*
	                    try {
							Desktop.getDesktop().open(new File(pathArchivo));
						} catch (IOException e) {
							e.printStackTrace();
						}
	                     */
	                }else{
						JOptionPane.showMessageDialog(null, "No se ha modificado la información");
	                }
	                 
					
				}	
			}
		});
		btnCargarArchivo.setBounds(249, 231, 157, 23);
		contentPane.add(btnCargarArchivo);
	}
	
	public int insertarCampo(int id_Empresa,String nombreCampo,float pot_inst){
		
		int retorno = 0;
		String sql_1 = "INSERT INTO Campo (ID_Empresa,Nombre,Potencia_Instalada) VALUES ("+id_Empresa+",'"+nombreCampo+"',"+pot_inst+")";
		String sql_2 = "SELECT top 1 ID_Campo FROM Campo ORDER BY ID_Campo DESC;";
		cBD.conectar();
		ResultSet rst;
		try{
			cBD.getStat().executeUpdate(sql_1);
			rst = cBD.getStat().executeQuery(sql_2);
			rst.next();
			retorno = rst.getInt(1);
			System.out.println("success function n°1!");
		}catch(SQLException e){
			e.printStackTrace();
			cBD.desconectar();
		}
		cBD.desconectar();
		return retorno;
	}
	
	
	public int insertarSistema(int idCampo,String nom_sist,String tipo_con){
		
		int retorno = 0;
		String sql_1 = "INSERT into Sistema_de_Riego (ID_Campo,Nombre_Sist,Tipo_Conexion) VALUES ("+idCampo+",'"+nom_sist+"','"+tipo_con+"');";
		String sql_2 = "SELECT top 1 ID_Sistema FROM Sistema_de_Riego ORDER BY ID_Sistema DESC";
		cBD.conectar();
		ResultSet rst;
		
		try{
			cBD.getStat().executeUpdate(sql_1);
			rst = cBD.getStat().executeQuery(sql_2);
			rst.next();
			retorno = rst.getInt(1);
			System.out.println("success function n°2!");
		}catch(SQLException e){
			e.printStackTrace();
			cBD.desconectar();
		}
		cBD.desconectar();
		return retorno ;
	}
	
	public int insertarCuartel(String nom_cuartel, float ef_bomba, String variedad, String categoria, float tamanio, float plantas_h, float goteros_p, float caudal_gotero){
		
		int retorno = 0;
		
		String sql_1 = "INSERT INTO Cuartel (Nombre,Variedad,Categoría,Tamaño,Plantas_Há,Goteros_Planta,Caudal_Gotero,Punto_Eficiencia) VALUES ('"+nom_cuartel+"','"+variedad+"','"+categoria+"',"+tamanio+","+plantas_h+","+goteros_p+","+caudal_gotero+","+ef_bomba+");";
		String sql_2 = "SELECT top 1 ID_Cuartel from Cuartel ORDER BY ID_Cuartel desc;";
		
		cBD.conectar();
		ResultSet rst;
		
		try{
			cBD.getStat().executeUpdate(sql_1);
			rst = cBD.getStat().executeQuery(sql_2);
			rst.next();
			retorno = rst.getInt(1);
			System.out.println("success function n°3!");
		}catch(SQLException e){
			e.printStackTrace();
			cBD.desconectar();
		}
		cBD.desconectar();
		return retorno;
	}
	
	public void insertarRiego(int idSistema,int idCuartel,String mes,String anio,float ETo,float coef_cultivo,float precipitacion,float ef_sist,float hrs_efectivas,float hrs_teo,float consumo_real,float consumo_teo){
		
		String sql_1 = "INSERT INTO Sistema_Cuartel (ID_Sistema,ID_Cuartel,Mes_Año,Año,Evapotranspiración,Coef_Cultivo,Precipitación_Sist,Eficiencia_Sist_Riego,Horas_Riego_Efectivas,Horas_Riego_Teóricas,Consumo_Cuartel,Consumo_Teo_Cuartel) VALUES ("+idSistema+","+idCuartel+",'"+mes+"','"+anio+"',"+ETo+","+coef_cultivo+","+precipitacion+","+ef_sist+","+hrs_efectivas+","+hrs_teo+","+consumo_real+","+consumo_teo+");";
		cBD.conectar();
		try{
			cBD.getStat().executeUpdate(sql_1);
			System.out.println("success function n°4!");
		}catch(SQLException e){
			e.printStackTrace();
			cBD.desconectar();
		}
		cBD.desconectar();
		return;
	}
	

}
