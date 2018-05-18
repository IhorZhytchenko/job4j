package jdbc;


import jdbc.entity.Entries;
import jdbc.entity.Entry;
import org.xml.sax.InputSource;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * class Parser.
 *
 * @author Ihor Zhytchenko (igor.zhytchenko@gmail.com)
 * @version $1$
 * @since 17.05.2018
 */
public class Parser {
    private static final String PATH_XSLT = "juniorChapter_003/src/main/java/jdbc/xslt.xsl";

    public void objectToXml(String path, Entries object) {

        File file = new File(path);
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(object, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    public Entries XmlToObject(String path)  {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        Entries result = new Entries();
        XMLStreamReader reader = null;
        try(FileInputStream in = new FileInputStream(path)) {
            reader = factory.createXMLStreamReader(in);
            String name;
            int state;

            List<Entry> entries = new ArrayList<>();
            while (reader.hasNext()) {
                state = reader.next();

                if (state == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals("entry")) {
                        int field = Integer.parseInt(reader.getAttributeValue(0));
                        Entry entry = new Entry();
                        entry.setField(field);
                        entries.add(entry);
                    }
                }
            }
            result.setEntries(entries);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void transformFoXslt(String pathIn, String pathOut)  {
        try(InputStream in = new FileInputStream(pathIn)) {
            SAXSource saxSource = new SAXSource(new InputSource(in));
            StreamResult result = new StreamResult(new File(pathOut));
            TransformerFactory factory = SAXTransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(PATH_XSLT));
            transformer.transform(saxSource, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }
}
