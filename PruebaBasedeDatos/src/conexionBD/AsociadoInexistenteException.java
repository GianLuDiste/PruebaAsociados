package conexionBD;

public class AsociadoInexistenteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Asociado a;

	public AsociadoInexistenteException(Asociado a) {
		super();
		this.a = a;
	}
	
	public Asociado getA() {
		return a;
	}
	

}
