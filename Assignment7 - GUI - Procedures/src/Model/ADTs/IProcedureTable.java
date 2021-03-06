package Model.ADTs;

import Model.Statements.IStmt;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

public interface IProcedureTable {
    void put(String key, Pair<List<String>, IStmt> value);
    Pair<List<String>,IStmt> get(String key);
    boolean containsKey(String key);
    int getFreeAddress();
    void update(String key,Pair<List<String>,IStmt> value);
    void setFreeAddress(int freeAddress);
    HashMap<String, Pair<List<String>,IStmt>> getProcTable();
    void setProcTable(HashMap<String, Pair<List<String>,IStmt>> heap);
    String toString();
}
