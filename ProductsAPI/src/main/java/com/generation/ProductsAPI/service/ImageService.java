package com.generation.ProductsAPI.service;

import com.generation.ProductsAPI.model.OrderDetail;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ImageService {

    public abstract String uploadImageToFileSystem(MultipartFile imageFile) throws IOException;
    public abstract byte[] getImage(String imageName) throws IOException;
    public abstract void deleteImage(String imageName) throws IOException;

//    public abstract void deleteImage(Integer productId) throws IOException;


}
