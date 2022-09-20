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

import static com.sbp.controller.command.impl.DefaultCommand.pathToJsp;

public class CreateProductCommand implements Command {
  private static final Logger LOG = Logger.getLogger(CreateProductCommand.class.getName());
  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("CREATE PRODUCT STARTS.");
    String message;
    try {
      Product product = getProductFromClient(request);
      if (productService.create(product)) {
        message = "Product is created";
      } else {
        message = "Product with such title already exists!";
      }
      request.getSession().setAttribute("message", message);
      request.getRequestDispatcher(pathToJsp(Command.prepareUri(request))).forward(request, response);
    } catch (Exception e) {
      request.getSession().setAttribute("message", e.getMessage());
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
