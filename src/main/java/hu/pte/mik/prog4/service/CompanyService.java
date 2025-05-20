package hu.pte.mik.prog4.service;
import hu.pte.mik.prog4.entity.CompanyEntity;
import hu.pte.mik.prog4.repository.CompanyRepository;
import java.util.List;
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService() {
        this.companyRepository = new CompanyRepository();
    }

    public List<CompanyEntity> findAll() {
        return this.companyRepository.listAll();
    }

    public CompanyEntity findById(Long id) {
        return this.companyRepository.findById(id);
    }

    public CompanyEntity save(Long id, String nev, String alapitasi_ev, String orszag, String ismert_termek){
        CompanyEntity company = new CompanyEntity();
        company.setId(id);
        company.setNev(nev);
        company.setAlapitasiEv(alapitasi_ev);
        company.setOrszag(orszag);
        company.setIsmertTermek(ismert_termek);
        return this.companyRepository.save(company);
    }

    public long getMovie(String movieId) {
        return 1;
    }
}
