package no.ntnu.idatg2001;

import java.net.URL;
import java.util.Optional;
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
         * Display the input dialog to get input to create a new Patient.
         * If the user confirms creating a new patient, a new instance
         * of Patient is created and added to the literature register provided.
         *
         * @param actionEvent the event triggering the action
         */
        @FXML
        public void addPatient(ActionEvent actionEvent) {

            PatientDetailsDialog patientDetailsDialogDialog = new PatientDetailsDialog();

            Optional<Patient> result = patientDetailsDialogDialog.showAndWait();

            if (result.isPresent()) {
                Patient newPatient = result.get();
                this.patientRegister.addPatient(newPatient);
                this.updateOblist();
            }
        }




    /**
     * Updates the observable literature register by replacing the entire content
     * by the current content in the literature register.
     * Method to be called whenever changes are made to the literature register.
     */
    private void updateOblist() {
        this.oblist.setAll(this.patientRegister.getPatientList());
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
     * Displays alert, an informative dialog about the application.
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
