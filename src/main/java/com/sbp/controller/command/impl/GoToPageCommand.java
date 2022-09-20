package com.sbp.controller.command.impl;

import com.sbp.controller.command.Command;
import com.sbp.controller.exception.ControllerException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class GoToPageCommand implements Command {
  private static final Logger LOG = Logger.getLogger(GoToPageCommand.class.getName());

  @Override
  public void process(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
    LOG.info("Start in GoToPageCommand");

    try {
      String goToPage = "/index.jsp".equals(request.getParameter("address")) ? "/index.jsp" : "/WEB-INF/jsp/" + request.getParameter("address");
      request.getRequestDispatcher(goToPage).forward(request, response);
    } catch (IOException | ServletException e) {
      throw new ControllerException(e);
    }
  }

}
