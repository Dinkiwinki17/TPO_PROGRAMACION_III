package Juego;

import java.util.*;

import salasEscape.Asignacion;
import salasEscape.Incompatibilidades;
import salasEscape.ResolverJuego;
import salasEscape.Restricciones;

public class ResolverJuegoImpl implements ResolverJuego {

    private ArrayList<Asignacion> resultadoFinal;
    private Set<Integer> salasOcupadas;
    private Map<String, String> asignacionTemporal;
    private Map<String, Set<String>> incompatibilidadesMap;

    @Override
    public ArrayList<Asignacion> resolverAsignaciones(ArrayList<String> personas, int cantSalasPersonas,
                                                      ArrayList<Incompatibilidades> incompatibilidades, Restricciones restricciones) {

        resultadoFinal = new ArrayList<>();
        salasOcupadas = new HashSet<>();
        asignacionTemporal = new HashMap<>();
        incompatibilidadesMap = construirMapaIncompatibilidades(incompatibilidades);

        backtrack(0, personas, cantSalasPersonas, restricciones);

        return resultadoFinal;
    }

    private boolean backtrack(int index, ArrayList<String> personas, int cantSalas, Restricciones restricciones) {
        if (index == personas.size()) {
            for (Map.Entry<String, String> entry : asignacionTemporal.entrySet()) {
                resultadoFinal.add(new Asignacion(entry.getKey(), Integer.parseInt(entry.getValue())));
            }
            return true;
        }

        String persona = personas.get(index);

        for (int sala = 1; sala <= cantSalas; sala++) {
            if (salasOcupadas.contains(sala)) continue;
            if (!restricciones.puedeEstarEnSala(persona, sala)) continue;
            if (!esCompatible(persona, sala)) continue;

            // Asignar provisionalmente
            asignacionTemporal.put(persona, String.valueOf(sala));
            salasOcupadas.add(sala);

            if (backtrack(index + 1, personas, cantSalas, restricciones)) {
                return true;
            }

            // Deshacer
            asignacionTemporal.remove(persona);
            salasOcupadas.remove(sala);
        }

        return false;
    }

    private boolean esCompatible(String persona, int sala) {
        Set<String> incompatibles = incompatibilidadesMap.getOrDefault(persona, new HashSet<>());

        for (Map.Entry<String, String> entry : asignacionTemporal.entrySet()) {
            String otraPersona = entry.getKey();
            int otraSala = Integer.parseInt(entry.getValue());

            if (incompatibles.contains(otraPersona)) {
                if (Math.abs(otraSala - sala) == 1) {
                    return false; // están en salas consecutivas
                }
            }
        }

        return true;
    }

    private Map<String, Set<String>> construirMapaIncompatibilidades(ArrayList<Incompatibilidades> lista) {
        Map<String, Set<String>> map = new HashMap<>();

        for (Incompatibilidades inc : lista) {
            map.computeIfAbsent(inc.getPersona1(), k -> new HashSet<>()).add(inc.getPersona2());
            map.computeIfAbsent(inc.getPersona2(), k -> new HashSet<>()).add(inc.getPersona1());
        }

        return map;
    }
}
