package conexionBD;

import java.util.ArrayList;

public interface IPersistencia 
{
	public void conectar();
	public void desconectar();
	public void inicializacion();
	public ArrayList<Asociado> getAsociados();
	public void altaBD(Asociado a) throws AsociadoExistenteException;
	public void bajaBD(Asociado a) throws AsociadoInexistenteException;
}

// usar wnidowsListener onClose();