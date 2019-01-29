package com.example.admin.widgetcurrency;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class ValCurs
{
    @Attribute
    private String Date;

    @Attribute
    private String name;

    @ElementList(entry = "Valute", required = true, inline = true)
    private List<Valute> Valute;

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    //public Valute[] getValute ()
    public List<Valute> getValute ()
    {
        return Valute;
    }

    //public void setValute (Valute[] Valute)
    public void setValute (List<Valute> Valute)
    {
        this.Valute = Valute;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Date = "+Date+", name = "+name+", Valute = "+Valute+"]";
    }
}