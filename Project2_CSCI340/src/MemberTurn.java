
public class MemberTurn {

	FamilyMember member;
	Fruit fruit;

	MemberTurn(FamilyMember member) {
		this.member = member;
	}

	MemberTurn(FamilyMember member, Fruit fruit) {
		this.member = member;
		this.fruit = fruit;
	}

	public void persormTask() {
		if (member.equals(FamilyMember.FATHER) && fruit.equals(Fruit.APPLE)) {
			System.out.println("Father puts an apple on the plate");
		} 
		else if (member.equals(FamilyMember.FATHER) && fruit.equals(Fruit.ORANGE)) {
			System.out.println("Father puts an orange on the plate");
		} 
		else if (member.equals(FamilyMember.SON)) {
			System.out.println("Son grabs an orange");
		} 
		else if (member.equals(FamilyMember.DAUGHTER)) {
			System.out.println("Daughter grabs an apple");
		} 
		else {
			System.out.println("Wrong Member");
		}
	}

}
