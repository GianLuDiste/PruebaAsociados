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
		String bd = "grupo_9";
		String usuario = "progra_c";
		String password = "progra_c";

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
	
	private void crearTablaValores(java.sql.Statement s) throws SQLException
	{
		s.execute("CREATE TABLE asociados(\r\n"
				+ "	dni varchar(8) primary key not null,\r\n"
				+ " nombre varchar(30) not null,\r\n"
				+ " apellido varchar(30) not null,\r\n"
				+ "	calle varchar(30) not null,\r\n"
				+ "	numero int not null,\r\n"
				+ "	telefono varchar(15) not null,\r\n"
				+ "	ciudad varchar(30) not null);"
				);
		
		s.execute("insert into asociados(dni, nombre, apellido, calle, numero, telefono, ciudad) \r\n"
				+ "values ('47256279', 'Gian Luca', 'Distefano', 'Constitucion', 6150, '2235376209', 'Mar del Plata'),\r\n"
				+ "	('46054123', 'Santiago', 'Yanosky', 'Matheu', 5102, '2236556561', 'Mar del Plata'),\r\n"
				+ "	('45265789', 'Valentino', 'Giordano', 'San Juan', 2315, '2236851429', 'Mar del Plata'), \r\n"
				+ "	('47015236', 'Juan', 'Aldana', 'Ratery', 5013, '2236884563', 'Mar del Plata'), \r\n"
				+ "	('46025175', 'Agustin', 'Proia', 'Juan b Justo', 982, '2236841257', 'Mar del Plata');"
				);
	}

	public void inicializacion()
	{
		try {
			java.sql.Statement s = this.con.createStatement();
			ResultSet rs = ((java.sql.Statement) s).executeQuery("show tables like 'asociados';");
			
			if (rs.next())
			{
				s.execute("drop table asociados;");
			}
			this.crearTablaValores(s);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public ArrayList<Asociado> getAsociados() {

		String nombre, apellido, dni, calle, telefono, ciudad;
		int numero;

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

				Asociado a = new Asociado(nombre, apellido, dni, calle, numero, telefono, ciudad, 0);
				// System.out.println(a);
				listado.add(a);
			}

		} catch (SQLException e) {
			System.out.println("No se ejecuto bien el query");
		}

		return listado;
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
				s.execute("INSERT INTO asociados (dni, nombre, apellido, calle, numero, telefono, ciudad)"
						+ " VALUES('" + a.dni + "', '" + a.nombre + "', '" + a.apellido + "', '" + a.calle + "', "
						+ a.numero + ", '" + a.telefono + "', '" + a.ciudad + "');");
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
