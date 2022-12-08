package com.macrochina.net.ldap;

import javax.naming.Name;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(base="ou=fis", objectClasses={"top"})
public class LDAPUser
{

    @Id
    private Name id;

    @Attribute(name="userName")
    private String username;

    @Attribute(name="userPassword")
    private String password;

    @Attribute(name="sshPublicKey")
    private String sshPublicKey;

    public void setUid(String uid)
    {
    }

    public Name getId()
    {
        return this.id;
    }

    public String getUsername()
    {
        return this.username;
    }
    public String getPassword() {
        return this.password;
    }

    public String getSshPublicKey()
    {
        return this.sshPublicKey;
    }

    public void setId(Name id)
    {
        this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setSshPublicKey(String sshPublicKey) { this.sshPublicKey = sshPublicKey; }
    public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof LDAPUser)) return false; LDAPUser other = (LDAPUser)o; if (!other.canEqual(this)) return false; Object this$id = getId(); Object other$id = other.getId(); if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false; Object this$username = getUsername(); Object other$username = other.getUsername(); if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false; Object this$password = getPassword(); Object other$password = other.getPassword(); if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false; Object this$sshPublicKey = getSshPublicKey(); Object other$sshPublicKey = other.getSshPublicKey(); return this$sshPublicKey == null ? other$sshPublicKey == null : this$sshPublicKey.equals(other$sshPublicKey); }
    protected boolean canEqual(Object other) { return other instanceof LDAPUser; }
    public int hashCode() { int PRIME = 59; int result = 1; Object $id = getId(); result = result * 59 + ($id == null ? 43 : $id.hashCode()); Object $username = getUsername(); result = result * 59 + ($username == null ? 43 : $username.hashCode()); Object $password = getPassword(); result = result * 59 + ($password == null ? 43 : $password.hashCode()); Object $sshPublicKey = getSshPublicKey(); return result * 59 + ($sshPublicKey == null ? 43 : $sshPublicKey.hashCode()); }
    public String toString() { return "LDAPUser(id=" + getId() + ", username=" + getUsername() + ", password=" + getPassword() + ", sshPublicKey=" + getSshPublicKey() + ")";
    }
}