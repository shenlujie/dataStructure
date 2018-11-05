package 并查集;


import java.util.Random;

/**
 * @Description: 对于各个并查集的性能进行测试
 * @create: 2018/11/5
 * @Author: SLJ
 */
public class TestUF {

    public static double countTime(UF uf,int m){
        long startTime = System.nanoTime();

        Random random = new Random();
        int size = uf.getSize();
        for (int i = 0; i < m; i++) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.unionElements(q,p);
        }
        for (int i = 0; i < m; i++) {
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            uf.isConnected(q,p);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime)/1000000000.0;
    }



    public static void main(String[] args) {
        int m = 10000000;
        int size = 10000000;
//        UnionFind1 u1 = new UnionFind1(size);
//        UnionFind2 u2 = new UnionFind2(size);
        UnionFind3 u3 = new UnionFind3(size);
        UnionFind4 u4 = new UnionFind4(size);
        UnionFind5 u5 = new UnionFind5(size);
        UnionFind6 u6 = new UnionFind6(size);
//        System.out.println("UnionFind1 执行以上程序共耗时： " + countTime(u1,m) + " s");
//        System.out.println("UnionFind2 执行以上程序共耗时： " + countTime(u2,m) + " s");
        System.out.println("UnionFind3 执行以上程序共耗时： " + countTime(u3,m) + " s");
        System.out.println("UnionFind4 执行以上程序共耗时： " + countTime(u4,m) + " s");
        System.out.println("UnionFind5 执行以上程序共耗时： " + countTime(u5,m) + " s");
        System.out.println("UnionFind6 执行以上程序共耗时： " + countTime(u6,m) + " s");
    }
}
