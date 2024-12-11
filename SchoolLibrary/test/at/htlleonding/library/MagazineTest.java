package at.htlleonding.library;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class MagazineTest {
    @Test
    void testInheritance() {
        Magazine magazine = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);

        assertEquals(true, magazine instanceof Medium);
        //assertEquals(true, magazine instanceof Comparable<Medium>);

        MagazineDateComparator dateComparator = new MagazineDateComparator();

        //assertEquals(true, dateComparator instanceof Comparator<Magazine>);
    }

    @Test
    void testConstructor() {
        Magazine magazine = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);

        assertEquals("LEO322069", magazine.getCatalogId());
        assertEquals("Ct Magazin fuer Computer Technik", magazine.getAuthor());
        assertEquals("KI-Kunst: In Sekunden zum grandiosen Bild", magazine.getTitle());
        assertEquals(2022, magazine.getYear());
        assertEquals(11, magazine.getMonth());
        assertEquals(false, magazine.isBorrowed());

        assertEquals(false, magazine.isRelevantForCourse(Course.AM));
        assertEquals(false, magazine.isRelevantForCourse(Course.D));
        assertEquals(false, magazine.isRelevantForCourse(Course.E));
        assertEquals(true, magazine.isRelevantForCourse(Course.KIDS));
        assertEquals(false, magazine.isRelevantForCourse(Course.POSE));
        assertEquals(false, magazine.isRelevantForCourse(Course.SEW));
    }

    @Test
    void testConstructorWithMonthsTooLow() {
        assertThrows(IllegalArgumentException.class, () -> {
            Magazine magazine = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 0);
        });
    }

    @Test
    void testConstructorWithMonthsTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> {
            Magazine magazine = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 13);
        });
    }

    @Test
    void testConstructorWithInvalidCatalogId() {
        assertThrows(IllegalArgumentException.class, () -> {
            Magazine magazine = new Magazine("LEO322067", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);
        });
    }

    @Test
    void testBorrowAndGiveBack() {
        Magazine magazine = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);

        assertEquals(false, magazine.isBorrowed());

        boolean isSuccess = magazine.borrow();

        assertEquals(true, isSuccess);
        assertEquals(true, magazine.isBorrowed());

        magazine.giveBack();

        assertEquals(false, magazine.isBorrowed());

        isSuccess = magazine.borrow();

        assertEquals(true, isSuccess);
        assertEquals(true, magazine.isBorrowed());

        magazine.giveBack();

        assertEquals(false, magazine.isBorrowed());
    }

    @Test
    void testBorrowBorrowed() {
        Magazine magazine = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);

        assertEquals(false, magazine.isBorrowed());

        boolean isSuccess = magazine.borrow();

        assertEquals(true, isSuccess);
        assertEquals(true, magazine.isBorrowed());

        isSuccess = magazine.borrow();

        assertEquals(false, isSuccess);
        assertEquals(true, magazine.isBorrowed());

        magazine.giveBack();

        assertEquals(false, magazine.isBorrowed());

        isSuccess = magazine.borrow();

        assertEquals(true, isSuccess);
        assertEquals(true, magazine.isBorrowed());
    }

    @Test
    void testGiveBackNotBorrowed() {
        Magazine magazine = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);

        SchoolLibraryException ex = assertThrows(SchoolLibraryException.class, () -> {
            magazine.giveBack();
        });

        assertEquals("You must borrow a medium before you can return it!", ex.getMessage());

        magazine.borrow();
        magazine.giveBack();

        ex = assertThrows(SchoolLibraryException.class, () -> {
            magazine.giveBack();
        });

        assertEquals("You must borrow a medium before you can return it!", ex.getMessage());
    }

    @Test
    void testEquality() {
        Magazine magazineA = new Magazine("LEO217783", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);
        Magazine magazineB = new Magazine("LEO217783", "Developer Magazine", "Die Schoenheit von Python", new Course[]{Course.KIDS, Course.SEW}, 2021, 8);
        Magazine magazineC = new Magazine("LEO391719", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);

        assertEquals(true, magazineA.equals(magazineA));
        assertEquals(true, magazineA.equals(magazineB));
        assertEquals(false, magazineA.equals(magazineC));

        assertEquals(true, magazineB.equals(magazineA));
        assertEquals(true, magazineB.equals(magazineB));
        assertEquals(false, magazineB.equals(magazineC));

        assertEquals(false, magazineC.equals(magazineA));
        assertEquals(false, magazineC.equals(magazineB));
        assertEquals(true, magazineC.equals(magazineC));
    }

    @Test
    void testComparable() {
        Magazine magazineA = new Magazine("LEO848240", "Autoren Aktuell", "Alles ueber Berthold Brecht", new Course[]{Course.D}, 2022, 1);
        Magazine magazineB = new Magazine("LEO693061", "Autoren Aktuell", "Alles ueber Guenter Grass", new Course[]{Course.D}, 2022, 3);
        Magazine magazineC = new Magazine("LEO560263", "Autoren Aktuell", "Alles ueber Hermann Hesse", new Course[]{Course.D}, 2022, 9);
        Magazine magazineD = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);
        Magazine magazineE = new Magazine("LEO287325", "Entwickler Magazin", "Machine Learning KI im Einsatz", new Course[]{Course.KIDS}, 2021, 8);
        Magazine magazineF = new Magazine("LEO912474", "Entwickler Magazin", "RUST Systemnah programmieren", new Course[]{Course.SEW, Course.POSE}, 2022, 5);
        Magazine magazineG = new Magazine("LEO217783", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);
        Magazine magazineH = new Magazine("LEO883645", "T3n Magazin", "Planet A.I. – wie Tech uns im Klimawandel helfen kann", new Course[]{Course.KIDS, Course.POSE}, 2022, 6);

        List<Medium> media = new LinkedList<>();
        media.add(magazineA);
        media.add(magazineB);
        media.add(magazineC);
        media.add(magazineD);
        media.add(magazineE);
        media.add(magazineF);
        media.add(magazineG);
        media.add(magazineH);

        Collections.shuffle(media);

        Collections.sort(media);

        assertEquals(magazineA, media.get(0));
        assertEquals(magazineB, media.get(1));
        assertEquals(magazineC, media.get(2));
        assertEquals(magazineD, media.get(3));
        assertEquals(magazineE, media.get(4));
        assertEquals(magazineF, media.get(5));
        assertEquals(magazineG, media.get(6));
        assertEquals(magazineH, media.get(7));
    }

    @Test
    void testMagazineDateComparator() {
        Magazine magazineA = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);
        Magazine magazineB = new Magazine("LEO560263", "Autoren Aktuell", "Alles ueber Hermann Hesse", new Course[]{Course.D}, 2022, 9);
        Magazine magazineC = new Magazine("LEO217783", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);
        Magazine magazineD = new Magazine("LEO883645", "T3n Magazin", "Planet A.I. – wie Tech uns im Klimawandel helfen kann", new Course[]{Course.KIDS, Course.POSE}, 2022, 6);
        Magazine magazineE = new Magazine("LEO912474", "Entwickler Magazin", "RUST Systemnah programmieren", new Course[]{Course.SEW, Course.POSE}, 2022, 5);
        Magazine magazineF = new Magazine("LEO693061", "Autoren Aktuell", "Alles ueber Guenter Grass", new Course[]{Course.D}, 2022, 3);
        Magazine magazineG = new Magazine("LEO848240", "Autoren Aktuell", "Alles ueber Berthold Brecht", new Course[]{Course.D}, 2022, 1);
        Magazine magazineH = new Magazine("LEO287325", "Entwickler Magazin", "Machine Learning KI im Einsatz", new Course[]{Course.KIDS}, 2021, 8);

        List<Magazine> magazines = new LinkedList<>();
        magazines.add(magazineA);
        magazines.add(magazineB);
        magazines.add(magazineC);
        magazines.add(magazineD);
        magazines.add(magazineE);
        magazines.add(magazineF);
        magazines.add(magazineG);
        magazines.add(magazineH);

        Collections.shuffle(magazines);

        Collections.sort(magazines, new MagazineDateComparator());

        assertEquals(magazineA, magazines.get(0));
        assertEquals(magazineB, magazines.get(1));
        assertEquals(magazineC, magazines.get(2));
        assertEquals(magazineD, magazines.get(3));
        assertEquals(magazineE, magazines.get(4));
        assertEquals(magazineF, magazines.get(5));
        assertEquals(magazineG, magazines.get(6));
        assertEquals(magazineH, magazines.get(7));
    }

    @Test
    void testToString() {
        Magazine magazine = new Magazine("LEO217783", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", magazine.toString());
    }
}