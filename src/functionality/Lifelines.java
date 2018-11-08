package functionality;

public class Lifelines {

	private boolean skipAvailable = true;
	private boolean doubleGuessAvailable = true;
	
	private boolean doubleGuessInUse = false;
	
	/* Resets everything to default */
	public void resetDefault(){
		skipAvailable = true;
		doubleGuessAvailable = true;
		
		doubleGuessInUse = false;
	}
	
	/* Sets skip available to false */
	public void skipUsed() {
		skipAvailable = false;
	}
	
	/* Sets double guess available to false */
	public void doubleGuessUsed() {
		doubleGuessAvailable = false;
	}
	
	/* Returns the status of Skip */
	public boolean getSkipAvailable() {
		return skipAvailable;
	}
	
	/* Returns the status of Double Guess */
	public boolean getDoubleGuessAvailable() {
		return doubleGuessAvailable;
	}


	/* Sets double guess currently in use */
	public void enableDoubleGuess() {
		doubleGuessInUse = true;
	}

	/* Disables Double Guess in use on next question */
	public void disableDoubleGuess() {
		doubleGuessInUse = false;
	}

	
	/* Gets double guess in use status */
	public boolean getDoubleGuessInUse() {
		return doubleGuessInUse;
	}
	
}
