package no.ntnu.idatg2001;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AppController implements Initializable {

    //--------------------------Tableview------------------------------------------------------------------------

    /**
     * The Oblist.
     */
    ObservableList<Patient> oblist = FXCollections.observableArrayList();
    /**
     * The Oblist.
     */
    private PatientRegister patientRegister;

    @FXML
    private TableView<Patient> patientTableView;

    @FXML
    private TableColumn<Patient, String>
            firstNameTable,
            lastNameTable,
            socialSecTable;



    @FXML
    private TableView<Patient> patientTableView;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create the business logic by creating an instance of
        // PatientRegister and filling it with dummy data.
        this.patientRegister = new PatientRegister();
        this.fillRegisterWithDummyData();

        // Finalize the setup of the TableView
        firstNameTable.setMinWidth(184);
        firstNameTable.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        lastNameTable.setMinWidth(184);
        lastNameTable.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        socialSecTable.setMinWidth(184);
        socialSecTable.setCellValueFactory(new PropertyValueFactory<>("socialSecurityNumber"));

        // Populate the TableView by data from the patient register
        this.oblist =
                FXCollections.observableArrayList(this.patientRegister.getPatientList());
        this.patientTableView.setItems(this.oblist);

    }
        /**
         * Fills the register with dummy data.
         */
        private void fillRegisterWithDummyData() {
            this.patientRegister.addPatient(new Patient("Nina", "Teknologi", "060467 46356", "ADHD", "Bob Kåre"));
            this.patientRegister.addPatient(new Patient("Nanna", "Na", "120403 56435", "Dbeetus", "Kåre Konradi"));
            this.patientRegister.addPatient(new Patient("Nora", "Toriet", "281178 36524", "Nuclear cold","Fast Legen" ));
            this.patientRegister.addPatient(new Patient("Ove", "Ralt", "091045 35632", "The plague", "Kari Traad"));
        }

    /**
     * Displays an example of an alert (info) dialog. In this case an "about"
     * type of dialog.
     */
    @FXML
    public void showAboutDialog(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog - About");
        alert.setHeaderText("Patient Register");
        alert.setContentText("A brilliant application created by\n"
                + "(C)Niklas Leivestad\n"
                + "v1 2021-05-04");

        alert.showAndWait();
    }

}
