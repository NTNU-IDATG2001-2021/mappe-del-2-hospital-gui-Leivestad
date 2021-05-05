package no.ntnu.idatg2001;

import java.util.ArrayList;
import java.util.List;

public class PatientRegister {

    /**
     * The collection of Patient instances.
     */
    private final ArrayList<Patient> PatientReg;

    /**
     * Creates an instance of the patient register.
     */
    public PatientRegister() {
            this.PatientReg = new ArrayList<>();
        }

    /**
     * Returns the list of patients.
     *
     * @return the list of patients.
     */
    public List<Patient> getPatientList() {
            return this.PatientReg;
        }

    /**
     * Add a patient instance to the register.
     *
     * @param patient patient object
     */
     public void addPatient(Patient patient) {
            this.PatientReg.add(patient);
        }

    /**
     * Removes the patient provided by the parameter, from the register.
     * Returns <code>true</code> is remove was successful.
     *
     * @param patient the literature object to remove
     * @return <code>true</code> is remove was successful
     */
    public boolean remove(Patient patient) {
        return this.PatientReg.remove(patient);
    }

}
