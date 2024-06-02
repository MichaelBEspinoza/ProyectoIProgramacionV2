package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.PatronNoEncontradoException;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BuscarPorIDXML {
    public DesignPattern buscarPorID(String id, Element raiz) throws IOException, JDOMException, PatronNoEncontradoException {
        List<Element> patrones = raiz.getChildren("patron");
        for (Element patron : patrones) {
            String idPatron = patron.getAttributeValue("idDelPatron");
            if (idPatron.equals(id)) {
                String nombre = patron.getChildText("nombre");
                String descripcion = patron.getChildText("descripcion");
                String tipo = patron.getChildText("tipo");
                String codigo = patron.getChildText("ejemploDeCodigo");
                LocalDate fechaAgregado = LocalDate.parse(patron.getChildText("fechaAgregado"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String contexto = patron.getChildText("contexto");
                String problema = patron.getChildText("problema");
                String solution = patron.getChildText("solucion");
                return new DesignPattern(idPatron, nombre, descripcion, tipo, codigo, contexto, problema, solution, fechaAgregado);
            }
        }
        throw new PatronNoEncontradoException();
    }
}
