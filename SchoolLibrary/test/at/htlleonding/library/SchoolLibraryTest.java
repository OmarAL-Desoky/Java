package at.htlleonding.library;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolLibraryTest {
    @Test
    void testGetByNonExistingCatalogId() {
        SchoolLibrary library = new SchoolLibrary();

        assertEquals(null, library.getMediumByCatalogId("LEO402040"));
    }

    @Test
    void testAddAndGetByCatalogId() {
        Book bookJavaInsel = new Book("LEO388587", "Ullenboom Christian", "Java ist auch eine Insel", new Course[]{Course.SEW, Course.POSE}, 1306);
        Book bookKlara = new Book("LEO966854", "Ishiguro Kazuo", "Klara and the Sun", new Course[]{Course.KIDS, Course.E}, 320);
        Book bookRobotermaerchen = new Book("LEO834506", "Lem Stanislaw", "Robotermaerchen", new Course[]{Course.D, Course.KIDS}, 148);
        Magazine magazineBeautyPython = new Magazine("LEO217783", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);
        Magazine magazineKiKunst = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);

        SchoolLibrary library = new SchoolLibrary();

        assertEquals(0, library.getMediaCount());
        assertEquals(null, library.getMediumByCatalogId("LEO388587"));
        assertEquals(null, library.getMediumByCatalogId("LEO966854"));
        assertEquals(null, library.getMediumByCatalogId("LEO834506"));
        assertEquals(null, library.getMediumByCatalogId("LEO217783"));
        assertEquals(null, library.getMediumByCatalogId("LEO322069"));

        boolean isSuccess = library.addMedium(bookJavaInsel);

        assertEquals(true, isSuccess);
        assertEquals(1, library.getMediaCount());
        assertEquals(bookJavaInsel, library.getMediumByCatalogId("LEO388587"));
        assertEquals(null, library.getMediumByCatalogId("LEO966854"));
        assertEquals(null, library.getMediumByCatalogId("LEO834506"));
        assertEquals(null, library.getMediumByCatalogId("LEO217783"));
        assertEquals(null, library.getMediumByCatalogId("LEO322069"));

        isSuccess = library.addMedium(bookKlara);
        assertEquals(true, isSuccess);
        isSuccess = library.addMedium(bookRobotermaerchen);
        assertEquals(true, isSuccess);

        assertEquals(3, library.getMediaCount());
        assertEquals(bookJavaInsel, library.getMediumByCatalogId("LEO388587"));
        assertEquals(bookKlara, library.getMediumByCatalogId("LEO966854"));
        assertEquals(bookRobotermaerchen, library.getMediumByCatalogId("LEO834506"));
        assertEquals(null, library.getMediumByCatalogId("LEO217783"));
        assertEquals(null, library.getMediumByCatalogId("LEO322069"));

        isSuccess = library.addMedium(magazineBeautyPython);
        assertEquals(true, isSuccess);
        isSuccess = library.addMedium(magazineKiKunst);
        assertEquals(true, isSuccess);

        assertEquals(5, library.getMediaCount());
        assertEquals(bookJavaInsel, library.getMediumByCatalogId("LEO388587"));
        assertEquals(bookKlara, library.getMediumByCatalogId("LEO966854"));
        assertEquals(bookRobotermaerchen, library.getMediumByCatalogId("LEO834506"));
        assertEquals(magazineBeautyPython, library.getMediumByCatalogId("LEO217783"));
        assertEquals(magazineKiKunst, library.getMediumByCatalogId("LEO322069"));
    }

    @Test
    void testAddSameCatalogId() {
        Book bookKlaraSun = new Book("LEO966854", "Ishiguro Kazuo", "Klara and the Sun", new Course[]{Course.KIDS, Course.E}, 320);
        Book bookKlaraSonne = new Book("LEO966854", "Ishiguro Kazuo", "Klara und die Sonne", new Course[]{Course.KIDS, Course.D}, 352);

        SchoolLibrary library = new SchoolLibrary();

        boolean isSuccess = library.addMedium(bookKlaraSun);

        assertEquals(true, isSuccess);
        assertEquals(1, library.getMediaCount());
        assertEquals(bookKlaraSun, library.getMediumByCatalogId("LEO966854"));

        isSuccess = library.addMedium(bookKlaraSonne);

        assertEquals(false, isSuccess);
        assertEquals(1, library.getMediaCount());
        assertEquals(bookKlaraSun, library.getMediumByCatalogId("LEO966854"));
    }

    @Test
    void testGetAvailableMedia() {
        Book bookJavaInsel = new Book("LEO388587", "Ullenboom Christian", "Java ist auch eine Insel", new Course[]{Course.SEW, Course.POSE}, 1306);
        Book bookKlara = new Book("LEO966854", "Ishiguro Kazuo", "Klara and the Sun", new Course[]{Course.KIDS, Course.E}, 320);
        Book bookRobotermaerchen = new Book("LEO834506", "Lem Stanislaw", "Robotermaerchen", new Course[]{Course.D, Course.KIDS}, 148);
        Magazine magazineBeautyPython = new Magazine("LEO217783", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);
        Magazine magazinePlanetAi = new Magazine("LEO883645", "T3n Magazin", "Planet A.I. – wie Tech uns im Klimawandel helfen kann", new Course[]{Course.KIDS}, 2022, 6);

        SchoolLibrary library = new SchoolLibrary();

        List<Medium> media = library.getAvailableMedia();

        assertEquals(0, media.size());

        library.addMedium(bookJavaInsel);
        library.addMedium(bookKlara);
        library.addMedium(bookRobotermaerchen);
        library.addMedium(magazineBeautyPython);
        library.addMedium(magazinePlanetAi);

        media = library.getAvailableMedia();

        assertEquals(5, media.size());
        assertEquals(magazineBeautyPython, media.get(0));
        assertEquals(bookKlara, media.get(1));
        assertEquals(bookRobotermaerchen, media.get(2));
        assertEquals(magazinePlanetAi, media.get(3));
        assertEquals(bookJavaInsel, media.get(4));

        bookRobotermaerchen.borrow();

        media = library.getAvailableMedia();

        assertEquals(4, media.size());
        assertEquals(magazineBeautyPython, media.get(0));
        assertEquals(bookKlara, media.get(1));
        assertEquals(magazinePlanetAi, media.get(2));
        assertEquals(bookJavaInsel, media.get(3));

        magazineBeautyPython.borrow();

        media = library.getAvailableMedia();

        assertEquals(3, media.size());
        assertEquals(bookKlara, media.get(0));
        assertEquals(magazinePlanetAi, media.get(1));
        assertEquals(bookJavaInsel, media.get(2));

        bookRobotermaerchen.giveBack();

        media = library.getAvailableMedia();

        assertEquals(4, media.size());
        assertEquals(bookKlara, media.get(0));
        assertEquals(bookRobotermaerchen, media.get(1));
        assertEquals(magazinePlanetAi, media.get(2));
        assertEquals(bookJavaInsel, media.get(3));

        Book bookCleanCode = new Book("LEO365786", "Martin Robert", "Clean Code", new Course[]{Course.SEW, Course.POSE}, 475);
        library.addMedium(bookCleanCode);

        media = library.getAvailableMedia();

        assertEquals(5, media.size());
        assertEquals(bookKlara, media.get(0));
        assertEquals(bookRobotermaerchen, media.get(1));
        assertEquals(bookCleanCode, media.get(2));
        assertEquals(magazinePlanetAi, media.get(3));
        assertEquals(bookJavaInsel, media.get(4));
    }

    @Test
    void testGetRelevantCourseMedia() {
        Book bookJavaInsel = new Book("LEO388587", "Ullenboom Christian", "Java ist auch eine Insel", new Course[]{Course.SEW, Course.POSE}, 1306);
        Book bookKlara = new Book("LEO966854", "Ishiguro Kazuo", "Klara and the Sun", new Course[]{Course.KIDS, Course.E}, 320);
        Book bookRobotermaerchen = new Book("LEO834506", "Lem Stanislaw", "Robotermaerchen", new Course[]{Course.D, Course.KIDS}, 148);
        Magazine magazineBeautyPython = new Magazine("LEO217783", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);
        Magazine magazineKiKunst = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);
        Magazine magazineBuchkultur200 = new Magazine("LEO522750", "Buchkultur", "Heft 200 (Jubilaeum)", new Course[]{Course.D}, 2022, 1);

        SchoolLibrary library = new SchoolLibrary();

        List<Medium> media = library.getRelevantCourseMedia(Course.KIDS);

        assertEquals(0, media.size());

        library.addMedium(bookJavaInsel);
        library.addMedium(bookKlara);
        library.addMedium(bookRobotermaerchen);
        library.addMedium(magazineBeautyPython);
        library.addMedium(magazineKiKunst);
        library.addMedium(magazineBuchkultur200);

        media = library.getRelevantCourseMedia(Course.AM);

        assertEquals(0, media.size());

        media = library.getRelevantCourseMedia(Course.SEW);

        assertEquals(1, media.size());
        assertEquals(bookJavaInsel, media.get(0));

        media = library.getRelevantCourseMedia(Course.D);

        assertEquals(2, media.size());
        assertEquals(magazineBuchkultur200, media.get(0));
        assertEquals(bookRobotermaerchen, media.get(1));

        media = library.getRelevantCourseMedia(Course.KIDS);

        assertEquals(4, media.size());
        assertEquals(magazineKiKunst, media.get(0));
        assertEquals(magazineBeautyPython, media.get(1));
        assertEquals(bookKlara, media.get(2));
        assertEquals(bookRobotermaerchen, media.get(3));

        //Availability should have no effect!
        bookJavaInsel.borrow();
        bookRobotermaerchen.borrow();
        magazineBeautyPython.borrow();

        media = library.getRelevantCourseMedia(Course.POSE);

        assertEquals(2, media.size());
        assertEquals(magazineBeautyPython, media.get(0));
        assertEquals(bookJavaInsel, media.get(1));

        media = library.getRelevantCourseMedia(Course.KIDS);

        assertEquals(4, media.size());
        assertEquals(magazineKiKunst, media.get(0));
        assertEquals(magazineBeautyPython, media.get(1));
        assertEquals(bookKlara, media.get(2));
        assertEquals(bookRobotermaerchen, media.get(3));

        Book bookCleanCode = new Book("LEO365786", "Martin Robert", "Clean Code", new Course[]{Course.SEW, Course.POSE}, 475);
        library.addMedium(bookCleanCode);

        media = library.getRelevantCourseMedia(Course.SEW);

        assertEquals(2, media.size());
        assertEquals(bookCleanCode, media.get(0));
        assertEquals(bookJavaInsel, media.get(1));
    }

    @Test
    void testGetNewestAvailableMagazines() {
        Book bookJavaInsel = new Book("LEO388587", "Ullenboom Christian", "Java ist auch eine Insel", new Course[]{Course.SEW, Course.POSE}, 1306);
        Book bookKlara = new Book("LEO966854", "Ishiguro Kazuo", "Klara and the Sun", new Course[]{Course.KIDS, Course.E}, 320);
        Book bookRobotermaerchen = new Book("LEO834506", "Lem Stanislaw", "Robotermaerchen", new Course[]{Course.D, Course.KIDS}, 148);
        Magazine magazineBeautyPython = new Magazine("LEO217783", "Entwickler Magazin", "The beauty of Python", new Course[]{Course.KIDS, Course.POSE}, 2022, 7);
        Magazine magazineKiKunst = new Magazine("LEO322069", "Ct Magazin fuer Computer Technik", "KI-Kunst: In Sekunden zum grandiosen Bild", new Course[]{Course.KIDS}, 2022, 11);
        Magazine magazineBuchkultur200 = new Magazine("LEO522750", "Buchkultur", "Heft 200 (Jubilaeum)", new Course[]{Course.D}, 2022, 1);
        Magazine magazinePlanetAi = new Magazine("LEO883645", "T3n Magazin", "Planet A.I. – wie Tech uns im Klimawandel helfen kann", new Course[]{Course.KIDS}, 2022, 6);
        Magazine magazineBuchkultur199 = new Magazine("LEO753619", "Buchkultur", "Heft 199", new Course[]{Course.D}, 2021, 12);

        SchoolLibrary library = new SchoolLibrary();

        List<Magazine> magazines = library.getNewestAvailableMagazines();

        assertEquals(0, magazines.size());

        library.addMedium(bookJavaInsel);
        library.addMedium(bookKlara);
        library.addMedium(bookRobotermaerchen);
        library.addMedium(magazineBeautyPython);
        library.addMedium(magazineKiKunst);
        library.addMedium(magazineBuchkultur200);
        library.addMedium(magazinePlanetAi);
        library.addMedium(magazineBuchkultur199);

        magazines = library.getNewestAvailableMagazines();

        assertEquals(5, magazines.size());
        assertEquals(magazineKiKunst, magazines.get(0));
        assertEquals(magazineBeautyPython, magazines.get(1));
        assertEquals(magazinePlanetAi, magazines.get(2));
        assertEquals(magazineBuchkultur200, magazines.get(3));
        assertEquals(magazineBuchkultur199, magazines.get(4));

        magazineBeautyPython.borrow();
        magazineBuchkultur199.borrow();

        magazines = library.getNewestAvailableMagazines();

        assertEquals(3, magazines.size());
        assertEquals(magazineKiKunst, magazines.get(0));
        assertEquals(magazinePlanetAi, magazines.get(1));
        assertEquals(magazineBuchkultur200, magazines.get(2));

        Book bookCleanCode = new Book("LEO365786", "Martin Robert", "Clean Code", new Course[]{Course.SEW, Course.POSE}, 475);
        library.addMedium(bookCleanCode);
        Magazine magazineRust = new Magazine("LEO912474", "Entwickler Magazin", "RUST Systemnah programmieren", new Course[]{Course.SEW, Course.POSE}, 2022, 5);
        library.addMedium(magazineRust);

        magazines = library.getNewestAvailableMagazines();

        assertEquals(4, magazines.size());
        assertEquals(magazineKiKunst, magazines.get(0));
        assertEquals(magazinePlanetAi, magazines.get(1));
        assertEquals(magazineRust, magazines.get(2));
        assertEquals(magazineBuchkultur200, magazines.get(3));

        magazineBuchkultur199.giveBack();

        magazines = library.getNewestAvailableMagazines();

        assertEquals(5, magazines.size());
        assertEquals(magazineKiKunst, magazines.get(0));
        assertEquals(magazinePlanetAi, magazines.get(1));
        assertEquals(magazineRust, magazines.get(2));
        assertEquals(magazineBuchkultur200, magazines.get(3));
        assertEquals(magazineBuchkultur199, magazines.get(4));

        magazineBeautyPython.giveBack();

        magazines = library.getNewestAvailableMagazines();

        assertEquals(6, magazines.size());
        assertEquals(magazineKiKunst, magazines.get(0));
        assertEquals(magazineBeautyPython, magazines.get(1));
        assertEquals(magazinePlanetAi, magazines.get(2));
        assertEquals(magazineRust, magazines.get(3));
        assertEquals(magazineBuchkultur200, magazines.get(4));
        assertEquals(magazineBuchkultur199, magazines.get(5));
    }
}