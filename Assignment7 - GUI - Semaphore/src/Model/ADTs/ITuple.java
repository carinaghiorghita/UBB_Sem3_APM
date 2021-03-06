package Model.ADTs;

import java.util.List;

public interface ITuple <T1,T2,T3>{
    String toString();
    T1 getFirst();
    T2 getSecond();
    T3 getThird();
}
