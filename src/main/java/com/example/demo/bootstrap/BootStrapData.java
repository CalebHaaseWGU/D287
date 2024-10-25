package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;
    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository = outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (partRepository.count() == 0) {
            // Add "Processor" Inhouse Part
            Optional<Part> existingProcessor = partRepository.findById(101L);

            InhousePart processor = new InhousePart();
            processor.setName("Processor");
            processor.setMin(1);
            processor.setMax(20);
            processor.setInv(10);
            processor.setPrice(250.0);
            processor.setPartId(101);  // Use int instead of long
            partRepository.save(processor);
            System.out.println("Added Processor Inhouse Part");


            // Add "Graphics Card" Outsourced Part
            Optional<Part> existingGraphicsCard = partRepository.findById(102L);

            OutsourcedPart graphicsCard = new OutsourcedPart();
            graphicsCard.setName("Graphics Card");
            graphicsCard.setMin(1);
            graphicsCard.setMax(10);
            graphicsCard.setInv(5);
            graphicsCard.setPrice(500.0);
            graphicsCard.setCompanyName("GPU World");
            graphicsCard.setId(102L);  // Use long for OutsourcedPart ID
            partRepository.save(graphicsCard);
            System.out.println("Added Graphics Card Outsourced Part");


            // Add "RAM" Inhouse Part
            Optional<Part> existingRAM = partRepository.findById(103L);

            InhousePart ram = new InhousePart();
            ram.setName("RAM");
            ram.setMin(5);
            ram.setMax(50);
            ram.setInv(20);
            ram.setPrice(100.0);
            ram.setPartId(103);  // Use int instead of long
            partRepository.save(ram);
            System.out.println("Added RAM Inhouse Part");


            // Add "Hard Drive" Outsourced Part
            Optional<Part> existingHardDrive = partRepository.findById(104L);

            OutsourcedPart hardDrive = new OutsourcedPart();
            hardDrive.setName("Hard Drive");
            hardDrive.setMin(3);
            hardDrive.setMax(30);
            hardDrive.setInv(15);
            hardDrive.setPrice(75.0);
            hardDrive.setCompanyName("Storage Inc.");
            hardDrive.setId(104L);  // Use long for OutsourcedPart ID
            partRepository.save(hardDrive);
            System.out.println("Added Hard Drive Outsourced Part");


            // Add "Power Supply" Inhouse Part
            Optional<Part> existingPowerSupply = partRepository.findById(105L);
            InhousePart powerSupply = new InhousePart();
            powerSupply.setName("Power Supply");
            powerSupply.setMin(2);
            powerSupply.setMax(15);
            powerSupply.setInv(8);
            powerSupply.setPrice(60.0);
            powerSupply.setPartId(105);  // Use int instead of long
            partRepository.save(powerSupply);
            System.out.println("Added Power Supply Inhouse Part");


            // Print out the current state of the database
            System.out.println("Number of Parts: " + partRepository.count());
            System.out.println(partRepository.findAll());
        }
    }
}












