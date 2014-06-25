package gr.ntua.ivml.mint.fashion.wsdl.test;

import gr.ntua.ivml.fashion.catwalk.schema.CatWalk;
import gr.ntua.ivml.fashion.catwalk.schema.CatWalk.Record;
import gr.ntua.ivml.fashion.catwalk.schema.CategoryType;
import gr.ntua.ivml.fashion.catwalk.schema.ObjectFactory;
import gr.ntua.ivml.fashion.catwalk.schema.PictureType;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.axis.AxisFault;
import org.apache.axis.client.Stub;
import org.jsoup.Jsoup;

import wsdl.catwalk.Category;
import wsdl.catwalk.CatwalkWebserviceBindingStub;
import wsdl.catwalk.CatwalkWebservicePortTypeProxy;
import wsdl.catwalk.Picture;

public class TestServices {
	private static ObjectFactory fact = new ObjectFactory();
	
	private static JAXBContext context;
	private static Marshaller m;
	
	private static CatwalkWebservicePortTypeProxy prox;
	
	private static FTPClient client;
	
	private static String imagePath = "/data/vardata/tmp/cpard/22-05-2014/images/";
	private static String metadataPath = "/data/vardata/tmp/cpard/22-05-2014/metadata/";
	private static String aggregatedMetadataPath = "/data/vardata/tmp/cpard/22-05-2014/aggregated/";
//	private static String imagePath = "C:\\data\\fashion\\test\\images\\";
//	private static String metadataPath = "C:\\data\\fashion\\test\\metadata\\";
//	private static String aggregatedMetadataPath = "C:\\data\\fashion\\test\\aggregated\\";
	private static String finalURL = "http://repos.europeanafashion.eu/catwalk/";
	public TestServices() throws JAXBException, IllegalStateException, IOException, FTPIllegalReplyException, FTPException{

	}
	
	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FTPAbortedException 
	 * @throws FTPDataTransferException 
	 * @throws FTPException 
	 * @throws FTPIllegalReplyException 
	 * @throws IllegalStateException 
	 */
	
	public static void main(String[] args) throws JAXBException, MalformedURLException, IOException, IllegalStateException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException {
//		CatwalkWebserviceBindingStub stub = new CatwalkWebserviceBindingStub();
//		stub._setProperty(stub.USERNAME_PROPERTY, "Henk_Soap");
//		stub._setProperty(stub.PASSWORD_PROPERTY, "hZ2AtIdWoG");
//		stub._setProperty(stub.ENDPOINT_ADDRESS_PROPERTY, "http://soap.catwalkpictures.com/index.php?wsdl");
//		stub.getAllCategories("en");
		
//		client = new FTPClient();
//		client.connect("repos.europeanafashion.eu");
//		client.login("catwalk", "XdK6R5");
		
		prox = new CatwalkWebservicePortTypeProxy();
		Stub stub = (Stub) prox.getCatwalkWebservicePortType();
		//stub._setProperty(Stub.USERNAME_PROPERTY, "Henk_Soap");
		//stub._setProperty(Stub.PASSWORD_PROPERTY, "hZ2AtIdWoG");
		//((Stub)prox.getCatwalkWebservicePortType())._setProperty(Stub.USERNAME_PROPERTY, "");
		//System.out.println(prox.getAllCategories("en").length);
		stub._setProperty(Stub.USERNAME_PROPERTY, "EuropeanaSoap");
		stub._setProperty(Stub.PASSWORD_PROPERTY, "uZ4WwV66xI");
		Category[] cats = prox.getAllCategories("en");
		SimpleDateFormat formatD = new SimpleDateFormat("yyyy-MM-dd");
		
		int counter = 0;
		//System.out.println(prox.getCategories(9270, "en")[0].getName());
		//System.out.println(prox.getCategories(9270, "en").length);
		//System.out.println(prox.getCategories(9270, "en")[0].getId());
		for(int i = 0; i < cats.length; i++){
			Category cat = cats[i];
			CategoryType categoryType = fact.createCategoryType();
			categoryType.setCategoryId("" + cat.getId());
			categoryType.setDescription(Jsoup.parse(cat.getDescription()).text());
			categoryType.setName(cat.getName());
			Picture[] pics = prox.getCategoryPictures(cat.getId());
			
			if(pics.length != 0){
				Category location = getParentCategory(cats, cat.getParentId());
				Category type = getParentCategory(cats, location.getParentId());
				Category period = getParentCategory(cats, type.getParentId());
				categoryType.setLocation(location.getName());
				categoryType.setType(type.getName());
				categoryType.setPeriod(period.getName());
				
				for(int j = 0; j < pics.length; j ++){
					Picture pic = pics[j];
					PictureType picType = fact.createPictureType();
					picType.setUrl(finalURL + pic.getName() + ".jpg");
					Date date = new Date();
					date.setTime( (long) pic.getTimestamp() * 1000);
					
					picType.setTimestamp(formatD.format(date));
					picType.setName(pic.getName());
					String[] nameValues = pic.getName().split("_");
					picType.setDesigner(cat.getName());
					picType.setPeriod(nameValues[nameValues.length-2]);
					if(pic.getTags() != null){
						Object[] tags = pic.getTags();
						for(int k = 0; k < tags.length;k++){
							String tag = (String)tags[k];
							picType.getTag().add(tag);
						}
					}
					Record rec = fact.createCatWalkRecord();
					rec.setCategory(categoryType);
					rec.getPicture().add(picType);
					saveRecord(rec, counter);
					String photoPath = imagePath + pic.getName() + ".jpg";
					org.apache.commons.io.FileUtils.copyURLToFile(new URL(pic.getUrl()), new File(photoPath));
					//System.out.println(photoPath);

					//client.upload(new java.io.File(photoPath));
					counter++;
				}
				//serialize the event
				serializeCategoryasEvent(cat,cats, counter);
			}
			
			/*for(int j = 0; j < pics.length; j ++){
				Picture pic = pics[j];
				PictureType picType = fact.createPictureType();
				picType.setUrl(finalURL + pic.getName() + ".jpg");
				Date date = new Date();
				date.setTime( (long) pic.getTimestamp() * 1000);
				
				picType.setTimestamp(formatD.format(date));
				picType.setName(pic.getName());
				String[] nameValues = pic.getName().split("_");
				picType.setDesigner(cat.getName());
				picType.setPeriod(nameValues[nameValues.length-2]);
				if(pic.getTags() != null){
					Object[] tags = pic.getTags();
					for(int k = 0; k < tags.length;k++){
						String tag = (String)tags[k];
						picType.getTag().add(tag);
					}
				}
				Record rec = fact.createCatWalkRecord();
				rec.setCategory(categoryType);
				rec.setPicture(picType);
				saveRecord(rec, counter);
				String photoPath = imagePath + pic.getName() + ".jpg";
				org.apache.commons.io.FileUtils.copyURLToFile(new URL(pic.getUrl()), new File(photoPath));
				//System.out.println(photoPath);

				client.upload(new java.io.File(photoPath));
				counter++;
			}*/
		}

	}
	
	
	public static void serializeCategoryasEvent(Category cat, Category[] cats, int count) throws RemoteException, JAXBException{
		CategoryType categoryType = fact.createCategoryType();
		
		categoryType.setCategoryId("" + cat.getId());
		categoryType.setDescription(Jsoup.parse(cat.getDescription()).text());
		categoryType.setName(cat.getName());
		Picture[] pics = prox.getCategoryPictures(cat.getId());
		SimpleDateFormat formatD = new SimpleDateFormat("yyyy-MM-dd");
		
		Category location = getParentCategory(cats, cat.getParentId());
		Category type = getParentCategory(cats, location.getParentId());
		Category period = getParentCategory(cats, type.getParentId());
		
		categoryType.setLocation(location.getName());
		categoryType.setType(type.getName());
		categoryType.setPeriod(period.getName());
		
		Record rec = fact.createCatWalkRecord();
		rec.setCategory(categoryType);
		for(int j = 0; j < pics.length; j ++){
			Picture pic = pics[j];
			PictureType picType = fact.createPictureType();
			picType.setUrl(finalURL + pic.getName() + ".jpg");
			Date date = new Date();
			date.setTime( (long) pic.getTimestamp() * 1000);
			
			picType.setTimestamp(formatD.format(date));
			picType.setName(pic.getName());
			String[] nameValues = pic.getName().split("_");
			picType.setDesigner(cat.getName());
			picType.setPeriod(nameValues[nameValues.length-2]);
			if(pic.getTags() != null){
				Object[] tags = pic.getTags();
				for(int k = 0; k < tags.length;k++){
					String tag = (String)tags[k];
					picType.getTag().add(tag);
				}
			}
			rec.getPicture().add(picType);
		}
		saveRecord(rec, count, aggregatedMetadataPath);
	}
	
	public static Category getParentCategory(Category[] all, int id){
		Category res = null;
		for(Category cat: all){
			if(cat.getId() == id){
				res = cat;
			}
		}
		return res;
	}
	
	public static void saveRecord(Record rec, int count, String path) throws JAXBException{
		context = JAXBContext.newInstance( "gr.ntua.ivml.fashion.catwalk.schema" );
		m = context.createMarshaller();
		
		CatWalk cat = fact.createCatWalk();
		cat.setRecord(rec);
		
		m.marshal(cat, new File(path + count + ".xml"));
	}
	
	public static void saveRecord(Record rec, int count) throws JAXBException{
		context = JAXBContext.newInstance( "gr.ntua.ivml.fashion.catwalk.schema" );
		m = context.createMarshaller();
		
		CatWalk cat = fact.createCatWalk();
		cat.setRecord(rec);
		
		m.marshal(cat, new File(metadataPath + count + ".xml"));
	}
}
