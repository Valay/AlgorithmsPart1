/**
 * Weighted Quick Union with Path Compression
 * (Single pass variant - https://www.coursera.org/learn/algorithms-part1/lecture/RZW72/quick-union-improvements)
 */
public class UnionFind {

    int[] id;
    int[] sz;
    int count;

    /**
     * Initialize UF data structure with N objects
     * @param size
     */
    public UnionFind(int size){
        id = new int[size];
        sz = new int[size];
        count = size;
        for(int i=0; i<size;i++){
            id[i] = i;
            sz[i] = 1;
        }
    }

    /**
     * add connection between q and p
     * @param p
     * @param q
     */
    public void union(int p, int q){
        int pid = find(p);
        int qid = find(q);
        if(pid != qid){
            if(sz[pid] <= sz[qid]){
                id[pid] = qid;
                sz[pid] += sz[qid];
            }else{
                id[qid] = pid;
                sz[qid] += sz[pid];
            }
        }
    }

    /**
     * check if p and q are connected i.e. they are in the same component
     * @param p
     * @param q
     * @return
     */
    public boolean isConnected(int p, int q){
        return find(p) == find(q);
    }

    /**
     * Return the component identifier for p
     * @param p
     * @return
     */
    public int find(int p){
        while(p != id[p]){
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    /**
     * Return the number of connected components
     * @return
     */
    public int count(){
        int count = 0;
        for(int i=0; i<id.length; i++){
            if(i == id[i]){
                count++;
            }
        }
        this.count = count;
        return count;
    }
}
