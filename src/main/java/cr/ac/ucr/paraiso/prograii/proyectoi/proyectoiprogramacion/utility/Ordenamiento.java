package cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.utility;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client.GuardarXML;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Ordenamiento {

    GuardarXML guardar = new GuardarXML();

    public void ordenarPatronesPorNombre(Element raiz, Document documento, String rutaDocumento) throws IOException, JDOMException {
        List<Element> patrones = raiz.getChildren("patron");

        // Ordena los patrones por nombre usando un comparador (ahora lambda).
        patrones.sort((p1, p2) -> {
            String nombre1 = p1.getChildText("nombre");
            String nombre2 = p2.getChildText("nombre");
            return nombre1.compareToIgnoreCase(nombre2);
        });// End of lambda [sort].
        guardar.guardar(rutaDocumento, documento);
    }// End of method [ordenarPatronesPorNombre].

    public void ordenarPatronesPorID(Element raiz, Document documento, String rutaDocumento) throws IOException, JDOMException {
        List<Element> patrones = raiz.getChildren("patron");

        // Ordena los patrones por ID usando un comparador (ahora lambda).
        patrones.sort((p1, p2) -> {
            String id1 = p1.getAttributeValue("idDelPatron");
            String id2 = p2.getAttributeValue("idDelPatron");
            return id1.compareTo(id2);
        });// End of lambda [sort].
        guardar.guardar(rutaDocumento, documento);
    }// End of method [ordenarPatronesPorID].

    public void ordenarPatronesPorTipo(Element raiz, Document documento, String rutaDocumento) throws IOException, JDOMException {
        List<Element> patrones = raiz.getChildren("patron");

        // Ordena los patrones por tipo usando un comparador (ahora lambda).
        patrones.sort((p1, p2) -> {
            String tipo1 = p1.getChildText("tipo");
            String tipo2 = p2.getChildText("tipo");
            return tipo1.compareToIgnoreCase(tipo2);
        });// End of lambda [sort].
        guardar.guardar(rutaDocumento, documento);
    }// End of method [ordenarPatronesPorTipo].

    public void ordenarPatronesPorFecha(Element raiz, Document documento, String rutaDocumento) throws IOException, JDOMException {
        List<Element> patrones = raiz.getChildren("patron");

        // Ordena los patrones por fecha de agregado usando un comparador (ahora lambda).
        patrones.sort((p1, p2) -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha1 = LocalDate.parse(p1.getChildText("fechaAgregado"), formatter);
            LocalDate fecha2 = LocalDate.parse(p2.getChildText("fechaAgregado"), formatter);
            return fecha1.compareTo(fecha2);
        });// End of lambda [sort].

        // Reemplazar los elementos ordenados en el documento.
        raiz.removeChildren("patron");
        for (Element patron : patrones)
            raiz.addContent(patron);

        guardar.guardar(rutaDocumento, documento);
    }// End of method [ordenarPatronesPorFecha].

}
