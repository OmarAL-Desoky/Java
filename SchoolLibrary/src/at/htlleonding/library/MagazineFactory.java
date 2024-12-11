package at.htlleonding.library;

public class MagazineFactory implements MediumFactory{
    @Override
    public Medium createFromString(String line) {
        Medium result = null;

        try {
            String[] parts = line.split(";");
            String[] rawCourses = parts[5].split(",");
            Course[] courses = new Course[rawCourses.length];

            for(int i = 0; i < rawCourses.length; i++) {
                courses[i] = Course.valueOf(rawCourses[i].strip());
            }

            result = new Magazine(parts[0].strip(), parts[3].strip(), parts[4].strip(), courses, Integer.parseInt(parts[1].strip()), Integer.parseInt(parts[2].strip()));
        }
        catch (RuntimeException e) {
            throw new SchoolLibraryException("Invalid line length!", e);
        }

        return result;
    }
}
