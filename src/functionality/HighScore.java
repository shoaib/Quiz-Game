package functionality;

import java.io.*;

public class HighScore {
	
	private int highScore;	
	File highScoreFile = new File("D:/HighScore.txt");	
	
	public HighScore() {
		if(!highScoreFile.exists()) {
			try {
				highScoreFile.createNewFile();
				setHighScore(0);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/* Fetches high score */
	public int getHighScore(){
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(highScoreFile));
			String n = reader.readLine();
			highScore = Integer.parseInt(n);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return highScore;
	}
	
	/* Saves high score */
	public void setHighScore(int highScore) {
		this.highScore = highScore;
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(highScoreFile)));
			out.println(highScore);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
