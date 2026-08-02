package JavaFaker;
import com.github.javafaker.Faker;

import java.util.Locale;

public class Topic_02_Faker {
    public static void main(String[] args) {
        Faker faker = new Faker(new Locale(""));
        System.out.println(faker.internet().emailAddress());
        System.out.println(faker.internet().password());
        System.out.println(faker.internet().ipV4Address());
        System.out.println(faker.internet().uuid());
        System.out.println(faker.address().streetName());
        System.out.println(faker.address().country());
        System.out.println(faker.address().streetAddress());

        System.out.println(faker.internet().password(10,15,true,true,true));
        System.out.println(faker.internet().password(10,15,true,true,true));
        System.out.println(faker.internet().password(10,15,true,true,true));

        System.out.println(faker.number().randomDigit());
    }
}
