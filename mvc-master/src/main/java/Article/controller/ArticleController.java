package Article.controller;

import Article.model.Article;
import Article.model.ArticleRepository;
import Article.model.Reply;
import Article.model.ReplyRepository;
import Article.view.ArticleView;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class ArticleController {

    ArticleView articleView = new ArticleView();
    ArticleRepository articleRepository = new ArticleRepository();
    ReplyRepository replyRepository = new ReplyRepository();
    Scanner scan = new Scanner(System.in);

    public void add() {
        System.out.print("게시물 제목을 입력해주세요 : ");
        String title = scan.nextLine();
        System.out.print("게시물 내용을 입력해주세요 : ");
        String content = scan.nextLine();

        articleRepository.insert(title, content);

        System.out.println("게시물이 등록되었습니다.");
    }

    public void list() {
        ArrayList<Article> articles = articleRepository.findAllArticles();
        articleView.printArticles(articles);
    }

    public void update() {

        System.out.print("수정할 게시물 번호 : ");
        int targetId = getParamInt(scan.nextLine(), -1);
        if(targetId == -1) {
            return;
        }
        Article article = articleRepository.findById(targetId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
        } else {
            System.out.print("제목 : ");
            String newTitle = scan.nextLine();
            System.out.print("내용 : ");
            String newContent = scan.nextLine();

            article.setTitle(newTitle);
            article.setContent(newContent);

            System.out.println("수정이 완료되었습니다.");
        }
    }

    public void delete() {
        System.out.print("삭제할 게시물 번호 : ");
        int targetId = getParamInt(scan.nextLine(), -1);

        if(targetId == -1) {
            return;
        }

        Article article = articleRepository.findById(targetId);

        if (article == null) {
            System.out.println("없는 게시물입니다.");
        } else {
            articleRepository.delete(article);
        }
    }

    public void detail() {
        // 중복 -> 함수

        System.out.print("상세보기 할 게시물 번호를 입력해주세요 : ");
        int targetId = getParamInt(scan.nextLine(), -1);
        if(targetId == -1) {
            return;
        }
        Article article = articleRepository.findById(targetId);

        if (article == null) {
            System.out.println("존재하지 않는 게시물입니다.");
        } else {
            article.setHit(article.getHit() + 1);
            ArrayList<Reply> replies = replyRepository.getRepliesByArticleId(article.getId());
            articleView.printArticleDetail(article, replies);
            doDetailProcess(article);
        }
    }

    public void doDetailProcess(Article article) {
        while(true) {
            System.out.print("상세보기 기능을 선택해주세요(1. 댓글 등록, 2. 추천, 3. 수정, 4. 삭제, 5. 목록으로) : ");
            int cmd = getParamInt(scan.nextLine(), -1);

            switch(cmd) {
                case 1 :
                    addReply(article);
                    break;
                case 2 :
                    System.out.println("추천");
                    break;
                case 3 :
                    System.out.println("수정");
                    break;
                case 4 :
                    System.out.println("삭제");
                    break;
                case 5 :
                    System.out.println("목록으로 돌아갑니다.");
                    break;
            }

            if(cmd == 5) {
                break;
            }
        }
    }

    public void addReply(Article article) {
        System.out.print("댓글 내용 : ");
        String body = scan.nextLine();
        String regDate = Util.getCurrentDate();
        int articleId = article.getId();

        replyRepository.insert(articleId, body, regDate);

        System.out.println("댓글이 성공적으로 등록되었습니다.");
        ArrayList<Reply> replies = replyRepository.getRepliesByArticleId(article.getId());
        articleView.printArticleDetail(article, replies);
    }

    public void search() {
        System.out.print("검색 키워드를 입력해주세요 : ");
        String keyword = scan.nextLine();
        ArrayList<Article> searchedArticles = articleRepository.findByTitle(keyword);
        articleView.printArticles(searchedArticles);
    }



    public int getParamInt(String input, int defaulValue) {

        try {
                int num = Integer.parseInt(input);
                return num;

            }catch (NumberFormatException e) {
                System.out.println("숫자만 입력 가능합니다.");
        }

        return defaulValue;
    }
}
