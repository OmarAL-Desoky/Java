package at.htlleonding.library;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    @Test
    void testInheritance() {
        Book book = new Book("LEO380758", "Mcconnell Steve", "Code Complete: A Practical Handbook of Software Construction", new Course[]{Course.POSE, Course.SEW}, 960);

        assertEquals(true, book instanceof Medium);
        assertEquals(true, book instanceof Comparable<Medium>);
    }

    @Test
    void testConstructor() {
        Book book = new Book("LEO380758", "Mcconnell Steve", "Code Complete: A Practical Handbook of Software Construction", new Course[]{Course.POSE, Course.SEW}, 960);

        assertEquals("LEO380758", book.getCatalogId());
        assertEquals("Mcconnell Steve", book.getAuthor());
        assertEquals("Code Complete: A Practical Handbook of Software Construction", book.getTitle());
        assertEquals(960, book.getPageCount());
        assertEquals(false, book.isBorrowed());

        assertEquals(false, book.isRelevantForCourse(Course.AM));
        assertEquals(false, book.isRelevantForCourse(Course.D));
        assertEquals(false, book.isRelevantForCourse(Course.E));
        assertEquals(false, book.isRelevantForCourse(Course.KIDS));
        assertEquals(true, book.isRelevantForCourse(Course.POSE));
        assertEquals(true, book.isRelevantForCourse(Course.SEW));
    }

    @Test
    void testConstructorWithPageCountZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("LEO380758", "Mcconnell Steve", "Code Complete: A Practical Handbook of Software Construction", new Course[]{Course.POSE, Course.SEW}, 0);
        });
    }

    @Test
    void testConstructorWithNegativePageCount() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("LEO380758", "Mcconnell Steve", "Code Complete: A Practical Handbook of Software Construction", new Course[]{Course.POSE, Course.SEW}, -150);
        });
    }

    @Test
    void testConstructorWithInvalidCatalogId() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book book = new Book("LEO380757", "Mcconnell Steve", "Code Complete: A Practical Handbook of Software Construction", new Course[]{Course.POSE, Course.SEW}, 960);
        });
    }

    @Test
    void testBorrowAndGiveBack() {
        Book book = new Book("LEO380758", "Mcconnell Steve", "Code Complete: A Practical Handbook of Software Construction", new Course[]{Course.POSE, Course.SEW}, 960);

        assertEquals(false, book.isBorrowed());

        boolean isSuccess = book.borrow();

        assertEquals(true, isSuccess);
        assertEquals(true, book.isBorrowed());

        book.giveBack();

        assertEquals(false, book.isBorrowed());

        isSuccess = book.borrow();

        assertEquals(true, isSuccess);
        assertEquals(true, book.isBorrowed());

        book.giveBack();

        assertEquals(false, book.isBorrowed());
    }

    @Test
    void testBorrowBorrowed() {
        Book book = new Book("LEO380758", "Mcconnell Steve", "Code Complete: A Practical Handbook of Software Construction", new Course[]{Course.POSE, Course.SEW}, 960);

        assertEquals(false, book.isBorrowed());

        boolean isSuccess = book.borrow();

        assertEquals(true, isSuccess);
        assertEquals(true, book.isBorrowed());

        isSuccess = book.borrow();

        assertEquals(false, isSuccess);
        assertEquals(true, book.isBorrowed());

        book.giveBack();

        assertEquals(false, book.isBorrowed());

        isSuccess = book.borrow();

        assertEquals(true, isSuccess);
        assertEquals(true, book.isBorrowed());
    }

    @Test
    void testGiveBackNotBorrowed() {
        Book book = new Book("LEO380758", "Mcconnell Steve", "Code Complete: A Practical Handbook of Software Construction", new Course[]{Course.POSE, Course.SEW}, 960);

        SchoolLibraryException ex = assertThrows(SchoolLibraryException.class, () -> {
            book.giveBack();
        });

        assertEquals("You must borrow a medium before you can return it!", ex.getMessage());

        book.borrow();
        book.giveBack();

        ex = assertThrows(SchoolLibraryException.class, () -> {
            book.giveBack();
        });

        assertEquals("You must borrow a medium before you can return it!", ex.getMessage());
    }

    @Test
    void testEquality() {
        Book bookA = new Book("LEO671905", "Thomas David", "The Pragmatic Programmer", new Course[]{Course.POSE}, 352);
        Book bookB = new Book("LEO671905", "David Thomas", "The Pragmatic Programmer 2nd Edition", new Course[]{Course.SEW}, 370);
        Book bookC = new Book("LEO696340", "Thomas David", "The Pragmatic Programmer", new Course[]{Course.POSE}, 352);

        assertEquals(true, bookA.equals(bookA));
        assertEquals(true, bookA.equals(bookB));
        assertEquals(false, bookA.equals(bookC));

        assertEquals(true, bookB.equals(bookA));
        assertEquals(true, bookB.equals(bookB));
        assertEquals(false, bookB.equals(bookC));

        assertEquals(false, bookC.equals(bookA));
        assertEquals(false, bookC.equals(bookB));
        assertEquals(true, bookC.equals(bookC));
    }

    @Test
    void testComparable() {
        Book bookA = new Book("LEO882880", "Gamma Erich", "Design Patterns", new Course[]{Course.POSE, Course.SEW}, 395);
        Book bookB = new Book("LEO734969", "Kafka Franz", "Der Prozess", new Course[]{Course.D}, 208);
        Book bookC = new Book("LEO151755", "Kafka Franz", "Die Verwandlung", new Course[]{Course.D}, 70);
        Book bookD = new Book("LEO846548", "Lem Stanislaw", "Fables For Robots", new Course[]{Course.E, Course.KIDS}, 145);
        Book bookE = new Book("LEO298646", "Lem Stanislaw", "Solaris", new Course[]{Course.E}, 292);
        Book bookF = new Book("LEO378127", "Rashid Tariq", "Neuronale Netze selbst programmieren", new Course[]{Course.AM, Course.KIDS}, 232);
        Book bookG = new Book("LEO677107", "Rost Vincent", "Statistik endlich verstehen", new Course[]{Course.AM, Course.KIDS}, 118);

        List<Medium> media = new LinkedList<>();
        media.add(bookA);
        media.add(bookB);
        media.add(bookC);
        media.add(bookD);
        media.add(bookE);
        media.add(bookF);
        media.add(bookG);

        Collections.shuffle(media);

        Collections.sort(media);

        assertEquals(bookA, media.get(0));
        assertEquals(bookB, media.get(1));
        assertEquals(bookC, media.get(2));
        assertEquals(bookD, media.get(3));
        assertEquals(bookE, media.get(4));
        assertEquals(bookF, media.get(5));
        assertEquals(bookG, media.get(6));
    }

    @Test
    void testToString() {
        Book book = new Book("LEO298646", "Lem Stanislaw", "Solaris", new Course[]{Course.E}, 292);
        assertEquals("LEO298646 Lem Stanislaw - Solaris (292 pages)", book.toString());
    }
}