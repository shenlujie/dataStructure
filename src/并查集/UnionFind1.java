package 并查集;

/**
 * @Description: 第一版的并查集，没有任何优化，执行unionElements操作时，就是将
 * 所有的p所在集合中所有id全部变为q的id
 * @create: 2018/11/5
 * @Author: SLJ
 */
public class UnionFind1 implements UF{

    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int qId = id[q];
        int pId = id[p];
        if (qId == pId){
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId){
                id[i] = pId;
            }
        }
    }

    private int find(int p){
        if (p < 0 || p >= id.length){
            throw new IllegalArgumentException("参数输入违法");
        }
        return id[p];
    }
}
