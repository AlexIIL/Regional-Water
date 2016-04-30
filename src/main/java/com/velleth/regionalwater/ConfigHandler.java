package com.velleth.regionalwater;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Zerren on 4/27/2016.
 */
public class ConfigHandler {

    public static Configuration config;

    public static int[] validDims = { 0 };
    public static int[] bannedDims = { -1, 1 };
    public static int[] oceanDims = { };
    public static boolean reverse = false;
    public static String[] validBiomeDictionary = { "OCEAN", "BEACH", "RIVER" };
    public static String[] bannedBiomeDictionary = { "NETHER", "END" };
    public static int waterLower = 0;
    public static int waterUpper = 255;

    public static void init(File configFile){

        if (config == null) {
            config = new Configuration(configFile);
            loadConfigValues();
            if (config.hasChanged())
                config.save();
        }

    }

    private static void loadConfigValues() {
        validDims = config.get("general", "validDims", validDims,
                "Dimension array to allow infinite source water to be created. Unused if 'reversed' is set to true").getIntList();

        bannedDims = config.get("general", "bannedDims", bannedDims,
                "Dimension array to ban infinite source water to be created (overrides 'validDims' and 'validBiomes')").getIntList();

        reverse = config.getBoolean("reversed", "general", reverse,
                "If water should create an infinite source normally (vanilla style), and disabling should be handled via 'bannedDims' and 'bannedBiomes");

        validBiomeDictionary = config.getStringList("validBiomes", "general", validBiomeDictionary,
                "Biome dictionary entries where infinite sources are allowed to be created, eg OCEAN, MAGICAL, or BEACH. Unused if 'reversed' is set to true");

        bannedBiomeDictionary = config.getStringList("bannedBiomes", "general", bannedBiomeDictionary,
                "Biome dictionary entries where infinite sources are NOT allowed to be created (overrides 'validDims' and 'validBiomes')");

        waterLower = config.getInt("waterLowerBounds", "general", waterLower, 0, 255,
                "The lowest block on the Y-axis that source water can form at. Must be lower than 'waterUpper'");

        waterUpper = config.getInt("waterUpperBounds", "general", waterUpper, 0, 255,
                "The highest block on the Y-axis that source water can form at. Must be higher than 'waterLower'");

        oceanDims = config.get("general", "oceanDims", oceanDims,
                "Dimension array that allows water regeneration regardless of biome (overrides all other config settings)").getIntList();
    }

    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase("regionalwater")) {
            loadConfigValues();
        }
    }
}
