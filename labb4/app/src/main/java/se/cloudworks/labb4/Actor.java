package se.cloudworks.labb4;

public class Actor {
    //not much to say here also, about the same as the movie class, some values that are set during the construction and overrides the toString function

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
