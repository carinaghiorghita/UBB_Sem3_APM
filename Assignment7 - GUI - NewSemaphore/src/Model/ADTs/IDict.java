package Model.ADTs;

import java.util.HashMap;

public interface IDict<T1,T2>{

    void add(T1 v1, T2 v2);
    void update(T1 v1, T2 v2);
    T2 lookup(T1 id);
    boolean isDefined(T1 id);
    String toString();
    void clear();
    HashMap<T1,T2> getDict();
    void remove(T1 id);
    String toStringFiles();
    IDict<T1, T2> clone() ;

}
