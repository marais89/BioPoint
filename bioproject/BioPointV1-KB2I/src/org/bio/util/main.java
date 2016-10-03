package org.bio.util;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import java.io.File;
import java.io.IOException;
import org.codehaus.groovy.control.CompilationFailedException;

public class main {

    public static void main(String[] args) {

	GroovyShell shell = new GroovyShell();
	Script script;
	try {
	    // Chargement du script groovy
	    script = shell.parse(new File("WebContent/resources/script/test.gy"));
	  
	    Binding binding = new Binding();
	    // Création d'un paramètre
	    binding.setVariable("a", 1);
	    binding.setVariable("b", 2);

	    script.setBinding(binding);

	    // Exécution du script
	    Object retour = script.run();
	    // Affichage de la valeur de retour du script
	    System.out.println(retour);

	} catch (CompilationFailedException e) {

	    e.printStackTrace();
	} catch (IOException e) {

	    e.printStackTrace();
	}

    }
}