package de.androbin.util;

import javax.sound.sampled.*;

public final class SoundGenerator
{
	private static byte[]	buffer		= new byte[ 4096 * 2 / 3 ];
	private static int		bufferSize	= 0;
	
	public static void play( final SourceDataLine line, double in )
	{
		/**/ if ( in < -1.0 )
		{
			in = -1.0;
		}
		else if ( in > +1.0 )
		{
			in = +1.0;
		}
		
		final short s = (short) ( Short.MAX_VALUE * in );
		
		buffer[ bufferSize++ ] = (byte) s;
		buffer[ bufferSize++ ] = (byte) ( s >> 8 );
		
		if ( bufferSize >= buffer.length )
		{
			line.write( buffer, 0, buffer.length );
			bufferSize = 0;
		}
	}
	
	public static double[] tone( final double hz, final double duration )
	{
		final double amplitude = 1.0;
		final int N = (int) ( 44100 * duration );
		
		final double[] a = new double[ N + 1 ];
		
		for ( int i = 0; i <= N; i++ )
		{
			a[ i ] = amplitude * Math.sin( 2 * Math.PI * i * hz / 44100 );
		}
		
		return a;
	}
}