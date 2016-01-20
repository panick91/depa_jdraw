package jdraw.commands;

import jdraw.framework.DrawCommand;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Patrick on 16.01.2016.
 */
public class Script implements DrawCommand {

    public List<DrawCommand> commands = new LinkedList<>();

    @Override
    public void redo() {
        for (DrawCommand command : commands) command.redo();
    }

    @Override
    public void undo() {
        int size = commands.size();
        ListIterator<DrawCommand> it = commands.listIterator(size);
        while(it.hasPrevious()) it.previous().undo();
    }
}
