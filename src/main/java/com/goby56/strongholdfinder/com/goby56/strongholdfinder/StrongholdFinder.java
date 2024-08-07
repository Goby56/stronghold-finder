package com.goby56.strongholdfinder.com.goby56.strongholdfinder;

import com.goby56.strongholdfinder.utils.EntityRaycaster;
import com.goby56.strongholdfinder.utils.NbtHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StrongholdFinder implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("strongholdfinder");

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ClientTickEvents.END_CLIENT_TICK.register(EntityRaycaster::tick);
		ClientTickEvents.END_CLIENT_TICK.register(NbtHandler::tick);
	}
}
