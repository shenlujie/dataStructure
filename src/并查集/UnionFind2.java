package 并查集;

/**
 * @Description: 优化：执行unionElements操作时，将p为根节点的子树指向q为根节点的子树
 * @create: 2018/11/5
 * @Author: SLJ
 */
public class UnionFind2 implements UF {

    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    //查看p和q元素是否属于同一个集合（是否在一棵树上）
    //O（h）复杂度，h为树的高度
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //合并p和q元素到一棵树上，q的根节点指向p的根节点
    //O（h）复杂度，h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pROOT = parent[p];
        int qROOT = parent[q];
        if (pROOT == qROOT){
            return;
        }
        parent[pROOT] = qROOT;
    }

    //查找元素P所对应的树的根
    //O（h）复杂度，h为树的高度
    private int find(int p){
        if (p < 0 || p >= parent.length){
            throw new IllegalArgumentException("参数输入违法");
        }
        while (p != parent[p]){
            p = parent[p];
        }
        return p;
    }
}
