package com.macrochina.net.ldap;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.naming.Name;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses={"group", "top"})
public class Group
{

    @Id
    @JsonIgnore
    private Name id;

    @DnAttribute(value="CN", index=0)
    @Attribute(name="sAMAccountName")
    private String accountName;
    private List<String> members;
    private String name;
    private String groupType;

    @Attribute(name="distinguishedName")
    private String dn;

    @Attribute(name="cn")
    private String commonName;

    public Name getId()
    {
        return this.id;
    }

    public String getAccountName() {
        return this.accountName;
    }
    public List<String> getMembers() { return this.members; }
    public String getName() {
        return this.name;
    }
    public String getGroupType() { return this.groupType; }

    public String getDn() {
        return this.dn;
    }
    public String getCommonName() {
        return this.commonName;
    }

    public void setId(Name id)
    {
        this.id = id; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    public void setMembers(List<String> members) { this.members = members; }
    public void setName(String name) { this.name = name; }
    public void setGroupType(String groupType) { this.groupType = groupType; }
    public void setDn(String dn) { this.dn = dn; }
    public void setCommonName(String commonName) { this.commonName = commonName; }
    public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof Group)) return false; Group other = (Group)o; if (!other.canEqual(this)) return false; Object this$id = getId(); Object other$id = other.getId(); if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false; Object this$accountName = getAccountName(); Object other$accountName = other.getAccountName(); if (this$accountName == null ? other$accountName != null : !this$accountName.equals(other$accountName)) return false; Object this$members = getMembers(); Object other$members = other.getMembers(); if (this$members == null ? other$members != null : !this$members.equals(other$members)) return false; Object this$name = getName(); Object other$name = other.getName(); if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false; Object this$groupType = getGroupType(); Object other$groupType = other.getGroupType(); if (this$groupType == null ? other$groupType != null : !this$groupType.equals(other$groupType)) return false; Object this$dn = getDn(); Object other$dn = other.getDn(); if (this$dn == null ? other$dn != null : !this$dn.equals(other$dn)) return false; Object this$commonName = getCommonName(); Object other$commonName = other.getCommonName(); return this$commonName == null ? other$commonName == null : this$commonName.equals(other$commonName); }
    protected boolean canEqual(Object other) { return other instanceof Group; }
    public int hashCode() { int PRIME = 59; int result = 1; Object $id = getId(); result = result * 59 + ($id == null ? 43 : $id.hashCode()); Object $accountName = getAccountName(); result = result * 59 + ($accountName == null ? 43 : $accountName.hashCode()); Object $members = getMembers(); result = result * 59 + ($members == null ? 43 : $members.hashCode()); Object $name = getName(); result = result * 59 + ($name == null ? 43 : $name.hashCode()); Object $groupType = getGroupType(); result = result * 59 + ($groupType == null ? 43 : $groupType.hashCode()); Object $dn = getDn(); result = result * 59 + ($dn == null ? 43 : $dn.hashCode()); Object $commonName = getCommonName(); return result * 59 + ($commonName == null ? 43 : $commonName.hashCode()); }
    public String toString() { return "Group(id=" + getId() + ", accountName=" + getAccountName() + ", members=" + getMembers() + ", name=" + getName() + ", groupType=" + getGroupType() + ", dn=" + getDn() + ", commonName=" + getCommonName() + ")";
    }
}
