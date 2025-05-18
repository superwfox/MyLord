package sudark2.Sudark.myLord;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MyLord extends JavaPlugin {

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new PlayerTame(),this);

        TameRelationship.newTask(this);

    }
}
