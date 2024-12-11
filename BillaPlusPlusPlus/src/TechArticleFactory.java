public class TechArticleFactory implements ArticleFactory{
    @Override
    public Article createFromString(String line) {
        String[] splitted = line.split(";");
        TechArticle article = null;

        try {
            article = new TechArticle(splitted[1].strip(), Integer.parseInt(splitted[0].strip()), Integer.parseInt(splitted[2].strip()), Integer.parseInt(splitted[3].strip()));
        }
        catch (Exception e) {
            throw new ArticleManagementException("Invalid TechArticle", e);
        }

        return article;
    }
}
