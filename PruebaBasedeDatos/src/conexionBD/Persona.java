package conexionBD;
	
public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String calle;
    protected int numero;
    protected String telefono;
    protected String ciudad;

  

    public Persona(String nombre, String apellido, String dni, String calle, int numero, String telefono, String ciudad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.calle = calle;
        this.numero = numero;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }
}
