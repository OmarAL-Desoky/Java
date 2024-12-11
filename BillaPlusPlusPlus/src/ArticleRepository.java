import com.sun.jdi.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ArticleRepository {
    private Map<Integer, Article> articles;

    public ArticleRepository() {
        this.articles = new HashMap<Integer, Article>();
    }

    public boolean addArticle(Article article) {
        boolean result = false;

        if(article.getQuantity() > 0) {
            if(this.articles.putIfAbsent(article.getBarcode(), article) == null) {
                result = true;
            }
        }

        return result;
    }

    public void addArticlesFromFile(String path, ArticleFactory articleFactory) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));

            for (int i = 1; i < lines.size(); i++) {
                addArticle(articleFactory.createFromString(lines.get(i)));
            }
        }
        catch(IOException e) {
            throw new ArticleManagementException("Exception occured while loading file!", e);
        }
    }

    public Article getArticleByBarcode(int barcode) {
        Article result = null;

        for(Article article : this.articles.values()) {
            if(article.getBarcode() == barcode) {
                result = article;
            }
        }

        return result;
    }

    public List<Article> getArticlesWithQuantityBelow(int quantity) {
        List<Article> result = new ArrayList<Article>();

        for(Article article : this.articles.values()) {
            if(article.getQuantity() < quantity) {
                result.add(article);
            }
        }

        result.sort(new ArticleQuantityComparator());
        return result;
    }

    public List<FoodArticle> getFoodWithoutAllergens(AllergenType[] allergens) {
        List<FoodArticle> result = new ArrayList<FoodArticle>();

        for(Article article : this.articles.values()) {
            if(article instanceof FoodArticle) {
                FoodArticle foodArticle = (FoodArticle) article;

                if(!foodArticle.containsAnyAllergen(allergens)) {
                    result.add(foodArticle);
                }
            }
        }

        Collections.sort(result);
        return result;
    }

    public List<Article> getSortedArticles() {
        List<Article> articles = new ArrayList<Article>(this.articles.values());
        Collections.sort(articles);
        return articles;
    }

    public TechArticle getTechArticleWithLongestWarranty() {
        TechArticle result = null;

        for(Article article : this.articles.values()) {
            if(article instanceof TechArticle) {
                TechArticle techArticle = (TechArticle) article;

                if (result == null) {
                    result =  techArticle;
                }
                else {
                    if(techArticle.getWarrantyMonths() > result.getWarrantyMonths()) {
                        result = techArticle;
                    }
                }
            }
        }

        return result;
    }
}
