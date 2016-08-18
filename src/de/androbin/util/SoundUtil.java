package de.androbin.util;

import java.io.*;
import javax.sound.sampled.*;

public final class SoundUtil
{
	private SoundUtil()
	{
	}
	
	public static Clip loadClip( final String path )
	{
		try ( final AudioInputStream audioIn = AudioSystem.getAudioInputStream( ClassLoader.getSystemResource( "sfx/" + path ) ) )
		{
			final Clip sound = AudioSystem.getClip();
			sound.open( audioIn );
			return sound;
		}
		catch ( final IOException | UnsupportedAudioFileException | LineUnavailableException e )
		{
			return null;
		}
	}
}
