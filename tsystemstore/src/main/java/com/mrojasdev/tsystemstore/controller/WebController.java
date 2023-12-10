package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.exception.ProductNotFoundException;
import com.mrojasdev.tsystemstore.mapper.ClientAddressMapper;
import com.mrojasdev.tsystemstore.mapper.ClientMapper;
import com.mrojasdev.tsystemstore.mapper.OrderMapper;
import com.mrojasdev.tsystemstore.mapper.ProductMapper;
import com.mrojasdev.tsystemstore.model.*;
import com.mrojasdev.tsystemstore.repository.ClientAddressRepository;
import com.mrojasdev.tsystemstore.repository.ClientRepository;
import com.mrojasdev.tsystemstore.repository.OrderRepository;
import com.mrojasdev.tsystemstore.repository.ProductRepository;
import com.mrojasdev.tsystemstore.service.CartService;
import com.mrojasdev.tsystemstore.service.ClientService;
import com.mrojasdev.tsystemstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping()
public class WebController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientAddressRepository clientAddressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientAddressMapper clientAddressMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ClientService clientService;

    private Client getCurrentClient() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = null;
        if(principal instanceof Client){
            client = ((Client) principal);
        }
        return client;
    }


    @GetMapping("/")
    public String getHome(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        return "index";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("products", productMapper.productsToProductsDTO(productRepository.findAll()));
        return "products";
    }

    @GetMapping("/products/{category}")
    public String getProductsByCategory(@PathVariable("category") String category, Model model) {
        List<ProductDTO> products = productService.getProductsOfCategory(category);
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("products", products);
        return "category-products";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        Client client = getCurrentClient();
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("client", clientMapper.clientToClientDTO(client));
        return "profile";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("client", new Client());
        return "register";
    }

    @GetMapping("/addresses")
    public String getAddresses(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("addresses",
                clientAddressMapper.clientAdressesToClientAddressesDTO(
                        clientAddressRepository.findByClient(
                                getCurrentClient()
                        )
                )
        );
        return "addresses";
    }

    @GetMapping("/create-address")
    public String createAddress(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("address", new ClientAddress());
        return "create-address";
    }

    @GetMapping("/admin/management")
    public String getManagement(Model model) {
        Client client = getCurrentClient();
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("client", clientMapper.clientToClientDTO(client));
        return "admin/management";
    }

    @GetMapping("/admin/orders")
    public String getAdminOrders(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("orders", orderRepository.findAll());
        return "admin/orders";
    }

    @GetMapping("/admin/list-products")
    public String getAdminProductsList(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("products", productRepository.findAll());
        return "admin/list-products";
    }

    @GetMapping(path = "/admin/edit-product/{id}")
    public String getAdminProductEdit(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product with id "+id+" not found")
        );
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("product", product);
        return "admin/edit-product";
    }

    @GetMapping("/admin/create-product")
    public String getAdminProductCreate(Model model) {
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("product", new Product());
        return "admin/create-product";
    }

    @GetMapping("/admin/statistics")
    public String getAdminStatistics(Model model) {
        List<TopClientDTO> topTenClients = clientService.getTopTenClientsMostOrders();
        List<TopProductDTO> topTenProducts = productService.getTopTenProductsMostSold();
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("customers", topTenClients);
        model.addAttribute("products", topTenProducts);
        return "admin/statistics";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        Client client = getCurrentClient();
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("products", cartService.getCartSummary(client));
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model) {
        Client client = getCurrentClient();
        Order order = new Order();
        order.setDeliveryMethod("UPS");
        order.setPaymentMethod("Card");
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("order", order);
        model.addAttribute("products", cartService.getCartSummary(client));
        model.addAttribute("addresses",
                clientAddressMapper.clientAdressesToClientAddressesDTO(
                        clientAddressRepository.findByClient(
                                getCurrentClient()
                        )
                )
        );
        return "checkout";
    }

    @GetMapping("/order-history")
    public String getOrderHistory(Model model) {
        Client client = getCurrentClient();
        model.addAttribute("categories", productService.getAllCategories());
        model.addAttribute("orders", orderRepository.findByClient(client));
        return "order-history";
    }



}
