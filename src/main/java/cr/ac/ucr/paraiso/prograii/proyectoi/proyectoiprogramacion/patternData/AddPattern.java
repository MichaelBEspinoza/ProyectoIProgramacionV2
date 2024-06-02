package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.patternData;


import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import java.util.List;

public class AddPattern {
    public void addPattern(DesignPattern pattern) throws Exception {
        List<DesignPattern> patterns = LoadPatterns.loadPatterns();
        patterns.add(pattern);
        SavePatterns.savePatterns(patterns);
    }
}