package 并查集;

/**
 * @Description: 在路径压缩的基础上把路径压缩成高度为2,执行递归操作，开销变大，性能反而变小了
 * @create: 2018/11/5
 * @Author: SLJ
 */
public class UnionFind6 implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //查看p和q元素是否属于同一个集合
    //O（h）复杂度，h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并p和q元素的集合
    //O（h）复杂度，h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pROOT = parent[p];
        int qROOT = parent[q];
        if (pROOT == qROOT){
            return;
        }
        if (rank[pROOT] < rank[qROOT]){
            parent[pROOT] = qROOT;
        }else if (rank[pROOT] > rank[qROOT]){
            parent[qROOT] = pROOT;
        }else {
            parent[qROOT] = pROOT;
            rank[pROOT] += 1;
        }
    }

    //查找元素P所对应的集合编号
    //O（h）复杂度，h为树的高度
    private int find(int p){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("参数输入违法");
        }
        if (p != parent[p]){
            parent[p] = find(parent[p]);//执行递归操作
        }
        return p;
    }
}
