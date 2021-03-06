package Controller;

import Exceptions.StackException;
import Model.ADTs.IDict;
import Model.ADTs.IHeap;
import Model.ProgramState;
import Model.Values.IValue;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Repository.IRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repo;
    private ExecutorService executor;
    private String programName;

    public Controller(IRepository r,String name){repo=r;programName=name;}

    public Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer,IValue> heap){
        return heap.entrySet()
                .stream()
                .filter(e->symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));    }

    List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }

    List<ProgramState> removeCompletedProgram(List<ProgramState> inProgramList){
        return inProgramList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public void displayCurrent(ProgramState state){
            System.out.print(state.toString());
        }

    public void allStep() throws InterruptedException {
        executor = Executors.newFixedThreadPool(2);

        List<ProgramState> prgList = removeCompletedProgram(repo.getAllPrograms());

        while (prgList.size() > 0) {
            repo.getAllPrograms().stream().forEach(program -> {
                try {
                    program.getHeapTable().setHeap((HashMap<Integer,IValue>)unsafeGarbageCollector(
                            getAddrFromSymTable(program.getSymTable().clone().pop().getDict().values()),
                            program.getHeapTable().getHeap()));
                } catch (StackException e) {
                    e.printStackTrace();
                }
            });
            oneStepForAllPrg(prgList);
            prgList = removeCompletedProgram(repo.getAllPrograms());
        }

        executor.shutdownNow();

        repo.setPrgList(prgList);
    }


    void oneStepForAllPrg(List<ProgramState> prgList) throws InterruptedException {
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        });

        List<Callable<ProgramState>> callList = prgList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());

        List<ProgramState> newPrgList = executor.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                    return null;
                })
                .filter(p -> p != null)
                .collect(Collectors.toList());
        prgList.addAll(newPrgList);
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        });
        repo.setPrgList(prgList);
    }

    public void executeOneStep()
    {
        executor = Executors.newFixedThreadPool(8);
        removeCompletedProgram(repo.getAllPrograms());
        List<ProgramState> programStates = repo.getAllPrograms();
        if(programStates.size() > 0)
        {
            try {
                oneStepForAllPrg(repo.getAllPrograms());
            } catch (InterruptedException e) {
                System.out.println();
            }
            programStates.forEach(e -> {
                try {
                    repo.logPrgStateExec(e);
                } catch (IOException e1) {
                    System.out.println();
                }
            });
            removeCompletedProgram(repo.getAllPrograms());
            executor.shutdownNow();
        }
    }

    public String getProgramName() { return programName; }

    public IRepository getRepo(){return repo;}
}
