package farmacia.view;

import clientes.Clientes;
import farmacia.Farmacia;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import productos.Productos;
import trabajadores.Trabajadores;
import ventas.Ventas;

public class raizControlador {
    //funcion de menu cerrar
    @FXML
    public void Cerrar(){
        Farmacia.escenario.close();
    }
    //funcion de menu Clientes
    @FXML
    private void Clientes(){
        //cargar los clientes en la raiz
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Clientes.class.getResource("view/clientes.fxml"));
            AnchorPane pagina = cargador.load();
            Farmacia.raiz.setCenter(pagina);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Clientes.Mostrar();
    }
    //funcion de menu Productos
    @FXML
    private void Productos(){
        //cargar los productos en la raiz
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Productos.class.getResource("view/productos.fxml"));
            AnchorPane pagina = cargador.load();
            Farmacia.raiz.setCenter(pagina);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Productos.Mostrar();
    }
    //funcion de menu Trabajadores
    @FXML
    private void Trabajadores(){
        //cargar los trabajadores en la raiz
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Trabajadores.class.getResource("view/trabajadores.fxml"));
            AnchorPane pagina = cargador.load();
            Farmacia.raiz.setCenter(pagina);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Trabajadores.Mostrar();
    }
    //funcion de menu Ventas
    @FXML
    private void Ventas(){
        //cargar las ventas en la raiz
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Ventas.class.getResource("view/ventas.fxml"));
            AnchorPane pagina = cargador.load();
            Farmacia.raiz.setCenter(pagina);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Ventas.Mostrar();
    }
    //funcion de menu Acerca de
    @FXML
    private void Acerca(){
        //cargar el dialogo agregar

        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Farmacia.class.getResource("view/acerca.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle("Acerca de...");
            dialogo.initModality(Modality.WINDOW_MODAL);
            dialogo.initOwner(Farmacia.escenario);
            dialogo.setResizable(false);
            dialogo.setScene(escena);
            acercaControlador controlador = cargador.getController();
            controlador.escenario = dialogo;
            dialogo.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //funcion de menu Tutoriales de
    @FXML
    private void Tutoriales(){
        //cargar el dialogo agregar

        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Farmacia.class.getResource("view/tutoriales.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle("Tutoriales");
            dialogo.initModality(Modality.WINDOW_MODAL);
            dialogo.initOwner(Farmacia.escenario);
            dialogo.setResizable(false);
            dialogo.setScene(escena);
            tutorialesControlador controlador = cargador.getController();
            dialogo.showAndWait();
            if (controlador.reproductor != null) controlador.reproductor.dispose();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
