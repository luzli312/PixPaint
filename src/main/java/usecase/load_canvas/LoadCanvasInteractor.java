package usecase.load_canvas;

import data_access.UserDataAccessObject;
import entity.CanvasData;
import entity.User;
import interface_adapter.canvas_grid.ChangeColorController;
import interface_adapter.logged_in.LoggedInState;
import org.bson.Document;
import view.CanvasGridPanel;

import java.awt.*;

public class LoadCanvasInteractor {

    public void execute(CanvasGridPanel canvasGridPanel) {
        Document rawCanvasData = new UserDataAccessObject().getProject("test");
        Color[][] parsedData = CanvasData.parseCanvasData(rawCanvasData);
        canvasGridPanel.loadCanvasGridPanel(parsedData);
    }

}
