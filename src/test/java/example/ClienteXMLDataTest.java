package example;

import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.client.*;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.DesignPattern;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.GeneratePatternCode;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.domain.PatronNoEncontradoException;
import cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion.utility.Ordenamiento;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ClienteXMLDataTest {

    ClienteXMLData client = new ClienteXMLData("pruebas.xml");
    LimpiarXML limpiar = new LimpiarXML();
    BuscarPorIDXML buscar = new BuscarPorIDXML();
    InsertarXML insertar = new InsertarXML();
    MostrarContenidosXML mostrar = new MostrarContenidosXML();
    ListaXML lista = new ListaXML();
    ActualizarXML actualizar = new ActualizarXML();
    ClienteXMLDataTest() throws IOException, JDOMException {}
    ExisteXML existe = new ExisteXML();
    EliminarXML eliminar = new EliminarXML();
    Ordenamiento ordenes = new Ordenamiento();
    GeneratePatternCode generate = new GeneratePatternCode();

    @Test
    public void insertar_y_eliminar_funcionan() throws Exception {

        // Arrange.
        DesignPattern nuevoPatron = new DesignPattern();
        String id = generate.generatePatternCode();
        nuevoPatron = new DesignPattern(id, "Nombre", "Descripción", "Tipo","codeExamples", "Context", "Problem", "Solution", LocalDate.of(2024,5,17));

        // Act.
        insertar.insertar(nuevoPatron, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());

        // Assert.
        assert(existe.existe(nuevoPatron, client.getRaiz()));

        // Act.
        eliminar.eliminar(nuevoPatron, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());

        // Assert.
        assert(!existe.existe(nuevoPatron, client.getRaiz()));
        limpiar.limpiar(client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
    }

    @Test
    public void insertar_ordenado() throws IOException, JDOMException {
        limpiar.limpiar(client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(new DesignPattern("ID1", "Patron A", "Descripción A", "Tipo A", "Examples", "Código A", "Context", "Problem", LocalDate.now().minusDays(1)), client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(new DesignPattern("ID2", "Patron B", "Descripción B", "Tipo B", "Examples", "Código B", "Context", "Problem", LocalDate.now().minusDays(2)), client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(new DesignPattern("ID3", "Patron C", "Descripción C", "Tipo C", "Examples", "Código C", "Context", "Problem", LocalDate.now().minusDays(3)), client.getDocumento(), client.getRaiz(), client.getRutaDocumento());

        List<Element> patrones = lista.lista(client.getRaiz());

        // Assert: Verificar que los patrones estén ordenados correctamente por nombre.
        assertEquals("Patron A", patrones.get(0).getChildText("nombre"));
        assertEquals("Patron B", patrones.get(1).getChildText("nombre"));
        assertEquals("Patron C", patrones.get(2).getChildText("nombre"));

        limpiar.limpiar(client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
    }

    @Test
    void actualizar_funciona() throws IOException, PatronNoEncontradoException, JDOMException {
        // Arrange: crear un patrón inicial en el XML.
        DesignPattern patronInicial = new DesignPattern("ABC123", "PatronInicial", "DescripcionInicial", "TipoInicial", "EjemploCodigoInicial", "Codigo", "Context", "Problem", LocalDate.now().minusDays(1));
        insertar.insertar(patronInicial, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());

        // Crear un patrón nuevo con los nuevos valores para actualizar.
        DesignPattern nuevoPatron = new DesignPattern("ABC123", "NuevoPatron", "DescripcionNuevoPatron", "TipoNuevoPatron", "EjemploCodigoNuevoPatron", "Codigo", "Context", "Problem", LocalDate.now().minusDays(2));

        // Act: actualizar el patrón existente en el XML.
        actualizar.actualizar("ABC123", nuevoPatron, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        List<Element> patrones = lista.lista(client.getRaiz());

        // Verificar el contenido de la lista de patrones.
        for (Element patron : patrones) {
            System.out.println("ID: " + patron.getAttributeValue("idDelPatron"));
            System.out.println("Nombre: " + patron.getChildText("nombre"));
            System.out.println("Descripcion: " + patron.getChildText("descripcion"));
            System.out.println("Tipo: " + patron.getChildText("tipo"));
            System.out.println("EjemploCodigo: " + patron.getChildText("ejemploDeCodigo"));
            System.out.println("FechaAgregado: " + patron.getChildText("fechaAgregado"));
        }

        // Assert: verificar que los cambios se realizaron correctamente.
        assertEquals(1, patrones.size()); // Debe haber solo un patrón en el archivo XML
        assertEquals("NuevoPatron", patrones.getFirst().getChildText("nombre"));
        assertEquals("DescripcionNuevoPatron", patrones.getFirst().getChildText("descripcion"));
        assertEquals("TipoNuevoPatron", patrones.getFirst().getChildText("tipo"));
        assertEquals("EjemploCodigoNuevoPatron", patrones.getFirst().getChildText("ejemploDeCodigo"));
        assertNotNull(patrones.getFirst().getChildText("fechaAgregado"));
        assertEquals(nuevoPatron.getFechaAgregado().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), patrones.getFirst().getChildText("fechaAgregado"));

        limpiar.limpiar(client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
    }

    @Test
    public void ordenar_por_criterios_funciona() throws IOException, JDOMException  {
        // Arrange: Agregar algunos patrones desordenados.
        limpiar.limpiar(client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(new DesignPattern("ID2", "Patron B", "Descripción B", "Tipo B", "Examples", "Código B", "Context", "Problem", LocalDate.of(2024,5,31)), client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(new DesignPattern("ID1", "Patron A", "Descripción A", "Tipo A", "Examples", "Código A", "Context", "Problem", LocalDate.of(2024, 5, 30)), client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(new DesignPattern("ID3", "Patron C", "Descripción C", "Tipo C", "Examples", "Código C", "Context", "Problem", LocalDate.of(2024,6,1)), client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        ordenes.ordenarPatronesPorNombre(client.getRaiz(), client.getDocumento(), client.getRutaDocumento());

        // Assert: Verificar que los patrones estén ordenados correctamente por nombre.
        List<Element> patrones = lista.lista(client.getRaiz());
        assertEquals("Patron A", patrones.get(0).getChildText("nombre"));
        assertEquals("Patron B", patrones.get(1).getChildText("nombre"));
        assertEquals("Patron C", patrones.get(2).getChildText("nombre"));
        System.out.println(mostrar.mostrarContenidos(client.getRaiz()));

        ordenes.ordenarPatronesPorFecha(client.getRaiz(), client.getDocumento(), client.getRutaDocumento());
        System.out.println("Por fechas\n" + mostrar.mostrarContenidos(client.getRaiz()));

        assertEquals("30/05/2024", patrones.get(0).getChildText("fechaAgregado"));
        assertEquals("31/05/2024", patrones.get(1).getChildText("fechaAgregado"));
        assertEquals("01/06/2024", patrones.get(2).getChildText("fechaAgregado"));
        System.out.println(mostrar.mostrarContenidos(client.getRaiz()));

        ordenes.ordenarPatronesPorTipo(client.getRaiz(), client.getDocumento(), client.getRutaDocumento());
        assertEquals("Tipo A", patrones.get(0).getChildText("tipo"));
        assertEquals("Tipo B", patrones.get(1).getChildText("tipo"));
        assertEquals("Tipo C", patrones.get(2).getChildText("tipo"));
        System.out.println(mostrar.mostrarContenidos(client.getRaiz()));

        ordenes.ordenarPatronesPorID(client.getRaiz(), client.getDocumento(), client.getRutaDocumento());
        assertEquals("ID1", patrones.get(0).getAttributeValue("idDelPatron"));
        assertEquals("ID2", patrones.get(1).getAttributeValue("idDelPatron"));
        assertEquals("ID3", patrones.get(2).getAttributeValue("idDelPatron"));
        System.out.println(mostrar.mostrarContenidos(client.getRaiz()));

        limpiar.limpiar(client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
    }

    @Test
    void mostrar_contenidos_funciona() throws IOException, JDOMException, ParserConfigurationException, SAXException {
        DesignPattern patron1 = new DesignPattern("ID1", "Patron A", "Descripción A", "Tipo A", "Examples", "Código A", "Context", "Problem", LocalDate.now().minusDays(1));
        DesignPattern patron2 = new DesignPattern("ID2", "Patron B", "Descripcion B", "Tipo B", "Examples", "Codigo B", "Context", "Problem", LocalDate.now().minusDays(2));
        insertar.insertar(patron1, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(patron2, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        System.out.println(mostrar.mostrarContenidos(client.getRaiz()));
        limpiar.limpiar(client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
    }

    @Test
    public void buscar_por_nombre_funciona() throws IOException, JDOMException, PatronNoEncontradoException {
        // Arrange: Crea e inserta algunos patrones en el archivo XML.
        DesignPattern dp1 = new DesignPattern("ID1", "Patron A", "Descripción A", "Tipo A", "Examples", "Código A", "Context", "Problem", LocalDate.now().minusDays(1));
        DesignPattern dp2 = new DesignPattern("ID2", "Patron B", "Descripción B", "Tipo B", "Examples", "Código B", "Context", "Problem", LocalDate.now().minusDays(2));
        DesignPattern dp3 = new DesignPattern("ID3", "Patron C", "Descripción C", "Tipo C", "Examples", "Código C", "Context", "Problem", LocalDate.now().minusDays(3));
        insertar.insertar(dp1, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(dp2, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
        insertar.insertar(dp3, client.getDocumento(), client.getRaiz(), client.getRutaDocumento());

        // Act: Busca por el ID.
        DesignPattern resultado = buscar.buscarPorID("ID2", client.getRaiz());

        // Assert: Verifica que se encontró el patrón correcto.
        assertNotNull(resultado);
        assertEquals("ID2", resultado.getId());
        assertEquals("Patron B", resultado.getName());
        assertEquals("Descripción B", resultado.getDescription());
        assertEquals("Tipo B", resultado.getType());
        assertEquals("Examples", resultado.getCodeExamples());
        assertEquals(LocalDate.now().minusDays(2), resultado.getFechaAgregado());

        limpiar.limpiar(client.getDocumento(), client.getRaiz(), client.getRutaDocumento());
    }
}