package ventas;

import farmacia.util.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ventas.model.Producto;
import ventas.model.Venta;

public class Ventas {
    //variables globales
    public static ObservableList<Venta> lista = FXCollections.observableArrayList();
    public static ObservableList<Producto> productos = FXCollections.observableArrayList();


    //funcion para mostrar pacientes
    public static void Mostrar(){
        try {
            lista.clear();
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(new String[]{"*"}, "Ventas", "", "Fecha");
            while (bd.Resultado.next()){
                Venta nuevo = new Venta();
                nuevo.clave.setValue(bd.Resultado.getString("Clave"));
                nuevo.trabajador.setValue(bd.Resultado.getString("Trabajador"));
                nuevo.cliente.setValue(bd.Resultado.getString("Cliente"));
                nuevo.fecha.setValue(bd.Resultado.getString("Fecha"));
                nuevo.total.setValue(bd.Resultado.getString("Total"));
                lista.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
