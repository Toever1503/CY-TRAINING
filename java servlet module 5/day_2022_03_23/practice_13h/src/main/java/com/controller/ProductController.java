package com.controller;

import com.dao.ProductDao;
import com.entity.Product;
import com.util.Func;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@WebServlet(name = "ProductManagement", value = {"/products", "/products/new", "/products/update", "/products/delete", "/products/search"})
@MultipartConfig
public class ProductController extends HttpServlet {
    private ProductDao productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        productDao = new ProductDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (Func.getLastPath(req.getRequestURI())) {
            case "products":
                req.setAttribute("listProduct", productDao.findAll());
                req.getRequestDispatcher("/view/ProductManagement.jsp").forward(req, resp);
                break;
            case "new":
                req.getRequestDispatcher("/view/AddProduct.jsp").forward(req, resp);
                break;
            case "update":
                req.setAttribute("productUpdate", productDao.findById(Long.valueOf(req.getParameter("id"))));
                req.getRequestDispatcher("/view/UpdateProduct.jsp").forward(req, resp);
                break;
            case "search":
                String q = req.getParameter("q");
                System.out.println("search->" + q);

                List<Product> listSearch = productDao.searchByName(q.trim());
                req.setAttribute("listProduct", listSearch);
                req.getRequestDispatcher("/view/ProductManagement.jsp").forward(req, resp);
                break;
            default:
                break;
        }
    }

    protected void addNewProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameFile = null;

        req.setAttribute("type", "error");
        boolean check = false;

        String rootPath = this.getServletContext().getRealPath("/");
        System.out.println("upload file path servlet: " + rootPath);

        try {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            File repository = new File(rootPath + "/upload");
            if (repository.exists() == false)
                repository.mkdir();

            ServletFileUpload upload = new ServletFileUpload(factory);
            List<FileItem> items = upload.parseRequest(req);

            Iterator<FileItem> iter = items.iterator();
            HashMap<String, String> fiels = new HashMap<>();
            String newImageName = null;

            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    fiels.put(item.getFieldName(), item.getString());
                } else {
                    System.out.println("file ->" + item.getFieldName());
                    System.out.println("file->");
                    System.out.println(item.getName());

                    nameFile = item.getName();
                    StringBuilder sb = new StringBuilder();
                    sb.append(rootPath).append("/upload/").append(nameFile);
                    newImageName = nameFile;

                    if (checkFileExist(sb.toString()))
                        for (int i = 0; ; i++) {
                            sb.setLength(0);
                            newImageName = i + nameFile;
                            sb.append(rootPath).append("/upload/").append(newImageName);
                            if (!checkFileExist(sb.toString())) {
                                break;
                            }
                        }
                    item.write(new File(sb.toString()));
                    System.out.println("new file-> " + newImageName);
                    System.out.println("path after upload->" + sb.toString());
                }
            }
            System.out.println("check parameter");
            Product product = new Product();
            product.setName(fiels.get("name"));
            product.setPrice(Double.valueOf(fiels.get("price").trim()));
            product.setPrice(Double.valueOf(fiels.get("old_price").trim()));
            product.setDescription(fiels.get("description"));
            product.setImage("http://localhost:8080/practice_13h/upload/" + newImageName);
            product.setColor(fiels.get("color"));
            product.setMaterial(fiels.get("material"));
            product.setStyle(fiels.get("style"));
            product.setSeason(fiels.get("season"));
            product.setYear(Integer.valueOf(fiels.get("year").trim()));

            if (productDao.save(product) != null)
                check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (check) {
            req.setAttribute("type", "success");
            req.setAttribute("message", "Add successfully!");
        } else
            req.setAttribute("message", "Add failed!");
        req.getRequestDispatcher("/view/AddProduct.jsp").forward(req, resp);
    }

    private boolean checkFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        switch (Func.getLastPath(req.getRequestURI())) {
            case "new":
                addNewProduct(req, resp);
                break;
            case "update":
                updateProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                break;
        }
    }

    public void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post update");
        String id = req.getParameter("id");


        req.setAttribute("type", "error");
        boolean check = false;

        System.out.println("id=>>>" + id);

        Product p = new Product();
        p.setId(Long.valueOf(id));
        p.setName(req.getParameter("name"));
        p.setPrice(Double.valueOf(req.getParameter("price")));
        if (productDao.update(p) != null)
            check = true;

        if (check) {
            req.setAttribute("productUpdate", p);
            req.setAttribute("type", "success");
            req.setAttribute("message", "Update successfully!");
        } else
            req.setAttribute("message", "Update failed!");

        req.getRequestDispatcher("/view/UpdateProduct.jsp").forward(req, resp);
    }

    public void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] url = req.getRequestURI().split("/");
        String path = url[url.length - 1];

        String id = req.getParameter("id");
        req.setAttribute("type", "error");
        if (id.matches("[0-9]*")) {
            productDao.deleteById(Long.valueOf(id));
            req.setAttribute("listProduct", productDao.findAll());
            req.setAttribute("type", "success");
            req.setAttribute("message", "Delete successfully!");
        } else
            req.setAttribute("message", "Delete failed!");
        req.getRequestDispatcher("/view/ProductManagement.jsp").forward(req, resp);
    }

}
