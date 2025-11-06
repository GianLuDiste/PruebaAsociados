package conexionBD;

public class AsociadoExistenteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Asociado a;

	public AsociadoExistenteException(Asociado a) {
		super();
		this.a = a;
	}

	public Asociado getA() {
		return a;
	}
	
	

}
