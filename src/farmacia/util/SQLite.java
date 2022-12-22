package farmacia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SQLite {
	
	private Connection conexion;
	public ResultSet Resultado;
	
	private String nombre = "Farmacia.db";
	
	public void Abrir(){
		try{
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + nombre);
			ActivarRelaciones();
			
			Crear("Clientes",
				new String[]{
				"Clave INTEGER PRIMARY KEY AUTOINCREMENT",
				"Nombre TEXT",
				"Economia TEXT",
				"Enfermedades TEXT",
				"Alergias TEXT",
				"Edad REAL",
				"Sintomas TEXT",
				"Embarazo TEXT"});

			Crear("Productos",
				new String[]{
				"Clave INTEGER PRIMARY KEY AUTOINCREMENT",
				"Nombre TEXT",
				"Precio REAL",
				"Caducidad TEXT",
				"Cantidad REAL",
				"Funcion TEXT",
				"Dosis TEXT",
				"Formula TEXT",
				"Precauciones TEXT",
				"Reaccion TEXT"});

			Crear("Trabajadores",
				new String[]{
				"Clave INTEGER PRIMARY KEY AUTOINCREMENT",
				"Nombre TEXT",
				"Genero TEXT",
				"Edad REAL",
				"Estudios TEXT",
				"Capacidad TEXT",
				"Domicilio TEXT",
				"Telefono REAL",
				"Responsable TEXT",
				"Honesto TEXT",
				"Comportamiento TEXT"});

			Crear("Ventas",
				new String[]{
				"Clave INTEGER PRIMARY KEY AUTOINCREMENT",
				"Trabajador INTEGER",
				"Cliente INTEGER",
				"Fecha TEXT",
				"Total REAL"});

			Crear("Productos_Vendidos",
				new String[]{
				"Clave INTEGER PRIMARY KEY AUTOINCREMENT",
				"Ventas INTEGER",
				"Productos INTEGER",
				"Cantidad INTEGER",
				"Subtotal REAL"});

		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void ActivarRelaciones() {
		try{
			conexion.createStatement().executeUpdate("PRAGMA foreign_keys = ON;");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void Crear(String Tabla, String[] Campos){
		try {
			String sql = "";
			sql += "CREATE TABLE " + Tabla + " (";
			for(String campo : Campos)
				sql += (campo + ", ");
			sql = sql.substring(0, sql.length() - 2) + ");";
			conexion.createStatement().executeUpdate(sql);
		} catch (Exception e) {
			if(!e.getMessage().contains("exists")){
				System.out.println(Tabla + " : " + e.getMessage());
			}
		}
	}

	public void Consulta(String[] Campos, String Tabla, String Condicion, String Orden) {
		PreparedStatement SQL;
		try {
			String sql = "";
			sql += "SELECT ";
			for(String campo : Campos)
				sql += (campo + ", ");
			sql = sql.substring(0, sql.length() - 2) + " ";
			sql += "FROM " + Tabla + " ";
			if(!Condicion.equals(""))
				sql += "WHERE " + Condicion + " ";
			if(!Orden.equals(""))
				sql += "ORDER BY " + Orden + " ";
			sql = sql.substring(0, sql.length() - 1) + ";";
			SQL = conexion.prepareStatement(sql);
			Resultado = SQL.executeQuery();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void Insertar(String Tabla, String[] Campos, String[] Valores) {
		PreparedStatement SQL;
		try {
			String sql = "";
			sql += "INSERT INTO "+ Tabla + " (";
			for(String campo : Campos)
				sql += (campo + ", ");
			sql = sql.substring(0, sql.length() - 2) + ") VALUES (";
			for(String valor : Valores)
				if(valor==null)
					sql += ("NULL, ");
				else
					sql += ("\"" + valor + "\", ");
			sql = sql.substring(0, sql.length() - 2) + ");";
			SQL = conexion.prepareStatement(sql);
			SQL.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void Eliminar(String Tabla, String Condicion) {
		PreparedStatement SQL;
		try{
			String sql = "";
			sql += "DELETE FROM "+ Tabla + " ";
			sql += "WHERE " + Condicion + ";";
			SQL = conexion.prepareStatement(sql);
			SQL.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void Modificar(String Tabla, String[] Campos, String[] Valores, String Condicion) {
		PreparedStatement SQL;
		try{
			String sql = "";
			sql += "UPDATE "+ Tabla + " SET ";
			for(int i=0; i<Campos.length; i++)
				if(Valores[i]==null)
					sql += (Campos[i] + " = NULL, ");
				else
					sql += (Campos[i] + "='" + Valores[i] + "', ");
			sql = sql.substring(0, sql.length() - 2) + " WHERE ";
			sql += (Condicion + ";");
			SQL = conexion.prepareStatement(sql);
			SQL.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void Cerrar() {
		try {
			conexion.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}