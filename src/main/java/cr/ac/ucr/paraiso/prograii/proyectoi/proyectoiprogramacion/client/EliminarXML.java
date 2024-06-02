package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.PatronNoEncontradoException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.List;

public class EliminarXML {
    private final GuardarXML guardarXML = new GuardarXML();

    public void eliminar(DesignPattern DP, Document documento, Element raiz, String rutaDocumento) throws IOException, JDOMException, PatronNoEncontradoException {
        String codigoDelPatron = DP.getId();
        List<Element> patrones = raiz.getChildren("patron");
        for (Element patron : patrones)
            if (patron.getAttributeValue("idDelPatron").equals(codigoDelPatron)) {
                patron.detach();
                guardarXML.guardar(rutaDocumento, documento);
                return;
            }
        throw new PatronNoEncontradoException();
    }
}
