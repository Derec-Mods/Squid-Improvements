package io.github.derexxd.squidImprovements.listeners;

import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.ThreadLocalRandom;

public final class SquidDamageListener implements Listener {

    private static final double INK_RANGE = 5.0;

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) {
            return;
        }
        if (!(event.getEntity() instanceof Squid squid)) {
            return;
        }
        if (player.getLocation().distanceSquared(squid.getLocation()) > INK_RANGE * INK_RANGE) {
            return;
        }

        squid.getWorld().spawnParticle(
                Particle.SQUID_INK,
                squid.getLocation(),
                24,
                0.4,
                0.4,
                0.4,
                0.02
        );

        player.addPotionEffect(new PotionEffect(
                PotionEffectType.BLINDNESS,
                ThreadLocalRandom.current().nextInt(40, 61),
                0
        ));
    }
}
