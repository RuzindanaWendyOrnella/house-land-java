import org.sql2o.Connection;

import java.util.List;

public class Buy {
    private String name;
    private String email;
    private String link;
    private String location;
    private String purpose;
    private String meansOfPayment;
    private String price;
    private int id;

    public Buy(String name, String email, String link, String location, String purpose, String meansOfPayment, String price) {
        this.name=name;
        this.email =email;
        this.link=link;
        this.location=location;
        this.purpose=purpose;
        this.meansOfPayment= meansOfPayment;
        this.price=price;


    }

    public String getName() {

        return name;
    }
    public String getEmail() {

        return email;
    }

    public String getLink() {
        return link;
    }

    public String getLocation() {
        return location;
    }

    public String getPurpose() {
        return purpose;
    }
    public String getMeansOfPayment() {

        return meansOfPayment;
    }

    public String getPrice() {
        return price;
    }
    public  int getId() {
        return id;
    }
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO buy (name,email,link,location,purpose,meansOfPayment,price) VALUES (:name,:email,:link,:location,:purpose,:meansOfPayment,:price);";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("link", this.link)
                    .addParameter("location", this.location)
                    .addParameter("purpose", this.purpose)
                    .addParameter("meansOfPayment", this.meansOfPayment)
                    .addParameter("price", this.price)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Buy> all() {

        String sql = "select * from buy;";
        try(Connection con = DB.sql2o.open()) {

            return con.createQuery(sql).executeAndFetch(Buy.class);
        }
    }
}
