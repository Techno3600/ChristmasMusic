// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

import java.util.Iterator;
import ro.Fr33styler.ChristmasMusic.Menu;
import org.bukkit.entity.Player;

public class RadioSongPlayer extends SongPlayer
{
    public RadioSongPlayer(final Song song) {
        super(song);
    }
    
    @Override
    public void playTick(final Player p, final int tick) {
        final byte playerVolume = Menu.instance.getPlayerVolume(p);
        for (final Layer l : this.song.getLayerHashMap().values()) {
            final Note note = l.getNote(tick);
            if (note == null) {
                continue;
            }
            p.playSound(p.getEyeLocation(), Instrument.getInstrument(note.getInstrument()), l.getVolume() * this.volume * playerVolume / 1000000.0f, NotePitch.getPitch(note.getKey() - 33));
        }
    }
}
