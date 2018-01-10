import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * @brief Music player which controls the music in the game
 * @author hao
 * @file GameMusic.java
 * @date 10/01/2018	hao: Created GameMusic.java
 *
 */
public class GameMusic {

	private Clip bgm; /**< background music of the game */
	private Clip gameOverMusic; /**< game over music */
	
	/**
	 * @brief Constructor, loads the bgm and gameOverMusic from the given file path.
	 * @param bgmPath file path of background music
	 * @param gameOverMusicPath file path of game over music
	 */
	public GameMusic(String bgmPath, String gameOverMusicPath) {
		this.bgm = loadFromPath(bgmPath);
		this.gameOverMusic = loadFromPath(gameOverMusicPath);
	}

	/**
	 * @brief Load the specific music and return it in Clip style
	 * @param musicPath file path of the music
	 * @return Clip type of the music
	 */
	private Clip loadFromPath(String musicPath) {
		AudioInputStream audio;
		Clip clip = null;
		
		try {
			
			/** load music with given path */
			audio = AudioSystem.getAudioInputStream(new File(musicPath).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audio);
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return clip;
	}
	
	

}
