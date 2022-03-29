package DownloadProgram;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Download {
    public void showMenu() {
        boolean run = true;
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(System.in);

        while (run) {
            System.out.print("Enter url link: ");
            do {
                sb.append(scan.next());
                if (!sb.toString().contains("http")) {
                    System.out.print("Invalid image url!, please enter again");
                    sb.setLength(0);
                }
            } while (sb.length() == 0);

            String uploadedImage = downloadFunc(sb.toString());

            if (uploadedImage == null) {
                System.out.println("Cannot download this image");
            } else {
                System.out.println("Downloaded Successfully!\n Here is path: " + new File(uploadedImage).getAbsolutePath());
            }
            System.out.print("Are you still want to continue (y/n): ");
            sb.setLength(0);
            do {
                sb.append(scan.next());
            } while (sb.length() == 0);
            if (!sb.toString().equalsIgnoreCase("y")) {
                System.out.println("Exited successfully! code: 0");
                run = false;
            }
        }
        scan.close();
    }

    public String downloadFunc(String imgUrl) {
        StringBuilder sb = new StringBuilder();
        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);

        String imageFolderPath = "Images";
        File imageFolder = new File(imageFolderPath);
        if (!imageFolder.exists())
            imageFolder.mkdir();

        URL url = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            url = new URI(imgUrl).toURL();
            URLConnection urlConnection = url.openConnection();
            if (urlConnection.getContentType().contains("text/plain")) // check content type
                return null;

            // check image where exist in folder
            sb.append(imageFolderPath).append("/").append(fileName);
            File fileImg = new File(sb.toString());

            // change image file name
            if (fileImg.exists())
                for (int i = 0; ; ++i) {
                    sb.setLength(0);
                    sb.append(imageFolderPath).append("/").append(fileName);
                    fileImg = new File(sb.toString());
                    if (!fileImg.exists()) {
                        break;
                    }
                }
            in = urlConnection.getInputStream();
            out = new FileOutputStream(fileImg);
            out.write(in.readAllBytes());
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return sb.toString(); // sb current keep value such as Image/<image file name>
    }

    public static void main(String[] args) {
        new Download().showMenu();
    }
}
