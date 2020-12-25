// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class SongEndEvent extends Event
{
    private static final HandlerList handlers;
    private SongPlayer song;
    
    static {
        handlers = new HandlerList();
    }
    
    public SongEndEvent(final SongPlayer song) {
        this.song = song;
    }
    
    public SongPlayer getSongPlayer() {
        return this.song;
    }
    
    public HandlerList getHandlers() {
        return SongEndEvent.handlers;
    }
    
    public static HandlerList getHandlerList() {
        return SongEndEvent.handlers;
    }
}
