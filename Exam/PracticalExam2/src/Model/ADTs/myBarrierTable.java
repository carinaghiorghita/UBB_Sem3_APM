package Model.ADTs;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class myBarrierTable implements IBarrierTable{
    private HashMap<Integer, Pair<Integer, List<Integer>>> barrierTable;
    private int freeLocation = 0;
    private static Lock lock=new ReentrantLock();


    public myBarrierTable() {
        lock.lock();
        barrierTable = new HashMap<Integer, Pair<Integer, List<Integer>>>();
        lock.unlock();
    }

    public myBarrierTable(HashMap<Integer, Pair<Integer, List<Integer>>> heap) {
        lock.lock();
        barrierTable=heap;
        lock.unlock();
    }

    @Override
    public void put(int key,Pair<Integer, List<Integer>> value){
        lock.lock();
        barrierTable.put(key,value);
        lock.unlock();
    }

    @Override
    public Pair<Integer, List<Integer>> get(int key){
        lock.lock();
        Pair<Integer, List<Integer>> p=barrierTable.get(key);
        lock.unlock();
        return p;
    }

    @Override
    public boolean containsKey(int key){
        lock.lock();
        Pair<Integer, List<Integer>> p=barrierTable.get(key);
        lock.unlock();
        if(p==null)return false;
        return true;
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }

    @Override
    public void update(int key,Pair<Integer, List<Integer>> value){

        lock.lock();
        barrierTable.replace(key,value);
        lock.unlock();
    }

    @Override
    public void setFreeAddress(int free){
        freeLocation=free;
    }

    @Override
    public HashMap<Integer, Pair<Integer, List<Integer>>> getBarrierTable(){
        lock.lock();
        HashMap<Integer, Pair<Integer, List<Integer>>> b= barrierTable;
        lock.unlock();
        return b;
    }

    @Override
    public void setBarrierTable(HashMap<Integer, Pair<Integer, List<Integer>>> heap){

        lock.lock();
        barrierTable=heap;
        lock.unlock();
    }



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(Integer key:barrierTable.keySet())
            s.append(key.toString()+"->"+barrierTable.get(key).toString()+" ");
        return s.toString();
    }

}
