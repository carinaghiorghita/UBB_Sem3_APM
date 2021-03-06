package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;

import javax.imageio.IIOException;
import java.io.BufferedReader;
import java.io.IOException;

public class CloseFileStmt implements IStmt{
    private IExp exp;

    public CloseFileStmt(IExp e) { exp=e; }

    @Override
    public String toString() {
        return "closeFile(" +exp.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        IDict<StringValue, BufferedReader> files=state.getFileTable();
        IDict<String, IValue> table=state.getSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        try {
            if (!(exp.eval(table,heap).getType().equals(new StringType())))
                throw new StmtException("Invalid type (should be a string)");
            StringValue filestr = (StringValue) exp.eval(table,heap);
            //String file = filestr.getValue();

            if(!(files.isDefined(filestr))){
                throw new StmtException("File is not defined");
            }

            BufferedReader r=files.lookup(filestr);
            try{
                r.close();
            }catch(IOException e){
                throw new StmtException(e.getMessage());
            }

            files.remove(filestr);

        }catch(EvalException e){
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
