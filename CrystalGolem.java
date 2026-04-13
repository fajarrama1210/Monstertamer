public class CrystalGolem extends Monster {
 
    public CrystalGolem() {
        super("Crystal Golem", "💎", 130, 20, "Crystal Crush");
    }
 
    @Override  // <-- METHOD OVERRIDING
    public String makeSound() {
        return "💎 BOOOOM! Golem kristal bergemuruh mengguncang bumi!";
    }
 
    @Override  // <-- METHOD OVERRIDING
    public int specialAttack() {
        boolean crit   = Math.random() > 0.5;
        int     base   = (int)(Math.random() * 10) + 20;
        int     damage = crit ? (int)(base * 1.8) : base;
        System.out.println("   💎 CRYSTAL CRUSH!" + (crit ? " 💥 CRITICAL HIT!" : ""));
        return damage;
    }
}
 