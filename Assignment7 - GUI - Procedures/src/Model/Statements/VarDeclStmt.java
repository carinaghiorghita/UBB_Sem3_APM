package Model.Statements;

import Exceptions.StackException;
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
        try{IDict<String, IValue> table=state.getCurrentSymTable();
        if(table.isDefined(name)) throw new StmtException("Variable already exists");
//        if(type.equals(new IntType())) table.add(name,new IntValue(0));
//        else if(type.equals(new BoolType())) table.add(name,new BoolValue(false));
//        else throw new StmtException("Type is undefined");
        table.add(name,type.defaultValue());
        state.getSymTable().push(table);
        }catch (StackException e){throw new StmtException(e.getMessage());}
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
