package ExecutorServiceThreadPool;

public class Quote {
    private String site;

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private int price;

    public Quote(String site, int price) {
        this.site = site;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "site='" + site + '\'' +
                ", price=" + price +
                '}';
    }
}
