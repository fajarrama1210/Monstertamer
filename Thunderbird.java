public class ThunderBird extends Monster {
 
    public ThunderBird() {
        super("Thunder Bird", "⚡", 80, 16, "Lightning Strike");
    }
 
    @Override  // <-- METHOD OVERRIDING
    public String makeSound() {
        return "⚡ KRAAAK! Burung petir menukik dari awan gelap!";
    }
 
    @Override  // <-- METHOD OVERRIDING
    public int specialAttack() {
        int damage = (int)(Math.random() * 12) + 18;
        System.out.println("   ⚡ LIGHTNING STRIKE! Petir menyambar dengan dahsyat!");
        return damage;
    }
}
 