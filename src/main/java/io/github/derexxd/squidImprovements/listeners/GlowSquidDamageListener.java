package io.github.derexxd.squidImprovements.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.GlowSquid;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ThreadLocalRandom;

public final class GlowSquidDamageListener implements Listener {

    private static final double GLOW_RANGE = 5.0;

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }
        if (!(event.getEntity() instanceof GlowSquid glowSquid)) {
            return;
        }
        if (player.getLocation().distanceSquared(glowSquid.getLocation()) > GLOW_RANGE * GLOW_RANGE) {
            return;
        }

        glowSquid.getWorld().spawnParticle(
                Particle.END_ROD,
                glowSquid.getLocation(),
                24,
                0.4,
                0.4,
                0.4,
                0.02
        );

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.GLOWING,
                ThreadLocalRandom.current().nextInt(40, 61),
                0
        ));
    }
}
