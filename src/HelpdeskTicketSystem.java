/*
src/HelpdeskTicketSystem.java
C Njaro
May 2026
 */

import javax.swing.*;
import java.util.ArrayList;

class Ticket {
    int id;
    String name;
    String issue;
    String priority;
    String status;

    Ticket(int id, String name, String issue, String priority) {
        this.id = id;
        this.name = name;
        this.issue = issue;
        this.priority = priority;
        this.status = "Open";
    }

    public String toString() {
        return "Ticket ID: " + id +
                "\nName: " + name +
                "\nIssue: " + issue +
                "\nPriority: " + priority +
                "\nStatus: " + status + "\n";
    }
}

public class HelpdeskTicketSystem {
    static ArrayList<Ticket> tickets = new ArrayList<>();
    static int ticketId = 1;

    public static void main(String[] args) {
        while (true) {
            String menu = """
                    IT Helpdesk Ticket System

                    1. Create Ticket
                    2. View All Tickets
                    3. Update Ticket Status
                    4. Search Ticket
                    5. Exit
                    """;

            String choice = JOptionPane.showInputDialog(menu);

            if (choice == null || choice.equals("5")) {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                break;
            }

            switch (choice) {
                case "1" -> createTicket();
                case "2" -> viewTickets();
                case "3" -> updateStatus();
                case "4" -> searchTicket();
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Try again.");
            }
        }
    }

    static void createTicket() {
        String name = JOptionPane.showInputDialog("Enter user name:");
        String issue = JOptionPane.showInputDialog("Enter issue description:");
        String priority = JOptionPane.showInputDialog("Enter priority: Low, Medium, High");

        if (name == null || issue == null || priority == null ||
                name.isEmpty() || issue.isEmpty() || priority.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.");
            return;
        }

        Ticket ticket = new Ticket(ticketId++, name, issue, priority);
        tickets.add(ticket);

        JOptionPane.showMessageDialog(null, "Ticket created successfully!\n\n" + ticket);
    }

    static void viewTickets() {
        if (tickets.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tickets found.");
            return;
        }

        StringBuilder result = new StringBuilder("All Tickets:\n\n");
        for (Ticket ticket : tickets) {
            result.append(ticket).append("\n");
        }

        JOptionPane.showMessageDialog(null, result.toString());
    }

    static void updateStatus() {
        String input = JOptionPane.showInputDialog("Enter Ticket ID to update:");
        if (input == null || input.isEmpty()) return;

        int id = Integer.parseInt(input);

        for (Ticket ticket : tickets) {
            if (ticket.id == id) {
                String newStatus = JOptionPane.showInputDialog("Enter new status: Open, In Progress, Closed");
                if (newStatus != null && !newStatus.isEmpty()) {
                    ticket.status = newStatus;
                    JOptionPane.showMessageDialog(null, "Ticket updated successfully!\n\n" + ticket);
                }
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Ticket not found.");
    }

    static void searchTicket() {
        String input = JOptionPane.showInputDialog("Enter Ticket ID to search:");
        if (input == null || input.isEmpty()) return;

        int id = Integer.parseInt(input);

        for (Ticket ticket : tickets) {
            if (ticket.id == id) {
                JOptionPane.showMessageDialog(null, ticket.toString());
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "Ticket not found.");
    }
}