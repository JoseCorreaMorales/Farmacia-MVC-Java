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

public class editarControlador {
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

    public Venta seleccionado;

    //funcion para inicializar
    @FXML
    private void initialize() {
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
        bd.Modificar("Ventas",
                new String[]{"Trabajador", "Cliente", "Fecha", "Total"},
                new String[]{trabajador.getValue(), cliente.getValue(), Fechas.Conversion(fecha.getValue()), total.getText()},
                "Clave=" + seleccionado.clave.get());
        bd.Eliminar("Productos_Vendidos", "Ventas=" + seleccionado.clave.get());
        for (Producto producto : Ventas.productos){
            bd.Insertar("Productos_Vendidos",
                new String[] {"Ventas", "Productos", "Cantidad", "Subtotal"},
                new String[] {seleccionado.clave.get(), producto.nombre.get(), producto.cantidad.get(), producto.subtotal.get()});
        }
        bd.Cerrar();
        Ventas.Mostrar();
        new Dialogos(escenario).Mensaje("Ventas", "Venta Editada");
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
    //funcion de llenar
    public void Llenar(){
        trabajador.setValue(seleccionado.trabajador.get());
        cliente.setValue(seleccionado.cliente.get());
        fecha.setValue(Fechas.Conversion(seleccionado.fecha.get()));
        Ventas.productos.clear();
        try {
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(new String[]{"*"},
                    "Productos_Vendidos",
                    "Ventas=" + seleccionado.clave.get(),
                    "");
            while (bd.Resultado.next()){
                Producto nuevo = new Producto();
                nuevo.no.setValue((Ventas.productos.size() + 1) + "");
                nuevo.nombre.setValue(bd.Resultado.getString("Productos"));
                nuevo.precio.setValue((bd.Resultado.getFloat("Subtotal")/
                        bd.Resultado.getFloat("Cantidad")) + "");
                nuevo.cantidad.setValue(bd.Resultado.getString("Cantidad"));
                nuevo.subtotal.setValue(bd.Resultado.getString("Subtotal"));
                Ventas.productos.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Actualizar();
    }
}
