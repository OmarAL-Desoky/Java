package at.htlleonding.library;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class MediumTest {
    @Test
    void testIsAbstract() {
        assertEquals(true, Modifier.isAbstract(Medium.class.getModifiers()));
    }

    @Test
    void testCheckIfCatalogIdIsValid() {
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO792792"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO580709"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO420144"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO295285"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO362609"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO860104"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO851749"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO782394"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO837183"));
        assertEquals(true, Medium.checkIfCatalogIdIsValid("LEO966981"));

        assertEquals(false, Medium.checkIfCatalogIdIsValid("LE1112230"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LEO58X709"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("HTL420144"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LEO295282"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LEO362604"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LEO860105"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LEO851747"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LO966981"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LEO782393"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LEO837182"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("LEO85!749"));
        assertEquals(false, Medium.checkIfCatalogIdIsValid("966981"));
    }
}