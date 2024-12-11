public class FoodArticleFactory implements ArticleFactory {
    @Override
    public Article createFromString(String line) {
        String[] splitted = line.split(";");
        FoodArticle article = null;

        try {
            String[] allergenStrings = splitted[3].split(",");
            AllergenType[] allergens = new AllergenType[allergenStrings.length];

            for (int j = 0; j < allergens.length; j++) {
                allergens[j] = AllergenType.valueOf(allergenStrings[j].strip());
            }

            article = new FoodArticle(splitted[1].strip(), (Integer.parseInt(splitted[0].strip())), (Integer.parseInt(splitted[2].strip())), allergens);
        }
        catch (Exception e) {
            throw new ArticleManagementException("Invalid FoodArticle", e);
        }

        return article;
    }
}
