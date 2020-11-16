package Controller;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("TextValidator")
public class TextValidator implements Validator {
    private FacesMessage ex1 = new FacesMessage("Вы не ввели Y");
    private FacesMessage ex2 = new FacesMessage("Y должен быть от -3 до 3");
    private FacesMessage ex3 = new FacesMessage("Y должен быть числом");

    /**
     * Опа серверная валидация))
     * @param facesContext
     * @param uiComponent
     * @param o
     * @throws ValidatorException
     */
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        try {
            if (o.toString().equals("")) {
                throw new ValidatorException(ex1);
            }
            int y = Integer.parseInt(String.valueOf(o));
            if (y < -3 || y > 3) {
                System.out.println("aaaa");
                throw new ValidatorException(ex2);
            }
        } catch (NumberFormatException e) {
            throw new ValidatorException(ex3);
        }
    }
}