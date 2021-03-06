package Repository;

import Exceptions.OneStepException;
import Exceptions.RepoException;
import Model.ADTs.IList;
import Model.ProgramState;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    void addProgram(ProgramState newPrg);
    //ProgramState getCurrentProgram();
    void setPrgList(List<ProgramState> programStates);
    List<ProgramState> getAllPrograms();
    void logPrgStateExec(ProgramState state) throws IOException;
    int getSize();
}
