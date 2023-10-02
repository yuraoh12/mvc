package Article.model;

import Article.controller.Pagination;
import util.Util;

import java.util.*;

// Dao
// Repository
public class ArticleRepository {

    private static ArrayList<Article> articles = new ArrayList<>();
    private int lastArticleId = 4;

    public ArticleRepository() {

        if(articles.isEmpty()) {
            Article a1 = new Article(1, "안녕하세요 반갑습니다. 자바 공부중이에요.", "냉무", 1, Util.getCurrentDate());
            a1.setHit(10);
            Article a2 = new Article(2, "자바 질문좀 할게요~.", "냉무", 2, Util.getCurrentDate());
            a2.setHit(102);
            Article a3 = new Article(3, "정처기 따야되나요?", "냉무", 1, Util.getCurrentDate());
            a3.setHit(55);

            articles.add(a2);
            articles.add(a3);
            articles.add(a1);

            for(int i = 4; i < 100; i++) {
                Article a = new Article(i, "제목" + i, "내용" + i, 2, Util.getCurrentDate());
                articles.add(a);
            }

        }
    }

    public void insert(String title, String content, int memberId) {
        Article article = new Article(lastArticleId, title, content, memberId, Util.getCurrentDate());
        articles.add(article);
        lastArticleId++;
    }

    public void delete(Article article) {
        //articles.remove(i); // 위치 기반으로 삭제
        articles.remove(article);// 값 기반 삭제
    }

    public ArrayList<Article> findAllArticles() {
        return articles;
    }

    public Article findById(int id) {

        Article target = null;

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            if (id == article.getId()) {
                target = article;
            }
        }

        return target;
    }

    public ArrayList<Article> findByTitle(String keyword) {
        ArrayList<Article> searchedArticles = new ArrayList<>();

        for (int i = 0; i < articles.size(); i++) {
            Article article = articles.get(i);
            String title = article.getTitle();

            if (title.contains(keyword)) {
                searchedArticles.add(article);
            }
        }

        return searchedArticles;
    }

    public void update(int articleId, String title, String content) {
        Article article = findById(articleId);
        article.setTitle(title);
        article.setContent(content);
    }

    public int getTotalArticleCount() {
        return articles.size();
    }

    public ArrayList<Article> findPagedArticles(Pagination pagination) {
        ArrayList<Article> pagedArticles = new ArrayList<>();

        for(int i = pagination.getStartIdx(); i < pagination.getEndIdx(); i++) {
            pagedArticles.add(articles.get(i));
        }

        return pagedArticles;
    }

    public void sortArticles(int sortTarget, int sortType) {
        Collections.sort(articles, new SortFactory().getSort(sortTarget).setDirection(sortType));
    }
}

class Sort {
    protected int order = 1;

    Comparator<Article> setDirection(int direction) {
        if(direction == 2) {
            order = -1;
        }

        return (Comparator<Article>)this;
    }
}

class SortFactory {

    Map<Integer, Sort> sortMap = new HashMap<>();

    SortFactory() {
        sortMap.put(1, new SortById());
        sortMap.put(2, new SortByHit());
    }
    public Sort getSort(int sortTarget) {
        return sortMap.get(sortTarget);
    }
}

class SortById extends Sort implements Comparator<Article> {

    @Override
    public int compare(Article o1, Article o2) {
        if(o1.getId() > o2.getId()) {
            return order;
        }
        return -1 * order;
    }
}

class SortByHit extends Sort implements Comparator<Article> {
    @Override
    public int compare(Article o1, Article o2) {
        if(o1.getHit() > o2.getHit()) {
            return order;
        }
        return -1 * order;
    }
}

