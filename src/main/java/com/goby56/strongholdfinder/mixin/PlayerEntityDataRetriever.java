package com.goby56.strongholdfinder.mixin;

import com.goby56.strongholdfinder.utils.PlayerEntityDataCache;
import com.ibm.icu.impl.ValidIdentifiers;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

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








/*
@Mixin(Entity.class)
public abstract class PlayerEntityDataRetriever implements PlayerEntityDataCache {
    private double[] playerData;
    private Vec3d pos;
    private float yaw;

    public double playerX;
    public double playerY;
    public float playerYaw;

    @Override
    public double[] getPlayerData() {
        playerData = new double[]{playerX, playerY, playerYaw};
        return playerData;
    }

    @Inject(method = "getPos", at = @At("HEAD"))
    protected void injectGetPos(CallbackInfoReturnable info) {
        playerX = this.pos.x;
        playerY = this.pos.z;
    }

    @Inject(method = "getJaw", at = @At("HEAD"))
    protected void injectGetJaw(CallbackInfoReturnable info) {
        playerYaw = this.yaw;
    }


}
*/