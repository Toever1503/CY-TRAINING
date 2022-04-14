package com.util;

import com.config.WebConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUtil {
    public static String uploadImage(MultipartFile file) {
        if(file == null||file.isEmpty()) return null; // trả về null nếu file rỗng

        String formatImageName = file.getOriginalFilename().replace(" ", "-"); // thay thế khoảng trắng bằng dấu gạch ngang, ví dụ "abc def" thành "abc-def"

        int index = 0; // biến đếm số lần xuất hiện ký tự '.' trong tên file
        StringBuilder imageName = new StringBuilder(); // tạo một chuỗi StringBuilder để lưu tên file
        imageName.append(formatImageName); // đưa tên file vào chuỗi StringBuilder

        // 'WebConfiguration.imageFolder' là đường dẫn đến thư mục chứa ảnh trên disk
        File imageFile = checkFileExist(WebConfiguration.imageFolder + imageName.toString()); // kiểm tra xem file có tồn tại hay không bằng hàm checkFileExist
        if (imageFile == null)  // nếu tên file upload tồn tại thì ta thêm index vào đầu tên file, ví dụ nếu file upload là abc.png đã có thì tên file sau khi kiểm tra sẽ có dạng <index>abc.png, ví dụ  0abc.png hoặc 1abc.png...
            while (true) {
                imageName.setLength(0);
                imageName.append(index).append(formatImageName); // đưa index vào đầu tên file, ví dụ index = 0 và tên file là abc.png thì file sẽ có tên là 0abc.png
                imageFile = checkFileExist(WebConfiguration.imageFolder + imageName.toString()); // kiểm tra xem file có tồn tại hay không bằng hàm checkFileExist
                if (imageFile != null) // nếu file không tồn tại thì thoát khỏi lặp
                    break;
                else { // ngược lại ta reset stringbuilder để check tên image khác
                    imageName.setLength(0);
                }
            }
        try {
            file.transferTo(imageFile); // upload file lên server vào folder trên disk
        } catch (IOException e) {
            imageName.setLength(0); // nếu lỗi thì ta reset chuỗi StringBuilder về rỗng
            e.printStackTrace();
        }
        return imageName.length() == 0 ? null : imageName.toString(); // nếu chuỗi StringBuilder rỗng thì trả về null, ngược lại trả về chuỗi StringBuilder chứa tên file ảnh
    }

    // kiểm tra file có tồn tại hay không
    static File checkFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists() ? null : file; // nếu file tồn tại thì trả về null, ngược lại trả về file, file instance là file chưa có trên disk
    }

    // hàm này  đang cập nhật
//    public static boolean deleteFile(String filePath) { // hàm xóa file ảnh trên server
//        File file = new File(filePath);
//        return file.delete();
//    }

}
