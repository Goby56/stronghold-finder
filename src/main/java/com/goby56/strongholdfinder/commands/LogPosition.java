package com.goby56.strongholdfinder.commands;

import com.goby56.strongholdfinder.utils.PlayerEntityDataCache;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;

public class LogPosition {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("logpos").executes(LogPosition::logPos));
        //dispatcher.register(CommandManager.literal("logpos").then(CommandManager.literal("a").executes(LogPosition::setA)));
        //dispatcher.register(CommandManager.literal("logpos").then(CommandManager.literal("b").executes(LogPosition::setB)));
        //dispatcher.register(CommandManager.literal("logpos").then(CommandManager.literal("clear").executes(LogPosition::clear)));
    }

    public static int logPos(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntityDataCache playerData = (PlayerEntityDataCache)context.getSource().getPlayer();
        Entity player = context.getSource().getPlayer();
        double x = player.getX();
        double z = player.getZ();
        double yaw = player.getYaw();

        //Remove int casting and go with double
        int[] valuesArray = {(int)x, (int)z, (int)yaw};
        String valuesString = "x=" + x + ", z=" + z + ", yaw=" + yaw;

        playerData.getPlayerData().putDouble("xSFposA", x);
        playerData.getPlayerData().putDouble("zSFposA", z);
        playerData.getPlayerData().putDouble("yawSFposA", yaw);


        playerData.getPlayerData().putIntArray("SFposA", valuesArray);

        context.getSource().sendFeedback(new LiteralText("Logged" + valuesString), true);
        return 1;

    }



}

