module cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jdom2;
    requires java.xml;


    opens cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion to javafx.fxml;
    exports cr.ac.ucr.paraiso.prograii.proyectoi.proyectoiprogramacion;
}