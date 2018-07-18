import beans.models.Event;
import beans.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class ClassToXml {

    private static String className;
    private static final String XSD_EXTENSION = ".xsd";

    public static void main(String[] args) throws JAXBException, IOException {
        JAXBContext jaxbContextUser = JAXBContext.newInstance(User.class);
        JAXBContext jaxbContextEvent = JAXBContext.newInstance(Event.class);

        SchemaOutputResolver sor = new MySchemaOutputResolver();
        className = User.class.getSimpleName() + XSD_EXTENSION;
        jaxbContextUser.generateSchema(sor);

        className = Event.class.getSimpleName() + XSD_EXTENSION;
        jaxbContextEvent.generateSchema(sor);
    }

    private static class MySchemaOutputResolver extends SchemaOutputResolver {

        public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
            File file = new File(className);
            StreamResult result = new StreamResult(file);
            result.setSystemId(file.toURI().toURL().toString());
            return result;
        }

    }
}


