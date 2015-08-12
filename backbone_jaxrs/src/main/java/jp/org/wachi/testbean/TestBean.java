package jp.org.wachi.testbean;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="testbean")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name="TestBean")
@Table(name="TEST_BEAN")
public class TestBean {

	@XmlElement
	@Id
	@SequenceGenerator(name="TESTBEAN_SEQ", allocationSize=25)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TESTBEAN_SEQ")
	private long id;

	@XmlElement
	@Basic
	private String key;

	@XmlElement
	@Basic
	private String value;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
