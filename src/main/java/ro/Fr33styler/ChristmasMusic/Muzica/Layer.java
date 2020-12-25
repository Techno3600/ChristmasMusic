// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

import java.util.HashMap;

public class Layer
{
    private HashMap<Integer, Note> hashMap;
    private byte volume;
    private String name;
    
    public Layer() {
        this.hashMap = new HashMap<Integer, Note>();
        this.volume = 100;
        this.name = "";
    }
    
    public HashMap<Integer, Note> getHashMap() {
        return this.hashMap;
    }
    
    public void setHashMap(final HashMap<Integer, Note> hashMap) {
        this.hashMap = hashMap;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public Note getNote(final int tick) {
        return this.hashMap.get(tick);
    }
    
    public void setNote(final int tick, final Note note) {
        this.hashMap.put(tick, note);
    }
    
    public byte getVolume() {
        return this.volume;
    }
    
    public void setVolume(final byte volume) {
        this.volume = volume;
    }
}
