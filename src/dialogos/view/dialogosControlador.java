package dialogos.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class dialogosControlador {
    //variables gloales
    @FXML
    private Button si;
    @FXML
    private Button no;
    @FXML
    private ImageView pregunta;
    @FXML
    private Label mensaje;
    @FXML
    private ImageView informacion;
    @FXML
    private ImageView error;
    @FXML
    private Button aceptar;

    public boolean respuesta = false;
    private Stage escenario;

    //funcion para el boton Si
    @FXML
    private void Si(){
        respuesta = true;
        escenario.close();
    }
    //funcion para el boton No
    @FXML
    private void No(){
        escenario.close();
    }
    //funcion para el boton Aceptar
    @FXML
    private void Aceptar(){
        escenario.close();
    }
    //funcion para preparar el dialogo
    public void Preparar(Stage escenario, int tipo, String mensaje){
        this.escenario = escenario;
        this.mensaje.setText(mensaje);
        switch (tipo){
            //informacion
            case 1:
                informacion.setVisible(true);
                pregunta.setVisible(false);
                error.setVisible(false);
                si.setVisible(false);
                no.setVisible(false);
                aceptar.setVisible(true);
                break;
            //pregunta
            case 2:
                informacion.setVisible(false);
                pregunta.setVisible(true);
                error.setVisible(false);
                si.setVisible(true);
                no.setVisible(true);
                aceptar.setVisible(false);
                break;
            //error
            case 3:
                informacion.setVisible(false);
                pregunta.setVisible(false);
                error.setVisible(true);
                si.setVisible(false);
                no.setVisible(false);
                aceptar.setVisible(true);
        }
    }
}
