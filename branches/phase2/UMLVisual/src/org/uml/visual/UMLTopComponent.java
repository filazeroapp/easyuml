package org.uml.visual;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.spi.palette.PaletteController;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;
import org.uml.model.ClassDiagram;
import org.uml.visual.palette.PaletteSupport;
import org.uml.visual.widgets.ClassDiagramScene;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//org.uml.visual//UML//EN",
        autostore = false)
@TopComponent.Description(
        preferredID = "UMLTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.uml.visual.UMLTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_UMLAction",
        preferredID = "UMLTopComponent")
@Messages({
    "CTL_UMLAction=UML",
    "CTL_UMLTopComponent=UML Class Diagramv Window",
    "HINT_UMLTopComponent=This is a UML Class Diagram window"
})
public final class UMLTopComponent extends TopComponent {

    private ClassDiagram classDiagram;
    private ClassDiagramScene classDiagramScene;
    private JScrollPane classDiagramPanel;    
    private PaletteController palette;
    
    
    public UMLTopComponent() {
        initComponents();
        setName(Bundle.CTL_UMLTopComponent());
        setToolTipText(Bundle.HINT_UMLTopComponent());
        this.classDiagram = new ClassDiagram(); // create new empty class diagram
        classDiagramScene = new ClassDiagramScene(this.classDiagram);
        classDiagramPanel = new JScrollPane();
        classDiagramPanel.setViewportView(classDiagramScene.createView());
        add(classDiagramPanel, BorderLayout.CENTER);
        add(classDiagramScene.createSatelliteView(), BorderLayout.WEST);
        palette = PaletteSupport.createPalette();
    }

    public UMLTopComponent(ClassDiagram classDiagram) {
        this.classDiagram = classDiagram;
        initComponents();
        setName(classDiagram.getName());
        setToolTipText(Bundle.HINT_UMLTopComponent());
        
        classDiagramScene = new ClassDiagramScene(classDiagram);
        classDiagramPanel = new JScrollPane();
        classDiagramPanel.setViewportView(classDiagramScene.createView());
        add(classDiagramPanel, BorderLayout.CENTER);
        add(classDiagramScene.createSatelliteView(), BorderLayout.WEST);

        palette = PaletteSupport.createPalette();
        classDiagramScene.validate();
    }

    @Override
    public Lookup getLookup() {
        return new ProxyLookup(
                new Lookup[]{
            classDiagramScene.getLookup(),
            Lookups.singleton(classDiagram),
            Lookups.singleton(palette)
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewPane = new javax.swing.JScrollPane();

        setLayout(new java.awt.BorderLayout());
        add(viewPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane viewPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {

    }

    @Override
    public void componentClosed() {

    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
//    @Override
//    public Lookup getLookup() {
//        return new ProxyLookup(new Lookup[]{super.getLookup(),aLookup});
//    }
//    Project selectedProject;
//
//    @Override
//    public void resultChanged(LookupEvent le) {
//        Lookup.Result localResult = (Result)le.getSource();
//        Collection<Object> coll = localResult.allInstances();
//        if (!coll.isEmpty()){
//            for (Object selectedItem : coll){
//                if (selectedItem instanceof Project) selectedProject = (Project) selectedItem;
//            }
//        }
//        
//         FileObject folder = selectedProject.getProjectDirectory();
//         String path = folder.getPath();
//    }
    
    public ClassDiagramScene getScene() {
        return classDiagramScene;
    }
}
