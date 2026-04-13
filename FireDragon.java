public class FireDragon extends Monster {

    public FireDragon() {
        super("Fire Dragon", "🐉", 90, 18, "Fireball");
    }

    @Override  // <-- METHOD OVERRIDING
    public String makeSound() {
        return "🔥 RAWRR! Naga api bangkit dari kawah!";
    }

    @Override  // <-- METHOD OVERRIDING
    public int specialAttack() {
        int damage = (int)(Math.random() * 8) + 15;
        System.out.println("   🔥 FIREBALL! Naga menyemburkan api dahsyat!");
        return damage;
    }
}