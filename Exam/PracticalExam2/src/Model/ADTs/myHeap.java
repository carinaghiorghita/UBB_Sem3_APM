package Model.ADTs;

import java.util.HashMap;

public class myHeap<V> implements IHeap<V> {
    private HashMap<Integer, V> heapTable;
    private int freeLocation = 0;

    public myHeap() {
        heapTable = new HashMap<Integer, V>();
    }

    public myHeap(HashMap<Integer, V> heap) {
        heapTable=heap;
    }

    @Override
    public void put(int key,V value){
        heapTable.put(key,value);
    }

    @Override
    public V get(int key){
        return heapTable.get(key);
    }

    @Override
    public boolean containsKey(int key){
        return heapTable.containsKey(key);
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }

    @Override
    public void update(int key,V value){
        heapTable.replace(key,value);
    }

    @Override
    public void setFreeAddress(int free){
        freeLocation=free;
    }

    @Override
    public HashMap<Integer, V> getHeap(){
        return heapTable;
    }

    @Override
    public void setHeap(HashMap<Integer, V> heap){
        heapTable=heap;
    }



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer key:heapTable.keySet())
            s.append(key.toString()+"->"+heapTable.get(key).toString()+" ");
        return s.toString();
    }
}
