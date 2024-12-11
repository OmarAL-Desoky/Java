package at.htlleonding.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class SchoolLibrary {
    private HashMap<String, Medium> media;

    public SchoolLibrary() {
        this.media = new HashMap<>();
    }

    public boolean addMedium(Medium medium) {
        boolean result = false;
        if(this.media.putIfAbsent(medium.getCatalogId(), medium) == null) {
            result = true;
        }

        return result;
    }

    public void addMediaFromFile(String path, MediumFactory factory) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            for (int i = 1; i < lines.size(); i++) {
                Medium medium = factory.createFromString(lines.get(i));
                this.media.putIfAbsent(medium.getCatalogId(), medium);
            }
        }
        catch(IOException e) {
            throw new SchoolLibraryException("Exception occured while loading file.", e);
        }
    }

    public int getMediaCount() {
        return this.media.size();
    }

    public Medium getMediumByCatalogId(String catalogID) {
        return this.media.get(catalogID);
    }

    public List<Medium> getAvailableMedia() {
        List<Medium> result = new ArrayList<>();
        Collection<Medium> values = this.media.values();

        for (Medium value : values) {
            if(!value.isBorrowed()) {
                result.add(value);
            }
        }
        Collections.sort(result);
        return result;
    }

    public List<Medium> getRelevantCourseMedia(Course course) {
        List<Medium> result = new ArrayList<>();
        Collection<Medium> values = this.media.values();

        for (Medium value : values) {
            if(value.isRelevantForCourse(course)) {
                result.add(value);
            }
        }
        Collections.sort(result);
        return result;
    }

    public List<Magazine> getNewestAvailableMagazines() {
        List<Magazine> result = new ArrayList<>();
        Collection<Medium> values = this.media.values();

        for (Medium value : values) {
            if(value instanceof Magazine) {
                if(!value.isBorrowed()) {
                    result.add((Magazine) value);
                }
            }
        }

        Collections.sort(result, new MagazineDateComparator());
        return result;
    }
}
