package com.sbp.controller;

import com.sbp.controller.command.Command;
import com.sbp.controller.command.CommandEnum;
import com.sbp.controller.command.impl.*;
import com.sbp.controller.exception.ControllerException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Logger;

import static com.sbp.controller.command.CommandEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class FrontController extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(FrontController.class.getName());
  private Map<CommandEnum, Command> commandMap;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    initCommandMap();
  }

  private void initCommandMap() {
    if (commandMap == null) {
      commandMap = new EnumMap<>(CommandEnum.class);
    }
    commandMap.put(CREATE_PRODUCT, new CreateProductCommand());
    commandMap.put(UPDATE_PRODUCT, new UpdateProductCommand());
    commandMap.put(DELETE_PRODUCT, new DeleteProductCommand());
    commandMap.put(GET_PRODUCT_BY_ID, new GetProductByIdCommand());
    commandMap.put(GET_ALL_PRODUCTS, new GetAllProductsCommand());
    commandMap.put(DEFAULT, new DefaultCommand());
    commandMap.put(GO_TO_PAGE, new GoToPageCommand());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//    commandMap.get(GET_ALL_PRODUCTS).process(req, resp);
    doProcess(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
    doProcess(req, resp);
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
    doProcess(req, resp);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
    doProcess(req, resp);
  }

  private void doProcess(HttpServletRequest req, HttpServletResponse resp) {
    LOG.info("Call to FrontController#doProcess()");
    try {
      final CommandEnum command = getCommand(req.getParameter("command"));
      commandMap.get(command).process(req, resp);
    } catch (ControllerException e) {
      Throwable cause = getCause(e);
      LOG.warning(cause.getMessage());
    }
  }

  private CommandEnum getCommand(String commandNameParam) {
    if (isNull(commandNameParam)) {
      commandNameParam = "default";
    }
    return CommandEnum.valueOf(commandNameParam.toUpperCase());
  }

  private Throwable getCause(Throwable cause) {
    if (nonNull(cause.getCause())) {
      cause = getCause(cause.getCause()); // recursive call of same method inside of it
    }
    return cause;
  }

}

