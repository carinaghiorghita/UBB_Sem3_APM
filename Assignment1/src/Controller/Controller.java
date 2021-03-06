package Controller;
import Model.IObject;
import Repository.IRepository;
import Repository.MyException;

public class Controller {
    IRepository repo;

    public Controller(IRepository repo) {
        this.repo = repo;
    }

    public void add(IObject newObj) throws MyException {
        this.repo.add(newObj);
    }

    public void remove(int index) throws MyException {
        this.repo.remove(index);
    }

    public IObject[] filter() throws MyException{
        return this.repo.filter();
    }
    public IObject[] getAll() throws MyException{
        return this.repo.getAll();
    }
    public int getSize() throws MyException{
        return this.repo.getSize();
    }
}
