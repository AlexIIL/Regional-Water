package com.velleth.regionalwater;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Zerren on 4/27/2016.
 */

public class RWModContainer extends DummyModContainer {

    //public static Logger log;

    public RWModContainer() {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "regionalwater";
        meta.name = "Regional Water";
        meta.description = "Enables infinite water in only certain biomes and dimensions that are specified in the config file";
        meta.version = "1.11.2-1.1";
        meta.logoFile = "assets/regionalwater/regionalwaterlogo.png";
        meta.url = "https://github.com/Zerrens/Regional-Water";
        meta.authorList = Arrays.asList("Velleth");
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void preInit(FMLPreInitializationEvent event) {
        //log = event.getModLog();
        ConfigHandler.init(event.getSuggestedConfigurationFile());
    }
}
