package keywords;

public class Topic_10 extends Topic_09 {
    //biến phạm vi toàn cục
    String address;

    public Topic_10(String name, String address) {//biến phạm vi cục bộ
        super(name);
        this.address = address;
    }

    public static void main(String[] args) {
        String localtor = "id=username";
        localtor=localtor.substring(3);
    }
}
