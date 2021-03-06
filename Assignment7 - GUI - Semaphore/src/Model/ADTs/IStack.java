package Model.ADTs;

import Exceptions.StackException;

import java.util.Stack;

public interface IStack<T> {
    T pop() throws StackException;
    void push(T elem);
    boolean isEmpty();
    String toString();
    Stack<T> getStack();
    void clear();
    int size();
}
