package com.goby56.strongholdfinder.commands;

import com.goby56.strongholdfinder.utils.PlayerEntityDataCache;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.fabricmc.fabric.api.util.NbtType; //NbtType Constants!!!!

import static com.goby56.strongholdfinder.math.StrongholdPosition.triangulatePosition;

public class LogPosition {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(CommandManager.literal("logpos").executes(LogPosition::logPos));
    }

    public static int logPos(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        PlayerEntityDataCache playerData = (PlayerEntityDataCache)context.getSource().getPlayer();
        Entity player = context.getSource().getPlayer();
        var valuesArray = new NbtList();

        valuesArray.add(NbtDouble.of(player.getX()));
        valuesArray.add(NbtDouble.of(player.getZ()));
        valuesArray.add(NbtDouble.of(player.getYaw()));

        if (playerData.getPlayerData().contains("SfPrevPos")) {
            NbtList nbtList = playerData.getPlayerData().getList("SfPrevPos", NbtType.DOUBLE);

            double[] prevPos = {nbtList.getDouble(0), nbtList.getDouble(1), nbtList.getDouble(2)};
            double[] currPos = {player.getX(), player.getZ(), player.getYaw()};

            int[] strongholdPosition = triangulatePosition(prevPos, currPos);
            int x = strongholdPosition[0];
            int z = strongholdPosition[1];

            playerData.getPlayerData().remove("SfPrevPos");
            context.getSource().sendFeedback(new LiteralText("Stronghold located at x=" + x + ", z=" + z + ". Clearing log..."), true);
        }
        else {
            playerData.getPlayerData().put("SfPrevPos", valuesArray);
            context.getSource().sendFeedback(new LiteralText("Logged position. Provide second location..."), true);
        }
        return 1;
    }
}