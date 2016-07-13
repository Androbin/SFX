package de.androbin.util;

import com.sun.xml.internal.ws.api.*;
import java.applet.*;
import java.io.*;
import javax.sound.sampled.*;

public final class SoundUtil
{
	private SoundUtil()
	{
	}
	
	public static AudioClip loadAudioClip( final String path )
	{
		return Applet.newAudioClip( ResourceLoader.class.getResource( "/sfx/" + path ) );
	}
	
	@ SuppressWarnings( "resource" )
	public static Clip loadClip( final String path )
	{
		try
		{
			final AudioInputStream audioIn = AudioSystem.getAudioInputStream( ResourceLoader.class.getResource( "/sfx/" + path ) );
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
