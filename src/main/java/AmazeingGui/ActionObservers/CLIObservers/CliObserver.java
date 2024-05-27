package AmazeingGui.ActionObservers.CLIObservers;

import AmazeingGui.CLI;
import AmazeingGui.ActionObservers.ActionObserver;

abstract class CliObserver implements ActionObserver {
    CLI cli;
    public CliObserver(CLI cli) {
        this.cli = cli;
    }
}
