public abstract class Monster {

    protected String name;
    protected String emoji;
    protected int maxHp;
    protected int hp;
    protected int atk;
    protected String specialName;

    public Monster(String name, String emoji, int hp, int atk, String specialName) {
        this.name      = name;
        this.emoji     = emoji;
        this.maxHp     = hp;
        this.hp        = hp;
        this.atk       = atk;
        this.specialName = specialName;
    }

    public String makeSound() {
        return name + " menggertak keras!";
    }

    public int specialAttack() {
        return atk + 5;
    }

    public int basicAttack() {
        return (int)(atk * (0.7 + Math.random() * 0.6));
    }

    public boolean isAlive()  { return hp > 0; }
    public String getName()   { return name; }
    public String getEmoji()  { return emoji; }
    public int getHp()        { return Math.max(0, hp); }
    public int getMaxHp()     { return maxHp; }
    public String getSpecialName() { return specialName; }

    public void takeDamage(int dmg) {
        hp -= dmg;
        if (hp < 0) hp = 0;
    }

    public String getHpBar() {
        int filled = (int)((double)hp / maxHp * 20);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < 20; i++)
            bar.append(i < filled ? "█" : "░");
        bar.append("] ").append(hp).append("/").append(maxHp);
        return bar.toString();
    }
}