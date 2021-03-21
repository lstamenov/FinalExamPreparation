package HeroesOfCodeAndLogicVII;

public class Hero {
    private final String name;
    private int hp;
    private int mp;

    Hero(String name, int hp, int mp){
        this.name = name;
        this.hp = hp;
        this.mp = mp;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMp() {
        return mp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMp(int mp) {
        this.mp = mp;
    }

    public void castSpell(int mpQuantity, String spell){
        if (getMp() >= mpQuantity){
            mp -= mpQuantity;
            System.out.printf("%s has successfully cast %s and now has %d MP!%n", getName(), spell, getMp());
        }else {
            System.out.printf("%s does not have enough MP to cast %s!%n",getName(), spell);
        }
    }

    public void takeDamage(int dmg, String attacker){
        hp -= dmg;
        if (hp > 0){
            System.out.printf("%s was hit for %d HP by %s and now has %d HP left!%n",name, dmg, attacker, hp);
        }else {
            System.out.printf("%s has been killed by %s!%n", name, attacker);
        }
    }

    public void rechargeMana(int amountMana){
        if (mp + amountMana > 200){
            int healAmount = amountMana - (mp + amountMana - 200);
            mp = 200;
            System.out.printf("%s recharged for %d MP!%n", getName(), healAmount);
        }else {
            mp += amountMana;
            System.out.printf("%s recharged for %d MP!%n", getName(), amountMana);
        }
    }

    public void heal(int amount){
        if (hp + amount > 100){
            int healAmount = amount - (hp + amount - 100);
            hp = 100;
            System.out.printf("%s healed for %d HP!%n",getName(), healAmount);
        }else {
            hp += amount;
            System.out.printf("%s healed for %d HP!%n",getName(), amount);
        }
    }
}
