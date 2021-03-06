package Model.ADTs;

import java.util.ArrayList;

public class myList<T> implements IList<T>{
    private ArrayList<T> list;

    public myList(){
        list=new ArrayList<T>();
    }

    @Override
    public void add(T elem) {
        list.add(elem);
    }

    @Override
    public boolean remove(T elem) {
        return list.remove(elem);
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(T elem:list)
            s.append(elem.toString()+" ");
        return s.toString();
    }

    @Override
    public ArrayList<T> getList() {
        return list;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public void clear() {
        list.clear();
    }
}
