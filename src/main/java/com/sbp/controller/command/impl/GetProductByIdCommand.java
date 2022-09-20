package com.sbp.controller.command.impl;

import com.sbp.controller.command.Command;
import com.sbp.controller.exception.ControllerException;
import com.sbp.service.ServiceFactory;
import com.sbp.service.entity.Product;
import com.sbp.service.exception.ServiceException;
import com.sbp.service.logic.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;

public class GetProductByIdCommand implements Command {

  private static final Logger LOG = Logger.getLogger(GetProductByIdCommand.class.getName());
  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("GET PRODUCT BY ID STARTS.");

    String productId = request.getParameter("product_id");
    String message;

    try {
      Product product = productService.getById(productId);

      if (nonNull(product)) {
        request.setAttribute("product", product);
      } else {
        message = "There is not such a product in DB";
        request.getSession().setAttribute("message", message);
      }
      request.getRequestDispatcher("frontController?command=go_to_page&address=product_info.jsp").forward(request, response);
    } catch (ServiceException | ServletException | IOException e) {
      request.getSession().setAttribute("message", e.getMessage());
      LOG.info(e.getMessage());
      throw new ControllerException(e);
    }
  }

}
