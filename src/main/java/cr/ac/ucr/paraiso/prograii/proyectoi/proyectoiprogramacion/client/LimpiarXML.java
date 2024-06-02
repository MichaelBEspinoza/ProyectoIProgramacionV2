package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;

public class LimpiarXML {
    private final GuardarXML guardarXML = new GuardarXML();

    public void limpiar(Document documento, Element raiz, String rutaDocumento) throws IOException, JDOMException {
        raiz.removeContent();
        guardarXML.guardar(rutaDocumento, documento);
    }
}
