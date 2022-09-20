package com.sbp.controller.command.impl;

import com.sbp.controller.command.Command;
import com.sbp.controller.exception.ControllerException;
import com.sbp.service.ServiceFactory;
import com.sbp.service.entity.Product;
import com.sbp.service.logic.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static com.sbp.controller.command.impl.DefaultCommand.pathToJsp;

public class DeleteProductCommand implements Command {

  private static final Logger LOG = Logger.getLogger(DeleteProductCommand.class.getName());
  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("DELETE PRODUCT STARTS.");
    String message;
    try {
      String product_id = request.getParameter("product_id");

      if (productService.delete(new Product(product_id))) {
        message = "Product is deleted";
      } else {
        message = "No such product exists";
      }
      request.getSession().setAttribute("message", message);
      request.getRequestDispatcher(pathToJsp(Command.prepareUri(request))).forward(request, response);
    } catch (Exception e) {
      request.getSession().setAttribute("message", e.getMessage());
      LOG.info(e.getMessage());
      throw new ControllerException(e);
    }
  }

}
