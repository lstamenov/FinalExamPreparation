package HeroesOfCodeAndLogicVII;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Hero> heroes = new ArrayList<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] arr = scanner.nextLine().split("\\s+");
            String name = arr[0];
            int hp = Integer.parseInt(arr[1]);
            int mp = Integer.parseInt(arr[2]);
            Hero hero = new Hero(name, hp, mp);
            heroes.add(hero);
        }

        String input = scanner.nextLine();
        while (!input.equals("End")){
            String[] commands = input.split(" - ");
            String command = commands[0];

            switch (command){
                case "CastSpell":
                    String name = commands[1];
                    int mp = Integer.parseInt(commands[2]);
                    String spellName = commands[3];
                    int heroIndex = getHeroIndex(name, heroes);
                    if (heroIndex != -1){
                        heroes.get(heroIndex).castSpell(mp, spellName);
                    }
                    break;
                case "TakeDamage":
                    name = commands[1];
                    int damage = Integer.parseInt(commands[2]);
                    String attacker = commands[3];

                    heroIndex = getHeroIndex(name, heroes);
                    if (heroIndex != -1){
                        heroes.get(heroIndex).takeDamage(damage, attacker);
                        if (heroes.get(heroIndex).getHp() < 1){
                            heroes.remove(heroIndex);
                        }
                    }
                    break;
                case "Recharge":
                    name = commands[1];
                    int amount = Integer.parseInt(commands[2]);

                    heroIndex = getHeroIndex(name, heroes);
                    if (heroIndex != -1){
                        heroes.get(heroIndex).rechargeMana(amount);
                    }
                    break;
                case "Heal":
                    name = commands[1];
                    amount = Integer.parseInt(commands[2]);

                    heroIndex = getHeroIndex(name, heroes);
                    if (heroIndex != -1){
                        heroes.get(heroIndex).heal(amount);
                    }
            }
            input = scanner.nextLine();
        }
        heroes.stream()
                .sorted(Comparator.comparing(Hero::getHp).reversed().thenComparing(Hero::getName))
                .forEach(hero -> {
                    System.out.println(hero.getName());
                    System.out.println("  HP: " + hero.getHp());
                    System.out.println("  MP: " + hero.getMp());
                });
    }

    private static int getHeroIndex(String name, List<Hero> heroes) {
        for (int i = 0; i < heroes.size(); i++) {
            if (heroes.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
}
