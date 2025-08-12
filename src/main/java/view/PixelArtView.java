package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import dataaccess.UserDataAccessObject;
import entity.User;
import interfaceadapter.export.ExportController;
import interfaceadapter.load.LoadController;
import usecase.loadcanvas.LoadCanvasInteractor;
import usecase.loadcanvas.LoadInputBoundary;

public class PixelArtView extends JPanel {
    private static final Integer BORDER_SIZE = 10;

    private final CanvasPanel canvasPanel;
    private final PalettePanel palettePanel;

    public PixelArtView(String username) {

        final ExportController exportController = new ExportController(username);

        this.setLayout(new BorderLayout(BORDER_SIZE, BORDER_SIZE));
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));

        canvasPanel = new CanvasPanel();
        palettePanel = new PalettePanel(canvasPanel.getCanvasGridPanel(), exportController, this, username);

        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(BORDER_SIZE, BORDER_SIZE));
        rightPanel.add(palettePanel.getToolPanel(), BorderLayout.NORTH);
        rightPanel.add(palettePanel, BorderLayout.CENTER);
        rightPanel.add(palettePanel.getButtonPanel(), BorderLayout.SOUTH);

        this.add(canvasPanel, BorderLayout.CENTER);
        this.add(rightPanel, BorderLayout.EAST);
    }

    public CanvasPanel getCanvasPanel() {
        return canvasPanel;
    }
}
