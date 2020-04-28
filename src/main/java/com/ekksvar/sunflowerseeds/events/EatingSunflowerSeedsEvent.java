package com.ekksvar.sunflowerseeds.events;

import java.util.Random;

import com.ekksvar.sunflowerseeds.SunflowerSeeds;
import com.ekksvar.sunflowerseeds.entity.SunflowerShellEntity;
import com.ekksvar.sunflowerseeds.init.ItemInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = SunflowerSeeds.MOD_ID, bus = Bus.FORGE)
public class EatingSunflowerSeedsEvent 
{
	
	@SubscribeEvent
	public static void SunflowerSeedEatingEvent(LivingEntityUseItemEvent.Finish event) 
	{
		LivingEntity player = event.getEntityLiving();
		World world = player.getEntityWorld();
		Random rand = new Random();
		ItemStack[] shells = new ItemStack[] {new ItemStack(ItemInit.sunflower_seed_shell0), new ItemStack(ItemInit.sunflower_seed_shell1), new ItemStack(ItemInit.sunflower_seed_shell2)};
		ItemStack[] roastedShells = new ItemStack[] {new ItemStack(ItemInit.roasted_sunflower_seed_shell0), new ItemStack(ItemInit.roasted_sunflower_seed_shell1), new ItemStack(ItemInit.roasted_sunflower_seed_shell2)};
		
		//raw seeds
		if (event.getItem().getItem().equals(ItemInit.sunflower_seeds)) {

			if(!world.isRemote) 
			{
				int shellNumber = rand.nextInt(3);
				SunflowerSeeds.LOGGER.info("Player ate sunflower seeds, creating projectile");
				SunflowerShellEntity shellentity = new SunflowerShellEntity(world, player);
				shellentity.func_213884_b(shells[shellNumber]);
				shellentity.setKnockbackStrength(2);
				shellentity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
		        world.addEntity(shellentity);
				
			}
		}
		//roasted seeds
		if (event.getItem().getItem().equals(ItemInit.roasted_sunflower_seeds)) {

			if(!world.isRemote) 
			{
				int shellNumber = rand.nextInt(3);
				SunflowerSeeds.LOGGER.info("Player ate roasted sunflower seeds, creating projectile");
				SunflowerShellEntity shellentity = new SunflowerShellEntity(world, player);
				shellentity.func_213884_b(roastedShells[shellNumber]);
				shellentity.setKnockbackStrength(6);
				shellentity.shoot(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
		        world.addEntity(shellentity);
				
			}
		}
	}
}
