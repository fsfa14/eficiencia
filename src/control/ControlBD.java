package control;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.sql.*;

@SuppressWarnings("unused")
public class ControlBD {

	private Connection conn;
	private Statement stat;
	private ResultSet rst;

	
	public void conectar(){
		
		String url= "jdbc:ODBC:MS Access Database";   //direccion del driver de access
		String login= "";
		String password= "";
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");   //se carga driver de access
			try{
				conn=DriverManager.getConnection(url,login,password);  //se establece conexion con la BD
				stat = conn.createStatement();
				System.out.println("Conexion establecida");
			}catch(SQLException e){
				System.out.println("Error en la conexión");
				e.printStackTrace();
			}
		}catch(ClassNotFoundException e){
			System.out.println("No se encontro la clase del Driver");
		}		
		
	}
	
	
	public String[] getEmpresas(){
		int numFilas;
		String[] datos;
		String sql="select count(*) as numFilas from Empresa;";
		try {
			rst=stat.executeQuery(sql);
			rst.next();
			numFilas=rst.getInt("numFilas");
			datos=new String[numFilas+1];
			String sql2="Select Nombre from Empresa Order BY Nombre";
			rst=stat.executeQuery(sql2);
			datos[0]="Nueva Empresa...";
			int i=1;
			while(rst.next()){
				datos[i]=rst.getString("Nombre");
				i++;
			}
			return datos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String[] getCampos(int id_empresa){
		int numFilas;
		String[] datos;
		String sql="select count(*) as numFilas from Campo;";
		try {
			rst=stat.executeQuery(sql);
			rst.next();
			numFilas=rst.getInt("numFilas");
			datos=new String[numFilas+1];
			String sql2="Select Nombre from Campo where ID_Empresa="+id_empresa+" Order BY Nombre";
			rst=stat.executeQuery(sql2);
			datos[0]="Nuevo Campo...";
			int i=1;
			while(rst.next()){
				datos[i]=rst.getString("Nombre");
				i++;
			}
			return datos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public String[] getSist_Riego(int idCampo){
		
		int numFilas;
		String[] datos;
		String sql="select count(*) as numFilas from Sistema_de_Riego where ID_Campo="+idCampo+";";
		try {
			rst=stat.executeQuery(sql);
			rst.next();
			numFilas=rst.getInt("numFilas");
			datos=new String[numFilas+1];
			String sql2="Select Nombre_Sist from Sistema_de_Riego where ID_Campo="+idCampo+";";
			rst=stat.executeQuery(sql2);
			datos[0]="Nuevo Sistema...";
			int i=1;
			while(rst.next()){
				datos[i]=rst.getString("Nombre_Sist");
				i++;
			}
			return datos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	
	public Nom_e_ID getBombas_cbbx(String Nom_Sist, int idCampo){		
		
		int numFilas;
		String[] nombres;
		int [] ids;
		String sql="select count(*) as num_filas from bomba AS pump INNER JOIN (sistema_bomba AS sisbom INNER JOIN sistema_de_riego AS riego ON sisbom.id_sistema = riego.id_sistema) ON pump.ID_Bomba = sisbom.ID_Bomba where Nombre_Sist ='"+Nom_Sist+"' and riego.ID_Campo="+idCampo+";";
		try {
			rst=stat.executeQuery(sql);
			rst.next();
			numFilas=rst.getInt(1);
			nombres=new String[numFilas+1];
			ids=new int[numFilas+1];
			String sql2="SELECT Modelo_Rodete_RPM,pump.ID_Bomba FROM bomba AS pump INNER JOIN (sistema_bomba AS sisbom INNER JOIN sistema_de_riego AS riego ON sisbom.id_sistema = riego.id_sistema) ON pump.ID_Bomba = sisbom.ID_Bomba where Nombre_Sist ='"+Nom_Sist+"' and riego.ID_Campo="+idCampo+";";
			rst=stat.executeQuery(sql2);
			nombres[0]="Nueva Bomba...";
			ids[0]=(-1);
			int i=1;
			while(rst.next()){
				nombres[i]=rst.getString("Modelo_Rodete_RPM");
				ids[i]=rst.getInt("ID_Bomba");
				i++;
			}
			Nom_e_ID datos = new Nom_e_ID(ids, nombres);
			return datos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}	
	
	public Nom_e_ID getCuarteles_cbbx(String Nom_Sist, int idCampo){
		
		int numFilas;
		String[] nombres;
		int [] ids;
		String sql="select count(*) as num_filas FROM Cuartel AS cuart INNER JOIN (Sistema_Cuartel AS siscuar INNER JOIN sistema_de_riego AS riego ON siscuar.ID_Sistema = riego.id_sistema) ON cuart.ID_Cuartel = siscuar.ID_Cuartel WHERE riego.Nombre_Sist='"+Nom_Sist+"' AND riego.ID_Campo="+idCampo+";";
		try {
			rst=stat.executeQuery(sql);
			rst.next();
			numFilas=rst.getInt(1);
			nombres=new String[numFilas+1];
			ids=new int[numFilas+1];
			String sql2="SELECT Nombre, cuart.ID_Cuartel FROM Cuartel AS cuart INNER JOIN (Sistema_Cuartel AS siscuar INNER JOIN sistema_de_riego AS riego ON siscuar.ID_Sistema = riego.id_sistema) ON cuart.ID_Cuartel = siscuar.ID_Cuartel where riego.Nombre_Sist ='"+Nom_Sist+"' and riego.ID_Campo="+idCampo+";";
			rst=stat.executeQuery(sql2);
			nombres[0]="Nuevo Cuartel...";
			ids[0]=(2100000000);
			int i=1;
			while(rst.next()){
				nombres[i]=rst.getString(1);
				ids[i]=rst.getInt(2);
				i++;
			}
			Nom_e_ID datos = new Nom_e_ID(ids, nombres);
			Nom_e_ID datos_r = datos.sinRepetir();
			
			return datos_r;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
		

	}
	
	public String [][] getRiegosSemanales(int idCuartel){
		
		int num_filas;
		String datos[][];
		String sql = "SELECT count (*) as num_filas from Riego_Semanal where ID_Cuartel="+idCuartel+";";
		
		try{
			
			rst=stat.executeQuery(sql);
			rst.next();
			num_filas=rst.getInt("num_filas");
			datos=new String[num_filas][3];
			String sql2 = "SELECT Semana, Cant_Horas, Cant_Agua FROM Riego_Semanal where ID_Cuartel="+idCuartel+" ORDER BY Semana;";
			rst=stat.executeQuery(sql2);
			int i=0;
			while(rst.next()){
				
				datos[i][0] = Integer.toString(rst.getInt("Semana"));
				datos[i][1] = Float.toString(rst.getFloat("Cant_Horas"));
				datos[i][2] = Float.toString(rst.getFloat("Cant_Agua"));
				i++;
				
			}
			
			return datos;
			
		}catch(SQLException e){
			e.printStackTrace();
			return null;

		}
		
	}
	
	
	
	
	
	
	public void desconectar(){
		try {
			conn.close();
			System.out.println("Conexion cerrada");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
	public Connection getConn() {
		return conn;
	}


	public void setConn(Connection conn) {
		this.conn = conn;
	}


	public Statement getStat() {
		return stat;
	}


	public void setStat(Statement stat) {
		this.stat = stat;
	}


	public ResultSet getRst() {
		return rst;
	}


	public void setRst(ResultSet rst) {
		this.rst = rst;
	}

	
	
	
}
