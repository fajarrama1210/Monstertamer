public class GhostCat extends Monster {
 
    public GhostCat() {
        super("Ghost Cat", "👻", 70, 14, "Soul Drain");
    }
 
    @Override  // <-- METHOD OVERRIDING
    public String makeSound() {
        return "👻 Nyaaa~~ Kucing hantu muncul dari bayang-bayang!";
    }
 
    @Override  // <-- METHOD OVERRIDING
    public int specialAttack() {
        int damage = (int)(Math.random() * 10) + 12;
        System.out.println("   👻 SOUL DRAIN! Kucing menyedot energi hidupmu!");
        return damage;
    }
}