package at.jsug.action;

import at.jsug.jackrabbit.RepositoryContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

/**
 * Created by daja on 04.05.14.
 */
public class RepositoryActionBean implements ActionBean {
    private RepositoryContext context;

    @Override
    public void setContext(ActionBeanContext actionBeanContext) {
        this.context = (RepositoryContext) actionBeanContext;
    }

    @Override
    public RepositoryContext getContext() {
        return this.context;
    }
}
