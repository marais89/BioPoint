package org.bio.web;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

public class SimpleViewer extends JFrame {
    
    // Contenu HTML
    private JEditorPane editorPane = null;
   
    // Construit un Viewer avec l'url et avec titre l'url
    public SimpleViewer(String url) {
      this(url, url);
    }
   
    // Construit un Viewer avec l'url et avec le titre titre
    public SimpleViewer(String url, String title) {
      super(title);
        
      // Par defaut, on quitte le programme quand on ferme la JFrame
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          exit();
        }
      });
   
      // Construit la page d'url url
      try {
        editorPane = new JEditorPane(url);
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }
      editorPane.setEditable(false);
   
      // Sensibilité aux clics sur les liens
      editorPane.addHyperlinkListener(new HyperlinkListener() {
        public void hyperlinkUpdate(HyperlinkEvent e) {
          if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
            // Nouvelle page
            JEditorPane pane = (JEditorPane) e.getSource();
            if (e instanceof HTMLFrameHyperlinkEvent) {
              HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
              HTMLDocument doc = (HTMLDocument)pane.getDocument();
              doc.processHTMLFrameHyperlinkEvent(evt);
            } 
            else {
              try {
                pane.setPage(e.getURL());
              } catch (Throwable t) {
                t.printStackTrace();
              }
            }
          }
        }
      });
   
      // Affichage de la fenêtre
      getContentPane().add("Center", new JScrollPane(editorPane));
      setSize(1024, 768);
      setVisible(true);
    }
   
    private static void exit() {
      System.exit(0);
    }
   
    public static void main(String[] args) {
      new SimpleViewer("http://www.anadoncamille.com/");
    }
   
  }


