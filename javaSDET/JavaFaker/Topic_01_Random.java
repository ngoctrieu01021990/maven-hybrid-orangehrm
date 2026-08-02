package JavaFaker;

import java.util.Calendar;
import java.util.Random;

public class Topic_01_Random {
    public static void main(String[] args) {
        Random ran = new Random();

        System.out.println("john" + ran.nextInt(99999) + "@gmail.com");
        System.out.println("john" + ran.nextInt(99999) + "@gmail.com");

        System.out.println(getRandomEmail());
        System.out.println(getRandomEmail());
        System.out.println(getRandomNumber(100, 200));
        System.out.println(getRandomNumber());
    }

    public static int getRandomNumber() {
        int ulimit = 999;
        int llimit = 100;
        Random rand = new Random();
        return llimit + rand.nextInt(ulimit - llimit);
    }

    public static int getRandomNumber(int minimum, int maximum) {
        Random rand = new Random();
        return minimum + rand.nextInt(maximum - minimum);
    }

    public static String getRandomEmail() {
        return "automation" + getRandomNumberByDateTime() + "@live.com";

    }

    public static long getRandomNumberByDateTime() {
        return Calendar.getInstance().getTimeInMillis() % 100000;
    }
}
