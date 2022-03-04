package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Input;
import uz.pdp.entity.InputProduct;
import uz.pdp.entity.Product;
import uz.pdp.payload.InputProductDto;
import uz.pdp.payload.Result;
import uz.pdp.repository.InputProductRepository;
import uz.pdp.repository.InputRepository;
import uz.pdp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputRepository inputRepository;

    public Result create(InputProductDto inputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Mahsulot ididsi topilmadi", false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()) {
            return new Result("Kirim ididsi topilmadi", false);
        }
        InputProduct inputProduct = new InputProduct();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Kirim mahsuloti saqlandi", true);
    }

    public List<InputProduct> getAll() {
        List<InputProduct> all = inputProductRepository.findAll();
        return all;

    }

    public InputProduct getOne(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new InputProduct();
        }
        InputProduct inputProduct = optionalInputProduct.get();
        return inputProduct;
    }

    public Result edit(Integer id, InputProductDto inputProductDto) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new Result("Kirim mahsulotini idisi topilmadi", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Mahsulot ididsi topilmadi", false);
        }
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        if (!optionalInput.isPresent()) {
            return new Result("Kirim ididsi topilmadi", false);
        }
        InputProduct inputProduct = optionalInputProduct.get();
        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(optionalInput.get());
        inputProductRepository.save(inputProduct);
        return new Result("Kirim mahsuloti o'zgartirildi", true);
    }

    public Result delete(Integer id) {
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent()) {
            return new Result("Kirim mahsulotini idisi topilmadi", false);
        }
        inputProductRepository.deleteById(id);
        return null;
    }
}
