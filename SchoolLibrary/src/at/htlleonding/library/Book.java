package at.htlleonding.library;

public class Book extends Medium{
    private int pageCount;

    public int getPageCount() {
        return this.pageCount;
    }

    private void setPageCount(int pageCount) {
        if (pageCount < 1) {
            throw new IllegalArgumentException("Page count cannot be less than 1");
        }
        this.pageCount = pageCount;
    }

    public Book(String catalogId, String author, String title, Course[] courses, int pageCount) {
        super(catalogId, author, title, courses);
        setPageCount(pageCount);
    }

    //LEO298646 Lem Stanislaw - Solaris (292 pages)
    @Override
    public String toString() {
        return super.toString() + String.format("(%d pages)", this.pageCount);
    }
}
