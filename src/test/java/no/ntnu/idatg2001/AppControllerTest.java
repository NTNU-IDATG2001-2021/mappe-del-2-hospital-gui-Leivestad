package no.ntnu.idatg2001;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing the AppController class and its business critical functions.
 */
class AppControllerTest {

    private PatientRegister patientReg;

    ArrayList<Patient> patientRegister = new ArrayList<>();

    ObservableList<Patient> oblist = FXCollections.observableArrayList();



    @Test
    private void updateOblist() {

        assertNotEquals(4, oblist.size());


        oblist.add( new Patient("Nina", "Teknologi", "060467 46356", "ADHD", "Bob Kåre"));
        oblist.add( new Patient("Nanna", "Na", "120403 56435", "Dbeetus", "Kåre Konradi"));
        oblist.add( new Patient("Nora", "Toriet", "281178 36524", "Nuclear cold","Fast Legen"));
        oblist.add( new Patient("Ove", "Ralt", "091045 35632", "The plague", "Kari Traad"));
        assertEquals(4, oblist.size());

        oblist.remove(1);
        assertEquals(3, oblist.size());
    }

}