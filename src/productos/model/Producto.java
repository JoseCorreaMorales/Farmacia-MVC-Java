package productos.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Producto {
    //atributos
    public StringProperty clave;
    public StringProperty nombre;
    public StringProperty precio;
    public StringProperty caducidad;
    public StringProperty cantidad;
    public StringProperty funcion;
    public StringProperty dosis;
    public StringProperty formula;
    public StringProperty precauciones;
    public StringProperty reaccion;


    //constructor
    public Producto(){
        clave = new SimpleStringProperty("");
        nombre = new SimpleStringProperty("");
        precio = new SimpleStringProperty("");
        caducidad = new SimpleStringProperty("");
        cantidad = new SimpleStringProperty("");
        funcion = new SimpleStringProperty("");
        dosis = new SimpleStringProperty("");
        formula = new SimpleStringProperty("");
        precauciones = new SimpleStringProperty("");
        reaccion = new SimpleStringProperty("");
    }
}
