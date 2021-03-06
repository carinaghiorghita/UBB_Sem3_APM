package Model.Statements;

import Exceptions.EvalException;
import Exceptions.StackException;
import Exceptions.StmtException;
import Exceptions.TypeCheckException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ADTs.IStack;
import Model.Expressions.IExp;
import Model.ProgramState;
import Model.Types.IType;
import Model.Types.StringType;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenFileStmt implements IStmt{
    private IExp exp;

    public OpenFileStmt(IExp e) { exp=e; }

    @Override
    public String toString() {
        return "openFile(" +exp.toString()+")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws StmtException {
        try{IDict<StringValue, BufferedReader> files=state.getFileTable();
        IDict<String, IValue> table=state.getCurrentSymTable();
        IHeap<IValue> heap=state.getHeapTable();

        try {
            StringValue filestr=(StringValue)exp.eval(table,heap);
            String file = filestr.getValue();
            IType type = exp.eval(table,heap).getType();


            if (!(type.equals(new StringType()))) {
                throw new StmtException("Invalid type");
            }

            if (files.isDefined(filestr)) {
                throw new StmtException("Key already exists");
            }

            BufferedReader r = new BufferedReader(new FileReader(file));
            files.add(filestr,r);


        }catch(IOException | EvalException  e){
            throw new StmtException(e.getMessage());
        }        state.getSymTable().push(table);

        }catch (StackException e){throw new StmtException(e.getMessage());}
        return null;

    }

    @Override
    public IDict<String, IType> typecheck(IDict<String, IType> typeEnv) throws TypeCheckException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}
