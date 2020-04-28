package com.ekksvar.sunflowerseeds.events;

import java.util.Random;

import com.ekksvar.sunflowerseeds.SunflowerSeeds;
import com.ekksvar.sunflowerseeds.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = SunflowerSeeds.MOD_ID, bus = Bus.FORGE)
public class SunflowerSeedsDropEvent 
{
	@SubscribeEvent
	public static void OnBlockBreakEvent(BlockEvent.BreakEvent event) 
	{
		if(event.getState().getBlock().getRegistryName().toString().equals("minecraft:sunflower")) 
		{
			if(event.getPlayer().isCreative())
				return;
			
			ItemStack heldItem = event.getPlayer().getHeldItemMainhand();
			
			if(!(heldItem.getItem().equals(Items.BAKED_POTATO) || heldItem.getItem().equals(Items.WOODEN_HOE) || heldItem.getItem().equals(Items.STONE_HOE) 
					|| heldItem.getItem().equals(Items.IRON_HOE) || heldItem.getItem().equals(Items.GOLDEN_HOE) || heldItem.getItem().equals(Items.DIAMOND_HOE))) {
				Block.spawnAsEntity(event.getWorld().getWorld(), event.getPos(), new ItemStack(Items.SUNFLOWER, 1));
				return;
			}
			
			int fortuneLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, heldItem);
			Random rand = new Random();
			int seedNumber;
			if(fortuneLevel == 1)
				seedNumber = 1 + rand.nextInt(3);
			else if(fortuneLevel == 2)
				seedNumber = 2 + rand.nextInt(3);
			else if(fortuneLevel == 3)
				seedNumber = 2 + rand.nextInt(4);
			else
				seedNumber = rand.nextInt(3);
			Block.spawnAsEntity(event.getWorld().getWorld(), event.getPos(), new ItemStack(ItemInit.sunflower_seeds, seedNumber));
			
		}
	}
}
