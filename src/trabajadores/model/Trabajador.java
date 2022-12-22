package trabajadores.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Trabajador {
    //atributos
    public StringProperty clave;
    public StringProperty nombre;
    public StringProperty genero;
    public StringProperty edad;
    public StringProperty estudios;
    public StringProperty capacidad;
    public StringProperty domicilio;
    public StringProperty telefono;
    public StringProperty responsable;
    public StringProperty honesto;
    public StringProperty comportamiento;


    //constructor
    public Trabajador(){
        clave = new SimpleStringProperty("");
        nombre = new SimpleStringProperty("");
        genero = new SimpleStringProperty("");
        edad = new SimpleStringProperty("");
        estudios = new SimpleStringProperty("");
        capacidad = new SimpleStringProperty("");
        domicilio = new SimpleStringProperty("");
        telefono = new SimpleStringProperty("");
        responsable = new SimpleStringProperty("");
        honesto = new SimpleStringProperty("");
        comportamiento = new SimpleStringProperty("");
    }
}
