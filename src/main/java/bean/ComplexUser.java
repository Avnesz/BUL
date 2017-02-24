package bean;

public class ComplexUser extends SimplyUser {
    private boolean verified;

    /**
     * @return the verified
     */
    public boolean isVerified() {
        return verified;
    }

    /**
     * @param verified
     *            the verified to set
     */
    public void setVerified(final boolean verified) {
        this.verified = verified;
    }
}
