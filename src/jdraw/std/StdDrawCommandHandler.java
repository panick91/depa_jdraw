package jdraw.std;

import jdraw.commands.Script;
import jdraw.framework.DrawCommand;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Patrick on 16.01.2016.
 */
public class StdDrawCommandHandler implements jdraw.framework.DrawCommandHandler {

    private Stack<DrawCommand> undoStack = new Stack<>();
    private Stack<DrawCommand> redoStack = new Stack<>();
    private Script script = null;


    @Override
    public void addCommand(DrawCommand cmd) {
        if (cmd == null) throw new IllegalArgumentException();
        redoStack.clear();

        if (script == null) {
            undoStack.push(cmd);
        } else {
            script.commands.add(cmd);
        }
    }

    @Override
    public void undo() {
        if (undoPossible()) {
            DrawCommand cmd = undoStack.pop();
            redoStack.push(cmd);
            cmd.undo();
        }
    }

    @Override
    public void redo() {
        if (redoPossible()) {
            DrawCommand cmd = redoStack.pop();
            undoStack.push(cmd);
            cmd.redo();
        }

    }

    @Override
    public boolean undoPossible() {
        return undoStack.size() > 0;
    }

    @Override
    public boolean redoPossible() {
        return redoStack.size() > 0;
    }

    @Override
    public void beginScript() {
        if (script != null) throw new IllegalStateException();
        script = new Script();
    }

    @Override
    public void endScript() {
        if (script == null) throw new IllegalStateException();
        Script tmp = script;
        script = null;
        if (tmp.commands.size() > 0) addCommand(tmp);
    }

    @Override
    public void clearHistory() {
        undoStack.clear();
        redoStack.clear();
    }
}
