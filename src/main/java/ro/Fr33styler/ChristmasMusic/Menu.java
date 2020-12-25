// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import org.bukkit.plugin.PluginManager;
import java.util.ArrayList;
import java.io.File;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import java.util.HashMap;
import org.bukkit.plugin.java.JavaPlugin;

public class Menu extends JavaPlugin
{
    public static Menu instance;
    public static HashMap<String, Byte> playerVolume;
    public static HashMap<Integer, String> music;
    
    static {
        Menu.playerVolume = new HashMap<String, Byte>();
        Menu.music = new HashMap<Integer, String>();
    }
    
    public void onEnable() {
        final PluginManager pm = this.getServer().getPluginManager();
        pm.registerEvents((Listener)new EvenimenteJucator(), (Plugin)this);
        this.getCommand("stopmusic").setExecutor((CommandExecutor)new Commands());
        Menu.instance = this;
        final File f = new File(String.valueOf(this.getDataFolder().getPath()) + "/Muzica");
        if (!f.exists()) {
            this.saveResource("Muzica/0.nbs", true);
            this.saveResource("Muzica/1.nbs", true);
            this.saveResource("Muzica/2.nbs", true);
            this.saveResource("Muzica/3.nbs", true);
            this.saveResource("Muzica/4.nbs", true);
            this.saveResource("Muzica/5.nbs", true);
        }
        final ArrayList<String> l = new ArrayList<String>();
        l.add("0");
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        l.add("5");
        this.getConfig().addDefault("Music", (Object)l);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        for (final String s : this.getConfig().getStringList("Music")) {
            if (Menu.music.size() == 0) {
                Menu.music.put(Menu.music.size(), s);
            }
            else {
                Menu.music.put(Menu.music.size() + 1, s);
            }
        }
    }
    
    public void onDisable() {
        HandlerList.unregisterAll((Plugin)this);
    }
    
    public byte getPlayerVolume(final Player p) {
        Byte b = Menu.playerVolume.get(p.getName());
        if (b == null) {
            b = 100;
            Menu.playerVolume.put(p.getName(), b);
        }
        return b;
    }
}
