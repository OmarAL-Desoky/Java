import java.util.Objects;

public abstract class Article implements Comparable<Article> {
    private String name;
    private int barcode;
    private int quantity;

    public String getName() {
        return this.name;
    }

    public int getBarcode() {
        return this.barcode;
    }

    public int getQuantity() {
        return this.quantity;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setBarcode(int barcode) {
        if(!checkBarcode(barcode)) {
            throw new IllegalArgumentException("Barcode is not valid");
        }

        this.barcode = barcode;
    }

    private void setQuantity(int quantity) {
        if(quantity < 0) {
            quantity = 0;
        }

        this.quantity = quantity;
    }

    public Article(String name, int barcode, int quantity) {
        setName(name);
        setBarcode(barcode);
        setQuantity(quantity);
    }

    public int buy(int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Amount is not valid");
        }

        int actualAmount = amount;

        if(amount > this.quantity) {
            actualAmount = this.quantity;
        }

        this.quantity -= actualAmount;

        return actualAmount;
    }

    public int compareTo(Article other) {
        return this.name.compareTo(other.name);
    }

    public void restock(int amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Amount is not valid");
        }

        if(amount > 0) {
            this.quantity += amount;
        }
    }

    public static boolean checkBarcode(int barcode) {
        boolean isValid = false;
        String code = "" + barcode;
        int result = 0;

        for(int i = 0; i < code.length() - 1; i++) {
            int number = code.charAt(i) - '0';

            if(i % 2 == 0) {
                result += number * 1;
            }
            else {
                result += number * 3;
            }
        }

        result = 10 - (result % 10);
        isValid = (result == code.charAt(code.length() - 1) - '0');
        return isValid;
    }

    public String toString() {
        //#12345678 Raeuchertofu (100 in stock)
        String result = String.format("#%d %s (%d in stock)", this.barcode, this.name, this.quantity);
        return result;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;
        return barcode == article.barcode;
    }

    @Override
    public int hashCode() {
        return barcode;
    }
}
