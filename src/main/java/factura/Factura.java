package factura;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRXmlUtils;

public class Factura {
    private String error;
    private String rootPath;
    private HashMap<String, String> tipuriDeclaratii;

    public Factura(String path) {
        (this.tipuriDeclaratii = new HashMap<String, String>()).put("Invoice", "Factura");
        this.rootPath = path;
    }
    
    public String generarePDF(File xmlFile) throws ParserConfigurationException, SAXException, IOException, JRException {
        this.error = null;
        try {
            final Map<String, Object> parameters = new HashMap<String, Object>();
            final Document document = JRXmlUtils.parse(JRLoader.getLocationInputStream(xmlFile.getAbsolutePath()));
            parameters.put("XML_DATA_DOCUMENT", document);
            parameters.put("SUBREPORT_DIR", this.rootPath + "/jasper//");
            final String numePdf = this.rootPath + "/output.pdf";
            final JasperPrint jasperPrint = JasperFillManager.fillReport(this.rootPath + "/jasper//factura_complet2.jasper", parameters);
            JasperExportManager.exportReportToPdfFile(jasperPrint, numePdf);
            return numePdf;
        }
        catch (Throwable e) {
            this.scrieErr( e);
            return null;
        }
    }


    public String returnString(final String numeXML, final String numeATT) throws FileNotFoundException, ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        return "";
    }

    public void scrieErr(Throwable e) {
        e.printStackTrace();
    }

    public String getError() {
        return this.error;
    }
}
