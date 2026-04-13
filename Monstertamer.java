import java.util.Scanner;

public class MonsterTamer {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printBanner();
        System.out.print("  Masukkan nama karaktermu: ");
        String playerName = sc.nextLine().trim();
        if (playerName.isEmpty()) playerName = "Hero";

        Player player = new Player(playerName);

        Monster[] monsters = {
            new FireDragon(),    // Stage 1
            new GhostCat(),      // Stage 2
            new SlimeBoss(),     // Stage 3
            new ThunderBird(),   // Stage 4
            new CrystalGolem()   // Stage 5 (BOSS)
        };

        System.out.println("\n  🗡️  Petualanganmu dimulai, " + playerName + "!\n");
        pause(800);

        for (int stage = 0; stage < monsters.length; stage++) {
            Monster enemy = monsters[stage];

            System.out.println(line('=', 60));
            System.out.println("  ⚔️  STAGE " + (stage + 1) + "/5 — " + enemy.getEmoji() + " " + enemy.getName());
            System.out.println(line('=', 60));

            // makeSound() — hasilnya beda karena METHOD OVERRIDING
            System.out.println("  " + enemy.makeSound());
            pause(600);

            boolean stageWon = runBattle(player, enemy);

            if (!stageWon) {
                gameOver(playerName);
                return;
            }

            System.out.println("\n  ✅ " + enemy.getName() + " berhasil dikalahkan!\n");

            if (stage < monsters.length - 1) {
                player.heal(30);
                player.regenMp(20);
                System.out.println("  💫 Bonus recover sebelum stage berikutnya!");
                pause(500);
            }
        }

        victory(playerName);
    }

    static boolean runBattle(Player player, Monster enemy) {
        String[] elements = {"api", "es", "petir", "tanah"};
        int turn = 0;

        while (player.isAlive() && enemy.isAlive()) {
            turn++;
            System.out.println("\n" + line('-', 60));
            System.out.println("  [TURN " + turn + "]");
            printStatus(player, enemy);
            System.out.println(line('-', 60));

            // ── PLAYER TURN ──
            System.out.println("\n  Pilih aksimu:");
            System.out.println("  [1] ⚔️  Serang        — attack()");
            System.out.println("  [2] ✨  Sihir (10MP)  — attack(power)");
            System.out.println("  [3] 🌟  Ultimate(25MP) — attack(power, element)");
            System.out.println("  [4] 💊  Pulih (15MP)  — heal(amount)");
            System.out.print("\n  Pilihanmu [1-4]: ");

            String input = sc.nextLine().trim();
            int dmg = 0;

            System.out.println();
            switch (input) {
                case "1" -> {
                    // METHOD OVERLOADING versi 1: attack()
                    dmg = player.attack();
                    enemy.takeDamage(dmg);
                    if (dmg > 0) System.out.println("  💢 " + enemy.getName() + " terkena " + dmg + " damage!");
                }
                case "2" -> {
                    // METHOD OVERLOADING versi 2: attack(int power)
                    dmg = player.attack(20);
                    enemy.takeDamage(dmg);
                    if (dmg > 0) System.out.println("  💢 " + enemy.getName() + " terkena " + dmg + " damage!");
                }
                case "3" -> {
                    // METHOD OVERLOADING versi 3: attack(int power, String element)
                    String el = elements[(int)(Math.random() * elements.length)];
                    dmg = player.attack(25, el);
                    enemy.takeDamage(dmg);
                    if (dmg > 0) System.out.println("  💢 " + enemy.getName() + " terkena " + dmg + " damage!");
                }
                case "4" -> {
                    // METHOD OVERLOADING versi 2: heal(int amount)
                    int healed = player.heal(25);
                    if (healed > 0) System.out.println("  💚 " + player.getName() + " pulih " + healed + " HP!");
                }
                default  -> System.out.println("  ❓ Input tidak valid, giliran dilewati.");
            }

            if (!enemy.isAlive()) break;

            pause(400);

            System.out.println("\n  " + enemy.getEmoji() + " Giliran " + enemy.getName() + "...");
            boolean useSpecial = Math.random() > 0.5;
            int enemyDmg;

            if (useSpecial) {
                
                enemyDmg = enemy.specialAttack();
                System.out.println("  🔴 " + player.getName() + " terkena " + enemyDmg + " damage!");
            } else {
                enemyDmg = enemy.basicAttack();
                System.out.println("  " + enemy.getEmoji() + " Serangan biasa! " + player.getName() + " terkena " + enemyDmg + " damage!");
            }

            player.takeDamage(enemyDmg);
            player.regenMp(5); // regen MP tiap giliran
        }

        return player.isAlive();
    }

    static void printBanner() {
        System.out.println("\n");
        System.out.println("  ╔══════════════════════════════════════════╗");
        System.out.println("  ║     🐉  MONSTER  TAMER  RPG  🐉          ║");
        System.out.println("  ║        ~ PBO Battle Edition ~            ║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  Konsep OOP yang dipakai:                ║");
        System.out.println("  ║  ✅ Method Overloading  (class Player)   ║");
        System.out.println("  ║  ✅ Method Overriding   (class Monster)  ║");
        System.out.println("  ╚══════════════════════════════════════════╝");
        System.out.println();
    }

    static void printStatus(Player p, Monster e) {
        System.out.println("  🧙 " + p.getName());
        System.out.println("     HP " + p.getHpBar());
        System.out.println("     MP " + p.getMpBar());
        System.out.println("  " + e.getEmoji() + "  " + e.getName());
        System.out.println("     HP " + e.getHpBar());
    }

    static void gameOver(String name) {
        System.out.println("\n  ╔══════════════════════════════════════════╗");
        System.out.println("  ║           💀  GAME  OVER  💀             ║");
        System.out.println("  ║  " + padCenter(name + " kalah dalam pertempuran...", 40) + "║");
        System.out.println("  ╚══════════════════════════════════════════╝\n");
    }

    static void victory(String name) {
        System.out.println("\n  ╔══════════════════════════════════════════╗");
        System.out.println("  ║        🏆  SELAMAT  MENANG!  🏆          ║");
        System.out.println("  ║  " + padCenter(name + " menaklukkan semua monster!", 40) + "║");
        System.out.println("  ║                                          ║");
        System.out.println("  ║  Method Overloading & Overriding         ║");
        System.out.println("  ║  berhasil dipraktikkan! Mantap! 🎉       ║");
        System.out.println("  ╚══════════════════════════════════════════╝\n");
    }

    static String line(char c, int len) {
        return "  " + String.valueOf(c).repeat(len);
    }

    static String padCenter(String s, int width) {
        int pad = Math.max(0, width - s.length());
        int left = pad / 2, right = pad - left;
        return " ".repeat(left) + s + " ".repeat(right);
    }

    static void pause(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}