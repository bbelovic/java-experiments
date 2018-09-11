package net.bbelovic.javaexperiments.websocket.client;

import java.util.Scanner;

public class CliApp {
    public static void main(String[] args) {
        System.out.println("hello");
        System.out.println(System.console());

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            System.out.println("received: " + next);
        }
    }
}
