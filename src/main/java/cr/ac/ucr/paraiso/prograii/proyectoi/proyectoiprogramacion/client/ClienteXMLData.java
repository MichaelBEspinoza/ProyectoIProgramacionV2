package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.patternData.SaveDocument;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;

public class ClienteXMLData {

    /* Declaración de atributos. */
    private final String rutaDocumento;
    private final Element raiz;
    private final Document documento;


    public ClienteXMLData(String rutaDocumento) throws IOException, JDOMException {
        File file = new File(rutaDocumento);
        if (!file.exists()) {
            // Si el archivo no existe, se encarga de crear el DOM y el documento XML.
            this.rutaDocumento = rutaDocumento;
            this.raiz = new Element("patrones"); // Asigna el nombre al tag 'raiz'.
            this.documento = new Document(raiz); // Agrega la raiz al documento XML.
            SaveDocument.saveDocument(this.documento, this.rutaDocumento); // Guarda el XML respectivo.
        } else {
            // Se encarga de parsear el documento XML a un DOM.
            SAXBuilder saxBuilder = new SAXBuilder();
            saxBuilder.setIgnoringElementContentWhitespace(true); // Se define para ignorar los elementos con espacios en blanco.
            this.documento = saxBuilder.build(new File(rutaDocumento)); // Obtiene el documento XML utilizando el SAXBuilder.
            this.raiz = documento.getRootElement(); // Obtiene el elemento raiz del documento y lo asigna al atributo raiz.
            this.rutaDocumento = rutaDocumento;
        }
    }

    public Document getDocumento() {
        return documento;
    }

    public Element getRaiz() {
        return raiz;
    }

    public String getRutaDocumento() {
        return rutaDocumento;
    }
}