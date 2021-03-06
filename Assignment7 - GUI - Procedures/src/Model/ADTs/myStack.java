package Model.ADTs;

import Exceptions.StackException;

import java.util.Stack;

public class myStack<T> implements IStack<T> {
    private Stack<T> stack;

    public myStack(){
        stack=new Stack<T>();
    }
    public myStack(Stack<T> s){
        stack=s;
    }


    @Override
    public T pop() throws StackException{
        if(this.stack.isEmpty()) throw new StackException("The stack is empty!");
        return stack.pop();
    }

    @Override
    public void push(T elem){
        stack.push(elem);
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for(T elem:stack)
            s.append(elem.toString()+" ");
        return s.toString();
    }

    @Override
    public Stack<T> getStack(){
        return stack;
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public int size(){
        if(stack.isEmpty())return 0;
        return stack.size();
    }

    @Override
    public IStack<T> clone() {
        return new myStack<T>((Stack)stack.clone());
    }
}
