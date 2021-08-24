package ua.com.vovacoffee.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.vovacoffee.exception.BadRequestException;
import ua.com.vovacoffee.exception.DuplicateException;
import ua.com.vovacoffee.exception.ForbiddenException;
import ua.com.vovacoffee.exception.WrongInformationException;
import ua.com.vovacoffee.service.ShoppingCartService;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AdviceController {

    private ShoppingCartService shoppingCartService;

    private static final Logger logger = Logger.getLogger(AdviceController.class);

    @Autowired
    public AdviceController(ShoppingCartService shoppingCartService) {
        super();
        this.shoppingCartService = shoppingCartService;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка 404. Не найдено!");
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView badRequestException(BadRequestException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка в запросе!");
    }

    @ExceptionHandler(WrongInformationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView wrongInformationException(WrongInformationException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка в запросе!");
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView forbiddenException(ForbiddenException ex, HttpServletRequest request) {
        return handleException(ex, request, "У Вас нет достаточных прав для доступа к этой странице.");
    }

    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView duplicateException(DuplicateException ex, HttpServletRequest request) {
        return handleException(ex, request, "Такой объект уже существует!");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView otherException(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, "Временные неполадки с сервером... Приносим свои извинения!");
    }

    private ModelAndView handleException(Exception ex, HttpServletRequest request, String textError) {
        logger.error(request.getRemoteAddr() + " : " + request.getRequestURL());
        logger.error(ex.getMessage(), ex);
        ex.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("cart_size", shoppingCartService.getSize());
        modelAndView.addObject("text_error", textError);
        modelAndView.setViewName("client/error");
        return modelAndView;
    }
}
