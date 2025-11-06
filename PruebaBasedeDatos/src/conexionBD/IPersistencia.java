package conexionBD;

import java.util.ArrayList;

public interface IPersistencia 
{
	public void conectar();
	public void desconectar();
	public ArrayList<Asociado> getAsociados();
	public void altaBD(Asociado a) throws AsociadoExistenteException;
	public void bajaBD(Asociado a) throws AsociadoInexistenteException;
}

// usar wnidowsListener onClose();