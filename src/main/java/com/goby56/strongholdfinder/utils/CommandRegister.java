package com.goby56.strongholdfinder.utils;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;

import static com.goby56.strongholdfinder.commands.LogPosition.logPos;

public class CommandRegister {
    public static void registerCommand() {
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("logpos").executes(context -> {
            logPos(context);
            return 1;
        }));
    }
}
