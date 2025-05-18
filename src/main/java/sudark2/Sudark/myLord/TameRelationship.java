package sudark2.Sudark.myLord;

import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TameRelationship {

    public static void newTask(Plugin plugin) {
       new BukkitRunnable(){
           @Override
           public void run() {
               for (Player pl : PlayerTame.tameMap.keySet()) {
                   Mob mob = PlayerTame.tameMap.get(pl);

                   if(mob.getLocation().distanceSquared(pl.getLocation()) > 100) {
                       mob.getPathfinder().moveTo(pl.getLocation(),1.2f);
                   }

                   if(mob.isDead()) {
                       PlayerTame.tameMap.remove(pl);
                   }

               }

           }
       }.runTaskTimer(plugin,0,10);
    }
}
