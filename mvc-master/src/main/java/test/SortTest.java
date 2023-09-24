package test;

import java.util.ArrayList;
import java.util.Collections;

public class SortTest {
    public static void main(String[] args) {

        ArrayList<Integer> numList = new ArrayList<>();

        numList.add(3);
        numList.add(2);
        numList.add(6);
        numList.add(13);
        numList.add(1);

        for(Integer num : numList) {
            System.out.println(num);
        }

        Collections.sort(numList);

        for(Integer num : numList) {
            System.out.println(num);
        }

    }
}
