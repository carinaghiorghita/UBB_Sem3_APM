package Repository;
import Model.IObject;

public interface IRepository {
    void add(IObject elem) throws MyException;
    void remove(int pos) throws MyException;
    IObject[] filter() throws MyException;
    int getSize();
    IObject[] getAll() throws MyException;
    //String toString();

}
