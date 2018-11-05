package 并查集;

/**
 * @Description: 优化：执行unionElements操作时，比较以p、q为根节点的子树的节点数，将
 *  * 节点数少的子树的根指向节点数多的子树的根
 * @create: 2018/11/5
 * @Author: SLJ
 */
public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
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
        if (sz[pROOT] > sz[qROOT]){
            parent[qROOT] = pROOT;
            sz[pROOT] += sz[qROOT];
        }else {
            parent[pROOT] = qROOT;
            sz[qROOT] += sz[pROOT];
        }
    }

    //查找元素P所对应的集合编号
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
