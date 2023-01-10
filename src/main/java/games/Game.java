package games;

public class Game {
    private int id;
    private String Title;
    private Double price;
    private String platform;

    public Game(String title, Double price, String platform) {
        Title = title;
        this.price = price;
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", price=" + price +
                ", platform='" + platform + '\'' +
                '}';
    }
}
