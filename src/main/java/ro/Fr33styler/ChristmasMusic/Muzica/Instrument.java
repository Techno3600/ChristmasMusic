// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

import org.bukkit.Sound;

public class Instrument
{
    public static Sound getInstrument(final byte instrument) {
        switch (instrument) {
            case 0: {
                return Sound.BLOCK_NOTE_BLOCK_HARP;
            }
            case 1: {
                return Sound.BLOCK_NOTE_BLOCK_GUITAR;
            }
            case 2: {
                return Sound.BLOCK_NOTE_BLOCK_BASEDRUM;
            }
            case 3: {
                return Sound.BLOCK_NOTE_BLOCK_SNARE;
            }
            case 4: {
                return Sound.BLOCK_NOTE_BLOCK_HAT;
            }
            default: {
                return Sound.BLOCK_NOTE_BLOCK_PLING;
            }
        }
    }
    
    public static org.bukkit.Instrument getBukkitInstrument(final byte instrument) {
        switch (instrument) {
            case 0: {
                return org.bukkit.Instrument.PIANO;
            }
            case 1: {
                return org.bukkit.Instrument.BASS_GUITAR;
            }
            case 2: {
                return org.bukkit.Instrument.BASS_DRUM;
            }
            case 3: {
                return org.bukkit.Instrument.SNARE_DRUM;
            }
            case 4: {
                return org.bukkit.Instrument.STICKS;
            }
            default: {
                return org.bukkit.Instrument.PIANO;
            }
        }
    }
}
