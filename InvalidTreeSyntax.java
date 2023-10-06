/**
 * This class represents an exception that is thrown when an invalid syntax is detected in a tree.
 * It extends the Exception class and provides a constructor that takes a message as a parameter.
 *Claire Lindstrom CMSC 350 Project 3
 * */
public class InvalidTreeSyntax extends Exception {
    public InvalidTreeSyntax(String message) {
        super(message);
    }
}
