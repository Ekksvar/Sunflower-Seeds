package com.ekksvar.sunflowerseeds.events;

import com.ekksvar.sunflowerseeds.SunflowerSeeds;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = SunflowerSeeds.MOD_ID, bus = Bus.FORGE)
public class SunflowerLootTableLoad {
	
	@SubscribeEvent
	public static void SunflowerLootChange(LootTableLoadEvent event) {
		if(event.getName().getPath().equals("blocks/sunflower")) {
			event.getTable().removePool("main");
		}
	}

}
