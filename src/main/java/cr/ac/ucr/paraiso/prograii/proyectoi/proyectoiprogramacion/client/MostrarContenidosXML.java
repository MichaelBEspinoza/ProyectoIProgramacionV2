package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client;

import org.jdom2.Attribute;
import org.jdom2.Element;

public class MostrarContenidosXML {
    public String mostrarContenidos(Element raiz) {
        StringBuilder contenidos = new StringBuilder();
        for (Element elemento : raiz.getChildren()) {
            contenidos.append("\n<patron");

            Attribute idAttribute = elemento.getAttribute("idDelPatron");
            if (idAttribute != null) contenidos.append(" idDelPatron=\"").append(idAttribute.getValue()).append("\"");

            contenidos.append(">\n");

            String nombre = elemento.getChildText("nombre");
            if (nombre != null && !nombre.isEmpty()) contenidos.append("  <nombre>").append(nombre).append("</nombre>\n");

            String descripcion = elemento.getChildText("descripcion");
            if (descripcion != null && !descripcion.isEmpty()) contenidos.append("  <descripcion>").append(descripcion).append("</descripcion>\n");

            String tipo = elemento.getChildText("tipo");
            if (tipo != null && !tipo.isEmpty()) contenidos.append("  <tipo>").append(tipo).append("</tipo>\n");

            String ejemploCodigo = elemento.getChildText("ejemploDeCodigo");
            if (ejemploCodigo != null && !ejemploCodigo.isEmpty()) contenidos.append("  <ejemploDeCodigo>").append(ejemploCodigo).append("</ejemploDeCodigo>\n");

            String fechaAgregado = elemento.getChildText("fechaAgregado");
            if (fechaAgregado != null && !fechaAgregado.isEmpty()) contenidos.append("  <fechaAgregado>").append(fechaAgregado).append("</fechaAgregado>\n");

            contenidos.append("</patron>\n");
        }
        return contenidos.toString();
    }
}
