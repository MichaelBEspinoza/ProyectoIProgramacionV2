package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain;


import org.jdom2.Element;

public class ToXMLElement {
    public Element toXMLElement(DesignPattern pattern) {
        Element patternElement = new Element("pattern");
        patternElement.setAttribute("id", pattern.getId());
        patternElement.addContent(new Element("name").setText(pattern.getName()));
        patternElement.addContent(new Element("description").setText(pattern.getDescription()));
        patternElement.addContent(new Element("type").setText(pattern.getType()));
        patternElement.addContent(new Element("example").setText(pattern.getCodeExamples()));
        patternElement.addContent(new Element("localDate").setText(String.valueOf(pattern.getFechaAgregado())));
        patternElement.addContent(new Element("context").setText(pattern.getContext()));
        patternElement.addContent(new Element("problem").setText(pattern.getProblem()));
        patternElement.addContent(new Element("solution").setText(pattern.getSolution()));
        return patternElement;
    }
}