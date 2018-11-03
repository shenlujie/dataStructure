package 集合和映射;

import java.util.ArrayList;

/**
 * @Description:
 * @create: 2018/11/3
 * @Author: SLJ
 */
public class TestMap {
    public static double countTime(Map<String,Integer> map){
        long startTime = System.nanoTime();

        ArrayList<String> list = new ArrayList<>();
        if (FileOperation.readFile("B:\\dataStructure\\src\\集合和映射\\傲慢与偏见.txt",list)){
            for (String word:list) {
                if (map.contains(word)){
                    map.set(word,map.get(word)+1);
                }else {
                    map.add(word,1);
                }

            }
        }
        System.out.println("pride 在文中出现的次数为:" + map.get("pride"));
        System.out.println("prejudice 在文中出现的次数为:" + map.get("prejudice"));

        long endTime = System.nanoTime();
        return (endTime - startTime)/1000000000.0;
    }

    public static void main(String[] args) {
        BSTMap<String,Integer> bstMap = new BSTMap<>();
        System.out.println("BSTSet共耗时： " + countTime(bstMap) + "s");
        System.out.println();
        LinkedListMap<String,Integer> linkedListMap = new LinkedListMap<>();
        System.out.println("LinkedListSet共耗时: " + countTime(linkedListMap) + "s");

    }
}
