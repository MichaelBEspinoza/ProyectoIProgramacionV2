package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain;

import java.util.Random;

public class GeneratePatternCode {
    public String generatePatternCode() {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder code = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 6; i++) {
            code.append(caracteres.charAt(rnd.nextInt(caracteres.length())));
        }
        return code.toString();
    }
}