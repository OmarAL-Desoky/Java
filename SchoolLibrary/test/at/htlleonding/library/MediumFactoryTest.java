package at.htlleonding.library;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

class MediumFactoryTest {
    @Test
    void testInterfaceAndInheritance() {
        assertEquals(true, Modifier.isInterface(MediumFactory.class.getModifiers()));

        BookFactory bookFactory = new BookFactory();
        assertEquals(true, bookFactory instanceof MediumFactory);

        MagazineFactory magazineFactory = new MagazineFactory();
        assertEquals(true, magazineFactory instanceof MediumFactory);
    }

    @Test
    void testBookFactoryLineTooShort() {
        SchoolLibraryException ex = assertThrows(SchoolLibraryException.class, () -> {
            BookFactory bookFactory = new BookFactory();
            bookFactory.createFromString("LEO966854;Ishiguro Kazuo;Klara and the Sun;320");
        });

        assertEquals("Invalid line length!", ex.getMessage());
    }

    @Test
    void testBookFactoryLineTooLong() {
        SchoolLibraryException ex = assertThrows(SchoolLibraryException.class, () -> {
            BookFactory bookFactory = new BookFactory();
            bookFactory.createFromString("LEO966854;Ishiguro Kazuo;Klara and the Sun;Faber & Faber;E,KIDS;320");
        });

        assertEquals("Invalid line length!", ex.getMessage());
    }

    @Test
    void testBookFactoryValidLine() {
        BookFactory bookFactory = new BookFactory();
        Medium medium = bookFactory.createFromString("LEO966854;Ishiguro Kazuo;Klara and the Sun;E,KIDS;320");

        assertEquals(true, medium instanceof Book);

        Book book = (Book)medium;

        assertEquals("LEO966854", book.getCatalogId());
        assertEquals("Ishiguro Kazuo", book.getAuthor());
        assertEquals("Klara and the Sun", book.getTitle());
        assertEquals(320, book.getPageCount());
        assertEquals(false, book.isBorrowed());

        assertEquals(false, book.isRelevantForCourse(Course.AM));
        assertEquals(false, book.isRelevantForCourse(Course.D));
        assertEquals(true, book.isRelevantForCourse(Course.E));
        assertEquals(true, book.isRelevantForCourse(Course.KIDS));
        assertEquals(false, book.isRelevantForCourse(Course.POSE));
        assertEquals(false, book.isRelevantForCourse(Course.SEW));
    }

    @Test
    void testBookFactoryValidLineWithWhitespaces() {
        BookFactory bookFactory = new BookFactory();
        Medium medium = bookFactory.createFromString("   LEO796987 ; Frisch Max   ;  Biedermann und die Brandstifter ;    D;  96 ");

        assertEquals(true, medium instanceof Book);

        Book book = (Book)medium;

        assertEquals("LEO796987", book.getCatalogId());
        assertEquals("Frisch Max", book.getAuthor());
        assertEquals("Biedermann und die Brandstifter", book.getTitle());
        assertEquals(96, book.getPageCount());
        assertEquals(false, book.isBorrowed());

        assertEquals(false, book.isRelevantForCourse(Course.AM));
        assertEquals(true, book.isRelevantForCourse(Course.D));
        assertEquals(false, book.isRelevantForCourse(Course.E));
        assertEquals(false, book.isRelevantForCourse(Course.KIDS));
        assertEquals(false, book.isRelevantForCourse(Course.POSE));
        assertEquals(false, book.isRelevantForCourse(Course.SEW));
    }

    @Test
    void testMagazineFactoryLineTooShort() {
        SchoolLibraryException ex = assertThrows(SchoolLibraryException.class, () -> {
            MagazineFactory magazineFactory = new MagazineFactory();
            magazineFactory.createFromString("LEO217783;2022;Entwickler Magazin;The beauty of Python;POSE,SEW,KIDS");
        });

        assertEquals("Invalid line length!", ex.getMessage());
    }

    @Test
    void testMagazineFactoryLineTooLong() {
        SchoolLibraryException ex = assertThrows(SchoolLibraryException.class, () -> {
            MagazineFactory magazineFactory = new MagazineFactory();
            magazineFactory.createFromString("LEO217783;2022;7;5;Entwickler Magazin;The beauty of Python;POSE,SEW,KIDS");
        });

        assertEquals("Invalid line length!", ex.getMessage());
    }

    @Test
    void testMagazineFactoryValidLine() {
        MagazineFactory magazineFactory = new MagazineFactory();
        Medium medium = magazineFactory.createFromString("LEO217783;2022;7;Entwickler Magazin;The beauty of Python;POSE,SEW,KIDS");

        assertEquals(true, medium instanceof Magazine);

        Magazine magazine = (Magazine) medium;

        assertEquals("LEO217783", magazine.getCatalogId());
        assertEquals(2022, magazine.getYear());
        assertEquals(7, magazine.getMonth());
        assertEquals("Entwickler Magazin", magazine.getAuthor());
        assertEquals("The beauty of Python", magazine.getTitle());
        assertEquals(false, magazine.isBorrowed());

        assertEquals(false, magazine.isRelevantForCourse(Course.AM));
        assertEquals(false, magazine.isRelevantForCourse(Course.D));
        assertEquals(false, magazine.isRelevantForCourse(Course.E));
        assertEquals(true, magazine.isRelevantForCourse(Course.KIDS));
        assertEquals(true, magazine.isRelevantForCourse(Course.POSE));
        assertEquals(true, magazine.isRelevantForCourse(Course.SEW));
    }

    @Test
    void testMagazineFactoryValidLineWithWhitespaces() {
        MagazineFactory magazineFactory = new MagazineFactory();
        Medium medium = magazineFactory.createFromString("   LEO522750  ;    2022      ; 1  ;   Buchkultur;Heft 200 (Jubilaeum)   ;   D ");

        assertEquals(true, medium instanceof Magazine);

        Magazine magazine = (Magazine) medium;

        assertEquals("LEO522750", magazine.getCatalogId());
        assertEquals(2022, magazine.getYear());
        assertEquals(1, magazine.getMonth());
        assertEquals("Buchkultur", magazine.getAuthor());
        assertEquals("Heft 200 (Jubilaeum)", magazine.getTitle());
        assertEquals(false, magazine.isBorrowed());

        assertEquals(false, magazine.isRelevantForCourse(Course.AM));
        assertEquals(true, magazine.isRelevantForCourse(Course.D));
        assertEquals(false, magazine.isRelevantForCourse(Course.E));
        assertEquals(false, magazine.isRelevantForCourse(Course.KIDS));
        assertEquals(false, magazine.isRelevantForCourse(Course.POSE));
        assertEquals(false, magazine.isRelevantForCourse(Course.SEW));
    }
}