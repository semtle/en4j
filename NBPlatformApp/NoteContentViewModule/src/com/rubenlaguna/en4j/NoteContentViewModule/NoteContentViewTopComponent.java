/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rubenlaguna.en4j.NoteContentViewModule;


import com.rubenlaguna.en4j.noteinterface.Note;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.openide.util.Exceptions;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.NotificationDisplayer;
import org.openide.util.Lookup;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xhtmlrenderer.simple.XHTMLPanel;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.rubenlaguna.en4j.NoteContentViewModule//NoteContentView//EN",
autostore = false)
public final class NoteContentViewTopComponent extends TopComponent implements LookupListener {

    private static NoteContentViewTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "NoteContentViewTopComponent";
    private Lookup.Result result = null;
    private XHTMLPanel panel = null;

    public NoteContentViewTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(NoteContentViewTopComponent.class, "CTL_NoteContentViewTopComponent"));
        setToolTipText(NbBundle.getMessage(NoteContentViewTopComponent.class, "HINT_NoteContentViewTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        panel = new XHTMLPanel();
        jScrollPane2.setViewportView(panel);

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized NoteContentViewTopComponent getDefault() {
        if (instance == null) {
            instance = new NoteContentViewTopComponent();
        }
        return instance;
    }

    public void resultChanged(LookupEvent ev) {
        try {
            Collection<? extends Note> notes = result.allInstances();
            if (!notes.isEmpty()) {
                Note n = notes.iterator().next();
                //jLabel1.setText(n.getContent());
                //jTextArea1.setText(n.getContent());


                // Create a builder factory
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setValidating(false);
                //factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
                factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

                // Create the builder and parse the file
                String content = n.getContent();
                Document doc = factory.newDocumentBuilder().parse(new InputSource(new StringReader(content)));

                //Document doc2 = factory.newDocumentBuilder().parse(getClass().getResourceAsStream("/com/rubenlaguna/en4j/NoteContentViewModule/xhtmlstricttemplate.xhtml"));

                //NodeList list = doc.getElementsByTagName("en-note");
                //Element element = (Element) list.item(0);
                //Node dup = doc2.importNode(element, true);

                //doc2.getElementsByTagName("body").item(0).appendChild(dup);

                panel.setDocument(doc);



            }
        } catch (SAXException e) {
            // A parsing error occurred; the xml input is not valid
            Logger.getLogger(getName()).log(Level.SEVERE, "Excepton", e);
        } catch (ParserConfigurationException e) {
            Logger.getLogger(getName()).log(Level.SEVERE, "Excepton", e);
        } catch (IOException e) {
            Logger.getLogger(getName()).log(Level.SEVERE, "Excepton", e);

        }

    }

    // Parses an XML file and returns a DOM document.
    // If validating is true, the contents is validated against the DTD
    // specified in the file.
    public static Document parseXmlFile(String filename, boolean validating) {
        try {
            // Create a builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(validating);

            // Create the builder and parse the file
            Document doc = factory.newDocumentBuilder().parse(new File(filename));
            return doc;
        } catch (SAXException e) {
            // A parsing error occurred; the xml input is not valid
            } catch (ParserConfigurationException e) {
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * Obtain the NoteContentViewTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized NoteContentViewTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(NoteContentViewTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof NoteContentViewTopComponent) {
            return (NoteContentViewTopComponent) win;
        }
        Logger.getLogger(NoteContentViewTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID +
                "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
        Lookup.Template tpl = new Lookup.Template(Note.class);
        result = Utilities.actionsGlobalContext().lookup(tpl);
        result.addLookupListener(this);
        resultChanged(null);
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        NoteContentViewTopComponent singleton = NoteContentViewTopComponent.getDefault();
        singleton.readPropertiesImpl(p);
        return singleton;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }
}
