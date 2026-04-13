public class Player {

    private String name;
    private int maxHp, hp;
    private int maxMp, mp;

    public Player(String name) {
        this.name  = name;
        this.maxHp = 100; this.hp  = 100;
        this.maxMp = 50;  this.mp  = 50;
    }

    // [OVERLOAD 1] Serangan fisik dasar
    public int attack() {
        int dmg = (int)(Math.random() * 12) + 8;
        System.out.println("   ⚔️  [OVERLOAD 1] attack() — Serangan fisik dasar!");
        return dmg;
    }

    // [OVERLOAD 2] Serangan magis (Tadi bagian ini yang hilang kepalanya)
    public int attack(int power) {
        if (!useMp(10)) return 0;
        int dmg = (int)(power * (0.9 + Math.random() * 0.4));
        System.out.println("   ✨ [OVERLOAD 2] attack(" + power + ") — Serangan magis bertenaga!");
        return dmg;
    }

    // [OVERLOAD 3] Serangan elemen
    public int attack(int power, String element) {
        if (!useMp(25)) return 0;
        double bonus = switch (element.toLowerCase()) {
            case "api"      -> 1.5;
            case "es"       -> 1.3;
            case "petir"    -> 1.6;
            case "tanah"    -> 1.2;
            default         -> 1.0;
        };
        int dmg = (int)(power * bonus * (0.8 + Math.random() * 0.5));
        System.out.println("   🌟 [OVERLOAD 3] attack(" + power + ", \"" + element + "\") — ULTIMATE " + element.toUpperCase() + " STRIKE!");
        return dmg;
    }

    public int heal() {
        int amount = 20;
        hp = Math.min(maxHp, hp + amount);
        System.out.println("   💊 [OVERLOAD 1] heal() — Pulih sedikit secara alami.");
        return amount;
    }

    public int heal(int amount) {
        if (!useMp(15)) return 0;
        hp = Math.min(maxHp, hp + amount);
        System.out.println("   💊 [OVERLOAD 2] heal(" + amount + ") — Minum ramuan! Pulih " + amount + " HP!");
        return amount;
    }

    private boolean useMp(int cost) {
        if (mp < cost) {
            System.out.println("   ❌ MP tidak cukup! (butuh " + cost + ", punya " + mp + ")");
            return false;
        }
        mp -= cost;
        return true;
    }

    public void regenMp(int amount) { mp = Math.min(maxMp, mp + amount); }
    public void takeDamage(int dmg) { hp = Math.max(0, hp - dmg); }

    public boolean isAlive() { return hp > 0; }
    public String getName()  { return name; }
    public int getHp()       { return hp; }
    public int getMaxHp()    { return maxHp; }
    public int getMp()       { return mp; }
    public int getMaxMp()    { return maxMp; }

    public String getHpBar() {
        int filled = (int)((double)hp / maxHp * 20);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 20; i++)
            bar.append(i < filled ? "█" : "░");
        bar.append("] ").append(hp).append("/").append(maxHp);
        return bar.toString();
    }

    public String getMpBar() {
        int filled = (int)((double)mp / maxMp * 10);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 10; i++)
            bar.append(i < filled ? "▪" : "·");
        bar.append("] ").append(mp).append("/").append(maxMp);
        return bar.toString();
    }
}