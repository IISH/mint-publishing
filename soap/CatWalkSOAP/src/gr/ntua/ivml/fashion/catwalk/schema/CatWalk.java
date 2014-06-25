//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.3-hudson-jaxb-ri-2.2-70- 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.22 at 02:54:14 �� EEST 
//


package gr.ntua.ivml.fashion.catwalk.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="record">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="category" type="{}CategoryType"/>
 *                   &lt;element name="picture" type="{}PictureType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "record"
})
@XmlRootElement(name = "CatWalk")
public class CatWalk {

    @XmlElement(required = true)
    protected CatWalk.Record record;

    /**
     * Gets the value of the record property.
     * 
     * @return
     *     possible object is
     *     {@link CatWalk.Record }
     *     
     */
    public CatWalk.Record getRecord() {
        return record;
    }

    /**
     * Sets the value of the record property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatWalk.Record }
     *     
     */
    public void setRecord(CatWalk.Record value) {
        this.record = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="category" type="{}CategoryType"/>
     *         &lt;element name="picture" type="{}PictureType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "category",
        "picture"
    })
    public static class Record {

        @XmlElement(required = true)
        protected CategoryType category;
        @XmlElement(required = true)
        protected List<PictureType> picture;

        /**
         * Gets the value of the category property.
         * 
         * @return
         *     possible object is
         *     {@link CategoryType }
         *     
         */
        public CategoryType getCategory() {
            return category;
        }

        /**
         * Sets the value of the category property.
         * 
         * @param value
         *     allowed object is
         *     {@link CategoryType }
         *     
         */
        public void setCategory(CategoryType value) {
            this.category = value;
        }

        /**
         * Gets the value of the picture property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the picture property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPicture().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PictureType }
         * 
         * 
         */
        public List<PictureType> getPicture() {
            if (picture == null) {
                picture = new ArrayList<PictureType>();
            }
            return this.picture;
        }

    }

}
