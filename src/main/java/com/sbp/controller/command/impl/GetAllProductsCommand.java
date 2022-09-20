package com.sbp.controller.command.impl;

import com.sbp.controller.command.Command;
import com.sbp.controller.exception.ControllerException;
import com.sbp.service.ServiceFactory;
import com.sbp.service.entity.Page;
import com.sbp.service.entity.Product;
import com.sbp.service.logic.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

import static com.sbp.controller.command.impl.DefaultCommand.pathToJsp;
import static java.util.Objects.isNull;

public class GetAllProductsCommand implements Command {

  private static final Logger LOG = Logger.getLogger(GetAllProductsCommand.class.getName());

  private final ProductService productService = ServiceFactory.getInstance().getProductService();

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("GET ALL PRODUCTS STARTS.");
    try {
      // prepare data
      String currentPageParam = request.getParameter("currentPage");
      if (isNullOrEmpty(currentPageParam)) {
        currentPageParam = "1";
      }
      String currentLimitParam = request.getParameter("pageLimit");
      if (isNullOrEmpty(currentLimitParam)) {
        currentLimitParam = "5";
      }
      int currentPage = Integer.parseInt(currentPageParam);
      int pageLimit = Integer.parseInt(currentLimitParam);
      final Page<Product> pageRequest = new Page<>();
      pageRequest.setPageNumber(currentPage);
      pageRequest.setLimit(pageLimit);

      // business logic
      final Page<Product> pageable = productService.getAllProducts(pageRequest);

      // send response
      request.setAttribute("pageable", pageable);
      request.getRequestDispatcher(pathToJsp(Command.prepareUri(request))).forward(request, response);
    } catch (Exception e) {
      throw new ControllerException(e);
    }
  }

  public static boolean isNullOrEmpty(final String s) {
    return isNull(s) || s.isEmpty();
  }

}
