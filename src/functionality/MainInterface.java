package functionality;
import javax.swing.*;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.IOException;
import java.util.*;

public class MainInterface extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	Container c;
	Questions questionObject = new Questions();
	Lifelines lifelinesObject = new Lifelines();
	HighScore highScoreObject = new HighScore();
	private String question;		// stores the question
	private String answer;		// stores the respective answer
	private String options[] = new String[4];		// stores available options	
	private int highScore;		// stores high scores
	private int currentLevel=0;		// stores current level
	private boolean gameStatus=true;		// stores game status
	private int prizeMoney;		// stores current prize money
	
	JButton skipButton;
	JButton doubleGuessButton;

	/* Creates and shows Main Interface */
	public void showInterface() {
		
		/* Loads the method to call and format question, answer, and options */
		formatQuestion();
				
		/* Creates Content Pane */
		c = this.getContentPane();
		
		/* Makes buttons and add events listener */ 
		
		JButton b1 = new JButton(options[0]);
		JButton b2 = new JButton(options[1]);
		JButton b3 = new JButton(options[2]);
		JButton b4 = new JButton(options[3]);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		b1.setBounds(170,350,200,60); // (x-axis, y-axis, width, height)
		b2.setBounds(400,350,200,60);
		b3.setBounds(170,450,200,60);
		b4.setBounds(400,450,200,60);
		
		c.add(b1);
		c.add(b2);
		c.add(b3);
		c.add(b4);
		
		/* Makes labels to show Question, High Score, and Current Level */
		
		JLabel questionLabel = new JLabel("<html><font color=#ffffff size=5>" + question + "</font></html>", SwingConstants.CENTER);
		questionLabel.setBounds(80,200,600,120);
		
		highScore = highScoreObject.getHighScore();

		JLabel highScoreLabel = new JLabel("<html><font color=#127bac size=6> Highest Win: </font><font color=#ffffff size=6>$" + highScore + "</font></html>");
		highScoreLabel.setBounds(70,0,600,120);
		
		JLabel currentLevelLabel = new JLabel("<html><font color=#127bac size=6>Current Level: </font><font color=#ffffff size=6>" + currentLevel + "</font></html>");
		currentLevelLabel.setBounds(520,0,600,120);
		
		JLabel prizeMoneyLabel = new JLabel("<html><font color=#127bac size=6>Current Prize Money: </font><font color=#ffffff size=6>$" + prizeMoney + "</font></html>");
		prizeMoneyLabel.setBounds(440,25,600,120);
		
		c.add(questionLabel);
		c.add(highScoreLabel);
		c.add(currentLevelLabel);
		c.add(prizeMoneyLabel);
		
		showLifelines();
		
		/* Formatting of JFrame and JButtons */
		
		Color myColor1 = Color.decode("#1a1a1a");
		Color myColor2 = Color.decode("#127bac");
		b1.setBackground(myColor2);
		b2.setBackground(myColor2);
		b3.setBackground(myColor2);
		b4.setBackground(myColor2);
		this.setSize(800, 600);
		this.setLayout(null);
		this.getContentPane().setBackground(myColor1);
		this.setTitle("Quiz Game");
		this.setVisible(true);
	}
	
	/* Shows lifelines and handles their functionality */
	public void showLifelines() {
		skipButton = new JButton( new AbstractAction("Skip") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed( ActionEvent e ) {
		            skipButton.setEnabled(false);
		            lifelinesObject.skipUsed();
		            resetPane();
		            showInterface();
		            
			}
		});;
		doubleGuessButton = new JButton( new AbstractAction("2x") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed( ActionEvent e ) {
				doubleGuessButton.setEnabled(false);
	            lifelinesObject.doubleGuessUsed();
				lifelinesObject.enableDoubleGuess();
			}
		});
		
		skipButton.setBounds(270,150,100,50); // (x-axis, y-axis, width, height)
		doubleGuessButton.setBounds(390,150,100,50);
		
		
		c.add(skipButton);
		c.add(doubleGuessButton);

		if (!lifelinesObject.getSkipAvailable()) {
			skipButton.setEnabled(false);
		}

		if (!lifelinesObject.getDoubleGuessAvailable()) {
			doubleGuessButton.setEnabled(false);
		}

	}
	
	/* This method is called when the game is over */
	public void gameOver() {
		String labelText = "Game Over! You're taking home $";
		if (currentLevel >= 10) {
			labelText = "Congratulations! You've won and are taking home $";
		}
		
        if(prizeMoney > highScore) {
        	highScoreObject.setHighScore(prizeMoney);
        }
        
		JLabel gameOver = new JLabel("<html><font color=#ffffff size=5>" + labelText + prizeMoney + "</font></html>", SwingConstants.CENTER);
		gameOver.setBounds(100,200,600,120);
		
		JButton b1 = new JButton( new AbstractAction("Replay") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed( ActionEvent e ) {
					currentLevel = 0;
		            prizeMoney = 0;
		            gameStatus = true;
		            questionObject.clearAskedQuestionsList();
					lifelinesObject.resetDefault();
		            skipButton.setEnabled(true);
		            doubleGuessButton.setEnabled(true);
		            resetPane();
		            showInterface();
		            
			}
		});

		JButton b2 = new JButton( new AbstractAction("Exit") {

			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed( ActionEvent e ) {
		            dispose();
			}
		});
		
		b1.setBounds(170,350,200,60); // (x-axis, y-axis, width, height)
		b2.setBounds(400,350,200,60);
		
		b1.addActionListener(this);
		b2.addActionListener(this);

		
		c.add(gameOver);
		c.add(b1);
		c.add(b2);
		
	}
	/* Catches performed actions and gives result based on that */
	 @Override
	 public void actionPerformed(ActionEvent e) {
		 String userSelection = e.getActionCommand();
		 
		 if(userSelection.equals(answer)){
			 currentLevel++;
			 prizeMoney += 10000;
			 gameStatus = true;
			 
			 if(lifelinesObject.getDoubleGuessInUse()) {
				 lifelinesObject.disableDoubleGuess();
			 }
			 
			 loadNextQuestion();
			 
		 }
		 
		 else if (!userSelection.equals(answer) && !lifelinesObject.getDoubleGuessInUse()){
			 gameStatus = false;
		 }
		 
		 if(!lifelinesObject.getDoubleGuessInUse())
			 loadNextQuestion();
		 
		 if(lifelinesObject.getDoubleGuessInUse()) {
			 lifelinesObject.disableDoubleGuess();
		 }
	 }
	 
	/* Loads next question */
	public void loadNextQuestion() {
		resetPane();
		 if(gameStatus && currentLevel < 10)
			 showInterface();
		 
		 else
			 gameOver();
	}
	
	/* Formats question, answer, and options */	
	public void formatQuestion() {
		/* Loads File Read Method in Questions class*/
		try {
			questionObject.fileRead();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		question = questionObject.getQuestion();
		answer = questionObject.getAnswer();
		
		/* Separates options string into four parts and saves each in an array location */
		StringTokenizer opt = new StringTokenizer(questionObject.getOptions(),",");

		for(int i=0; opt.hasMoreTokens(); i++) {
			options[i] = opt.nextToken();
		}

	}

	/* Resets Content Pane */
	public void resetPane() {
		 c.removeAll();
		 c.revalidate(); 
		 c.repaint();
	}


}
