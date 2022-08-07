package com.lgj.myflowdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Set;

public class DemoWeiPing {
    public static void main(String[] args) {
        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
        ArrayList<WangZhi> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String str = scanner.nextLine();
            String str2 = str.replaceAll("[0-9]", "");
            // 说明输入的是数字
            if ("".equals(str2)) {
                // 遍历hashmap
                Set<String> keySet = hashMap.keySet();
                for (String url : keySet) {
                    Integer integer = hashMap.get(url);
                    WangZhi wangZhi = new WangZhi(url, integer);
                    arrayList.add(wangZhi);
                }
//                对集合元素进行排序，
                Collections.sort(arrayList, new Comparator<WangZhi>() {
                    @Override
                    public int compare(WangZhi o1, WangZhi o2) {
                        if (o2.num > o1.num) {
                            return 1;
                        } else if (o2.num == o1.num) {
                            return 0;
                        } else {
                            return -1;
                        }
                    }
                });
                Integer number = Integer.valueOf(str);
                if (number > hashMap.size()) {
                    number = hashMap.size();
                }
                for (int i = 0; i < number; i++) {
                    WangZhi wangZhi = arrayList.get(i);
                    System.out.println(wangZhi.url);
                }
                arrayList.clear();

            } else {// 说明用户输入的是网址
                Integer integer = hashMap.get(str2);
                if (integer == null) {
                    hashMap.put(str2, 1);
                } else {
                    // 取出网址加加
                    hashMap.put(str2, ++integer);
                }
            }
            if (str.equals("skip")) {
                // 输入skip跳出循环
                return;
            }
        }
    }
    static class WangZhi {
        public String url;
        public int num;
        public WangZhi(String url, int num) {
            this.url = url;
            this.num = num;
        }

    }
}
