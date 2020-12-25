// 
// Decompiled by Procyon v0.5.36
// 

package ro.Fr33styler.ChristmasMusic.Muzica;

import java.io.IOException;
import java.io.DataInputStream;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;

public class NBSDecoder
{
    public static Song parse(final File decodeFile) {
        try {
            return parse(new FileInputStream(decodeFile), decodeFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    
    private static Song parse(final InputStream inputStream, final File decodeFile) {
        final HashMap<Integer, Layer> layerHashMap = new HashMap<Integer, Layer>();
        try {
            final DataInputStream dis = new DataInputStream(inputStream);
            final short length = readShort(dis);
            final short songHeight = readShort(dis);
            final String title = readString(dis);
            final String author = readString(dis);
            readString(dis);
            final String description = readString(dis);
            final float speed = readShort(dis) / 100.0f;
            dis.readBoolean();
            dis.readByte();
            dis.readByte();
            readInt(dis);
            readInt(dis);
            readInt(dis);
            readInt(dis);
            readInt(dis);
            readString(dis);
            short tick = -1;
            while (true) {
                final short jumpTicks = readShort(dis);
                if (jumpTicks == 0) {
                    break;
                }
                tick += jumpTicks;
                short layer = -1;
                while (true) {
                    final short jumpLayers = readShort(dis);
                    if (jumpLayers == 0) {
                        break;
                    }
                    layer += jumpLayers;
                    setNote(layer, tick, dis.readByte(), dis.readByte(), layerHashMap);
                }
            }
            for (int i = 0; i < songHeight; ++i) {
                final Layer l = layerHashMap.get(i);
                if (l != null) {
                    l.setName(readString(dis));
                    l.setVolume(dis.readByte());
                }
            }
            return new Song(speed, layerHashMap, songHeight, length, title, author, description, decodeFile);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
        return null;
    }
    
    private static void setNote(final int layer, final int ticks, final byte instrument, final byte key, final HashMap<Integer, Layer> layerHashMap) {
        Layer l = layerHashMap.get(layer);
        if (l == null) {
            l = new Layer();
            layerHashMap.put(layer, l);
        }
        l.setNote(ticks, new Note(instrument, key));
    }
    
    private static short readShort(final DataInputStream dis) throws IOException {
        final int byte1 = dis.readUnsignedByte();
        final int byte2 = dis.readUnsignedByte();
        return (short)(byte1 + (byte2 << 8));
    }
    
    private static int readInt(final DataInputStream dis) throws IOException {
        final int byte1 = dis.readUnsignedByte();
        final int byte2 = dis.readUnsignedByte();
        final int byte3 = dis.readUnsignedByte();
        final int byte4 = dis.readUnsignedByte();
        return byte1 + (byte2 << 8) + (byte3 << 16) + (byte4 << 24);
    }
    
    private static String readString(final DataInputStream dis) throws IOException {
        int length = readInt(dis);
        final StringBuilder sb = new StringBuilder(length);
        while (length > 0) {
            char c = (char)dis.readByte();
            if (c == '\r') {
                c = ' ';
            }
            sb.append(c);
            --length;
        }
        return sb.toString();
    }
}
