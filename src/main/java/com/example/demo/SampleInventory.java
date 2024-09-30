package com.example.demo;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Product;
import com.example.demo.service.PartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleInventory implements CommandLineRunner {

    private final PartService partService;
    private final ProductService productService;

    @Autowired
    public SampleInventory(PartService partService, ProductService productService) {
        this.partService = partService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if both part and product lists are empty
        if (partService.findAll().isEmpty() && productService.findAll().isEmpty()) {
            addSampleInventory();
        }
    }

    private void addSampleInventory() {
        // Add sample parts
        InhousePart part1 = new InhousePart();
        part1.setName("Processor");
        part1.setPrice(250.00);
        part1.setMin(1);  // Set minimum inventory
        part1.setMax(20); // Set maximum inventory
        part1.setInv(10); // Ensure inventory is within the min and max range
        part1.setPartId(101);
        System.out.println("Part1: inv = " + part1.getInv() + ", min = " + part1.getMin() + ", max = " + part1.getMax());
        partService.save(part1);

        OutsourcedPart part2 = new OutsourcedPart();
        part2.setName("Graphics Card");
        part2.setPrice(500.00);
        part2.setMin(1);
        part2.setMax(10);
        part2.setInv(5);
        part2.setCompanyName("GPU World");
        System.out.println("Part2: inv = " + part2.getInv() + ", min = " + part2.getMin() + ", max = " + part2.getMax());
        partService.save(part2);

        InhousePart part3 = new InhousePart();
        part3.setName("RAM");
        part3.setPrice(100.00);
        part3.setMin(5);
        part3.setMax(50);
        part3.setInv(20);
        part3.setPartId(102);
        System.out.println("Part3: inv = " + part3.getInv() + ", min = " + part3.getMin() + ", max = " + part3.getMax());
        partService.save(part3);

        OutsourcedPart part4 = new OutsourcedPart();
        part4.setName("Hard Drive");
        part4.setPrice(75.00);
        part4.setMin(3);
        part4.setMax(30);
        part4.setInv(15);
        part4.setCompanyName("Storage Inc.");
        System.out.println("Part4: inv = " + part4.getInv() + ", min = " + part4.getMin() + ", max = " + part4.getMax());
        partService.save(part4);

        InhousePart part5 = new InhousePart();
        part5.setName("Power Supply");
        part5.setPrice(60.00);
        part5.setMin(2);
        part5.setMax(15);
        part5.setInv(8);
        part5.setPartId(103);
        System.out.println("Part5: inv = " + part5.getInv() + ", min = " + part5.getMin() + ", max = " + part5.getMax());
        partService.save(part5);

        // Add sample products
        Product product1 = new Product();
        product1.setName("Gaming PC");
        product1.setPrice(1500.00);
        product1.setMin(1);
        product1.setMax(10);
        product1.setInv(5);
        System.out.println("Product1: inv = " + product1.getInv() + ", min = " + product1.getMin() + ", max = " + product1.getMax());
        productService.save(product1);

        Product product2 = new Product();
        product2.setName("Workstation PC");
        product2.setPrice(1200.00);
        product2.setMin(1);
        product2.setMax(5);
        product2.setInv(3);
        System.out.println("Product2: inv = " + product2.getInv() + ", min = " + product2.getMin() + ", max = " + product2.getMax());
        productService.save(product2);

        Product product3 = new Product();
        product3.setName("Laptop");
        product3.setPrice(1000.00);
        product3.setMin(2);
        product3.setMax(15);
        product3.setInv(10);
        System.out.println("Product3: inv = " + product3.getInv() + ", min = " + product3.getMin() + ", max = " + product3.getMax());
        productService.save(product3);

        Product product4 = new Product();
        product4.setName("Server");
        product4.setPrice(2500.00);
        product4.setMin(1);
        product4.setMax(3);
        product4.setInv(2);
        System.out.println("Product4: inv = " + product4.getInv() + ", min = " + product4.getMin() + ", max = " + product4.getMax());
        productService.save(product4);

        Product product5 = new Product();
        product5.setName("NAS Storage");
        product5.setPrice(800.00);
        product5.setMin(2);
        product5.setMax(10);
        product5.setInv(7);
        System.out.println("Product5: inv = " + product5.getInv() + ", min = " + product5.getMin() + ", max = " + product5.getMax());
        productService.save(product5);

        System.out.println("Sample inventory added to the database.");
    }

}






