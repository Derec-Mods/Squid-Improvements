package io.github.derexxd.squidimprovements;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;

import java.util.concurrent.ThreadLocalRandom;

public class SquidImprovements implements ModInitializer {
    private static final double RANGE_SQUARED = 25.0;

    @Override
    public void onInitialize() {
        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (world.isClient()) return ActionResult.PASS;

            if (entity instanceof GlowSquidEntity) {
                GlowSquidEntity glowSquid = (GlowSquidEntity) entity;
                if (player.squaredDistanceTo(glowSquid) <= RANGE_SQUARED) {
                    ((ServerWorld) world).spawnParticles(ParticleTypes.END_ROD, glowSquid.getX(), glowSquid.getBodyY(0.5), glowSquid.getZ(), 24, 0.4, 0.4, 0.4, 0.02);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, ThreadLocalRandom.current().nextInt(150, 301), 0));
                }
            } else if (entity instanceof SquidEntity) {
                SquidEntity squid = (SquidEntity) entity;
                if (player.squaredDistanceTo(squid) <= RANGE_SQUARED) {
                    ((ServerWorld) world).spawnParticles(ParticleTypes.SQUID_INK, squid.getX(), squid.getBodyY(0.5), squid.getZ(), 24, 0.4, 0.4, 0.4, 0.02);
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ThreadLocalRandom.current().nextInt(40, 62), 0));
                }
            }

            return ActionResult.PASS;
        });
    }
}
