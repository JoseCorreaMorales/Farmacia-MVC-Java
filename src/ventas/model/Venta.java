package ventas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Venta {
    //atributos
    public StringProperty clave;
    public StringProperty trabajador;
    public StringProperty cliente;
    public StringProperty fecha;
    public StringProperty total;

    //constructor
    public Venta(){
        clave = new SimpleStringProperty("");
        trabajador = new SimpleStringProperty("");
        cliente = new SimpleStringProperty("");
        fecha = new SimpleStringProperty("");
        total = new SimpleStringProperty("");
    }
}
