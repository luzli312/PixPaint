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

    public void execute(String username, CanvasGridPanel canvasGridPanel, String title) {
        Document rawCanvasData = new UserDataAccessObject().getProject(username, title);
        Color[][] parsedData = CanvasData.parseCanvasData(rawCanvasData);
        canvasGridPanel.loadCanvasGridPanel(parsedData);
        System.out.println("Ran Load Canvas Interactor");
    }
}
