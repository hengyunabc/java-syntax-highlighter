package com.rodsum.maptool;

import com.rodsum.CommonFunction;
import com.rodsum.ini.IniHandler;
import com.rodsum.rgui.RGUIListener;
import com.rodsum.rgui.RObject;
import com.rodsum.rgui.RWindow;
import com.rodsum.route.DataDigest;
import com.rodsum.route.DataReader;
import com.rodsum.route.Station;
import com.rodsum.util.ElementUtil.ElementList;
import com.rodsum.util.ElementUtil.ElementSet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Chan Wai Shing <ws.chan@rodsum.com>
 */
public class PostProcess extends JFrame implements ActionListener, RGUIListener {

    private MapTool mapTool;
    private String mapFolderInput;
    //
    private HashMap<Integer, ZoomLevel> zoomLevelMap = new HashMap<Integer, ZoomLevel>();
    //
    private JCheckBox copyStopCodeFromFirstMap;
    private JButton outputButton;
    private JButton cancelButton;

    public PostProcess(MapTool mapTool, String mapFolderInput) {
        this.mapTool = mapTool;
        this.mapFolderInput = mapFolderInput;

        // create zoom level
        for (int i = 1;; i++) {
            File mapFolder = new File(mapFolderInput + "\\" + i);
            if (!mapFolder.exists() || !mapFolder.isDirectory()) {
                break;
            }
            ZoomLevel zoomLevel = new ZoomLevel();
            zoomLevelMap.put(i, zoomLevel);


            // sampling interval
            int samplingRate = 1;
            try {
                IniHandler config = new IniHandler(mapFolderInput + "\\" + i + "\\config.ini");
                samplingRate = Integer.parseInt(config.get("sampling_interval"));
            } catch (IOException ex) {
            }
            zoomLevel.processor = new DataDigest(samplingRate);

            // presentation map
            File presentationMap = new File(mapFolder.getPath() + "\\rail.png");
            File stopNameMap = new File(mapFolder.getPath() + "\\stop_name.png");
            try {
                if (!presentationMap.exists()) {
                    throw new Exception();
                }
                if (!stopNameMap.exists()) {
                    throw new Exception();
                }
                zoomLevel.presentationMap = ImageIO.read(presentationMap);
                Graphics presentationMapGraphics = zoomLevel.presentationMap.getGraphics();
                presentationMapGraphics.drawImage(ImageIO.read(stopNameMap), 0, 0, null);
                presentationMapGraphics.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Presentation map (rail.png) or stop name (stop_name.png) not exist/not valid in " + mapFolder.getPath());
                continue;
            }

            // route image
            for (int j = 1;; j++) {
                File mapFile = new File(mapFolderInput + "\\" + i + "\\route\\" + j + ".png");
                if (!mapFile.exists()) {
                    break;
                }
                try {
                    mapTool.updateProcessingMessage("Processing " + "\\" + i + "\\route\\" + j + ".png");
                    System.out.println(mapFile.getPath());
                    zoomLevel.processor.process(mapFile.getPath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Failed to open route image: " + mapFile.getPath());
                }
            }

            // old information
            File oldInfoFile = new File(mapFolderInput + "\\old\\Map.dat");
            if (oldInfoFile.exists()) {
                try {
                    DataReader dataReader = new DataReader(oldInfoFile.getPath());
                    zoomLevel.stationList = dataReader.readStationList(i);
                } catch (Exception ex) {
                    System.out.println(i + "\\data.txt error");
                }
            }
        }
        if (zoomLevelMap.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No map data found.");
            returnToInput();
            return;
        }

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stop Code Mapping");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/rodsum/maptool/images/staion_large.png")));
        JPanel postProductionPanel = new JPanel();
        postProductionPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        postProductionPanel.setLayout(new BorderLayout());
        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(postProductionPanel, BorderLayout.CENTER);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFocusable(false);
        Iterator iterator = zoomLevelMap.keySet().iterator();
        while (iterator.hasNext()) {
            int zoomLevelKey = (Integer) iterator.next();
            ZoomLevel zoomLevel = zoomLevelMap.get(zoomLevelKey);
            tabbedPane.add("Level " + zoomLevelKey, createPanelForZoomLevel(zoomLevel));
        }

        JPanel postProductionPanelInner = new JPanel();
        postProductionPanelInner.setPreferredSize(new Dimension(800, 550));
        postProductionPanelInner.setLayout(new BorderLayout());
        postProductionPanelInner.add(tabbedPane, BorderLayout.CENTER);

        copyStopCodeFromFirstMap = new JCheckBox("Copy stop code from level 1");
        copyStopCodeFromFirstMap.setSelected(true);
        copyStopCodeFromFirstMap.setFocusable(false);
        outputButton = new JButton("Output");
        outputButton.setActionCommand("output");
        outputButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("cancel");
        cancelButton.addActionListener(this);

        JPanel postProductionPanelButtons = new JPanel();
        postProductionPanelButtons.setLayout(new BoxLayout(postProductionPanelButtons, BoxLayout.X_AXIS));
        postProductionPanelButtons.add(Box.createHorizontalGlue());
        postProductionPanelButtons.add(copyStopCodeFromFirstMap);
        postProductionPanelButtons.add(Box.createRigidArea(new Dimension(5, 5)));
        postProductionPanelButtons.add(outputButton);
        postProductionPanelButtons.add(Box.createRigidArea(new Dimension(5, 5)));
        postProductionPanelButtons.add(cancelButton);

        postProductionPanel.setLayout(new BoxLayout(postProductionPanel, BoxLayout.Y_AXIS));
        postProductionPanel.add(postProductionPanelInner);
        postProductionPanel.add(Box.createRigidArea(new Dimension(5, 5)));
        postProductionPanel.add(postProductionPanelButtons);

        pack();
        CommonFunction.CenterWindow(this);
        setVisible(true);

        mapTool.setVisible(false);
    }

    private JPanel createPanelForZoomLevel(ZoomLevel zoomLevel) {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());


        // map panel
        MapPanel mapPanel = new MapPanel();

        JScrollPane mapScrollPane = new JScrollPane();
        mapScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        mapScrollPane.setBorder(null);
        mapScrollPane.setViewportView(mapPanel);

        zoomLevel.mapWindow = new RWindow(mapPanel);
        zoomLevel.mapWindow.setBackgroundColor(new Color(255, 255, 255));
        zoomLevel.mapWindow.setScrollPane(mapScrollPane);
        zoomLevel.mapWindow.addBackgroundImage(1, zoomLevel.presentationMap);
        zoomLevel.mapWindow.setBackgroundImage(1);
        mapPanel.setRWindow(zoomLevel.mapWindow);

        // list panel
        JPanel listPanel = new JPanel();
        listPanel.setBackground(Color.white);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.add(Box.createRigidArea(new Dimension(5, 5)));

        JScrollPane listScrollPane = new JScrollPane();
        listScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        listScrollPane.setBorder(null);
        listScrollPane.setViewportView(listPanel);


        // list panel header
        JLabel codeLabel = new JLabel("Stop Code");
        codeLabel.setFont(new java.awt.Font("Tahoma", 1, 14));
        codeLabel.setPreferredSize(new Dimension(150, 20));
        codeLabel.setMinimumSize(new Dimension(150, 20));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.add(Box.createRigidArea(new Dimension(5, 5)));
        titlePanel.add(Box.createRigidArea(new Dimension(30, 20)));
        titlePanel.add(codeLabel);
        titlePanel.add(Box.createHorizontalGlue());
        listPanel.add(titlePanel);

        listPanel.add(Box.createRigidArea(new Dimension(10, 10)));


        // indicate extra point
        HashMap<String, ArrayList<Point>> stationToStationExtraPointList = zoomLevel.processor.getStationToStationExtraPointList();
        for (ArrayList<Point> pointList : stationToStationExtraPointList.values()) {
            for (Point point : pointList) {
                try {
                    RObject object = new RObject();
                    object.setVertexColor(new Color(0, 255, 0));
                    object.addImage(1, ImageIO.read(getClass().getClassLoader().getResourceAsStream("com/rodsum/maptool/images/extra_point.png")));
                    object.setImage(1);
                    object.setLocation(point.x, point.y);
                    zoomLevel.mapWindow.addObject(object);
                } catch (IOException ex) {
                    Logger.getLogger(PostProcess.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }


        // draw route
        HashMap<String, ArrayList<Point>> stationToStationRoutePointList = zoomLevel.processor.getStationToStationRoutePointList();
        for (ArrayList<Point> pointList : stationToStationRoutePointList.values()) {
            for (Point point : pointList) {
                zoomLevel.presentationMap.setRGB(point.x, point.y, Color.red.getRGB());
            }
        }


        // generate list items into list panel
        ArrayList<Station> stationList = zoomLevel.processor.getStationList();
        for (int i = 0; i < stationList.size(); i++) {
            Station station = stationList.get(i);

            // draw station points
            ArrayList<Point> pointList = station.getPointList();
            for (Point point : pointList) {
                zoomLevel.presentationMap.setRGB(point.x, point.y, Color.green.getRGB());
            }

            try {
                RObject object = new RObject();
                object.setVertexColor(new Color(0, 255, 0));
                object.addImage(1, ImageIO.read(getClass().getClassLoader().getResourceAsStream("com/rodsum/maptool/images/marker.png")));
                object.addImage(2, ImageIO.read(getClass().getClassLoader().getResourceAsStream("com/rodsum/maptool/images/marker_over.png")));
                object.addImage(3, ImageIO.read(getClass().getClassLoader().getResourceAsStream("com/rodsum/maptool/images/marker_click.png")));
                object.setImage(1);
                object.setLocation(station.getPoint(0, 1).x, station.getPoint(0, 1).y);
                object.setEventListener(this);
                zoomLevel.mapWindow.addObject(object);

                JLabel label = new JLabel(Integer.toString(i));
                label.setPreferredSize(new Dimension(30, 20));
                label.setMinimumSize(new Dimension(30, 20));

                JTextField textField = new JTextField();
                for (String oldStationCode : zoomLevel.stationList.keySet()) {
                    Station _station = zoomLevel.stationList.get(oldStationCode);
                    if (_station.getPoint(0, 1).equals(station.getPoint(0, 1))) {
                        textField.setText(oldStationCode);
                    }
                }
                textField.setPreferredSize(new Dimension(150, 20));
                textField.setMinimumSize(new Dimension(150, 20));
                textField.setMaximumSize(new Dimension(150, 20));

                JButton button = new JButton("Show");
                button.addActionListener(this);
                button.setActionCommand("indicate");

                ElementSet elementSet = new ElementSet();
                elementSet.elementSet.put(1, object);
                elementSet.elementSet.put(2, textField);
                elementSet.elementSet.put(3, button);
                zoomLevel.stationElementList.elementList.add(elementSet);

                JPanel stationPanel = new JPanel();
                stationPanel.setBackground(Color.white);
                stationPanel.setLayout(new BoxLayout(stationPanel, BoxLayout.X_AXIS));
                stationPanel.add(Box.createRigidArea(new Dimension(5, 5)));
                stationPanel.add(label);
                stationPanel.add(textField);
                stationPanel.add(Box.createRigidArea(new Dimension(5, 5)));
                stationPanel.add(button);
                stationPanel.add(Box.createHorizontalGlue());
                listPanel.add(stationPanel);

                listPanel.add(Box.createRigidArea(new Dimension(5, 5)));
            } catch (IOException ex) {
                Logger.getLogger(PostProcess.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        // split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setBorder(null);
        splitPane.setDividerLocation(350);
        splitPane.setTopComponent(mapScrollPane);
        splitPane.setBottomComponent(listScrollPane);
        mainPanel.add(splitPane, BorderLayout.CENTER);

        return mainPanel;
    }

    private void process() {
        deleteDirectory(new File(mapFolderInput + "\\new\\temp"));
        new File(mapFolderInput + "\\new\\temp").mkdir();

        for (int zoomLevelKey : zoomLevelMap.keySet()) {
            ZoomLevel zoomLevel = zoomLevelMap.get(zoomLevelKey);

            HashMap<String, Integer> stationCodeToIdList = new HashMap<String, Integer>();
            int elementCount = zoomLevel.stationElementList.elementList.size();
            for (int i = 0; i < elementCount; i++) {
                ElementSet elementSet = zoomLevel.stationElementList.elementList.get(i);
                if (copyStopCodeFromFirstMap.isSelected()) {
                    ElementSet elementSetCopy = zoomLevelMap.get(1).stationElementList.elementList.get(i);
                    if (!((JTextField) elementSetCopy.elementSet.get(2)).getText().isEmpty()) {
                        stationCodeToIdList.put(((JTextField) elementSetCopy.elementSet.get(2)).getText(), zoomLevelMap.get(1).stationElementList.elementList.indexOf(elementSetCopy));
                    }
                } else if (!((JTextField) elementSet.elementSet.get(2)).getText().isEmpty()) {
                    stationCodeToIdList.put(((JTextField) elementSet.elementSet.get(2)).getText(), zoomLevel.stationElementList.elementList.indexOf(elementSet));
                }
            }
//            for (ElementSet elementSet : zoomLevel.stationElementList.elementList) {
//                if (!((JTextField) elementSet.elementSet.get(2)).getText().isEmpty()) {
//                    stationCodeToIdList.put(((JTextField) elementSet.elementSet.get(2)).getText(), zoomLevel.stationElementList.elementList.indexOf(elementSet));
//                }
//            }
            zoomLevel.processor.setCodeToStation(stationCodeToIdList);

            try {
                createFolder(mapFolderInput + "\\new\\temp\\" + zoomLevelKey);
                createFolder(mapFolderInput + "\\new\\temp\\" + zoomLevelKey + "\\stop");
                createFolder(mapFolderInput + "\\new\\temp\\" + zoomLevelKey + "\\tram");
                preProcessImages(mapFolderInput + "\\" + zoomLevelKey, mapFolderInput + "\\new\\temp\\" + zoomLevelKey);
                zoomLevel.processor.output(mapFolderInput + "\\new\\temp\\" + zoomLevelKey + "\\data.txt");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to output to " + mapFolderInput + "\\new\\temp\\" + zoomLevelKey);
                returnToInput();
                return;
            }
        }

        try {
            ZipOutputStream output = new ZipOutputStream(new FileOutputStream(mapFolderInput + "\\new\\Map.dat"));
            zipDirectory(mapFolderInput + "\\new\\temp\\", mapFolderInput + "\\new\\temp\\", output);
            output.close();

            deleteDirectory(new File(mapFolderInput + "\\new\\temp"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to create package: " + mapFolderInput + "\\new\\Map.dat");
            returnToInput();
            return;
        }

        JOptionPane.showMessageDialog(this, "Output finished.");
        returnToInput();
    }

    private void returnToInput() {
        for (ZoomLevel zoomLevel : zoomLevelMap.values()) {
            zoomLevel.mapWindow.close();
        }
        setVisible(false);
        dispose();
        mapTool.returnToInput();
    }

    private boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    private void zipDirectory(String parentDirectory, String directory, ZipOutputStream output) throws Exception {
        int bytesRead = 0;
        byte[] buffer = new byte[2048];

        File directoryFile = new File(directory);
        File[] fileList = directoryFile.listFiles();
        for (int i = 0; i < fileList.length; i++) {
            File file = fileList[i];

            if (file.isDirectory()) {
                zipDirectory(parentDirectory, file.getPath(), output);
                continue;
            }

            output.putNextEntry(new ZipEntry(file.getPath().replace(parentDirectory, "")));

            FileInputStream in = new FileInputStream(file);
            while ((bytesRead = in.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            in.close();
        }
    }

    private void preProcessImages(String inputPath, String outputPath) throws IOException {
        BufferedImage rail = ImageIO.read(new File(inputPath + "\\rail.png"));
        BufferedImage powerSupply = ImageIO.read(new File(inputPath + "\\power_supply.png"));
        BufferedImage stopName = ImageIO.read(new File(inputPath + "\\stop_name.png"));

        ImageIO.write(rail, "png", new File(outputPath + "\\rail__.png"));
        ImageIO.write(combineImage(rail, powerSupply), "png", new File(outputPath + "\\rail_power_.png"));
        ImageIO.write(combineImage(rail, stopName), "png", new File(outputPath + "\\rail__stop.png"));
        ImageIO.write(combineImage(combineImage(rail, stopName), powerSupply), "png", new File(outputPath + "\\rail_power_stop.png"));

        rail = null;
        powerSupply = null;
        stopName = null;

        BufferedImage tram = ImageIO.read(new File(inputPath + "\\tram\\tram.png"));
        BufferedImage emptyImage = new BufferedImage(tram.getWidth(), tram.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = ((Graphics2D) emptyImage.getGraphics());
        graphics.setBackground(new Color(0, 0, 0, 0));
        graphics.dispose();

        ArrayList<BufferedImage> select = new ArrayList<BufferedImage>();
        select.add(ImageIO.read(new File(inputPath + "\\tram\\tram_onselect.png")));
        select.add(emptyImage);

        ArrayList<BufferedImage> overlap = new ArrayList<BufferedImage>();
        overlap.add(ImageIO.read(new File(inputPath + "\\tram\\tram_overlap.png")));
        overlap.add(emptyImage);

        ArrayList<BufferedImage> arrow = new ArrayList<BufferedImage>();
        arrow.add(ImageIO.read(new File(inputPath + "\\tram\\arrow_up.png")));
        arrow.add(ImageIO.read(new File(inputPath + "\\tram\\arrow_down.png")));
        arrow.add(ImageIO.read(new File(inputPath + "\\tram\\arrow_left.png")));
        arrow.add(ImageIO.read(new File(inputPath + "\\tram\\arrow_right.png")));

        for (BufferedImage selectImage : select) {
            for (BufferedImage overlapImage : overlap) {
                for (BufferedImage arrowImage : arrow) {
                    ImageIO.write(combineImage(combineImage(combineImage(overlapImage, selectImage), tram), arrowImage), "png",
                            new File(outputPath + "\\tram\\tram_" + select.indexOf(selectImage) + "_" + overlap.indexOf(overlapImage) + "_" + arrow.indexOf(arrowImage) + ".png"));
                }
            }
        }

        File stopFolder = new File(inputPath + "\\stop");
        File[] fileName = stopFolder.listFiles();
        for (int i = 0; i < fileName.length; i++) {
            if (fileName[i].getName().equals("stop_border.png")) {
                CommonFunction.copyFile(fileName[i], new File(outputPath + "\\stop\\stop_border_0.png"));

                BufferedImage stopBorder = ImageIO.read(fileName[i]);
                ImageIO.write(CommonFunction.rotateImage(stopBorder, 90), "png", new File(outputPath + "\\stop\\stop_border_90.png"));
                ImageIO.write(CommonFunction.rotateImage(stopBorder, 180), "png", new File(outputPath + "\\stop\\stop_border_180.png"));
                ImageIO.write(CommonFunction.rotateImage(stopBorder, 270), "png", new File(outputPath + "\\stop\\stop_border_270.png"));
            }
        }

        File tramFolder = new File(inputPath + "\\tram");
        fileName = tramFolder.listFiles();
        for (int i = 0; i < fileName.length; i++) {
            if (fileName[i].getName().startsWith("dest_") || fileName[i].getName().startsWith("next_dest_")) {
                CommonFunction.copyFile(fileName[i], new File(outputPath + "\\tram\\" + fileName[i].getName()));
            }
        }
    }

    private BufferedImage combineImage(BufferedImage back, BufferedImage front) {
        BufferedImage temp = new BufferedImage(back.getWidth(), back.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = ((Graphics2D) temp.getGraphics());
        graphics.setBackground(new Color(0, 0, 0, 0));
        graphics.drawImage(back, 0, 0, null);
        graphics.drawImage(front, 0, 0, null);
        graphics.dispose();
        return temp;
    }

    private void createFolder(String path) {
        // 'new' folder
        File newFolder = new File(path);
        if (!newFolder.exists() || !newFolder.isDirectory()) {
            try {
                if (!newFolder.mkdir()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to create '" + path + "' folder.");
                returnToInput();
                return;
            }
        }
    }

    private void enableOperation(boolean enable) {
        if (enable) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } else {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
        cancelButton.setEnabled(enable);
        outputButton.setEnabled(enable);
        outputButton.setText("Processing ...");
        copyStopCodeFromFirstMap.setEnabled(false);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.equals("output")) {
            enableOperation(false);
            new Thread(new Runnable() {

                public void run() {
                    process();
                }
            }).start();
        } else if (cmd.equals("cancel")) {
            returnToInput();
        } else if (cmd.equals("indicate")) {
            ZoomLevel zoomLevel = null;
            ElementSet elementSet = null;

            for (int key : zoomLevelMap.keySet()) {
                zoomLevel = zoomLevelMap.get(key);
                if ((elementSet = zoomLevel.stationElementList.getElementSetByElement(e.getSource())) != null) {
                    break;
                }
            }

            if (zoomLevel.currentOnclick != null) {
                zoomLevel.currentOnclick.setImage(1);
            }
            zoomLevel.currentOnclick = (RObject) elementSet.elementSet.get(1);
            zoomLevel.currentOnclick.setImage(3);

            zoomLevel.mapWindow.panToObject(zoomLevel.currentOnclick);
        }
    }

    // all zoom level share the same RGUIListener, so better use synchronized or split it to different ZoomLevel object
    @Override
    public synchronized void RObjectEvent(RObject object, int eventType, MouseEvent e) {
        ZoomLevel zoomLevel = null;
        ElementSet elementSet = null;

        for (int key : zoomLevelMap.keySet()) {
            zoomLevel = zoomLevelMap.get(key);
            if ((elementSet = zoomLevel.stationElementList.getElementSetByElement(object)) != null) {
                break;
            }
        }

        JTextField textField = (JTextField) elementSet.elementSet.get(2);
        if (eventType == RGUIListener.EVENT_CLICK_FOCUSING) {
            textField.selectAll();
            textField.grabFocus();

            // move the scrollPane to selected station
            JScrollPane scrollPane = (JScrollPane) textField.getParent().getParent().getParent().getParent();
            JPanel panel = (JPanel) textField.getParent().getParent();
            int y = ((JPanel) textField.getParent()).getLocation().y - 5;
            if (y > panel.getHeight() - scrollPane.getHeight()) {
                y = panel.getHeight() - scrollPane.getHeight();
            }
            scrollPane.getViewport().setViewPosition(new Point(0, y));

            if (zoomLevel.currentOnclick != null) {
                zoomLevel.currentOnclick.setImage(1);
            }
            zoomLevel.currentOnclick = object;
            object.setImage(3);
        } else if (eventType == RGUIListener.EVENT_MOUSE_OVER) {
            if (zoomLevel.currentOnclick != object) {
                object.setImage(2);
            }
        } else if (eventType == RGUIListener.EVENT_MOUSE_OUT) {
            if (zoomLevel.currentOnclick != object) {
                object.setImage(1);
            }
        }
    }

    @Override
    public void RWindowEvent(int eventType, MouseEvent e) {
    }

    private class MapPanel extends JPanel {

        private RWindow window;

        public void setRWindow(RWindow window) {
            this.window = window;
        }

        @Override
        public void paint(Graphics g) {
            if (window != null) {
                window.drawGraphic(g);
            }
            super.paint(g);
        }
    }

    private class ZoomLevel {

        public RWindow mapWindow;
        public DataDigest processor;
        public BufferedImage presentationMap;
        public HashMap<String, Station> stationList = new HashMap<String, Station>();
        //
        public ElementList stationElementList = new ElementList();
        //
        public RObject currentOnclick;
    }
}
f