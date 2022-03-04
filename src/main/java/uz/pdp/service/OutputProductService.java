package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Output;
import uz.pdp.entity.OutputProduct;
import uz.pdp.entity.Product;
import uz.pdp.payload.OutputProductDto;
import uz.pdp.payload.Result;
import uz.pdp.repository.OutputProductRepository;
import uz.pdp.repository.OutputRepository;
import uz.pdp.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;

    public Result create(OutputProductDto outputProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Bu mahsulot idisi mavjud emas!", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new Result("Bu chiqimning idisi mavjud emas!", false);
        }
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Chiqim kiritildi", true);
    }

    public List<OutputProduct> getAll() {
        List<OutputProduct> outputProductList = outputProductRepository.findAll();
        return outputProductList;
    }

    public OutputProduct getOne(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new OutputProduct();
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        return outputProduct;
    }

    public Result edit(Integer id, OutputProductDto outputProductDto) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new Result("bu chiqim idisi mavjud emas", false);
        }
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (!optionalProduct.isPresent()) {
            return new Result("Bu mahsulot idisi mavjud emas!", false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (!optionalOutput.isPresent()) {
            return new Result("Bu chiqimning idisi mavjud emas!", false);
        }
        OutputProduct outputProduct = optionalOutputProduct.get();
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Result("Chiqim o'zgartirildi", true);
    }

    public Result delete(Integer id) {
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        if (!optionalOutputProduct.isPresent()) {
            return new Result("bu chiqim idisi mavjud emas", false);
        }
        outputProductRepository.deleteById(id);
        return new Result("Chiqim o'chrildi", true);
    }
}
