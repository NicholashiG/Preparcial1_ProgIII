package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Estudiante;
import model.Programa;
import model.Universidad;
import persistencia.ArchivoUtil;
import persistencia.Persistencia;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

public class ControllerMainView implements Initializable {

    ArrayList<Estudiante> estudiantes = new ArrayList<>();

    ArrayList<Programa> programas = new ArrayList<>();

    Universidad universidad = new Universidad(estudiantes, programas);

    @FXML
    TextField txtNombreEstudiante;
    @FXML
    TextField txtIdEstudiante;
    @FXML
    TextField txtNotaUno;
    @FXML
    TextField txtNotaDos;
    @FXML
    TextField txtNotaTres;
    @FXML
    Button btnGuardarEstudiante;
    @FXML
    TextField txtNombrePrograma;
    @FXML
    TextField txtCodigoPrograma;
    @FXML
    ChoiceBox<String> txtModalidad;
    @FXML
    Button btnGuardarPrograma;
    @FXML
    Button btnBuscarEstudiante;
    @FXML
    Button btnBuscarPrograma;
    @FXML
    Label lblInfoEstudiante;
    @FXML
    Label lblInfoPrograma;
    @FXML
    TextField txtBuscarID;
    @FXML
    TextField txtBuscarCodigo;


    // Obtiene los datos del estudiante de la GUI y lo crea
    // Así mismo, por medio de Persistencia se guardan en un .txt y
    // cada acción se guarda en un log para conocer todos los cambios
    @FXML
    private void guardarEstudiante(ActionEvent event) throws IOException {
        String nombre = txtNombreEstudiante.getText();
        String id = txtIdEstudiante.getText();
        String nota1 = txtNotaUno.getText();
        String nota2 = txtNotaDos.getText();
        String nota3 = txtNotaTres.getText();
        Estudiante estudiante = new Estudiante(nombre, id, nota1, nota2, nota3);
        universidad.getEstudiantes().add(estudiante);
        Persistencia.guardarEstudiantes(universidad.getEstudiantes());
        Persistencia.guardaRegistroLogEstudiantes("Nuevo estudiante ", 1, "Se guardó estudiante: "+estudiante);
    }


    // Obtiene los datos del programa desde la GUI y lo crea
    // Además, se guarda la universidad en un archivo xml para
    // así guardar los programas en tal archivo serializado
    @FXML
    private void guardarPrograma(ActionEvent event){
        String nombre = txtNombrePrograma.getText();
        String codigo = txtCodigoPrograma.getText();
        String modalidad = txtModalidad.getValue();
        Programa programa = new Programa(nombre, modalidad, codigo);
        universidad.getProgramas().add(programa);
        Persistencia.guardarRecursoProgramaXML(universidad);
    }

    // Obtiene el arreglo de estudiantes desde el archivo .txt y los pasa a
    // objetos para poder comparar con el ID buscado
    // En caso de que exista, por pantalla se mostrará la información de ese estudiante
    // de lo contrario, dirá que no existe
    @FXML
    private void buscarEstudiante(ActionEvent event) throws IOException {
        Boolean existe = false;
        String id = txtBuscarID.getText();
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        // Se añaden los estudiantes desde el archivo archivoEstudiantes.txt y
        // se guardan en el arreglo de estudiantes
        estudiantes.addAll(Persistencia.cargarEstudiantes());
        for (Estudiante estudiante: estudiantes) {
            if (estudiante.getId().equals(id)){
                existe = true;
                lblInfoEstudiante.setText(estudiante.toString());
            }
        }
        if (!existe){
            lblInfoEstudiante.setText("El estudiante buscado no existe");
        }
    }

    // Por medio del archivo serializado el cual devuelve el objeto universidad, se obtienen
    // los programas
    // Ya luego, busca si hay un programa con el código escrito el la GUI
    // En caso de encontrarlo, muestra toda la información del programa
    // en caso contrario, dirá que no existe
    @FXML
    private void buscarPrograma(ActionEvent event){
        Boolean existe = false;
        String codigo = txtBuscarCodigo.getText();
        ArrayList<Programa> programas = new ArrayList<>();
        // Se añaden los programas desde el XML hacia el arreglo de programas
        programas.addAll(Persistencia.cargarRecursoProgramaXML().getProgramas());
        for (Programa programa: programas) {
            if (programa.getCodigo().equals(codigo)){
                existe = true;
                lblInfoPrograma.setText(programa.toString());
            }
        }
        if (!existe){
            lblInfoPrograma.setText("El programa buscado no existe");
        }
    }



    // En este método initialize se inicializan las variables y lo que nececistemos antes de mostrar
    // el programa al usuario
    // Es decir, cargamos, en este caso, todos los datos ya existentes que teníamos guardados
    // en los archivos de persistencia para que así no se pierdan y se mantengan en cada instancia del
    // programa
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Se cargan las modalidades desde el archivo modalidades.properties
        txtModalidad.getItems().add(Persistencia.cargarModalidades().get(0));
        txtModalidad.getItems().add(Persistencia.cargarModalidades().get(1));
        // Se cargan los programas desde el archivo serializado de xml
        programas = Persistencia.cargarRecursoProgramaXML().getProgramas();
        // Se guardan los programas cargados en la universidad
        universidad.getProgramas().addAll(programas);
        // Se cargan los estudiantes desde la universidad
        try {
            universidad.getEstudiantes().addAll(Persistencia.cargarEstudiantes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
