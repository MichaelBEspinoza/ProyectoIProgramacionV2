package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.patternData;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;

import java.util.List;

public class UpdatePattern {
    public static void updatePattern(DesignPattern updatedPattern) throws Exception {
        List<DesignPattern> patterns = LoadPatterns.loadPatterns();
        for (int i = 0; i < patterns.size(); i++) {
            if (patterns.get(i).getId().equals(updatedPattern.getId())) {
                patterns.set(i, updatedPattern);
                break;
            }
        }
        SavePatterns.savePatterns(patterns);
    }
}