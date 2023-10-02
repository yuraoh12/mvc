package test;

import java.util.ArrayList;
import java.util.Scanner;

public class PageTest {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        ArrayList<String> stringList = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            stringList.add("제목" + i);
        }

        System.out.print("원하는 페이지 번호 : ");
        int currentPageNo = scan.nextInt();

        int itemsCountPerPage = 3;

        int startIdx = (currentPageNo - 1) * itemsCountPerPage;

        int endIdx = startIdx + itemsCountPerPage;
        int totalCnt = stringList.size();

        if(endIdx >= totalCnt) {
            endIdx = totalCnt;
        }

        double pageCnt = Math.ceil((double)stringList.size() / itemsCountPerPage);

        for(int i = startIdx; i < endIdx; i++) {
            String s = stringList.get(i);
            System.out.printf("제목 : %s\n", s);
            System.out.println("======================");
        }

        int pageCntPerBlock = 5;
        int pageBlockNo = (int)(Math.ceil((double)currentPageNo / pageCntPerBlock));
        int startPageNo = (pageBlockNo - 1) * pageCntPerBlock + 1;
        int endPageNo = startPageNo + pageCntPerBlock - 1 ;

        for(int i = startPageNo; i <= endPageNo; i++) {
            if (i == currentPageNo) {
                System.out.print("[" + i +"]" + " ");
            } else {
                System.out.print(i + " ");
            }
        }
    }
}
