package Manage;
import javax.swing.DefaultListModel;

public class CustomLinkedList {
    private class Node {
        Cocktail data;
        Node next;
        Node prev;
        Node(Cocktail data) { this.data = data; }
    }
    private Node head;
    private Node tail;
    private int size;
    
    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    public void add(Cocktail cocktail) {
        Node newNode = new Node(cocktail);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }
    public void removeByString(String targetString) {
        Node current = head;
        while (current != null) {
            if (current.data.toString().equals(targetString)) {
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next;
                if (current.next != null) current.next.prev = current.prev;
                else tail = current.prev;
                size--;
                return;
            }
            current = current.next;
        }
    }
    public void update(String oldString, Cocktail newCocktail) {
        Node current = head;
        while (current != null) {
            if (current.data.toString().equals(oldString)) {
                current.data = newCocktail;
                return;
            }
            current = current.next;
        }
    }
    public DefaultListModel<String> toListModel() {
        DefaultListModel<String> model = new DefaultListModel<>();
        Node current = head;
        while (current != null) {
            model.addElement(current.data.toString());
            current = current.next;
        }
        return model;
    }
}
