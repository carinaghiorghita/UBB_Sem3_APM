package Repository;
import Model.IObject;

public class Repository implements IRepository{

    private int size;
    private IObject[] data;

    public Repository(int capacity) {
        this.data = new IObject[capacity];
        this.size=0;
    }

    public void add(IObject elem) throws MyException {
        if(data.length==this.size){
            throw new MyException("List is full");
        }
        if(elem.getVolume()<=0){
            throw new MyException("Invalid volume");
        }
        this.data[size++]=elem;
    }

    public void remove(int index) throws MyException {
        if(index<0 || index>=this.size){
            throw new MyException("Invalid position");
        }
        for(int i=index+1;i<this.size;++i){
            this.data[i-1]=this.data[i];
        }
        this.size--;
    }

    public int getSize() {
        return this.size;
    }

    public IObject[] filter() throws MyException{
        if(this.size==0){
            throw new MyException("List is empty");
        }
        int len=0;

        for(int i=0;i<this.size;++i){
            if(this.data[i].getVolume()>IObject.MIN_VOLUME){
                len++;
            }
        }

        IObject[] filteredData = new IObject[len];
        len=0;

        for(int i=0;i<this.size;++i){
            if(this.data[i].getVolume()>IObject.MIN_VOLUME){
                filteredData[len++]=this.data[i];
            }
        }
        return filteredData;
    }
    public IObject[] getAll()throws MyException{
            if(this.size==0){
                throw new MyException("List is empty");
            }
        return this.data;
    }

}
