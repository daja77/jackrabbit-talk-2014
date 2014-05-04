package at.jsug.action;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/Home.action")
public class HomeActionBean extends RepositoryActionBean {

    public Resolution view() {
        return new ForwardResolution("/WEB-INF/jsp/home.jsp");
    }

}
