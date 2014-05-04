package at.jsug.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

import javax.jcr.Node;
import javax.jcr.Session;
import java.io.InputStream;

@UrlBinding("/DisplayImage.action")
public class DisplayImageActionBean extends RepositoryActionBean {

    @DefaultHandler
    public Resolution view() {
        String id = getContext().getRequest().getParameter("id");
        Session session = getSession();
        try {
            Node picture = session.getNode("/Albums/Default/" + id);
            Node content = picture.getNode("jcr:content");
            InputStream in = content.getProperty("jcr:data").getBinary().getStream();
            String mimeType = content.getProperty("jcr:mimeType").getString();

            releaseSession();
            return new StreamingResolution(mimeType, in).setFilename(id+".jpg");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Session getSession() {
      return  getContext().getJCRSession();
    }

    public void releaseSession() {
        getContext().releaseJCRSession();
    }
}
