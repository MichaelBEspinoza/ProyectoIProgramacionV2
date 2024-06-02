package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.PatronNoEncontradoException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActualizarXML {
    private final GuardarXML guardarXML = new GuardarXML();

    public void actualizar(String codigoDelPatron, DesignPattern nuevoPatron, Document documento, Element raiz, String rutaDocumento) throws IOException, JDOMException, PatronNoEncontradoException {
        List<Element> patrones = raiz.getChildren("patron");
        for (Element patron : patrones)
            if (patron.getAttributeValue("idDelPatron").equals(codigoDelPatron)) {
                patron.getChild("nombre").setText(nuevoPatron.getName());
                patron.getChild("descripcion").setText(nuevoPatron.getDescription());
                patron.getChild("tipo").setText(nuevoPatron.getType());
                patron.getChild("ejemploDeCodigo").setText(nuevoPatron.getCodeExamples());
                patron.getChild("fechaAgregado").setText(nuevoPatron.getFechaAgregado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                patron.getChild("contexto").setText(nuevoPatron.getContext());
                patron.getChild("problema").setText(nuevoPatron.getProblem());
                patron.getChild("solucion").setText(nuevoPatron.getSolution());
                guardarXML.guardar(rutaDocumento, documento);
                return;
            }
        throw new PatronNoEncontradoException();
    }
}
