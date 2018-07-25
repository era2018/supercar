package com.era.supercar;

class Credentials
{
    private String email;
    private String pass;

    public Credentials(String email, String pass)
    {
        this.email = email;
        this.pass = pass;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPass()
    {
        return this.pass;
    }
}