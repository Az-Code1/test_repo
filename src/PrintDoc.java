//
//import javax.print.*;
//import javax.print.attribute.*;
//import javax.print.attribute.standard.*;
//import javax.swing.*;
//import java.io.*;
//
//public class PrintDoc {
//
//    public PrintDoc() {
//        // Create a file chooser
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Select Document to Print");
//
//        // Open the file chooser and wait for user selection
//        int userSelection = fileChooser.showOpenDialog(null);
//
//        // Check if the user selected a file
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File file = fileChooser.getSelectedFile();
//            printFile(file);
//        } else {
//            System.out.println("No file selected.");
//        }
//    }
//
//    private void printFile(File file) {
//        // Create a print job
//        try {
//            DocFlavor flavor = DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8;
//            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
//            printRequestAttributeSet.add(new Copies(1)); // Number of copies
//            printRequestAttributeSet.add(Sides.ONE_SIDED); // One-sided printing
//
//            // Get a print service
//            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(flavor, printRequestAttributeSet);
//            if (printServices.length > 0) {
//                PrintService printService = printServices[0]; // Select the first available printer
//                DocPrintJob printJob = printService.createPrintJob();
//
//                // Create a Doc object
//                try (InputStream inputStream = new FileInputStream(file)) {
//                    Doc doc = new SimpleDoc(inputStream, flavor, null);
//                    printJob.print(doc, printRequestAttributeSet);
//                    System.out.println("Document sent to printer.");
//                } catch (IOException e) {
//                    System.err.println("Error reading document: " + e.getMessage());
//                }
//            } else {
//                System.err.println("No suitable print services found.");
//            }
//        } catch (PrintException e) {
//            System.err.println("Print error: " + e.getMessage());
//        }
//    }
//}

import java.awt.*;
import java.awt.print.*;

public class PrintDoc implements Printable {

    private final String content;

    public PrintDoc(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        // Sample content for the document
        String content = """
                Hello, this is a sample document for printing.
                This demonstrates how to prepare a document in Java.
                Enjoy printing!""";

        // Create an instance of the print document
        PrintDoc document = new PrintDoc(content);

        // Create a PrinterJob
        PrinterJob printerJob = PrinterJob.getPrinterJob();

        // Set the Printable to our document
        printerJob.setPrintable(document);

        // Show print dialog
        if (printerJob.printDialog()) {
            try {
                // Print the document
                printerJob.print();
            } catch (PrinterException e) {
                System.err.println("Print error: " + e.getMessage());
            }
        } else {
            System.out.println("Print job was canceled.");
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        // Check if the requested page is available
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        // Set the printing area
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        // Set font and draw the content
        g.setFont(new Font("Serif", Font.PLAIN, 12));
        g.drawString(content, 100, 100);

        return PAGE_EXISTS;
    }
}
