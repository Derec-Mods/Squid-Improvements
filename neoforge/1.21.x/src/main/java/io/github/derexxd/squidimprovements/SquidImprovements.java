package io.github.derexxd.squidimprovements;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.animal.Squid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

import java.util.concurrent.ThreadLocalRandom;

@Mod("squidimprovements")
@EventBusSubscriber(modid = "squidimprovements")
public class SquidImprovements {

    private static final double RANGE_SQUARED = 25.0;

    public SquidImprovements() {
    }

    @SubscribeEvent
    public static void onAttackEntity(AttackEntityEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        if (event.getTarget() instanceof GlowSquid) {
            GlowSquid glowSquid = (GlowSquid) event.getTarget();
            if (event.getEntity().distanceToSqr(glowSquid) <= RANGE_SQUARED) {
                ((ServerLevel) glowSquid.level()).sendParticles(ParticleTypes.END_ROD, glowSquid.getX(), glowSquid.getY(0.5), glowSquid.getZ(), 24, 0.4, 0.4, 0.4, 0.02);
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.GLOWING, ThreadLocalRandom.current().nextInt(150, 301), 0));
            }
        } else if (event.getTarget() instanceof Squid) {
            Squid squid = (Squid) event.getTarget();
            if (event.getEntity().distanceToSqr(squid) <= RANGE_SQUARED) {
                ((ServerLevel) squid.level()).sendParticles(ParticleTypes.SQUID_INK, squid.getX(), squid.getY(0.5), squid.getZ(), 24, 0.4, 0.4, 0.4, 0.02);
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.BLINDNESS, ThreadLocalRandom.current().nextInt(40, 62), 0));
            }
        }
    }
}
