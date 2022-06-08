package com.goby56.strongholdfinder.utils;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;

import static com.goby56.strongholdfinder.commands.LogPosition.logPos;

public class CommandRegister {
    public static void registerCommand() {
        ClientCommandRegistrationCallback.EVENT.register(((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommandManager.literal("logpos").executes((context -> {
                logPos(context);
                return 1;
            })));
        }));
    }
}
