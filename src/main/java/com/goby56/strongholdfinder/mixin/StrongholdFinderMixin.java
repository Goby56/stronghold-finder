package com.goby56.strongholdfinder.mixin;

import com.goby56.strongholdfinder.StrongholdFinder;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class StrongholdFinderMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		StrongholdFinder.LOGGER.info("This line is printed by an example mod mixin!");
	}
}