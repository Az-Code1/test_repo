//<dependency>
//        <groupId>org.apache.pdfbox</groupId>
//        <artifactId>pdfbox</artifactId>
//        <version>2.0.24</version> <!-- Use the latest version -->
//        </dependency>
//        <dependency>
//        <groupId>com.sun.mail</groupId>
//        <artifactId>javax.mail</artifactId>
//        <version>1.6.2</version> <!-- Use the latest version -->
//        </dependency>
//
//
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//
//import javax.mail.*;
//import javax.mail.internet.*;
//import java.io.*;
//import java.util.Properties;
//
//public class SendStatementToEmail {
//
//    public static void main(String[] args) {
//        String documentPath = "path/to/document.txt";
//        String pdfPath = "path/to/document.pdf";
//        String emailTo = "recipient@example.com";
//
//        try {
//            // Convert document to PDF
//            convertToPdf(documentPath, pdfPath);
//            // Send email with PDF attachment
//            sendEmailWithAttachment(emailTo, pdfPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void convertToPdf(String textFilePath, String pdfFilePath) throws IOException {
//        PDDocument document = new PDDocument();
//        PDPage page = new PDPage();
//        document.addPage(page);
//
//        try (PDPageContentStream contentStream = new PDPageContentStream(document, page);
//             BufferedReader br = new BufferedReader(new FileReader(textFilePath))) {
//
//            contentStream.setFont(PDType1Font.HELVETICA, 12);
//            contentStream.beginText();
//            contentStream.newLineAtOffset(25, 700);
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                contentStream.showText(line);
//                contentStream.newLineAtOffset(0, -15); // Move down for the next line
//            }
//
//            contentStream.endText();
//        }
//
//        document.save(pdfFilePath);
//        document.close();
//    }
//
//    private static void sendEmailWithAttachment(String to, String filePath) {
//        String from = "your_email@example.com";
//        String host = "smtp.gmail.com"; // Use your SMTP server
//
//        // Set properties
//        Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("mail.smtp.port", "587"); // or your SMTP port
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//
//        // Get the Session object
//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("your_email@example.com", "your_password");
//            }
//        });
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(from));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject("Here is your PDF document");
//
//            // Create the message part
//            BodyPart messageBodyPart = new MimeBodyPart();
//            messageBodyPart.setText("Please find the attached PDF document.");
//
//            // Create a multipart message
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart);
//
//            // Part two is the attachment
//            messageBodyPart = new MimeBodyPart();
//            DataSource source = new FileDataSource(filePath);
//            messageBodyPart.setDataHandler(new DataHandler(source));
//            messageBodyPart.setFileName(filePath);
//            multipart.addBodyPart(messageBodyPart);
//
//            // Send the complete message parts
//            message.setContent(multipart);
//
//            // Send message
//            Transport.send(message);
//            System.out.println("Email Sent Successfully!");
//
//        } catch (MessagingException mex) {
//            mex.printStackTrace();
//        }
//    }
//}
