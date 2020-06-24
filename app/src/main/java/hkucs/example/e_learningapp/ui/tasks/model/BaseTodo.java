package hkucs.example.e_learningapp.ui.tasks.model;


public abstract class BaseTodo {

    protected int id;

    protected String name, description;

    public BaseTodo() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
