package AmazeingGui.CustomActionListeners.TuiListeners;

import AmazeingGui.CustomActionListeners.CustomActionListener;
import AmazeingGui.CustomEvent.CustomEvent;
import AmazeingGui.CustomEvent.MazeFileReadEvent;
import AmazeingGui.CLI;
import AmazeingGui.TuiStates.InstructionAwaitState;

public class CliFileReadListener implements CustomActionListener {
    private final CLI cli;

    public CliFileReadListener(CLI cli)
    {
        this.cli = cli;
    }

    @Override
    public void call(CustomEvent event) {
        if(!(event instanceof MazeFileReadEvent mazeFileReadEvent))
            return;

        cli.setMazeData(mazeFileReadEvent.getNewMaze());

        cli.printToStream("Wczytano labirynt z: " + mazeFileReadEvent.getSource());

        if(mazeFileReadEvent.getNewMaze().getEntry() != null && mazeFileReadEvent.getNewMaze().getExit() != null)
            cli.changeState(new InstructionAwaitState(cli, false));
        else
            cli.changeState(new InstructionAwaitState(cli, true));

    }
}
