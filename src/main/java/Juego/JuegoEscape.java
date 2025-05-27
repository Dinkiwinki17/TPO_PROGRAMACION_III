package Juego;

import java.util.*;

import salasEscape.Asignacion;
import salasEscape.Incompatibilidades;
import salasEscape.Restricciones;

public class JuegoEscape {

	
	    public static void main(String[] args) {
			System.out.println("Hola");
	    	ResolverJuegoImpl juego = new ResolverJuegoImpl();
	    	int cantidadSalas = 6;
	    	Incompatibilidades ic1 = new Incompatibilidades("A","B");
	    	ArrayList<Incompatibilidades> imcop = new ArrayList<Incompatibilidades>();
	    	
	    	
	    	imcop.add(ic1);
	    	
	    	Restricciones rest = new Restricciones();
	    	
	    	rest.agregarRestriccion("A", 1);
	    	rest.agregarRestriccion("A", 4);
	    	
	    	rest.agregarRestriccion("C", 3);
	    	
	    	ArrayList<String> personas = new ArrayList<String>();
	    	personas.add("A");
	    	personas.add("B");
	    	personas.add("C");
	    	personas.add("D");
	    	personas.add("E");
	    	personas.add("F");
	    	
	    
	    	
	
	    	ArrayList<Asignacion> resultado = juego.resolverAsignaciones(personas, cantidadSalas, imcop, rest);
	    	imprimirAsignacion(resultado);
	    	
	     
	    }
	    
	    public static void imprimirAsignacion(ArrayList<Asignacion> resultado) {
	    	
	    	for (int i= 0; i < resultado.size(); i++) {
	    		System.out.println(resultado.get(i).getPersona()+"-"+resultado.get(i).getSala());
	    	}
	    }
}
