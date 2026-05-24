package unilities;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataConfig {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);

    public static DataConfig getData() {
        return new DataConfig();
    }

    public String getFirstName(){
        return faker.address().firstName();
    }

    public String getLastName(){
        return faker.address().lastName();
    }

    public String getRandomNumber(){
        return String.valueOf(faker.number().randomDigit());
    }

    public String getEmailAddress(){
        return faker.internet().emailAddress();
    }
}
