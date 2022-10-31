package factura;

import java.net.URISyntaxException;
import java.io.File;
import java.io.FileNotFoundException;


public class App 
{

    private Factura factura;
    private String rootPath;
    private File xmlFile;
    
    public App( String[] args) throws FileNotFoundException {
        final String numeXml;

        try {
            this.findPath();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
            System.err.println("error detecting application path");
        }
        System.out.println("root path: "+ this.rootPath);

        if (args.length < 1) {
            System.err.println("no file parameter given, using default");
            numeXml = this.rootPath + "/samples/factura_big.xml";
        }
        else {
            numeXml = args[0];
        }

        this.xmlFile = new File(numeXml.trim());

        if (!this.xmlFile.exists()) {
            throw new FileNotFoundException("specified xml file not found: "+ numeXml.trim());
        }
    }

    public void work() {
        this.factura = new Factura(this.rootPath);
    
        try {
            final String numePdf = factura.generarePDF(this.xmlFile);
            System.out.println(numePdf);
        }
        catch (Exception ex) {}

    }

    public static void main( String[] args )
    {
        final App app; 
        try {
            app = new App(args);
        } catch (FileNotFoundException e) {
            System.err.println("Nu am gasit fisierul! " + e.getMessage());
            return;
        }
        app.work();
    }

    // guess the root dir of the application
    private void findPath() throws URISyntaxException {
        String jarpath = App.class
            .getProtectionDomain()
            .getCodeSource()
            .getLocation()
            .toURI()
            .getPath();
        this.rootPath = (new File(jarpath)).getParentFile().getPath();
        return;
    }



}
