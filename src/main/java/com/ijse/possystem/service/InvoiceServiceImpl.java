package com.ijse.possystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ijse.possystem.dto.InvoiceDto;
import com.ijse.possystem.entity.Cart;
import com.ijse.possystem.entity.CartItem;
import com.ijse.possystem.entity.Invoice;
import com.ijse.possystem.entity.InvoiceItem;
import com.ijse.possystem.entity.Item;
import com.ijse.possystem.repository.CartItemRepository;
import com.ijse.possystem.repository.CartRepository;
import com.ijse.possystem.repository.InvoiceItemRepository;
import com.ijse.possystem.repository.InvoiceRepository;
import com.ijse.possystem.repository.ItemRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService{
        
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public List<Invoice> getAllInvoices(){
        return invoiceRepository.findAll();
    };
    
    @Override
    public Invoice getInvoiceById(Long id){
        return invoiceRepository.findById(id).orElse(null);
    };
    
    @Transactional
    @Override
    public Invoice createInvoice(InvoiceDto invoiceDto){
        try {
            Invoice invoice=new Invoice();
            invoice.setCustomer(invoiceDto.getCustomer());
            invoice.setUser(invoiceDto.getUser());
            invoice.setIssuedDate(LocalDateTime.now());
            invoice.setTotalItems(invoiceDto.getTotalItems());
            invoice.setTotalPrice(invoiceDto.getTotalPrice());
            invoice.setDiscountFraction(invoiceDto.getDiscountFraction());
            Double discount=invoiceDto.getTotalPrice()*invoiceDto.getDiscountFraction();
            invoice.setDiscount(discount);
            invoice.setFinalPrice(invoiceDto.getFinalPrice());

            Invoice createInvoice=invoiceRepository.save(invoice);

            for (CartItem cartItem : invoiceDto.getCartItems()) {
                InvoiceItem invoiceItem=new InvoiceItem();
                invoiceItem.setPurchaseQty(cartItem.getCartQty());
                Double itemDiscount=cartItem.getCartQty()*cartItem.getItem().getUnitPrice()*invoiceDto.getDiscountFraction();
                invoiceItem.setDiscount(itemDiscount);
                invoiceItem.setUnitPrice(cartItem.getItem().getUnitPrice());
                invoiceItem.setUnits(cartItem.getItem().getUnits());
                invoiceItem.setItem(cartItem.getItem());
                invoiceItem.setInvoice(createInvoice);

                InvoiceItem createInvoiceItem=invoiceItemRepository.save(invoiceItem);

                Item existItem=itemRepository.findById(createInvoiceItem.getItem().getId()).orElse(null);

                existItem.setQuantity(existItem.getQuantity()-createInvoiceItem.getPurchaseQty());
                itemRepository.save(existItem);

                CartItem existCartItem=cartItemRepository.findById(cartItem.getId()).orElse(null);
                existCartItem.setStatus("deactive");
                existCartItem.setCartQty(0.0);
                cartItemRepository.save(existCartItem);
            }

            Cart existCart=cartRepository.findById(invoiceDto.getCartItems().get(0).getCart().getId()).orElse(null);
            existCart.setStatus("deactive");
            existCart.setLast_modified(LocalDateTime.now());
            cartRepository.save(existCart);
            return createInvoice;
   
        } catch (Exception e) {
            return null;
        }
    };

    @Override
    public Invoice getLatestInvoice(){
        Invoice lat_invoice=invoiceRepository.testFindTopByOrderByLastUpdatedDesc().orElse(null);
        return lat_invoice;
    };
}
