package com.ekksvar.sunflowerseeds.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileItemEntity;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SunflowerShellEntity extends ProjectileItemEntity 
{
	private int knockbackStrength;
	
	public SunflowerShellEntity(EntityType<? extends SnowballEntity> p_i50159_1_, World p_i50159_2_) {
		super(p_i50159_1_, p_i50159_2_);
	}

	public SunflowerShellEntity(World worldIn, LivingEntity throwerIn) {
		super(EntityType.SNOWBALL, throwerIn, worldIn);
	}

	public SunflowerShellEntity(World worldIn, double x, double y, double z) {
		super(EntityType.SNOWBALL, x, y, z, worldIn);
	}

	protected Item func_213885_i() {
		return Items.SNOWBALL;
	}
   
	@OnlyIn(Dist.CLIENT)
	private IParticleData func_213887_n() 
	{
		ItemStack itemstack = this.func_213882_k();
		return (IParticleData)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleData(ParticleTypes.ITEM, itemstack));
	}
	
	/**
	 * Handler for {@link World#setEntityState}
	 */
	
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) 
	{
		if (id == 3) 
		{
			IParticleData iparticledata = this.func_213887_n();
			for(int i = 0; i < 8; ++i) 
			{
				this.world.addParticle(iparticledata, this.func_226277_ct_(), this.func_226278_cu_(), this.func_226281_cx_(), 0.0D, 0.0D, 0.0D);
			}
		}
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(RayTraceResult result) {
		if (result.getType() == RayTraceResult.Type.ENTITY) {
			Entity entity = ((EntityRayTraceResult)result).getEntity();
			
			if (entity instanceof LivingEntity) 
			{
				LivingEntity livingentity = (LivingEntity)entity;
				
				if (this.knockbackStrength > 0) 
				{
					Vec3d vec3d = this.getMotion().mul(1.0D, 0.0D, 1.0D).normalize().scale((double)this.knockbackStrength * 0.6D);
					if (vec3d.lengthSquared() > 0.0D) 
					{
						livingentity.addVelocity(vec3d.x, 0.1D, vec3d.z);
					}
				}
			}
	         
			entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)0);
		}

		if (!this.world.isRemote) {
			this.world.setEntityState(this, (byte)3);
			this.remove();
		}

	}

	
	public void setKnockbackStrength(int knockbackStrengthIn) {
		this.knockbackStrength = knockbackStrengthIn;
	}

}
