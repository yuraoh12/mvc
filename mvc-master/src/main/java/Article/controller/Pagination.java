package Article.controller;

import Article.model.ArticleRepository;

public class Pagination {
    private int currentPageNo = 1;
    private int itemsCountPerPage = 3;
    private int pageCntPerBlock = 5;
    private int totalCount = 0;
    private ArticleRepository articleRepository;
    public int getEndPageNo() {
        int endPageNo = getStartPageNo() + pageCntPerBlock - 1 ;
        if(endPageNo >= getLastPageNo()) {
            endPageNo = getLastPageNo();
        }
        return endPageNo;
    }
    public int getStartPageNo() {
        return (getPageBlockNo() - 1) * pageCntPerBlock + 1;
    }
    public int getPageBlockNo() {
        return (int)(Math.ceil((double)currentPageNo / pageCntPerBlock));
    }
    public int getLastPageNo() {
        return (int)(Math.ceil((double)getTotalCount() / itemsCountPerPage));
    }
    public int getStartIdx() {
        int startIdx = (currentPageNo - 1) * itemsCountPerPage;

        if(startIdx < 0) {
            startIdx = 1;
        }

        return  startIdx;
    }

    public int getEndIdx() {
        int endIdx = getStartIdx() + itemsCountPerPage;

        if(endIdx >= getTotalCount()) {
            endIdx = getTotalCount();
        }

        return endIdx;
    }


    public Pagination() {
        articleRepository = new ArticleRepository();
    }

    public int getCurrentPageNo() {
        return currentPageNo;
    }

    public int getTotalCount() {
        return articleRepository.getTotalArticleCount();
    }

    public int getLastPageBlockNo() {
        return (int)(Math.ceil((double)getLastPageNo() / pageCntPerBlock));
    }

    public boolean hasNextBlock() {
        if(getPageBlockNo() < getLastPageBlockNo()) {
            return true;
        }
        return false;
    }

    public boolean hasPrevBlock() {
        if(getPageBlockNo() > 1) {
            return true;
        }
        return false;
    }

    public void prevPage() {
        currentPageNo--;
        if(currentPageNo < 1) {
            currentPageNo = 1;
        }
    }

    public void nextPage() {
        currentPageNo++;
        if(currentPageNo > getLastPageNo()) {
            currentPageNo = getLastPageNo();
        }
    }

    public void selectPage(int pageNo) {
        if(pageNo >= 1 && pageNo <= getLastPageNo()) {
            currentPageNo = pageNo;
        }
    }
}
