package com.sbp.controller.command.impl;

import com.sbp.controller.command.Command;
import com.sbp.controller.exception.ControllerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;

public class DefaultCommand implements Command {
  private static final Logger LOG = Logger.getLogger(DefaultCommand.class.getName());

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("DEFAULT STARTS.");
    try {
      request.getRequestDispatcher(pathToJsp(Command.prepareUri(request))).forward(request, response);
    } catch (Exception e) {
      throw new ControllerException(e);
    }
  }

  public static String pathToJsp(final String jspName) {
    return "WEB-INF/jsp/" + jspName + ".jsp";
  }

}
