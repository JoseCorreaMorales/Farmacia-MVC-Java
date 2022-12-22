package clientes;

import clientes.model.Cliente;
import farmacia.util.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Clientes {
    //variables globales
    public static ObservableList<Cliente> lista = FXCollections.observableArrayList();

    //funcion para mostrar pacientes
    public static void Mostrar(){
        try {
            lista.clear();
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(new String[]{"*"}, "Clientes", "", "Nombre");
            while (bd.Resultado.next()){
                Cliente nuevo = new Cliente();
                nuevo.clave.setValue(bd.Resultado.getString("Clave"));
                nuevo.nombre.setValue(bd.Resultado.getString("Nombre"));
                nuevo.economia.setValue(bd.Resultado.getString("Economia"));
                nuevo.enfermedades.setValue(bd.Resultado.getString("Enfermedades"));
                nuevo.alergias.setValue(bd.Resultado.getString("Alergias"));
                nuevo.edad.setValue(bd.Resultado.getString("Edad"));
                nuevo.sintomas.setValue(bd.Resultado.getString("Sintomas"));
                nuevo.embarazo.setValue(bd.Resultado.getString("Embarazo"));
                lista.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
