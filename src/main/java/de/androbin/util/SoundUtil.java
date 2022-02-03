package de.androbin.util;

import de.androbin.io.*;
import java.io.*;
import java.nio.file.*;
import javax.sound.sampled.*;

public final class SoundUtil {
  private SoundUtil() {
  }
  
  public static Clip loadClip( final String path ) {
    final Path file = DynamicClassLoader.getPath( "sfx/" + path );
    
    if ( file == null ) {
      return null;
    }
    
    try ( final AudioInputStream audioIn = AudioSystem
        .getAudioInputStream( Files.newInputStream( file ) ) ) {
      final Clip sound = AudioSystem.getClip();
      sound.open( audioIn );
      return sound;
    } catch ( final IOException | LineUnavailableException | UnsupportedAudioFileException e ) {
      return null;
    }
  }
}