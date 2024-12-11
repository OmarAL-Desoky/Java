package at.htlleonding.library;

public class Magazine extends Medium {
    private int month;
    private int year;

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public void setMonth(int month) {
        if(month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12");
        }

        this.month = month;
    }

    public Magazine(String catalogId, String author, String title, Course[] courses, int year, int month) {
        super(catalogId, author, title, courses);
        this.year = year;
        setMonth(month);
    }

    //"LEO217783 Entwickler Magazin - The beauty of Python (2022/7)"
    @Override
    public String toString() {
        return super.toString() + String.format("(%d/%d)", this.year, this.month);
    }
}
