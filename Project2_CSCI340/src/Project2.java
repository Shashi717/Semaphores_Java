
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

public class Project2{
	
	ArrayList<Thread> threadList = new ArrayList<Thread>();

	public Project2() {
	}
	
	public void runSnacking(int turns) throws InterruptedException {
	
		for(int i=0; i<turns; i++) {
			int randomNum = getRandomNum(3);
			FamilyMember member = getTurn(randomNum);
			
			Thread newAction = new Thread(new Action(member));
			//threadList.add(newAction);
			
			
			newAction.start();
			try {
				Thread.sleep(200);
			}
			catch (InterruptedException e) {}

		
//				
//				if (newAction.getState().equals(Thread.State.WAITING)) {
//					System.out.println("waiting");
//					newAction.interrupt();
//				}
		
	
		
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

		// random number generator
		public static int getRandomNum(int items) {
			Random rand = new Random();
			int n = rand.nextInt(items);
			return n;
		}

	public static void main(String[] args) throws IOException, InterruptedException {

		Project2 trial = new Project2();
		trial.runSnacking(10);

	}

}
