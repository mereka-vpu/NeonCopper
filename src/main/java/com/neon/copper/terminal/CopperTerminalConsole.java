package com.neon.copper.terminal;

import net.minecrell.terminalconsole.SimpleTerminalConsole;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

public class CopperTerminalConsole extends SimpleTerminalConsole {

    private final CommandManager commandManager = MinecraftServer.getCommandManager();

    @Override
    protected LineReader buildReader(LineReaderBuilder builder) {
        return super.buildReader(builder
                .appName("Copper")
                .completer(new CopperConsoleCommandCompleter()));
    }

    @Override
    protected boolean isRunning() {
        return MinecraftServer.isStarted() && !MinecraftServer.isStopping();
    }

    @Override
    protected void runCommand(String command) {
        commandManager.execute(commandManager.getConsoleSender(), command);
    }

    @Override
    protected void shutdown() {
        MinecraftServer.LOGGER.info("Shutting down server...");
        try {
            MinecraftServer.stopCleanly();
            System.exit(0);
        } catch (Throwable t) {
            System.exit(1);
        }
    }

}