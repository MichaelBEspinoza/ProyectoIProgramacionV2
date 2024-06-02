package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.patternData;


import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.ToXMLElement;
import org.jdom2.Document;
import org.jdom2.Element;

import java.io.IOException;
import java.util.List;

public class SavePatterns {
    private static final String FILE_PATH = "patterns.xml";
    static final ToXMLElement toXMLElement = new ToXMLElement();

    public static void savePatterns(List<DesignPattern> patterns) throws IOException {
        Element rootElement = new Element("patterns");
        Document document = new Document(rootElement);
        for (DesignPattern pattern : patterns) {
            rootElement.addContent(toXMLElement.toXMLElement(pattern));
        }
        SaveDocument.saveDocument(document, FILE_PATH);
    }
}