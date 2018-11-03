package 集合和映射;

import java.util.TreeSet;

/**
 * @Description: 国际摩尔斯密码定义一种标准编码方式，将每个字母对应于一个由一系列点和短线组成的字符串， 比如: "a" 对应 ".-", "b" 对应 "-...", "c" 对应 "-.-.", 等等。
 *
 * 为了方便，所有26个英文字母对应摩尔斯密码表如下：
 *
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * 给定一个单词列表，每个单词可以写成每个字母对应摩尔斯密码的组合。例如，"cab" 可以写成 "-.-.-....-"，(即 "-.-." + "-..." + ".-"字符串的结合)。我们将这样一个连接过程称作单词翻译。
 *
 * 返回我们可以获得所有词不同单词翻译的数量。
 *
 * 例如:
 * 输入: words = ["gin", "zen", "gig", "msg"]
 * 输出: 2
 * 解释:
 * 各单词翻译如下:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 *
 * 共有 2 种不同翻译, "--...-." 和 "--...--."
 * @create: 2018/11/3
 * @Author: SLJ
 */
public class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        for(int i = 0;i < words.length;i++){
            words[i] = transCode(words[i]);
        }
        TreeSet<String> set = new TreeSet<>();
        for(int i = 0;i < words.length;i++){
            set.add(words[i]);
        }
        return set.size();
    }

    private String transCode(String str){
        StringBuilder stringBuilder = new StringBuilder();
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        char[] charStr = str.toCharArray();
        for(int i = 0;i < charStr.length;i++){
            stringBuilder.append(codes[(int)charStr[i] - 97]);
        }
        return stringBuilder.toString();

    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        System.out.println(new Solution().uniqueMorseRepresentations(words));
    }
}
