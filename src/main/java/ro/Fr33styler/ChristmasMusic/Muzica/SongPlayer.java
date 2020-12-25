// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.bukkit.entity.Player;
import java.util.Iterator;
import org.bukkit.event.Event;
import org.bukkit.Bukkit;
import ro.Fr33styler.ChristmasMusic.EvenimenteJucator;

import java.util.HashMap;
import java.util.ArrayList;

public abstract class SongPlayer
{
    protected Song song;
    protected boolean playing;
    protected short tick;
    protected ArrayList<String> playerList;
    public HashMap<String, ArrayList<SongPlayer>> playingSongs;
    protected boolean autoDestroy;
    protected boolean destroyed;
    protected Thread playerThread;
    protected byte volume;
    
    public SongPlayer(final Song song) {
        this.playing = false;
        this.tick = -1;
        this.playerList = new ArrayList<String>();
        this.playingSongs = new HashMap<String, ArrayList<SongPlayer>>();
        this.autoDestroy = false;
        this.destroyed = false;
        this.volume = 100;
        this.song = song;
        this.createThread();
    }
    
    protected void createThread() {
        (this.playerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!SongPlayer.this.destroyed) {
                    final long startTime = System.currentTimeMillis();
                    synchronized (SongPlayer.this) {
                        if (SongPlayer.this.playing) {
                            final SongPlayer this$0 = SongPlayer.this;
                            ++this$0.tick;
                            if (SongPlayer.this.tick > SongPlayer.this.song.getLength()) {
                                SongPlayer.this.playing = false;
                                SongPlayer.this.tick = -1;
                                EvenimenteJucator.endSong(SongPlayer.this);
                                if (SongPlayer.this.autoDestroy) {
                                    SongPlayer.this.destroy();
                                    // monitorexit(this.this$0)
                                    return;
                                }
                            }
                            for (final String s : SongPlayer.this.playerList) {
                                final Player p = Bukkit.getPlayerExact(s);
                                if (p == null) {
                                    continue;
                                }
                                SongPlayer.this.playTick(p, SongPlayer.this.tick);
                            }
                        }
                    }
                    // monitorexit(this.this$0)
                    final long duration = System.currentTimeMillis() - startTime;
                    final float delayMillis = SongPlayer.this.song.getDelay() * 50.0f;
                    if (duration < delayMillis) {
                        try {
                            Thread.sleep((long)(delayMillis - duration));
                        }
                        catch (InterruptedException ex) {}
                    }
                }
            }
        })).setPriority(10);
        this.playerThread.start();
    }
    
    public List<String> getPlayerList() {
        return Collections.unmodifiableList((List<? extends String>)this.playerList);
    }


    public void addPlayer(final Player p) {
        synchronized (this) {
            if (!this.playerList.contains(p.getName())) {
                this.playerList.add(p.getName());
                ArrayList<SongPlayer> songs = this.playingSongs.get(p.getName());
                if (songs == null) {
                    songs = new ArrayList<SongPlayer>();
                }
                songs.add(this);
                this.playingSongs.put(p.getName(), songs);
            }
        }
    }
    
    public void setAutoDestroy(final boolean value) {
        synchronized (this) {
            this.autoDestroy = value;
        }
    }
    
    public boolean getAutoDestroy() {
        synchronized (this) {
            return this.autoDestroy;
        }
    }
    
    public abstract void playTick(final Player p0, final int p1);
    
    public void destroy() {
        synchronized (this) {
            this.destroyed = true;
            this.playing = false;
            this.setTick((short)(-1));
        }
    }
    
    public boolean isPlaying() {
        return this.playing;
    }
    
    public void setPlaying(final boolean playing) {
        this.playing = playing;
    }
    
    public short getTick() {
        return this.tick;
    }
    
    public void setTick(final short tick) {
        this.tick = tick;
    }
    
    public void removePlayer(final Player p) {
        synchronized (this) {
            this.playerList.remove(p.getName());
            if (this.playingSongs.get(p.getName()) == null) {
                // monitorexit(this)
                return;
            }
            final ArrayList<SongPlayer> songs = new ArrayList<SongPlayer>(this.playingSongs.get(p.getName()));
            songs.remove(this);
            this.playingSongs.put(p.getName(), songs);
            if (this.playerList.isEmpty() && this.autoDestroy) {
                final SongEndEvent event = new SongEndEvent(this);
                Bukkit.getPluginManager().callEvent((Event)event);
                this.destroy();
            }
        }
    }
    
    public byte getVolume() {
        return this.volume;
    }
    
    public void setVolume(final byte volume) {
        this.volume = volume;
    }
    
    public Song getSong() {
        return this.song;
    }
}
