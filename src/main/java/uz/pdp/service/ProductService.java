package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Attachment;
import uz.pdp.entity.Category;
import uz.pdp.entity.Measurement;
import uz.pdp.entity.Product;
import uz.pdp.payload.ProductDto;
import uz.pdp.payload.Result;
import uz.pdp.repository.AttachmentRepository;
import uz.pdp.repository.CategoryRepository;
import uz.pdp.repository.MeasurementRepository;
import uz.pdp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public Result addProduct(ProductDto productDto) {
        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategoryId)
            return new Result("Bunday mahsulot ushbu kategoriyada mahjud!", false);

        //todo CHECK CATEGORY
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("Bunday kategoriya mavjud emas!", false);
        }

        //todo CHECK PHOTO
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalCategory.isPresent()) {
            return new Result("Bunday rasm mavjud emas!", false);
        }

        //todo CHECK MEASUREMENT
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalCategory.isPresent()) {
            return new Result("Bunday o'lchov birligi mavjud emas!", false);
        }

        //todo SAVE PRODUCT
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode(generateCode());       //todo code ni generatsiya qilish un method yozish kk
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());

        productRepository.save(product);
        return new Result("Mahsulot saqlandi!", true);
    }

    public List<Product> getAll() {
        List<Product> all = productRepository.findAll();
        return all;
    }

    public Product getOne(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new Product();
        }
        Product product = optionalProduct.get();
        return product;
    }

    public Result edit(Integer id, ProductDto productDto) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new Result("Mahsulot idisi topilmadi", false);
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()) {
            return new Result("Mahsulot kategoriyasi topilmadi", false);
        }
        product.setCategory(optionalCategory.get());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalCategory.isPresent()) {
            return new Result("Mahsulot kategoriyasi topilmadi", false);
        }
        product.setPhoto(optionalAttachment.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalCategory.isPresent()) {
            return new Result("Mahsulot kategoriyasi topilmadi", false);
        }
        product.setMeasurement(optionalMeasurement.get());

        productRepository.save(product);
        return new Result("Mahsulot o'zgartirildi", true);
    }

    public Result delete(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent()) {
            return new Result("Mahsulot idisi topilmadi", false);
        }
        productRepository.deleteById(id);
        return new Result("Mahsulot o'chirildi", true);
    }

    private String generateCode() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 3;
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }

}

