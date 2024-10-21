/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication52;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 *
 * @author ekii
 */
public class FileSplit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta input file path dari pengguna
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        // Meminta input ukuran bagian dari pengguna (berapa baris per bagian)
        System.out.print("Enter the number of lines per part: ");
        int linesPerPart = scanner.nextInt();

        Queue<String> queue = new LinkedList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            int lineCount = 0;
            int partNumber = 1;

            // Membaca file baris demi baris dan menambahkannya ke dalam queue
            while ((line = reader.readLine()) != null) {
                queue.add(line);
                lineCount++;

                // Jika jumlah baris dalam queue sudah mencapai linesPerPart, tulis bagian tersebut ke file baru
                if (lineCount == linesPerPart) {
                    writePartToFile(queue, partNumber);
                    partNumber++;
                    lineCount = 0;  // Reset counter baris
                }
            }

            // Jika ada sisa baris dalam queue, tulis sisa baris tersebut sebagai bagian terakhir
            if (!queue.isEmpty()) {
                writePartToFile(queue, partNumber);
            }

            System.out.println("File split completed.");
        } catch (IOException e) {
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
    }

    // Fungsi untuk menulis bagian dari queue ke dalam file terpisah
    private static void writePartToFile(Queue<String> queue, int partNumber) {
        String partFileName = "part_" + partNumber + ".txt";
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(partFileName));
            while (!queue.isEmpty()) {
                writer.write(queue.poll());
                writer.newLine();
            }
            System.out.println("Written part " + partNumber + " to " + partFileName);
        } catch (IOException e) {
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
            }
        }
    }
}

