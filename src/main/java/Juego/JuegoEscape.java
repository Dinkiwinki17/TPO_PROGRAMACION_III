package Juego;

import java.util.*;
import salasEscape.*;

public class JuegoEscape {

	public static void medirTiempos(ResolverJuego juego) {
		System.out.printf("%-10s%-15s\n", "N", "Tiempo (ms)");

		for (int n = 2; n <= 10; n++) {
			ArrayList<String> personas = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				personas.add("P" + i);
			}

			ArrayList<Incompatibilidades> incompatibilidades = new ArrayList<>();
			// Podés agregar restricciones si querés, por ahora dejamos sin para evaluar el
			// caso peor

			Restricciones restricciones = new Restricciones();

			long inicio = System.currentTimeMillis();
			ArrayList<Asignacion> resultado = juego.resolverAsignaciones(personas, n, incompatibilidades,
					restricciones);
			long fin = System.currentTimeMillis();

			System.out.printf("%-10d%-15d\n", n, (fin - inicio));
		}
	}

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
		ArrayList<Asignacion> asignaciones = resolutor.resolverAsignaciones(personas, cantSalas, incompatibilidades,
				restricciones);

		if (asignaciones.isEmpty()) {
			System.out.println("No se encontró una solución válida.");
		} else {
			System.out.println("Solución encontrada:");
			for (Asignacion asignacion : asignaciones) {
				System.out.println("Persona " + asignacion.getPersona() + " → Sala " + asignacion.getSala());
			}
		}

		medirTiempos(resolutor);
	}

}
