package functionality;
import java.io.*;
import java.util.*;

public class Questions {
	
	private String question;
	private String options;
	private String answer;
	List<Integer> alreadyAskedQuestions = new ArrayList<Integer>();
	
	/* Reads the files */
	public void fileRead() throws IOException {
		
		File questionsFile = new File("D:/Questions.txt");
		File optionsFile = new File("D:/Options.txt");
		File answersFile = new File("D:/Answers.txt");


		BufferedReader reader1 = new BufferedReader(new FileReader(questionsFile));
		BufferedReader reader2 = new BufferedReader(new FileReader(optionsFile));
		BufferedReader reader3 = new BufferedReader(new FileReader(answersFile));

		String line1 = reader1.readLine();
		List<String> questionsList = new ArrayList<String>();
		
		
		String line2 = reader2.readLine();
		List<String> optionsList = new ArrayList<String>();
		
		String line3 = reader3.readLine();
		List<String> answersList = new ArrayList<String>();

		/* Add the contents of text files to lists */
		
		while (line1 != null) {
			questionsList.add(line1);
		     line1 = reader1.readLine();
		}
		
		while (line2 != null) {
			optionsList.add(line2);
		     line2 = reader2.readLine();
		}

		while (line3 != null) {
			answersList.add(line3);
		     line3 = reader3.readLine();
		}
		
		Random r = new Random();
		int randomInt = r.nextInt(questionsList.size());

		
		/* Checks if the selected question has already been asked. Loop keeps running until a unique number is chosen */
		while(alreadyAskedQuestions.contains(randomInt)) {
			randomInt = r.nextInt(questionsList.size());
		}
		
		alreadyAskedQuestions.add(randomInt);
		question = questionsList.get(randomInt);
		options = optionsList.get(randomInt);
		answer = answersList.get(randomInt);
		
		reader1.close();
		reader2.close();
		reader3.close();
	}
	/* Returns Question */
	public String getQuestion() {
		return question;
	}
	
	/* Returns Options */
	public String getOptions() {
		return options;
	}
	
	/* Returns Answer */
	public String getAnswer() {
		return answer;
	}
	
	/* Resets already asked questions list */
	public void clearAskedQuestionsList() {
		alreadyAskedQuestions.clear();
	}

}
