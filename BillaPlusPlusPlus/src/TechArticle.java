import java.util.Arrays;
import java.util.HashSet;

public class TechArticle extends Article {
    private int warrantyMonths;

    public int getWarrantyMonths() {
        return this.warrantyMonths;
    }

    private void setWarrantyMonths(int warrantyMonths) {
        if(warrantyMonths < 6) {
            warrantyMonths = 6;
        }

        this.warrantyMonths = warrantyMonths;
    }

    public TechArticle(String name, int barcode, int quantity, int warrantyMonths) {
        super(name, barcode, quantity);
        setWarrantyMonths(warrantyMonths);
    }
}
