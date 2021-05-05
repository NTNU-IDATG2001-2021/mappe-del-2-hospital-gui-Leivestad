package no.ntnu.idatg2001;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

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
    private VBox rootContainer;

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
         * of Patient is created and added to the patient register provided.
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
     * Deletes the patient selected in the table. If no patient is
     * selected, nothing is deleted, and the user is informed that he/she must
     * select which patient to delete.
     *
     * @param actionEvent the event triggering the action
     */
    @FXML
    public void deletePatient(ActionEvent actionEvent) {
        Patient selectedPatient =
                this.patientTableView.getSelectionModel().getSelectedItem();

            if (showDeleteConfirmationDialog()) {
                this.patientRegister.remove(selectedPatient);
                this.updateOblist();
            }

    }

    /**
     * Edit the selected item.
     *
     * @param actionEvent the event triggering the action
     */
    public void editPatient(ActionEvent actionEvent) {
        Patient selectedPatient =
                this.patientTableView.getSelectionModel().getSelectedItem();

            if (selectedPatient instanceof Patient) {
                PatientDetailsDialog patientDialog = new PatientDetailsDialog(selectedPatient, true);
                patientDialog.showAndWait();

                this.updateOblist();
            }

    }

    /**
     * Updates the observable patient register by replacing the entire content
     * by the current content in the patient register.
     * Method to be called whenever changes are made to the patient register.
     */
    private void updateOblist() {
        this.oblist.setAll(this.patientRegister.getPatientList());
    }



    @FXML
    void exportToCSV(MouseEvent event) {
        if (exportSession(rootContainer)){
            updateOblist();
        }
    }

    @FXML
    void importFromCsv(MouseEvent mouseEvent) throws IOException{
        if (importSession(rootContainer)){
            updateOblist();
        }
    }



    public boolean exportSession(VBox rootContainer) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV Files(*.csv)", "*.csv"));
        File selectedFile = fileChooser.showSaveDialog(rootContainer.getScene().getWindow());
        if (selectedFile != null && !selectedFile.getName().equals(".csv")){
            if (!selectedFile.getName().endsWith(".csv")){
                selectedFile = new File(selectedFile.getAbsolutePath() + ".csv");
            }
        }
        return false;
    }

    /**
     *
     * @param rootContainer
     * @return
     * @throws IOException
     */
    public boolean importSession(VBox rootContainer) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("CSV Files(*.csv)", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(rootContainer.getScene().getWindow());
        if (!selectedFile.getName().endsWith(".csv")) {
            return false;
        }
        return true;
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

    // -----------------------------------------------------------
    //    DIALOGS
    // -----------------------------------------------------------

    /**
     * Displays a delete confirmation dialog. If the user confirms the delete,
     * <code>true</code> is returned.
     *
     * @return <code>true</code> if the user confirms the delete
     */
    private boolean showDeleteConfirmationDialog() {
        boolean deleteConfirmed = false;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete confirmation");
        alert.setHeaderText("Delete confirmation");
        alert.setContentText("Are you sure you want to delete this patient?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            deleteConfirmed = (result.get() == ButtonType.OK);
        }
        return deleteConfirmed;
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
