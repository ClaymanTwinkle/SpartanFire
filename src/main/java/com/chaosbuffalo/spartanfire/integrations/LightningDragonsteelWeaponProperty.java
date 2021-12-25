package com.chaosbuffalo.spartanfire.integrations;

import com.github.alexthe666.iceandfire.IafConfig;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.trait.MeleeCallbackWeaponTrait;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.server.ServerWorld;

import java.util.Collections;

public class LightningDragonsteelWeaponProperty extends MeleeCallbackWeaponTrait {

    public LightningDragonsteelWeaponProperty(String propType, String propModId) {
        super(propType, propModId, TraitQuality.POSITIVE);
    }

    public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker, Entity projectile) {
        if (IafConfig.dragonWeaponLightningAbility) {
            boolean flag = true;
            if (attacker instanceof PlayerEntity) {
                if (attacker.swingProgress > 0.2) {
                    flag = false;
                }
            }
            if (!attacker.world.isRemote && flag) {
                LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(target.world);
                if (lightningboltentity != null) {
                    lightningboltentity.setCaster(attacker instanceof ServerPlayerEntity ? (ServerPlayerEntity) attacker : null);
                    lightningboltentity.lightningState = 1;
                    lightningboltentity.moveForced(target.getPositionVec());
                    if (!target.world.isRemote) {
                        target.world.addEntity(lightningboltentity);
                    }
                }
            }
            target.addPotionEffect(new EffectInstance(Effects.GLOWING, 300, 2));
            target.applyKnockback(0.5F, MathHelper.sin(attacker.rotationYaw * ((float) Math.PI / 180F)), -MathHelper.cos(attacker.rotationYaw * ((float) Math.PI / 180F)));
        }
    }
}
