public class SlimeBoss extends Monster {
 
    public SlimeBoss() {
        super("Slime Boss", "🫧", 110, 12, "Goo Splash");
    }
 
    @Override  // <-- METHOD OVERRIDING
    public String makeSound() {
        return "💚 BLORP BLORP! Slime raksasa terbentuk dari lendir!";
    }
 
    @Override  // <-- METHOD OVERRIDING
    public int specialAttack() {
        int hits   = (int)(Math.random() * 3) + 1;
        int damage = ((int)(Math.random() * 6) + 7) * hits;
        System.out.println("   💚 GOO SPLASH x" + hits + "! Slime membelah dan menyerang " + hits + " kali!");
        return damage;
    }
}