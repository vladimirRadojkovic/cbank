package com.mybank.dao;

import com.mybank.domain.Korisnik;
import com.mybank.domain.KorisnikPodaci;
import com.mybank.domain.KorisnikRoles;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Vladimir
 */
public interface UserDao {

    public List<Korisnik> getAllKorisnik();

    public Korisnik findByUserName(String username);

    public void registerUser(Korisnik korisnik, KorisnikPodaci korisnikPodaci, Set<KorisnikRoles> korisnikRole);

    public void deleteKorisnik(Korisnik korisnik);

    public KorisnikPodaci getKorisnikPodaci(String username);

    public void azurirajKorisnika(Korisnik korisnik, KorisnikPodaci korisnikPodaci);

    public void azurirajRole(KorisnikRoles korisnikRoles);

    public void removeKorisnikRole(Korisnik korisnik);

    public void registerNewActiveUser(Korisnik korisnik);

    public void azurirajKorisnikPodaci(String username, String photo);
}
