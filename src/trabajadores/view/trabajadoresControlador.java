package trabajadores.view;

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
import trabajadores.Trabajadores;
import trabajadores.model.Trabajador;

public class trabajadoresControlador {
    //variables globales
    @FXML
    private TableView<Trabajador> tabla;
    @FXML
    private TableColumn<Trabajador, String> clave;
    @FXML
    private TableColumn<Trabajador,String> nombre;
    @FXML
    private TableColumn<Trabajador,String> genero;
    @FXML
    private TableColumn<Trabajador,String> edad;
    @FXML
    private TableColumn<Trabajador,String> estudios;
    @FXML
    private TableColumn<Trabajador,String> capacidad;
    @FXML
    private TableColumn<Trabajador,String> domicilio;
    @FXML
    private TableColumn<Trabajador,String> telefono;
    @FXML
    private TableColumn<Trabajador,String> responsable;
    @FXML
    private TableColumn<Trabajador,String> honesto;
    @FXML
    private TableColumn<Trabajador,String> comportamiento;

    @FXML
    private TextField buscar;

    //funcion para inicializar la ventana
    @FXML
    private void initialize() {
        tabla.setItems(Trabajadores.lista);
        clave.setCellValueFactory(celda -> celda.getValue().clave);
        nombre.setCellValueFactory(celda -> celda.getValue().nombre);
        genero.setCellValueFactory(celda -> celda.getValue().genero);
        edad.setCellValueFactory(celda -> celda.getValue().edad);
        estudios.setCellValueFactory(celda -> celda.getValue().estudios);
        capacidad.setCellValueFactory(celda -> celda.getValue().capacidad);
        domicilio.setCellValueFactory(celda -> celda.getValue().domicilio);
        telefono.setCellValueFactory(celda -> celda.getValue().telefono);
        responsable.setCellValueFactory(celda -> celda.getValue().responsable);
        honesto.setCellValueFactory(celda -> celda.getValue().honesto);
        comportamiento.setCellValueFactory(celda -> celda.getValue().comportamiento);
    }

    //funcion para el boton Agregar
    @FXML
    private void Agregar(){
        //cargar el dialogo agregar
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Trabajadores.class.getResource("view/agregar.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle("Agregar Trabajador");
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
            Trabajadores.lista.clear();
            SQLite bd = new SQLite();
            bd.Abrir();
            bd.Consulta(
                    new String[]{"*"},
                    "Trabajadores",
                    "Nombre LIKE '%" + buscar.getText() + "%'",
                    "Nombre");
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
                Trabajadores.lista.add(nuevo);
            }
            bd.Cerrar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //codigo del boton eliminar
    @FXML
    private void Eliminar(){
        Trabajador seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Trabajadores", "Selecciona un Trabajador");
        }
        else {
            if (new Dialogos(Farmacia.escenario).Pregunta("Eliminar", "Estas seguro?")){
                SQLite bd = new SQLite();
                bd.Abrir();
                bd.Eliminar("Trabajadores", "Clave=" + seleccionado.clave.get());
                bd.Cerrar();
                Trabajadores.Mostrar();
                new Dialogos(Farmacia.escenario).Mensaje("Trabajadores", "Trabajador Eliminado");
            }
        }
    }

    //codigo del boton editar
    @FXML
    private void Editar(){
        Trabajador seleccionado =  tabla.getSelectionModel().getSelectedItem();
        if (seleccionado == null){
            new Dialogos(Farmacia.escenario).Error("Trabajadores", "Selecciona un Trabajador");
        }
        else {
            //cargar el dialogo editar
            try {
                FXMLLoader cargador = new FXMLLoader();
                cargador.setLocation(Trabajadores.class.getResource("view/editar.fxml"));
                AnchorPane pagina = cargador.load();
                Scene escena = new Scene(pagina);
                Stage dialogo = new Stage();
                //inicializar el dialogo
                dialogo.setTitle("Editar Trabajador");
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
