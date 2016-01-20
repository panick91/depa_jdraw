/*
 * Copyright (c) 2000-2016 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import jdraw.commands.GroupFigureCommand;
import jdraw.figures.*;
import jdraw.framework.*;
import jdraw.grid.SimpleGrid;

/**
 * Standard implementation of interface DrawContext.
 *
 * @author Dominik Gruntz & Christoph Denzler
 * @version 2.6, 24.09.09
 * @see DrawView
 */
public class StdContext extends AbstractContext {

    /**
     * Constructs a standard context with a default set of drawing tools.
     *
     * @param view the view that is displaying the actual drawing.
     */
    public StdContext(DrawView view) {
        super(view, null);
    }

    /**
     * Constructs a standard context. The drawing tools available can be parameterized using <code>toolFactories</code>.
     *
     * @param view          the view that is displaying the actual drawing.
     * @param toolFactories a list of DrawToolFactories that are available to the user
     */
    public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
        super(view, toolFactories);
    }


    private List<Figure> clipboard;
    private int pasteCounter = 0;

    /**
     * Creates and initializes the "Edit" menu.
     *
     * @return the new "Edit" menu.
     */
    @Override
    protected JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        final JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        editMenu.add(undo);
        undo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final DrawCommandHandler h = getModel().getDrawCommandHandler();
                if (h.undoPossible()) {
                    h.undo();
                }
            }
        });

        final JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
        editMenu.add(redo);
        redo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final DrawCommandHandler h = getModel().getDrawCommandHandler();
                if (h.redoPossible()) {
                    h.redo();
                }
            }
        });
        editMenu.addSeparator();

        JMenuItem sa = new JMenuItem("SelectAll");
        sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
        editMenu.add(sa);
        sa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Figure f : getModel().getFigures()) {
                    getView().addToSelection(f);
                }
                getView().repaint();
            }
        });

        editMenu.addSeparator();
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(e -> {
            List<Figure> selection = getView().getSelection();
            if (selection != null && selection.size() > 0) {
                clipboard = null;
                pasteCounter = -1;
                clipboard = new LinkedList<>(selection);
                for (Figure f : selection) {
                    getModel().removeFigure(f);
                }
            }

        });
        editMenu.add(cut);
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(e -> {
            List<Figure> selection = getView().getSelection();
            if (selection != null && selection.size() > 0) {
                clipboard = null;
                pasteCounter = 0;
                clipboard = new LinkedList<>(selection);
            }
        });
        editMenu.add(copy);
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(e -> {
            getView().clearSelection();
            if (clipboard != null && clipboard.size() > 0) {
                DrawModel m = getModel();
                for (Figure f : new LinkedList<>(clipboard)) {
                    Figure newFig = f.clone();
                    newFig.move(++pasteCounter * 10, 0);
                    m.addFigure(newFig);
                    getView().addToSelection(newFig);
                }
            }
        });
        editMenu.add(paste);

        editMenu.addSeparator();
        JMenuItem group = new JMenuItem("Group");
        group.addActionListener(e -> {
            List<Figure> selection = getView().getSelection();
            if (selection != null && selection.size() > 1) {
                Group g = new Group(selection, getModel());
                DrawModel m = getModel();
                for (Figure f : selection) {
                    m.removeFigure(f);
                }
                m.addFigure(g);
                getView().addToSelection(g);
                getModel().getDrawCommandHandler().addCommand(new GroupFigureCommand(getModel(),g,true));
            }
        });
        editMenu.add(group);

        JMenuItem ungroup = new JMenuItem("Ungroup");
        ungroup.addActionListener(e -> {
            for (Figure g : getView().getSelection()) {
                if (g instanceof FigureGroup) {
                    getModel().removeFigure(g);
                    for (Figure f : ((FigureGroup) g).getFigureParts()) {
                        getModel().addFigure(f);
                        getView().addToSelection(f);
                    }
                    getModel().getDrawCommandHandler().addCommand(new GroupFigureCommand(getModel(),(FigureGroup) g,false));
                }
            }

        });
        editMenu.add(ungroup);

        editMenu.addSeparator();

        JMenu orderMenu = new JMenu("Order...");
        JMenuItem frontItem = new JMenuItem("Bring To Front");
        frontItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bringToFront(getView().getModel(), getView().getSelection());
                getView().repaint();
            }
        });
        orderMenu.add(frontItem);
        JMenuItem backItem = new JMenuItem("Send To Back");
        backItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendToBack(getView().getModel(), getView().getSelection());
                getView().repaint();
            }
        });
        orderMenu.add(backItem);
        editMenu.add(orderMenu);

        JMenu grid = new JMenu("Grid...");
        JMenuItem simpleGrid = new JMenuItem("20 Grid");
        simpleGrid.addActionListener(e -> getView().setConstrainer(new SimpleGrid()));
        grid.add(simpleGrid);
        JMenuItem noGrid = new JMenuItem("Deactivate grid");
        noGrid.addActionListener(e -> getView().setConstrainer(null));
        grid.add(noGrid);

        editMenu.add(grid);
        editMenu.addSeparator();

        JMenuItem decorator = new JMenuItem("Border");
        decorator.addActionListener(e -> {
            List<Figure> s = getView().getSelection();
            getView().clearSelection();
            for (Figure f : s) {
                BorderDecorator dec = new BorderDecorator(f);
                getModel().removeFigure(f);
                getModel().addFigure(dec);
                getView().addToSelection(dec);
            }
        });
        editMenu.add(decorator);

        JMenuItem bundle = new JMenuItem("Bundle");
        bundle.addActionListener(e -> {
            List<Figure> s = getView().getSelection();
            getView().clearSelection();
            for (Figure f : s) {
                BundleDecorator dec = new BundleDecorator(f);
                getModel().removeFigure(f);
                getModel().addFigure(dec);
                getView().addToSelection(dec);
            }
        });
        editMenu.add(bundle);


        return editMenu;
    }

    /**
     * Creates and initializes items in the file menu.
     *
     * @return the new "File" menu.
     */
    @Override
    protected JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        fileMenu.add(open);
        open.setAccelerator(KeyStroke.getKeyStroke("control O"));
        open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doOpen();
            }
        });

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
        fileMenu.add(save);
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doSave();
            }
        });

        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return fileMenu;
    }

    @Override
    protected void doRegisterDrawTools() {
        // TODO Add new figure tools here
        DrawTool rectangleTool = new RectTool(this, "Rectangle", "rectangle.png");
        addTool(rectangleTool);
        DrawTool circleTool = new OvalTool(this, "Oval", "oval.png");
        addTool(circleTool);
        LineTool lineTool = new LineTool(this, "Line", "line.png");
        addTool(lineTool);
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the front, i.e. moves them to the end of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to front
     */
    public void bringToFront(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in
        // the model
        List<Figure> orderedSelection = new LinkedList<Figure>();
        int pos = 0;
        for (Figure f : model.getFigures()) {
            pos++;
            if (selection.contains(f)) {
                orderedSelection.add(0, f);
            }
        }
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, --pos);
        }
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the back, i.e. moves them to the front of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to the back
     */
    public void sendToBack(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in
        // the model
        List<Figure> orderedSelection = new LinkedList<Figure>();
        for (Figure f : model.getFigures()) {
            if (selection.contains(f)) {
                orderedSelection.add(f);
            }
        }
        int pos = 0;
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, pos++);
        }
    }

    /**
     * Handles the saving of a drawing to a file.
     */
    private void doSave() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("")
                .getFile());
        chooser.setDialogTitle("Save Graphic");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        FileFilter filter = new FileFilter() {
            @Override
            public String getDescription() {
                return "JDraw Graphic (*.draw)";
            }

            @Override
            public boolean accept(File f) {
                return f.getName().endsWith(".draw");
            }
        };
        chooser.setFileFilter(filter);
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // save graphic
            File file = chooser.getSelectedFile();
            if (chooser.getFileFilter() == filter && !filter.accept(file)) {
                file = new File(chooser.getCurrentDirectory(), file.getName() + ".draw");
            }
            System.out.println("save current graphic to file " + file.getName());

            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));

                for (Figure f : getModel().getFigures()) {
                    outputStream.writeObject(f.clone());
                }

                outputStream.writeObject(null);

                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the opening of a new drawing from a file.
     */
    private void doOpen() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("")
                .getFile());
        chooser.setDialogTitle("Open Graphic");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public String getDescription() {
                return "JDraw Graphic (*.draw)";
            }

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".draw");
            }
        });
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // read jdraw graphic
            System.out.println("read file "
                    + chooser.getSelectedFile().getName());

            getModel().removeAllFigures();

            try {
                ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()));

                Object importObj = inputStream.readObject();
                while (importObj != null) {
                    getModel().addFigure((Figure) importObj);
                    importObj = inputStream.readObject();
                }

                inputStream.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}
