import java.util.Arrays;
import java.util.HashSet;

public class FoodArticle extends Article {
    private HashSet<AllergenType> allergens;

    public boolean addAllergen(AllergenType allergen) {
        return this.allergens.add(allergen);
    }

    public boolean containsAnyAllergen(AllergenType[] allergens) {
        boolean found = false;
        for(int i = 0; i < allergens.length && !found; i++) {
            found = this.allergens.contains(allergens[i]);
        }

        return found;
    }

    public boolean removeAllergen(AllergenType allergen) {
        return this.allergens.remove(allergen);
    }

    public FoodArticle(String name, int barcode, int quantity, AllergenType[] allergens) {
        super(name, barcode, quantity);
        this.allergens = new HashSet<>();
        this.allergens.addAll(Arrays.asList(allergens));
    }
}
