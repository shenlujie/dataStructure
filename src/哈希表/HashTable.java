package 哈希表;

import java.util.TreeMap;

/**
 * @Description: 用TreeMap数组来实现一个HashTable(是存在bug的，HashTable内部是由TreeMap实现的，而TreeMap的key必须是继承自Comparable的，
 * 自己实现的HashTabled的key是不支持进行比较的，所以当key值为不可比较的值时，add操作就会报错)
 * @create: 2018/11/7
 * @Author: SLJ
 */
public class HashTable<K,V> {
    private final int[] capacity = {53,97,193,389,769,1543,3079,6151,12289,24953,
    49157,98317,196613,393241,786433,1572869,3145739,6291469,12582917,25165843,
    50331653,100663319,201326611,402653189,805306457,1610612741};//仅小于整形最大值的素数

    private static final int UPTOL = 10;
    private static final int LOWTOL = 2;
    private int capacityIndex = 0;

    private int M;
    private TreeMap<K,V>[] hashtable;
    private int size;

    public HashTable() {
        M = capacity[capacityIndex];
        hashtable = new TreeMap[M];
        size = 0;
        for (int i = 0; i < hashtable.length; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    private int hash(K key){
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize(){
        return size;
    }

    public void add(K key,V value){
        TreeMap<K,V> map = hashtable[hash(key)];
        if (map.containsKey(key)){
            map.put(key,value );
        }else {
            map.put(key, value);
            size ++;
            if (size >= UPTOL*M && capacityIndex + 1 < capacity.length){
                capacityIndex ++;
                resize(capacity[capacityIndex]);
            }
        }


    }

    public V remove(K key){
        TreeMap<K,V> map = hashtable[hash(key)];
        V ret = null;
        if (map.containsKey(key)){
            ret = map.remove(key);
            size --;
            if (size < LOWTOL * M && capacityIndex - 1 >= 0){
                capacityIndex --;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public void set(K key,V value){
        TreeMap<K,V> map = hashtable[hash(key)];
        if (map.containsKey(key)){
            map.put(key,value );
        }else {
            throw new IllegalArgumentException("key 不存在");
        }
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }
    
    private void resize(int newM){
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K,V> map = hashtable[i];
            for (K k : map.keySet()) {
                newHashTable[hash(k)].put(k,map.get(k));
            }
        }
        this.hashtable = newHashTable;
        
    }
}
