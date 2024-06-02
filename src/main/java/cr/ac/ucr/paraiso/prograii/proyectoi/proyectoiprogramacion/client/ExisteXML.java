package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import org.jdom2.Element;

import java.util.List;

public class ExisteXML {
    public boolean existe(DesignPattern DP, Element raiz) {
        List<Element> patrones = raiz.getChildren("patron");
        if (!patrones.isEmpty())
            for (Element patron : patrones) {
                String comparar = String.valueOf(patron.getAttributeValue("idDelPatron"));
                if (comparar.equals(DP.getId())) return true;
            }
        return false;
    }
}
