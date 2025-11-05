package conexionBD;

import java.util.ArrayList;
import java.util.Iterator;

public class Prueba {

	public static void main(String[] args) {
		basedeDatos bd = new basedeDatos();
		
		bd.conectar();
		ArrayList<Asociado> listado = bd.getAsociados();
		
		Iterator it = listado.iterator();
		while (it.hasNext())
		{
			Asociado a = (Asociado) it.next();
			System.out.println(a);
		}
		
		listado.add(new Asociado("Cristina", "Pepi", "16858124", "Calle 100", 156, "223456789", "Mechongue", 0));
		
		
		System.out.println("*************************");
		
		it = listado.iterator();
		while (it.hasNext())
		{
			Asociado a = (Asociado) it.next();
			System.out.println(a);
		}
		
		bd.setAsociados(listado);
		
		
		bd.desconectar();
	}

}
