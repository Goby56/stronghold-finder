package com.goby56.strongholdfinder.utils;

import com.goby56.strongholdfinder.commands.LogPosition;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class CommandRegister {
    public static void registerCommand() {
        CommandRegistrationCallback.EVENT.register(LogPosition::register);
    }
}
