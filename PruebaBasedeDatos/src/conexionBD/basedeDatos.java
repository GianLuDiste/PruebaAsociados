package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class basedeDatos {

	private Connection con = null;

	public basedeDatos() {
	}

	public void conectar() {

		String url = "jdbc:mysql://127.0.0.1:3306/";
		String bd = "Prueba_Asociados";
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
			ResultSet rs = ((java.sql.Statement) s).executeQuery("Select * from asociados");

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
			
			s.execute("DROP TABLE asociados");
		} catch (SQLException e) {
			System.out.println("No se ejecuto bien el query");
		}

		return listado;
	}

	public void setAsociados(ArrayList<Asociado> listado) {
		
		
		try {
			java.sql.Statement s = this.con.createStatement();
			
			s.execute("CREATE TABLE asociados("
					+ " dni varchar(8) primary key not null, "
					+ " nombre varchar(30) not null, "
					+ " apellido varchar(30) not null, "
					+ " calle varchar(30) not null, "
					+ " numero int not null, "
					+ " telefono varchar(15) not null, "
					+ " ciudad varchar(30) not null, "
					+ " solicitudes int not null)");
			
			Iterator<Asociado> it = listado.iterator();
			while(it.hasNext())
			{
				Asociado a = (Asociado) it.next();
				s.execute("INSERT INTO asociados (dni, nombre, apellido, calle, numero, telefono, ciudad, solicitudes)"
						+ " VALUES('"
						+ a.dni + "', '" + a.nombre + "', '" + a.apellido + "', '" + a.calle + "', " + a.numero + ", '" +
						a.telefono + "', '" + a.ciudad + "', " + a.solicitudes + ");");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
