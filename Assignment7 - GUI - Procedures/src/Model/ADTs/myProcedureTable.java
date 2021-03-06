package Model.ADTs;

import Model.Statements.IStmt;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

public class myProcedureTable implements IProcedureTable{
    private HashMap<String, Pair<List<String>, IStmt>> heapTable;
    private int freeLocation = 0;

    public myProcedureTable() {
        heapTable = new HashMap<String, Pair<List<String>, IStmt>>();
    }

    public myProcedureTable(HashMap<String, Pair<List<String>, IStmt>> heap) {
        heapTable=heap;
    }

    @Override
    public void put(String key,Pair<List<String>, IStmt> value){
        heapTable.put(key,value);
    }

    @Override
    public Pair<List<String>, IStmt> get(String key){
        return heapTable.get(key);
    }

    @Override
    public boolean containsKey(String key){
        return heapTable.containsKey(key);
    }

    @Override
    public int getFreeAddress(){
        freeLocation++;
        return freeLocation;
    }

    @Override
    public void update(String key,Pair<List<String>, IStmt> value){
        heapTable.replace(key,value);
    }

    @Override
    public void setFreeAddress(int free){
        freeLocation=free;
    }

    @Override
    public HashMap<String, Pair<List<String>, IStmt>> getProcTable(){
        return heapTable;
    }

    @Override
    public void setProcTable(HashMap<String, Pair<List<String>,IStmt>> heap){
        heapTable=heap;
    }



    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(String key:heapTable.keySet())
            s.append(key.toString()+"->"+heapTable.get(key).toString()+" ");
        return s.toString();
    }
}
