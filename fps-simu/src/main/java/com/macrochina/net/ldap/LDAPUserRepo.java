package com.macrochina.net.ldap;
import java.util.List;
import java.util.Optional;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface LDAPUserRepo extends LdapRepository<LDAPUser>
{
    public abstract Optional<LDAPUser> findByUsername(String paramString);

    public abstract Optional<LDAPUser> findByUsernameAndPassword(String paramString1, String paramString2);

    public abstract List<LDAPUser> findByUsernameLikeIgnoreCase(String paramString);

    public abstract LDAPUser findPersonByUid(String paramString);
}
