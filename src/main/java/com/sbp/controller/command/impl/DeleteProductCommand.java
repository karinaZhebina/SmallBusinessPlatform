package com.sbp.controller.command.impl;

import com.sbp.controller.command.Command;
import com.sbp.controller.exception.ControllerException;
import com.sbp.service.ServiceFactory;
import com.sbp.service.entity.Product;
import com.sbp.service.exception.ServiceException;
import com.sbp.service.logic.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class DeleteProductCommand implements Command {

  private static final Logger LOG = Logger.getLogger(DeleteProductCommand.class.getName());
  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("DELETE PRODUCT STARTS.");

    try {
      String product_id = request.getParameter("product_id");

      if (productService.delete(new Product(product_id))) {
        response.getWriter().write("Product is deleted");
      } else {
        response.getWriter().write("Product is not deleted");
      }
    } catch (IOException | ServiceException e) {
      LOG.info(e.getMessage());
      throw new ControllerException(e);
    }
  }

}
