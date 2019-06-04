package at.spengergasse.hbgm.gui;

import at.spengergasse.hbgm.IO.ImagePanel;
import at.spengergasse.hbgm.entities.Patient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.View;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainWindow extends JFrame {

    private PatientBrowser patientTree = new PatientBrowser();

    private JScrollPane browserContainer = new JScrollPane(patientTree);
    private JScrollPane infoContainer = new JScrollPane();
    private JScrollPane controlContainer = new JScrollPane();
    private JLabel status = new JLabel("Status");
    private JPanel imageContainer = new JPanel();

    public MainWindow() throws IOException {
        initFrame();
        initMenu();
        browserContainer.setViewportView(patientTree);

        EntityManager em = Persistence
                .createEntityManagerFactory("viewer")
                .createEntityManager();

        TypedQuery<Patient> query = em
                .createNamedQuery("Patient.findAll", Patient.class);
        List<Patient> patients = query.getResultList();

        for (Patient p : patients) {
            patientTree.add(p);
        }

        patientTree.getSelectionModel().addTreeSelectionListener(new Selector());
        patientTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }

    private void initMenu(){
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem loadDirectoryMenu = new JMenuItem("load directory");
        fileMenu.add(loadDirectoryMenu);
    }

    private void initFrame() throws IOException {
        this.setTitle("ImageViewer");
        this.setLayout(new BorderLayout());
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        split.setDividerLocation(150);
        this.add(split,BorderLayout.CENTER);

        JPanel left = new JPanel(new GridLayout(0,1));
        split.add(left, JSplitPane.LEFT);
        left.add(browserContainer);
        left.add(infoContainer);
        left.add(controlContainer);
        split.add(imageContainer,JSplitPane.RIGHT);

        browserContainer.add(patientTree);
        this.add(status, BorderLayout.SOUTH);

        browserContainer.setBorder(new TitledBorder("Patients"));
        infoContainer.setBorder(new TitledBorder("Info"));
        controlContainer.setBorder(new TitledBorder("Contrast"));
        imageContainer.setBorder(new TitledBorder("Image"));

        imageContainer.setLayout(new BorderLayout());
        imageContainer.add(new ImagePanel(new File("images/sT2W-FLAIR - 401/IM-0001-0002.dcm")), BorderLayout.CENTER);
    }

    public static void main(String[] args) throws IOException {
        MainWindow mw = new MainWindow();
        mw.setSize(800,600);
        mw.setDefaultCloseOperation(EXIT_ON_CLOSE);
        mw.setVisible(true);
    }

    private class Selector implements TreeSelectionListener {
        public void valueChanged(TreeSelectionEvent event) {
            Object obj = event.getNewLeadSelectionPath().getLastPathComponent();
            System.out.println(obj + "*****************************" + "\n" + obj.toString().length());
        }
    }

}
