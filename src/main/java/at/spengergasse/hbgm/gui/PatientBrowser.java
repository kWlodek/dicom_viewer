package at.spengergasse.hbgm.gui;

import at.spengergasse.hbgm.entities.Instance;
import at.spengergasse.hbgm.entities.Patient;
import at.spengergasse.hbgm.entities.Series;
import at.spengergasse.hbgm.entities.Study;

import javax.persistence.criteria.CriteriaBuilder;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class PatientBrowser extends JTree {
    private DefaultMutableTreeNode rootNode;
    private DefaultTreeModel model;

    public PatientBrowser(){
        rootNode = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(rootNode);
        this.setModel(model);
        this.setCellRenderer(new Renderer());
    }

    public void add(Patient p){
        DefaultMutableTreeNode patientNode
                = new DefaultMutableTreeNode(p);
        rootNode.add(patientNode);
        for (Study st : p.getStudyList()){
            DefaultMutableTreeNode studyNode
                    = new DefaultMutableTreeNode(st);
            patientNode.add(studyNode);
            for (Series s : st.getSeriesList()){
                DefaultMutableTreeNode seriesNode
                        = new DefaultMutableTreeNode(s);
                studyNode.add(seriesNode);
                for (Instance i : s.getInstanceList()){
                    DefaultMutableTreeNode instanceNode
                            = new DefaultMutableTreeNode(i);
                    seriesNode.add(instanceNode);
                }
            }
        }
        model.reload();
    }

    private static class Renderer extends DefaultTreeCellRenderer{
        @Override
        public Component getTreeCellRendererComponent(
                JTree tree, Object value, boolean sel,
                boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            System.out.println(value);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object userObject = node.getUserObject();
            String display = "...";
            if (userObject instanceof Patient){
                Patient p = (Patient)userObject;
                display = p.getPatientID() + " " + p.getName();
            }
            if (userObject instanceof Study){
                Study s = (Study)userObject;
                display = s.getStudyDate().toString();
            }
            if (userObject instanceof Series){
                Series se = (Series) userObject;
                display = se.getSeriesInstanceUID().toString();
            }
            if (userObject instanceof Instance){
                Instance i = (Instance) userObject;
                display = i.getSopInstanceUID().toString();
            }


            return super.getTreeCellRendererComponent(tree, display, sel, expanded, leaf, row, hasFocus);
        }
    }

}
