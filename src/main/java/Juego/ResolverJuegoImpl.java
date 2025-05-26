package Juego;

import java.util.ArrayList;

import salasEscape.Asignacion;
import salasEscape.Incompatibilidades;
import salasEscape.ResolverJuego;
import salasEscape.Restricciones;

public class ResolverJuegoImpl implements ResolverJuego {
	@Override
	public ArrayList<Asignacion> resolverAsignaciones(ArrayList<String> personas, int cantSalasPersonas, ArrayList<Incompatibilidades> incompatibilidades, Restricciones restricciones){
		ArrayList<Asignacion> resultado = new ArrayList<Asignacion>();
	/*	
		Asignacion a = new Asignacion("A", 1);
		resultado.add(a);
		a = new Asignacion("C", 3);
		resultado.add(a);
		a = new Asignacion("B", 2);
		resultado.add(a); */
		return resultado;
	}


}
