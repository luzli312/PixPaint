package usecase.colorcanvas;

import interfaceadapter.canvasgrid.ChangeColorController;
import interfaceadapter.export.ExportController;
import interfaceadapter.load.LoadController;
import org.junit.Test;
import view.CanvasGridPanel;
import view.PalettePanel;
import view.PixelArtView;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

import static junit.framework.TestCase.*;

public class PaletteSelectionTest {

    @Test
    public void setCurrentSelectionTest() {
        CanvasGridPanel canvasGridPanel = new CanvasGridPanel(new ChangeColorController());
        PalettePanel palettePanel = new PalettePanel(canvasGridPanel, new ExportController("luzli"), new PixelArtView("luzli"), "luzli");

        PaletteSelection paletteSelection = palettePanel.getPaletteSelection();

        assertNull(paletteSelection.getCurrentSelection());

        JButton colorTileRed = palettePanel.getColorTiles()[0];
        JButton colorTileBlue = palettePanel.getColorTiles()[1];
        BevelBorder bevelBorderRed = (BevelBorder) colorTileRed.getBorder();
        BevelBorder bevelBorderBlue = (BevelBorder) colorTileBlue.getBorder();

        assertEquals(BevelBorder.RAISED, bevelBorderRed.getBevelType());
        assertEquals(BevelBorder.RAISED, bevelBorderBlue.getBevelType());

        colorTileRed.doClick();
        bevelBorderRed = (BevelBorder) colorTileRed.getBorder();

        assertNotNull(paletteSelection.getCurrentSelection());
        assertEquals(Color.RED, paletteSelection.getCurrentSelection().getBackground());
        assertEquals(BevelBorder.LOWERED, bevelBorderRed.getBevelType());

        colorTileBlue.doClick();
        bevelBorderRed = (BevelBorder) colorTileRed.getBorder();
        bevelBorderBlue = (BevelBorder) colorTileBlue.getBorder();

        assertEquals(Color.BLUE, paletteSelection.getCurrentSelection().getBackground());
        assertEquals(BevelBorder.LOWERED, bevelBorderBlue.getBevelType());
        assertEquals(BevelBorder.RAISED, bevelBorderRed.getBevelType());
    }

    @Test
    public void colorCanvasTest() {
        CanvasGridPanel canvasGridPanel = new CanvasGridPanel(new ChangeColorController());
        PalettePanel palettePanel = new PalettePanel(canvasGridPanel, new ExportController("luzli"), new PixelArtView("luzli"), "luzli");

        JButton brushButton = palettePanel.getBrushButton();
                brushButton.doClick();
        BevelBorder bevelBorderBrush = (BevelBorder) brushButton.getBorder();

        assertEquals(BevelBorder.LOWERED, bevelBorderBrush.getBevelType());

        palettePanel.getColorTiles()[0].doClick();

        JButton gridSquare = canvasGridPanel.getAllCells()[0][0];
        assertEquals(Color.WHITE, gridSquare.getBackground());

        gridSquare.doClick();

        assertEquals(Color.RED, gridSquare.getBackground());

        palettePanel.getColorTiles()[1].doClick();
        gridSquare.doClick();

        assertEquals(Color.BLUE, gridSquare.getBackground());
    }

    @Test
    public void eraseCanvasTest() {
        CanvasGridPanel canvasGridPanel = new CanvasGridPanel(new ChangeColorController());
        PalettePanel palettePanel = new PalettePanel(canvasGridPanel, new ExportController("luzli"), new PixelArtView("luzli"), "luzli");

        JButton gridSquare = canvasGridPanel.getAllCells()[0][0];
        gridSquare.setBackground(Color.RED);

        JButton eraserButton = palettePanel.getEraserButton();
        BevelBorder eraserBevelBorder = (BevelBorder) eraserButton.getBorder();
        assertEquals(BevelBorder.RAISED, eraserBevelBorder.getBevelType());

        gridSquare.doClick();
        assertEquals(new Color(0, 0, 0), gridSquare.getBackground());
    }

    @Test
    public void switchBrushEraseTest() {
        CanvasGridPanel canvasGridPanel = new CanvasGridPanel(new ChangeColorController());
        PalettePanel palettePanel = new PalettePanel(canvasGridPanel, new ExportController("luzli"), new PixelArtView("luzli"), "luzli");

        JButton brushButton = palettePanel.getBrushButton();
        JButton eraserButton = palettePanel.getEraserButton();

        BevelBorder brushBevel = (BevelBorder) brushButton.getBorder();
        BevelBorder eraserBevel = (BevelBorder) eraserButton.getBorder();

        JButton colorTileRed = palettePanel.getColorTiles()[0];
        JButton colorTileBlue = palettePanel.getColorTiles()[1];

        colorTileRed.doClick();
        brushButton.doClick();
        brushBevel = (BevelBorder) brushButton.getBorder();

        BevelBorder bevelBorderRed = (BevelBorder) colorTileRed.getBorder();
        BevelBorder bevelBorderBlue = (BevelBorder) colorTileBlue.getBorder();

        assertEquals(BevelBorder.LOWERED, bevelBorderRed.getBevelType());
        assertEquals(BevelBorder.RAISED, bevelBorderBlue.getBevelType());
        assertEquals(BevelBorder.LOWERED, brushBevel.getBevelType());
        assertEquals(BevelBorder.RAISED, eraserBevel.getBevelType());

        eraserButton.doClick();
        brushBevel = (BevelBorder) brushButton.getBorder();
        eraserBevel = (BevelBorder) eraserButton.getBorder();

        assertEquals(BevelBorder.RAISED, brushBevel.getBevelType());
        assertEquals(BevelBorder.LOWERED, eraserBevel.getBevelType());

        colorTileBlue.doClick();
        bevelBorderRed = (BevelBorder) colorTileRed.getBorder();
        bevelBorderBlue = (BevelBorder) colorTileBlue.getBorder();

        assertEquals(BevelBorder.RAISED, bevelBorderRed.getBevelType());
        assertEquals(BevelBorder.LOWERED, bevelBorderBlue.getBevelType());

        JButton gridSquare = canvasGridPanel.getAllCells()[0][0];
        gridSquare.doClick();
        assertNotSame(Color.BLUE, gridSquare.getBackground());

        brushButton.doClick();
        brushBevel = (BevelBorder) brushButton.getBorder();
        eraserBevel = (BevelBorder) eraserButton.getBorder();

        assertEquals(BevelBorder.LOWERED, brushBevel.getBevelType());
        assertEquals(BevelBorder.RAISED, eraserBevel.getBevelType());

        gridSquare.doClick();
        assertEquals(Color.BLUE, gridSquare.getBackground());
    }

}
