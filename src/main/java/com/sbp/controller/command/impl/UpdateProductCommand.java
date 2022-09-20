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

public class UpdateProductCommand implements Command {

  private static final Logger LOG = Logger.getLogger(UpdateProductCommand.class.getName());
  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("UPDATE PRODUCT STARTS.");

    String productId = request.getParameter("product_id");
    String lastCommand = "frontController?command=get_product_by_id&id=" + productId;

    try {
      Product product = getUpdatedProduct(request);
      if (productService.update(product)) {
        lastCommand = "frontController?command=go_to_page&address=product_info.jsp&id=" + productId;
        response.sendRedirect(lastCommand);
      } else {
        response.getWriter().write("Product is not updated");
        request.getRequestDispatcher(lastCommand).forward(request, response);
      }
    } catch (Exception e) {
      LOG.info(e.getMessage());
      throw new ControllerException(e);
    }
  }

  private Product getUpdatedProduct(HttpServletRequest request) {
    String productId = request.getParameter("product_id").trim();
    String name = request.getParameter("name").trim();
    String description = request.getParameter("description").trim();
    String businessOwnerId = request.getParameter("business_owner_id").trim();
    BigDecimal price = new BigDecimal(request.getParameter("price").trim());

    return new Product(productId, name, description, businessOwnerId, price);
  }

}
