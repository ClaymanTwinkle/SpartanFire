package com.chaosbuffalo.spartanfire.integrations;
import com.github.alexthe666.iceandfire.entity.EntityIceDragon;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.trait.MeleeCallbackWeaponTrait;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

public class FireSwordWeaponProperty extends MeleeCallbackWeaponTrait {

    public FireSwordWeaponProperty(String propType, String propModId) {
        super(propType, propModId, TraitQuality.POSITIVE);
    }

    public void onHitEntity(WeaponMaterial material, ItemStack stack, LivingEntity target, LivingEntity attacker, Entity projectile) {
        if (target instanceof EntityIceDragon) {
            target.attackEntityFrom(DamageSource.IN_FIRE, 13.5F);
        }
        target.setFire(5);
        target.applyKnockback(0.5F, MathHelper.sin(attacker.rotationYaw * ((float)Math.PI / 180F)), -MathHelper.cos(attacker.rotationYaw * ((float)Math.PI / 180F)));
    }
}
