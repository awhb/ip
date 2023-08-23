package main.java;

enum Command {
    BYE("bye"),
    LIST("list"),
    MARK("mark"),
    UNMARK("unmark"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete");

    private final String value;

    Command(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}