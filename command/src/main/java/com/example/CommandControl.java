package com.example;

/**
 * 本类由: Risky 创建于: 15/12/15.
 */
public class CommandControl {
    private Command [] onCommands;
    private Command [] offCommands;

    private Command undoCommand;

    public CommandControl() {
        onCommands = new Command[7];
        offCommands= new Command[7];
        Command noCommand = new NoCommand();
        for (int i = 0; i < onCommands.length; i++) {
            onCommands[i] = noCommand;
            offCommands[i]= noCommand;
        }
    }

    public void onPressed(int index){
        onCommands[index].execute();
        undoCommand = onCommands[index];
    }

    public void offPressed(int index){
        offCommands[index].execute();
        undoCommand = offCommands[index];
    }

    public void undoPressed(){
        undoCommand.undo();
    }

    public void setCommand(int index,Command onCommand,Command offCommand) {
        this.onCommands[index] = onCommand;
        this.offCommands[index]= offCommand;
    }
}
