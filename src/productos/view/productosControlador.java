package productos.view;

import dialogos.Dialogos;
import farmacia.Farmacia;
import farmacia.util.SQLite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import productos.Productos;
import productos.model.Producto;

public class productosControlador {
    //variables globales
    @FXML
    private TableView<Producto> tabla;
    @FXML
    private TableColumn<Producto, String> clave;
    @FXML
    private TableColumn<Producto,String> nombre;
    @FXML
    private TableColumn<Producto,String> precio;
    @FXML
    private TableColumn<Producto,String> caducidad;
    @FXML
    private TableColumn<Producto,String> cantidad;
    @FXML
    private TableColumn<Producto,String> funcion;
    @FXML
    private TableColumn<Producto,String> dosis;
    @FXML
    private TableColumn<Producto,String> formula;
    @FXML
    private TableColumn<Producto,String> precauciones;
    @FXML
    private TableColumn<Producto,String> reaccion;

    @FXML
    private TextField buscar;

    //funcion para inicializar la ventana
    @FXML
    private void initialize() {
        tabla.setItems(Productos.lista);
        clave.setCellValueFactory(celda -> celda.getValue().clave);
        nombre.setCellValueFactory(celda -> celda.getValue().nombre);
        precio.setCellValueFactory(celda -> celda.getValue().precio);
        caducidad.setCellValueFactory(celda -> celda.getValue().caducidad);
        cantidad.setCellValueFactory(celda -> celda.getValue().cantidad);
        funcion.setCellValueFactory(celda -> celda.getValue().funcion);
        dosis.setCellValueFactory(celda -> celda.getValue().dosis);
        formula.setCellValueFactory(celda -> celda.getValue().formula);
        precauciones.setCellValueFactory(celda -> celda.getValue().precauciones);
        reaccion.setCellValueFactory(celda -> celda.getValue().reaccion);
    }

    //funcion para el boton Agregar
    @FXML
    private void Agregar(){
        //cargar el dialogo agregar
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Productos.class.getResource("view/agregar.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle("Agregar Producto");
            dialogo.initModality(Modality.WINDOW_MODAL);
            dialogo.initOwner(Farmacia.escenario);
            dialogo.setResizable(false);
            dialogo.setScene(escena);
            agregarControlador controlador = cargador.getController();
            controlador.escenario = dialogo;
            dialogo.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //funcion para el boton Buscar
    @FXML
    private void Buscar(){
        try {
            Productos.lista.clear();
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(
                    new String[]{"*"},
                    "Productos",
                    "Nombre LIKE '%" + buscar.getText() + "%'",
                    "Nombre");
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
                Productos.lista.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //codigo del boton eliminar
    @FXML
    private void Eliminar(){
        Producto seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Producto", "Selecciona un Producto");
        }
        else {
            if (new Dialogos(Farmacia.escenario).Pregunta("Eliminar", "Estas seguro?")){
                SQLite bd = new SQLite();
                bd.Abrir();
                bd.Eliminar("Productos", "Clave=" + seleccionado.clave.get());
                bd.Cerrar();
                Productos.Mostrar();
                new Dialogos(Farmacia.escenario).Mensaje("Productos", "Producto Eliminado");
            }
        }
    }

    //codigo del boton editar
    @FXML
    private void Editar(){
        Producto seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Productos", "Selecciona un Producto");
        }
        else {
            //cargar el dialogo editar
            try {
                FXMLLoader cargador = new FXMLLoader();
                cargador.setLocation(Productos.class.getResource("view/editar.fxml"));
                AnchorPane pagina = cargador.load();
                Scene escena = new Scene(pagina);
                Stage dialogo = new Stage();
                //inicializar el dialogo
                dialogo.setTitle("Editar Producto");
                dialogo.initModality(Modality.WINDOW_MODAL);
                dialogo.initOwner(Farmacia.escenario);
                dialogo.setResizable(false);
                dialogo.setScene(escena);
                editarControlador controlador = cargador.getController();
                controlador.escenario = dialogo;
                controlador.seleccionado = seleccionado;
                controlador.Llenar();
                dialogo.showAndWait();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
