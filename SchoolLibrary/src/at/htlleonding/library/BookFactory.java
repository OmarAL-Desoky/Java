package at.htlleonding.library;

public class BookFactory implements MediumFactory{
    @Override
    public Medium createFromString(String line) {
        Medium result = null;
        //"LEO966854;Ishiguro Kazuo;Klara and the Sun;E,KIDS;320"
        try {
            String[] parts = line.split(";");
            String[] rawCourses = parts[3].split(",");
            Course[] courses = new Course[rawCourses.length];

            for(int i = 0; i < rawCourses.length; i++) {
                courses[i] = Course.valueOf(rawCourses[i].strip());
            }

            result = new Book(parts[0].strip(), parts[1].strip(), parts[2].strip(), courses, Integer.parseInt(parts[4].strip()));
        }
        catch (RuntimeException e) {
            throw new SchoolLibraryException("Invalid line length!", e);
        }

        return result;
    }
}
