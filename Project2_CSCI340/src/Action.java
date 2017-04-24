import java.util.Random;

public class Action implements Runnable {

	private FamilyMember familyMember;
	private static Semaphore emptyPlateSemaphore = new Semaphore(1);
	private static Semaphore orangeSemaphore = new Semaphore(0);
	private static Semaphore appleSemaphore = new Semaphore(0);

	public Action(FamilyMember familyMember) {
		this.familyMember = familyMember;
	}

	public void run() {
		
		//System.out.println("--------------");
		System.out.println(familyMember.toString()+" gets to the plate\n");
		
		if (familyMember.equals(FamilyMember.FATHER)) {
			fatherGetsToPlate();
		} else if (familyMember.equals(FamilyMember.SON)) {
			sonGetsToPlate();
		} else {
			daughterGetsToPlate();
		}
		
	}

	// method for father reaching the plate to place a fruit
	synchronized private void fatherGetsToPlate() {
		emptyPlateSemaphore.p();
		System.out.println("   Plate is empty");
		Fruit fruit = getFruit(getRandomNum(2));
		if (fruit.equals(Fruit.APPLE)) {
			System.out.println("   Father puts an apple on the plate");
			appleSemaphore.v();
		} else {
			System.out.println("   Father puts an orange on the plate\n");
			orangeSemaphore.v();
		}

	}

	// method for son reaching the plate to grab an orange
	synchronized private void sonGetsToPlate() {
		orangeSemaphore.p();
		System.out.println("   There is an orange");
		System.out.println("   Son grabs an orange");
		emptyPlateSemaphore.v();
		System.out.println("   Plate becomes empty\n");
	}

	// method for daughter reaching the plate to grab an apple
	synchronized private void daughterGetsToPlate() {
		appleSemaphore.p();
		System.out.println("   There is an apple");
		System.out.println("   Daughter grabs an apple");
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
