package ventas.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
    //atributos
    public StringProperty venta;
    public StringProperty no;
    public StringProperty nombre;
    public StringProperty precio;
    public StringProperty cantidad;
    public StringProperty subtotal;

    //constructor
    public Producto(){
        venta = new SimpleStringProperty("");
        no = new SimpleStringProperty("");
        nombre = new SimpleStringProperty("");
        precio = new SimpleStringProperty("");
        cantidad = new SimpleStringProperty("");
        subtotal = new SimpleStringProperty("");
    }
}
