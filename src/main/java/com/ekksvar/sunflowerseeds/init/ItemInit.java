package com.ekksvar.sunflowerseeds.init;

import com.ekksvar.sunflowerseeds.SunflowerSeeds;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = SunflowerSeeds.MOD_ID, bus = Bus.MOD)
@ObjectHolder(SunflowerSeeds.MOD_ID)
public class ItemInit 
{
	public static final Item sunflower_seeds = null;
	public static final Item roasted_sunflower_seeds = null;
	public static final Item sunflower_seed_shell0 = null;
	public static final Item sunflower_seed_shell1 = null;
	public static final Item sunflower_seed_shell2 = null;
	public static final Item roasted_sunflower_seed_shell0 = null;
	public static final Item roasted_sunflower_seed_shell1 = null;
	public static final Item roasted_sunflower_seed_shell2 = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event) 
	{
		event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(1).fastToEat().build())).setRegistryName("sunflower_seeds"));
		event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.FOOD).food(new Food.Builder().hunger(2).saturation(0.3f).fastToEat().build())).setRegistryName("roasted_sunflower_seeds"));
		event.getRegistry().register(new Item(new Item.Properties()).setRegistryName("sunflower_seed_shell0"));
		event.getRegistry().register(new Item(new Item.Properties()).setRegistryName("sunflower_seed_shell1"));
		event.getRegistry().register(new Item(new Item.Properties()).setRegistryName("sunflower_seed_shell2"));
		event.getRegistry().register(new Item(new Item.Properties()).setRegistryName("roasted_sunflower_seed_shell0"));
		event.getRegistry().register(new Item(new Item.Properties()).setRegistryName("roasted_sunflower_seed_shell1"));
		event.getRegistry().register(new Item(new Item.Properties()).setRegistryName("roasted_sunflower_seed_shell2"));
	}
}

