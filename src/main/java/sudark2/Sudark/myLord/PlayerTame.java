package sudark2.Sudark.myLord;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ConcurrentHashMap;

public class PlayerTame implements Listener {

    static ConcurrentHashMap<Player, Mob> tameMap = new ConcurrentHashMap<Player, Mob>();

    @EventHandler
    public void onPlayerTame(PlayerInteractAtEntityEvent e) {
        if (e.getRightClicked() instanceof Mob entity) {
            Player pl = e.getPlayer();

            if (pl.getItemInHand().getType().equals(Material.BONE)) {
                pl.getInventory().removeItem(new ItemStack(Material.BONE, 1));
                tameMap.put(pl, entity);
            }

            if (tameMap.containsKey(pl) && tameMap.get(pl).getUniqueId() != entity.getUniqueId()) {
                tameMap.get(pl).setTarget(entity);
                tameMap.get(pl).getWorld().spawnParticle(
                        Particle.ANGRY_VILLAGER, tameMap.get(pl).getLocation(), 5, 0.5, 0.5, 0.5, 0.03f
                );
            }

        }

    }

    @EventHandler
    public void onPlayerInteract(EntityTargetLivingEntityEvent e) {
        if (e.getTarget() instanceof Player pl && tameMap.containsKey(pl)) {

            if (e.getEntity().getType().equals(EntityType.EXPERIENCE_ORB)) return;

            if (e.getEntity().getUniqueId() == tameMap.get(pl).getUniqueId()) {
                e.setCancelled(true);
                return;
            }
            //e.setTarget(tameMap.get(pl));
            tameMap.get(pl).setTarget((LivingEntity) e.getEntity());

            tameMap.get(pl).getWorld().spawnParticle(
                    Particle.ANGRY_VILLAGER, tameMap.get(pl).getLocation(), 5, 0.5, 0.5, 0.5, 0.03f
            );
        }
    }


}
