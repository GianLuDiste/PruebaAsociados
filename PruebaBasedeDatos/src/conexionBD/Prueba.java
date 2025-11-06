package conexionBD;

import java.util.ArrayList;
import java.util.Iterator;

public class Prueba {

	public static void main(String[] args) {
		IPersistencia bd = new BasedeDatos();
		
		bd.conectar();
		//cuando se crea la ventana hay que llamar a getAsociados para mostralos en una lista.
		//cada vez que se hace algo, (baja, alta) volver a llamar a getAsociados para actualizar la lista
		ArrayList<Asociado> listado = bd.getAsociados();
		
		Iterator it = listado.iterator();
		while (it.hasNext())
		{
			Asociado a = (Asociado) it.next();
			System.out.println(a);
		}
		
		/*
		try {
			bd.altaBD(new Asociado("Cristina", "Pepi", "16858124", "Calle 100", 156, "223456789", "Mechongue", 0, 'A'));
		} catch (AsociadoExistenteException e) {
			System.out.println(e.getA() + "No existia");
			e.printStackTrace();
		}
		*/
		
		System.out.println("*************************");
		
		
		it = bd.getAsociados().iterator();
		while (it.hasNext())
		{
			Asociado a = (Asociado) it.next();
			System.out.println(a);
		}
		
		
		bd.desconectar();
	}

}
