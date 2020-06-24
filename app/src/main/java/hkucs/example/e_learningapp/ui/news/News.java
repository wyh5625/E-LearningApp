package hkucs.example.e_learningapp.ui.news;

public class News {
    // Member variables representing the title and information about the sport.
    private String title;
    private String date;
    private String addr;


    News(String title, String date, String addr) {
        this.date = date;
        this.title = title;
        this.addr = addr;
    }

    /**
     * Gets the title of the sport.
     *
     * @return The title of the sport.
     */
    String getTitle() {
        return title;
    }

    /**
     * Gets the info about the sport.
     *
     * @return The info about the sport.
     */
    String getDate() {
        return date;
    }

    String getAddr() { return addr; }

}
