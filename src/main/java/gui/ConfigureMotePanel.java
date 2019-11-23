package gui;


import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import gui.configuration.AbstractConfigurePanel;
import gui.util.CompoundPainterBuilder;
import iot.networkentity.Mote;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConfigureMotePanel extends AbstractConfigurePanel {
    private JPanel mainPanel;
    private JPanel drawPanel;

    public ConfigureMotePanel(MainGUI mainGUI) {
        super(mainGUI, 5);
        mapViewer.addMouseListener(new MapMouseAdapter(this));
        loadMap(false);
    }

    protected void loadMap(boolean isRefresh) {
        mapViewer.setOverlayPainter(new CompoundPainterBuilder()
            .withMotes(environment)
            .withBorders(environment)
            .build()
        );

        if (!isRefresh) {
            mapViewer.setAddressLocation(environment.getMapCenter());
        }

        drawPanel.add(mapViewer);
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }

    private class MapMouseAdapter implements MouseListener {
        private ConfigureMotePanel panel;

        MapMouseAdapter(ConfigureMotePanel panel) {
            this.panel = panel;
        }

        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1) {
                Point p = e.getPoint();
                GeoPosition geo = mapViewer.convertPointToGeoPosition(p);

                boolean exists = false;

                for (Mote mote : environment.getMotes()) {
                    int xDistance = Math.abs(environment.toMapXCoordinate(geo) - mote.getXPosInt());
                    int yDistance = environment.toMapYCoordinate(geo) - mote.getYPosInt();
                    if (xDistance < 100 && yDistance > -20 && yDistance < 250) {
                        JFrame frame = new JFrame("Mote settings");
                        MoteGUI moteGUI = new MoteGUI(environment, mote.getOriginalPosInt(), frame, panel, mainGUI, mote);
                        frame.setContentPane(moteGUI.getMainPanel());
                        frame.setMinimumSize(moteGUI.getMainPanel().getMinimumSize());
                        frame.setPreferredSize(moteGUI.getMainPanel().getPreferredSize());
                        frame.setVisible(true);
                        exists = true;
                        frame.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosed(WindowEvent e) {
                                refresh();
                            }
                        });

                    }
                }

                if (!exists) {
                    JFrame frame = new JFrame("New mote");
                    MoteGUI moteGUI = new MoteGUI(environment, geo, frame, panel, mainGUI);
                    frame.setMinimumSize(moteGUI.getMainPanel().getMinimumSize());
                    frame.setPreferredSize(moteGUI.getMainPanel().getPreferredSize());
                    frame.setContentPane(moteGUI.getMainPanel());
                    frame.setVisible(true);
                }

            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        drawPanel = new JPanel();
        drawPanel.setLayout(new BorderLayout(0, 0));
        mainPanel.add(drawPanel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        drawPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 15), null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(15, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(15, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }


}
