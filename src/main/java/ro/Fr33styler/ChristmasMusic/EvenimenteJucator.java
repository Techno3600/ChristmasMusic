// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic;

import org.bukkit.event.player.PlayerQuitEvent;
import java.util.Iterator;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import ro.Fr33styler.ChristmasMusic.Muzica.SongEndEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.EventHandler;
import ro.Fr33styler.ChristmasMusic.Muzica.Song;
import ro.Fr33styler.ChristmasMusic.Muzica.RadioSongPlayer;
import ro.Fr33styler.ChristmasMusic.Muzica.NBSDecoder;
import java.io.File;
import org.bukkit.event.player.PlayerJoinEvent;
import ro.Fr33styler.ChristmasMusic.Muzica.SongPlayer;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.event.Listener;

public class EvenimenteJucator implements Listener
{
    public static HashMap<Player, SongPlayer> currentStations;
    
    static {
        EvenimenteJucator.currentStations = new HashMap<Player, SongPlayer>();
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final int r = new Random().nextInt(6);
        System.out.println(r);
        final Song s = NBSDecoder.parse(new File(Menu.instance.getDataFolder(), "Muzica/" + Menu.music.get(r) + ".nbs"));
        final SongPlayer sp = new RadioSongPlayer(s);
        sp.setAutoDestroy(true);
        sp.addPlayer(p);
        sp.setPlaying(true);
        EvenimenteJucator.currentStations.put(p, sp);
    }
    
    @EventHandler
    public void onKick(final PlayerKickEvent e) {
        final Player p = e.getPlayer();
        try {
            EvenimenteJucator.currentStations.get(p).removePlayer(p);
            EvenimenteJucator.currentStations.get(p).destroy();
            EvenimenteJucator.currentStations.remove(p);
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void onSongEnd(final SongEndEvent e) {
        final SongPlayer SP = e.getSongPlayer();
        for (final String S : SP.getPlayerList()) {
            final Player p = Bukkit.getPlayer(S);
            if (p == null) {
                return;
            }
            new BukkitRunnable() {
                public void run() {
                    final int r = (int)(Math.random() * Menu.music.size());
                    final Song s = NBSDecoder.parse(new File(Menu.instance.getDataFolder(), "Muzica/" + Menu.music.get(r) + ".nbs"));
                    final SongPlayer sp = new RadioSongPlayer(s);
                    sp.setAutoDestroy(true);
                    sp.addPlayer(p);
                    sp.setPlaying(true);
                    EvenimenteJucator.currentStations.put(p, sp);
                }
            }.runTaskLater((Plugin)Menu.instance, 40L);
        }
    }
    
    @EventHandler
    public void onLeave(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        try {
            EvenimenteJucator.currentStations.get(p).removePlayer(p);
            EvenimenteJucator.currentStations.get(p).destroy();
            EvenimenteJucator.currentStations.remove(p);
        }
        catch (Exception ex) {}
    }
}
