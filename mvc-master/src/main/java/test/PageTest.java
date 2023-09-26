package test;

import java.util.ArrayList;
import java.util.Scanner;

public class PageTest {
    public static void main(String[] args) {
//        System.out.println("[1] 2 3 4 5 6 7");

        Scanner scan = new Scanner(System.in);

        ArrayList<String> stringList = new ArrayList<>();
        for(int i = 1; i <= 100; i++) {
            stringList.add("제목" + i);
        }

//        for (String s : stringList) {
//            System.out.printf("제목 : %s\n", s);
//            System.out.println("======================");
//        }

        System.out.print("원하는 페이지 번호 : ");
        int currentPageNo = scan.nextInt();

        int itemsCountPerPage = 3; // 한 페이지에 나와야하는 게시물 개수

        int startIdx = (currentPageNo - 1) * itemsCountPerPage; // 꺼내올 게시물의 처음 인덱스. -> 내가 입력한 페이지(1 - 0, 2 - 3, 3 - 6, 4 - 9)

        // (입력한페이지넘버(1) - 1) * 3 = 0, (입력한페이지넘버(2)- 1) * 3 = 3, (입력한페이지넘버(3) - 1) * 3 = 6 ....

        int endIdx = startIdx + itemsCountPerPage; // 꺼내올 게시물의 마지막 인덱스
        int totalCnt = stringList.size();

        if(endIdx >= totalCnt) {
            endIdx = totalCnt;
        }


        // 전체 페이지 구하기
        // 전체 아이템 개수 / 한페이지당 보여주는 아이템 개수
        // 100 / 3 => 33.3 -> 올림을 해야함

        double pageCnt = Math.ceil((double)stringList.size() / itemsCountPerPage);


        for(int i = startIdx; i < endIdx; i++) {
            String s = stringList.get(i);
            System.out.printf("제목 : %s\n", s);
            System.out.println("======================");
        }

        // 1번째 페이지 리스트 : 1 2 3 4 5  -> 1,2,3,4,5
        // 2번째 페이지 리스트 : 6 7 8 9 10 -> 6, 7, 8, 9, 10
        // 3번째 페이지 리스트 : 11 12 13 14 15  -> 11, 12, 13, 14, 15

        // 1 / 5 = 0.2(올림 1), 2 / 5 = 0.4(올림 1), 3 / 5 = 0.6(올림 1), 4 / 5 = 0.8(올림 1), 5 / 5 = 1(올림 1)
        // 6 / 5 = 1.2(올림 2), 7 / 5 = 1.4(올림 2), 8 / 5 = 1.6(올림 2), 9 / 5 = 1.8(올림 2), 10 / 5 = 2(올림 2)

        // 올림(입력한 페이지 번호 / 한번에 보여주고 싶은 페이지 개수) => 페이지 리스트 순번
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
