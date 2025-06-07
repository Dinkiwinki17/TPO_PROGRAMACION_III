package Juego;

import java.util.*;
import salasEscape.*;

public class JuegoEscape {

	public static void main(String[] args) {
		ArrayList<String> personas = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			personas.add(String.format("%04d", i));
		}
		int cantSalas = personas.size();

		Restricciones restricciones = new Restricciones();

		ArrayList<Incompatibilidades> incompatibilidades = new ArrayList<>();

		ResolverJuego resolutor = new ResolverJuegoImpl();


	// Medir el tiempo de ejecución
		long startTime = System.nanoTime();
		ArrayList<Asignacion> asignaciones = resolutor.resolverAsignaciones(personas, cantSalas, incompatibilidades, restricciones);
		long endTime = System.nanoTime();

	// Calcular el tiempo transcurrido en nanosegundos
		long duration = endTime - startTime;

	// Convertir nanosegundos a milisegundos
		double durationInMillis = duration / 1_000_000.0;
		String tiempoEjecucion = "Tiempo de ejecución para " + cantSalas + " personas es: " + durationInMillis + " milisegundos";




		if (asignaciones.isEmpty()) {
			System.out.println("No se encontró una solución válida.");
			// Imprimir el tiempo de ejecución
			System.out.println(tiempoEjecucion);
		} else {
			System.out.println("Solución encontrada:");
			for (Asignacion asignacion : asignaciones) {
				System.out.println("Persona " + asignacion.getPersona() + " → Sala " + asignacion.getSala());
			}
			// Imprimir el tiempo de ejecución
			System.out.println(tiempoEjecucion);
		}
	}
}


