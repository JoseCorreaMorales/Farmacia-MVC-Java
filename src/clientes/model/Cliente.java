package clientes.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente {
    //atributos
    public StringProperty clave;
    public StringProperty nombre;
    public StringProperty economia;
    public StringProperty enfermedades;
    public StringProperty alergias;
    public StringProperty edad;
    public StringProperty sintomas;
    public StringProperty embarazo;


    //constructor
    public Cliente(){
        clave = new SimpleStringProperty("");
        nombre = new SimpleStringProperty("");
        economia = new SimpleStringProperty("");
        enfermedades = new SimpleStringProperty("");
        alergias = new SimpleStringProperty("");
        edad = new SimpleStringProperty("");
        sintomas = new SimpleStringProperty("");
        embarazo = new SimpleStringProperty("");

    }

}
