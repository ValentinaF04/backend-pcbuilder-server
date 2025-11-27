package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.annotation.PostConstruct;
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
        if(productRepository.count() == 0) {
            productRepository.save(new Product("PC Gamer Ultra", "Intel i9, RTX 4090", 2500000.0, 5, "https://cdnx.jumpseller.com/compuelite/image/64431640/thumb/610/610?1749678037"));
            productRepository.save(new Product("Teclado Mecánico", "RGB Cherry MX", 80000.0, 20, "https://images-na.ssl-images-amazon.com/images/I/61meQuPPc-L.jpg"));
            productRepository.save(new Product("Mouse Pro", "20000 DPI", 45000.0, 15, "https://www.ebest.cl/media/catalog/product/cache/47abc4af9d81a631bd44d97ba9797770/p/r/pro-gaming-mouse-_1_.jpg"));
        }
    }

    // --- ENDPOINT 1: OBTENER PRODUCTOS ---
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // --- ENDPOINT 2: LOGIN FALSO (Para cumplir rúbrica) ---
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> creds) {
        String email = creds.get("email");
        String password = creds.get("password");

        // Validación simple "hardcoded"
        if ("user@gmail.com".equals(email) && "user1234".equals(password)) {
            // Retornamos un JSON que simula ser el usuario
            return ResponseEntity.ok(Map.of(
                    "uid", 1,
                    "name", "Usuario Backend",
                    "email", email,
                    "isAdmin", false
            ));
        }
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}