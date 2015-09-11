package scripts.glassblower.nodes;

public abstract class Node {

    //*
    /* This class gets extended in 
     * each class you want to preform an action.
     * It cycles through a list of nodes,
     * If the isValid method returns true,
     * than the execute method will be called
     */
    public abstract boolean isValid(); 
    public abstract void execute();
    public abstract String getStatus();
}