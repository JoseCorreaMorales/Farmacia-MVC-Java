package clientes.view;

import clientes.Clientes;
import clientes.model.Cliente;
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

public class clientesControlador {
    //variables globales
    @FXML
    private TableView<Cliente> tabla;
    @FXML
    private TableColumn<Cliente, String> clave;
    @FXML
    private TableColumn<Cliente,String> nombre;
    @FXML
    private TableColumn<Cliente,String> economia;
    @FXML
    private TableColumn<Cliente,String> enfermedades;
    @FXML
    private TableColumn<Cliente,String> alergias;
    @FXML
    private TableColumn<Cliente,String> edad;
    @FXML
    private TableColumn<Cliente,String> sintomas;
    @FXML
    private TableColumn<Cliente,String> embarazo;

    @FXML
    private TextField buscar;

    //funcion para inicializar la ventana
    @FXML
    private void initialize() {
        tabla.setItems(Clientes.lista);
        clave.setCellValueFactory(celda -> celda.getValue().clave);
        nombre.setCellValueFactory(celda -> celda.getValue().nombre);
        economia.setCellValueFactory(celda -> celda.getValue().economia);
        enfermedades.setCellValueFactory(celda -> celda.getValue().enfermedades);
        alergias.setCellValueFactory(celda -> celda.getValue().alergias);
        edad.setCellValueFactory(celda -> celda.getValue().edad);
        sintomas.setCellValueFactory(celda -> celda.getValue().sintomas);
        embarazo.setCellValueFactory(celda -> celda.getValue().embarazo);
    }

    //funcion para el boton Agregar
    @FXML
    private void Agregar(){
        //cargar el dialogo agregar
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Clientes.class.getResource("view/agregar.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle("Agregar Cliente");
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
            Clientes.lista.clear();
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(
                    new String[]{"*"},
                    "Clientes",
                    "Nombre LIKE '%" + buscar.getText() + "%'",
                    "Nombre");
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
                Clientes.lista.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //codigo del boton eliminar
    @FXML
    private void Eliminar(){
        Cliente seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Clientes", "Selecciona un Cliente");
        }
        else {
            if (new Dialogos(Farmacia.escenario).Pregunta("Eliminar", "Estas seguro?")){
                SQLite bd = new SQLite();
                bd.Abrir();
                bd.Eliminar("Clientes", "Clave=" + seleccionado.clave.get());
                bd.Cerrar();
                Clientes.Mostrar();
                new Dialogos(Farmacia.escenario).Mensaje("Clientes", "Cliente Eliminado");
            }
        }
    }

    //codigo del boton editar
    @FXML
    private void Editar(){
        Cliente seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Clientes", "Selecciona un Cliente");
        }
        else {
            //cargar el dialogo editar
            try {
                FXMLLoader cargador = new FXMLLoader();
                cargador.setLocation(Clientes.class.getResource("view/editar.fxml"));
                AnchorPane pagina = cargador.load();
                Scene escena = new Scene(pagina);
                Stage dialogo = new Stage();
                //inicializar el dialogo
                dialogo.setTitle("Editar Cliente");
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
