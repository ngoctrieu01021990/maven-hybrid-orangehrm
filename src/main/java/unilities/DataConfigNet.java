package unilities;
import net.datafaker.Faker;

import java.util.Locale;

public class DataConfigNet {
    private Locale locale = new Locale("en");
    private Faker faker = new Faker(locale);

    public static DataConfigNet getData() {
        return new DataConfigNet();
    }

    public String getFirstName(){
        return faker.name().firstName();
    }

    public String getLastName(){
        return faker.name().lastName();
    }
    public String getUsername(){
        return faker.credentials().username();
    }

    public String getPassword(){
        return faker.credentials().password(10,15,true,true,true);
    }

    public String getEmployeeId(){
        return getRandomNumber();
    }

    public String getRandomNumber(){
        return String.valueOf(faker.number().randomDigit());
    }

    public String getEmailAddress(){
        return faker.internet().emailAddress();
    }
}
