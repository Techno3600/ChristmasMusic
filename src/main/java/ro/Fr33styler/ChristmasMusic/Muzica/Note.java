// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

public class Note
{
    private byte instrument;
    private byte key;
    
    public Note(final byte instrument, final byte key) {
        this.instrument = instrument;
        this.key = key;
    }
    
    public byte getInstrument() {
        return this.instrument;
    }
    
    public void setInstrument(final byte instrument) {
        this.instrument = instrument;
    }
    
    public byte getKey() {
        return this.key;
    }
    
    public void setKey(final byte key) {
        this.key = key;
    }
}
