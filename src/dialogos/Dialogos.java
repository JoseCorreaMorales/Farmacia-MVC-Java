package dialogos;

import dialogos.view.dialogosControlador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialogos {
    //variables globales
    Stage propietario;

    //constructor
    public Dialogos(Stage propietario){ this.propietario = propietario; }

    //funcion para enviar un mensaje de informacion
    public void Mensaje(String titulo, String mensaje){
        //cargar el dialogo
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Dialogos.class.getResource("view/dialogos.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle(titulo);
            dialogo.initModality(Modality.WINDOW_MODAL);
            dialogo.initOwner(propietario);
            dialogo.setResizable(false);
            dialogo.setScene(escena);
            dialogosControlador controlador = cargador.getController();
            controlador.Preparar(dialogo, 1, mensaje);
            dialogo.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //funcion para enviar un mensaje de error
    public void Error(String titulo, String mensaje){
        //cargar el dialogo
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Dialogos.class.getResource("view/dialogos.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle(titulo);
            dialogo.initModality(Modality.WINDOW_MODAL);
            dialogo.initOwner(propietario);
            dialogo.setResizable(false);
            dialogo.setScene(escena);
            dialogosControlador controlador = cargador.getController();
            controlador.Preparar(dialogo, 3, mensaje);
            dialogo.showAndWait();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //funcion para enviar un mensaje de pregunta
    public boolean Pregunta(String titulo, String mensaje){
        boolean respuesta = false;
        //cargar el dialogo
        try {
            FXMLLoader cargador = new FXMLLoader();
            cargador.setLocation(Dialogos.class.getResource("view/dialogos.fxml"));
            AnchorPane pagina = cargador.load();
            Scene escena = new Scene(pagina);
            Stage dialogo = new Stage();
            //inicializar el dialogo
            dialogo.setTitle(titulo);
            dialogo.initModality(Modality.WINDOW_MODAL);
            dialogo.initOwner(propietario);
            dialogo.setResizable(false);
            dialogo.setScene(escena);
            dialogosControlador controlador = cargador.getController();
            controlador.Preparar(dialogo, 2, mensaje);
            dialogo.showAndWait();
            respuesta = controlador.respuesta;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return respuesta;
    }
}
