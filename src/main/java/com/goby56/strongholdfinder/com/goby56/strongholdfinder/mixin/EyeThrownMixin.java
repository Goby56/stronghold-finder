package com.goby56.strongholdfinder.com.goby56.strongholdfinder.mixin;

import com.goby56.strongholdfinder.utils.EntityRaycaster;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;


@Mixin(EnderEyeItem.class)
public abstract class EyeThrownMixin {
    private static Predicate<Entity> predicate = entity -> !entity.isSpectator();

    @Inject(method = "use", at = @At("TAIL"))
    protected void afterUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable info) {
        EntityRaycaster.setDelay(4);
    }
}
