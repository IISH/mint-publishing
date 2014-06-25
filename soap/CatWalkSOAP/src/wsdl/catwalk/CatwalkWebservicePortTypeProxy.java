package wsdl.catwalk;

public class CatwalkWebservicePortTypeProxy implements wsdl.catwalk.CatwalkWebservicePortType {
  private String _endpoint = null;
  private wsdl.catwalk.CatwalkWebservicePortType catwalkWebservicePortType = null;
  
  public CatwalkWebservicePortTypeProxy() {
    _initCatwalkWebservicePortTypeProxy();
  }
  
  public CatwalkWebservicePortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initCatwalkWebservicePortTypeProxy();
  }
  
  private void _initCatwalkWebservicePortTypeProxy() {
    try {
      catwalkWebservicePortType = (new wsdl.catwalk.CatwalkWebserviceLocator()).getCatwalkWebservicePort();
      if (catwalkWebservicePortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)catwalkWebservicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)catwalkWebservicePortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (catwalkWebservicePortType != null)
      ((javax.xml.rpc.Stub)catwalkWebservicePortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public wsdl.catwalk.CatwalkWebservicePortType getCatwalkWebservicePortType() {
    if (catwalkWebservicePortType == null)
      _initCatwalkWebservicePortTypeProxy();
    return catwalkWebservicePortType;
  }
  
  public wsdl.catwalk.Category[] getAllCategories(java.lang.String language) throws java.rmi.RemoteException{
    if (catwalkWebservicePortType == null)
      _initCatwalkWebservicePortTypeProxy();
    return catwalkWebservicePortType.getAllCategories(language);
  }
  
  public wsdl.catwalk.Category[] getCategories(int parentId, java.lang.String language) throws java.rmi.RemoteException{
    if (catwalkWebservicePortType == null)
      _initCatwalkWebservicePortTypeProxy();
    return catwalkWebservicePortType.getCategories(parentId, language);
  }
  
  public wsdl.catwalk.Category loadCategory(int id, java.lang.String language) throws java.rmi.RemoteException{
    if (catwalkWebservicePortType == null)
      _initCatwalkWebservicePortTypeProxy();
    return catwalkWebservicePortType.loadCategory(id, language);
  }
  
  public wsdl.catwalk.Picture[] getCategoryPictures(int categoryId) throws java.rmi.RemoteException{
    if (catwalkWebservicePortType == null)
      _initCatwalkWebservicePortTypeProxy();
    return catwalkWebservicePortType.getCategoryPictures(categoryId);
  }
  
  public wsdl.catwalk.Picture loadPicture(int pictureId, int categoryId) throws java.rmi.RemoteException{
    if (catwalkWebservicePortType == null)
      _initCatwalkWebservicePortTypeProxy();
    return catwalkWebservicePortType.loadPicture(pictureId, categoryId);
  }
  
  
}