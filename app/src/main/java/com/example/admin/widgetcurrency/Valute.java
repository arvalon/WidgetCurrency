package com.example.admin.widgetcurrency;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Valute
{
    @Element(required = false)
    private String Name;

    @Element(required = false)
    private String Value;

    @Attribute(required = false)
    private String ID;

    @Element(required = false)
    private String Nominal;

    @Element(required = false)
    private String CharCode;

    @Element(required = false)
    private String NumCode;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getNominal ()
    {
        return Nominal;
    }

    public void setNominal (String Nominal)
    {
        this.Nominal = Nominal;
    }

    public String getCharCode ()
    {
        return CharCode;
    }

    public void setCharCode (String CharCode)
    {
        this.CharCode = CharCode;
    }

    public String getNumCode ()
    {
        return NumCode;
    }

    public void setNumCode (String NumCode)
    {
        this.NumCode = NumCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", Value = "+Value+", ID = "+ID+", Nominal = "+Nominal+", CharCode = "+CharCode+", NumCode = "+NumCode+"]";
    }
}