/**
 * CatwalkWebservicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package wsdl.catwalk;

public interface CatwalkWebservicePortType extends java.rmi.Remote {

    /**
     * Get all the available categories. The result is an array of
     * associative arrays
     */
    public wsdl.catwalk.Category[] getAllCategories(java.lang.String language) throws java.rmi.RemoteException;

    /**
     * Get the children categories of the given parent id. The result
     * is an array of associative arrays
     */
    public wsdl.catwalk.Category[] getCategories(int parentId, java.lang.String language) throws java.rmi.RemoteException;

    /**
     * Load a simple category. The result is an associative array
     */
    public wsdl.catwalk.Category loadCategory(int id, java.lang.String language) throws java.rmi.RemoteException;

    /**
     * Get all the available pictures from a category. The result
     * is an array of associative arrays
     */
    public wsdl.catwalk.Picture[] getCategoryPictures(int categoryId) throws java.rmi.RemoteException;

    /**
     * Load a simple picture. The result is in an associative array
     */
    public wsdl.catwalk.Picture loadPicture(int pictureId, int categoryId) throws java.rmi.RemoteException;
}
