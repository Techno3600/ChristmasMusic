// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic;

import ro.Fr33styler.ChristmasMusic.Muzica.SongPlayer;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class Commands implements CommandExecutor
{
    public boolean onCommand(final CommandSender cs, final Command cmd, final String label, final String[] args) {
        if (!(cs instanceof Player)) {
            return false;
        }
        final Player p = (Player)cs;
        try {
            EvenimenteJucator.currentStations.get(p).removePlayer(p);
            EvenimenteJucator.currentStations.get(p).destroy();
            EvenimenteJucator.currentStations.remove(p);
        }
        catch (Exception ex) {}
        return true;
    }
}
