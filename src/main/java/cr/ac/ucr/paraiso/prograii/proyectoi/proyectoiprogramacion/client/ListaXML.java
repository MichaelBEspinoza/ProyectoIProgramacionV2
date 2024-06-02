package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;

import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;

public class ListaXML {
    public List<Element> lista(Element raiz) {
        List<Element> listaCompleta = new ArrayList<>(), elementos = raiz.getChildren();

        for (Element elemento : elementos) {
            Element copiaElemento = new Element(elemento.getName());

            for (Attribute atributo : elemento.getAttributes())
                copiaElemento.setAttribute(atributo.getName(), atributo.getValue());

            for (Content contenido : elemento.getContent())
                copiaElemento.addContent(contenido.clone());

            listaCompleta.add(copiaElemento);
        }
        return listaCompleta;
    }
}
