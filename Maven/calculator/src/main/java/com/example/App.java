package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (PrintWriter historyWriter = new PrintWriter(new FileWriter("history.txt", true))) {
            System.out.println("간단한 계산기 프로그램입니다.");

            while (true) {
                clearConsole();

                System.out.println("\n1: 더하기");
                System.out.println("2: 빼기");
                System.out.println("3: 곱하기");
                System.out.println("4: 나누기");
                System.out.println("5: 종료");
                System.out.print("원하는 연산을 선택하세요: ");
                String choice = scanner.nextLine();

                if (choice.equals("5")) {
                    System.out.println("계산기를 종료합니다.");
                    break;
                }

                System.out.print("첫 번째 숫자를 입력하세요: ");
                double num1 = Double.parseDouble(scanner.nextLine());

                System.out.print("두 번째 숫자를 입력하세요: ");
                double num2 = Double.parseDouble(scanner.nextLine());

                double result = 0;
                String operation = "";

                switch (choice) {
                    case "1":
                        result = num1 + num2;
                        operation = "+";
                        break;
                    case "2":
                        result = num1 - num2;
                        operation = "-";
                        break;
                    case "3":
                        result = num1 * num2;
                        operation = "*";
                        break;
                    case "4":
                        if (num2 != 0) {
                            result = num1 / num2;
                            operation = "/";
                        } else {
                            System.out.println("0으로 나눌 수 없습니다.");
                            historyWriter.println("0으로 나누기 오류 발생");
                            continue;
                        }
                        break;
                    default:
                        System.out.println("잘못된 선택입니다. 다시 시도하세요.");
                        continue;
                }

                System.out.printf("결과: %.2f %s %.2f = %.2f\n", num1, operation, num2, result);

                historyWriter.printf("계산: %.2f %s %.2f = %.2f\n", num1, operation, num2, result);
                historyWriter.flush(); // 즉시 파일에 기록
                
                System.out.println("\n계속하려면 Enter를 누르세요...");
                scanner.nextLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").startsWith("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
