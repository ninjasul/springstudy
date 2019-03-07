
package com.ninjasul.tobyspring31.learningtest.jdk.jaxb;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sqlType", propOrder = { "value" })
@Getter @Setter
public class SqlType {

    @XmlValue
    protected String value;

    @XmlAttribute(required = true)
    protected String key;

}
