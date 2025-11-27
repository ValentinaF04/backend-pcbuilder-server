package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.annotation.PostConstruct; // Import necesario
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") // Importante para evitar bloqueos
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // --- CARGA DE DATOS INICIAL (SEEDER) ---
    @PostConstruct
    public void init() {
        // Solo carga si la base de datos está vacía
        if(productRepository.count() == 0) {
            // --- PROCESADORES ---
            productRepository.save(new Product("Intel Core i9-13900K", "24 Núcleos, 5.8GHz Turbo", 650000.0, 10, "https://www.gsmpro.cl/cdn/shop/files/procesador-intel-core-i9-13900k.png?v=1747340824"));
            productRepository.save(new Product("AMD Ryzen 9 7950X", "16 Núcleos, Arquitectura Zen 4", 590000.0, 8, "https://www.gsmpro.cl/cdn/shop/files/procesador-amd-ryzen-9-7950x3d.png?v=1747340816"));

            // --- TARJETAS GRÁFICAS ---
            productRepository.save(new Product("NVIDIA RTX 4090", "24GB VRAM GDDR6X, ASUS ROG", 2100000.0, 3, "https://www.winpy.cl/files/36915-3331-ASUS-ROG-Strix-GeForce-RTX-4090-24G-1.jpg"));
            productRepository.save(new Product("AMD Radeon RX 7900 XTX", "24GB VRAM, Sapphire Nitro+", 1200000.0, 5, "https://www.winpy.cl/files/40276-1210-Tarjeta-de-Video-Gigabyte-Gaming-RX-7900-XTX-OC-de-24GB-GDDR6-1.jpg"));
            productRepository.save(new Product("NVIDIA RTX 3060", "12GB VRAM, MSI Ventus", 350000.0, 15, "https://asset.msi.com/resize/image/global/product/product_1610443907b48feb29b4a49834f4b19e35c5511db6.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png"));

            // --- RAM Y ALMACENAMIENTO ---
            productRepository.save(new Product("RAM Corsair 32GB", "DDR5 5600MHz (2x16GB)", 140000.0, 20, "https://www.winpy.cl/files/37195-9295-Corsair-Vengeance-RGB-2.jpg"));
            productRepository.save(new Product("SSD Samsung 990 PRO 2TB", "NVMe M.2 Gen4, 7450 MB/s", 180000.0, 25, "https://images-na.ssl-images-amazon.com/images/I/71OWtcxKgvL.jpg"));

            // --- PERIFÉRICOS ---
            productRepository.save(new Product("Teclado Redragon Kumara", "RGB, Switches Azules, Español", 45000.0, 30, "https://etchile.net/wp-content/uploads/2023/03/redragon_KUMARA_K552-KR_SP_09.jpg"));

            // --- OTROS ---
            productRepository.save(new Product("Monitor LG UltraGear 27''", "144Hz, IPS, 1ms, G-Sync", 280000.0, 12, "https://media.spdigital.cl/thumbnails/products/d1d99qom_a9faaaa6_thumbnail_4096.jpg"));
        }
    }

    // --- ENDPOINT 1: OBTENER PRODUCTOS ---
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // --- ENDPOINT 2: GUARDAR PRODUCTOS ---
    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // --- ENDPOINT 3: LOGIN FALSO ---
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds) {
        String email = creds.get("email");
        String password = creds.get("password");

        if ("user@gmail.com".equals(email) && "user1234".equals(password)) {
            return ResponseEntity.ok(Map.of(
                    "uid", 1,
                    "name", "Usuario Backend",
                    "email", email,
                    "isAdmin", false
            ));
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}git