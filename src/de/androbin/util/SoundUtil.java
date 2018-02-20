package de.androbin.util;

import de.androbin.io.*;
import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public final class SoundUtil {
  private SoundUtil() {
  }
  
  public static Clip loadClip( final String path ) {
    final URL res = DynamicClassLoader.get().getResource( "sfx/" + path );
    
    if ( res == null ) {
      return null;
    }
    
    try ( final AudioInputStream audioIn = AudioSystem.getAudioInputStream( res ) ) {
      final Clip sound = AudioSystem.getClip();
      sound.open( audioIn );
      return sound;
    } catch ( final IOException | LineUnavailableException | UnsupportedAudioFileException e ) {
      return null;
    }
  }
}