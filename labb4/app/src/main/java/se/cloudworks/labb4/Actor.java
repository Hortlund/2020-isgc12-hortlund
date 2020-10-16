package se.cloudworks.labb4;

public class Actor {

    private String character;
    private String name;

    public Actor(String character, String name){
        this.character = character;
        this.name = name;
    }

    public String toString(){

        return name + " as " + character;
    }

}
