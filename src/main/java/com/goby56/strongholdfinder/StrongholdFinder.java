package com.goby56.strongholdfinder;

import com.goby56.strongholdfinder.utils.CommandRegister;
import net.fabricmc.api.ClientModInitializer;
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

		CommandRegister.registerCommand();

		/**
		 * Take a look at this
		 * https://fabricmc.net/wiki/tutorial:events
		 *
		 * Try this for raycasting
		 * https://fabricmc.net/wiki/tutorial:pixel_raycast
		 *
		 * This could be good for raycasting
		 * https://github.com/ToroCraft/ToroHealth/blob/master/src/main/java/net/torocraft/torohealth/util/RayTrace.java
		 */


		//EventListener.EMPTY.addListener(new GameEventListener() );

		LOGGER.info("Hello Fabric world!");
	}
}
