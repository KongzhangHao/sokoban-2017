import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * @brief Music player which controls the music in the game
 * @author hao
 * @file GameMusic.java
 * @date 10/01/2018	hao: Created GameMusic.java
 *					hao: Added method to load music from file path
 *					hao: Fixed bug: Trying to run a music while it's already running
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
	
	/**
	 * @brief Start playing the bg music
	 */
	public void playBGM() {
		if (bgm.isRunning()) return;
		
		/** Stop the game over music if it is runinng */
		if (gameOverMusic.isRunning()) gameOverMusic.stop();
		
		/** Start playing the bg music */
		if (!bgm.isRunning()) bgm.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * @brief Start playing the gameOver music
	 */
	public void playGameOverMusic() {
		if (gameOverMusic.isRunning()) return;
		
		/** Stop the bgm if it is runinng */
		if (bgm.isRunning()) bgm.stop();
		
		/** Start playing the game over music */
		gameOverMusic.start();
	}
	
	/**
	 * @brief Play the music as given in the file path
	 * @param filePath give music's file path
	 */
	public void playSound(String filePath) {
	    AudioInputStream audio;
	    
		try {
			
			/** load music with given path */
			audio = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.start();
			
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}
	

}
