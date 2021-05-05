package no.ntnu.idatg2001;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing PatientRegister class's business critical functions
 */
class PatientRegisterTest {

    /**
     * Testing if the list adds Patients objects.
     */
    @Test
    void addPatient() {
        ArrayList<Patient> patientReg = new ArrayList<>();

        assertEquals(new ArrayList<>(), patientReg);
        assertNotEquals(4, patientReg.size());

        patientReg.add( new Patient("Nina", "Teknologi", "060467 46356", "ADHD", "Bob Kåre"));
        patientReg.add( new Patient("Nanna", "Na", "120403 56435", "Dbeetus", "Kåre Konradi"));
        patientReg.add( new Patient("Nora", "Toriet", "281178 36524", "Nuclear cold","Fast Legen"));
        patientReg.add( new Patient("Ove", "Ralt", "091045 35632", "The plague", "Kari Traad"));
        assertEquals(4, patientReg.size());

        patientReg.remove(1);
        assertEquals(3, patientReg.size());

    }
}