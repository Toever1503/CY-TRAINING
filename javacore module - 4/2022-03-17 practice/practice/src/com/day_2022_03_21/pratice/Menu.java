package com.day_2022_03_21.pratice;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    private final List<Product> productList;
    private final Scanner scan;
    private String tmp = null;

    public Menu() {
        this.productList = new ArrayList<Product>();
        this.scan = new Scanner(System.in);
    }


    public Product EnterProductInfor(Product p) {
        System.out.print("Enter product id: ");
        do {
            tmp = scan.next();
            if (!tmp.matches("[0-9]*")) {
                System.out.print("Id invalid, please enter again!");
                tmp = null;
            }
        } while (tmp == null);
        p.setId(Integer.valueOf(tmp));

        System.out.print("Enter product name: ");
        p.setName(scan.next());

        System.out.print("Enter product price: ");
        do {
            tmp = scan.next();
            if (tmp.matches("([0-9]*)|([0-9]*.[0.9]*)")) {
                System.out.print("Price invalid, please enter again!");
                tmp = null;
            }
        } while (tmp == null);
        p.setPrice(Double.valueOf(tmp));

        System.out.print("Enter product picture url: ");
        do {
            tmp = scan.next();
            if (!tmp.contains("http")) {
                System.out.print("Url invalid, please enter again!");
                tmp = null;
            }
        } while (tmp == null);
        p.setPictureUrl(tmp);
        return p;
    }

    public void menu() {
        BeforeInitMenu();
        boolean run = true;
        StringBuilder sb = new StringBuilder();

        while (run) {
            System.out.println("------------Product Management-----------");
            System.out.println("1. Add new product");
            System.out.println("2. Display all product");
            System.out.println("3. Update product");
            System.out.println("4. Delete product");
            System.out.println("0. Exit");
            System.out.println("You choose: ");
            do {
                sb.append(scan.next());
                if (!sb.toString().matches("[0-4]")) {
                    System.out.println("Your choice invalid, please try again!");
                    sb.setLength(0);
                }
            } while (sb.length() == 0);
            switch (sb.toString()) {
                case "0":
                    WriteDataBeforeExit();
                    System.out.println("Exit successfully!");
                    this.scan.close();
                    run = false;
                    break;
                case "1":
                    addProduct();
                    break;
                case "2":
                    displayAllProducts();
                    break;
                case "3":
                    updateProduct();
                    break;
                case "4":
                    deleteProduct();
                    break;
                default:
                    System.out.println("Opt not available!");
                    break;
            }
        }
    }

    public void BeforeInitMenu() {
        ObjectInputStream in = null;
        File file = new File("data/productList.txt");
        if (file.exists())
            try {
                in = new ObjectInputStream(new FileInputStream(file));
                while (in.available() != 0) {
                    Product p = (Product) in.readObject();
                    this.productList.add(p);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    public void WriteDataBeforeExit() {
        ObjectOutputStream outs = null;
        DataOutputStream log = null;
        new File("data").mkdir();
        try {
            outs = new ObjectOutputStream(new FileOutputStream("data/productList.txt"));
            log = new DataOutputStream(new FileOutputStream("data/productListLog.txt"));
            ObjectOutputStream finalOuts = outs;
            DataOutputStream finalLog = log;
            this.productList.forEach(p -> {
                try {
                    finalOuts.writeObject(p);
                } catch (IOException e) {
                    try {
                        finalLog.write(("Save data product got error with id " + p.getId()).getBytes());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outs.close();
                log.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addProduct() {
        Product p = new Product();
        p = EnterProductInfor(p);
        String imgUrl = getImage(p.getPictureUrl());
        if (imgUrl.length() != 0) {
            System.out.println("Add successfully!");
            p.setPictureName(imgUrl.substring(imgUrl.lastIndexOf("/") + 1));
            this.productList.add(p);
        } else
            System.out.println("Add failed!");
    }

    public void displayAllProducts() {
        this.productList.forEach(System.out::println);
    }

    public void updateProduct() {
        System.out.print("Enter product id that need delete: ");
        do {
            tmp = this.scan.next();
            if (tmp.matches("[0-9]+")) {
                System.out.print("Id invalid, please enter again! ");
                tmp = null;
            }
        } while (tmp == null);
        Integer id = Integer.valueOf(tmp);
        Product p = searchProductById(id);
        if (p == null)
            System.out.println("Product not exist!");
        else {
            for (int i = 0; i < this.productList.size(); ++i)
                if (this.productList.get(i).getId() == id) {
                    this.productList.set(i, p);
                    break;
                }
        }
    }

    public void deleteProduct() {
        System.out.print("Enter product id that need delete: ");
        do {
            tmp = this.scan.next();
            if (!tmp.matches("[0-9]+")) {
                System.out.print("Id invalid, please enter again! ");
                tmp = null;
            }
        } while (tmp == null);
        Product p = searchProductById(Integer.valueOf(tmp));
        if (p == null)
            System.out.println("Product not exist!");
        else {
            String imageFolderPath = "imagePicture/";
            File fileImg = new File(imageFolderPath + p.getPictureUrl());
            fileImg.delete();
            this.productList.remove(p);
            System.out.println("Delete product successfully!");
        }
    }

    public Product searchProductById(Integer id) {
        return this.productList.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList()).stream().findFirst().orElse(null);
    }

    public String getImage(String imgUrl) {
        System.out.println("url->" + imgUrl);

        URL url = null;
        StringBuilder sb = new StringBuilder();
        InputStream in = null;
        OutputStream out = null;
        String imageFolderPath = "imagePicture";
        File filePath = new File("imagePicture");
        if (!filePath.exists())
            filePath.mkdir();
        try {
            url = new URI(imgUrl).toURL();
            URLConnection urlConnection = url.openConnection();
            if (urlConnection.getContentType().contains("text/plain"))
                return sb.toString();

            String fileName = url.getFile();
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            if (!fileName.contains(".")) {
                fileName = "product.jpg";
            }

            sb.append(imageFolderPath).append("/").append(fileName);
            File fileImg = new File(sb.toString());

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
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        new Menu().menu();
    }
}
