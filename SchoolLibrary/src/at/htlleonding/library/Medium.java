package at.htlleonding.library;

import java.awt.print.Book;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public abstract class Medium implements Comparable<Medium>{
    private String catalogId;
    private String author;
    private String title;
    private boolean isBorrowed;
    private HashSet<Course> relevantCourses;

    public String getCatalogId() {
        return this.catalogId;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isBorrowed() {
        return this.isBorrowed;
    }

    public void setCatalogId(String catalogId) {
        if(!checkIfCatalogIdIsValid(catalogId)) {
            throw new IllegalArgumentException("Catalog id is invalid");
        }

        this.catalogId = catalogId;
    }

    public Medium(String catalogId, String author, String title, Course[] courses) {
        setCatalogId(catalogId);
        this.author = author;
        this.title = title;
        this.isBorrowed = false;

        this.relevantCourses = new HashSet<>();
        this.relevantCourses.addAll(List.of(courses));
    }

    public boolean borrow() {
        boolean result = false;
        if(!this.isBorrowed)  {
            this.isBorrowed = true;
            result = true;
        }

        return result;
    }

    public void giveBack() {
        if(!this.isBorrowed)  {
            throw new SchoolLibraryException("You must borrow a medium before you can return it!");
        }

        this.isBorrowed = false;
    }

    public boolean isRelevantForCourse(Course course) {
        return this.relevantCourses.contains(course);
    }

    public static boolean checkIfCatalogIdIsValid(String catalogId) {
        boolean result = false;

        if(catalogId.startsWith("LEO")) {
            int sum = 0;
            boolean containsdigits = false;

            for(int i = 3; i < catalogId.length() - 1 && !containsdigits; i++) {
                if('0' <= catalogId.charAt(i) && catalogId.charAt(i) <= '9') {
                    sum += ((catalogId.charAt(i) - '0') * i);
                }
                else {
                    containsdigits = true;
                }
            }

            if(!containsdigits) {
                result = ((sum % 10) == catalogId.charAt(catalogId.length() - 1) - '0');
            }
        }

        return result;
    }

    @Override
    public int compareTo(Medium o) {
        int result = this.author.compareTo(o.author);

        if(result == 0) {
            result = this.title.compareTo(o.title);
        }

        return result;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medium)) return false;

        Medium medium = (Medium) o;
        return Objects.equals(catalogId, medium.catalogId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(catalogId);
    }

    public String toString() {
        //LEO217783 Entwickler Magazin - The beauty of Python
        return String.format("%s %s - %s ", this.catalogId, this.author, this.title);
    }
}
