package com.goby56.strongholdfinder.mixin;

import net.fabricmc.loader.impl.lib.sat4j.core.Vec;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(EnderEyeItem.class)
public abstract class EyeThrownMixin {
    private static Predicate<Entity> isVisible = entity -> !entity.isSpectator() && entity.collides();

    @Inject(method = "use", at = @At("TAIL"))
    protected void afterUse(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable info) {
        System.out.println("EYE THROWN");
        double reach = 60;
        Vec3d position = user.getCameraPosVec(0);
        Vec3d viewVec = user.getRotationVec(0F);
        Vec3d stoppingPoint = position.add(viewVec.x * reach, viewVec.y * reach, viewVec.z * reach);
        Box searchBox = user.getBoundingBox().stretch(viewVec.multiply(reach)).expand(1D, 1D, 1D);
        EntityHitResult hitResult = ProjectileUtil.raycast(user, position, stoppingPoint, searchBox, isVisible, reach*reach);
        if (hitResult != null && hitResult.getEntity() != null) {
            System.out.println(hitResult.getEntity().getName());
        }
    }
}
