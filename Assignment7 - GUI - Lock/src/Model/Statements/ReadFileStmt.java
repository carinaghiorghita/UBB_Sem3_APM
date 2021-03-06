package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt{

    private IExp exp;
    private String var_name;

    public ReadFileStmt(IExp e, String s) {
        exp=e;
        var_name=s;
    }

    @Override
    public String toString() {
        return "readFile(" +exp.toString()+")";
    }


    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IDict<String, IValue> table=state.getSymTable();
        IDict<StringValue, BufferedReader> files=state.getFileTable();
        IHeap<IValue> heap=state.getHeapTable();

        if(!(table.isDefined(var_name)))
            throw new StmtException("Undefined variable");
        else if(!table.lookup(var_name).getType().equals(new IntType()))
            throw new StmtException("Type is not int");

        try {
            if(!(exp.eval(table,heap).getType().equals(new StringType())))
                throw new StmtException("Invalid type (should be a string)");
            StringValue filestr = (StringValue) exp.eval(table,heap);
            //String file = filestr.getValue();

            if(!(files.isDefined(filestr))){
                throw new StmtException("File is not defined");
            }

            BufferedReader r=files.lookup(filestr);
            String line=null;
            try{
                line=r.readLine();
            }catch (IOException e){
                throw new StmtException(e.getMessage());
            }
            int value;
            if(line==null) value=0;
            else value=Integer.parseInt(line);
            IValue val=new IntValue(value);

            table.update(var_name,val);


        }catch (EvalException e){
            throw new StmtException(e.getMessage());
        }

        return null;
    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
