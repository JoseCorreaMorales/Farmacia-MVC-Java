package ventas.view;

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
import ventas.Ventas;
import ventas.model.Venta;

public class ventasControlador {
    //variables globales
    @FXML
    private TableView<Venta> tabla;
    @FXML
    private TableColumn<Venta, String> clave;
    @FXML
    private TableColumn<Venta,String> trabajador;
    @FXML
    private TableColumn<Venta,String> cliente;
    @FXML
    private TableColumn<Venta,String> fecha;
    @FXML
    private TableColumn<Venta,String> total;

    @FXML
    private TextField buscar;

    //funcion para inicializar la ventana
    @FXML
    private void initialize() {
        tabla.setItems(Ventas.lista);
        clave.setCellValueFactory(celda -> celda.getValue().clave);
        trabajador.setCellValueFactory(celda -> celda.getValue().trabajador);
        cliente.setCellValueFactory(celda -> celda.getValue().cliente);
        fecha.setCellValueFactory(celda -> celda.getValue().fecha);
        total.setCellValueFactory(celda -> celda.getValue().total);
    }

    //funcion para el boton Agregar
    @FXML
    private void Agregar(){
        //cargar el dialogo agregar
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Ventas.class.getResource("view/agregar.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle("Agregar Venta");
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
            Ventas.lista.clear();
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(
                    new String[]{"*"},
                    "Ventas",
                    "Fecha LIKE '%" + buscar.getText() + "%'",
                    "Fecha");
            while (bd.Resultado.next()){
                Venta nuevo = new Venta();
                nuevo.clave.setValue(bd.Resultado.getString("Clave"));
                nuevo.trabajador.setValue(bd.Resultado.getString("Trabajador"));
                nuevo.cliente.setValue(bd.Resultado.getString("Cliente"));
                nuevo.fecha.setValue(bd.Resultado.getString("Fecha"));
                nuevo.total.setValue(bd.Resultado.getString("Total"));
                Ventas.lista.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //codigo del boton eliminar
    @FXML
    private void Eliminar(){
        Venta seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Ventas", "Selecciona un Venta");
        }
        else {
            if (new Dialogos(Farmacia.escenario).Pregunta("Eliminar", "Estas seguro?")){
                SQLite bd = new SQLite();
                bd.Abrir();
                bd.Eliminar("Ventas", "Clave=" + seleccionado.clave.get());
                bd.Eliminar("Productos_Vendidos", "Ventas=" + seleccionado.clave.get());
                bd.Cerrar();
                Ventas.Mostrar();
                new Dialogos(Farmacia.escenario).Mensaje("Ventas", "Venta Eliminado");
            }
        }
    }

    //codigo del boton editar
    @FXML
    private void Editar(){
        Venta seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Ventas", "Selecciona un Venta");
        }
        else {
            //cargar el dialogo editar
            try {
                FXMLLoader cargador = new FXMLLoader();
                cargador.setLocation(Ventas.class.getResource("view/editar.fxml"));
                AnchorPane pagina = cargador.load();
                Scene escena = new Scene(pagina);
                Stage dialogo = new Stage();
                //inicializar el dialogo
                dialogo.setTitle("Editar Venta");
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
