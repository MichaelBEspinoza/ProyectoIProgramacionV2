package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.patternData;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.FromXMLElement;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class LoadPatterns {
    private static final String FILE_PATH = "patterns.xml";

    public static List<DesignPattern> loadPatterns() throws Exception {
        File xmlFile = new File(FILE_PATH);
        FromXMLElement XMLElement = new FromXMLElement();
        if (!xmlFile.exists()) {
            xmlFile.createNewFile();
            Element rootElement = new Element("patterns");
            Document document = new Document(rootElement);
            SaveDocument.saveDocument(document, FILE_PATH);
        }

        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(xmlFile);
        Element rootElement = document.getRootElement();
        return rootElement.getChildren("pattern").stream()
                .map(XMLElement::fromXMLElement)
                .collect(Collectors.toList());
    }
}