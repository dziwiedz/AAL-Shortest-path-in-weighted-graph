/**
 * Created by Gulgulgulgul on 12.04.2016.
 */
import Model.*;
import View.*;
import Controller.*;

public class main {

    public static void main(String[] args)
    {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller (model, view);
    }
}
