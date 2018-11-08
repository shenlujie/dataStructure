package 哈希表;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description: java中的hashCode方法
 * @create: 2018/11/7
 * @Author: SLJ
 */
public class Main {
    public static void main(String[] args) {
        int a = 42;
        int b = -42;
        String s = "slj";
        System.out.println(new Integer(a).hashCode());
        System.out.println(new Integer(b).hashCode());
        System.out.println(s.hashCode());
        System.out.println(new Student(15,15070841,"LUJIE","shen").hashCode());
        System.out.println(new Student(15,15070841,"LUJIE","shen").hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(new Student(15,15070841,"LUJIE","shen"));
        HashMap<Student,Integer> map = new HashMap<>();
        map.put(new Student(15,15070841,"LUJIE","shen"), 100);
        System.out.println();
    }
}
