package io.github.derexxd.squidimprovements;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ThreadLocalRandom;

@Mod(SquidImprovements.MOD_ID)
public final class SquidImprovements {
    public static final String MOD_ID = "squidimprovements";

    private static final double EFFECT_RANGE = 5.0;

    public SquidImprovements() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (!(event.getSource().getDirectEntity() instanceof Player player)) {
            return;
        }

        LivingEntity target = event.getEntity();
        if (target.level().isClientSide() || player.distanceToSqr(target) > EFFECT_RANGE * EFFECT_RANGE) {
            return;
        }

        if (target instanceof GlowSquid) {
            spawnParticles(target, ParticleTypes.END_ROD);
            player.addEffect(new MobEffectInstance(
                    MobEffects.GLOWING,
                    ThreadLocalRandom.current().nextInt(150, 300),
                    0
            ));
            return;
        }

        if (target instanceof Squid) {
            spawnParticles(target, ParticleTypes.SQUID_INK);
            player.addEffect(new MobEffectInstance(
                    MobEffects.BLINDNESS,
                    ThreadLocalRandom.current().nextInt(40, 61),
                    0
            ));
        }
    }

    private static void spawnParticles(LivingEntity target, ParticleOptions particle) {
        if (target.level() instanceof ServerLevel level) {
            level.sendParticles(particle, target.getX(), target.getY(), target.getZ(), 24, 0.4, 0.4, 0.4, 0.02);
        }
    }
}
