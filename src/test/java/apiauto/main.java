package apiauto;

import org.testng.TestNG;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        runningXml();


        String[] commands = {
                "gradlew.bat clean test",
                "allure serve build/allure-results"
        };

        for (String command : commands) {
            runCommand(command);
        }

        System.exit(0);
    }

    public static void runningXml() {
        TestNG testng = new TestNG();
        List<String> testSuite = new ArrayList<>();
        testSuite.add("D:\\JayJay\\Homework_module13\\src\\test\\resources\\running.xml");
        testng.setTestSuites(testSuite);

        testng.run();
    }

private static void runCommand(String command) {
    ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);

    try {
        // Jalankan perintah, tanpa menangani output atau error
        processBuilder.start();
    } catch (IOException e) {
        // Tangani error jika proses tidak bisa dijalankan
        e.printStackTrace();
    }
}
}
