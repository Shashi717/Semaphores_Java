import java.util.Random;

public class Action implements Runnable {

	private FamilyMember familyMember;
	
	//semaphore to l
	private static Semaphore emptyPlateSemaphore = new Semaphore(1);
	private static Semaphore orangeSemaphore = new Semaphore(0);
	private static Semaphore appleSemaphore = new Semaphore(0);

	public Action(FamilyMember familyMember) {
		this.familyMember = familyMember;
	}

	//run method for the thread
	public void run() {
		
		System.out.println(familyMember.toString()+" gets to the plate\n");
		
		if (familyMember.equals(FamilyMember.FATHER)) {
			fatherGetsToPlate();
		} else if (familyMember.equals(FamilyMember.SON)) {
			sonGetsToPlate();
		} else {
			daughterGetsToPlate();
		}
		
	}

	// method for father reaching the plate to place a randomly picked fruit
	synchronized private void fatherGetsToPlate() {
		//generate a random fruit
		Fruit fruit = getFruit(getRandomNum(2));
		
		//wait until the plate gets empty
		emptyPlateSemaphore.p();
		System.out.println("   Plate is empty");
		
		if (fruit.equals(Fruit.APPLE)) {
			System.out.println("   Father puts an apple on the plate");
			
			//if the fruit is an apple, release(notify) apple semaphore
			appleSemaphore.v();
		} else {
			System.out.println("   Father puts an orange on the plate");
			
			//if the fruit is an orange, release(notify) orange semaphore
			orangeSemaphore.v();
		}
		
		//plate is now full after placing the fruit
		System.out.println("   Plate is full\n");
	}

	// method for son reaching the plate to grab an orange
	synchronized private void sonGetsToPlate() {
		
		//wait on orange semaphore
		orangeSemaphore.p();
		System.out.println("   There is an orange");
		System.out.println("   Son grabs the orange");
		
		//plate is now empty
		//release the emptyPlate semaphore
		emptyPlateSemaphore.v();
		
		System.out.println("   Plate becomes empty\n");
	}

	// method for daughter reaching the plate to grab an apple
	synchronized private void daughterGetsToPlate() {
		
		//wait on apple semaphore
		appleSemaphore.p();
		System.out.println("   There is an apple");
		System.out.println("   Daughter grabs the apple");
		
		//plate is now empty
		//release the emptyPlate semaphore
		emptyPlateSemaphore.v();
		System.out.println("   Plate becomes empty\n");

	}
	
	// random fruit generator
	private Fruit getFruit(int randomNum) {
		if (randomNum == 0) {
			return Fruit.APPLE;
		} else {
			return Fruit.ORANGE;
		}
	}

	// random number generator
	public static int getRandomNum(int items) {
		Random rand = new Random();
		int n = rand.nextInt(items);
		return n;
	}
}
