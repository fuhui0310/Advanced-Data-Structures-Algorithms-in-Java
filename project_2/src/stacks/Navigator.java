package stacks;

/**
 *Class which provides the navigation feature of class BrowserNavigation.
 * Created by Fu on 24/1/2018.
 */
public class Navigator {
    private String currentLink;
    private StackList<String> backLinks;
    private StackList<String> forwardLinks;

    /**
     * A constructor that initializes the class attributes.
     */
    public Navigator() {
        currentLink = null;
        backLinks = new StackList<String>();
        forwardLinks = new StackList<String>();
        backLinks.setName("Back");
        forwardLinks.setName("Forward");
    }

    /**
     * Takes a string for the current requested link.
     * @param currentLink  the input data.
     */
    public void setCurrentLink(String currentLink) {
        if(this.currentLink != null){
            backLinks.push(this.currentLink);
        }
        this.currentLink = currentLink;
    }

    /**
     * Update the currentLink to the link at the top of the backLinks stack or forwardLinks stack respectively.
     */
    public void goBack() {
        if(backLinks.peek() != null) {
            forwardLinks.push(currentLink);
            currentLink = backLinks.pop();
        }
    }

    /**
     * Update the currentLink to the link at the top of the backLinks stack or forwardLinks stack respectively.
     */
    public void goForward() {
        if(forwardLinks.peek() != null) {
            backLinks.push(currentLink);
            currentLink = forwardLinks.pop();
        }
    }

    /**
     *Accessor method return the currentLink
     * @return currentLink
     */
    public String getCurrentLink() {
        return currentLink;
    }

    /**
     * Accessor method return the backLinks
     * @return backLinks
     */
    public StackList<String> getBackLinks() {
        return backLinks;
    }

    /**
     * Accessor method return the forwardLinks
     * @return forwardLinks
     */
    public StackList<String> getForwardLinks() {
        return forwardLinks;
    }
}
