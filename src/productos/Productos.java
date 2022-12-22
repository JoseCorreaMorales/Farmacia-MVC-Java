package productos;

import farmacia.util.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import productos.model.Producto;

public class Productos {
    //variables globales
    public static ObservableList<Producto> lista = FXCollections.observableArrayList();

    //funcion para mostrar pacientes
    public static void Mostrar(){
        try {
            lista.clear();
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(new String[]{"*"}, "Productos", "", "Nombre");
            while (bd.Resultado.next()){
                Producto nuevo = new Producto();
                nuevo.clave.setValue(bd.Resultado.getString("Clave"));
                nuevo.nombre.setValue(bd.Resultado.getString("Nombre"));
                nuevo.precio.setValue(bd.Resultado.getString("Precio"));
                nuevo.caducidad.setValue(bd.Resultado.getString("Caducidad"));
                nuevo.cantidad.setValue(bd.Resultado.getString("Cantidad"));
                nuevo.funcion.setValue(bd.Resultado.getString("Funcion"));
                nuevo.dosis.setValue(bd.Resultado.getString("Dosis"));
                nuevo.formula.setValue(bd.Resultado.getString("Formula"));
                nuevo.precauciones.setValue(bd.Resultado.getString("Precauciones"));
                nuevo.reaccion.setValue(bd.Resultado.getString("Reaccion"));
                lista.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
