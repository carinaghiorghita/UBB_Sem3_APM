package Model.ADTs;

import java.util.HashMap;

public class myDict<T1,T2> implements IDict<T1,T2>{
    private HashMap<T1,T2> dict;

    public myDict(){
        dict=new HashMap<T1, T2>();
    }

    @Override
    public void add(T1 v1, T2 v2) {
        dict.put(v1,v2);
    }

    @Override
    public void update(T1 v1, T2 v2) {
        dict.replace(v1,v2);
    }

    @Override
    public T2 lookup(T1 id) {
        if(dict.containsKey(id))return dict.get(id);
        return null;
    }

    @Override
    public boolean isDefined(T1 id) {
        return dict.containsKey(id);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(T1 key:dict.keySet())
            s.append(key.toString()+"->"+dict.get(key).toString()+" ");
        return s.toString();
    }

    @Override
    public String toStringFiles() {
        StringBuilder s = new StringBuilder();
        for(T1 key:dict.keySet())
            s.append(key.toString()+" ");
        return s.toString();
    }

    @Override
    public HashMap<T1, T2> getDict() {
        return dict;
    }

    @Override
    public void clear() {
        dict.clear();
    }

    @Override
    public void remove(T1 id) {
        dict.remove(id);
    }

    @Override
    public IDict<T1, T2> clone() {
        IDict<T1, T2> di = new myDict<>();
        for(T1 key : dict.keySet())
            di.add(key, dict.get(key));
        return di;
    }
}
