package Model.ADTs;

import java.util.ArrayList;

public interface IList<T> {
    void add(T elem);
    boolean remove(T elem);
    T get(int index);
    String toString();
    ArrayList<T> getList();
    int getSize();
    void clear();
}
