package com.goby56.strongholdfinder.utils;

import com.goby56.strongholdfinder.commands.LogPosition;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.text.LiteralText;

import static com.goby56.strongholdfinder.commands.LogPosition.logPos;
import static com.goby56.strongholdfinder.math.StrongholdPosition.triangulatePosition;

public class CommandRegister {
    public static void registerCommand() {
        //CommandRegistrationCallback.EVENT.register(LogPosition::register);
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("logpos").executes(context -> {
            //logPos(context);

            Entity player = context.getSource().getPlayer();
            //Entity player = MinecraftClient.getInstance().player; alternative method
            PlayerEntityDataCache playerData = (PlayerEntityDataCache) MinecraftClient.getInstance().player;
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
                context.getSource().sendFeedback(new LiteralText("Stronghold located at x=" + x + ", z=" + z + ". Clearing log..."));
            }
            else {
                playerData.getPlayerData().put("SfPrevPos", valuesArray);
                context.getSource().sendFeedback(new LiteralText("Logged position. Provide second location..."));
            }
            return 1;
        }));
    }
}
