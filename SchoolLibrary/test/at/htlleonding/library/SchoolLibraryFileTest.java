package at.htlleonding.library;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SchoolLibraryFileTest {
    @Test
    void testLoadFromNonExistingFile() {
        SchoolLibrary library = new SchoolLibrary();
        SchoolLibraryException ex = assertThrows(SchoolLibraryException.class, () -> {
            library.addMediaFromFile("data/haxi.csv", new BookFactory());
        });

        assertEquals("Exception occured while loading file.", ex.getMessage());
        assertEquals(true, ex.getCause() instanceof IOException);
    }

    @Test
    void testLoadFromBooksFile() {
        SchoolLibrary library = new SchoolLibrary();
        library.addMediaFromFile("data/books.csv", new BookFactory());

        assertEquals(14, library.getMediaCount());

        Book book = (Book)library.getMediumByCatalogId("LEO796987");
        assertEquals("LEO796987 Frisch Max - Biedermann und die Brandstifter (96 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO834506");
        assertEquals("LEO834506 Lem Stanislaw - Robotermaerchen (148 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO966854");
        assertEquals("LEO966854 Ishiguro Kazuo - Klara and the Sun (320 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO734969");
        assertEquals("LEO734969 Kafka Franz - Der Prozess (208 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO151755");
        assertEquals("LEO151755 Kafka Franz - Die Verwandlung (70 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO561299");
        assertEquals("LEO561299 Lem Stanislaw - The Cyberiad (295 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO388587");
        assertEquals("LEO388587 Ullenboom Christian - Java ist auch eine Insel (1306 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO677107");
        assertEquals("LEO677107 Rost Vincent - Statistik endlich verstehen (118 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO378127");
        assertEquals("LEO378127 Rashid Tariq - Neuronale Netze selbst programmieren (232 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO365786");
        assertEquals("LEO365786 Martin Robert - Clean Code (475 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO307446");
        assertEquals("LEO307446 Steinbrecher Steffen - C# fuer Dummies (572 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO882880");
        assertEquals("LEO882880 Gamma Erich - Design Patterns (395 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO345500");
        assertEquals("LEO345500 Zegarelli Mark - Grundlagen der Mathematik fuer Dummies (400 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO896846");
        assertEquals("LEO896846 Orwell George - Nineteen Eighty-Four (384 pages)", book.toString());
    }

    @Test
    void testLoadFromMagazinesFile() {
        SchoolLibrary library = new SchoolLibrary();
        library.addMediaFromFile("data/magazines.csv", new MagazineFactory());

        assertEquals(13, library.getMediaCount());

        Magazine magazine = (Magazine)library.getMediumByCatalogId("LEO141713");
        assertEquals("LEO141713 Buchkultur - Heft 203 (2022/4)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO646662");
        assertEquals("LEO646662 Buchkultur - Heft 202 (2022/3)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO334967");
        assertEquals("LEO334967 Buchkultur - Heft 201 (2022/2)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO522750");
        assertEquals("LEO522750 Buchkultur - Heft 200 (Jubilaeum) (2022/1)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO753619");
        assertEquals("LEO753619 Buchkultur - Heft 199 (2021/12)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO322069");
        assertEquals("LEO322069 Ct Magazin fuer Computer Technik - KI-Kunst: In Sekunden zum grandiosen Bild (2022/11)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO883645");
        assertEquals("LEO883645 T3n Magazin - Planet A.I. – wie Tech uns im Klimawandel helfen kann (2022/6)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO373968");
        assertEquals("LEO373968 Entwickler Magazin - JavaScript auf dem Server (2022/9)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO217783");
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO287325");
        assertEquals("LEO287325 Entwickler Magazin - Machine Learning KI im Einsatz (2021/8)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO912474");
        assertEquals("LEO912474 Entwickler Magazin - RUST Systemnah programmieren (2022/5)", magazine.toString());
    }

    @Test
    void testLoadFromTwoFiles() {
        SchoolLibrary library = new SchoolLibrary();
        library.addMediaFromFile("data/books.csv", new BookFactory());
        library.addMediaFromFile("data/magazines.csv", new MagazineFactory());

        assertEquals(27, library.getMediaCount());

        Book book = (Book)library.getMediumByCatalogId("LEO796987");
        assertEquals("LEO796987 Frisch Max - Biedermann und die Brandstifter (96 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO834506");
        assertEquals("LEO834506 Lem Stanislaw - Robotermaerchen (148 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO966854");
        assertEquals("LEO966854 Ishiguro Kazuo - Klara and the Sun (320 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO734969");
        assertEquals("LEO734969 Kafka Franz - Der Prozess (208 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO151755");
        assertEquals("LEO151755 Kafka Franz - Die Verwandlung (70 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO561299");
        assertEquals("LEO561299 Lem Stanislaw - The Cyberiad (295 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO388587");
        assertEquals("LEO388587 Ullenboom Christian - Java ist auch eine Insel (1306 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO677107");
        assertEquals("LEO677107 Rost Vincent - Statistik endlich verstehen (118 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO378127");
        assertEquals("LEO378127 Rashid Tariq - Neuronale Netze selbst programmieren (232 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO365786");
        assertEquals("LEO365786 Martin Robert - Clean Code (475 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO307446");
        assertEquals("LEO307446 Steinbrecher Steffen - C# fuer Dummies (572 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO882880");
        assertEquals("LEO882880 Gamma Erich - Design Patterns (395 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO345500");
        assertEquals("LEO345500 Zegarelli Mark - Grundlagen der Mathematik fuer Dummies (400 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO896846");
        assertEquals("LEO896846 Orwell George - Nineteen Eighty-Four (384 pages)", book.toString());

        Magazine magazine = (Magazine)library.getMediumByCatalogId("LEO141713");
        assertEquals("LEO141713 Buchkultur - Heft 203 (2022/4)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO646662");
        assertEquals("LEO646662 Buchkultur - Heft 202 (2022/3)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO854388");
        assertEquals("LEO854388 Spektrum der Wissenschaft - Primzahlen: Die Stars der Mathematik (2019/6)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO334967");
        assertEquals("LEO334967 Buchkultur - Heft 201 (2022/2)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO522750");
        assertEquals("LEO522750 Buchkultur - Heft 200 (Jubilaeum) (2022/1)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO753619");
        assertEquals("LEO753619 Buchkultur - Heft 199 (2021/12)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO322069");
        assertEquals("LEO322069 Ct Magazin fuer Computer Technik - KI-Kunst: In Sekunden zum grandiosen Bild (2022/11)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO883645");
        assertEquals("LEO883645 T3n Magazin - Planet A.I. – wie Tech uns im Klimawandel helfen kann (2022/6)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO373968");
        assertEquals("LEO373968 Entwickler Magazin - JavaScript auf dem Server (2022/9)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO217783");
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO287325");
        assertEquals("LEO287325 Entwickler Magazin - Machine Learning KI im Einsatz (2021/8)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO912474");
        assertEquals("LEO912474 Entwickler Magazin - RUST Systemnah programmieren (2022/5)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO965126");
        assertEquals("LEO965126 Spektrum der Wissenschaft - Mathematische Spiele und Strategien (2021/1)", magazine.toString());
    }

    @Test
    void testAddManuallyAndFromFile() {
        SchoolLibrary library = new SchoolLibrary();
        Magazine magazineAlgorithmenZukunft = new Magazine("LEO937631", "Spektrum der Wissenschaft", "Algorithmen für die Zukunft: Von digitalen Helfern bis zum künstlichen Bewusstsein", new Course[]{Course.POSE, Course.SEW, Course.KIDS}, 2021, 3);
        library.addMedium(magazineAlgorithmenZukunft);
        library.addMediaFromFile("data/books.csv", new BookFactory());
        library.addMediaFromFile("data/magazines.csv", new MagazineFactory());
        Book bookFermat = new Book("LEO134752", "Singh Simon", "Fermats letzter Satz", new Course[]{Course.AM, Course.D}, 368);
        library.addMedium(bookFermat);

        assertEquals(29, library.getMediaCount());

        Book book = (Book)library.getMediumByCatalogId("LEO796987");
        assertEquals("LEO796987 Frisch Max - Biedermann und die Brandstifter (96 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO834506");
        assertEquals("LEO834506 Lem Stanislaw - Robotermaerchen (148 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO966854");
        assertEquals("LEO966854 Ishiguro Kazuo - Klara and the Sun (320 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO734969");
        assertEquals("LEO734969 Kafka Franz - Der Prozess (208 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO151755");
        assertEquals("LEO151755 Kafka Franz - Die Verwandlung (70 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO561299");
        assertEquals("LEO561299 Lem Stanislaw - The Cyberiad (295 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO388587");
        assertEquals("LEO388587 Ullenboom Christian - Java ist auch eine Insel (1306 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO677107");
        assertEquals("LEO677107 Rost Vincent - Statistik endlich verstehen (118 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO378127");
        assertEquals("LEO378127 Rashid Tariq - Neuronale Netze selbst programmieren (232 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO365786");
        assertEquals("LEO365786 Martin Robert - Clean Code (475 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO307446");
        assertEquals("LEO307446 Steinbrecher Steffen - C# fuer Dummies (572 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO882880");
        assertEquals("LEO882880 Gamma Erich - Design Patterns (395 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO345500");
        assertEquals("LEO345500 Zegarelli Mark - Grundlagen der Mathematik fuer Dummies (400 pages)", book.toString());
        book = (Book)library.getMediumByCatalogId("LEO896846");
        assertEquals("LEO896846 Orwell George - Nineteen Eighty-Four (384 pages)", book.toString());

        Magazine magazine = (Magazine)library.getMediumByCatalogId("LEO141713");
        assertEquals("LEO141713 Buchkultur - Heft 203 (2022/4)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO646662");
        assertEquals("LEO646662 Buchkultur - Heft 202 (2022/3)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO854388");
        assertEquals("LEO854388 Spektrum der Wissenschaft - Primzahlen: Die Stars der Mathematik (2019/6)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO334967");
        assertEquals("LEO334967 Buchkultur - Heft 201 (2022/2)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO522750");
        assertEquals("LEO522750 Buchkultur - Heft 200 (Jubilaeum) (2022/1)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO753619");
        assertEquals("LEO753619 Buchkultur - Heft 199 (2021/12)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO322069");
        assertEquals("LEO322069 Ct Magazin fuer Computer Technik - KI-Kunst: In Sekunden zum grandiosen Bild (2022/11)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO883645");
        assertEquals("LEO883645 T3n Magazin - Planet A.I. – wie Tech uns im Klimawandel helfen kann (2022/6)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO373968");
        assertEquals("LEO373968 Entwickler Magazin - JavaScript auf dem Server (2022/9)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO217783");
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO287325");
        assertEquals("LEO287325 Entwickler Magazin - Machine Learning KI im Einsatz (2021/8)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO912474");
        assertEquals("LEO912474 Entwickler Magazin - RUST Systemnah programmieren (2022/5)", magazine.toString());
        magazine = (Magazine)library.getMediumByCatalogId("LEO965126");
        assertEquals("LEO965126 Spektrum der Wissenschaft - Mathematische Spiele und Strategien (2021/1)", magazine.toString());

        assertEquals(magazineAlgorithmenZukunft, library.getMediumByCatalogId("LEO937631"));
        assertEquals(bookFermat, library.getMediumByCatalogId("LEO134752"));
    }

    @Test
    void testAddSameCatalogIdShouldNotBeOverwritten() {
        SchoolLibrary library = new SchoolLibrary();
        Magazine magazineCt = new Magazine("LEO322069", "Ct Magazine For Computer Technology", "AI Art", new Course[]{Course.KIDS}, 2022, 11);
        Book bookKlaraSonne = new Book("LEO966854", "Ishiguro Kazuo", "Klara und die Sonne", new Course[]{Course.KIDS, Course.D}, 352);

        library.addMedium(bookKlaraSonne);
        library.addMedium(magazineCt);

        library.addMediaFromFile("data/books.csv", new BookFactory());
        library.addMediaFromFile("data/magazines.csv", new MagazineFactory());

        assertEquals(magazineCt, library.getMediumByCatalogId("LEO322069"));
        assertEquals(bookKlaraSonne, library.getMediumByCatalogId("LEO966854"));
    }

    @Test
    void testGetAvailableMediaCorrectCount() {
        SchoolLibrary library = new SchoolLibrary();
        Magazine magazineAlgorithmenZukunft = new Magazine("LEO937631", "Spektrum der Wissenschaft", "Algorithmen für die Zukunft: Von digitalen Helfern bis zum künstlichen Bewusstsein", new Course[]{Course.POSE, Course.SEW, Course.KIDS}, 2021, 3);
        library.addMedium(magazineAlgorithmenZukunft);
        library.addMediaFromFile("data/books.csv", new BookFactory());
        library.addMediaFromFile("data/magazines.csv", new MagazineFactory());
        Book bookFermat = new Book("LEO134752", "Singh Simon", "Fermats letzter Satz", new Course[]{Course.AM, Course.D}, 368);
        library.addMedium(bookFermat);

        List<Medium> media = library.getAvailableMedia();
        assertEquals(29, media.size());

        Book bookNeuralNetworks = (Book)library.getMediumByCatalogId("LEO378127");
        bookNeuralNetworks.borrow();

        media = library.getAvailableMedia();
        assertEquals(28, media.size());

        Magazine magazineBeautyPython = (Magazine)library.getMediumByCatalogId("LEO217783");
        magazineBeautyPython.borrow();

        media = library.getAvailableMedia();
        assertEquals(27, media.size());

        bookNeuralNetworks.giveBack();

        media = library.getAvailableMedia();
        assertEquals(28, media.size());

        magazineBeautyPython.giveBack();

        media = library.getAvailableMedia();
        assertEquals(29, media.size());
    }

    @Test
    void testGetRelevantCourseMedia() {
        SchoolLibrary library = new SchoolLibrary();
        Magazine magazineAlgorithmenZukunft = new Magazine("LEO937631", "Spektrum der Wissenschaft", "Algorithmen für die Zukunft: Von digitalen Helfern bis zum künstlichen Bewusstsein", new Course[]{Course.POSE, Course.SEW, Course.KIDS}, 2021, 3);
        library.addMedium(magazineAlgorithmenZukunft);
        library.addMediaFromFile("data/books.csv", new BookFactory());
        library.addMediaFromFile("data/magazines.csv", new MagazineFactory());
        Book bookFermat = new Book("LEO134752", "Singh Simon", "Fermats letzter Satz", new Course[]{Course.AM, Course.D}, 368);
        library.addMedium(bookFermat);

        List<Medium> media = library.getRelevantCourseMedia(Course.AM);
        assertEquals(6, media.size());
        assertEquals("LEO378127 Rashid Tariq - Neuronale Netze selbst programmieren (232 pages)", media.get(0).toString());
        assertEquals("LEO677107 Rost Vincent - Statistik endlich verstehen (118 pages)", media.get(1).toString());
        assertEquals("LEO134752 Singh Simon - Fermats letzter Satz (368 pages)", media.get(2).toString());
        assertEquals("LEO965126 Spektrum der Wissenschaft - Mathematische Spiele und Strategien (2021/1)", media.get(3).toString());
        assertEquals("LEO854388 Spektrum der Wissenschaft - Primzahlen: Die Stars der Mathematik (2019/6)", media.get(4).toString());
        assertEquals("LEO345500 Zegarelli Mark - Grundlagen der Mathematik fuer Dummies (400 pages)", media.get(5).toString());

        media = library.getRelevantCourseMedia(Course.D);
        assertEquals(10, media.size());
        assertEquals("LEO753619 Buchkultur - Heft 199 (2021/12)", media.get(0).toString());
        assertEquals("LEO522750 Buchkultur - Heft 200 (Jubilaeum) (2022/1)", media.get(1).toString());
        assertEquals("LEO334967 Buchkultur - Heft 201 (2022/2)", media.get(2).toString());
        assertEquals("LEO646662 Buchkultur - Heft 202 (2022/3)", media.get(3).toString());
        assertEquals("LEO141713 Buchkultur - Heft 203 (2022/4)", media.get(4).toString());
        assertEquals("LEO796987 Frisch Max - Biedermann und die Brandstifter (96 pages)", media.get(5).toString());
        assertEquals("LEO734969 Kafka Franz - Der Prozess (208 pages)", media.get(6).toString());
        assertEquals("LEO151755 Kafka Franz - Die Verwandlung (70 pages)", media.get(7).toString());
        assertEquals("LEO834506 Lem Stanislaw - Robotermaerchen (148 pages)", media.get(8).toString());
        assertEquals("LEO134752 Singh Simon - Fermats letzter Satz (368 pages)", media.get(9).toString());

        //Borrowing should have no effect
        Book bookNeuralNetworks = (Book)library.getMediumByCatalogId("LEO378127");
        bookNeuralNetworks.borrow();
        Magazine magazineBeautyPython = (Magazine)library.getMediumByCatalogId("LEO217783");
        magazineBeautyPython.borrow();
        bookFermat.borrow();
        magazineAlgorithmenZukunft.borrow();

        media = library.getRelevantCourseMedia(Course.KIDS);
        assertEquals(10, media.size());
        assertEquals("LEO322069 Ct Magazin fuer Computer Technik - KI-Kunst: In Sekunden zum grandiosen Bild (2022/11)", media.get(0).toString());
        assertEquals("LEO287325 Entwickler Magazin - Machine Learning KI im Einsatz (2021/8)", media.get(1).toString());
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", media.get(2).toString());
        assertEquals("LEO966854 Ishiguro Kazuo - Klara and the Sun (320 pages)", media.get(3).toString());
        assertEquals("LEO834506 Lem Stanislaw - Robotermaerchen (148 pages)", media.get(4).toString());
        assertEquals("LEO561299 Lem Stanislaw - The Cyberiad (295 pages)", media.get(5).toString());
        assertEquals("LEO378127 Rashid Tariq - Neuronale Netze selbst programmieren (232 pages)", media.get(6).toString());
        assertEquals("LEO677107 Rost Vincent - Statistik endlich verstehen (118 pages)", media.get(7).toString());
        assertEquals("LEO937631 Spektrum der Wissenschaft - Algorithmen für die Zukunft: Von digitalen Helfern bis zum künstlichen Bewusstsein (2021/3)", media.get(8).toString());
        assertEquals("LEO883645 T3n Magazin - Planet A.I. – wie Tech uns im Klimawandel helfen kann (2022/6)", media.get(9).toString());

        media = library.getRelevantCourseMedia(Course.POSE);
        assertEquals(8, media.size());
        assertEquals("LEO373968 Entwickler Magazin - JavaScript auf dem Server (2022/9)", media.get(0).toString());
        assertEquals("LEO912474 Entwickler Magazin - RUST Systemnah programmieren (2022/5)", media.get(1).toString());
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", media.get(2).toString());
        assertEquals("LEO882880 Gamma Erich - Design Patterns (395 pages)", media.get(3).toString());
        assertEquals("LEO365786 Martin Robert - Clean Code (475 pages)", media.get(4).toString());
        assertEquals("LEO937631 Spektrum der Wissenschaft - Algorithmen für die Zukunft: Von digitalen Helfern bis zum künstlichen Bewusstsein (2021/3)", media.get(5).toString());
        assertEquals("LEO307446 Steinbrecher Steffen - C# fuer Dummies (572 pages)", media.get(6).toString());
        assertEquals("LEO388587 Ullenboom Christian - Java ist auch eine Insel (1306 pages)", media.get(7).toString());
    }

    @Test
    void testGetNewestAvailableMagazines() {
        SchoolLibrary library = new SchoolLibrary();
        Magazine magazineAlgorithmenZukunft = new Magazine("LEO937631", "Spektrum der Wissenschaft", "Algorithmen für die Zukunft: Von digitalen Helfern bis zum künstlichen Bewusstsein", new Course[]{Course.POSE, Course.SEW, Course.KIDS}, 2021, 3);
        library.addMedium(magazineAlgorithmenZukunft);
        library.addMediaFromFile("data/books.csv", new BookFactory());
        library.addMediaFromFile("data/magazines.csv", new MagazineFactory());
        Book bookFermat = new Book("LEO134752", "Singh Simon", "Fermats letzter Satz", new Course[]{Course.AM, Course.D}, 368);
        library.addMedium(bookFermat);

        List<Magazine> magazines = library.getNewestAvailableMagazines();
        assertEquals(14, magazines.size());
        assertEquals("LEO322069 Ct Magazin fuer Computer Technik - KI-Kunst: In Sekunden zum grandiosen Bild (2022/11)", magazines.get(0).toString());
        assertEquals("LEO373968 Entwickler Magazin - JavaScript auf dem Server (2022/9)", magazines.get(1).toString());
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", magazines.get(2).toString());
        assertEquals("LEO883645 T3n Magazin - Planet A.I. – wie Tech uns im Klimawandel helfen kann (2022/6)", magazines.get(3).toString());
        assertEquals("LEO912474 Entwickler Magazin - RUST Systemnah programmieren (2022/5)", magazines.get(4).toString());
        assertEquals("LEO141713 Buchkultur - Heft 203 (2022/4)", magazines.get(5).toString());
        assertEquals("LEO646662 Buchkultur - Heft 202 (2022/3)", magazines.get(6).toString());
        assertEquals("LEO334967 Buchkultur - Heft 201 (2022/2)", magazines.get(7).toString());
        assertEquals("LEO522750 Buchkultur - Heft 200 (Jubilaeum) (2022/1)", magazines.get(8).toString());
        assertEquals("LEO753619 Buchkultur - Heft 199 (2021/12)", magazines.get(9).toString());
        assertEquals("LEO287325 Entwickler Magazin - Machine Learning KI im Einsatz (2021/8)", magazines.get(10).toString());
        assertEquals("LEO937631 Spektrum der Wissenschaft - Algorithmen für die Zukunft: Von digitalen Helfern bis zum künstlichen Bewusstsein (2021/3)", magazines.get(11).toString());
        assertEquals("LEO965126 Spektrum der Wissenschaft - Mathematische Spiele und Strategien (2021/1)", magazines.get(12).toString());
        assertEquals("LEO854388 Spektrum der Wissenschaft - Primzahlen: Die Stars der Mathematik (2019/6)", magazines.get(13).toString());

        library.getMediumByCatalogId("LEO646662").borrow();
        library.getMediumByCatalogId("LEO322069").borrow();
        library.getMediumByCatalogId("LEO854388").borrow();
        library.getMediumByCatalogId("LEO937631").borrow();
        library.getMediumByCatalogId("LEO883645").borrow();

        magazines = library.getNewestAvailableMagazines();
        assertEquals(9, magazines.size());
        assertEquals("LEO373968 Entwickler Magazin - JavaScript auf dem Server (2022/9)", magazines.get(0).toString());
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", magazines.get(1).toString());
        assertEquals("LEO912474 Entwickler Magazin - RUST Systemnah programmieren (2022/5)", magazines.get(2).toString());
        assertEquals("LEO141713 Buchkultur - Heft 203 (2022/4)", magazines.get(3).toString());
        assertEquals("LEO334967 Buchkultur - Heft 201 (2022/2)", magazines.get(4).toString());
        assertEquals("LEO522750 Buchkultur - Heft 200 (Jubilaeum) (2022/1)", magazines.get(5).toString());
        assertEquals("LEO753619 Buchkultur - Heft 199 (2021/12)", magazines.get(6).toString());
        assertEquals("LEO287325 Entwickler Magazin - Machine Learning KI im Einsatz (2021/8)", magazines.get(7).toString());
        assertEquals("LEO965126 Spektrum der Wissenschaft - Mathematische Spiele und Strategien (2021/1)", magazines.get(8).toString());

        library.getMediumByCatalogId("LEO854388").giveBack();
        library.getMediumByCatalogId("LEO937631").giveBack();
        library.getMediumByCatalogId("LEO883645").giveBack();

        magazines = library.getNewestAvailableMagazines();
        assertEquals(12, magazines.size());
        assertEquals("LEO373968 Entwickler Magazin - JavaScript auf dem Server (2022/9)", magazines.get(0).toString());
        assertEquals("LEO217783 Entwickler Magazin - The beauty of Python (2022/7)", magazines.get(1).toString());
        assertEquals("LEO883645 T3n Magazin - Planet A.I. – wie Tech uns im Klimawandel helfen kann (2022/6)", magazines.get(2).toString());
        assertEquals("LEO912474 Entwickler Magazin - RUST Systemnah programmieren (2022/5)", magazines.get(3).toString());
        assertEquals("LEO141713 Buchkultur - Heft 203 (2022/4)", magazines.get(4).toString());
        assertEquals("LEO334967 Buchkultur - Heft 201 (2022/2)", magazines.get(5).toString());
        assertEquals("LEO522750 Buchkultur - Heft 200 (Jubilaeum) (2022/1)", magazines.get(6).toString());
        assertEquals("LEO753619 Buchkultur - Heft 199 (2021/12)", magazines.get(7).toString());
        assertEquals("LEO287325 Entwickler Magazin - Machine Learning KI im Einsatz (2021/8)", magazines.get(8).toString());
        assertEquals("LEO937631 Spektrum der Wissenschaft - Algorithmen für die Zukunft: Von digitalen Helfern bis zum künstlichen Bewusstsein (2021/3)", magazines.get(9).toString());
        assertEquals("LEO965126 Spektrum der Wissenschaft - Mathematische Spiele und Strategien (2021/1)", magazines.get(10).toString());
        assertEquals("LEO854388 Spektrum der Wissenschaft - Primzahlen: Die Stars der Mathematik (2019/6)", magazines.get(11).toString());
    }
}