package no.ntnu.idatg2001;
/**
 * Patient class for mappedel2 in the hospital assignment
 *
 * @author Niklas Leivestad
 *
 * @version 03.05.2021
 *
 */
public class Patient {
    String firstName;
    String lastName;
    String socialSecurityNumber;
    String diagnosis;
    String generalPractitioner;


    /**
     * Constructor
     *
     * @param firstName     first name of patient
     * @param lastName      last name of patient
     * @param socialSecurityNumber  social security number
     * @param diagnosis     diagnosis
     * @param generalPractitioner   the patient's general practitioner
     */

    public Patient(String firstName, String lastName, String socialSecurityNumber, String diagnosis, String generalPractitioner) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this. diagnosis = diagnosis;
        this.generalPractitioner = generalPractitioner;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }
}
