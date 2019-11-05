package GUI;

import IotDomain.networkentity.Gateway;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GatewayGUI extends JFrame {
    private JSpinner xPosSpinner;
    private JSpinner yPosSpinner;
    private JSpinner powerSpinner;
    private JSpinner SFSpinner;
    private JButton saveButton;
    private JLabel longitudeLabel;
    private JLabel latitudeLabel;
    private JPanel mainPanel;
    private JLabel EUIDText;
    private JLabel TPthresholdText;
    private JLabel SFLabel;
    private JLabel powerSettingLabel;
    private JLabel yCoordinateLabel;
    private JLabel xCoordinateLabel;
    private JLabel gatewayNumberLabel;
    private Gateway gateway;
    private JFrame frame;

    public GatewayGUI(Gateway gateway, JFrame frame) {
        this.frame = frame;
        this.gateway = gateway;
        gatewayNumberLabel.setText(Integer.toString(gateway.getEnvironment().getGateways().indexOf(gateway) + 1));
        EUIDText.setText(Long.toUnsignedString(gateway.getEUI()));
        Double latitude = gateway.getEnvironment().toLatitude(gateway.getYPosInt());
        Integer latitudeDegrees = (int) Math.round(Math.floor(latitude));
        Integer latitudeMinutes = (int) Math.round(Math.floor((latitude - latitudeDegrees) * 60));
        Double latitudeSeconds = (double) Math.round(((latitude - latitudeDegrees) * 60 - latitudeMinutes) * 60 * 1000d) / 1000d;
        Double longitude = gateway.getEnvironment().toLongitude(gateway.getXPosInt());
        Integer longitudeDegrees = (int) Math.round(Math.floor(longitude));
        Integer longitudeMinutes = (int) Math.round(Math.floor((longitude - longitudeDegrees) * 60));
        Double longitudeSeconds = (double) Math.round(((longitude - longitudeDegrees) * 60 - longitudeMinutes) * 60 * 1000d) / 1000d;
        latitudeLabel.setText(((Math.signum(gateway.getEnvironment().toLatitude(gateway.getYPosInt())) == 1) ? "N " : "S ") +
                latitudeDegrees + "° " + latitudeMinutes + "' " + latitudeSeconds + "\" ");
        longitudeLabel.setText(((Math.signum(gateway.getEnvironment().toLongitude(gateway.getXPosInt())) == 1) ? "E " : "W ") +
                longitudeDegrees + "° " + longitudeMinutes + "' " + longitudeSeconds + "\" ");

        xCoordinateLabel.setText(Integer.toString(gateway.getXPosInt()));
        yCoordinateLabel.setText(Integer.toString(gateway.getYPosInt()));
        powerSettingLabel.setText(Integer.toString(gateway.getTransmissionPower()));
        SFLabel.setText(Integer.toString(gateway.getSF()));

        TPthresholdText.setText(gateway.getTransmissionPowerThreshold().toString());


    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void refresh() {
        EUIDText.setText(Long.toUnsignedString(gateway.getEUI()));
        Double latitude = gateway.getEnvironment().toLatitude(gateway.getYPosInt());
        Integer latitudeDegrees = (int) Math.round(Math.floor(latitude));
        Integer latitudeMinutes = (int) Math.round(Math.floor((latitude - latitudeDegrees) * 60));
        Double latitudeSeconds = (double) Math.round(((latitude - latitudeDegrees) * 60 - latitudeMinutes) * 60 * 1000d) / 1000d;
        Double longitude = gateway.getEnvironment().toLongitude(gateway.getXPosInt());
        Integer longitudeDegrees = (int) Math.round(Math.floor(longitude));
        Integer longitudeMinutes = (int) Math.round(Math.floor((longitude - longitudeDegrees) * 60));
        Double longitudeSeconds = (double) Math.round(((longitude - longitudeDegrees) * 60 - longitudeMinutes) * 60 * 1000d) / 1000d;
        latitudeLabel.setText(((Math.signum(gateway.getEnvironment().toLatitude(gateway.getYPosInt())) == 1) ? "N " : "S ") +
                latitudeDegrees + "° " + latitudeMinutes + "' " + latitudeSeconds + "\" ");
        longitudeLabel.setText(((Math.signum(gateway.getEnvironment().toLongitude(gateway.getXPosInt())) == 1) ? "E " : "W ") +
                longitudeDegrees + "° " + longitudeMinutes + "' " + longitudeSeconds + "\" ");
        xPosSpinner.setValue(gateway.getXPosInt());
        yPosSpinner.setValue(gateway.getYPosInt());
        powerSpinner.setValue(gateway.getTransmissionPower());
        SFSpinner.setValue(gateway.getSF());
        TPthresholdText.setText(gateway.getTransmissionPowerThreshold().toString());
    }

    ActionListener saveActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            gateway.setSF((Integer) SFSpinner.getValue());
            gateway.setXPos((Integer) xPosSpinner.getValue());
            gateway.setYPos((Integer) yPosSpinner.getValue());
            gateway.setTransmissionPower((Integer) powerSpinner.getValue());
            refresh();
            frame.dispose();
        }
    };

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
        mainPanel.setLayout(new GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("EUID");
        mainPanel.add(label1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("x-coordinate");
        mainPanel.add(label2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("y-coordinate");
        mainPanel.add(label3, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Powersetting");
        mainPanel.add(label4, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Spreading factor");
        mainPanel.add(label5, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Transmission power threshold");
        mainPanel.add(label6, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(20, -1), new Dimension(20, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), null, 0, false));
        final Spacer spacer3 = new Spacer();
        mainPanel.add(spacer3, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, new Dimension(10, -1), new Dimension(10, -1), null, 0, false));
        final Spacer spacer4 = new Spacer();
        mainPanel.add(spacer4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 20), new Dimension(20, -1), null, 0, false));
        longitudeLabel = new JLabel();
        longitudeLabel.setText("Label");
        mainPanel.add(longitudeLabel, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        latitudeLabel = new JLabel();
        latitudeLabel.setText("Label");
        mainPanel.add(latitudeLabel, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        EUIDText = new JLabel();
        EUIDText.setText("Label");
        mainPanel.add(EUIDText, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        TPthresholdText = new JLabel();
        TPthresholdText.setText("Label");
        mainPanel.add(TPthresholdText, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        xCoordinateLabel = new JLabel();
        xCoordinateLabel.setText("Label");
        mainPanel.add(xCoordinateLabel, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        yCoordinateLabel = new JLabel();
        yCoordinateLabel.setText("Label");
        mainPanel.add(yCoordinateLabel, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        powerSettingLabel = new JLabel();
        powerSettingLabel.setText("Label");
        mainPanel.add(powerSettingLabel, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SFLabel = new JLabel();
        SFLabel.setText("Label");
        mainPanel.add(SFLabel, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Gateway");
        mainPanel.add(label7, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        gatewayNumberLabel = new JLabel();
        gatewayNumberLabel.setText("");
        mainPanel.add(gatewayNumberLabel, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
