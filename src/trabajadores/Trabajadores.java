package trabajadores;

import farmacia.util.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trabajadores.model.Trabajador;

public class Trabajadores {
    //variables globales
    public static ObservableList<Trabajador> lista = FXCollections.observableArrayList();

    //funcion para mostrar pacientes
    public static void Mostrar(){
        try {
            lista.clear();
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(new String[]{"*"}, "Trabajadores", "", "Nombre");
            while (bd.Resultado.next()){
                Trabajador nuevo = new Trabajador();
                nuevo.clave.setValue(bd.Resultado.getString("Clave"));
                nuevo.nombre.setValue(bd.Resultado.getString("Nombre"));
                nuevo.genero.setValue(bd.Resultado.getString("Genero"));
                nuevo.edad.setValue(bd.Resultado.getString("Edad"));
                nuevo.estudios.setValue(bd.Resultado.getString("Estudios"));
                nuevo.capacidad.setValue(bd.Resultado.getString("Capacidad"));
                nuevo.domicilio.setValue(bd.Resultado.getString("Domicilio"));
                nuevo.telefono.setValue(bd.Resultado.getString("Telefono"));
                nuevo.responsable.setValue(bd.Resultado.getString("Responsable"));
                nuevo.honesto.setValue(bd.Resultado.getString("Honesto"));
                nuevo.comportamiento.setValue(bd.Resultado.getString("Comportamiento"));
                lista.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
