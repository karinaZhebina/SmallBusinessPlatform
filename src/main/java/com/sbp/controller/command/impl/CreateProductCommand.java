package com.sbp.controller.command.impl;

import com.sbp.controller.command.Command;
import com.sbp.controller.exception.ControllerException;
import com.sbp.service.ServiceFactory;
import com.sbp.service.entity.Product;
import com.sbp.service.logic.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.logging.Logger;

public class CreateProductCommand implements Command {
  private static final Logger LOG = Logger.getLogger(CreateProductCommand.class.getName());
  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("CREATE PRODUCT STARTS.");

    try {
      Product product = getProductFromClient(request);
      if (productService.create(product)) {
        response.getWriter().write("Product is created");
      } else {
        response.getWriter().write("Invalid data provided");
      }
    } catch (Exception e) {
      LOG.info(e.getMessage());
      throw new ControllerException(e);
    }
  }

  private Product getProductFromClient(HttpServletRequest request) {
    String productId = request.getParameter("product_id").trim();
    String name = request.getParameter("name").trim();
    String description = request.getParameter("description").trim();
    String businessOwnerId = request.getParameter("business_owner_id").trim();
    BigDecimal price = new BigDecimal(request.getParameter("price").trim());

    return new Product(productId, name, description, businessOwnerId, price);
  }

}
