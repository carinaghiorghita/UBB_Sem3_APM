package Model.Statements;

import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ProgramState;
import Model.Types.BoolType;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;

public class VarDeclStmt implements IStmt{
    private String name;
    private IType type;

    public VarDeclStmt(String s,IType t){
        name=s;
        type=t;
    }
    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IDict<String, IValue> table=state.getSymTable();
        if(table.isDefined(name)) throw new StmtException("Variable already exists");
//        if(type.equals(new IntType())) table.add(name,new IntValue(0));
//        else if(type.equals(new BoolType())) table.add(name,new BoolValue(false));
//        else throw new StmtException("Type is undefined");
        table.add(name,type.defaultValue());
        return null;
    }

    @Override
    public String toString() {
        return name+"<-"+type.toString();
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        typeEnv.add(name,type);
        return typeEnv;
    }
}
