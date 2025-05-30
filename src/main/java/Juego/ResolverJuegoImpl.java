package Juego;

import java.util.*;
import salasEscape.*;


public class ResolverJuegoImpl implements ResolverJuego {

    @Override
    public ArrayList<Asignacion> resolverAsignaciones(ArrayList<String> personas, int cantSalas,
            ArrayList<Incompatibilidades> incompatibilidades, Restricciones restricciones) {

        ArrayList<Asignacion> resultado = new ArrayList<>();
        boolean[] salasOcupadas = new boolean[cantSalas + 1];
        HashMap<String, Integer> asignacionesParciales = new HashMap<>();

        // Map auxiliar porque no podemos consultar Restricciones
        Map<String, Set<Integer>> mapaRestricciones = construirMapaRestricciones(personas);

        boolean exito = backtrack(personas, 0, salasOcupadas, asignacionesParciales, mapaRestricciones, incompatibilidades);

        if (exito) {
            for (String persona : personas) {
                int sala = asignacionesParciales.get(persona);
                resultado.add(new Asignacion(persona, sala));
            }
        }

        return resultado;
    }

    private Map<String, Set<Integer>> construirMapaRestricciones(ArrayList<String> personas) {
        Map<String, Set<Integer>> mapa = new HashMap<>();
        for (String persona : personas) {
            mapa.put(persona, new HashSet<>());
        }

        //  Esto no se puede llenar aquí directamente. El main debe llenarlo junto con restricciones
        return mapa;
    }

    private boolean backtrack(ArrayList<String> personas, int idx, boolean[] salasOcupadas,
            HashMap<String, Integer> asignaciones, Map<String, Set<Integer>> mapaRestricciones,
            ArrayList<Incompatibilidades> incompatibilidades) {

        if (idx == personas.size()) return true;

        String persona = personas.get(idx);

        for (int sala = 1; sala < salasOcupadas.length; sala++) {
            if (salasOcupadas[sala]) continue;
            if (mapaRestricciones.get(persona).contains(sala)) continue;
            if (!esCompatible(persona, sala, asignaciones, incompatibilidades)) continue;

            asignaciones.put(persona, sala);
            salasOcupadas[sala] = true;

            if (backtrack(personas, idx + 1, salasOcupadas, asignaciones, mapaRestricciones, incompatibilidades)) {
                return true;
            }

            asignaciones.remove(persona);
            salasOcupadas[sala] = false;
        }

        return false;
    }

    private boolean esCompatible(
    	    String persona,
    	    int sala,
    	    HashMap<String, Integer> asignaciones,
    	    ArrayList<Incompatibilidades> incompatibilidades
    	) {
    	    for (Incompatibilidades inc : incompatibilidades) {
    	        String p1 = inc.getPersona1();
    	        String p2 = inc.getPersona2();

    	        if (p1.equals(persona) || p2.equals(persona)) {
    	            String otra = p1.equals(persona) ? p2 : p1;
    	            if (asignaciones.containsKey(otra)) {
    	                int salaOtra = asignaciones.get(otra);
    	                if (Math.abs(sala - salaOtra) == 1) {
    	                    return false;
    	                }
    	            }
    	        }
    	    }
    	    return true;
    	}
}
