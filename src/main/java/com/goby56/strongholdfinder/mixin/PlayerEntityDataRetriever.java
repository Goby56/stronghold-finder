package com.goby56.strongholdfinder.mixin;

import com.goby56.strongholdfinder.utils.PlayerEntityDataCache;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
public abstract class PlayerEntityDataRetriever implements PlayerEntityDataCache {
    private NbtCompound playerData;

    @Override
    public NbtCompound getPlayerData() {
        if(this.playerData == null) {
            this.playerData = new NbtCompound();
        }
        return playerData;
    }
}