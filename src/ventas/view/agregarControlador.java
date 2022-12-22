package ventas.view;

import dialogos.Dialogos;
import farmacia.Farmacia;
import farmacia.util.Fechas;
import farmacia.util.SQLite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ventas.Ventas;
import ventas.model.Producto;
import ventas.model.Venta;

public class agregarControlador {
    //variables globales
    public Stage escenario;
    @FXML
    private ComboBox<String> trabajador;
    @FXML
    private ComboBox<String> cliente;
    @FXML
    private DatePicker fecha;
    @FXML
    private Label total;

    @FXML
    private TableView<Producto> tabla;
    @FXML
    private TableColumn<Producto, String> no;
    @FXML
    private TableColumn<Producto, String> nombre;
    @FXML
    private TableColumn<Producto, String> precio;
    @FXML
    private TableColumn<Producto, String> cantidad;
    @FXML
    private TableColumn<Producto, String> subtotal;

    //funcion para inicializar
    @FXML
    private void initialize() {
        Ventas.productos.clear();
        try {
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(new String[]{"*"}, "Trabajadores", "", "Nombre");
            while (bd.Resultado.next()) {
                trabajador.getItems().addAll(bd.Resultado.getString("Nombre"));
            }
            bd.Consulta(new String[]{"*"}, "Clientes", "", "Nombre");
            while (bd.Resultado.next()) {
                cliente.getItems().addAll(bd.Resultado.getString("Nombre"));
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        tabla.setItems(Ventas.productos);
        no.setCellValueFactory(celda -> celda.getValue().no);
        nombre.setCellValueFactory(celda -> celda.getValue().nombre);
        precio.setCellValueFactory(celda -> celda.getValue().precio);
        cantidad.setCellValueFactory(celda -> celda.getValue().cantidad);
        subtotal.setCellValueFactory(celda -> celda.getValue().subtotal);
    }

    //funcion del boton cancelar
    @FXML
    private void Cancelar(){
        escenario.close();
    }

    //funcion del boton guardar
    @FXML
    private void Guardar(){
        if (!Validar()) return;
        SQLite bd = new SQLite();
        bd.Abrir();
        bd.Insertar("Ventas",
                new String[]{"Trabajador", "Cliente", "Fecha", "Total"},
                new String[]{trabajador.getValue(), cliente.getValue(), Fechas.Conversion(fecha.getValue()), total.getText()});
        try {
            String clave;
            bd.Consulta(new String[]{"*"}, "Ventas", "", "Clave DESC");
            bd.Resultado.next();
            clave = bd.Resultado.getString("Clave");
            for (Producto producto : Ventas.productos){
                bd.Insertar("Productos_Vendidos",
                        new String[]{"Ventas", "Productos", "Cantidad", "Subtotal"},
                        new String[]{clave, producto.nombre.get(), producto.cantidad.get(), producto.subtotal.get()});
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        bd.Cerrar();
        Ventas.Mostrar();
        new Dialogos(escenario).Mensaje("Ventas", "Venta Agregado");
        escenario.close();
    }

    //funcion para validar
    private boolean Validar(){
        if (trabajador.getValue() == null){
            new Dialogos(escenario).Error("Validacion", "Falta el Trabajador");
            return false;
        }
        if (cliente.getValue() == null){
            new Dialogos(escenario).Error("Validacion", "Falta el Cliente");
            return false;
        }
        if (fecha.getValue() == null){
            new Dialogos(escenario).Error("Validacion", "Falta la Fecha");
            return false;
        }
        if (Ventas.productos.size() == 0) {
            new Dialogos(escenario).Error("Validacion", "Falta los Productos");
            return false;
        }
        return true;
    }
    //funcion para el boton Agregar
    @FXML
    private void Agregar(){
        //cargar el dialogo agregar
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Ventas.class.getResource("view/productos.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle("Agregar Producto");
            dialogo.initModality(Modality.WINDOW_MODAL);
            dialogo.initOwner(Farmacia.escenario);
            dialogo.setResizable(false);
            dialogo.setScene(escena);
            productosControlador controlador = cargador.getController();
            controlador.escenario = dialogo;
            dialogo.showAndWait();
            Actualizar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //codigo del boton eliminar
    @FXML
    private void Eliminar(){
        Producto seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Ventas", "Selecciona un Producto");
        }
        else {
            if (new Dialogos(Farmacia.escenario).Pregunta("Eliminar", "Estas seguro?")){
                Ventas.productos.remove(tabla.getSelectionModel().getSelectedItem());
                Actualizar();
            }
        }
    }
    private void Actualizar(){
        Float suma = 0f;
        for (Producto producto : Ventas.productos){
            suma += Float.parseFloat(producto.subtotal.get());
        }
        total.setText(suma.toString());
    }
}
