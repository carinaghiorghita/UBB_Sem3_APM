package Model.ADTs;

import java.util.List;

public class myTuple<T1,T2,T3> implements ITuple<T1,T2,T3> {
    private T1 first;
    private T2 second;
    private T3 third;

    public myTuple(T1 f,T2 s,T3 t){first=f;second=s;third=t;}

    @Override
    public String toString() {
        return "<"+first+","+second+","+third+">";
    }

    @Override
    public T1 getFirst() {
        return first;
    }

    @Override
    public T2 getSecond() {
        return second;
    }

    @Override
    public T3 getThird() {
        return third;
    }
}
