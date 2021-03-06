package Repository;

import Exceptions.RepoException;
import Model.ADTs.IList;
import Model.ADTs.myList;
import Model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> myProgramStates;
    private String logFilePath;

    public Repository(String file) {
        myProgramStates = new ArrayList<>();
        logFilePath = file;
    }
    public Repository(List<ProgramState> programList,String file){
        myProgramStates=programList;
        logFilePath = file;
    }

    /*
    @Override
    public ProgramState getCurrentProgram() {

        return myProgramStates.get(myProgramStates.size()-1);
    }
    */

    @Override
    public void setPrgList(List<ProgramState> programStates) {
        myProgramStates=programStates;
    }

    @Override
    public void addProgram(ProgramState newPrg) {
        myProgramStates.add(newPrg);
    }

    @Override
    public List<ProgramState> getAllPrograms() {
        return myProgramStates;
    }

    @Override
    public void logPrgStateExec(ProgramState state) throws IOException {
            PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
            logFile.write(state.toString());
            logFile.close();
    }

    @Override
    public int getSize() {
        return myProgramStates.size();
    }
}
