package de.androbin.util;

import static de.androbin.math.util.doubles.DoubleMathUtil.*;
import javax.sound.sampled.*;

public final class SoundGenerator {
  private static byte[] buffer = new byte[ 4096 * 2 / 3 ];
  private static int bufferSize;
  
  private SoundGenerator() {
  }
  
  public static void play( final SourceDataLine line, final double tone ) {
    final short s = (short) ( Short.MAX_VALUE * bound( -1.0, tone, 1.0 ) );
    
    buffer[ bufferSize++ ] = (byte) s;
    buffer[ bufferSize++ ] = (byte) ( s >> 8 );
    
    if ( bufferSize >= buffer.length ) {
      line.write( buffer, 0, buffer.length );
      bufferSize = 0;
    }
  }
  
  public static double[] tone( final double hz, final double duration ) {
    final double[] pcm = new double[ (int) ( 44100 * duration ) + 1 ];
    
    for ( int i = 0; i < pcm.length; i++ ) {
      pcm[ i ] = Math.sin( 2 * Math.PI * i * hz / 44100 );
    }
    
    return pcm;
  }
}