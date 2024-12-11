import java.util.Comparator;

public class ArticleQuantityComparator implements Comparator<Article> {
    public int compare(Article a1, Article a2) {
        return a1.getQuantity() - a2.getQuantity();
    }
}
