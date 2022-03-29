package CopyFileProgram;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class CopyFile {

    public void begin() {
        Scanner scan = new Scanner(System.in);
        String source = null;
        String dest = null;
        File sourceFile = null;
        File destFile = null;

        System.out.print("Enter file source: ");
        do {
            source = scan.nextLine();
            sourceFile = new File(source.trim());

            if (!sourceFile.isFile()) {
                System.out.println("Please enter exactly file, not path");
                source = null;
            }
        } while (source == null);

        System.out.print("Enter path dest to copy(path must exist before copy): ");
        do {
            dest = scan.nextLine();
            destFile = new File(dest);
            if (!destFile.isDirectory()) {
                System.out.print("Path destination not exist!, do you want do create?(y/n) ");
                if (scan.next().equalsIgnoreCase("n")) {
                    dest = null;
                    System.out.print("Please enter other path destination: ");
                } else
                    destFile.mkdirs();
            }
        } while (dest == null);

        String targetFile = dest.concat("/".concat(sourceFile.getName()));
        System.out.println(targetFile);
        try {
            Files.copy(Path.of(source), Path.of(targetFile));
            System.out.println("Copy successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        scan.close();
    }

    public static void main(String[] args) {
        new CopyFile().begin();
    }
}
