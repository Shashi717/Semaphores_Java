
/*
Father washes fruits (an apple and an orange) for his son and daughter (picks it randomly -> whichever he gets to first). 
Son wants an orange and daughter wants an apple. There is a plate that can only contain one fruit max. 
And only one person can access the plate at a time. So father places one fruit on the plate, let's say it's an apple. 
Son tries to get it, but it's not an orange. 
since the son is accessing it, daughter can't access it, and father can't access it to put the other fruit.
 */
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;;

public class Project2 {

	ArrayList<Thread> threadList = new ArrayList<Thread>();

	public Project2() {
	}

	//method to simulate the snack time
	@SuppressWarnings("deprecation")
	public void runSnacking(int turns) throws InterruptedException {

		//for-loop to create the number of thread passed as the input
		for (int i = 0; i < turns; i++) {
			int randomNum = getRandomNum(3);
			FamilyMember member = getTurn(randomNum);

			//creating a new thread - Simulates a family member reaching the plate
			Thread newAction = new Thread(new Action(member));
			threadList.add(newAction);
			newAction.start();
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				return;
			}

			for (Thread thread : threadList) {
				if (thread.getState().equals(Thread.State.WAITING)) {
					
					thread.stop();
	
				}
			}

		}

	}

	// random family member turn generator
	private FamilyMember getTurn(int randomNum) {
		if (randomNum == 0) {
			return FamilyMember.SON;
		} else if (randomNum == 1) {
			return FamilyMember.DAUGHTER;
		} else {
			return FamilyMember.FATHER;
		}
	}

	// random integer generator
	public static int getRandomNum(int items) {
		Random rand = new Random();
		int n = rand.nextInt(items);
		return n;
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		Project2 trial = new Project2();
		
		//10 turns for snacking
		int numOfTurns = 10;
		trial.runSnacking(numOfTurns);

	}

}
