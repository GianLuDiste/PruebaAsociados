package conexionBD;

public class Asociado extends Persona {

	protected int solicitudes;
	
	public Asociado(String nombre, String apellido, String dni, String calle, int numero, String telefono, String ciudad, int solicitudes) {
		super(nombre, apellido, dni, calle, numero, telefono, ciudad);
		this.solicitudes = solicitudes;
	}

	@Override
	public String toString() {
		return "Asociado [solicitudes=" + solicitudes + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", calle=" + calle + ", numero=" + numero + ", telefono=" + telefono + ", ciudad=" + ciudad + "]";
	}
	
	
	
}
