// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

public enum NotePitch
{
    NOTE_0("NOTE_0", 0, 0, 0.5f), 
    NOTE_1("NOTE_1", 1, 1, 0.53f), 
    NOTE_2("NOTE_2", 2, 2, 0.56f), 
    NOTE_3("NOTE_3", 3, 3, 0.6f), 
    NOTE_4("NOTE_4", 4, 4, 0.63f), 
    NOTE_5("NOTE_5", 5, 5, 0.67f), 
    NOTE_6("NOTE_6", 6, 6, 0.7f), 
    NOTE_7("NOTE_7", 7, 7, 0.76f), 
    NOTE_8("NOTE_8", 8, 8, 0.8f), 
    NOTE_9("NOTE_9", 9, 9, 0.84f), 
    NOTE_10("NOTE_10", 10, 10, 0.9f), 
    NOTE_11("NOTE_11", 11, 11, 0.94f), 
    NOTE_12("NOTE_12", 12, 12, 1.0f), 
    NOTE_13("NOTE_13", 13, 13, 1.06f), 
    NOTE_14("NOTE_14", 14, 14, 1.12f), 
    NOTE_15("NOTE_15", 15, 15, 1.18f), 
    NOTE_16("NOTE_16", 16, 16, 1.26f), 
    NOTE_17("NOTE_17", 17, 17, 1.34f), 
    NOTE_18("NOTE_18", 18, 18, 1.42f), 
    NOTE_19("NOTE_19", 19, 19, 1.5f), 
    NOTE_20("NOTE_20", 20, 20, 1.6f), 
    NOTE_21("NOTE_21", 21, 21, 1.68f), 
    NOTE_22("NOTE_22", 22, 22, 1.78f), 
    NOTE_23("NOTE_23", 23, 23, 1.88f), 
    NOTE_24("NOTE_24", 24, 24, 2.0f);
    
    public int note;
    public float pitch;
    
    private NotePitch(final String name, final int ordinal, final int note, final float pitch) {
        this.note = note;
        this.pitch = pitch;
    }
    
    public static float getPitch(final int note) {
        NotePitch[] values;
        for (int length = (values = values()).length, i = 0; i < length; ++i) {
            final NotePitch notePitch = values[i];
            if (notePitch.note == note) {
                return notePitch.pitch;
            }
        }
        return 0.0f;
    }
}
