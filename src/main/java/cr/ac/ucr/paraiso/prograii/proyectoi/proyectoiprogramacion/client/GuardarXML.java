package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.output.XMLOutputter;

import java.io.IOException;
import java.io.PrintWriter;

public class GuardarXML {
    public void guardar(String rutaDocumento, Document documento) throws IOException, JDOMException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(documento, new PrintWriter(rutaDocumento)); // Escribe en el documento de la ruta.
        xmlOutputter.output(documento, System.out); // Imprime el XML en consola.
    }
}
