package at.jsug.jackrabbit;

import net.sourceforge.stripes.action.ActionBeanContext;
import org.apache.jackrabbit.core.TransientRepository;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import java.util.Properties;

/**
 * Created by daja on 01.05.14.
 */
public class RepositoryContext extends ActionBeanContext {

    private Repository repository;
    private Session session;

    public Session getJCRSession() {

        if(session == null) {
            try {
                Properties repositoryProperties = new Properties();
                repositoryProperties.load(this.getClass().getClassLoader().getResourceAsStream("repository.properties"));

                String repositoryConfig = this.getClass().getClassLoader().getResource("repository.xml").getFile();
                String repositoryPath = repositoryProperties.getProperty("rep.home");

                repository = new TransientRepository(repositoryConfig, repositoryPath);
                session = repository.login(new SimpleCredentials("username", "password".toCharArray()));
                initRepository();
            }
            catch (Exception e) {
                e.printStackTrace();
                // TODO: logging
            }

        }
        return session;
    }

    public void releaseJCRSession() {
        session.logout();
    }

    private void initRepository() {
        Node defaultAlbum;
        try {
            if(!session.nodeExists("/Albums/Default")) {
                Node albums = session.getRootNode().addNode("Albums");
                albums.addNode("Default");
                session.save();
            }
        }
        catch (Exception e) {
            // no connection to repository
        }
    }
}
