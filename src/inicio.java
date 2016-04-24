import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.JComboBox;

import control.ControlBD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Font;


public class inicio extends JFrame {

	private JPanel contentPane;
	private ControlBD cBD=new ControlBD();
	private JTextField txtf_RUT;
	private JTextField txtf_Rp_Leg;
	private JTextField txtf_Giro;
	private JTextField txtf_Persona_Cotacto;
	private JTextField txtf_Nombre;
	private JTextField txtf_Fono_Contacto;
	private JTextField txtf_MailContacto;
	private JTextField txtf_Comuna;
	private JTextField txtf_Región;
	private JTextField txtf_Pais;
	private JTextField txtf_Exp_Imp;
	private JTextField txtf_sect_ecn;
	private JComboBox n_empresas;
	private JLabel lbl_Nombre;
	private JLabel lblFonoContacto;
	private JLabel lblMailContacto;
	private JLabel lblComuna;
	private JLabel lblRegin;
	private JLabel lblPais;
	private JLabel lblExportadorimportador;
	private JLabel lblSectorEconmico;
	private JButton btnEliminar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicio frame = new inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public inicio() {
		setTitle("Balance de Recursos");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FelipeSebasti\u00E1n\\Desktop\\Mas Recursos Naturales\\Proyecto_Ef_Energetica\\src\\logo_2.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 521, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		
		cBD.conectar();
		String[] ciudades=cBD.getEmpresas();	
		n_empresas = new JComboBox(ciudades);
		
		n_empresas.setBounds(10, 29, 159, 20);
		contentPane.add(n_empresas);
		
		JLabel lblRut = new JLabel("RUT");
		lblRut.setFont(lblRut.getFont().deriveFont(lblRut.getFont().getStyle() & ~Font.BOLD));
		lblRut.setBounds(10, 99, 39, 14);
		contentPane.add(lblRut);
		
		JLabel lblRepLeg = new JLabel("Representante Legal");
		lblRepLeg.setBounds(10, 138, 138, 14);
		contentPane.add(lblRepLeg);
		
		JLabel lblGiro = new JLabel("Giro");
		lblGiro.setFont(lblGiro.getFont().deriveFont(lblGiro.getFont().getStyle() & ~Font.BOLD));
		lblGiro.setBounds(170, 60, 50, 14);
		contentPane.add(lblGiro);
		
		JLabel lblPersona_Cotacto = new JLabel("Persona de Cotacto");
		lblPersona_Cotacto.setBounds(10, 181, 112, 14);
		contentPane.add(lblPersona_Cotacto);
		
		txtf_RUT = new JTextField();
		txtf_RUT.setEditable(false);
		txtf_RUT.setBounds(10, 113, 138, 20);
		contentPane.add(txtf_RUT);
		txtf_RUT.setColumns(10);
		
		txtf_Rp_Leg = new JTextField();
		txtf_Rp_Leg.setEditable(false);
		txtf_Rp_Leg.setColumns(10);
		txtf_Rp_Leg.setBounds(10, 155, 138, 20);
		contentPane.add(txtf_Rp_Leg);
		
		txtf_Giro = new JTextField();
		txtf_Giro.setEditable(false);
		txtf_Giro.setColumns(10);
		txtf_Giro.setBounds(170, 73, 112, 20);
		contentPane.add(txtf_Giro);
		
		txtf_Persona_Cotacto = new JTextField();
		txtf_Persona_Cotacto.setEditable(false);
		txtf_Persona_Cotacto.setColumns(10);
		txtf_Persona_Cotacto.setBounds(10, 197, 138, 20);
		contentPane.add(txtf_Persona_Cotacto);
		
		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtf_RUT.setEditable(false);
				txtf_Rp_Leg.setEditable(false);
				txtf_Giro.setEditable(false);
				txtf_Persona_Cotacto.setEditable(false);
				txtf_Nombre.setEditable(false);
				txtf_Fono_Contacto.setEditable(false);
				txtf_MailContacto.setEditable(false);
				txtf_Comuna.setEditable(false);
				txtf_Región.setEditable(false);
				txtf_Pais.setEditable(false);
				txtf_Exp_Imp.setEditable(false);
				txtf_sect_ecn.setEditable(false);
				
				ResultSet rst;
				String nom_empresa = (String) n_empresas.getSelectedItem();
				// se hace la consulta para cargar los campos en la ventana inicio
				String sql ="SELECT RUT,Representante_Legal,Giro,Persona_Cotacto,Fono_Contacto,Mail_Contacto,Comuna,Región,País,Sector_Económico,Exportador_Importador from Empresa where Nombre='"+nom_empresa+"';";
				cBD.conectar(); /*se conecta a la base de datos*/
				try{
					rst=cBD.getStat().executeQuery(sql);
					while(rst.next()){
						//se llena la ventana con los datos de la consulta
						txtf_Nombre.setText(nom_empresa);
						txtf_RUT.setText(rst.getString("RUT"));
						txtf_Rp_Leg.setText(rst.getString("Representante_Legal"));
						txtf_Giro.setText(rst.getString("Giro"));
						txtf_Persona_Cotacto.setText(rst.getString("Persona_Cotacto"));
						txtf_Fono_Contacto.setText(rst.getString("Fono_Contacto"));
						txtf_MailContacto.setText(rst.getString("Mail_Contacto"));
						txtf_Comuna.setText(rst.getString("Comuna"));
						txtf_Región.setText(rst.getString("Región"));
						txtf_Pais.setText(rst.getString("País"));
						txtf_sect_ecn.setText(rst.getString("Sector_Económico"));
						txtf_Exp_Imp.setText(rst.getString("Exportador_Importador"));
											
					}
					cBD.desconectar();
				}catch(SQLException e){
					e.printStackTrace();
					cBD.desconectar();
				}
			}
		});
		btnVer.setBounds(179, 28, 68, 23);
		contentPane.add(btnVer);
		
		txtf_Nombre = new JTextField();
		txtf_Nombre.setEditable(false);
		txtf_Nombre.setColumns(10);
		txtf_Nombre.setBounds(10, 73, 138, 20);
		contentPane.add(txtf_Nombre);
		
		lbl_Nombre = new JLabel("Nombre");
		lbl_Nombre.setFont(lbl_Nombre.getFont().deriveFont(lbl_Nombre.getFont().getStyle() & ~Font.BOLD));
		lbl_Nombre.setBounds(10, 59, 50, 14);
		contentPane.add(lbl_Nombre);
		
		lblFonoContacto = new JLabel("Fono Contacto");
		lblFonoContacto.setBounds(10, 220, 94, 14);
		contentPane.add(lblFonoContacto);
		
		txtf_Fono_Contacto = new JTextField();
		txtf_Fono_Contacto.setEditable(false);
		txtf_Fono_Contacto.setColumns(10);
		txtf_Fono_Contacto.setBounds(10, 234, 138, 20);
		contentPane.add(txtf_Fono_Contacto);
		
		lblMailContacto = new JLabel("Mail Contacto");
		lblMailContacto.setBounds(10, 260, 77, 14);
		contentPane.add(lblMailContacto);
		
		txtf_MailContacto = new JTextField();
		txtf_MailContacto.setEditable(false);
		txtf_MailContacto.setColumns(10);
		txtf_MailContacto.setBounds(10, 275, 138, 20);
		contentPane.add(txtf_MailContacto);
		
		lblComuna = new JLabel("Comuna");
		lblComuna.setFont(lblComuna.getFont().deriveFont(lblComuna.getFont().getStyle() & ~Font.BOLD));
		lblComuna.setBounds(170, 99, 50, 14);
		contentPane.add(lblComuna);
		
		txtf_Comuna = new JTextField();
		txtf_Comuna.setEditable(false);
		txtf_Comuna.setColumns(10);
		txtf_Comuna.setBounds(170, 113, 112, 20);
		contentPane.add(txtf_Comuna);
		
		txtf_Región = new JTextField();
		txtf_Región.setEditable(false);
		txtf_Región.setColumns(10);
		txtf_Región.setBounds(170, 155, 112, 20);
		contentPane.add(txtf_Región);
		
		lblRegin = new JLabel("Regi\u00F3n");
		lblRegin.setBounds(170, 138, 50, 14);
		contentPane.add(lblRegin);
		
		lblPais = new JLabel("Pa\u00EDs");
		lblPais.setBounds(168, 181, 50, 14);
		contentPane.add(lblPais);
		
		txtf_Pais = new JTextField();
		txtf_Pais.setEditable(false);
		txtf_Pais.setColumns(10);
		txtf_Pais.setBounds(170, 197, 112, 20);
		contentPane.add(txtf_Pais);
		
		txtf_Exp_Imp = new JTextField();
		txtf_Exp_Imp.setEditable(false);
		txtf_Exp_Imp.setColumns(10);
		txtf_Exp_Imp.setBounds(170, 275, 112, 20);
		contentPane.add(txtf_Exp_Imp);
		
		lblExportadorimportador = new JLabel("Exportador/Importador");
		lblExportadorimportador.setBounds(170, 260, 138, 14);
		contentPane.add(lblExportadorimportador);
		
		txtf_sect_ecn = new JTextField();
		txtf_sect_ecn.setEditable(false);
		txtf_sect_ecn.setColumns(10);
		txtf_sect_ecn.setBounds(170, 234, 112, 20);
		contentPane.add(txtf_sect_ecn);
		
		lblSectorEconmico = new JLabel("Sector Econ\u00F3mico");
		lblSectorEconmico.setBounds(170, 220, 112, 14);
		contentPane.add(lblSectorEconmico);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtf_RUT.setEditable(true);
				txtf_Rp_Leg.setEditable(true);
				txtf_Giro.setEditable(true);
				txtf_Persona_Cotacto.setEditable(true);
				txtf_Nombre.setEditable(true);
				txtf_Fono_Contacto.setEditable(true);
				txtf_MailContacto.setEditable(true);
				txtf_Comuna.setEditable(true);
				txtf_Región.setEditable(true);
				txtf_Pais.setEditable(true);
				txtf_Exp_Imp.setEditable(true);
				txtf_sect_ecn.setEditable(true);

			}
		});
		btnEditar.setBounds(257, 28, 77, 23);
		contentPane.add(btnEditar);
		
		final JCheckBox chkbxNE = new JCheckBox("Nueva Empresa");
		chkbxNE.setBounds(10, 312, 123, 23);
		contentPane.add(chkbxNE);
		
		JButton btnTerminarEdicion = new JButton("Terminar Edici\u00F3n");
		btnTerminarEdicion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//aca va la seleccion
				
				txtf_RUT.setEditable(false);
				txtf_Rp_Leg.setEditable(false);
				txtf_Giro.setEditable(false);
				txtf_Persona_Cotacto.setEditable(false);
				txtf_Nombre.setEditable(false);
				txtf_Fono_Contacto.setEditable(false);
				txtf_MailContacto.setEditable(false);
				txtf_Comuna.setEditable(false);
				txtf_Región.setEditable(false);
				txtf_Pais.setEditable(false);
				txtf_Exp_Imp.setEditable(false);
				txtf_sect_ecn.setEditable(false);
				
				int rspta;
				boolean chkbox=chkbxNE.isSelected();
				if(chkbox==true){
					
					rspta=JOptionPane.showConfirmDialog(null, "Va a ingresar una nueva Empresa. ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(rspta == JOptionPane.YES_OPTION){
						String nom = txtf_Nombre.getText();
						String rut = txtf_RUT.getText();
						String rp_l = txtf_Rp_Leg.getText();
						String giro = txtf_Giro.getText();
						String contacto = txtf_Persona_Cotacto.getText();
						String fono = txtf_Fono_Contacto.getText();
						String mail = txtf_MailContacto.getText();
						String comuna = txtf_Comuna.getText();
						String region = txtf_Región.getText();
						String pais = txtf_Pais.getText();
						String export = txtf_Exp_Imp.getText();
						String sector = txtf_sect_ecn.getText();
						String sql_1 = "INSERT INTO Empresa (Nombre,RUT,Representante_Legal,Giro,Persona_Cotacto,Fono_Contacto,Mail_Contacto,Comuna,Región,País,Sector_Económico,Exportador_Importador) VALUES ('"+nom+"','"+rut+"','"+rp_l+"','"+giro+"','"+contacto+"','"+fono+"','"+mail+"','"+comuna+"','"+region+"','"+pais+"','"+sector+"','"+export+"');";              
						cBD.conectar();
						try{
							cBD.getStat().executeUpdate(sql_1);
							cBD.desconectar();
							
							inicio n_ventana = new inicio();
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
					String nom_empresa = (String) n_empresas.getSelectedItem();
					rspta=JOptionPane.showConfirmDialog(null, "Va a modificar los datos de la Empresa "+nom_empresa+". ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(rspta == JOptionPane.YES_OPTION){
						String nom= txtf_Nombre.getText();
						String rut= txtf_RUT.getText();
						String rp_l= txtf_Rp_Leg.getText();
						String giro= txtf_Giro.getText();
						String contacto= txtf_Persona_Cotacto.getText();
						String fono= txtf_Fono_Contacto.getText();
						String mail= txtf_MailContacto.getText();
						String comuna= txtf_Comuna.getText();
						String region= txtf_Región.getText();
						String pais= txtf_Pais.getText();
						String export= txtf_Exp_Imp.getText();
						String sector= txtf_sect_ecn.getText();
						String sql_3="UPDATE Empresa SET Nombre='"+nom+"',RUT='"+rut+"',Representante_Legal='"+rp_l+"',Giro='"+giro+"',Persona_Cotacto='"+contacto+"',Fono_Contacto='"+fono+"',Mail_Contacto='"+mail+"',Comuna='"+comuna+"',Región='"+region+"',País='"+pais+"',Sector_Económico='"+sector+"',Exportador_Importador='"+export+"' where ID_Empresa=(select ID_Empresa from Empresa where Nombre='"+nom_empresa+"');";
						cBD.conectar();
						try{
							cBD.getStat().executeUpdate(sql_3);
							cBD.desconectar();
							
							inicio n_ventana = new inicio();
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
		btnTerminarEdicion.setBounds(149, 312, 138, 23);
		contentPane.add(btnTerminarEdicion);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom_empresa = (String) n_empresas.getSelectedItem();
				String rut = txtf_RUT.getText();
				if(nom_empresa.equals("Nueva Empresa...")){
					JOptionPane.showMessageDialog(null, "Debe seleccionar una Empresa...");

				}else{
					int rpta=JOptionPane.showConfirmDialog(null, "Va a eliminar los datos de la Empresa "+nom_empresa+", RUT "+rut+". ¿Continuar?","Aviso",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(rpta == JOptionPane.YES_OPTION){
						
						cBD.conectar();
						String sql_4="DELETE from Empresa where Nombre='"+nom_empresa+"' AND RUT='"+rut+"';";
						try{
							cBD.getStat().executeUpdate(sql_4);
							
							txtf_RUT.setText("");
							txtf_Rp_Leg.setText("");
							txtf_Giro.setText("");
							txtf_Persona_Cotacto.setText("");
							txtf_Nombre.setText("");
							txtf_Fono_Contacto.setText("");
							txtf_MailContacto.setText("");
							txtf_Comuna.setText("");
							txtf_Región.setText("");
							txtf_Pais.setText("");
							txtf_Exp_Imp.setText("");
							txtf_sect_ecn.setText("");
	
							cBD.desconectar();
							JOptionPane.showMessageDialog(null, "Se ha eliminado la información");
	
							
							inicio n_ventana = new inicio();
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
		btnEliminar.setBounds(344, 28, 87, 23);
		contentPane.add(btnEliminar);
		
		JButton btnCamposAsociados = new JButton("Campos\nAsociados");
		btnCamposAsociados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String nom_empresa = (String) n_empresas.getSelectedItem();
				if(nom_empresa.equals("Nueva Empresa...")){
					
					JOptionPane.showMessageDialog(null, "Debe seleccionar una Empresa...");
				
				}else{
					
					cBD.conectar();
					String sql="SELECT ID_Empresa from Empresa where Nombre='"+nom_empresa+"';";
					try{
						ResultSet rst=cBD.getStat().executeQuery(sql);
						rst.next();
						int idEmpresa=rst.getInt(1);
						cBD.desconectar();
						Campo_frame nuevo= new Campo_frame(nom_empresa,idEmpresa);
						nuevo.setVisible(true);
						dispose();
					}catch(SQLException e2){
						e2.printStackTrace();
						cBD.desconectar();
					}
					
				}

				
			}
		});
		btnCamposAsociados.setBounds(308, 95, 159, 29);
		contentPane.add(btnCamposAsociados);
		
		cBD.desconectar();

	}
		

	public ControlBD getcBD() {
		return cBD;
	}

	public void setcBD(ControlBD cBD) {
		this.cBD = cBD;
	}
}
