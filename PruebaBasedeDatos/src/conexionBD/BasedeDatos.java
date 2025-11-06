package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class BasedeDatos implements IPersistencia {

	private Connection con = null;

	public BasedeDatos() {
	}

	public void conectar() {

		String url = "jdbc:mysql://127.0.0.1:3306/";
		String bd = "prueba_asociados";
		String usuario = "root";
		String password = "root";

		try {
			this.con = DriverManager.getConnection(url + bd, usuario, password);
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos");
			e.printStackTrace();
		}
	}

	public void desconectar() {
		try {
			this.con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Asociado> getAsociados() {

		String nombre, apellido, dni, calle, telefono, ciudad;
		int numero, solicitudes;

		ArrayList<Asociado> listado = new ArrayList<Asociado>();

		try {
			java.sql.Statement s = this.con.createStatement();
			ResultSet rs = ((java.sql.Statement) s).executeQuery("Select * from asociados order by dni");

			while (rs.next()) {
				dni = rs.getString("dni");
				nombre = rs.getString("nombre");
				apellido = rs.getString("apellido");
				calle = rs.getString("calle");
				numero = rs.getInt("numero");
				telefono = rs.getString("telefono");
				ciudad = rs.getString("ciudad");
				solicitudes = rs.getInt("solicitudes");

				Asociado a = new Asociado(nombre, apellido, dni, calle, numero, telefono, ciudad, solicitudes);
				// System.out.println(a);
				listado.add(a);
			}

		} catch (SQLException e) {
			System.out.println("No se ejecuto bien el query");
		}

		return listado;
	}

	public void setAsociados(ArrayList<Asociado> listado) {

		try {
			java.sql.Statement s = this.con.createStatement();

			Iterator<Asociado> it = listado.iterator();
			while (it.hasNext()) {
				Asociado a = (Asociado) it.next();

			
				s.execute(
						"INSERT INTO asociados (dni, nombre, apellido, calle, numero, telefono, ciudad, solicitudes)"
								+ " VALUES('" + a.dni + "', '" + a.nombre + "', '" + a.apellido + "', '" + a.calle
								+ "', " + a.numero + ", '" + a.telefono + "', '" + a.ciudad + "', " + a.solicitudes
								+ ");");
			
				s.execute("delete from asociados where dni = '" + a.dni + "';");
					
				
				s.execute("update asociados set " + "nombre = '" + a.nombre + "', " + "apellido = '" + a.apellido
						+ "', " + "calle = '" + a.calle + "', " + "numero = " + a.numero + ", " + "telefono = '"
						+ a.telefono + "', " + "ciudad = '" + a.ciudad + "', " + "solicitudes = " + a.solicitudes
						+ " where dni = '" + a.dni + "';");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// el asociado debe ser distinto de null
	public void altaBD(Asociado a) throws AsociadoExistenteException {
		try {
			java.sql.Statement s = this.con.createStatement();

			ResultSet rs = ((java.sql.Statement) s)
					.executeQuery("select * from asociados where dni = '" + a.dni + "';");

			if (rs.next()) { // existe el associado en la BD
				throw new AsociadoExistenteException(a);
			} else { // agregar a la BD
				s.execute("INSERT INTO asociados (dni, nombre, apellido, calle, numero, telefono, ciudad, solicitudes)"
						+ " VALUES('" + a.dni + "', '" + a.nombre + "', '" + a.apellido + "', '" + a.calle + "', "
						+ a.numero + ", '" + a.telefono + "', '" + a.ciudad + "', " + a.solicitudes + ");");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// el asociado debe ser distinto de null
	public void bajaBD(Asociado a) throws AsociadoInexistenteException {

		try {
			java.sql.Statement s = this.con.createStatement();
			ResultSet rs = ((java.sql.Statement) s)
					.executeQuery("select * from asociados where dni = '" + a.dni + "';");

			if (rs.next()) { // eliminar de la BD
				s.execute("delete from asociados where dni = '" + a.dni + "';");
			} else { // no existe en la BD
				throw new AsociadoInexistenteException(a);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
