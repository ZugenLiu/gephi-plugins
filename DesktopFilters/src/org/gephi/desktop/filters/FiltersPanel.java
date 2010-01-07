/*
Copyright 2008 WebAtlas
Authors : Mathieu Bastian, Mathieu Jacomy, Julian Bilcke
Website : http://www.gephi.org

This file is part of Gephi.

Gephi is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Gephi is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Gephi.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gephi.desktop.filters;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.gephi.desktop.filters.library.FiltersExplorer;
import org.gephi.desktop.filters.query.QueryExplorer;
import org.gephi.filters.api.FilterController;
import org.gephi.filters.api.FilterModel;
import org.gephi.filters.api.Query;
import org.openide.explorer.ExplorerManager;
import org.openide.util.Lookup;
import org.w3c.dom.Document;

/**
 *
 * @author Mathieu Bastian
 */
public class FiltersPanel extends javax.swing.JPanel implements ExplorerManager.Provider {

    private ExplorerManager manager = new ExplorerManager();
    private FilterModel model;
    private FilterUIModel uIModel;

    public FiltersPanel() {
        initComponents();
        uIModel = new FilterUIModel();
        model = Lookup.getDefault().lookup(FilterController.class).getModel();
        ((FiltersExplorer) libraryTree).setup(manager, model, uIModel);

        southPanel.add(new FunctionPanel(), BorderLayout.CENTER);
        filtersUIPanel.add(new FilterPanelPanel(uIModel));

        resetButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                FilterController controller = Lookup.getDefault().lookup(FilterController.class);
                for (Query query : model.getQueries()) {
                    controller.remove(query);
                }
                uIModel.setSelectedFilter(null);
            }
        });

    }

    private class FunctionPanel extends JPanel implements ExplorerManager.Provider {

        private ExplorerManager manager = new ExplorerManager();

        public FunctionPanel() {
            super(new BorderLayout());
            QueryExplorer functionExplorer = new QueryExplorer();
            functionExplorer.setup(manager, model, uIModel);
            add(functionExplorer, BorderLayout.CENTER);
        }

        public ExplorerManager getExplorerManager() {
            return manager;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        toolbar = new javax.swing.JToolBar();
        resetButton = new javax.swing.JButton();
        splitPane = new javax.swing.JSplitPane();
        southPanel = new javax.swing.JPanel();
        libraryTree = new FiltersExplorer();
        filtersUIPanel = new javax.swing.JPanel();
        applyButton = new javax.swing.JButton();
        southToolbar = new javax.swing.JToolBar();
        dynamicRefreshButton = new javax.swing.JToggleButton();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        toolbar.setFloatable(false);
        toolbar.setRollover(true);

        resetButton.setText(org.openide.util.NbBundle.getMessage(FiltersPanel.class, "FiltersPanel.resetButton.text")); // NOI18N
        resetButton.setFocusable(false);
        resetButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resetButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolbar.add(resetButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 1.0;
        add(toolbar, gridBagConstraints);

        splitPane.setDividerLocation(260);
        splitPane.setDividerSize(3);
        splitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        southPanel.setLayout(new java.awt.BorderLayout());
        splitPane.setRightComponent(southPanel);
        splitPane.setLeftComponent(libraryTree);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(splitPane, gridBagConstraints);

        filtersUIPanel.setPreferredSize(new java.awt.Dimension(0, 50));
        filtersUIPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 1, 5);
        add(filtersUIPanel, gridBagConstraints);

        applyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gephi/desktop/filters/resources/apply.png"))); // NOI18N
        applyButton.setText(org.openide.util.NbBundle.getMessage(FiltersPanel.class, "FiltersPanel.applyButton.text")); // NOI18N
        applyButton.setMargin(new java.awt.Insets(2, 7, 2, 14));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        add(applyButton, gridBagConstraints);

        southToolbar.setFloatable(false);
        southToolbar.setRollover(true);

        dynamicRefreshButton.setText(org.openide.util.NbBundle.getMessage(FiltersPanel.class, "FiltersPanel.dynamicRefreshButton.text")); // NOI18N
        dynamicRefreshButton.setFocusable(false);
        dynamicRefreshButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dynamicRefreshButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        southToolbar.add(dynamicRefreshButton);

        jButton1.setText(org.openide.util.NbBundle.getMessage(FiltersPanel.class, "FiltersPanel.jButton1.text")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        southToolbar.add(jButton1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        add(southToolbar, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (model.getQueries().length == 0) {
            //Load
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new File("model.xml"));
                //((FilterModelImpl) model).readXML(document);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            //Save
            try {
                //Create doc
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                final Document document = documentBuilder.newDocument();
                document.setXmlVersion("1.0");
                document.setXmlStandalone(true);

                //Write doc
                // ((FilterModelImpl) model).writeXML(document);

                //Write XML file
                Source source = new DOMSource(document);
                Result result = new StreamResult(new File("model.xml"));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                transformer.transform(source, result);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JToggleButton dynamicRefreshButton;
    private javax.swing.JPanel filtersUIPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane libraryTree;
    private javax.swing.JButton resetButton;
    private javax.swing.JPanel southPanel;
    private javax.swing.JToolBar southToolbar;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JToolBar toolbar;
    // End of variables declaration//GEN-END:variables

    public ExplorerManager getExplorerManager() {
        return manager;
    }
}