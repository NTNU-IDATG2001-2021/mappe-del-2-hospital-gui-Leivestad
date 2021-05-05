package no.ntnu.idatg2001;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PatientDetailsDialog extends Dialog<Patient> {

    /**
     * The mode of the dialog. If the dialog is opened to edit an existing
     * Patient, the mode is set to <code>Mode.EDIT</code>. If the dialog is
     * opened to create a new Patient, the <code>Mode.NEW</code> is used.
     */
    public enum Mode {
        NEW, INFO, EDIT
    }

    /**
     * The mode of the dialog. NEW if new Patient, EDIT if edit existing
     * Patient.
     */
    private final Mode mode;

    /**
     * Holds the Patient instance to edit, if any.
     */
    private Patient existingPatient = null;

    /**
     * Creates an instance of the Patient details dialog to get information to
     * create a new instance of Patient.
     */
    public PatientDetailsDialog() {
        super();
        this.mode = Mode.NEW;
        createContent();

    }

    /**
     * Creates an instance of the PatientDetails dialog.
     *
     * @param patient the Patient instance to edit
     * @param editable  if set to <code>true</code>, the dialog will enable
     *                  editing of the fields in the dialog. if <code>false</code> the
     *                  information will be displayed in non-editable fields.
     */
    public PatientDetailsDialog(Patient patient, boolean editable) {
        super();
        if (editable) {
            this.mode = Mode.EDIT;
        } else {
            this.mode = Mode.INFO;
        }
        this.existingPatient = patient;
        createContent();
    }

    /**
     * Creates the content of the dialog.
     */
    private void createContent() {
        setTitle("Patient Details");

        // Set the button types.
        if (this.mode == Mode.INFO) {
            getDialogPane().getButtonTypes().addAll(ButtonType.OK);
        } else {
            getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        }

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField firstName = new TextField();
        firstName.setPromptText("First name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last name");

        TextField socialSecNoTxt = new TextField();
        socialSecNoTxt.setPromptText("Social security number");

        TextField diagnosis = new TextField();
        diagnosis.setPromptText("Diagnosis");

        TextField generalPractitioner = new TextField();
        generalPractitioner.setPromptText("General practitioner");

        // Fill inn data from the provided Patient, if not null.
        if ((mode == Mode.EDIT) || (mode == Mode.INFO)) {
            firstName.setText(existingPatient.getFirstName());
            lastName.setText(existingPatient.getLastName());
            socialSecNoTxt.setText(existingPatient.getSocialSecurityNumber());
            diagnosis.setText(existingPatient.getDiagnosis());
            generalPractitioner.setText(existingPatient.getGeneralPractitioner());
            // Set to non-editable if Mode.INFO
            if (mode == Mode.INFO) {
                firstName.setEditable(false);
                lastName.setEditable(false);
                socialSecNoTxt.setEditable(false);
                diagnosis.setEditable(false);
                generalPractitioner.setEditable(false);
            }

        }

        grid.add(new Label("First name:"), 0, 0);
        grid.add(firstName, 1, 0);
        grid.add(new Label("Last name:"), 0, 1);
        grid.add(lastName, 1, 1);
        grid.add(new Label("Social security number:"), 0, 2);
        grid.add(socialSecNoTxt, 1, 2);
        grid.add(new Label("Diagnosis:"), 0, 3);
        grid.add(diagnosis, 1, 3);
        grid.add(new Label("General practitioner:"), 0, 4);
        grid.add(generalPractitioner, 1, 4);

        getDialogPane().setContent(grid);

        // Convert the result to Patient-instance when the OK button is clicked.
        // Check out: https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html#setResultConverter-javafx.util.Callback-
        // and: https://docs.oracle.com/javase/8/javafx/api/javafx/util/Callback.html
        setResultConverter(
                (ButtonType button) -> {
                    Patient result = null;
                    if (button == ButtonType.OK) {
                        if (mode == Mode.NEW) {
                            result = new Patient(firstName.getText(), lastName.getText(), socialSecNoTxt.getText(), diagnosis.getText(), generalPractitioner.getText());
                        } else if (mode == Mode.EDIT) {
                            existingPatient.setFirstName(firstName.getText());
                            existingPatient.setLastName(lastName.getText());
                            existingPatient.setSocialSecurityNumber(socialSecNoTxt.getText());
                            existingPatient.setDiagnosis(diagnosis.getText());
                            existingPatient.setGeneralPractitioner(generalPractitioner.getText());
                            result = existingPatient;
                        }
                    }
                    return result;
                }
        );
    }

}
