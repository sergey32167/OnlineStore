package utils;
import java.util.Random;

public enum Categorys {
    ANIMAL("animal"), BOOK("book"),FOOD("food"),BEER("beer"),COLOR("color");

    private final String name;

    Categorys(String name) {
        this.name = name;
    }

    private static final Categorys[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static Categorys getRandomCategoryName()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }


    public String getName() {
        return name;
    }
}
