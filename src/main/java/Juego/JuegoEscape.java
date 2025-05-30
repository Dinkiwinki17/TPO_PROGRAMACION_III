package Juego;

import java.util.*;
import salasEscape.*;

public class JuegoEscape {

	public static void main(String[] args) {
		ArrayList<String> personas = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
		int cantSalas = 4;

		Restricciones restricciones = new Restricciones();
		restricciones.agregarRestriccion("A", 2);
		restricciones.agregarRestriccion("B", 3);
		restricciones.agregarRestriccion("B", 4);
		restricciones.agregarRestriccion("C", 1);
		restricciones.agregarRestriccion("D", 2);

		ArrayList<Incompatibilidades> incompatibilidades = new ArrayList<>();
		incompatibilidades.add(new Incompatibilidades("A", "B"));
		incompatibilidades.add(new Incompatibilidades("C", "D"));

		ResolverJuego resolutor = new ResolverJuegoImpl();
		ArrayList<Asignacion> asignaciones = resolutor.resolverAsignaciones(personas, cantSalas, incompatibilidades, restricciones);

		if (asignaciones.isEmpty()) {
			System.out.println("No se encontró una solución válida.");
		} else {
			System.out.println("Solución encontrada:");
			for (Asignacion asignacion : asignaciones) {
				System.out.println("Persona " + asignacion.getPersona() + " → Sala " + asignacion.getSala());
			}
		}
	}
}
