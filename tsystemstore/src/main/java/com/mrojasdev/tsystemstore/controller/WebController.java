package com.mrojasdev.tsystemstore.controller;

import com.mrojasdev.tsystemstore.mapper.ClientAddressMapper;
import com.mrojasdev.tsystemstore.mapper.ClientMapper;
import com.mrojasdev.tsystemstore.mapper.OrderMapper;
import com.mrojasdev.tsystemstore.mapper.ProductMapper;
import com.mrojasdev.tsystemstore.model.Client;
import com.mrojasdev.tsystemstore.model.ClientAddress;
import com.mrojasdev.tsystemstore.model.Order;
import com.mrojasdev.tsystemstore.model.OrderProduct;
import com.mrojasdev.tsystemstore.repository.ClientAddressRepository;
import com.mrojasdev.tsystemstore.repository.ClientRepository;
import com.mrojasdev.tsystemstore.repository.OrderRepository;
import com.mrojasdev.tsystemstore.repository.ProductRepository;
import com.mrojasdev.tsystemstore.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    private Client getCurrentClient() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Client client = null;
        if(principal instanceof Client){
            client = ((Client) principal);
        }
        return client;
    }


    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("products", productMapper.productsToProductsDTO(productRepository.findAll()));
        return "products";
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        Client client = getCurrentClient();
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
        model.addAttribute("address", new ClientAddress());
        return "create-address";
    }

    @GetMapping("/admin/management")
    public String getManagement(Model model) {
        Client client = getCurrentClient();
        model.addAttribute("client", clientMapper.clientToClientDTO(client));
        return "admin/management";
    }

    @GetMapping("/cart")
    public String getCart(Model model) {
        Client client = getCurrentClient();
        model.addAttribute("products", cartService.getCartSummary(client));
        return "cart";
    }

    @GetMapping("/checkout")
    public String getCheckout(Model model) {
        Client client = getCurrentClient();
        model.addAttribute("order", new Order());
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



}
