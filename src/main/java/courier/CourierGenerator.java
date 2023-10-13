package courier;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public Courier generic() {
        return new Courier("Jack", "P@ssw0rd123", "Sparrow");
    }

    public Courier random() {
        return new Courier(RandomStringUtils.randomAlphanumeric(10), "P@ssw0rd123", "Sparrow");
    }

    public Courier repeats() {return new Courier( "Jack", RandomStringUtils.randomAlphanumeric(10), "Sparrow");}

    public Courier notExist() { return new Courier(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10));}
}
